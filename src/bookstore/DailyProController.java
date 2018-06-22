
package bookstore;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;


public class DailyProController implements Initializable {

    @FXML
    private Label sale;
    @FXML
    private Label profit;
    void setVariables(String sale,String profit)
    {
        this.sale.setText(sale);
        this.profit.setText(profit);
        this.sale.setStyle("-fx-font-weight:bold");
        this.profit.setStyle("-fx-font-weight:bold");
    }
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
