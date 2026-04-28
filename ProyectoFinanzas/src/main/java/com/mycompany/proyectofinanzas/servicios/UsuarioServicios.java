/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofinanzas.servicios;

import com.mycompany.proyectofinanzas.repositorio.RepositorioUsuario;
import com.mycompany.proyectofinanzas.seguridad.Seguridad;

/**
 *
 * @author Pablo
 */
public class UsuarioServicios {
    private String usuarioActual;
    private RepositorioUsuario repo;

    public UsuarioServicios(RepositorioUsuario repo) {
        this.repo = repo;
    }

    public boolean login(String usuario, String password) {

        if (usuario == null || usuario.isEmpty()
                || password == null || password.isEmpty()) {
            return false;
        }

        String hash = Seguridad.hashPassword(password);

        return repo.ingresoLogin(usuario, hash);
    }
    
    public void setUsuarioActual(String usuario){
        this.usuarioActual = usuario;
    }
    
    public String getUsuario(){
        return usuarioActual;
    }

}
