package org.example;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class HTMLParser {
    private String url;
    private Document doc = null;
    private int cantidadPost;
    private int cantidadGet;
    public HTMLParser(String yourUrl){
        setUrl(yourUrl);
    }
    public String getUrl() {
        return url;
    }
    public void connectAndLoadDoc() throws IOException {
        this.doc = Jsoup.connect(getUrl()).get();
    };
    public void setUrl(String url) {
        if(url.isEmpty()){
            return;
        }
        this.url = url;
    }

    public int docCantidadDeLineas() {
        String[] numberOfLine = doc.toString().split("\r\n|\r|\n");
        return numberOfLine.length;
    }
    public int docCantidadTagP(){
        Elements cantidadParrafos = doc.getElementsByTag("p");
        return (int)cantidadParrafos.stream().count();
    }
    public int cantidadImagen(){
        Elements cantidadParafos = doc.getElementsByTag("p");
        Elements cantidadImagenes = cantidadParafos.select("img");
        return (int)cantidadImagenes.stream().count();
    }
    public void loadFormByPostAndGet(){
        Elements cantidadForms = doc.getElementsByTag("form");
        this.cantidadPost = 0;
        this.cantidadGet= 0;
        for(Element e: cantidadForms) {
            // System.out.println(e);
            if(e.attr("method" ).contentEquals("POST")|| e.attr("method" ).contentEquals("post"))
                this.cantidadPost++;
            if(e.attr("method" ).contentEquals("GET")||e.attr("method" ).contentEquals("get"))
                this.cantidadGet++;
        }
    }

    public int getCantidadPost() {
        return cantidadPost;
    }

    public int getCantidadGet() {
        return cantidadGet;
    }
    public Elements cantidadForms(){
        return doc.getElementsByTag("form");
    }
    public Document postRegeust() throws IOException {
        Elements forms = cantidadForms();
        for(Element e: forms){
            if(e.attr("method").contentEquals("POST")|| e.attr("method").contentEquals("post")){
                Connection.Response res = Jsoup.connect(getUrl()).data("asignatura","practica1").header("matricula","20102240")
                        .method(Connection.Method.POST)
                        .execute();
                System.out.println(res.parse());
            }
        }
        return null;
    }
}
