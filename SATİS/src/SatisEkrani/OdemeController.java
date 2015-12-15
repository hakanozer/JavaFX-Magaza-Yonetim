package SatisEkrani;

import Entities.Satis;
import Entities.Sepet;
import Entities.UrunProperty;
import Entities.Stoklar;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.Action;
import javax.swing.JOptionPane;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class OdemeController implements Initializable {

    @FXML
    private Label lblLogo;
    @FXML
    public String ref;
    @FXML
    public UrunProperty urun;
    @FXML
    public Label lblReferans;
    @FXML
    TableView table;
    @FXML
    private TableColumn<UrunProperty, String> adi;
    @FXML
    private TableColumn<UrunProperty, String> fiyat;
    @FXML
    private TableColumn<UrunProperty, String> adet;
    @FXML
    private TableColumn<UrunProperty, Boolean> terzi;
    @FXML
    private BigDecimal tot;
    @FXML
    private String per;
    @FXML
    private Label lblKasiyerAdi;
    @FXML
    private Label lblOdeme;
    @FXML
    private Label lblTarih;
    @FXML
    private Label lblTotal;
    @FXML
    private AnchorPane lblKasiyer;
    @FXML
    private Button btnOdemeOnay;
    @FXML
    private int perID;
    @FXML
    private Short odemeTipi;
    @FXML
    private Label lblBackground;
    ObservableList<UrunProperty> kliste = FXCollections.observableArrayList();
    DB db = new DB();
    ResultSet rs = null;

    SessionFactory sf = SatisHibernateUtil.getSessionFactory();
    Session sesi;
    Transaction tr;
    Satis st = new Satis();
    Boolean newValue;
    ArrayList<Integer> urunIDList = new ArrayList<>();

    public void getRefCode(String ref, String personel, String button, String total, int perID) {
        this.ref = ref;
        this.per = personel;
        this.perID = perID;
        if (button.contains("Nakit")) {
            this.odemeTipi = 1;
        } else if (button.contains("Kart")) {
            this.odemeTipi = 0;
        }
        lblReferans.setText(lblReferans.getText() + "  " + ref);
        lblKasiyerAdi.setText(lblKasiyerAdi.getText() + " " + personel);
        lblOdeme.setText(lblOdeme.getText() + "  " + button);
        lblTotal.setText(lblTotal.getText() + " " + total);

        lblTarih.setText(getCurrentTimeStamp());

        fillList();

    }

    public String getCurrentTimeStamp() {
        return new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());
    }

    @FXML
    public BigDecimal getTotal(BigDecimal total) {
        this.tot = total;
        return tot;
    }
    ArrayList<Integer> terziArray = new ArrayList<>();

    private void fillList() {
        try {
            rs = db.preProGetir("getProductsInSepet", ref);
            while (rs.next()) {
                UrunProperty ur = new UrunProperty();
                ur.setUAdi(rs.getString(2));
                ur.setUFiyat(rs.getBigDecimal(3));
                ur.setAdet(rs.getInt(1));
                ur.setDurum(rs.getBoolean(4));
                ur.setSepetID(rs.getInt("sepetID"));

                kliste.add(ur);
                urunIDList.add(rs.getInt(5));

                terziArray.add(ur.getSepetID());
                fillTable();

            }

        } catch (Exception e) {
            System.out.println("fillList Hatası : " + e);
        }
    }

    private void fillTable() {
        table.setItems(kliste);
        for (UrunProperty item : kliste) {

            adi.setCellValueFactory(new PropertyValueFactory<>("uAdi"));
            fiyat.setCellValueFactory(new PropertyValueFactory<>("uFiyat"));
            adet.setCellValueFactory(new PropertyValueFactory<>("adet"));
            Callback<TableColumn<UrunProperty, Boolean>, TableCell<UrunProperty, Boolean>> booleanCellFactory
                    = new Callback<TableColumn<UrunProperty, Boolean>, TableCell<UrunProperty, Boolean>>() {
                        @Override
                        public TableCell<UrunProperty, Boolean> call(TableColumn<UrunProperty, Boolean> p) {
                            return new BooleanCell();
                        }
                    };

            terzi.setCellValueFactory(new PropertyValueFactory<>("durum"));
            terzi.setCellFactory(booleanCellFactory);

        }
    }
    Integer rowNumber;

    @FXML
    public void ekle(ActionEvent evt) {
        try {
            updateTerzi();
            InsertSatis();
            updateStok();

            
              
     closeWindow();
            JOptionPane.showMessageDialog(null, "Ürün Satışı Tamamlanmıştır.");

          
 
            
            //       sf.close();
        } catch (Exception e) {
            System.out.println("Ürün Satışı Update Hatası : " + e);
            JOptionPane.showMessageDialog(null, "Ürün Satışı Sırasında Bir Sorun Oluştu!", "Hata", JOptionPane.WARNING_MESSAGE);
        }
         

   
    }
    private Stage stage;
     private Parent replaceSceneContent(String fxml) throws Exception {
        Parent page = (Parent) FXMLLoader.load(OdemeController.class.getResource(fxml), null, new JavaFXBuilderFactory());
        Scene scene = stage.getScene();
       page.getParent().getChildrenUnmodifiable().clear();
        if (scene == null) {
            scene = new Scene(page, 700, 450);
            scene.getStylesheets().add(OdemeController.class.getResource("SatisEkrani\\CSS.css").toExternalForm());
            stage.setScene(scene);
        } else {
            stage.getScene().setRoot(page);
        }
        stage.sizeToScene();
        return page;
    }

    private void updateStok() {
        Session session = sf.openSession();
        Transaction tx = null;
        try {

            for (Integer itemID : urunIDList) {
                tx = session.beginTransaction();
                Stoklar stok = getURunIDFromStock(itemID);

                int id = stok.getStokID();
     
               
            String hql = ("update Stoklar set kalanAdet=kalanAdet-1 where stokID=" + id);
             int a = session.createQuery(hql).executeUpdate();
                tx.commit();

            }

        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println("update Stok : " + e);
        } finally {
            session.close();
        }
    }

    private Stoklar getURunIDFromStock(Integer itemID) {
        Session session = sf.openSession();
        Transaction tx = null;
        Stoklar stok = null;

        try {
            tx = session.beginTransaction();
            Query query = session.getNamedQuery("Stoklar.findByUrunID").setParameter("urunID", itemID).setMaxResults(1);
            stok = (Stoklar) query.uniqueResult();
            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println("setID Stok : " + e);
        } finally {
            session.close();
        }
        return stok;

    }

    private void updateTerzi() {
        Session session = sf.openSession();
        Transaction tx = null;

        try {
           
            for (int list : terziUrunList) {
            tx = session.beginTransaction();
            String hql = ("update Sepet set durum=1 where refKodu='" + ref + "' and sepetID = " + list + "");
            session.createQuery(hql).executeUpdate();
             tx.commit();
            }
        
        

          

        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println("Terzi Update: " + e);
        } finally {
            session.close();
        }
    }
       ArrayList<Integer> terziUrunList = new ArrayList<>();
   
       
    private void InsertSatis() {

        Session session = sf.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Satis st = new Satis();

            st.setPersonelID(perID);
            st.setSepetRefKodu(ref);
            st.setFiyat(tot);
            st.setOdemeTipi(odemeTipi);
            st.setSTarih(new Date());

        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    int satir;

    class BooleanCell extends TableCell<UrunProperty, Boolean> {

        private CheckBox checkBox;

        public BooleanCell() {
            checkBox = new CheckBox();
//            checkBox.setDisable(true);
            tableSelectedItem();

            checkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    if (isEditing()) {
                        commitEdit(newValue == null ? false : newValue);

                    }
                    if (checkBox.isSelected()) {
                        TableRow row = getTableRow();
                        satir = row.getIndex();
                          
                        terziUrunList.add(terziArray.get(satir));
         
                    } else {
              
                        TableRow row = getTableRow();  
                        satir = row.getIndex();
                        terziUrunList.remove(terziArray.get(satir));
                     
                    }
           
                    

            }

            });

            this.setGraphic(checkBox);

            this.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            this.setEditable(true);
        }

        public void tableSelectedItem() {
            //            this.checkBox = new CheckBox();
            this.checkBox.setAlignment(Pos.CENTER);
            table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
                @Override
                public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                    //Check whether item is selected and set value of selected item to Label
                    if (table.getSelectionModel().getSelectedItem() != null) {
                        TableViewSelectionModel selectionModel = table.getSelectionModel();
                        ObservableList selectedCells = selectionModel.getSelectedCells();
                        TablePosition tablePosition = (TablePosition) selectedCells.get(0);
                        Object val = tablePosition.getTableColumn().getCellData(newValue);

                    }
                }
            });
        }

        @Override
        public void startEdit() {
            super.startEdit();
            if (isEmpty()) {
                return;
            }
            checkBox.setDisable(false);
            checkBox.requestFocus();
        }

        @Override
        public void cancelEdit() {
            super.cancelEdit();
            checkBox.setDisable(true);
        }

        public void commitEdit(Boolean value) {
            super.commitEdit(value);

//            checkBox.setDisable(true);
        }

        @Override
        public void updateItem(Boolean item, boolean empty) {
            super.updateItem(item, empty);
            if (!isEmpty()) {
                checkBox.setSelected(item);

            } else {
                setGraphic(null);
            }
        }

    }

    private Sepet SepetID(Session sesi) {
        Sepet sp = new Sepet();

        return sp;
    }

//    public void RowSelect(Integer rowNumber) {
//
//        for (int i = 0; i < urunIDList.size(); i++) {
//            System.out.println("ROW --- URUN ID : " + urunIDList.get(rowNumber));
//        }
//
//    }

    private void closeWindow() throws IOException {

        Stage st = (Stage) btnOdemeOnay.getScene().getWindow();

        st.close();

    }

    private void getSatisEkrani() throws IOException {
//        FXMLLoader loader = new FXMLLoader();
//        Pane p = loader.load(getClass().getResource("satisEkrani.fxml").openStream());
//        SatisController controller = (SatisController) loader.getController();
//        p.setUserData(controller);
//        //     SatisController sc = (SatisController) p.getUserData();
//        TableView table =(TableView) p.lookup("#table");
//        table.getItems().clear();
//        TextField txt=(TextField) p.lookup("#txtAlinanPara");
//        txt.setText(null);
 
    }
    @FXML private Button btnOdemeIptal;
    @FXML
    private void odemeIptal() throws IOException{
        
       
        Stage st = (Stage) btnOdemeIptal.getScene().getWindow();

        st.close();

    
    }
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblLogo.setGraphic(new ImageView("images/maviLOGO.png"));
        lblBackground.setGraphic(new ImageView("images/MAVI2.png"));

    }

}
