package com.sebastian.lab2.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sebastian.lab2.R;
import com.sebastian.lab2.activities.DetailPublicationActivity;
import com.sebastian.lab2.models.Publication;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class PublicationAdapter extends RecyclerView.Adapter<PublicationAdapter.ViewHolder> {

    private static final String TAG = PublicationAdapter.class.getSimpleName();

    private AppCompatActivity activity;

    private List<Publication> publications;

    //Pasamos referencia a la activity
    public PublicationAdapter(AppCompatActivity activity){
        this.activity = activity;
        publications = new ArrayList<>();
    }

    public void setPublications(List<Publication> publications){
        this.publications = publications;
    }

    @NonNull
    @Override //Que layout va usar y mantenerlo precargado
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_publication, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override //Como enlazamos
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        //Segun la posicion recupera elementos del array
        final Publication publication = this.publications.get(position);

        viewHolder.fullnameText.setText(publication.getName());
        viewHolder.addressText.setText(publication.getAddress());
        //viewHolder.number.setText(publication.getPhone());

        //viewHolder.pictureImage.set

        Context context = viewHolder.itemView.getContext();
        int resourceid = context.getResources().getIdentifier(publication.getLogo(),"drawable",context.getPackageName());
        viewHolder.pictureImage.setImageResource(resourceid);

        //viewHolder.pictureImage.setImageResource(resourceid);

        //Definiendo el evento onclick
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: "+ publication);
                //Redirigir al otro activity con el id del contacto
                Intent intent = new Intent(v.getContext(), DetailPublicationActivity.class);
                intent.putExtra("id",publication.getId());
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return publications.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        //Es mejor hacerlo aqui,en lugar del bind, ya que este se carga una vez
        //Pasando referencia a ViewHolder.
        //all debería estar aquí
        ImageView pictureImage;
        TextView fullnameText;
        TextView addressText;
        //TextView number;

        public ViewHolder(@NonNull View  itemView){
            super(itemView);
            pictureImage = itemView.findViewById(R.id.pictureImage);
            fullnameText = itemView.findViewById(R.id.fullname_text);
            addressText = itemView.findViewById(R.id.address_text);
            //number = itemView.findViewById(R.id.number_text);


        }
    }
}
