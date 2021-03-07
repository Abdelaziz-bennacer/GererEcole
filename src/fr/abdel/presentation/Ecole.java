package fr.abdel.presentation;

import fr.abdel.dao.AdresseService;
import fr.abdel.dao.EleveService;
import fr.abdel.dao.SalleService;
import fr.abdel.metier.Adresse;
import fr.abdel.metier.Eleve;
import fr.abdel.metier.Salle;

import java.time.LocalDate;

public class Ecole {

    public static void main(String[] args){

        SalleService s1 = new SalleService();
        AdresseService a1 = new AdresseService();
        /*Salle salle = new Salle("B001", "Chimie");
        Salle salle2 = new Salle("B002", "Anglais");
        Salle salle3 = new Salle("b003", "Littérature");

        s1.create(salle);
        s1.create(salle2);
        s1.create(salle3);*/
        //System.out.println(s1.findAll());
        Adresse adresse = new Adresse(27,"rue rouge", 62000,"arras", "france");
        //a1.create(adresse);
        EleveService e1 = new EleveService();
        Eleve eleve = new Eleve("beunret", "jean", LocalDate.of(1995,3,5),20, adresse);
        e1.create(eleve);
        //System.out.println(e1.findAll());

    }
}
