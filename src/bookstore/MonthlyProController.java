
package bookstore;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;


public class MonthlyProController implements Initializable {

    @FXML
    private Label sale;
    @FXML
    private Label income;
    @FXML
    private Label bookbuyCost;
    @FXML
    private Label staffSal;
    @FXML
    private Label ManSal;
    @FXML
    private Label GMSal;
    @FXML
    private Label profit;

    void setVariables(String totalSale,String income,String bookBuyCost,String staffSal,String manSal,String gmsal,String Profit)
    {
        this.sale.setText(totalSale);
        this.income.setText(income);
        this.bookbuyCost.setText(bookBuyCost);
        this.staffSal.setText(staffSal);
        this.ManSal.setText(manSal);
        this.GMSal.setText(gmsal);
        this.profit.setText(Profit);
        
        this.sale.setStyle("-fx-font-weight:bold");
        this.profit.setStyle("-fx-font-weight:bold");
         this.income.setStyle("-fx-font-weight:bold");
        this.bookbuyCost.setStyle("-fx-font-weight:bold");
         this.staffSal.setStyle("-fx-font-weight:bold");
        this.GMSal.setStyle("-fx-font-weight:bold");
        this.ManSal.setStyle("-fx-font-weight:bold");
      
  
  
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
