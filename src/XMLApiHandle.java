
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.ws.Response;
import jdk.internal.org.xml.sax.InputSource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Administrator
 */
public class XMLApiHandle {
    
    public static void main(String[] args) throws MalformedURLException, IOException, Exception {
        System.out.println("Start getting xml content ! ");
        String url = "https://youtube-api-challenger.appspot.com/xml/members?page=4&limit=1";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(con.getInputStream());
        doc.getDocumentElement().normalize();

        NodeList nodeList = doc.getElementsByTagName("Member");
        for (int i = 0; i < nodeList.getLength(); i++) {
            System.out.println("----------------------");
            Node node = nodeList.item(i);
            Element element = (Element) node;
            System.out.println("ID :" + element.getAttribute("id"));
            System.out.println("UserName :" + element.getElementsByTagName("UserName").item(0).getTextContent());
            System.out.println("Email :" + element.getElementsByTagName("FullName").item(0).getTextContent());
            System.out.println("Password :" + element.getElementsByTagName("Password").item(0).getTextContent());
            System.out.println("Birthday :" + element.getElementsByTagName("Birthday").item(0).getTextContent());
            System.out.println("Gender :" + element.getElementsByTagName("Gender").item(0).getTextContent());
            System.out.println("Avatar :" + element.getElementsByTagName("Avatar").item(0).getTextContent());
            System.out.println("Status :" + element.getElementsByTagName("Status").item(0).getTextContent());
        }
        
    }
    
}
