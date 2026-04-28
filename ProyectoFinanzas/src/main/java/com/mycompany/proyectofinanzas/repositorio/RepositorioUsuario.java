/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofinanzas.repositorio;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import javax.swing.JOptionPane;
import org.bson.Document;

/**
 *
 * @author Pablo
 */
public class RepositorioUsuario {

    private final MongoCollection<Document> coleccion;

    public RepositorioUsuario(MongoClient client) {
        MongoDatabase db = client.getDatabase("ProyectoFinanzasDB");
        this.coleccion = db.getCollection("usuarios");
    }

    public void registrarUsuarioNuevo(String usuarioRegistro, String passwordHash) {
        if (usuarioRegistro.isEmpty() || passwordHash.isEmpty()) {
            JOptionPane.showInternalMessageDialog(null, "Los espacios de usuario o contraseña estan vacios, por favor digitelos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Document usuarioExistente = coleccion.find(eq("usuarioRegistro", usuarioRegistro)).first();
        if (usuarioExistente != null) {
            JOptionPane.showMessageDialog(null, "Usuario ya asignado, por favor seleccione otro.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            Document doc = new Document("usuarioRegistro", usuarioRegistro).append("passwordHash", passwordHash);
            coleccion.insertOne(doc);
            JOptionPane.showMessageDialog(null, "El usuario fue registrado de manera correcta.");
        }
    }

    public boolean ingresoLogin(String usuario, String passwordHash) {

        if (usuario == null || usuario.isEmpty()
                || passwordHash == null || passwordHash.isEmpty()) {
            return false;
        }

        Document usuarioExistente = coleccion.find(eq("usuarioRegistro", usuario)).first();

        if (usuarioExistente == null) {
            return false;
        }

        String hashGuardado = usuarioExistente.getString("passwordHash");

        return hashGuardado.equals(passwordHash);
    }

}
