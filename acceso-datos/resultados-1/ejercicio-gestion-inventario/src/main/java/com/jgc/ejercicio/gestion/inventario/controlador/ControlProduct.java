/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.jgc.ejercicio.gestion.inventario.controlador;

import com.jgc.ejercicio.gestion.inventario.modelo.LectorProduct;
import com.jgc.ejercicio.gestion.inventario.modelo.WriterProduct;
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
public class ControlProduct implements ActionListener {

  private final InterfazVista vista;
  private final LectorProduct modeloLect;
  private final WriterProduct modeloWrite;
  
  private String filePath;

  
  public ControlProduct(InterfazVista inputVista, LectorProduct inputModeloLect, WriterProduct inputModeloWrite) {
    this.vista = inputVista;
    this.modeloLect = inputModeloLect;
    this.modeloWrite = inputModeloWrite;
      
    this.vista.setControladorProducto(this);
  }

  

  public void actionPerformed(ActionEvent evento) {
    switch (evento.getActionCommand()) {
      case InterfazVista.CREAR_PRODUCTO -> {
        this.filePath = this.vista.getRuta();
        this.modeloWrite.setPath(filePath);
        
        this.vista.escribeResultado("  - Introduce los datos del Producto:");
        long tempId = this.vista.getIdentificador();
        String tempNombre = this.vista.getNombre();
        double tempPrecio = this.vista.getPrecio();
        int tempCantStock = this.vista.getCantStock();
        
        Producto tempProduct = this.modeloWrite.createProduct(tempId, tempNombre, tempPrecio, tempCantStock);
        this.modeloWrite.saveProduct(tempProduct);
        this.vista.operacionExitosa();
      }
      
      case InterfazVista.LEER_PRODUCTOS -> {
        this.filePath = this.vista.getRuta();
        this.modeloLect.setPath(filePath);
        
        this.vista.escribeResultado("  - Lista de Productos:");
        
        List<String> listProducts = this.modeloLect.readProducts();
        for (int i=0; i<listProducts.size(); i++) {
          this.vista.escribeResultado(listProducts.get(i));
        }
        
        this.vista.operacionExitosa();
      }
      
      case InterfazVista.LEER_PRODUCTO_ID -> {
        this.filePath = this.vista.getRuta();
        this.modeloLect.setPath(filePath);
        
        this.vista.escribeResultado("  - Introduce el ID del Producto: ");
        long tempId = this.vista.getIdentificador();
        
        Producto tempProduct = this.modeloLect.readProduct(tempId);
        this.vista.escribeResultado(tempProduct.toString());
      }
      
      case InterfazVista.MODIFICAR_PRODUCTO_ID -> {
        this.filePath = this.vista.getRuta();
        this.modeloWrite.setPath(filePath);
        
        this.vista.escribeResultado("  - Introduce el ID del Producto: ");
        long tempId = this.vista.getIdentificador();
        
        this.vista.escribeResultado("  - Datos a Modificar:");
        double tempPrecio = this.vista.getPrecio();
        int tempStock = this.vista.getCantStock();
        
        this.modeloWrite.modifyProduct(tempId, tempPrecio, tempStock);
        this.vista.operacionExitosa();
      }
      
      case InterfazVista.BORRAR_PRODUCTO -> {
        this.filePath = this.vista.getRuta();
        this.modeloWrite.setPath(filePath);
        
        this.vista.escribeResultado("  - Introduce el ID del Producto: ");
        long tempId = this.vista.getIdentificador();
        
        this.modeloWrite.deleteProduct(tempId);
        this.vista.operacionExitosa();
      }
    }
  }
}
        
        
