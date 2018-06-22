package bookstore;

import bookstore.classes.*;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class BookBuyApplicationController implements Initializable {

    ObservableList<bookrequired> booksrequ = FXCollections.observableArrayList();
    @FXML
    private VBox vboxmain;
    @FXML
    private Label employeeId;
    @FXML
    private Label suppid;
    @FXML
    private Label companyname;
    @FXML
    private Label address;
    @FXML
    private Label emailid;
    @FXML
    private Label phoneno;
    @FXML
    private Label date;
    String userid = null;
    String who = null;

    void setVariable(String user, String who, String supp, ObservableList<bookrequired> booksrequ) throws ClassNotFoundException, SQLException {
        userid = user;
        this.who = who;
        this.booksrequ = booksrequ;
        Class.forName("oracle.jdbc.OracleDriver");
        Connection conn = new DBConnection().getConnection();

        System.out.println(supp);
        String sql = "SELECT SUPPLIER_ID,COMPANY_NAME,ADDRESS,EMAIL_ID,PHONE_NO FROM SUPPLIER WHERE SUPPLIER_ID=?";
        PreparedStatement pst = null;
        ResultSet rs = null;
        pst = conn.prepareStatement(sql);
        pst.setString(1, supp);
        pst.executeUpdate();
        rs = pst.executeQuery();
        while (rs.next()) {
            System.out.println("exist");
            suppid.setText(rs.getString("SUPPLIER_ID"));
            companyname.setText(rs.getString("COMPANY_NAME"));
            address.setText(rs.getString("ADDRESS"));
            emailid.setText(rs.getString("EMAIL_ID"));
            phoneno.setText(rs.getString("PHONE_NO"));
        }
        employeeId.setText(who + " ID: " + userid);
        Date today;
        CallableStatement call = conn.prepareCall("{? = call send_date()}");
        call.registerOutParameter(1, java.sql.Types.DATE);
        call.executeUpdate();
        System.out.println(call.getDate(1));
        today = call.getDate(1);
        Format formatter = new SimpleDateFormat("DD-MM-YYYY");
        String s = formatter.format(today);
        date.setText(s);
       
        for (int i = 0; i < this.booksrequ.size(); i++) {
            HBox book = new HBox();
            book.setPadding(new Insets(0, 0, 0, 30));
            book.setSpacing(40);
            Label no = new Label(Integer.toString(i + 1) + ".");
            no.setStyle("-fx-font-size:14px;-fx-font-weight:bold;");

            Label edi = new Label();
            edi.setStyle("-fx-font-size:14px");
            edi.setText(booksrequ.get(i).getEdition());

            Label amount = new Label();
            amount.setStyle("-fx-font-size:14px");
            amount.setText(Integer.toString(booksrequ.get(i).getAmount()));

            sql = "SELECT NAME FROM BOOK WHERE BOOK_ID=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, booksrequ.get(i).getBookid());
            pst.executeUpdate();
            rs = pst.executeQuery();
            rs.next();

            Label name = new Label();
            name.setStyle("-fx-font-size:14px");
            name.setText(rs.getString("NAME"));

            VBox author = new VBox();
            //author.setSpacing(10);

            sql = "SELECT A.NAME FROM AUTHOR_BOOK_RELATION R,AUTHOR A WHERE R.AUTHOR_ID=A.AUTHOR_ID AND R.BOOK_ID=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, booksrequ.get(i).getBookid());
            pst.executeUpdate();
            rs = pst.executeQuery();
            while (rs.next()) {
                Label au = new Label();
                au.setStyle("-fx-font-size:14px");
                au.setText(rs.getString("NAME"));
                author.getChildren().add(au);

            }

            book.getChildren().addAll(no, name, author, edi, amount);

            vboxmain.getChildren().addAll(book);

        }
        Label thanks = new Label();
        thanks.setStyle("-fx-font-size:14px");
        thanks.setText("Thanking you,");
        thanks.setPadding(new Insets(10, 0, 0, 30));

        Label faith = new Label();
        faith.setStyle("-fx-font-size:14px");
        faith.setText("Yours faithfully,");
        faith.setPadding(new Insets(10, 0, 0, 30));

        Label man = new Label();
        man.setStyle("-fx-font-size:14px");
        man.setText(who + " ID:" + userid);
        man.setPadding(new Insets(10, 0, 0, 30));

        vboxmain.getChildren().addAll(thanks, faith, man);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void sendRequ(ActionEvent event) throws ClassNotFoundException, SQLException {

        Class.forName("oracle.jdbc.OracleDriver");
        Connection conn = new DBConnection().getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
 
        //System.out.println(supp);
        //String sql = "SELECT SUPPLIER_ID,COMPANY_NAME,ADDRESS,EMAIL_ID,PHONE_NO FROM SUPPLIER WHERE SUPPLIER_ID=?";
        String sql = null;
        for (int i = 0; i < booksrequ.size(); i++) {
            sql = "UPDATE BOOK SET AMOUNT=((SELECT AMOUNT FROM BOOK WHERE BOOK_ID=?)+?) WHERE BOOK_ID=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1,booksrequ.get(i).getBookid());
            pst.setInt(2,booksrequ.get(i).getAmount());
            pst.setString(3,booksrequ.get(i).getBookid());
            pst.executeUpdate();
            //rs = pst.executeQuery();
            
            sql="UPDATE BOOK_WITH_EDITION SET AMOUNT=((SELECT AMOUNT FROM BOOK_WITH_EDITION WHERE BOOK_ID=? AND EDITION=?)+?) WHERE BOOK_ID=? AND EDITION=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1,booksrequ.get(i).getBookid());
            pst.setString(2,booksrequ.get(i).getEdition());
            pst.setInt(3,booksrequ.get(i).getAmount());
            pst.setString(4,booksrequ.get(i).getBookid());
            pst.setString(5,booksrequ.get(i).getEdition());
            pst.executeUpdate();
            Alert al = new Alert(Alert.AlertType.CONFIRMATION);
            al.setHeaderText(null);
            al.setContentText("CHANGED SUCCESSFULLY!!!");
            al.showAndWait();
           
        }

    }

}
