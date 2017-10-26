
import java.io.File;
import java.io.StringReader;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import jdk.internal.org.xml.sax.InputSource;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Administrator
 */
public class DomParserDemo {

    public static void main(String[] args) {
        try {
            File inputFile = new File("src//newXMLDocument.xml");
            
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            System.out.println("Root elemet :" + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("student");
            System.out.println("-------------------------");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    System.out.println("Student roll no : " + eElement.getAttribute("rollno"));

                    System.out.println("Firstsname : " + eElement.getElementsByTagName("firstname").item(0).getTextContent());

                    System.out.println("Last Name : " + eElement.getElementsByTagName("classname").item(0).getTextContent());

                    System.out.println("Nick Name : " + eElement.getElementsByTagName("nickname").item(0).getTextContent());

                    System.out.println("Marks : " + eElement.getElementsByTagName("marks").item(0).getTextContent());
                    
                    
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }





}
