/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofinanzas.repositorio;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;


/**
 *
 * @author Pablo
 */
public class RepositorioMovimiento {
    private MongoCollection<Document> coleccion;

    public RepositorioMovimiento(MongoClient client) {
        MongoDatabase db = client.getDatabase("ProyectoFinanzasDB");
        this.coleccion = db.getCollection("movimientos");
    }
   
    public void guardarMovimiento(String usuario, String tipoDeMovimiento, double monto, String descripcion){
        Document doc = new Document("usuario", usuario)
                .append("tipoDeMovimiento", tipoDeMovimiento)
                .append("monto", monto)
                .append("descripcion", descripcion);
       
        coleccion.insertOne(doc);
    }

    public List<Document> obtenerMovimientos(String usuario) {
        List<Document> lista = new ArrayList<>();
        FindIterable<Document> documentos = coleccion.find(eq("usuario", usuario));
        for (Document doc : documentos) {
            lista.add(doc);
        }
        return lista;
    }

    public void eliminarMovimiento(String idMovimiento) {
        ObjectId id = new ObjectId(idMovimiento);
        coleccion.deleteOne(eq("_id", id));
    }

    public void actualizarMovimiento(String idMovimiento, String tipoDeMovimiento, double monto, String descripcion) {
        ObjectId id = new ObjectId(idMovimiento);
        Document nuevosDatos = new Document("tipoDeMovimiento", tipoDeMovimiento)
                .append("monto", monto)
                .append("descripcion", descripcion);
        coleccion.updateOne(eq("_id", id), new Document("$set", nuevosDatos));
    }
    
    public double sumaDeIngresos(String usuario){
        double total = 0;
        List<Document> movimientos = coleccion.find(new Document("usuario", usuario).append("tipoDeMovimiento", "Ingreso")).into(new ArrayList<>());
        
        for(Document suma : movimientos){
            total += suma.getDouble("monto");
        }
        return total;
    }
    
    public double sumaDeEgresos(String usuario){
        double totalEgre = 0;
        List<Document> movimientos = coleccion.find(new Document("usuario", usuario).append("tipoDeMovimiento", "Gastos")).into(new ArrayList<>());
        
        for(Document suma : movimientos){
            totalEgre += suma.getDouble("monto");
        }
        return totalEgre;
    }
    
}
