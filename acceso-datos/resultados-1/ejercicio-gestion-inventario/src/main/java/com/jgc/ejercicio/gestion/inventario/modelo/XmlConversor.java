/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.jgc.ejercicio.gestion.inventario.modelo;

import com.jgc.ejercicio.gestion.inventario.modelo.objetos.FileProduct;
import com.jgc.ejercicio.gestion.inventario.modelo.objetos.Producto;
import java.io.File;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

/**
 *
 * @author JGC by Juan Garcia Cazallas
 * version 1.0
 * created on Oct 22, 2024
 */
public class XmlConversor extends FileProduct {
  private String XMLFilename;
  Document document;
  DocumentBuilderFactory docFactory;
  DocumentBuilder docBuilder;
  
  public XmlConversor (String inputPath, String inputMainNode) {
    super(inputPath);
    this.XMLFilename = inputMainNode;
    generateDOMContent(inputMainNode);
  }
  
  private void generateDOMContent (String inputMainNode) {
    try {
      this.docFactory = DocumentBuilderFactory.newInstance();
      docFactory.setIgnoringElementContentWhitespace(true);
      this.docBuilder = this.docFactory.newDocumentBuilder();
      
      DOMImplementation domImplementation = this.docBuilder.getDOMImplementation();
      this.document = (Document) domImplementation.createDocument(null, inputMainNode, null);
      this.document.setXmlVersion("1.0");
    } catch (ParserConfigurationException ex) {
      Logger.getLogger(XmlConversor.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  private Transformer preProcess () {
    Transformer domTransformer = null;
    try {
      domTransformer = TransformerFactory.newInstance().newTransformer();
      domTransformer.setOutputProperty(OutputKeys.INDENT, "yes");
    } catch (TransformerConfigurationException ex) {
      Logger.getLogger(XmlConversor.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    return domTransformer;
  }
  
  private void generateFileFromDOM (String inputFileName) {
    try {
      Source domSource = new DOMSource(this.document);
      Result domResult = new StreamResult(new File(inputFileName));
      
      preProcess().transform(domSource, domResult);
    } catch (TransformerException ex) {
      Logger.getLogger(XmlConversor.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  public Element addNodo (String inputNombreNodo) {
    Element mainNodo = this.document.createElement(inputNombreNodo);
    this.document.getDocumentElement().appendChild(mainNodo);
    return mainNodo;
  }
  
  public void addNodoTexto (String inputData, String inputText, Element nodoRoot) {
    Element data = this.document.createElement(inputData);
    Text dataText = this.document.createTextNode(inputText);
    
    data.appendChild(dataText);
    nodoRoot.appendChild(data);
  }
  
  public void exportDataToXML (List<Producto> inputListProduct) {
    for (Producto tempProduct : inputListProduct) {
      Element elemTempProduct = addNodo("Producto");
      
      addNodoTexto("Id", Long.toString(tempProduct.getId()), elemTempProduct);
      addNodoTexto("Nombre", tempProduct.getNombre(), elemTempProduct);
      addNodoTexto("Precio", Double.toString(tempProduct.getPrecio()), elemTempProduct);
      addNodoTexto("Stock", Integer.toString(tempProduct.getCantStock()), elemTempProduct);
    }
    
    generateFileFromDOM("./resources/" + this.XMLFilename + "_convert.xml");
  }
  
  public void setXMLFilename (String inputFilename) {
    this.XMLFilename = inputFilename;
  }
}
