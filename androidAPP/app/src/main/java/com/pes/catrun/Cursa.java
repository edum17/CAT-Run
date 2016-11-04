package com.pes.catrun;

/**
 * Created by Victor on 25/10/2016.
 */

public class Cursa {

    String nom;
    String distancia;
    String data;
    String participants;

    public Cursa (){};

    public Cursa(String nom, String distancia, String data)
    {
        this.nom = nom;
        this.distancia = distancia;
        this.data = data;
    }
    public Cursa(String nom, String data)
    {
        this.nom = nom;
        this.data = data;
    }


}
