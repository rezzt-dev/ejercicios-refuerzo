/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.jgc.ejercicio.gestion.inventario.modelo;

import com.jgc.ejercicio.gestion.inventario.modelo.objetos.FileProduct;
import com.jgc.ejercicio.gestion.inventario.modelo.objetos.Producto;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author JGC by Juan Garcia Cazallas
 * version 1.0
 * created on Oct 22, 2024
 */
public class BinaryConversor extends FileProduct {
  private final WriterProduct writer;
  
  private String BinFilename;
  Document document;
  DocumentBuilderFactory docFactory;
  DocumentBuilder docBuilder;
  private String xmlFilename;
  
  public BinaryConversor (String inputPath, String inputFileName) {
    super(inputPath);
    this.xmlFilename = inputPath;
    this.BinFilename = inputFileName;
    this.writer = new WriterProduct("./resources/" + BinFilename + "_convert.dat");
    initializeDocumentBuilder();
  }
  
  private void initializeDocumentBuilder() {
    try {
      this.docFactory = DocumentBuilderFactory.newInstance();
      docFactory.setIgnoringElementContentWhitespace(true);
      this.docBuilder = this.docFactory.newDocumentBuilder();
    } catch (ParserConfigurationException ex) {
      Logger.getLogger(BinaryConversor.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  private void loadXmlFile() {
    try {
      File xmlFile = new File(getPath()+ "/" + xmlFilename);
      this.document = this.docBuilder.parse(xmlFile);
      this.document.getDocumentElement().normalize();
    } catch (SAXException | IOException ex) {
      Logger.getLogger(BinaryConversor.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  private String getTagValue (String inputTag, Element inputElement) {
    NodeList nodeList = inputElement.getElementsByTagName(inputTag).item(0).getChildNodes();
    Node dataNode = nodeList.item(0);
    
    if (dataNode != null) {
      return dataNode.getNodeValue();
    } else return null;
  }
  
  private Producto getProducto (Node inputNode) {
    Producto returnProduct = new Producto();
    
    if (inputNode.getNodeType() == Node.ELEMENT_NODE) {
      Element tempElement = (Element) inputNode;
      
      returnProduct.setId(Long.parseLong(getTagValue("Id", tempElement)));
      returnProduct.setNombre(getTagValue("Nombre", tempElement));
      returnProduct.setPrecio(Double.parseDouble(getTagValue("Precio", tempElement)));
      returnProduct.setCantStock(Integer.parseInt(getTagValue("Stock", tempElement)));
    }
    
    return returnProduct;
  }
  
  private List<Producto> getProductos () {
    List<Producto> listProducts = new ArrayList<>();
    NodeList nodeList = this.document.getElementsByTagName("Producto");
    
    for (int i=0; i<nodeList.getLength(); i++) {
      listProducts.add(getProducto(nodeList.item(i)));
    }
    
    return listProducts;
  }
  
  public void exportDataToBinary () {
    loadXmlFile();
    List<Producto> listProducts = getProductos();
    
    for (Producto tempProduct : listProducts) {
      writer.saveProduct(tempProduct);
    }
  }
  
  public void setXmlFilename(String inputFilename) {
    this.xmlFilename = inputFilename;
  }
  
  public void setBinFilename (String inputFilename) {
    this.BinFilename = inputFilename;
  }
}
