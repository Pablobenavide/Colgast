/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofinanzas.controlador;

import com.mongodb.client.MongoClient;
import com.mycompany.proyectofinanzas.baseDeDatos.BaseDeDatos;
import com.mycompany.proyectofinanzas.interfaz.IngresarMovimiento;
import com.mycompany.proyectofinanzas.interfaz.Inicio;
import com.mycompany.proyectofinanzas.interfaz.MenuInicialUsuario;
import com.mycompany.proyectofinanzas.interfaz.MetaUsuario;
import com.mycompany.proyectofinanzas.interfaz.MovimientoUsuario;
import com.mycompany.proyectofinanzas.interfaz.RegistroUsuario;
import com.mycompany.proyectofinanzas.interfaz.SimulacionInversion;
import com.mycompany.proyectofinanzas.interfaz.VerProgresoDeMeta;
import com.mycompany.proyectofinanzas.repositorio.RepositorioMeta;
import com.mycompany.proyectofinanzas.repositorio.RepositorioMovimiento;
import com.mycompany.proyectofinanzas.repositorio.RepositorioUsuario;
import com.mycompany.proyectofinanzas.servicios.MovimientoServicio;
import com.mycompany.proyectofinanzas.servicios.UsuarioServicios;
/**
 *
 * @author Pablo
 */
public class InterfazControlador {
    Inicio pantalla;
    RegistroUsuario registroFrom;
    MenuInicialUsuario menuInicialUsuario;
    IngresarMovimiento ingresarMovimiento;
    MetaUsuario metaUsuario;
    MovimientoUsuario movimientoUsuario;
    SimulacionInversion simulacion;
    VerProgresoDeMeta verProgreso;
    //repos
    RepositorioUsuario repo;
    RepositorioMovimiento repoMo;
    RepositorioMeta repoMe;
    
    UsuarioServicios usuarioActualC;
    MovimientoServicio movimientosUsuarioC;
    

    public InterfazControlador() {
        MongoClient client = BaseDeDatos.accesoConexion();
        //repositorios
        repo = new RepositorioUsuario(client);
        repoMo = new RepositorioMovimiento(client);
        repoMe = new RepositorioMeta(client);
        
        // servicios
        usuarioActualC = new UsuarioServicios(repo);
        movimientosUsuarioC = new MovimientoServicio(repoMo, repoMe);
        
        //interfaz
        pantalla = new Inicio(this, repo, usuarioActualC);
        registroFrom = new RegistroUsuario(this, repo);
        menuInicialUsuario = new MenuInicialUsuario(this);
        ingresarMovimiento = new IngresarMovimiento(this, usuarioActualC, movimientosUsuarioC);
        metaUsuario = new MetaUsuario(this,usuarioActualC, movimientosUsuarioC);
        movimientoUsuario = new MovimientoUsuario(this, usuarioActualC, movimientosUsuarioC);
        simulacion = new SimulacionInversion(this);
        verProgreso = new VerProgresoDeMeta(this,usuarioActualC,movimientosUsuarioC);
        
    }
    
    public void mostrarInterfazInical(){
        pantalla.setVisible(true);
        pantalla.setLocationRelativeTo(null);
        pantalla.setResizable(false);
    }
    
    public void mostrarFormularioDeRegistro(){
       pantalla.setVisible(false);
       registroFrom.setVisible(true);
       registroFrom.setLocationRelativeTo(null);
       registroFrom.setResizable(false);
    }
    public void btnmostrarInterfazInical(){
        registroFrom.setVisible(false);
        mostrarInterfazInical();
    }
    
    public void menuIngreso(){
        pantalla.setVisible(false);
        menuInicialUsuario.setVisible(true);
        menuInicialUsuario.setLocationRelativeTo(null);
        menuInicialUsuario.setResizable(false);
    }
    
    public void deslogueo(){
      menuInicialUsuario.setVisible(false);
      mostrarInterfazInical();
    }
    
    public void mostrarMenuInicial(){
        menuInicialUsuario.setVisible(true);
        menuInicialUsuario.setResizable(false);
        menuInicialUsuario.setLocationRelativeTo(null);
    }
    
    public void mostrarMenuIngreso(){
        menuInicialUsuario.setVisible(false);
        ingresarMovimiento.setVisible(true);
        ingresarMovimiento.setLocationRelativeTo(null);
        ingresarMovimiento.setResizable(false);
    }
    public void mostrarMeta(){
        menuInicialUsuario.setVisible(false);
        metaUsuario.setVisible(true);
        metaUsuario.setLocationRelativeTo(null);
        metaUsuario.setResizable(false);
    }
    
    public void mostrarProgreso(){
        menuInicialUsuario.setVisible(false);
        verProgreso.setVisible(true);
        verProgreso.setLocationRelativeTo(null);
        verProgreso.setResizable(false);
    }
    public void mostrarSimulacion(){
        menuInicialUsuario.setVisible(false);
        simulacion.setVisible(true);
        simulacion.setLocationRelativeTo(null);
        simulacion.setResizable(false);
    }
    
    public void mostrarMovimientos(){
        menuInicialUsuario.setVisible(false);
        movimientoUsuario.setVisible(true);
        movimientoUsuario.setLocationRelativeTo(null);
        movimientoUsuario.setResizable(false);
    }
    
    public void volverMenuInicialIngre(){
        ingresarMovimiento.setVisible(false);
        mostrarMenuInicial();
    }
    public void volverMenuInicialmeta(){
        metaUsuario.setVisible(false);
        mostrarMenuInicial();
    }
    public void volverMenuInicialPro(){
        verProgreso.setVisible(false);
        mostrarMenuInicial();
    }
    public void volverMenuInicialSimu(){
        simulacion.setVisible(false);
        mostrarMenuInicial();
    }
    
    public void volverMenuInicialMovimi(){
        movimientoUsuario.setVisible(false);
        mostrarMenuInicial();
    }
    
    public void setUsuarioLogueado(String usuario) {
        usuarioActualC.setUsuarioActual(usuario);
    }

}
