package com.sebastian.lab2.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sebastian.lab2.R;
import com.sebastian.lab2.adapters.PublicationAdapter;
import com.sebastian.lab2.models.Publication;
import com.sebastian.lab2.repositories.PublicationRepository;

import java.util.List;

public class ListPublicationActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    //private String name;
    //private PublicationAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_publication);

        //this.name= getIntent().getExtras().getString("name");
        //List<Publication> companiesResult= PublicationRepository.findCompanies(name);

        recyclerView =findViewById(R.id.publicationList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        PublicationAdapter adapter = new PublicationAdapter(this);

        List<Publication> publications = PublicationRepository.getPublications();
        adapter.setPublications(publications);

        recyclerView.setAdapter(adapter);

    }
}
