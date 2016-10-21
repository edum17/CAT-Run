package com.pes.catrun;

import android.os.Bundle;
import android.os.StrictMode;
import android.widget.ListView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by Victor on 13/10/2016.
 */

public class OpenDataAPI {

    class Cursa{
        String nom;
        String distancia;
        String data;
        String participants;
    }
    String dirURL = "http://w10.bcn.es/APPS/asiasiacache/peticioXmlAsia?id=203";

    public ArrayList getListCurses()
    {
        ArrayList<Cursa> result = new ArrayList<>();

        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            URL url = new URL(dirURL);
            URLConnection conn = url.openConnection();
            InputStream is = conn.getInputStream();
            DocumentBuilderFactory docBF = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBF.newDocumentBuilder();
            InputSource inSource = new InputSource(new InputStreamReader(is, "ISO-8859-1"));
            Document doc = docBuilder.parse(inSource);
            doc.getDocumentElement().normalize();

            //System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName("acte");

            //System.out.println("----------------------------");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                // System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    String nomCursa =  eElement.getElementsByTagName("nom").item(0).getTextContent();
                    if(nomCursa.contains("Cursa")) {
                        Cursa cursa = new Cursa();
                        cursa.nom = eElement.getElementsByTagName("nom").item(0).getTextContent();
                        cursa.distancia = "A 0km de tu posición.";
                        cursa.data = eElement.getElementsByTagName("data_inici").item(0).getTextContent();
                        cursa.participants = "Hi participen 185 usuaris.";
                        result.add(cursa);
                        // System.out.println("Id : " + eElement.getElementsByTagName("id").item(0).getTextContent());
                        //System.out.println("Nom event: " + eElement.getElementsByTagName("nom").item(0).getTextContent());

                        //System.out.println("Data: " + eElement.getElementsByTagName("data_inici").item(0).getTextContent());
                    }
                    // Sy    stem.out.println("Id lloc: " + eElement.getElementsByTagName("id").item(1).getTextContent());
                    //System.out.println("Nom lloc: " + eElement.getElementsByTagName("nom").item(1).getTextContent());

                    //System.out.println("Districte: " + eElement.getElementsByTagName("districte").item(0).getTextContent());
                    //System.out.println("Carrer: " + eElement.getElementsByTagName("carrer").item(0).getTextContent());
                    //System.out.println("Codi Postal: " + eElement.getElementsByTagName("codi_postal").item(0).getTextContent());
                    //System.out.println("Municipi: " + eElement.getElementsByTagName("municipi").item(0).getTextContent());

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}