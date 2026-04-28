/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofinanzas.servicios;
import com.mycompany.proyectofinanzas.repositorio.RepositorioMeta;
import com.mycompany.proyectofinanzas.repositorio.RepositorioMovimiento;
import java.util.List;
import org.bson.Document;

/**
 *
 * @author Pablo
 */
public class MovimientoServicio {
    private RepositorioMovimiento repoMo;
    private RepositorioMeta repoMe;

    public MovimientoServicio(RepositorioMovimiento repoMo, RepositorioMeta repoMe) {
        this.repoMo = repoMo;
        this.repoMe = repoMe;
    }

    public void guardarMovimeinto(String usuario, String tipoDeMovimiento, double monto, String descripcion) {
        repoMo.guardarMovimiento(usuario, tipoDeMovimiento, monto, descripcion);
    }

    public List<Document> obtenerMovimientosPorUsuario(String usuario) {
        return repoMo.obtenerMovimientos(usuario);
    }

    public void eliminarMovimiento(String id) {
        repoMo.eliminarMovimiento(id);
    }

    public void actualizarMovimiento(String id, String tipoDeMovimiento, double monto, String descripcion) {
        repoMo.actualizarMovimiento(id, tipoDeMovimiento, monto, descripcion);
    }
    
    public void cargarMetaAUsuario(String usuarioActual, String nombreDeMeta, double valorMeta, int timpoDemeta, double ingresosParaMeta, double gastosParaMeta){
        repoMe.guardarMeta(usuarioActual, nombreDeMeta, valorMeta, timpoDemeta, ingresosParaMeta, gastosParaMeta);
    }
    
    public List<Document> obtenerMetaDeUsuario(String usuario){
        return repoMe.obtenerMetasPorUsuario(usuario);
    }
    
    public double obtenerTotalDeSumaDeIngresos(String usuario){
        return repoMo.sumaDeIngresos(usuario);
    }
    public double obtenerTotalDeSumaDeEgresos(String usuario){
        return repoMo.sumaDeEgresos(usuario);
    }
}
