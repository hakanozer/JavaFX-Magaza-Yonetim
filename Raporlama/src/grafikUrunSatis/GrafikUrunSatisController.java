package grafikUrunSatis;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import maviproje.MSSQLClass;

public class GrafikUrunSatisController implements Initializable {

    @FXML
    private PieChart piechart;
    @FXML
    private Label dilimPercent;

    MSSQLClass db = new MSSQLClass();
    ResultSet rs;

    public void btn() {
        ObservableList<Data> liste = FXCollections.observableArrayList();
        try {
            rs = db.preProGetir("grafikUrunSatis");
            while (rs.next()) {
                liste.add(new PieChart.Data(rs.getString("uAdi"), (rs.getInt("gelenAdet") - rs.getInt("kalanAdet"))));
            }
            piechart.setData(liste);

            for (PieChart.Data data : piechart.getData()) {

                data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {

                    double total = 0;
                    for (PieChart.Data d : piechart.getData()) {
                        total += d.getPieValue();
                    }

                    // dilimPercent.setTranslateX(event.getSceneX());
                    // dilimPercent.setTranslateY(event.getSceneY());
                    // dilimPercent.setText(data.getName() + "\n" + "% " + 100*String.valueOf(data.getPieValue()/total));
                    String text = String.format("%.1f%%", 100 * data.getPieValue() / total);
                    dilimPercent.setText(data.getName() + "\n" + text);
                    //dilimPercent.setText(text);

                });
            }

        } catch (SQLException ex) {
            Logger.getLogger(GrafikUrunSatisController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btn();
    }

}
