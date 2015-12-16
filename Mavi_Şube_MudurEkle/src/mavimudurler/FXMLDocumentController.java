/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mavimudurler;

import java.net.URL;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.transform.Scale;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author java6sabah
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private TextField txtadi;
    @FXML
    private TextField txtsoyadi;
    @FXML
    private TextField txttelefon;
    @FXML
    private TextField txtcep;
    @FXML
    private TextField txtmail;
    @FXML
    private TextArea txtadres;
    @FXML
    private TextField txtusername;
    @FXML
    private TextField txtsifre;
    @FXML
    private TableView<Mudurler> mudurtable;
    @FXML
    private TableColumn<Mudurler, String> mad;
    @FXML
    private TableColumn<Mudurler, String> msoyad;
    @FXML
    private TableColumn<Mudurler, String> mtel;
    @FXML
    private TableColumn<Mudurler, String> mctel;
    @FXML
    private TableColumn<Mudurler, String> mmail;
    @FXML
    private TableColumn<Mudurler, String> madres;
    @FXML
    private TableColumn<Mudurler, String> musername;
    @FXML
    private TableColumn<Mudurler, String> msifre;
    @FXML
    private TableColumn<Mudurler, Date> mtarih;
    @FXML
    private TableColumn<Mudurler, String> msubeadi;
    @FXML
    private ListView<String> subeliste;

    ObservableList<String> subebaslik = FXCollections.observableArrayList();
    ObservableList<Subeler> subelist = FXCollections.observableArrayList();

    ObservableList<Mudurler> data;
    ArrayList<Mudurler> list = new ArrayList<>();
    //sonradan eklenen
    ArrayList<Subeler> slist = new ArrayList<>();

    int secilenId;
    //sonradan eklenen
    int secilenSubeIDliste=0;
    String secilenSubeAdiliste="";
    int secilenSubeIDtablo;
    String secilensubeAditablo="";
    @FXML
    private void kaydetButtonAction(ActionEvent event) {
        dataInsert();
    }

    @FXML
    private void selectAction(ActionEvent event) {
        dataSelect();
    }

    @FXML
    private void silButtonAction(ActionEvent event) {
        dataSil();
    }

    @FXML
    private void guncelleButtonAction(ActionEvent event) {
        
        dataUpdate();

    }
    @FXML
    private void temizle(ActionEvent event){
        txtadi.setText("");
        txtsoyadi.setText("");
        txttelefon.setText("");
        txtcep.setText("");
        txtmail.setText("");
        txtadres.setText("");
        txtusername.setText("");
        txtsifre.setText("");
    }
    
    @FXML
    public void dataInsert() {
        try {
            boolean durum=true;
            String adi = txtadi.getText();
            String soyadi = txtsoyadi.getText();
            String tel = txttelefon.getText();
            String ctel = txtcep.getText();
            String mail = txtmail.getText();
            String adres = txtadres.getText();
            String user = txtusername.getText();
            String sifree=txtsifre.getText().toString();
            String sifre = sifreleme(txtsifre.getText().toString());
            
            
            if (secilenSubeIDliste == 0) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Lütfen Şube Seçiniz .", ButtonType.OK);
                alert.setHeaderText(null);
                alert.setTitle("Uyarı");
                alert.showAndWait();
            } 
            else {
                

                if (adi.equals("") || soyadi.equals("") || tel.equals("") || ctel.equals("") || mail.equals("") || adres.equals("") || user.equals("") || sifree.equals("")) {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "boş alan bırakılmaz", ButtonType.OK);
                    alert.setHeaderText(null);
                    alert.setTitle("Uyarı");
                    alert.showAndWait();
                    durum=false;
                }
                else if(sayikontrol(txttelefon.getText())==false){
                Alert alert = new Alert(Alert.AlertType.WARNING, "telefon numarası karakter olamaz", ButtonType.OK);
                alert.setHeaderText(null);
                alert.setTitle("Uyarı");
                alert.showAndWait(); 
                durum=false;
                
                }
                else if(sayikontrol(txtcep.getText())==false){
                Alert alert = new Alert(Alert.AlertType.WARNING, "cep telefonu numarası karakter olamaz ", ButtonType.OK);
                alert.setHeaderText(null);
                alert.setTitle("Uyarı");
                alert.showAndWait(); 
                durum=false;
                
                }
                else if(!mail(txtmail.getText())){
                Alert alert = new Alert(Alert.AlertType.WARNING, "hatalı mail formatı", ButtonType.OK);
                alert.setHeaderText(null);
                alert.setTitle("Uyarı");
                alert.showAndWait();
                durum=false;
                
                }
                else {
                    System.out.println("-----------------------------------------------------------------------------------------------------------------------");
                    SessionFactory sf = new Configuration().configure().buildSessionFactory();
                    Session sess = sf.openSession();
                    Transaction ts = sess.beginTransaction();
                    Query qee = sess.createQuery("from Mudurler");
                    Iterator ite = qee.iterate();
                    while (ite.hasNext()) {
                    Mudurler obj = (Mudurler) ite.next();
                    System.out.println(obj);
                    if(obj.getMKuladi().equals(txtusername.getText())){
                    Alert alert = new Alert(Alert.AlertType.WARNING, "kullanıcı adı öncedem kullanılmış başka kullanıcı adı giriniz", ButtonType.OK);
                    alert.setHeaderText(null);
                    alert.setTitle("Uyarı");
                    alert.showAndWait();
                    txtusername.setText("");
                    durum =false;
                    break;
                    }
                    
                    
                    }
                   
                }
                if(durum==true){
                    SessionFactory sf = new Configuration().configure().buildSessionFactory();
                    Session sess = sf.openSession();
                    Transaction ts = sess.beginTransaction();
                    Mudurler k = new Mudurler();                                        
                    k.setMID(Integer.SIZE);
                    k.setMAdi(txtadi.getText());
                    k.setSubeAdi(secilenSubeAdiliste);
                    k.setSubeID(secilenSubeIDliste);//sorun çıkarsa else in altına al .
                    k.setMSoyadi(txtsoyadi.getText());
                    k.setMTelefon(txttelefon.getText());
                    k.setMCep(txtcep.getText());
                    k.setMMail(txtmail.getText());
                    k.setMAdres(txtadres.getText());
                    k.setMKuladi(txtusername.getText());
                    k.setMSifre(sifre);
                    Date dt = new Date();
                    k.setMTarih(dt);
                    
                    sess.save(k);
                    ts.commit();
                    sess.close();
                    mudurtable.getSelectionModel().clearSelection();
                    list.clear();
                    tabloDoldur();
                    txtadi.setText("");
                    txtsoyadi.setText("");
                    txttelefon.setText("");
                    txtcep.setText("");
                    txtmail.setText("");
                    txtadres.setText("");
                    txtusername.setText("");
                    txtsifre.setText("");
                //    durum=true;
                    
                }
                }
         
            }
         catch (HibernateException he) {
            System.out.println(he);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        mad.setCellValueFactory(new PropertyValueFactory<>("mAdi"));
        msoyad.setCellValueFactory(new PropertyValueFactory<>("mSoyadi"));
        mtel.setCellValueFactory(new PropertyValueFactory<>("mTelefon"));
        mctel.setCellValueFactory(new PropertyValueFactory<>("mCep"));
        mmail.setCellValueFactory(new PropertyValueFactory<>("mMail"));
        madres.setCellValueFactory(new PropertyValueFactory<>("mAdres"));
        musername.setCellValueFactory(new PropertyValueFactory<>("mKuladi"));
        msifre.setCellValueFactory(new PropertyValueFactory<>("mSifre"));
        mtarih.setCellValueFactory(new PropertyValueFactory<>("mTarih"));
        msubeadi.setCellValueFactory(new PropertyValueFactory<>("subeAdi"));
        data = FXCollections.observableArrayList();
        tabloDoldur();
        listeDoldur();
        System.out.println(list);
    }

    @FXML
    public void tabloDoldur() {

        data = FXCollections.observableArrayList();

        try {
            SessionFactory sf = new Configuration().configure().buildSessionFactory();
            Session sess = sf.openSession();
            Transaction ts = sess.beginTransaction();

            Query qee = sess.createQuery("from Mudurler");
            Iterator ite = qee.iterate();
            while (ite.hasNext()) {
                Mudurler obj = (Mudurler) ite.next();
                System.out.println(obj);
                data.add(obj);
                list.add(obj);
            }

            mudurtable.setItems(data);
            ts.commit();
            sess.close();
        } catch (HibernateException he) {
            System.out.println(he);
        }
    }

    @FXML
    public void listeDoldur() {

        try {
            SessionFactory sf = new Configuration().configure().buildSessionFactory();
            Session sess = sf.openSession();
            Transaction ts = sess.beginTransaction();

            Query qee = sess.createQuery("from Subeler");
            Iterator ite = qee.iterate();
            while (ite.hasNext()) {
                Subeler obj = (Subeler) ite.next();
                System.out.println(obj);
                //sonradan eklenen
                slist.add(obj);
                subelist.add(obj);
            }

            for (Subeler sube : subelist) {
                subebaslik.add(sube.getSBaslik());
            }

            subeliste.setItems(subebaslik);
            ts.commit();
            sess.close();
        } catch (HibernateException he) {
            System.out.println(he);
        }
    }

    @FXML
    public void dataSil() {

        int idIndex = mudurtable.getSelectionModel().getSelectedIndex();
        Mudurler md = list.get(idIndex);

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
                    mudurtable.getSelectionModel().clearSelection();
                    list.clear();
                    tabloDoldur();
                    txtadi.setText("");
                    txtsoyadi.setText("");
                    txttelefon.setText("");
                    txtcep.setText("");
                    txtmail.setText("");
                    txtadres.setText("");
                    txtusername.setText("");
                    txtsifre.setText("");
                } catch (HibernateException he) {
                    System.out.println(he);
                }
            }
        });
    }

    //sonradan eklenen

    @FXML
    public void dataUpdate() {
        System.out.println("data update çalıştı");
        boolean durum=true;
            String adi = txtadi.getText();
            String soyadi = txtsoyadi.getText();
            String tel = txttelefon.getText();
            String ctel = txtcep.getText();
            String mail = txtmail.getText();
            String adres = txtadres.getText();
            String user = txtusername.getText();
            String sifree=txtsifre.getText().toString();
            String sifre = sifreleme(txtsifre.getText().toString());
        try {
            if (adi.equals("") || soyadi.equals("") || tel.equals("") || ctel.equals("") || mail.equals("") || adres.equals("") || user.equals("")  ) {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Lütfen boş alanları doldurunuz.", ButtonType.OK);
                    alert.setHeaderText(null);
                    alert.setTitle("Uyarı");
                    alert.showAndWait();
                    durum=false;
                }
                else if(!mail(txtmail.getText())){
                Alert alert = new Alert(Alert.AlertType.WARNING, "hatalı mail formatı", ButtonType.OK);
                alert.setHeaderText(null);
                alert.setTitle("Uyarı");
                alert.showAndWait();
                durum=false;
                
                }
                else {
                    System.out.println("-----------------------------------------------------------------------------------------------------------------------");
                    SessionFactory sf = new Configuration().configure().buildSessionFactory();
                    Session sess = sf.openSession();
                    Transaction ts = sess.beginTransaction();
                    Query qee = sess.createQuery("from Mudurler");
                    Iterator ite = qee.iterate();
                    while (ite.hasNext()) {
                    Mudurler obj = (Mudurler) ite.next();
                    System.out.println(obj);
                    if(obj.getMKuladi().equals(txtusername.getText())){
                        if(secilenkuladi.equals(txtusername.getText())){break;}
                    Alert alert = new Alert(Alert.AlertType.WARNING, "kullanıcı adı öncedem kullanılmış başka kullanıcı adı giriniz", ButtonType.OK);
                    alert.setHeaderText(null);
                    alert.setTitle("Uyarı");
                    alert.showAndWait();
                    txtusername.setText("");
                    durum =false;
                    break;
                    }
                    
                    
                    }
                   
                }
            
            
            if(durum==true){
            SessionFactory sf = new Configuration().configure().buildSessionFactory();
            Session sess = sf.openSession();
            Transaction ts = sess.beginTransaction();

            Mudurler md = new Mudurler();
            md.setMID(secilenId);
            // md.setSubeID(secilenSubeID);
            
            if(!secilenSubeAdiliste.equals(secilensubeAditablo)){
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Şube degiştirmek istediginizden emin misiniz?", ButtonType.YES, ButtonType.NO);
                alert.showAndWait();

                if (alert.getResult() == ButtonType.YES) {
                    if(secilenSubeIDliste==0){
                    md.setSubeAdi(secilensubeAditablo);
                    md.setSubeID(secilenSubeIDtablo);
                    }
                    else{
                    md.setSubeAdi(secilenSubeAdiliste);
                    md.setSubeID(secilenSubeIDliste);
                    }
                    
                }

                if (alert.getResult() == ButtonType.NO) {
                    md.setSubeAdi(secilensubeAditablo);
                    md.setSubeID(secilenSubeIDtablo);
                }
                
            }
            else{
            md.setSubeID(secilenSubeIDtablo);
            md.setSubeAdi(secilensubeAditablo);
            }
            
            md.setMAdi(txtadi.getText());
            md.setMSoyadi(txtsoyadi.getText());
            md.setMTelefon(txttelefon.getText());
            md.setMCep(txtcep.getText());
            md.setMMail(txtmail.getText());
            md.setMAdres(txtadres.getText());
            md.setMKuladi(txtusername.getText());
            if(sifree.equals("")){
                md.setMSifre(secilenssifre);
            }
            else{
            md.setMSifre(sifre);
            }
            
            
            
            Date date = new Date();
            md.setMTarih(date);
            
            sess.update(md);
            ts.commit();
            sess.close();
            mudurtable.getSelectionModel().clearSelection();
            list.clear();
            tabloDoldur();
            txtadi.setText("");
            txtsoyadi.setText("");
            txttelefon.setText("");
            txtcep.setText("");
            txtmail.setText("");
            txtadres.setText("");
            txtusername.setText("");
            txtsifre.setText("");
            }
            
        } catch (Exception e) {
            System.out.println("data güncelleme hatası : " + e);
        }

    }
    String secilenkuladi="";
    String secilenssifre="";
    
    @FXML
    public void dataSelect() {
        try {

            int idIndex = mudurtable.getSelectionModel().getSelectedIndex();

            Mudurler mudurObj = list.get(idIndex);
            secilenId = mudurObj.getMID();
            secilenSubeIDtablo = mudurObj.getSubeID();
            secilensubeAditablo=mudurObj.getSubeAdi();
            secilenkuladi=mudurObj.getMKuladi();
            txtadi.setText(mudurObj.getMAdi());
            txtsoyadi.setText(mudurObj.getMSoyadi());
            txttelefon.setText(mudurObj.getMTelefon());
            txtcep.setText(mudurObj.getMCep());
            txtmail.setText(mudurObj.getMMail());
            txtadres.setText(mudurObj.getMAdres());
            txtusername.setText(mudurObj.getMKuladi());
            secilenssifre=mudurObj.getMSifre();

            System.out.println(secilenId);
            System.out.println("secilen sube İD tablo: "+secilenSubeIDtablo);
            System.out.println("secilen kuladi"+secilenkuladi);
            System.out.println(mudurObj.getMAdi());
        } catch (Exception e) {
            System.out.println("Seçme hatası : " + e);
        }
    }

    //sonradan eklenen 
    @FXML
    public void listSelect() {
        try {
            int idIndex = subeliste.getSelectionModel().getSelectedIndex();
            secilenSubeAdiliste=subeliste.getSelectionModel().getSelectedItem();
            
            System.out.println("sube adi: "+secilenSubeAdiliste);
            System.out.println("index" + idIndex);
            Subeler subeobj = slist.get(idIndex);
            secilenSubeIDliste = subeobj.getSubeID();
            
            System.out.println("secilen sube id liste "+secilenSubeIDliste );
        } catch (Exception e) {
        }

    }

    public String sifreleme(String sifre) {

        String md5sifre = "";
        try {
            MessageDigest messageDigestNesnesi = MessageDigest.getInstance("MD5");
            messageDigestNesnesi.update(sifre.getBytes());
            byte messageDigestDizisi[] = messageDigestNesnesi.digest();
            StringBuffer sb16 = new StringBuffer();

            for (int i = 0; i < messageDigestDizisi.length; i++) {
                sb16.append(Integer.toString((messageDigestDizisi[i] & 0xff) + 0x100, 16).substring(1));

            }
            md5sifre = sb16.toString();
            System.out.println("Parolanın Şifrelenmiş Hali:(16) " + md5sifre);

        } catch (Exception ex) {
            System.err.println(ex);
        }

        return md5sifre;

    }
     public static boolean mail(String email) {
           String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
           java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
           java.util.regex.Matcher m = p.matcher(email);
           return m.matches();
    }
    @FXML
    private void printIt(ActionEvent event) {
        Printer printer = Printer.getDefaultPrinter();
        PageLayout pageLayout = printer.createPageLayout(Paper.A4, PageOrientation.LANDSCAPE, Printer.MarginType.EQUAL);
        mudurtable.getTransforms().add(new Scale(1.0, 1.0));
        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null) {
            boolean successPrintDialog = job.showPrintDialog(null);
            if (successPrintDialog) {
                boolean success = job.printPage(pageLayout, mudurtable);
                if (success) {
                    job.endJob();
                    System.out.println("Yazdırıldı");
                    
                }
            }
        }
        
    }
      public static boolean sayikontrol(String data) {
        boolean durum = false;
        for (int i = 0; i < data.length(); i++) {
            if (Character.isDigit(data.charAt(i))) {
                durum = true;
            } else {
                durum = false;
                break;
            }
        }
        return durum;
        
        
        

    }
}
