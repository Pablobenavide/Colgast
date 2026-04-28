/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofinanzas.baseDeDatos;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

/**
 *
 * @author Pablo
 */
public class BaseDeDatos {
    
     private static MongoClient mongoClient;
     
     public static MongoClient accesoConexion(){
         if(mongoClient == null){
             String url = "mongodb://localhost:27017";
             mongoClient = MongoClients.create(url);
             System.out.println("Base de datos conectada.");
         }
         return mongoClient;
     }
    
}
