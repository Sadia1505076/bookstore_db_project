package bookstore;

import bookstore.classes.bookrequired;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class InterSendrequController implements Initializable {

    @FXML
    private JFXButton back;
    @FXML
    private Label bill;
    @FXML
    private JFXTextField suppid;
    @FXML
    private JFXButton ok;
    @FXML
    private JFXButton cancel;

    String userid = null;
    String who = null;
    ObservableList<bookrequired> booksrequ = FXCollections.observableArrayList();
      
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    void setVariable(String user, String who,ObservableList<bookrequired> booksrequ) {
        this.who = who;
        userid = user;
        this.booksrequ=booksrequ;
    }

    @FXML
    private void goBack(ActionEvent event) {
        Parent root;
        Stage stage = null;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("employeeHome.fxml"));
            root = loader.load();
            Scene scene = new Scene(root);
            stage = new Stage(StageStyle.DECORATED);
            //stage.setTitle("RECEIPT");
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            System.out.println(ex.toString());
        }

    }

    @FXML
    private void handleok(ActionEvent event) throws ClassNotFoundException, SQLException {
        if (suppid.getText().isEmpty()) {
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setHeaderText(null);
            al.setContentText("PLEAZE ENTER A SUPPLIER!");
            al.showAndWait();
        }
        try {
            int a = Integer.parseInt(suppid.getText());
        } catch (NumberFormatException e) {
            //is = true;
            suppid.setText("* ENTER A VALID ID");
            suppid.setStyle("-fx-text-fill:#E74C3C;-fx-faint-focus-color: transparent; -fx-focus-color:rgba(255,0,0,0.2);");

        }
        Class.forName("oracle.jdbc.OracleDriver");
        Connection conn = new DBConnection().getConnection();

        String sql = "SELECT SUPPLIER_ID FROM SUPPLIER WHERE SUPPLIER_ID=?";
        PreparedStatement pst = null;
        ResultSet rs = null;
        pst = conn.prepareStatement(sql);
        pst.setString(1, suppid.getText());
        pst.executeUpdate();
        rs = pst.executeQuery();
        if (rs.next()) {
            Parent root;
            Stage stage = null;
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("bookBuyApplication.fxml"));
                root = loader.load();
                BookBuyApplicationController controller = loader.getController();
                controller.setVariable(userid, who,suppid.getText(),booksrequ);

                Scene scene = new Scene(root);
                stage = new Stage(StageStyle.DECORATED);
                //stage.setTitle("RECEIPT");
                stage.setScene(scene);
                stage.show();

            } catch (IOException ex) {
                System.out.println(ex.toString());
            }

        }
        else
        {
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setHeaderText(null);
            al.setContentText("SUPPLIER DOESN'T EXIST!");
            al.showAndWait();
        }

    }

    @FXML
    private void handleC(ActionEvent event) {
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }

}
