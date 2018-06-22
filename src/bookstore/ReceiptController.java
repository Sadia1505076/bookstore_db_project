
package bookstore;
import bookstore.classes.*;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ReceiptController implements Initializable {
    ArrayList<JFXTextField> customer=new ArrayList<>();
    ArrayList<receipt> receipt=new ArrayList<>();
    ArrayList<Double> price_with_vat_dis=new ArrayList<>();
    int invoiceno;
    DatePicker odate;
    @FXML
    private Label date;
    @FXML
    private Label invoice;
    @FXML
    private Label custName;
    @FXML
    private Label custAdd;
    @FXML
    private Label custEmail;
    @FXML
    private Label custPhone;
    @FXML
    private VBox vmain;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    void savingintable() throws ClassNotFoundException, SQLException
    {
        Class.forName("oracle.jdbc.OracleDriver");
        Connection conn = new DBConnection().getConnection();
        String sql = "SELECT COUNT(*)AS ROWCOUNT FROM CUSTOMER";
        PreparedStatement pst = null;
        ResultSet rs = null;
        Statement s = conn.createStatement();
        rs = s.executeQuery(sql);
        rs.next();
        int rowcust=rs.getInt("ROWCOUNT") + 1;
        sql="INSERT INTO CUSTOMER VALUES(?,?,?,?,?)";
        pst=conn.prepareStatement(sql);
        pst.setString(1,Integer.toString(rowcust));
        pst.setString(2,custName.getText());
        pst.setString(3,custEmail.getText());
        pst.setString(4,custPhone.getText());
        pst.setString(5,custAdd.getText());
        pst.executeUpdate();
       
        
        sql="INSERT INTO INVOICE VALUES(?,?)";
        pst=conn.prepareStatement(sql);
        pst.setInt(1,invoiceno);
        pst.setString(2,Integer.toString(rowcust));
        pst.executeUpdate();
       
        sql = "SELECT COUNT(*)AS ROWCOUNT FROM ORDER_OF_BOOK";
        rs = s.executeQuery(sql);
        rs.next();
        int roworder = rs.getInt("ROWCOUNT") + 1;

        for(int i=0;i<receipt.size();i++)
        {
            sql="UPDATE BOOK SET AMOUNT=((SELECT AMOUNT FROM BOOK WHERE BOOK_ID=?)-?) WHERE BOOK_ID=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1,receipt.get(i).getBookid());
            pst.setInt(2,Integer.parseInt(receipt.get(i).getAmount()));
            pst.setString(3, receipt.get(i).getBookid());
            pst.executeUpdate();
            
            sql="UPDATE BOOK_WITH_EDITION SET AMOUNT=((SELECT AMOUNT FROM BOOK_WITH_EDITION WHERE BOOK_ID=? AND EDITION=?)-?) WHERE BOOK_ID=? AND EDITION=?";     
            pst = conn.prepareStatement(sql);
            pst.setString(1,receipt.get(i).getBookid());
            pst.setString(2,receipt.get(i).getEdition());
            pst.setInt(3, Integer.parseInt(receipt.get(i).getAmount()));
            pst.setString(4,receipt.get(i).getBookid());
            pst.setString(5, receipt.get(i).getEdition());
          
            pst.executeUpdate();
            
            
            sql="INSERT INTO ORDER_OF_BOOK VALUES(?,?,?,?,?,?,?,?,?,?,?)";
            
            pst = conn.prepareStatement(sql);
            pst.setString(1, Integer.toString(roworder+i));
            pst.setString(2, Integer.toString(rowcust));
            pst.setString(3, receipt.get(i).getBookid());
            pst.setString(4,odate.getValue().toString());
            pst.setInt(5, Integer.parseInt(receipt.get(i).getAmount()));
            pst.setInt(6, Integer.parseInt(receipt.get(i).getVat()));
            //pst.setInt(7,Integer.parseInt(receipt.get(i).getPrice())*Integer.parseInt(receipt.get(i).getAmount()) );
            pst.setString(7, receipt.get(i).getDiscount());
            pst.setString(8,receipt.get(i).getEdition());
            pst.setInt(9,invoiceno);
            pst.setInt(10,Integer.parseInt(receipt.get(i).getPrice()));
            pst.setDouble(11,price_with_vat_dis.get(i));
           
            pst.executeUpdate();
            
        
        }
        

    }
    void settingvari(ArrayList<JFXTextField> cust, ArrayList<receipt> rece,DatePicker odate,String paidA) throws SQLException
    {
        customer=cust;
        receipt=rece;
        this.odate=odate;
        System.out.println("done");
        custName.setText(customer.get(0).getText());
        custAdd.setText(customer.get(1).getText());
        custEmail.setText(customer.get(2).getText());
        custPhone.setText(customer.get(3).getText());
        date.setText(odate.getValue().toString());
        
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            Connection conn = new DBConnection().getConnection();
            String sql = "SELECT COUNT(*)AS ROWCOUNT FROM INVOICE";
            PreparedStatement pst = null;
            ResultSet rs=null;
            Statement s=conn.createStatement();
            rs = s.executeQuery(sql);
            rs.next();
            invoiceno=rs.getInt("ROWCOUNT")+1;
            invoice.setText(Integer.toString(rs.getInt("ROWCOUNT")+1));
           
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReceiptController.class.getName()).log(Level.SEVERE, null, ex);
        }
        double totalp=0,totaldist=0,totalvat=0;
        for(int i=0;i<receipt.size();i++)
        {
            HBox hbox=new HBox();
            hbox.setSpacing(10);
            Label bookname=new Label();
            Label qty=new Label();
            Label unitprice=new Label();
            Label discount=new Label();
            Label totalPrice=new Label();
            Label vat=new Label();
            
            bookname.setPrefSize(122,34);
            //bookname.prefHeight(34);
            qty.setPrefSize(52,34);
            unitprice.setPrefSize(87,34);
            discount.setPrefSize(76,34);
            totalPrice.setPrefSize(98,34);
            vat.setPrefSize(61,34);
            
            bookname.setPadding(new Insets(0, 0, 0, 20));
            
            bookname.setText(receipt.get(i).getBookname());
            qty.setText(receipt.get(i).getAmount());
            unitprice.setText(receipt.get(i).getPrice());
            discount.setText(receipt.get(i).getDiscount());
            vat.setText(receipt.get(i).getVat());
            double dis=(Double.parseDouble(receipt.get(i).getDiscount())/100)*Double.parseDouble(receipt.get(i).getAmount()) * Double.parseDouble(receipt.get(i).getPrice());
            System.out.println(dis);
            double vat1=(Double.parseDouble(receipt.get(i).getVat())/100)*Double.parseDouble(receipt.get(i).getAmount()) * Double.parseDouble(receipt.get(i).getPrice());
            double total = Double.parseDouble(receipt.get(i).getAmount()) * Double.parseDouble(receipt.get(i).getPrice())-dis+vat1;
            Collections.addAll(price_with_vat_dis,total);
            totalp=totalp+total;
            totaldist=totaldist+dis;
            totalvat=totalvat+vat1;
            totalPrice.setText(Double.toString(total));
            hbox.getChildren().addAll(bookname,qty,unitprice,discount,vat,totalPrice);
            vmain.getChildren().add(hbox);
        }
//        Label space =new Label();
//        space.setPadding(new Insets(10,10, 0,10));
//        space.setPrefSize(600,1);
//        space.maxHeight(3);
       // space.setStyle("-fx-background-color:#000000");
         HBox totaldis=new HBox();
       // paytype.setSpacing(40);
        Label tdis=new Label("TOTAL DISCOUNT:");
        tdis.setPrefSize(200,10);
        tdis.setPadding(new Insets(20, 0, 0, 20));
        Label tdis2=new Label(Double.toString(totaldist));
        tdis2.setPadding(new Insets(20, 0,0,0));
        //payT.setPadding(new Insets(0, 0, 0, 20));
        totaldis.getChildren().addAll(tdis,tdis2);
        
        
         HBox totalv=new HBox();
       // paytype.setSpacing(40);
        Label tv=new Label("TOTAL VAT:");
        tv.setPrefSize(200,10);
        tv.setPadding(new Insets(0, 0, 0, 20));
        Label tv2=new Label(Double.toString(totalvat));
        //payT.setPadding(new Insets(0, 0, 0, 20));
        totalv.getChildren().addAll(tv,tv2);
       
       
       
       
        HBox hboxtotal=new HBox();
       // hboxtotal.setSpacing(20);
        Label totalplabel=new Label("NET AMOUNT(TK):");
        totalplabel.setStyle("-fx-font-weight:bold");
        totalplabel.setPrefSize(200,10);
        totalplabel.setPadding(new Insets(20, 0, 0, 20));
        Label totalplabel2=new Label(Double.toString(totalp));
        totalplabel2.setStyle("-fx-font-weight:bold");
        totalplabel2.setPadding(new Insets(20, 0, 0, 0));
        hboxtotal.getChildren().addAll(totalplabel,totalplabel2);
        
        HBox paytype=new HBox();
       // paytype.setSpacing(40);
        Label payT=new Label("PAY TYPE:");
        payT.setPrefSize(200,10);
        payT.setPadding(new Insets(0, 0, 0, 20));
        Label payT2=new Label("CASH");
        //payT.setPadding(new Insets(0, 0, 0, 20));
        paytype.getChildren().addAll(payT,payT2);
        //Alert
         HBox paidamount=new HBox();
       // paidamount.setSpacing(20);
        Label payA=new Label("PAID AMOUNT:");
        payA.setPrefSize(200,10);
        payA.setPadding(new Insets(0, 0, 0, 20));
        Label payA2=new Label(paidA);
        paidamount.getChildren().addAll(payA,payA2);
        
         HBox changedamount=new HBox();
       // changedamount.setSpacing(20);
        Label changeA=new Label("CHANGED AMOUNT:");
        changeA.setPrefSize(200,10);
        changeA.setPadding(new Insets(0, 0, 0, 20));
        Label changeA2=new Label(Double.toString(Double.parseDouble(paidA)-totalp));
        changedamount.getChildren().addAll(changeA,changeA2);
        
        Label sold=new Label("'sold product not returnable.'");
        sold.setPadding(new Insets(20, 0, 0, 200));
        
        Label change=new Label("'Only exchangable within 7 days'");
        change.setPadding(new Insets(0, 0, 0, 200));
        
        Label fore=new Label("'For exchange you must bring invoice and");
        fore.setPadding(new Insets(0, 0, 0, 200));
        
        Label barcode=new Label("barcode level attached with the product.'");
        barcode.setPadding(new Insets(0, 0, 0, 200));
        
        
        Label dis=new Label("'Discount sold product not returnable'");
        dis.setPadding(new Insets(0, 0, 0, 200));
        
        Label hotline=new Label("HOTLINE: 01235656722");
        hotline.setPadding(new Insets(2, 0, 0, 20));
        
        Label shopphoneno = new Label("SHOP PHONE NUMBER: 03127676123");
        shopphoneno.setStyle("-fx-font-weight:bold");
        shopphoneno.setPadding(new Insets(0, 0, 0, 20));

        Label thanku = new Label("Thank you for shopping at BOOK STORE");
        thanku.setPadding(new Insets(0, 0, 0, 20));

        vmain.getChildren().addAll(totaldis,totalv,hboxtotal,paytype,paidamount,changedamount,sold,change,fore,barcode,dis,hotline,shopphoneno,thanku);
        
        try {
            savingintable();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReceiptController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
