/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.jgc.ejercicio.gestion.inventario.controlador;

import com.jgc.ejercicio.gestion.inventario.modelo.BinaryConversor;
import com.jgc.ejercicio.gestion.inventario.modelo.LectorProduct;
import com.jgc.ejercicio.gestion.inventario.modelo.XmlConversor;
import com.jgc.ejercicio.gestion.inventario.modelo.objetos.Producto;
import com.jgc.ejercicio.gestion.inventario.vista.InterfazVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 *
 * @author JGC by Juan Garcia Cazallas
 * version 1.0
 * created on Oct 22, 2024
 */
public class ControlFormat implements ActionListener {
  private final InterfazVista vista;
  private final XmlConversor modeloXML;
  private final LectorProduct modeloLect;
  private final BinaryConversor modeloBin;
  
  private String filePath;
  
  public ControlFormat(InterfazVista inputVista, XmlConversor inputModeloXML,LectorProduct inputModeloLect, BinaryConversor inputModeloBin) {
    this.vista = inputVista;
    this.modeloXML = inputModeloXML;
    this.modeloBin = inputModeloBin;
    this.modeloLect = inputModeloLect;
      
    this.vista.setControladorFormato(this);
  }
  
  public void actionPerformed(ActionEvent evento) {
    switch (evento.getActionCommand()) {
      case InterfazVista.CONVERTIR_BINARY_XML -> {
        this.filePath = this.vista.getRuta();
        this.modeloLect.setPath(filePath);
        
        this.filePath = this.vista.getRuta();
        this.modeloXML.setPath(filePath);
        
        List<Producto> listProducts = this.modeloLect.returnListProduct();
        this.modeloXML.exportDataToXML(listProducts);
      }
      
      case InterfazVista.CONVERTIR_XML_BINARY -> {
        this.filePath = this.vista.getRuta();
        this.modeloBin.setPath(filePath);
        
        String newFilename = this.vista.getRuta();
        this.modeloBin.setBinFilename(newFilename);
        
        this.modeloBin.exportDataToBinary();
        this.vista.operacionExitosa();
      }
    }
  }
}
