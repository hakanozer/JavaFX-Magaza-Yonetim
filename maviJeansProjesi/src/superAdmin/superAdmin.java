package superAdmin;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class superAdmin extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/urunGiris/FXMLUrunler.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("Super Admin Girişi");
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void stop(){
       System.exit(1);
    }

}
