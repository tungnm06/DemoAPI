
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Administrator
 */
public class FormList extends JFrame {

    private Button btnNext;
    private Button btnBack;
    private JLabel lblText;
    private JTable table;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;
    private static int a;
    private static int b;

    public static void main(String[] args) throws IOException, MalformedURLException, ParserConfigurationException, SAXException {
        FormList fr1 = new FormList();
        a = 1;
        fr1.select(a);

        fr1.setVisible(true);

    }

    public FormList() throws MalformedURLException, IOException, ParserConfigurationException, SAXException {
        this.btnNext = new Button("Next");
        this.btnBack = new Button("Back");
        this.lblText = new JLabel();

        this.table = new JTable();
        this.tableModel = new DefaultTableModel();
        this.scrollPane = new JScrollPane(table);
        this.table.setLayout(null);
        this.a = a;
        this.b = b;
        this.setSize(620, 400);
        this.setLayout(null);
        this.scrollPane.setBounds(20, 20, 400, 200);
        this.btnBack.setBounds(300, 300, 50, 50);
        this.btnNext.setBounds(460, 300, 50, 50);
        this.lblText.setBounds(400, 300, 50, 50);
        this.tableModel.addColumn("Id");
        this.tableModel.addColumn("UserName");
        this.tableModel.addColumn("Email");
        this.tableModel.addColumn("Password");
        this.tableModel.addColumn("Birthday");
        this.tableModel.addColumn("Gender");
        this.tableModel.addColumn("Avatar");
        this.tableModel.addColumn("Status");


        lblText.setText("1/2");
        this.table.setModel(tableModel);
        this.table.setRowHeight(30);
//        table.getColumnModel().getColumn(2).setPreferredWidth(200);
//        table.getColumnModel().getColumn(2).setPreferredWidth(200);

        //
        this.add(lblText);
        this.add(btnBack);
        this.add(btnNext);
        this.add(scrollPane);
        this.btnNext.addActionListener(new Next());
        this.btnBack.addActionListener(new Back());
//        this.setVisible(true);
    }

    class Next implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            a = a + 1;
            
            if (a <= 2) {
                try {

                    FormList fr = new FormList();
                    fr.select(a);
                    fr.setVisible(true);

                } catch (IOException ex) {
                    Logger.getLogger(FormList.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParserConfigurationException ex) {
                    Logger.getLogger(FormList.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SAXException ex) {
                    Logger.getLogger(FormList.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                a=a-1;
            
        }

        }

    }

    class Back implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
             a = a - 1;
            if (a >= 1) {
                try {
                    FormList fr = new FormList();
                    fr.select(a);
                    fr.setVisible(true);

                } catch (IOException ex) {
                    Logger.getLogger(FormList.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParserConfigurationException ex) {
                    Logger.getLogger(FormList.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SAXException ex) {
                    Logger.getLogger(FormList.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                a=a+1;
            }
                
            

        }

        private void select(int c) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

    public boolean select(int a) throws MalformedURLException, IOException, ParserConfigurationException, SAXException {

        System.out.println("Start getting xml content ! ");
        String url = "https://youtube-api-challenger.appspot.com/xml/members?page=" + a + "&limit=5";

        PreparedStatement preSta;
        URL obj = new URL(url);

        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(con.getInputStream());
        doc.getDocumentElement().normalize();

        NodeList nodeList = doc.getElementsByTagName("Member");
//        int TT;
//        if (nodeList.getLength() % 5 == 0) {
//            TT = nodeList.getLength() / 5;
//
//        } else {
//            TT = nodeList.getLength() / 5 + 1;
//        }
//        System.out.println("" + TT);
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            Element element = (Element) node;
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Object[] str = new Object[]{element.getAttribute("id"),
                    element.getElementsByTagName("UserName").item(0).getTextContent(),
                    element.getElementsByTagName("Email").item(0).getTextContent(),
                    element.getElementsByTagName("Password").item(0).getTextContent(),
                    element.getElementsByTagName("Birthday").item(0).getTextContent(),
                    element.getElementsByTagName("Gender").item(0).getTextContent(),
                    element.getElementsByTagName("Avatar").item(0).getTextContent(),
                    element.getElementsByTagName("Status").item(0).getTextContent()};
                tableModel.addRow(str);
//                this.table.setModel(tableModel);
                lblText.setText(a + "/2");

            }
        }

        return true;
    }

}
