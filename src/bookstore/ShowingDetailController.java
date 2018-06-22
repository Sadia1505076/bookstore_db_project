
package bookstore;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ShowingDetailController implements Initializable {
    ArrayList<String> temp=new ArrayList<>();
    String what=null;
    @FXML
    private VBox vbox;
    @FXML
    private JFXButton close;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    void settingvariable(ArrayList<String> temp,String what)
    {
        this.temp=temp;
        this.what=what;
        if(what.equals("book"))
        {
            Label bookid=new Label("BOOK ID: "+temp.get(0));
            bookid.setPadding(new Insets(30, 0, 0, 50));
            bookid.setStyle("-fx-text-fill:#f2f2f2;-fx-font-weight:bold;-fx-font-size:16px");
            
           
            Label bookname=new Label("BOOK NAME: "+temp.get(1));
            bookname.setPadding(new Insets(10, 0, 0, 50));
            bookname.setStyle("-fx-text-fill:#f2f2f2;-fx-font-weight:bold;-fx-font-size:16px");
            
            Label amount=new Label("AMOUNT: "+temp.get(2));
            amount.setPadding(new Insets(10, 0, 0, 50));
            amount.setStyle("-fx-text-fill:#f2f2f2;-fx-font-weight:bold;-fx-font-size:16px");
            
            Label edi=new Label("EDITION: "+temp.get(3));
            edi.setPadding(new Insets(10, 0, 0, 50));
            edi.setStyle("-fx-text-fill:#f2f2f2;-fx-font-weight:bold;-fx-font-size:16px");
            
            Label sell=new Label("SELLING PRICE: "+temp.get(4));
            sell.setPadding(new Insets(10, 0, 0, 50));
            sell.setStyle("-fx-text-fill:#f2f2f2;-fx-font-weight:bold;-fx-font-size:16px");
            
            Label dis=new Label("DISCOUNT: "+temp.get(5));
            dis.setPadding(new Insets(10, 0, 0, 50));
            dis.setStyle("-fx-text-fill:#f2f2f2;-fx-font-weight:bold;-fx-font-size:16px");
            
            Label vat=new Label("VAT: "+temp.get(6));
            vat.setPadding(new Insets(10, 0, 0, 50));
            vat.setStyle("-fx-text-fill:#f2f2f2;-fx-font-weight:bold;-fx-font-size:16px");
            
            vbox.getChildren().addAll(bookid,bookname,amount,edi,sell,dis,vat);
        }
        if(what.equals("customer"))
        {
            Label CUSTid=new Label("CUSTOMER ID: "+temp.get(0));
            CUSTid.setPadding(new Insets(30, 0, 0, 50));
            CUSTid.setStyle("-fx-text-fill:#f2f2f2;-fx-font-weight:bold;-fx-font-size:16px");
            
           
            Label CUSTname=new Label("CUSTOMER NAME: "+temp.get(1));
            CUSTname.setPadding(new Insets(10, 0, 0, 50));
            CUSTname.setStyle("-fx-text-fill:#f2f2f2;-fx-font-weight:bold;-fx-font-size:16px");
            
            Label CUSTADD=new Label("ADDRESS: "+temp.get(2));
            CUSTADD.setPadding(new Insets(10, 0, 0, 50));
            CUSTADD.setStyle("-fx-text-fill:#f2f2f2;-fx-font-weight:bold;-fx-font-size:16px");
            
            Label CUSTEMAIL=new Label("EMAIL ID: "+temp.get(3));
            CUSTEMAIL.setPadding(new Insets(10, 0, 0, 50));
            CUSTEMAIL.setStyle("-fx-text-fill:#f2f2f2;-fx-font-weight:bold;-fx-font-size:16px");
            
            Label PHONE_NO=new Label("PHONE NO: "+temp.get(4));
            PHONE_NO.setPadding(new Insets(10, 0, 0, 50));
            PHONE_NO.setStyle("-fx-text-fill:#f2f2f2;-fx-font-weight:bold;-fx-font-size:16px");
            
            
            vbox.getChildren().addAll(CUSTid,CUSTname,CUSTADD,CUSTEMAIL,PHONE_NO);
        }
        if(what.equals("order"))
        {
            Label CUSTid=new Label("BOOK NAME: "+temp.get(0));
            CUSTid.setPadding(new Insets(30, 0, 0, 50));
            CUSTid.setStyle("-fx-text-fill:#f2f2f2;-fx-font-weight:bold;-fx-font-size:16px");
            
           
            Label CUSTname=new Label("AMOUNT: "+temp.get(1));
            CUSTname.setPadding(new Insets(10, 0, 0, 50));
            CUSTname.setStyle("-fx-text-fill:#f2f2f2;-fx-font-weight:bold;-fx-font-size:16px");
            
            Label CUSTADD=new Label("CUSTOMER ID: "+temp.get(2));
            CUSTADD.setPadding(new Insets(10, 0, 0, 50));
            CUSTADD.setStyle("-fx-text-fill:#f2f2f2;-fx-font-weight:bold;-fx-font-size:16px");
            
            Label CUSTEMAIL=new Label("DATE: "+temp.get(3));
            CUSTEMAIL.setPadding(new Insets(10, 0, 0, 50));
            CUSTEMAIL.setStyle("-fx-text-fill:#f2f2f2;-fx-font-weight:bold;-fx-font-size:16px");
            
            vbox.getChildren().addAll(CUSTid,CUSTname,CUSTADD,CUSTEMAIL);
        }
        if(what.equals("employee"))
        {
            Label CUSTid=new Label("EMPLOYEE ID: "+temp.get(0));
            CUSTid.setPadding(new Insets(30, 0, 0, 50));
            CUSTid.setStyle("-fx-text-fill:#f2f2f2;-fx-font-weight:bold;-fx-font-size:16px");
            
           
            Label CUSTname=new Label("NAME: "+temp.get(1));
            CUSTname.setPadding(new Insets(10, 0, 0, 50));
            CUSTname.setStyle("-fx-text-fill:#f2f2f2;-fx-font-weight:bold;-fx-font-size:16px");
            
            Label desi=new Label("NAME: "+temp.get(2));
            desi.setPadding(new Insets(10, 0, 0, 50));
            desi.setStyle("-fx-text-fill:#f2f2f2;-fx-font-weight:bold;-fx-font-size:16px");
            
            Label CUSTADD=new Label("ADDRESS: "+temp.get(3));
            CUSTADD.setPadding(new Insets(10, 0, 0, 50));
            CUSTADD.setStyle("-fx-text-fill:#f2f2f2;-fx-font-weight:bold;-fx-font-size:16px");
            
            Label CUSTEMAIL=new Label("EMAIL ID: "+temp.get(4));
            CUSTEMAIL.setPadding(new Insets(10, 0, 0, 50));
            CUSTEMAIL.setStyle("-fx-text-fill:#f2f2f2;-fx-font-weight:bold;-fx-font-size:16px");
            
            Label PHONE_NO=new Label("PHONE NO: "+temp.get(5));
            PHONE_NO.setPadding(new Insets(10, 0, 0, 50));
            PHONE_NO.setStyle("-fx-text-fill:#f2f2f2;-fx-font-weight:bold;-fx-font-size:16px");
            
            Label dis=new Label("SALARY: "+temp.get(6));
            dis.setPadding(new Insets(10, 0, 0, 50));
            dis.setStyle("-fx-text-fill:#f2f2f2;-fx-font-weight:bold;-fx-font-size:16px");
          
            Label COMM = new Label("COMMISSION PCT: " + temp.get(7));
            COMM.setPadding(new Insets(10, 0, 0, 50));
            COMM.setStyle("-fx-text-fill:#f2f2f2;-fx-font-weight:bold;-fx-font-size:16px");
           
             Label SD=new Label("START DATE: "+temp.get(8));
            SD.setPadding(new Insets(10, 0, 0, 50));
            SD.setStyle("-fx-text-fill:#f2f2f2;-fx-font-weight:bold;-fx-font-size:16px");
            
             Label ED=new Label("END DATE: "+temp.get(9));
            ED.setPadding(new Insets(10, 0, 0, 50));
            ED.setStyle("-fx-text-fill:#f2f2f2;-fx-font-weight:bold;-fx-font-size:16px");
           
            vbox.getChildren().addAll(CUSTid,CUSTname,desi,CUSTADD,CUSTEMAIL,PHONE_NO,dis,COMM,SD,ED);
        }
         if(what.equals("bookrequired"))
        {
            Label CUSTid=new Label("BOOK ID: "+temp.get(0));
            CUSTid.setPadding(new Insets(30, 0, 0, 50));
            CUSTid.setStyle("-fx-text-fill:#f2f2f2;-fx-font-weight:bold;-fx-font-size:16px");
            
           
            Label CUSTname=new Label("AMOUNT: "+temp.get(1));
            CUSTname.setPadding(new Insets(10, 0, 0, 50));
            CUSTname.setStyle("-fx-text-fill:#f2f2f2;-fx-font-weight:bold;-fx-font-size:16px");
            
            
             Label edi=new Label("EDITION: "+temp.get(2));
            edi.setPadding(new Insets(10, 0, 0, 50));
            edi.setStyle("-fx-text-fill:#f2f2f2;-fx-font-weight:bold;-fx-font-size:16px");
            
            vbox.getChildren().addAll(CUSTid,CUSTname,edi);
        }
        
        if(what.equals("manager"))
        {
            Label bookid=new Label("MANAGER ID: "+temp.get(0));
            bookid.setPadding(new Insets(30, 0, 0, 50));
            bookid.setStyle("-fx-text-fill:#f2f2f2;-fx-font-weight:bold;-fx-font-size:16px");
            
           
            Label bookname=new Label("NAME: "+temp.get(1));
            bookname.setPadding(new Insets(10, 0, 0, 50));
            bookname.setStyle("-fx-text-fill:#f2f2f2;-fx-font-weight:bold;-fx-font-size:16px");
            
            Label amount=new Label("ADDRESS: "+temp.get(2));
            amount.setPadding(new Insets(10, 0, 0, 50));
            amount.setStyle("-fx-text-fill:#f2f2f2;-fx-font-weight:bold;-fx-font-size:16px");
            
            Label edi=new Label("EMAIL ID: "+temp.get(3));
            edi.setPadding(new Insets(10, 0, 0, 50));
            edi.setStyle("-fx-text-fill:#f2f2f2;-fx-font-weight:bold;-fx-font-size:16px");
            
            Label sell=new Label("PHONE NO: "+temp.get(4));
            sell.setPadding(new Insets(10, 0, 0, 50));
            sell.setStyle("-fx-text-fill:#f2f2f2;-fx-font-weight:bold;-fx-font-size:16px");
            
            Label dis=new Label("DESIGNATION: "+temp.get(5));
            dis.setPadding(new Insets(10, 0, 0, 50));
            dis.setStyle("-fx-text-fill:#f2f2f2;-fx-font-weight:bold;-fx-font-size:16px");
            
             Label SAL=new Label("SALARY: "+temp.get(6));
            SAL.setPadding(new Insets(10, 0, 0, 50));
            SAL.setStyle("-fx-text-fill:#f2f2f2;-fx-font-weight:bold;-fx-font-size:16px");
            
             Label COMM=new Label("COMMISSION PCT: "+temp.get(7));
            COMM.setPadding(new Insets(10, 0, 0, 50));
            COMM.setStyle("-fx-text-fill:#f2f2f2;-fx-font-weight:bold;-fx-font-size:16px");
            
             Label SD=new Label("START DATE: "+temp.get(8));
            SD.setPadding(new Insets(10, 0, 0, 50));
            SD.setStyle("-fx-text-fill:#f2f2f2;-fx-font-weight:bold;-fx-font-size:16px");
            
             Label ED=new Label("END DATE: "+temp.get(9));
            ED.setPadding(new Insets(10, 0, 0, 50));
            ED.setStyle("-fx-text-fill:#f2f2f2;-fx-font-weight:bold;-fx-font-size:16px");
           
             
            vbox.getChildren().addAll(bookid,bookname,amount,edi,sell,dis,SAL,COMM,SD,ED);
        }
        if(what.equals("publisher"))
        {
            Label bookid=new Label("PUBLISHER ID: "+temp.get(0));
            bookid.setPadding(new Insets(30, 0, 0, 50));
            bookid.setStyle("-fx-text-fill:#f2f2f2;-fx-font-weight:bold;-fx-font-size:16px");
            
           
            Label bookname=new Label("COMPANY NAME: "+temp.get(1));
            bookname.setPadding(new Insets(10, 0, 0, 50));
            bookname.setStyle("-fx-text-fill:#f2f2f2;-fx-font-weight:bold;-fx-font-size:16px");
            
            Label amount=new Label("ADDRESS: "+temp.get(2));
            amount.setPadding(new Insets(10, 0, 0, 50));
            amount.setStyle("-fx-text-fill:#f2f2f2;-fx-font-weight:bold;-fx-font-size:16px");
            
            Label edi=new Label("PHONE NO: "+temp.get(3));
            edi.setPadding(new Insets(10, 0, 0, 50));
            edi.setStyle("-fx-text-fill:#f2f2f2;-fx-font-weight:bold;-fx-font-size:16px");
            
            Label sell=new Label("EMAIL ID: "+temp.get(4));
            sell.setPadding(new Insets(10, 0, 0, 50));
            sell.setStyle("-fx-text-fill:#f2f2f2;-fx-font-weight:bold;-fx-font-size:16px");
            
            
            vbox.getChildren().addAll(bookid,bookname,amount,edi,sell);
        }
        if(what.equals("supplier"))
        {
            Label bookid=new Label("SUPPLER ID: "+temp.get(0));
            bookid.setPadding(new Insets(30, 0, 0, 50));
            bookid.setStyle("-fx-text-fill:#f2f2f2;-fx-font-weight:bold;-fx-font-size:16px");
            
           
            Label bookname=new Label("COMPANY NAME: "+temp.get(1));
            bookname.setPadding(new Insets(10, 0, 0, 50));
            bookname.setStyle("-fx-text-fill:#f2f2f2;-fx-font-weight:bold;-fx-font-size:16px");
            
            Label amount=new Label("ADDRESS: "+temp.get(2));
            amount.setPadding(new Insets(10, 0, 0, 50));
            amount.setStyle("-fx-text-fill:#f2f2f2;-fx-font-weight:bold;-fx-font-size:16px");
            
            Label edi=new Label("PHONE NO: "+temp.get(3));
            edi.setPadding(new Insets(10, 0, 0, 50));
            edi.setStyle("-fx-text-fill:#f2f2f2;-fx-font-weight:bold;-fx-font-size:16px");
            
            Label sell=new Label("EMAIL ID: "+temp.get(4));
            sell.setPadding(new Insets(10, 0, 0, 50));
            sell.setStyle("-fx-text-fill:#f2f2f2;-fx-font-weight:bold;-fx-font-size:16px");
            
            
            vbox.getChildren().addAll(bookid,bookname,amount,edi,sell);
        }
        if(what.equals("purchase"))
        {
            Label bookid=new Label("PURCHASE ID: "+temp.get(0));
            bookid.setPadding(new Insets(30, 0, 0, 50));
            bookid.setStyle("-fx-text-fill:#f2f2f2;-fx-font-weight:bold;-fx-font-size:16px");
            
           
            Label bookname=new Label("SUPPLIER ID: "+temp.get(1));
            bookname.setPadding(new Insets(10, 0, 0, 50));
            bookname.setStyle("-fx-text-fill:#f2f2f2;-fx-font-weight:bold;-fx-font-size:16px");
            
            Label amount=new Label("BOOK ID: "+temp.get(2));
            amount.setPadding(new Insets(10, 0, 0, 50));
            amount.setStyle("-fx-text-fill:#f2f2f2;-fx-font-weight:bold;-fx-font-size:16px");
            
            Label edi=new Label("DATE: "+temp.get(3));
            edi.setPadding(new Insets(10, 0, 0, 50));
            edi.setStyle("-fx-text-fill:#f2f2f2;-fx-font-weight:bold;-fx-font-size:16px");
            
            Label sell=new Label("AMOUNT: "+temp.get(4));
            sell.setPadding(new Insets(10, 0, 0, 50));
            sell.setStyle("-fx-text-fill:#f2f2f2;-fx-font-weight:bold;-fx-font-size:16px");
            
            Label edition=new Label("EDITION: "+temp.get(5));
            edition.setPadding(new Insets(10, 0, 0, 50));
            edition.setStyle("-fx-text-fill:#f2f2f2;-fx-font-weight:bold;-fx-font-size:16px");
            
            Label price=new Label("BUYING PRICE: "+temp.get(6));
            price.setPadding(new Insets(10, 0, 0, 50));
            price.setStyle("-fx-text-fill:#f2f2f2;-fx-font-weight:bold;-fx-font-size:16px");
            
            
            vbox.getChildren().addAll(bookid,bookname,amount,edi,sell,edition,price);
        }
        if(what.equals("managing"))
        {
            Label bookid=new Label("MANAGING ID: "+temp.get(0));
            bookid.setPadding(new Insets(10, 0, 0, 50));
            bookid.setStyle("-fx-text-fill:#f2f2f2;-fx-font-weight:bold;-fx-font-size:16px");
            
           
            Label bookname=new Label("MANAGER ID: "+temp.get(1));
            bookname.setPadding(new Insets(10, 0, 0, 50));
            bookname.setStyle("-fx-text-fill:#f2f2f2;-fx-font-weight:bold;-fx-font-size:16px");
            
            Label amount=new Label("STAFF ID: "+temp.get(2));
            amount.setPadding(new Insets(10, 0, 0, 50));
            amount.setStyle("-fx-text-fill:#f2f2f2;-fx-font-weight:bold;-fx-font-size:16px");
            
            Label edi=new Label("START DATE: "+temp.get(3));
            edi.setPadding(new Insets(10, 0, 0, 50));
            edi.setStyle("-fx-text-fill:#f2f2f2;-fx-font-weight:bold;-fx-font-size:16px");
            
            Label sell=new Label("END DATE: "+temp.get(4));
            sell.setPadding(new Insets(10, 0, 0, 50));
            sell.setStyle("-fx-text-fill:#f2f2f2;-fx-font-weight:bold;-fx-font-size:16px");
            
            
            vbox.getChildren().addAll(bookid,bookname,amount,edi,sell);
        }
        
    }
    @FXML
    private void handleclose(ActionEvent event) {
         Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }
    
}
