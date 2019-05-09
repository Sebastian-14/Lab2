package com.sebastian.lab2.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.sebastian.lab2.R;

public class MainActivity extends AppCompatActivity {
    private EditText bus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bus = findViewById(R.id.search_main_input);

    }
    public void callLogin(View view) {


        Intent intent = new Intent(this, ListPublicationActivity.class);
        startActivity(intent);


        //String busqueda = bus.getText().toString();

        //if(busqueda.isEmpty()){
            //Intent intent = new Intent(this, ListPublicationActivity.class);
            //intent.putExtra("busqueda",busqueda);
            //startActivity(intent);
        //}else{
            //Intent intent = new Intent(this, ListPublicationActivity.class);
            //intent.putExtra("name",busqueda);
        //}
    }
}
