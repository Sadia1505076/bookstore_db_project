package bookstore;

import com.jfoenix.controls.JFXButton;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import bookstore.classes.*;

public class bookstoreController implements Initializable {

    @FXML
    private JFXButton addAuthor;
    @FXML
    private VBox vboxofAuthor;
    @FXML
    private VBox mainVbox;
    @FXML
    private JFXButton addSupplier;
    @FXML
    private VBox vboxsupp;
    @FXML
    private JFXButton submit;
    @FXML
    private DatePicker datepicker;
    @FXML
    private DatePicker datepicker2;
    @FXML
    private JFXTextField bookname;
    @FXML
    private JFXTextField totalamount;
    @FXML
    private JFXTextField authorname;
    @FXML
    private JFXTextField nationality;
    @FXML
    private DatePicker datepicker3;
    @FXML
    private JFXTextField writingyear;
    @FXML
    private JFXTextField floor;
    @FXML
    private JFXTextField row;
    @FXML
    private JFXTextField section;

    @FXML
    private JFXTextField pcompname;
    @FXML
    private JFXTextField paddress;
    @FXML
    private JFXTextField pemail;
    @FXML
    private JFXTextField pphoneno;
    @FXML
    private JFXTextField bookpublished;
    @FXML
    private JFXTextField scomname;
    @FXML
    private JFXTextField saddress;
    @FXML
    private JFXTextField semail;
    @FXML
    private JFXTextField sphoneno;
    @FXML
    private JFXTextField edition;
    @FXML
    private JFXTextField amountofb;
    @FXML
    private JFXTextField bpperb;
    @FXML
    private JFXTextField country;

    ArrayList<author> Authorlist = new ArrayList<>();
    ArrayList<supplier> Supplierlist = new ArrayList<>();
    ArrayList<JFXTextField> all = new ArrayList<>();
    ArrayList<JFXTextField> number = new ArrayList<>();
    ArrayList<Integer> authorid=new ArrayList<>();
    ArrayList<Integer> suppliid=new ArrayList<>();
    int flagWr = 0, flagpAdd = 0, flagEm = 0, flagC = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    boolean checkRightInput() {
        System.out.println("in");
        boolean is = false;
        Collections.addAll(number, totalamount, pphoneno, sphoneno, bookpublished, bpperb, amountofb);
        for (int i = 0; i < number.size(); i++) {
            try {
                int a = Integer.parseInt(number.get(i).getText());
            } catch (NumberFormatException e) {
                is = true;
                number.get(i).setText("* ENTER A NUMBER");
                number.get(i).setStyle("-fx-text-fill:#E74C3C;-fx-faint-focus-color: transparent; -fx-focus-color:rgba(255,0,0,0.2);");

            }
        }
        for (int i = 0; i < Supplierlist.size(); i++) {
            try {
                int a = Integer.parseInt(Supplierlist.get(i).getPhoneno().getText());
            } catch (NumberFormatException e) {
                is = true;
                Supplierlist.get(i).getPhoneno().setText("* ENTER A NUMBER");
                Supplierlist.get(i).getPhoneno().setStyle("-fx-text-fill:#E74C3C;-fx-faint-focus-color: transparent; -fx-focus-color:rgba(255,0,0,0.2);");

            }
            try {
                int a = Integer.parseInt(Supplierlist.get(i).getAmountofbook().getText());
            } catch (NumberFormatException e) {
                is = true;
                Supplierlist.get(i).getAmountofbook().setText("* ENTER A NUMBER");
                Supplierlist.get(i).getAmountofbook().setStyle("-fx-text-fill:#E74C3C;-fx-faint-focus-color: transparent; -fx-focus-color:rgba(255,0,0,0.2);");

            }
            try {
                int a = Integer.parseInt(Supplierlist.get(i).getBuyingPricePerBook().getText());
            } catch (NumberFormatException e) {
                is = true;
                Supplierlist.get(i).getBuyingPricePerBook().setText("* ENTER A NUMBER");
                Supplierlist.get(i).getBuyingPricePerBook().setStyle("-fx-text-fill:#E74C3C;-fx-faint-focus-color: transparent; -fx-focus-color:rgba(255,0,0,0.2);");

            }
        }
        return is;
    }

    boolean checkingNull() {
        boolean is = false;
        Collections.addAll(all, bookname, totalamount, authorname, nationality, writingyear, floor, row, section);
        Collections.addAll(all, pcompname, paddress, pemail, pphoneno, scomname, saddress, semail, sphoneno);
        Collections.addAll(all, bookpublished, bpperb, edition, country, amountofb);
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getText().isEmpty()) {
                if (all.get(i).equals(writingyear)) {
                    flagWr = 1;
                    //is=true;

                } else if (all.get(i).equals(paddress)) {
                    flagpAdd = 1;
                    //is= true;
                }  else if (all.get(i).equals(country)) {
                    flagC = 1;
                    //is= true;
                } else {
                    all.get(i).setText("* REQUIRED");
                    all.get(i).setStyle("-fx-text-fill:#E74C3C;-fx-faint-focus-color: transparent; -fx-focus-color:rgba(255,0,0,0.2); ");
                    is = true;
                }
            }

        }
        for (int i = 0; i < Authorlist.size(); i++) {
            if (Authorlist.get(i).getWritingYear().getText().isEmpty()) {
                Authorlist.get(i).setNullW("null");
            }
            //if(Authorlist.get(i).getBirthdate().getValue().toString().isEmpty()) Authorlist.get(i).setNullB("null");
            if (Authorlist.get(i).getAuthorname().getText().isEmpty()) {
                Authorlist.get(i).getAuthorname().setText("* REQUIRED");
                Authorlist.get(i).getAuthorname().setStyle("-fx-text-fill:#E74C3C;-fx-faint-focus-color: transparent; -fx-focus-color:rgba(255,0,0,0.2);");
                is = true;
            }
            if (Authorlist.get(i).getNationality().getText().isEmpty()) {
                Authorlist.get(i).getNationality().setText("* REQUIRED");
                Authorlist.get(i).getNationality().setStyle("-fx-text-fill:#E74C3C;-fx-faint-focus-color: transparent; -fx-focus-color:rgba(255,0,0,0.2);");
                is = true;
            }
        }
        for (int i = 0; i < Supplierlist.size(); i++) {
            if (Supplierlist.get(i).getComname().getText().isEmpty()) {
                Supplierlist.get(i).getComname().setText("* REQUIRED");
                Supplierlist.get(i).getComname().setStyle("-fx-text-fill:#E74C3C;-fx-faint-focus-color: transparent; -fx-focus-color:rgba(255,0,0,0.2);");
                is = true;
            }
            if (Supplierlist.get(i).getAddress().getText().isEmpty()) {
                Supplierlist.get(i).getAddress().setText("* REQUIRED");
                Supplierlist.get(i).getAddress().setStyle("-fx-text-fill:#E74C3C;-fx-faint-focus-color: transparent; -fx-focus-color:rgba(255,0,0,0.2);");
                is = true;
            }
            if (Supplierlist.get(i).getEmail().getText().isEmpty()) {
                Supplierlist.get(i).getEmail().setText("* REQUIRED");
                Supplierlist.get(i).getEmail().setStyle("-fx-text-fill:#E74C3C;-fx-faint-focus-color: transparent; -fx-focus-color:rgba(255,0,0,0.2);");
                is = true;
            }
            if (Supplierlist.get(i).getPhoneno().getText().isEmpty()) {
                Supplierlist.get(i).getPhoneno().setText("* REQUIRED");
                Supplierlist.get(i).getPhoneno().setStyle("-fx-text-fill:#E74C3C;-fx-faint-focus-color: transparent; -fx-focus-color:rgba(255,0,0,0.2);-fx-text-size=18px ");
                is = true;
            }
            if (Supplierlist.get(i).getEdition().getText().isEmpty()) {
                Supplierlist.get(i).getEdition().setText("* REQUIRED");
                Supplierlist.get(i).getEdition().setStyle("-fx-text-fill:#E74C3C;-fx-faint-focus-color: transparent; -fx-focus-color:rgba(255,0,0,0.2); ");
                is = true;
            }
            if (Supplierlist.get(i).getAmountofbook().getText().isEmpty()) {
                Supplierlist.get(i).getAmountofbook().setText("* REQUIRED");
                Supplierlist.get(i).getAmountofbook().setStyle("-fx-text-fill:#E74C3C;-fx-faint-focus-color: transparent; -fx-focus-color:rgba(255,0,0,0.2);");
                is = true;
            }
            if (Supplierlist.get(i).getBuyingPricePerBook().getText().isEmpty()) {
                Supplierlist.get(i).getBuyingPricePerBook().setText("* REQUIRED");
                Supplierlist.get(i).getBuyingPricePerBook().setStyle("-fx-text-fill:#E74C3C;-fx-faint-focus-color: transparent; -fx-focus-color:rgba(255,0,0,0.2);");
                is = true;
            }
        }

//        if(datepicker.getValue().toString().isEmpty())// || datepicker2.getValue().toString().isEmpty())
//        {
//            datepicker.setStyle("-fx-background-color: red;");
//        }
        return is;
    }

    @FXML
    private void addAnAouthor(ActionEvent event) {
        JFXTextField authorname = new JFXTextField();
        JFXTextField nationality = new JFXTextField();
        //JFXTextField birthdate = new JFXTextField();
        JFXTextField writingYear = new JFXTextField();
        Label birth = new Label();
        DatePicker birthdate = new DatePicker();
        Collections.addAll(Authorlist, new author(authorname, nationality, birthdate, writingYear));

        authorname.setPrefWidth(160);
        nationality.setPrefWidth(171);
        birthdate.setPrefWidth(178);
        writingYear.setPrefWidth(144);
        birth.setPrefWidth(80);

        authorname.setPromptText("AUTHOR NAME");
        nationality.setPromptText("NATIONALITY");
        //birthdate.setPromptText("BIRTHDATE");
        writingYear.setPromptText("WRITING YEAR");
        birth.setText("BIRTHDATE:");

        authorname.setLabelFloat(true);
        nationality.setLabelFloat(true);
        //birthdate.setLabelFloat(true);
        writingYear.setLabelFloat(true);

        HBox hboxinauthor = new HBox();
        hboxinauthor.setSpacing(20);
        hboxinauthor.setPadding(new Insets(10, 12, 15, 20));
        hboxinauthor.getChildren().addAll(authorname, nationality, birth, birthdate, writingYear);

        vboxofAuthor.getChildren().add(hboxinauthor);
    }

    @FXML
    private void addASupplier(ActionEvent event) {
        HBox hboxsupp1 = new HBox();
        HBox hboxsupp2 = new HBox();
        hboxsupp1.setSpacing(30);
        hboxsupp1.setPadding(new Insets(10, 12, 15, 20));
        hboxsupp2.setSpacing(30);
        hboxsupp2.setPadding(new Insets(10, 12, 15, 20));

        JFXTextField companyName = new JFXTextField();
        JFXTextField address = new JFXTextField();
        JFXTextField emailid = new JFXTextField();
        JFXTextField phoneno = new JFXTextField();
        JFXTextField edition = new JFXTextField();
        JFXTextField amountofbook = new JFXTextField();
        JFXTextField buyingprice = new JFXTextField();

        companyName.setPrefWidth(193);
        address.setPrefWidth(191);
        emailid.setPrefWidth(178);
        phoneno.setPrefWidth(193);
        edition.setPrefWidth(171);
        amountofbook.setPrefWidth(178);
        buyingprice.setPrefWidth(144);

        Collections.addAll(Supplierlist, new supplier(companyName, address, emailid, phoneno, edition, amountofbook, buyingprice));

        companyName.setPromptText("COMPANY NAME");
        address.setPromptText("ADDRESS");
        emailid.setPromptText("EMAIL ID");
        phoneno.setPromptText("PHONE NO");
        edition.setPromptText("EDITION");
        amountofbook.setPromptText("AMOUNT OF BOOK");
        buyingprice.setPromptText("BUYING PRICE PER BOOK");

        companyName.setLabelFloat(true);
        address.setLabelFloat(true);
        emailid.setLabelFloat(true);
        phoneno.setLabelFloat(true);
        edition.setLabelFloat(true);
        amountofbook.setLabelFloat(true);
        buyingprice.setLabelFloat(true);

        hboxsupp1.getChildren().addAll(companyName, address, emailid);
        hboxsupp2.getChildren().addAll(phoneno, edition, amountofbook, buyingprice);

        vboxsupp.getChildren().addAll(hboxsupp1, hboxsupp2);
    }
    
    void Clear()
    {
        bookname.clear();totalamount.clear();
        authorname.clear(); nationality.clear();writingyear.clear();
        pcompname.clear();paddress.clear();pphoneno.clear();pemail.clear();
        scomname.clear();saddress.clear();sphoneno.clear();semail.clear();
        edition.clear();row.clear();section.clear();floor.clear();
        amountofb.clear();bookpublished.clear();bpperb.clear();country.clear();
        for(int i=0;i<Authorlist.size();i++)
        {
            Authorlist.get(i).getAuthorname().clear();
            Authorlist.get(i).getNationality().clear();
            Authorlist.get(i).getWritingYear().clear();
            
        }
        for(int i=0;i<Supplierlist.size();i++)
        {
            Supplierlist.get(i).getComname().clear();
            Supplierlist.get(i).getAddress().clear();
            Supplierlist.get(i).getAmountofbook().clear();
            Supplierlist.get(i).getBuyingPricePerBook().clear();
            Supplierlist.get(i).getEdition().clear();
            Supplierlist.get(i).getEmail().clear();
            Supplierlist.get(i).getPhoneno();
        }
        
    }

    @FXML
    private void pressingSubmit(ActionEvent event) throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.OracleDriver");
        Connection conn = new DBConnection().getConnection();

        if (checkingNull() || checkRightInput()) {
            System.err.println("null");
        } else {
            String sql = "SELECT COUNT(*)AS ROWCOUNT FROM LOCATION_OF_BOOK";
            PreparedStatement pst = null;
            ResultSet rs = null;
            Statement s = conn.createStatement();

            //----------------------------location------------------------------
            rs = s.executeQuery(sql);
            rs.next();
            int rownoofloc = rs.getInt("ROWCOUNT")+1;

            sql = "insert into LOCATION_OF_BOOK values(?,?,?,?)";

            pst = conn.prepareStatement(sql);
            pst.setString(1, Integer.toString(rownoofloc ));
            pst.setString(2, floor.getText());
            pst.setString(3, row.getText());
            pst.setString(4, section.getText());
            pst.executeUpdate();

            //-------------------------------publisher----------------------------
            
            sql="SELECT PUBLISHER_ID FROM PUBLISHER WHERE COMPANY_NAME=? AND PHONE_NO=? AND EMAIL_ID=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, pcompname.getText());
            pst.setString(2, pphoneno.getText());
            pst.setString(3, pemail.getText());
            pst.executeUpdate();
            rs=pst.executeQuery();
            int rownoofpub=0;
            if(rs.next())
            {
                rownoofpub=rs.getInt("PUBLISHER_ID");
            }
            
           else  {
                sql = "SELECT COUNT(*)AS ROWCOUNT FROM PUBLISHER";

                rs = s.executeQuery(sql);
                rs.next();
                rownoofpub= rs.getInt("ROWCOUNT")+1;
                System.out.println(rownoofpub);
                //String temp="TO_DATE('"+datepicker2.getValue().toString()+"','MM/DD/YYYY')";

                sql = "insert into PUBLISHER(PUBLISHER_ID,PUBLISHING_DATE,AMOUNT,COMPANY_NAME,ADDRESS,COUNTRY,PHONE_NO,EMAIL_ID) values(?,?,?,?,?,?,?,?)";

                System.out.println("done");
                pst = conn.prepareStatement(sql);
                pst.setString(1, Integer.toString(rownoofpub));
                pst.setString(2, datepicker2.getValue().toString());
                pst.setInt(3, Integer.parseInt(bookpublished.getText()));
                pst.setString(4, pcompname.getText());
                pst.setString(5, paddress.getText());
                pst.setString(6, country.getText());
                pst.setString(7, pphoneno.getText());
                pst.setString(8, pemail.getText());

                pst.executeUpdate();

            }

            ///----------------------------author-------------------------------------
            
            int rownoofauthor=0;
            sql="SELECT AUTHOR_ID FROM AUTHOR WHERE NAME=? AND BIRTHDATE=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, authorname.getText());
            pst.setString(2, datepicker3.getValue().toString());
            //pst.setString(3, pemail.getText());
            pst.executeUpdate();
            rs=pst.executeQuery();
            
            if(rs.next())
            {
                rownoofauthor=rs.getInt("AUTHOR_ID");
                Collections.addAll(authorid,rownoofauthor);
            }
            else
            {
                sql = "SELECT COUNT(*)AS ROWCOUNT FROM AUTHOR";

                rs = s.executeQuery(sql);
                rs.next();
                rownoofauthor = rs.getInt("ROWCOUNT")+1;
                Collections.addAll(authorid,rownoofauthor);
                System.out.println(rownoofauthor);
                //String temp="TO_DATE('"+datepicker2.getValue().toString()+"','MM/DD/YYYY')";

                sql = "insert into AUTHOR values(?,?,?,?,?)";

                System.out.println("done");
                pst = conn.prepareStatement(sql);
                pst.setString(1, Integer.toString(rownoofauthor));
                pst.setString(2, authorname.getText());
                pst.setString(3, datepicker3.getValue().toString());
                pst.setString(4, writingyear.getText());
                pst.setString(5, nationality.getText());

                pst.executeUpdate();
            }
            
            

            int sizeofArray = Authorlist.size();
            for (int i = 0; i < sizeofArray; i++) {

                sql = "SELECT AUTHOR_ID FROM AUTHOR WHERE NAME=? AND BIRTHDATE=?";
                pst = conn.prepareStatement(sql);
                pst.setString(1, Authorlist.get(i).getAuthorname().getText());
                pst.setString(2, Authorlist.get(i).getBirthdate().getValue().toString());
                //pst.setString(3, pemail.getText());
                pst.executeUpdate();
                rs = pst.executeQuery();

                if (rs.next()) {
                    rownoofauthor = rs.getInt("AUTHOR_ID");
                    Collections.addAll(authorid,rownoofauthor);
                }
                //String temp="TO_DATE('"+datepicker2.getValue().toString()+"','MM/DD/YYYY')";
                else
                {
                  
                    sql = "SELECT COUNT(*)AS ROWCOUNT FROM AUTHOR";

                    rs = s.executeQuery(sql);
                    rs.next();
                    rownoofauthor = rs.getInt("ROWCOUNT")+1;
                    Collections.addAll(authorid,rownoofauthor);
                    sql = "insert into AUTHOR values(?,?,?,?,?)";

                    System.out.println("done");
                    pst = conn.prepareStatement(sql);
                    pst.setString(1, Integer.toString(rownoofauthor));
                    pst.setString(2, Authorlist.get(i).getAuthorname().getText());
                    pst.setString(3, Authorlist.get(i).getBirthdate().getValue().toString());
                    pst.setString(4, Authorlist.get(i).getWritingYear().getText());
                    pst.setString(5, Authorlist.get(i).getNationality().getText());

                    pst.executeUpdate();
                }
                
            }
            //-------------------------supplier-----------------------------------------
            
            int rownoofsupp=0;
            sql="SELECT SUPPLIER_ID FROM SUPPLIER WHERE COMPANY_NAME=? AND PHONE_NO=? AND EMAIL_ID=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, scomname.getText());
            pst.setString(2, sphoneno.getText());
            pst.setString(3, semail.getText());
            pst.executeUpdate();
            rs=pst.executeQuery();
            
            if(rs.next())
            {
                rownoofsupp=rs.getInt("SUPPLIER_ID");
                Collections.addAll(suppliid,rownoofsupp);
            } else {
                sql = "SELECT COUNT(*)AS ROWCOUNT FROM SUPPLIER";

                rs = s.executeQuery(sql);
                rs.next();
                rownoofsupp = rs.getInt("ROWCOUNT")+1;
                Collections.addAll(suppliid,rownoofsupp);
                System.out.println(rownoofsupp);
                //String temp="TO_DATE('"+datepicker2.getValue().toString()+"','MM/DD/YYYY')";

                sql = "insert into SUPPLIER values(?,?,?,?,?)";

                System.out.println("done");
                pst = conn.prepareStatement(sql);
                pst.setString(1, Integer.toString(rownoofsupp));
                pst.setString(2, scomname.getText());
                pst.setString(3, saddress.getText());
                pst.setString(4, semail.getText());
                pst.setString(5, sphoneno.getText());

                pst.executeUpdate();

            }
            
            sizeofArray = Supplierlist.size();
            //int step = rownoofsupp + 2;
   
            for (int i = 0; i < sizeofArray; i++) {
                sql = "SELECT SUPPLIER_ID FROM SUPPLIER WHERE COMPANY_NAME=? AND PHONE_NO=? AND EMAIL_ID=?";
                pst = conn.prepareStatement(sql);
                pst.setString(1, Supplierlist.get(i).getComname().getText());
                pst.setString(2, Supplierlist.get(i).getPhoneno().getText());
                pst.setString(3, Supplierlist.get(i).getEmail().getText());
                pst.executeUpdate();
                rs = pst.executeQuery();

                if (rs.next()) {
                    rownoofsupp = rs.getInt("SUPPLIER_ID");
                    Collections.addAll(suppliid, rownoofsupp);
                }
                else
                {
                    
                    sql = "SELECT COUNT(*)AS ROWCOUNT FROM SUPPLIER";

                    rs = s.executeQuery(sql);
                    rs.next();
                    rownoofsupp = rs.getInt("ROWCOUNT") + 1;
                    Collections.addAll(suppliid, rownoofsupp);
                    System.out.println(rownoofsupp);

                    sql = "insert into SUPPLIER values(?,?,?,?,?)";

                    System.out.println("done");
                    pst = conn.prepareStatement(sql);
                    pst.setString(1, Integer.toString(rownoofsupp));
                    pst.setString(2, Supplierlist.get(i).getComname().getText());
                    pst.setString(3, Supplierlist.get(i).getAddress().getText());
                    pst.setString(4, Supplierlist.get(i).getEmail().getText());
                    pst.setString(5, Supplierlist.get(i).getPhoneno().getText());

                    pst.executeUpdate();
                    //step++;
                }

            }

            //--------------book---------------------------------------
            
            String bookid = bookname.getText();

            bookid = bookid + authorname.getText();
            for (int i = 0; i < Authorlist.size(); i++) {
                bookid = bookid + Authorlist.get(i).getAuthorname().getText();
            }
            sql="SELECT BOOK_ID FROM BOOK WHERE BOOK_ID=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1,bookid);
//            pst.setString(2, sphoneno.getText());
//            pst.setString(3, semail.getText());
            pst.executeUpdate();
            rs=pst.executeQuery();
            if(rs.next())
            {
                sql = "UPDATE BOOK SET AMOUNT=((SELECT AMOUNT FROM BOOK WHERE BOOK_ID=?)+?)WHERE BOOK_ID=?";
                pst = conn.prepareStatement(sql);
                pst.setString(1, bookid);
                pst.setInt(2,Integer.parseInt(totalamount.getText())) ;
                pst.setString(3, bookid);
                pst.executeUpdate();
            }
            else {

                sql = "insert into BOOK values(?,?,?,?,?,?,?,?)";

                pst = conn.prepareStatement(sql);
                pst.setString(1, bookid);
                pst.setString(2, Integer.toString(rownoofloc));
                pst.setString(3, Integer.toString(rownoofpub));
                pst.setString(4, bookname.getText());
                pst.setInt(5, Integer.parseInt(totalamount.getText()));
                pst.setInt(6, 230);
                pst.setInt(7, 10);
                pst.setString(8, "4");
                pst.executeUpdate();
            }



//-----------------------author-book-rel------------------------------------S
            sql = "SELECT COUNT(*)AS ROWCOUNT FROM AUTHOR_BOOK_RELATION";
            rs = s.executeQuery(sql);
            rs.next();
            int authorbook = rs.getInt("ROWCOUNT")+1;

            for (int i = 0; i < Authorlist.size() + 1; i++) {
                sql = "insert into AUTHOR_BOOK_RELATION values(?,?,?)";

                pst = conn.prepareStatement(sql);
                pst.setString(1, Integer.toString(authorbook + i));
                pst.setString(2,Integer.toString(authorid.get(i)));
                pst.setString(3, bookid);
                pst.executeUpdate();
            }
            //-----------------------id_per_book---------------------

            sql="SELECT COUNT(*)AS ROWCOUNT FROM ID_PER_BOOK";
            rs = s.executeQuery(sql);
            rs.next();
            int idperbook=rs.getInt("ROWCOUNT")+1;
            //System.out.println(Integer.parseInt(amountofb.getText()));
            int temp=Integer.parseInt(totalamount.getText());
            System.out.println("me:"+temp);
            
            sql = "insert into ID_PER_BOOK values(?,?,?)";
            for (int i = 0; i < temp; i++) {
                pst = conn.prepareStatement(sql);
                pst.setString(1, Integer.toString(idperbook+i));
                pst.setString(2, bookid);
                pst.setString(3, datepicker.getValue().toString());

                pst.executeUpdate();
            }
            
            
            
            
            ///------------------------purchase_of_book-------------------------
            sql = "SELECT COUNT(*)AS ROWCOUNT FROM PURCHASE_OF_BOOK";

            rs = s.executeQuery(sql);
            rs.next();
            int rownoofpur = rs.getInt("ROWCOUNT")+1;
            System.out.println(rownoofpur);
            //String temp="TO_DATE('"+datepicker2.getValue().toString()+"','MM/DD/YYYY')";

            sql = "insert into PURCHASE_OF_BOOK values(?,?,?,?,?,?,?)";

            System.out.println("purchase no:"+rownoofpur);
            pst = conn.prepareStatement(sql);
            pst.setString(1, Integer.toString(rownoofpur));
            pst.setString(2, Integer.toString(suppliid.get(0)));
            pst.setString(3, bookid);
            pst.setString(4, datepicker.getValue().toString());
            pst.setInt(5, Integer.parseInt(amountofb.getText()));
            pst.setInt(6, Integer.parseInt(bpperb.getText()));
            pst.setString(7, edition.getText());

            pst.executeUpdate();

            sizeofArray = Supplierlist.size();
            System.out.println("size "+sizeofArray);
            int step = rownoofpur +1;
           // int step2 = rownoofsupp + 2;

            for (int i = 0; i < sizeofArray; i++) {
                sql = "insert into PURCHASE_OF_BOOK values(?,?,?,?,?,?,?)";

                System.out.println("in purchase"+step);
                pst = conn.prepareStatement(sql);
                pst.setString(1, Integer.toString(step));
                pst.setString(2, Integer.toString(suppliid.get(i+1)));
                pst.setString(3, bookid);
                pst.setString(4, datepicker.getValue().toString());
                pst.setInt(5, Integer.parseInt(Supplierlist.get(i).getAmountofbook().getText()));
                pst.setInt(6, Integer.parseInt(Supplierlist.get(i).getBuyingPricePerBook().getText()));
                pst.setString(7, Supplierlist.get(i).getEdition().getText());

                pst.executeUpdate();
                step++;
                //step2++;
            }
            
 ///----------------------------------book_with_edition---------------------
            
            sql="SELECT BOOK_ID FROM BOOK_WITH_EDITION WHERE BOOK_ID=? AND EDITION=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1,bookid);
            pst.setString(2,edition.getText());
//            pst.setString(2, sphoneno.getText());
//            pst.setString(3, semail.getText());
            pst.executeUpdate();
            rs=pst.executeQuery();
            if(rs.next())
            {
                sql = "UPDATE BOOK_WITH_EDITION SET AMOUNT=((SELECT AMOUNT FROM BOOK_WITH_EDITION WHERE BOOK_ID=? AND EDITION=?)+?)WHERE BOOK_ID=? AND EDITION=?";
                pst = conn.prepareStatement(sql);
                pst.setString(1, bookid);
                pst.setString(2, edition.getText());
                pst.setInt(3, Integer.parseInt(amountofb.getText()));
                pst.setString(4, bookid);
                pst.setString(5, edition.getText());
                pst.executeUpdate();
           
            }
            else
            {
                sql = "SELECT COUNT(*)AS ROWCOUNT FROM BOOK_WITH_EDITION";

                rs = s.executeQuery(sql);
                rs.next();
                int rownoofBE = rs.getInt("ROWCOUNT") + 1;
                //System.out.println(rownoofpur);
                sql = "insert into BOOK_WITH_EDITION values(?,?,?,?)";

                System.out.println("done");
                pst = conn.prepareStatement(sql);
                pst.setInt(1, rownoofBE);
                pst.setString(2, bookid);
                pst.setInt(3, Integer.parseInt(amountofb.getText()));
                pst.setString(4, edition.getText());
                pst.executeUpdate();

            }

            
            for (int i = 0; i < sizeofArray; i++) {
                sql = "SELECT BOOK_ID FROM BOOK_WITH_EDITION WHERE BOOK_ID=? AND EDITION=?";
                pst = conn.prepareStatement(sql);
                pst.setString(1, bookid);
                pst.setString(2, Supplierlist.get(i).getEdition().getText());
//            pst.setString(2, sphoneno.getText());
//            pst.setString(3, semail.getText());
                pst.executeUpdate();
                rs = pst.executeQuery();
                if(rs.next())
                {
                    sql = "UPDATE BOOK_WITH_EDITION SET AMOUNT=((SELECT AMOUNT FROM BOOK_WITH_EDITION WHERE BOOK_ID=? AND EDITION=?)+?)WHERE BOOK_ID=? AND EDITION=?";
                    pst = conn.prepareStatement(sql);
                    pst.setString(1, bookid);
                    pst.setString(2, Supplierlist.get(i).getEdition().getText());
                    pst.setInt(3, Integer.parseInt(Supplierlist.get(i).getAmountofbook().getText()));
                    pst.setString(4, bookid);
                    pst.setString(5, Supplierlist.get(i).getEdition().getText());
                    pst.executeUpdate();
                }
                else
                {
                    sql = "SELECT COUNT(*)AS ROWCOUNT FROM BOOK_WITH_EDITION";

                    rs = s.executeQuery(sql);
                    rs.next();
                    int rownoofBE = rs.getInt("ROWCOUNT") + 1;
                    sql = "insert into BOOK_WITH_EDITION values(?,?,?,?)";

                    System.out.println("done");
                    pst = conn.prepareStatement(sql);
                    pst.setInt(1, rownoofBE + i + 1);
                    pst.setString(2, bookid);
                    pst.setInt(3, Integer.parseInt(Supplierlist.get(i).getAmountofbook().getText()));
                    pst.setString(4, Supplierlist.get(i).getEdition().getText());
                    pst.executeUpdate();
                }

               
            }
          sql = "COMMIT";
          rs = s.executeQuery(sql);
           
          Clear();
        }

    }

    @FXML
    private void handleDatePickerAction(ActionEvent event) {
    }

    @FXML
    private void handleDatePickerAction2(ActionEvent event) {
    }

    @FXML
    private void handleDatePickerAction3(ActionEvent event) {
    }

}
