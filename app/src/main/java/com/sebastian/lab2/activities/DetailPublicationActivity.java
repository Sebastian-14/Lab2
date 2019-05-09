package com.sebastian.lab2.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.sebastian.lab2.R;
import com.sebastian.lab2.models.Publication;
import com.sebastian.lab2.repositories.PublicationRepository;

public class DetailPublicationActivity extends AppCompatActivity {

    private Integer id;
    private ImageView pictureImage;
    private TextView fullnameText;
    private TextView info;
    private TextView categoria;
    private TextView address;
    private TextView email;

    private TextView number;
    private TextView web;

    private static final int CALL_PERMISION_REQUEST =100;
    private Button callButton;
    private Button webButton;
    private Button emailButton;
    private Button msmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_publication);

        pictureImage = findViewById(R.id.pictureImage);
        fullnameText = findViewById(R.id.fullname_text);
        info= findViewById(R.id.text_info);
        categoria = findViewById(R.id.category_text);
        address = findViewById(R.id.address_text);
        email = findViewById(R.id.email_text);

        emailButton= findViewById(R.id.button_msg);
        emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email();
            }
        });

        msmButton = findViewById(R.id.button_msm);
        msmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sms();
            }
        });

        number = findViewById(R.id.phone_text);
        callButton = findViewById(R.id.call_button);
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call();
            }
        });

        web=findViewById(R.id.url_text);
        webButton=findViewById(R.id.button_web);
        webButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWebPage();
            }
        });

        //Recuperando el id e ContactAdapter
        this.id = getIntent().getExtras().getInt("id");

        Publication publication = PublicationRepository.getPublicationById(id);

        if(publication != null) {
            int resourceid = getResources().getIdentifier(
                    publication.getLogo(),
                    "drawable",
                    getPackageName()
            );

            pictureImage.setImageResource(resourceid);
            fullnameText.setText(publication.getName());
            email.setText(publication.getEmail());
            info.setText(publication.getInfo());
            categoria.setText(publication.getCategory());
            address.setText(publication.getAddress());
            number.setText(publication.getPhone());
            web.setText(publication.getUrl());

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == CALL_PERMISION_REQUEST){
            if(permissions[0].equals(Manifest.permission.CALL_PHONE)){
                if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "Permisos Otorgados", Toast.LENGTH_SHORT).show();
                    call();
                }
            }
        }
    }

    private void call(){
        //Verificar si tenemos permiso
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, CALL_PERMISION_REQUEST);
            return;
        }

        String phonenumber = number.getText().toString();

        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel: "+ phonenumber));

        //Si el intent puede ser resuelto por el
        if(intent.resolveActivity(getPackageManager())!=null) {
            startActivity(intent);
        }
    }

    private void openWebPage() {
        String url = web.getText().toString();

        //if(url.isEmpty()){
        //    Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show();
        //}

        if (!url.startsWith("http://") || !url.startsWith("https://")) {
            url="https://"+url;
        }

        //Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    private void email(){

        String recipientList = email.getText().toString();
        String[] recipients = recipientList.split(",");
        //example

        Intent intent= new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, recipients);

        intent.setType("message/rfc234");
        startActivity(Intent.createChooser(intent,"choose an email client"));

    }

    private void sms(){
        Intent sendIntent = new Intent(Intent.ACTION_VIEW);
        sendIntent.putExtra("cuerpo_sms", "default content");
        sendIntent.setType("vnd.android-dir/mms-sms");
        startActivity(sendIntent);
    }
}
