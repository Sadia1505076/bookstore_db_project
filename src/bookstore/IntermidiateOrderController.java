
package bookstore;
import bookstore.classes.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class IntermidiateOrderController implements Initializable {

    @FXML
    private JFXButton back;
    @FXML
    private Label bill;
    @FXML
    private JFXButton ok;
    @FXML
    private JFXButton cancel;
    ArrayList<JFXTextField> customer=new ArrayList<>();
    ArrayList<receipt> receipt=new ArrayList<>();
    DatePicker orderdate;
    double totalp=0;
    @FXML
    private JFXTextField paidmoney;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    void settingvariable(ArrayList<JFXTextField> cust, ArrayList<receipt> rece,DatePicker odate)
    {
        customer=cust;
        receipt=rece;
        orderdate=odate;
        
        for(int i=0;i<rece.size();i++)
        {
            double dis=(Double.parseDouble(rece.get(i).getDiscount())/100)*Double.parseDouble(rece.get(i).getAmount()) * Double.parseDouble(rece.get(i).getPrice());
            double vat=(Double.parseDouble(rece.get(i).getVat())/100)*Double.parseDouble(rece.get(i).getAmount()) * Double.parseDouble(rece.get(i).getPrice());
            double total = Double.parseDouble(rece.get(i).getAmount()) * Double.parseDouble(rece.get(i).getPrice())-dis+vat;
            totalp = totalp + total;
        }
        bill.setText("TOTAL BILL:        "+Double.toString(totalp));
        
    }

    @FXML
    private void goBack(ActionEvent event) {
        Parent root;
            Stage stage = null;
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("orderingBook.fxml"));
                root = loader.load();
                Scene scene = new Scene(root);
                stage = new Stage(StageStyle.DECORATED);
                stage.setTitle("RECEIPT");
                stage.setScene(scene);
                stage.show();

            } catch (IOException ex) {
                System.out.println(ex.toString());
            }
        
        
    }

    @FXML
    private void handleok(ActionEvent event) throws SQLException {
        
        if (Double.parseDouble(paidmoney.getText()) < totalp) {
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setHeaderText(null);
            al.setContentText("PLEAZE PAY SUFFICIENT MONEY!!");
            al.showAndWait();

        }
        else
        {
            Parent root;
            Stage stage = null;
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("receipt.fxml"));
                root = loader.load();
                ReceiptController controller = loader.getController();
                controller.settingvari(customer, receipt, orderdate, paidmoney.getText());
                //System.out.println(user);

                //controller.setMain(this);
                Scene scene = new Scene(root);
                stage = new Stage(StageStyle.DECORATED);
                stage.setTitle("RECEIPT");
                stage.setScene(scene);
                stage.show();

            } catch (IOException ex) {
                System.out.println(ex.toString());
            }
        }

    }

    @FXML
    private void handleC(ActionEvent event) {
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }

}
