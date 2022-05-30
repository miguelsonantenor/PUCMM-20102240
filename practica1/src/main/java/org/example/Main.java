package org.example;


import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Scanner;

/**
 * A simple example, used on the jsoup website.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner =  new Scanner(System.in);
        System.out.print("Porfavor entre el website que quiere pasear: ");
        String link = scanner.nextLine();
        HTMLParser urlParser =  new HTMLParser(link);
        urlParser.connectAndLoadDoc();
       //Document doc = Jsoup.connect(urlParser.getUrl()).get();

        //a) Indicar la cantidad de lineas del recurso retornado
        System.out.println("La Cantidad de lineas del recurso es: "+urlParser.docCantidadDeLineas());

        //b) Indicar la cantidad de párrafos (p) que contiene el documento HTML.
        System.out.println("La cantidad de parafos (p) es: "+urlParser.docCantidadTagP());

        //c) Indicar la cantidad de imágenes (img) dentro de los párrafos que
        //contiene el archivo HTML.
        System.out.println("La cantidad de imagenes (img) dentro de los parafos es: "+urlParser.cantidadImagen());

        //d) indicar la cantidad de formularios (form) que contiene el HTML por
        //categorizando por el método implementado POST o GET.
        urlParser.loadFormByPostAndGet();
        System.out.println("Cantidad form method post: "+urlParser.getCantidadPost()+ " cantidad menthod get "+urlParser.getCantidadGet());

        //e) Para cada formulario mostrar los campos del tipo input y su
        //respectivo tipo que contiene en el documento HTML.
        int cantidadForm = 1;
        for(Element e: urlParser.cantidadForms()){
            System.out.println(e.tagName()+" "+cantidadForm++);
           System.out.println(e.select("input")
                   .removeAttr("name")
                   .removeAttr("id")
                   .removeAttr("value")
                   .removeAttr("placeholder")
                   .removeAttr("class")
                   .removeAttr("data-name")
                   .removeAttr("autocomplete")
                   .removeAttr("data-testid")
                   .removeAttr("aria-label"));
        }
        System.out.println("Document retornado despues del post request.");
        urlParser.postRegeust();
    }
}