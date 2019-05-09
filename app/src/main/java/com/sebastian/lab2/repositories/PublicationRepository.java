package com.sebastian.lab2.repositories;

import com.sebastian.lab2.models.Publication;

import java.util.ArrayList;
import java.util.List;
public class PublicationRepository {
    private static List<Publication> publications = new ArrayList<>();

    static {
        publications = new ArrayList<>();
        publications.add(new Publication(100,"Moda","Adidas","Jr. Pelotillehue 53-48 Int 84","012242625","informes@adidas.com","www.adidas.pe","logo_adidas","…"));
        publications.add(new Publication(200,"Comida Rapida","Bembos","Mall Santa Anita","077454512","informes@bembos.com","www.bembos.com.pe","logo_bembos","…"));
        publications.add(new Publication(300,"Suepermercado","Wong","Av. Frutales, La Molina","077454672","informes@wong.com","www.wong.pe","logo_wong","…"));
        publications.add(new Publication(400,"Moda","Rebook","Jr. Pelotillehue 53-48 Int 84","012242625","informes@rebook.com","www.rebook.pe","logo_rebook","…"));
        publications.add(new Publication(500,"Supermercado","Plaza Vea","Mall Santa Anita","947912247","informes@plazavea.com","www.plazavea.com.pe","logo_plazavea","…"));
        publications.add(new Publication(600,"Suepermercado","Tottus","Av. Frutales, La Molina","877454672","informes@tottus.com","www.tottus.pe","logo_tottus","…"));
        publications.add(new Publication(700,"Moda","Supra","Jr. Pelotillehue 53-48 Int 84","012242625","informes@supra.com","www.supra.pe","logo_supra","…"));
        publications.add(new Publication(800,"Comida Rapida","McDonalds","Mall Santa Anita","015472317","informes@mcdonalds.com","www.mcdonalds.com.pe","logo_macdonalds","…"));
        publications.add(new Publication(900,"Comida Rapida","KFC","Av. Frutales, La Molina","972454672","informes@kfc.com","www.kfc.pe","logo_kfc","…"));
    }

    public static List<Publication> getPublications() {
        return publications;
    }



    public static Publication getPublicationById(Integer id){
        for(Publication publication: publications){
            if(publication.getId().equals(id)){
                return publication;
            }
        }
        return null;
    }

    public static List<Publication> findCompanies(String name) {
        List<Publication> companiesByCategory= new ArrayList<>();
        for (Publication company: publications) {
            if(company.getCategory().toLowerCase().indexOf(name.toLowerCase())>-1) {
                companiesByCategory.add(new Publication(company.getId(), company.getCategory(), company.getName(),
                        company.getAddress(), company.getPhone(), company.getEmail(), company.getUrl(), company.getLogo(),
                        company.getInfo()));
            }
        }
        return companiesByCategory;
    }
}
