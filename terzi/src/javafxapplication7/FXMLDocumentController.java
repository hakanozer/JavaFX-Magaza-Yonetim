/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication7;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author USer
 */
public class FXMLDocumentController implements Initializable {


    @FXML
    private TextField txtadi;
    @FXML
    private TextField txtsoyadi;
    @FXML
    private TextField txttelefon;
    @FXML
    private TextField txtcep;

    @FXML
    private TextArea txtadres;
    @FXML
    private TableView<Terziler> terzitable;
    @FXML
    private TableColumn<Terziler, String> mid;

    @FXML
    private TableColumn<Terziler, String> tadi;

    @FXML
    private TableColumn<Terziler, String> tsoyadi;

    @FXML
    private TableColumn<Terziler, String> ttelefon;

    @FXML
    private TableColumn<Terziler, String> tcep;
    @FXML
    private TableColumn<Terziler, String> tadres;
    @FXML
    private TableColumn<Terziler, Date> ttarih;
    ObservableList<Terziler> data;
    ArrayList<Terziler> list = new ArrayList<>();
    int secilenId;
    @FXML
    private Button sil;
    
    @FXML
    private void kaydetButtonAction(ActionEvent event) {
       dataInsert();
    }
    @FXML
    private void silButtonAction(ActionEvent event) {
        dataSil();
    }
    private void guncelleButtonAction(ActionEvent event) {
        dataUpdate();
    
    }

    public void dataInsert() {
        try {
            SessionFactory sf = new Configuration().configure().buildSessionFactory();
            Session sess = sf.openSession();
            Transaction ts = sess.beginTransaction();
            Terziler k = new Terziler();

            k.setTerziID(Integer.SIZE);
            k.setMudurID(1);
            k.setTAdi(txtadi.getText());
            k.setTSoyadi(txtsoyadi.getText());
            k.setTCep(txttelefon.getText());
            k.setTTelefonu(txtcep.getText());
            k.setTAdres(txtadres.getText());
            Date dt=new Date();
            k.setTTarih(dt);

           
            sess.save(k);
            ts.commit();
            sess.close();
            terzitable.getSelectionModel().clearSelection();
            list.clear();
            tabloDoldur();

        } catch (HibernateException he) {
            System.out.println(he);
        }
    }
    public void dataSil() {
    
        int idIndex = terzitable.getSelectionModel().getSelectedIndex();
        Terziler md = list.get(idIndex);

        Alert alert = new Alert(Alert.AlertType.WARNING, "Silmek için OK, iptal etmek için CANCEL butonuna basınız.", ButtonType.OK, ButtonType.CANCEL);
        alert.setHeaderText("Silmek istediğinizden emin misiniz?");
        alert.setTitle("Uyarı");

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                try {
                    SessionFactory sf = new Configuration().configure().buildSessionFactory();
                    Session sess = sf.openSession();
                    Transaction ts = sess.beginTransaction();
                    sess.delete(md);
                    ts.commit();
                    sess.flush();
                    terzitable.getSelectionModel().clearSelection();
                    list.clear();
                    tabloDoldur();
                    
                } catch (HibernateException he) {
                    System.out.println(he);
                }
            }
        });
    }
    
    public void dataUpdate() {
            try {
              SessionFactory sf = new Configuration().configure().buildSessionFactory();
            Session sess = sf.openSession();
            Transaction ts = sess.beginTransaction();
            
            
            Terziler tz = new Terziler();
            tz.setTerziID(secilenId);
            tz.setMudurID(1);
            tz.setTAdi(txtadi.getText());
            tz.setTSoyadi(txtsoyadi.getText());
            tz.setTCep(txtcep.getText());
            tz.setTTelefonu(txttelefon.getText());
          Date dt=new Date();
          tz.setTTarih(dt);
          sess.update(tz);
          ts.commit();
          sess.close();
          terzitable.getSelectionModel().clearSelection();
                list.clear();
                tabloDoldur();
                
            } catch (Exception e) {
            }
            
        
    }
    
    
    
    @FXML
    public void dataSelect() {
        try {
            System.out.println("Çalıştım");    
        int idIndex = terzitable.getSelectionModel().getSelectedIndex();

        Terziler mudurID = list.get(idIndex);
        secilenId = mudurID.getMudurID();
        
        txtadi.setText(mudurID.getTAdi());
        txtsoyadi.setText(mudurID.getTSoyadi());
        txttelefon.setText(mudurID.getTTelefonu());
        txtcep.setText(mudurID.getTCep());
        
        txtadres.setText(mudurID.getTAdres());
       
        
        System.out.println(secilenId);
        } catch (Exception e) {
            System.out.println("Seçme hatası : " + e);
        }
    }  
    public void tabloDoldur() {
        System.out.println("başladı");
        mid.setCellValueFactory(new PropertyValueFactory<>("mudurID"));
        tadi.setCellValueFactory(new PropertyValueFactory<>("tAdi"));
        tsoyadi.setCellValueFactory(new PropertyValueFactory<>("tSoyadi"));
        ttelefon.setCellValueFactory(new PropertyValueFactory<>("tTelefonu"));
        tcep.setCellValueFactory(new PropertyValueFactory<>("tCep"));
        tadres.setCellValueFactory(new PropertyValueFactory<>("tAdres"));
        ttarih.setCellValueFactory(new PropertyValueFactory<>("tTarih"));

        data = FXCollections.observableArrayList();
        data = FXCollections.observableArrayList();

        try {
            System.out.println("tablo girildi");
            SessionFactory sf = new Configuration().configure().buildSessionFactory();
            Session sess = sf.openSession();
            Transaction ts = sess.beginTransaction();

            Query qee = sess.createQuery("from Terziler");
            Iterator ite = qee.iterate();
            while (ite.hasNext()) {
                Terziler obj = (Terziler) ite.next();
                System.out.println(obj);
                data.add(obj);
                list.add(obj);
            }

            terzitable.setItems(data);
            ts.commit();
            sess.close();
        } catch (HibernateException he) {
            System.out.println(he);
        }
    
    
            
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("tablo girilmedi");
        tabloDoldur();

//        System.out.println(list);

    }

}
