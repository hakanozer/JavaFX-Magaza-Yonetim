package SatisEkrani;

import Entities.Satis;
import Entities.Sepet;
import Entities.UrunProperty;
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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.JOptionPane;
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
    private int refID;
    @FXML
    private Short odemeTipi;
    @FXML
    private Label lblBackground;
    ObservableList<UrunProperty> kliste = FXCollections.observableArrayList();
    DB db = new DB();
    ResultSet rs = null;

    SessionFactory sf;
    Session sesi;
    Transaction tr;
    Satis st = new Satis();
    Boolean newValue;
    ArrayList<Integer> ar = new ArrayList<>();

    public void getRefCode(String ref, String personel, String button, String total, int refID) {
        this.ref = ref;
        this.per = personel;
        this.refID = refID;
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

    private void fillList() {
        try {
            rs = db.preProGetir("getProductsInSepet", ref);
            while (rs.next()) {
                UrunProperty ur = new UrunProperty();
                ur.setUAdi(rs.getString(2));
                ur.setUFiyat(rs.getBigDecimal(3));
                ur.setAdet(rs.getInt(1));
                ur.setDurum(rs.getBoolean(4));

                kliste.add(ur);
                ar.add(rs.getInt(5));
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
    public void ekle() {
        try {
            sf = SatisHibernateUtil.getSessionFactory();
            sesi = sf.getCurrentSession();
            tr = sesi.beginTransaction();
            Satis st = InsertSatis(sesi);
            sesi.save(st);
            tr.commit();
            JOptionPane.showMessageDialog(null, "Ürün Satışı Tamamlanmıştır.");

            closeWindow();
            sf.close();
        } catch (Exception e) {
            System.out.println("Ürün Satışı Update Hatası : " + e);
            JOptionPane.showMessageDialog(null, "Ürün Satışı Sırasında Bir Sorun Oluştu!", "Hata", JOptionPane.WARNING_MESSAGE);
        }

    }

    private void updateTerzi(Boolean newValue) {
        SessionFactory sff = SatisHibernateUtil.getSessionFactory();
        Session sessi = sff.getCurrentSession();
        Transaction trr = sessi.beginTransaction();
        Sepet sp = new Sepet();
        sp.setDurum(newValue);
        sessi.update(sp);
        tr.commit();
    }

    private Satis InsertSatis(Session sesi) {
        Satis st = new Satis();

        st.setPersonelID(refID);
        st.setSepetRefKodu(ref);
        st.setFiyat(tot);
        st.setOdemeTipi(odemeTipi);
        st.setSTarih(new Date());
        return st;
    }

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
//                    updateTerzi(newValue);

                    TableRow row = getTableRow();
                    if (row != null) {
                        rowNumber = row.getIndex();

//                                   
                        RowSelect(rowNumber);
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

    public void RowSelect(Integer rowNumber) {

        for (int i = 0; i < ar.size(); i++) {
            System.out.println("ROW --- URUN ID : " + ar.get(rowNumber));
        }

    }

    private void closeWindow() {
        Stage st = (Stage) btnOdemeOnay.getScene().getWindow();
        st.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblLogo.setGraphic(new ImageView("images/maviLOGO.png"));
        lblBackground.setGraphic(new ImageView("images/MAVI2.png"));
//        btnOdemeOnay.setOnAction((event) -> {

//        });
    }

}
