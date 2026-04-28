/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofinanzas.repositorio;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

/**
 *
 * @author Pablo
 */
public class RepositorioMeta {
    private MongoCollection<Document> coleccionMeta;
    
    public RepositorioMeta(MongoClient client){
        MongoDatabase db = client.getDatabase("ProyectoFinanzasDB");
        this.coleccionMeta = db.getCollection("meta");
    }
    
    public void guardarMeta(String usuarioActual, String nombreDeMeta, double valorMeta, int timpoDemeta, double ingresosParaMeta, double gastosParaMeta){
        Document doc = new Document("usuario", usuarioActual).append("nombreDeMeta", nombreDeMeta).append("valorMeta", valorMeta).append("timpoDemeta", timpoDemeta).append("ingresosParaMeta", ingresosParaMeta).append("gastosParaMeta", gastosParaMeta);
        coleccionMeta.insertOne(doc);
    }
    
    public List<Document> obtenerMetasPorUsuario(String usuarioActual){
        return coleccionMeta.find(new Document("usuario", usuarioActual)).into(new ArrayList<>());
    }
    
}
