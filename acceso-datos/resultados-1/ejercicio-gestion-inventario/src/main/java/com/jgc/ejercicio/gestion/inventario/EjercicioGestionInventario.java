/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.jgc.ejercicio.gestion.inventario;

import com.jgc.ejercicio.gestion.inventario.controlador.ControlFormat;
import com.jgc.ejercicio.gestion.inventario.controlador.ControlProduct;
import com.jgc.ejercicio.gestion.inventario.modelo.BinaryConversor;
import com.jgc.ejercicio.gestion.inventario.modelo.LectorProduct;
import com.jgc.ejercicio.gestion.inventario.modelo.WriterProduct;
import com.jgc.ejercicio.gestion.inventario.modelo.XmlConversor;
import com.jgc.ejercicio.gestion.inventario.vista.InterfazVista;
import com.jgc.ejercicio.gestion.inventario.vista.VentanaTexto;

/**
 *
 * @author JGC by Juan Garcia Cazallas
 */
public class EjercicioGestionInventario {
  public static void main(String[] args) {
    String basePath = "";
    InterfazVista vista = new VentanaTexto();
    
    LectorProduct modeloLec = new LectorProduct(basePath);
    WriterProduct modeloWrt = new WriterProduct(basePath);
    ControlProduct controlProduct = new ControlProduct(vista, modeloLec, modeloWrt);
    
    XmlConversor modeloXML = new XmlConversor(basePath, "Productos");
    BinaryConversor modeloBin = new BinaryConversor(basePath, "Productos");
    ControlFormat controlFormat = new ControlFormat(vista, modeloXML, modeloLec, modeloBin);
    
    vista.arranca();
  }
}
