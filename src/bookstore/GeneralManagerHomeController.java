
package bookstore;
import bookstore.classes.*;
import bookstore.classes.customer;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.effects.JFXDepthManager;
import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GeneralManagerHomeController implements Initializable {

    @FXML
    private Label name;
    @FXML
    private Label designation;
    @FXML
    private Label address;
    @FXML
    private Label phoneno;
    @FXML
    private Label emailid;
    @FXML
    private JFXButton editPro;
    @FXML
    private HBox hbox;
    @FXML
    private JFXButton booklist;
    @FXML
    private JFXButton employee;
    @FXML
    private VBox vbox2;
    @FXML
    private JFXButton cust;
    @FXML
    private JFXButton order;
    @FXML
    private JFXButton bookr;
    @FXML
    private JFXButton manager;
    @FXML
    private JFXButton supplier;
    @FXML
    private JFXButton publisher;
   
    @FXML
    private VBox vboxperday;
    @FXML
    private JFXTextField day;
    @FXML
    private VBox vboxpermonth;
    @FXML
    private JFXComboBox<String> monthcombo;
    @FXML
    private JFXTextField year;
    ObservableList<String> SCombo=FXCollections.observableArrayList("JANUARY","FEBRUARY","MARCH","APRIL","MAY","JUNE","JULY","AUGUST","SEPTEMBER","NOVEMBER","DECEMBER");
  
    String userid=null;
    String who=null;
    @FXML
    private VBox vboxpermonthbuy;
    @FXML
    private JFXComboBox<String> monthcombo1;
    @FXML
    private JFXTextField year1;
    @FXML
    private Label totalbuy;
    @FXML
    private Label cost;
    @FXML
    private JFXTextField employeeid;
    @FXML
    private Label howlong;
    @FXML
    private JFXComboBox<String> employeeCombo;
    ObservableList<String> eCombo=FXCollections.observableArrayList("STAFF","MANAGER");
    @FXML
    private VBox howlongv;
    @FXML
    private VBox vboxadd;
    @FXML
    private JFXComboBox<String> stafformanC;
    @FXML
    private JFXTextField nameE;
    @FXML
    private JFXTextField addE;
    @FXML
    private JFXTextField emailE;
    @FXML
    private JFXTextField phoneE;
    @FXML
    private JFXTextField salE;
    @FXML
    private JFXTextField commE;
    @FXML
    private JFXTextField desiE;
    @FXML
    private JFXTextField nationaE;
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         JFXDepthManager.setDepth(vboxperday, 30);
         JFXDepthManager.setDepth(vboxpermonth, 30);
         JFXDepthManager.setDepth(vboxpermonthbuy, 30);
         JFXDepthManager.setDepth(howlongv, 30);
         JFXDepthManager.setDepth(vboxadd, 30);
        monthcombo.setValue("JANUARY");
        monthcombo.setItems(SCombo);
        monthcombo.setValue("FEBRUARY");
        monthcombo.setItems(SCombo);
        monthcombo.setValue("MARCH");
        monthcombo.setItems(SCombo);
        monthcombo.setValue("APRIL");
        monthcombo.setItems(SCombo);
        monthcombo.setValue("MAY");
        monthcombo.setItems(SCombo);
        monthcombo.setValue("JUNE");
        monthcombo.setItems(SCombo);
        monthcombo.setValue("JULY");
        monthcombo.setItems(SCombo);
        monthcombo.setValue("AUGUST");
        monthcombo.setItems(SCombo);
        monthcombo.setValue("SEPTEMBER");
        monthcombo.setItems(SCombo);
        monthcombo.setValue("OCTOBER");
        monthcombo.setItems(SCombo);
        monthcombo.setValue("NOVEMBER");
        monthcombo.setItems(SCombo);
        monthcombo.setValue("DECEMBER");
        monthcombo.setItems(SCombo);
        
        monthcombo1.setValue("JANUARY");
        monthcombo1.setItems(SCombo);
        monthcombo1.setValue("FEBRUARY");
        monthcombo1.setItems(SCombo);
        monthcombo1.setValue("MARCH");
        monthcombo1.setItems(SCombo);
        monthcombo1.setValue("APRIL");
        monthcombo1.setItems(SCombo);
        monthcombo1.setValue("MAY");
        monthcombo1.setItems(SCombo);
        monthcombo1.setValue("JUNE");
        monthcombo1.setItems(SCombo);
        monthcombo1.setValue("JULY");
        monthcombo1.setItems(SCombo);
        monthcombo1.setValue("AUGUST");
        monthcombo1.setItems(SCombo);
        monthcombo1.setValue("SEPTEMBER");
        monthcombo1.setItems(SCombo);
        monthcombo1.setValue("OCTOBER");
        monthcombo1.setItems(SCombo);
        monthcombo1.setValue("NOVEMBER");
        monthcombo1.setItems(SCombo);
        monthcombo1.setValue("DECEMBER");
        monthcombo1.setItems(SCombo);
        
        employeeCombo.setValue("STAFF");
        employeeCombo.setItems(eCombo);
        employeeCombo.setValue("MANAGER");
        employeeCombo.setItems(eCombo);
        
        stafformanC.setValue("STAFF");
        stafformanC.setItems(eCombo);
        stafformanC.setValue("MANAGER");
        stafformanC.setItems(eCombo);
    }

    void setVariable(String user,String who)
    {
        userid=user;
        this.who=who;
        System.out.println(userid);
         try {
            System.out.println("userid:"+userid);
            Connection conn=new DBConnection().getConnection();
            String sql="SELECT GM_NAME,GM_ADDRESS,GM_EMAIL_ID,GM_PHONE_NO FROM GM WHERE GM_ID=?";
            PreparedStatement pst = null;
            ResultSet rs=null;
             pst = conn.prepareStatement(sql);
             pst.setString(1, userid);
             pst.executeUpdate();
             rs=pst.executeQuery();
             //System.out.println(rs.next());
             while(rs.next())
             {
                 System.out.println("inside");
                 name.setText(rs.getString("GM_NAME"));
                 name.setFocusTraversable(true);
                 //name.setFont("16px");
                 System.out.println("name:"+name.getText());
                 designation.setText("GENERAL MANAGER");
                 
                 address.setText(rs.getString("GM_ADDRESS"));
                 emailid.setText(rs.getString("GM_EMAIL_ID"));
                 phoneno.setText(rs.getString("GM_PHONE_NO"));
                 
             }
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    }
    void printRow(ArrayList<String> temp,String what)
    {
        System.out.println("hi");
        Parent root;
        Stage stage = null;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("showingDetail.fxml"));
            root = loader.load();
            ShowingDetailController controller = loader.getController();
            controller.settingvariable(temp,what);
            //System.out.println(user);

            //controller.setMain(this);
            Scene scene = new Scene(root);
            stage = new Stage(StageStyle.UNDECORATED);
            //stage.setTitle("home");
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }
    
    @FXML
    private void handleeditPro(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader();
            loader.setLocation(getClass().getResource("editpro.fxml"));
            Parent root=loader.load();
            EditproController controller=loader.getController();
            controller.settext("gm",name.getText(),designation.getText(),emailid.getText(),address.getText(),phoneno.getText(),userid);
            
            Scene scene = new Scene(root);
            Stage stage=new Stage(StageStyle.DECORATED);
            stage.setTitle("EDIT");
            stage.setScene(scene);
            stage.show();
    }
     public ObservableList<book> getProduct(String name) throws ClassNotFoundException, SQLException{
        ObservableList<book> books = FXCollections.observableArrayList();
         Class.forName("oracle.jdbc.OracleDriver");
         Connection conn = new DBConnection().getConnection();

         if (name.equals("$")) {
             String sql = "SELECT B.BOOK_ID,B.NAME,P.AMOUNT,B.SELLING_PRICE,B.DISCOUNT,B.VAT,P.EDITION FROM BOOK B,BOOK_WITH_EDITION P WHERE B.BOOK_ID=P.BOOK_ID";
             ResultSet rs = null;
             Statement s = conn.createStatement();

             rs = s.executeQuery(sql);
             while (rs.next()) {
                 books.add(new book(rs.getString("BOOK_ID"), rs.getString("NAME"), rs.getInt("AMOUNT"), rs.getInt("SELLING_PRICE"), rs.getInt("DISCOUNT"),rs.getInt("VAT"),rs.getString("EDITION")));
             }
         }
         else
         {
             
             String sql = "SELECT B.BOOK_ID,B.NAME,P.AMOUNT,B.SELLING_PRICE,B.DISCOUNT,B.VAT,P.EDITION FROM BOOK B,BOOK_WITH_EDITION P WHERE B.BOOK_ID=P.BOOK_ID AND LOWER(B.NAME) LIKE ?";
             ResultSet rs = null;
             String nlikethis = "%" + name.toLowerCase() + "%";
             PreparedStatement pst = null;
             pst = conn.prepareStatement(sql);
             pst.setString(1, nlikethis);
             pst.executeUpdate();
             rs = pst.executeQuery();
             while (rs.next()) {
                books.add(new book(rs.getString("BOOK_ID"), rs.getString("NAME"), rs.getInt("AMOUNT"), rs.getInt("SELLING_PRICE"), rs.getInt("DISCOUNT"),rs.getInt("VAT") ,rs.getString("EDITION")));
              }
         }
         
        return books;
    }
     
     public ObservableList<customer> getcust(String name) throws ClassNotFoundException, SQLException{
        ObservableList<customer> custs = FXCollections.observableArrayList();
         Class.forName("oracle.jdbc.OracleDriver");
         Connection conn = new DBConnection().getConnection();
         if(name.equals("$"))
         {
             String sql = "SELECT * FROM CUSTOMER";
             ResultSet rs = null;
             Statement s = conn.createStatement();

             rs = s.executeQuery(sql);
             while (rs.next()) {
                 custs.add(new customer(rs.getString("CUSTOMER_ID"), rs.getString("NAME"), rs.getString("EMAIL_ID"), rs.getString("PHONE_NO"), rs.getString("ADDRESS")));
             }
         }
         else
         {
             String sql = "SELECT CUSTOMER_ID,NAME,ADDRESS,EMAIL_ID,PHONE_NO FROM CUSTOMER WHERE LOWER(NAME) LIKE ?";
             ResultSet rs = null;
             String nlikethis = "%" + name.toLowerCase() + "%";
             PreparedStatement pst = null;
             pst = conn.prepareStatement(sql);
             pst.setString(1, nlikethis);
             pst.executeUpdate();
             rs = pst.executeQuery();
             while (rs.next()) {
                 custs.add(new customer(rs.getString("CUSTOMER_ID"), rs.getString("NAME"), rs.getString("EMAIL_ID"), rs.getString("PHONE_NO"), rs.getString("ADDRESS")));
             }
         }
        
        return custs;
    }
     
      public ObservableList<employee> getemps(String name) throws ClassNotFoundException, SQLException {
        ObservableList<employee> emps = FXCollections.observableArrayList();
        Class.forName("oracle.jdbc.OracleDriver");
        Connection conn = new DBConnection().getConnection();

        if(name.equals("$"))
          {
              String sql = "SELECT * FROM STAFFS";
              ResultSet rs = null;
              Statement s = conn.createStatement();

              rs = s.executeQuery(sql);
              while (rs.next()) {
                  emps.add(new employee(rs.getString("STAFF_ID"), rs.getString("STF_NAME"),rs.getString("STF_DESIGNATION"),rs.getString("STF_ADDRESS"), rs.getString("STF_EMAIL_ID"), rs.getString("STF_PHONE_NO"), Integer.toString(rs.getInt("STF_SALARY")), Integer.toString(rs.getInt("STF_COMMISSION_PCT")),rs.getDate("STF_JOINING_DATE"),rs.getDate("STF_END_DATE")));
              }
          }
        else
        {
            String sql = "SELECT STAFF_ID,STF_NAME,STF_DESIGNATION,STF_ADDRESS,STF_EMAIL_ID,STF_PHONE_NO,STF_SALARY,STF_COMMISSION_PCT,STF_JOINING_DATE,STF_END_DATE FROM STAFFS WHERE LOWER(STF_NAME) LIKE ?";
            ResultSet rs = null;
            String nlikethis = "%" + name.toLowerCase() + "%";
            PreparedStatement pst = null;
            pst = conn.prepareStatement(sql);
            pst.setString(1, nlikethis);
            pst.executeUpdate();
            rs = pst.executeQuery();
              while (rs.next()) {
              emps.add(new employee(rs.getString("STAFF_ID"), rs.getString("STF_NAME"),rs.getString("STF_DESIGNATION"), rs.getString("STF_ADDRESS"), rs.getString("STF_EMAIL_ID"), rs.getString("STF_PHONE_NO"), Integer.toString(rs.getInt("STF_SALARY")), Integer.toString(rs.getInt("STF_COMMISSION_PCT")),rs.getDate("STF_JOINING_DATE"),rs.getDate("STF_END_DATE")));
              }
          }
       
        return emps;
    }
      
    public ObservableList<order> getords() throws ClassNotFoundException, SQLException {
        ObservableList<order> ords = FXCollections.observableArrayList();
        Class.forName("oracle.jdbc.OracleDriver");
        Connection conn = new DBConnection().getConnection();

        String sql = "SELECT B.NAME,O.CUSTOMER_ID,O.O_DATE,O.AMOUNT  FROM BOOK B,ORDER_OF_BOOK O WHERE B.BOOK_ID=O.BOOK_ID";
        ResultSet rs = null;
        Statement s = conn.createStatement();

        rs = s.executeQuery(sql);
        while (rs.next()) {
            ords.add(new order(rs.getString("NAME"), rs.getInt("AMOUNT"), rs.getString("CUSTOMER_ID"), rs.getString("O_DATE")));

        }
        return ords;
    }
    
    public ObservableList<bookrequired> getbooksreq(String id) throws ClassNotFoundException, SQLException {
        ObservableList<bookrequired> ords = FXCollections.observableArrayList();
        Class.forName("oracle.jdbc.OracleDriver");
        Connection conn = new DBConnection().getConnection();

        if (id.equals("$")) {
            String sql = "SELECT * FROM BOOK_REQUIREMENT";
            ResultSet rs = null;
            Statement s = conn.createStatement();

            rs = s.executeQuery(sql);
            while (rs.next()) {
                ords.add(new bookrequired(rs.getString("BOOK_ID"), rs.getInt("AMOUNT_REQUIRED"),rs.getString("EDITION_REQUIRED")));

            }
        }
        else
        {
            String sql = "SELECT BOOK_ID,AMOUNT_REQUIRED,EDITION_REQUIRED FROM BOOK_REQUIREMENT WHERE LOWER(BOOK_ID) LIKE ?";
            String nlikethis="%"+id.toLowerCase()+"%";
            ResultSet rs = null;
            PreparedStatement pst = null;
            pst = conn.prepareStatement(sql);
            pst.setString(1, nlikethis);
            pst.executeUpdate();
            rs = pst.executeQuery();
            while (rs.next()) {
                ords.add(new bookrequired(rs.getString("BOOK_ID"), rs.getInt("AMOUNT_REQUIRED"),rs.getString("EDITION_REQUIRED")));

            }
        }
       
        return ords;
    }
     public ObservableList<publisher> getpub(String id) throws ClassNotFoundException, SQLException {
        ObservableList<publisher> ords = FXCollections.observableArrayList();
        Class.forName("oracle.jdbc.OracleDriver");
        Connection conn = new DBConnection().getConnection();

        if (id.equals("$")) {
            String sql = "SELECT * FROM PUBLISHER";
            ResultSet rs = null;
            Statement s = conn.createStatement();

            rs = s.executeQuery(sql);
            while (rs.next()) {
                ords.add(new publisher(rs.getString("PUBLISHER_ID"), rs.getString("COMPANY_NAME"),rs.getString("ADDRESS"),rs.getString("PHONE_NO"),rs.getString("EMAIL_ID")));

            }
        }
        else
        {
            String sql = "SELECT PUBLISHER_ID,COMPANY_NAME,ADDRESS,PHONE_NO,EMAIL_ID FROM PUBLISHER WHERE LOWER(COMPANY_NAME) LIKE ?";
            String nlikethis="%"+id.toLowerCase()+"%";
            ResultSet rs = null;
            PreparedStatement pst = null;
            pst = conn.prepareStatement(sql);
            pst.setString(1, nlikethis);
            pst.executeUpdate();
            rs = pst.executeQuery();
            while (rs.next()) {
                ords.add(new publisher(rs.getString("PUBLISHER_ID"), rs.getString("COMPANY_NAME"),rs.getString("ADDRESS"),rs.getString("PHONE_NO"),rs.getString("EMAIL_ID")));

            }
        }
       
        return ords;
    }
     public ObservableList<supplierS> getsupp(String id) throws ClassNotFoundException, SQLException {
        ObservableList<supplierS> ords = FXCollections.observableArrayList();
        Class.forName("oracle.jdbc.OracleDriver");
        Connection conn = new DBConnection().getConnection();

        if (id.equals("$")) {
            String sql = "SELECT * FROM SUPPLIER";
            ResultSet rs = null;
            Statement s = conn.createStatement();

            rs = s.executeQuery(sql);
            while (rs.next()) {
                ords.add(new supplierS(rs.getString("SUPPLIER_ID"), rs.getString("COMPANY_NAME"),rs.getString("ADDRESS"),rs.getString("PHONE_NO"),rs.getString("EMAIL_ID")));

            }
        }
        else
        {
            String sql = "SELECT SUPPLIER_ID,COMPANY_NAME,ADDRESS,PHONE_NO,EMAIL_ID FROM SUPPLIER WHERE LOWER(COMPANY_NAME) LIKE ?";
            String nlikethis="%"+id.toLowerCase()+"%";
            ResultSet rs = null;
            PreparedStatement pst = null;
            pst = conn.prepareStatement(sql);
            pst.setString(1, nlikethis);
            pst.executeUpdate();
            rs = pst.executeQuery();
            while (rs.next()) {
                ords.add(new supplierS(rs.getString("SUPPLIER_ID"), rs.getString("COMPANY_NAME"),rs.getString("ADDRESS"),rs.getString("PHONE_NO"),rs.getString("EMAIL_ID")));

            }
        }
       
        return ords;
    }
      public ObservableList<purchase> getpurchase(String id) throws ClassNotFoundException, SQLException {
        ObservableList<purchase> ords = FXCollections.observableArrayList();
        Class.forName("oracle.jdbc.OracleDriver");
        Connection conn = new DBConnection().getConnection();

        if (id.equals("$")) {
            String sql = "SELECT * FROM PURCHASE_OF_BOOK";
            ResultSet rs = null;
            Statement s = conn.createStatement();

            rs = s.executeQuery(sql);
            while (rs.next()) {
                ords.add(new purchase(rs.getString("PURCHASE_ID"), rs.getString("SUPPLIER_ID"),rs.getString("BOOK_ID"),rs.getString("BUYING_DATE"),rs.getInt("AMOUNT"),rs.getString("EDITION"),Integer.toString(rs.getInt("BUYING_PRICE"))));

            }
        }
        else
        {
            String sql = "SELECT PURCHASE_ID,SUPPLIER_ID,BOOK_ID,BUYING_DATE,AMOUNT,BUYING_PRICE,EDITION FROM PURCHASE_OF_BOOK WHERE BUYING_DATE LIKE ?";
            String nlikethis="%"+id+"%";
            ResultSet rs = null;
            PreparedStatement pst = null;
            pst = conn.prepareStatement(sql);
            pst.setString(1, nlikethis);
            pst.executeUpdate();
            rs = pst.executeQuery();
            while (rs.next()) {
               ords.add(new purchase(rs.getString("PURCHASE_ID"), rs.getString("SUPPLIER_ID"),rs.getString("BOOK_ID"),rs.getString("BUYING_DATE"),rs.getInt("AMOUNT"),rs.getString("EDITION"),Integer.toString(rs.getInt("BUYING_PRICE"))));

            }
        }
       
        return ords;
    }
    public ObservableList<managing> getmanaging(String id) throws ClassNotFoundException, SQLException {
        ObservableList<managing> ords = FXCollections.observableArrayList();
        Class.forName("oracle.jdbc.OracleDriver");
        Connection conn = new DBConnection().getConnection();

        if (id.equals("$")) {
            String sql = "SELECT * FROM MANAGER_STAFF_RELATION";
            ResultSet rs = null;
            Statement s = conn.createStatement();

            rs = s.executeQuery(sql);
            while (rs.next()) {
                ords.add(new managing(rs.getInt("MANAGING_ID"), rs.getInt("MANAGER_ID"),rs.getInt("STAFF_ID"),rs.getDate("MANAGING_START_DATE"),rs.getDate("MANAGING_END_DATE")));

            }
        }
        else
        {
            String sql = "SELECT MANAGING_ID,MANAGER_ID,STAFF_ID,MANAGING_START_DATE,MANAGING_END_DATE  FROM MANAGER_STAFF_RELATION WHERE STAFF_ID LIKE ?";
            String nlikethis="%"+id+"%";
            ResultSet rs = null;
            PreparedStatement pst = null;
            pst = conn.prepareStatement(sql);
            pst.setString(1, nlikethis);
            pst.executeUpdate();
            rs = pst.executeQuery();
            while (rs.next()) {
                ords.add(new managing(rs.getInt("MANAGING_ID"), rs.getInt("MANAGER_ID"),rs.getInt("STAFF_ID"),rs.getDate("MANAGING_START_DATE"),rs.getDate("MANAGING_END_DATE")));

            }
        }
       
        return ords;
    }

    public ObservableList<manager> getmanager(String name) throws ClassNotFoundException, SQLException {
        ObservableList<manager> ords = FXCollections.observableArrayList();
        Class.forName("oracle.jdbc.OracleDriver");
        Connection conn = new DBConnection().getConnection();

        if(name.equals("$"))
        {
            String sql = "SELECT * FROM MANAGERS";
            PreparedStatement pst = null;
            ResultSet rs = null;
            Statement s = conn.createStatement();

            rs = s.executeQuery(sql);
            while (rs.next()) {
                ords.add(new manager(Integer.toString(rs.getInt("MANAGER_ID")), rs.getString("MGR_NAME"), rs.getString("MGR_DESIGNATION"), rs.getString("MGR_ADDRESS"), rs.getString("MGR_EMAIL_ID"), rs.getString("MGR_PHONE_NO"), rs.getInt("MGR_SALARY"), rs.getInt("MGR_COMMISSION_PCT"),rs.getDate("MGR_JOINING_DATE"),rs.getDate("MGR_END_DATE")));
            }

        }
        else {
            String nlikethis = "%" + name.toLowerCase() + "%";
            String sql = "SELECT MANAGER_ID,MGR_NAME,MGR_ADDRESS,MGR_EMAIL_ID,MGR_PHONE_NO,MGR_SALARY,MGR_COMMISSION_PCT,MGR_DESIGNATION,MGR_JOINING_DATE,MGR_END_DATE FROM MANAGERS WHERE LOWER(MGR_NAME) LIKE ?";
            PreparedStatement pst = null;
            ResultSet rs = null;
            pst = conn.prepareStatement(sql);
            pst.setString(1, nlikethis);
            pst.executeUpdate();
            rs = pst.executeQuery();
            while (rs.next()) {
               ords.add(new manager(Integer.toString(rs.getInt("MANAGER_ID")), rs.getString("MGR_NAME"), rs.getString("MGR_DESIGNATION"), rs.getString("MGR_ADDRESS"), rs.getString("MGR_EMAIL_ID"), rs.getString("MGR_PHONE_NO"), rs.getInt("MGR_SALARY"), rs.getInt("MGR_COMMISSION_PCT"),rs.getDate("MGR_JOINING_DATE"),rs.getDate("MGR_END_DATE")));
           }

        }

        return ords;
    }
  
    @FXML
    private void handleBookList(ActionEvent event) throws ClassNotFoundException, SQLException 
    {
            if(!vbox2.getChildren().isEmpty()) vbox2.getChildren().clear();
        TableView<book> table= new TableView<>();
        //table = new TableView<>();
        //Name column
        TableColumn<book, String> IdColumn = new TableColumn<>("BOOK ID");
        IdColumn.setMinWidth(100);
        IdColumn.setCellValueFactory(new PropertyValueFactory<>("bookid"));

        //Price column
        TableColumn<book, String> nameColumn = new TableColumn<>("NAME");
        nameColumn.setMinWidth(100);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        //Quantity column
        TableColumn<book, Integer> quantityColumn = new TableColumn<>("AMOUNT");
        quantityColumn.setMinWidth(100);
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        
        
        //Quantity column
        TableColumn<book, Integer> priceColumn = new TableColumn<>("SELLING PRICE");
        priceColumn.setMinWidth(100);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("sellingprice"));
        
        
        //Quantity column
        TableColumn<book, Integer> discountColumn = new TableColumn<>("DISCOUNT");
        discountColumn.setMinWidth(100);
        discountColumn.setCellValueFactory(new PropertyValueFactory<>("discount"));
        
        TableColumn<book, Integer> vatColumn = new TableColumn<>("VAT");
        vatColumn.setMinWidth(100);
        vatColumn.setCellValueFactory(new PropertyValueFactory<>("vat"));
        
        //Quantity column
        TableColumn<book,String> ediColumn = new TableColumn<>("EDITION");
        ediColumn.setMinWidth(100);
        ediColumn.setCellValueFactory(new PropertyValueFactory<>("edition"));

        
      
        table.getColumns().addAll(IdColumn,nameColumn,quantityColumn,priceColumn,discountColumn,vatColumn,ediColumn);
        table.getItems().setAll(getProduct("$"));
       
         Label searchbyname=new Label("SEARCH BY NAME:");
        searchbyname.setPadding(new Insets(10, 0, 0, 0));
        JFXTextField search=new JFXTextField();
        search.setLabelFloat(true);
        search.setText("ENTER A NAME");
        
        JFXButton search2 =new JFXButton();
         //search2.setContentDisplay(ContentDisplay.TOP);
        Image image = new Image(getClass().getResourceAsStream("/icons/icons8-search-50.png"));
        //Button button1 = new Button("Accept");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(28);
        imageView.setFitWidth(30);
        search2.setGraphic(imageView);
        
        search2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    table.getItems().setAll(getProduct(search.getText()));
                   
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(GeneralManagerHomeController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(GeneralManagerHomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
        
       //search2.setPadding(new Insets(10, 0, 0, 0));
        HBox hbox =new HBox(30,searchbyname,search,search2);
        hbox.setPadding(new Insets(20, 0, 0, 20));
        vbox2.getChildren().addAll(table,hbox);
        
        table.setRowFactory(tv -> {
            TableRow<book> row = new TableRow<>();
            row.setOnMouseClicked(e -> {
                if (!row.isEmpty() && e.getButton() == MouseButton.PRIMARY
                        && e.getClickCount() == 2) {

                    book clickedRow = row.getItem();
                    ArrayList<String> temp=new ArrayList<>();
                    //for (int i = 0; i < 6; i++) {
                    Collections.addAll(temp,clickedRow.getBookid());
                    Collections.addAll(temp,clickedRow.getName());
                    Collections.addAll(temp,Integer.toString(clickedRow.getAmount()));
                    Collections.addAll(temp,clickedRow.getEdition());
                    Collections.addAll(temp,Integer.toString(clickedRow.getSellingprice()));
                    Collections.addAll(temp,Integer.toString(clickedRow.getDiscount()));
                    Collections.addAll(temp,Integer.toString(clickedRow.getVat()));
                    //Integer.toString(clickedRow.getAmount()),clickedRow.);
                    //}
                    printRow(temp,"book");
                }
            });
            return row;
        });

    }
    


    @FXML
    private void handleEmployee(ActionEvent event) throws ClassNotFoundException, SQLException {
        if(!vbox2.getChildren().isEmpty()) vbox2.getChildren().clear();
        TableView<employee> table= new TableView<>();
        //table = new TableView<>();
        //Name column
        TableColumn<employee, String> IdColumn = new TableColumn<>("EMPLOYEE ID");
        IdColumn.setMinWidth(50);
        IdColumn.setCellValueFactory(new PropertyValueFactory<>("empid"));

        //Price column
        TableColumn<employee, String> nameColumn = new TableColumn<>("NAME");
        nameColumn.setMinWidth(100);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        TableColumn<employee, String> desiColumn = new TableColumn<>("DESIGNATION");
        desiColumn.setMinWidth(100);
        desiColumn.setCellValueFactory(new PropertyValueFactory<>("designation"));
       
        //Quantity column
        TableColumn<employee, String> emailColumn = new TableColumn<>("EMAIL");
        emailColumn.setMinWidth(100);
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("emailid"));
        
        
        //Quantity column
        TableColumn<employee, String> phoneColumn = new TableColumn<>("PHONE NO");
        phoneColumn.setMinWidth(100);
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phoneno"));
        
        
        //Quantity column
        TableColumn<employee, String> addColumn = new TableColumn<>("ADDRESS");
        addColumn.setMinWidth(100);
        addColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        
         //Quantity column
        TableColumn<employee, Integer> salColumn = new TableColumn<>("SALARY");
        salColumn.setMinWidth(100);
        salColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));
        
         //Quantity column
        TableColumn<employee, Integer> commColumn = new TableColumn<>("COMMISSION PCT");
        commColumn.setMinWidth(100);
        commColumn.setCellValueFactory(new PropertyValueFactory<>("commissionpct"));
        
        TableColumn<employee, Integer> SDColumn = new TableColumn<>("START DATE");
        SDColumn.setMinWidth(100);
        SDColumn.setCellValueFactory(new PropertyValueFactory<>("Sdate"));
       
        TableColumn<employee, Integer> EDColumn = new TableColumn<>("END DATE");
        EDColumn.setMinWidth(100);
        EDColumn.setCellValueFactory(new PropertyValueFactory<>("Edate"));
        
        
       
        table.getColumns().addAll(IdColumn,nameColumn,desiColumn,emailColumn,phoneColumn,addColumn,salColumn,commColumn,SDColumn,EDColumn);
        table.getItems().setAll(getemps("$"));
       
         Label searchbyname=new Label("SEARCH BY NAME:");
        searchbyname.setPadding(new Insets(10, 0, 0, 0));
        JFXTextField search=new JFXTextField();
        search.setLabelFloat(true);
        search.setText("ENTER A NAME");
        
        JFXButton search2 =new JFXButton();
         //search2.setContentDisplay(ContentDisplay.TOP);
        Image image = new Image(getClass().getResourceAsStream("/icons/icons8-search-50.png"));
        //Button button1 = new Button("Accept");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(28);
        imageView.setFitWidth(30);
        search2.setGraphic(imageView);
        
        search2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    table.getItems().setAll(getemps(search.getText()));
                   
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(GeneralManagerHomeController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(GeneralManagerHomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
        
       //search2.setPadding(new Insets(10, 0, 0, 0));
        HBox hbox =new HBox(30,searchbyname,search,search2);
        hbox.setPadding(new Insets(20, 0, 0, 20));
        vbox2.getChildren().addAll(table,hbox);
        table.setRowFactory(tv -> {
            TableRow<employee> row = new TableRow<>();
            row.setOnMouseClicked(e -> {
                if (!row.isEmpty() && e.getButton() == MouseButton.PRIMARY
                        && e.getClickCount() == 2) {

                    employee clickedRow = row.getItem();
                    ArrayList<String> temp=new ArrayList<>();
                    
                    Format formatter = new SimpleDateFormat("yyyy-MM-dd");
                    String s = formatter.format(clickedRow.getSdate());
                    String end=null;
                    if(clickedRow.getEdate()==null)
                    {
                        end = "null";
                    } else {
                        Format formatter1 = new SimpleDateFormat("yyyy-MM-dd");
                        end = formatter1.format(clickedRow.getEdate());

                    }
                    
                    //for (int i = 0; i < 6; i++) {
                    Collections.addAll(temp,clickedRow.getEmpid());
                    Collections.addAll(temp,clickedRow.getName());
                    Collections.addAll(temp, clickedRow.getDesignation());
                    Collections.addAll(temp,clickedRow.getAddress());
                    Collections.addAll(temp,clickedRow.getEmailid());
                    Collections.addAll(temp,clickedRow.getPhoneno());
                    Collections.addAll(temp,clickedRow.getSalary());
                    Collections.addAll(temp,clickedRow.getCommissionpct());
                    //Integer.toString(clickedRow.getAmount()),clickedRow.);
                    Collections.addAll(temp,s);
                    Collections.addAll(temp,end);
                    //}
                    printRow(temp,"employee");
                }
            });
            return row;
        });
    }

    @FXML
    private void handleCust(ActionEvent event) throws ClassNotFoundException, SQLException {
        
        if(!vbox2.getChildren().isEmpty()) vbox2.getChildren().clear();
        TableView<customer> table= new TableView<>();
        //table = new TableView<>();
        //Name column
        TableColumn<customer, String> IdColumn = new TableColumn<>("CUSTOMER ID");
        IdColumn.setMinWidth(100);
        IdColumn.setCellValueFactory(new PropertyValueFactory<>("custid"));

        //Price column
        TableColumn<customer, String> nameColumn = new TableColumn<>("NAME");
        nameColumn.setMinWidth(100);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("custname"));

        //Quantity column
        TableColumn<customer, String> emailColumn = new TableColumn<>("EMAIL");
        emailColumn.setMinWidth(100);
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("custemail"));
        
        
        //Quantity column
        TableColumn<customer, String> phoneColumn = new TableColumn<>("PHONE NO");
        phoneColumn.setMinWidth(100);
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("custphone"));
        
        
        //Quantity column
        TableColumn<customer, String> addColumn = new TableColumn<>("ADDRESS");
        addColumn.setMinWidth(100);
        addColumn.setCellValueFactory(new PropertyValueFactory<>("custadd"));
       
        table.getColumns().addAll(IdColumn,nameColumn,emailColumn,phoneColumn,addColumn);
        table.getItems().setAll(getcust("$"));
       
         Label searchbyname=new Label("SEARCH BY NAME:");
        searchbyname.setPadding(new Insets(10, 0, 0, 0));
        JFXTextField search=new JFXTextField();
        search.setLabelFloat(true);
        search.setText("ENTER A NAME");
        
        JFXButton search2 =new JFXButton();
         //search2.setContentDisplay(ContentDisplay.TOP);
        Image image = new Image(getClass().getResourceAsStream("/icons/icons8-search-50.png"));
        //Button button1 = new Button("Accept");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(28);
        imageView.setFitWidth(30);
        search2.setGraphic(imageView);
        
        search2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    table.getItems().setAll(getcust(search.getText()));
                   
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(GeneralManagerHomeController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(GeneralManagerHomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
        
       //search2.setPadding(new Insets(10, 0, 0, 0));
        HBox hbox =new HBox(30,searchbyname,search,search2);
        hbox.setPadding(new Insets(20, 0, 0, 20));
        vbox2.getChildren().addAll(table,hbox);
        
        table.setRowFactory(tv -> {
            TableRow<customer> row = new TableRow<>();
            row.setOnMouseClicked(e -> {
                if (!row.isEmpty() && e.getButton() == MouseButton.PRIMARY
                        && e.getClickCount() == 2) {

                    customer clickedRow = row.getItem();
                    ArrayList<String> temp=new ArrayList<>();
                    //for (int i = 0; i < 6; i++) {
                    Collections.addAll(temp,clickedRow.getCustid());
                    Collections.addAll(temp,clickedRow.getCustname());
                    Collections.addAll(temp,clickedRow.getCustadd());
                    Collections.addAll(temp,clickedRow.getCustemail());
                    Collections.addAll(temp,clickedRow.getCustphone());
                    
                    printRow(temp,"customer");
                }
            });
            return row;
        });
        
    }

    @FXML
    private void handleOrder(ActionEvent event) throws ClassNotFoundException, SQLException {
        if(!vbox2.getChildren().isEmpty()) vbox2.getChildren().clear();
        TableView<order> table= new TableView<>();
        //table = new TableView<>();
        //Name column
        TableColumn<order, String> nameColumn = new TableColumn<>("BOOK NAME");
        nameColumn.setMinWidth(100);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("bookname"));

        //Price column
        TableColumn<order, Integer> amountColumn = new TableColumn<>("AMOUNT");
        amountColumn.setMinWidth(100);
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));

        //Quantity column
        TableColumn<order, String> custidColumn = new TableColumn<>("CUSTOMER ID");
        custidColumn.setMinWidth(100);
        custidColumn.setCellValueFactory(new PropertyValueFactory<>("customerid"));
        
        
        //Quantity column
        TableColumn<order, String> dateColumn = new TableColumn<>("DATE");
        dateColumn.setMinWidth(100);
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        
        
        table.getColumns().addAll(nameColumn,amountColumn,custidColumn,dateColumn);
        table.getItems().setAll(getords());
       
        vbox2.getChildren().addAll(table);
        table.setRowFactory(tv -> {
            TableRow<order> row = new TableRow<>();
            row.setOnMouseClicked(e -> {
                if (!row.isEmpty() && e.getButton() == MouseButton.PRIMARY
                        && e.getClickCount() == 2) {

                    order clickedRow = row.getItem();
                    ArrayList<String> temp=new ArrayList<>();
                    //for (int i = 0; i < 6; i++) {
                    Collections.addAll(temp,clickedRow.getBookname());
                    Collections.addAll(temp,Integer.toString(clickedRow.getAmount()));
                    Collections.addAll(temp,clickedRow.getCustomerid());
                    Collections.addAll(temp,clickedRow.getDate());
                    //Collections.addAll(temp,clickedRow.getCustphone());
                    
                    printRow(temp,"order");
                }
            });
            return row;
        });
    }

    @FXML
    private void bookRequired(ActionEvent event) throws ClassNotFoundException, SQLException {
     if(!vbox2.getChildren().isEmpty()) vbox2.getChildren().clear();
        TableView<bookrequired> table= new TableView<>();
        //table = new TableView<>();
        //Name column
        TableColumn<bookrequired, String> nameColumn = new TableColumn<>("BOOK ID");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("bookid"));

        //Price column
        TableColumn<bookrequired, Integer> amountColumn = new TableColumn<>("AMOUNT");
        amountColumn.setMinWidth(100);
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));

        TableColumn<bookrequired, Integer> EDIColumn = new TableColumn<>("EDITION");
        EDIColumn.setMinWidth(100);
        EDIColumn.setCellValueFactory(new PropertyValueFactory<>("edition"));

        
        table.getColumns().addAll(nameColumn,amountColumn,EDIColumn);
        table.getItems().setAll(getbooksreq("$"));
       
        Label searchbyname=new Label("SEARCH BY BOOKID:");
        searchbyname.setPadding(new Insets(10, 0, 0, 0));
        JFXTextField search=new JFXTextField();
        search.setText("ENTER AN ID");
        search.setLabelFloat(true);
        JFXButton search2 =new JFXButton();
         //search2.setContentDisplay(ContentDisplay.TOP);
        Image image = new Image(getClass().getResourceAsStream("/icons/icons8-search-50.png"));
        //Button button1 = new Button("Accept");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(28);
        imageView.setFitWidth(30);
        search2.setGraphic(imageView);
        
        search2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    table.getItems().setAll(getbooksreq(search.getText()));
                   
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(GeneralManagerHomeController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(GeneralManagerHomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
        
       //search2.setPadding(new Insets(10, 0, 0, 0));
        HBox hbox =new HBox(30,searchbyname,search,search2);
        hbox.setPadding(new Insets(20, 0, 0, 20));
        vbox2.getChildren().addAll(table,hbox);
        table.setRowFactory(tv -> {
            TableRow<bookrequired> row = new TableRow<>();
            row.setOnMouseClicked(e -> {
                if (!row.isEmpty() && e.getButton() == MouseButton.PRIMARY
                        && e.getClickCount() == 2) {

                    bookrequired clickedRow = row.getItem();
                    ArrayList<String> temp=new ArrayList<>();
                    //for (int i = 0; i < 6; i++) {
                    Collections.addAll(temp,clickedRow.getBookid());
                    Collections.addAll(temp,Integer.toString(clickedRow.getAmount()));
                    Collections.addAll(temp,clickedRow.getEdition());
//                    Collections.addAll(temp,clickedRow.getDate());
                    //Collections.addAll(temp,clickedRow.getCustphone());
                    
                    printRow(temp,"bookrequired");
                }
            });
            return row;
        });
    }


    @FXML
    private void managerlist(ActionEvent event) throws ClassNotFoundException, SQLException {
        if(!vbox2.getChildren().isEmpty()) vbox2.getChildren().clear();
        TableView<manager> table= new TableView<>();
        //table = new TableView<>();
        //Name column
        TableColumn<manager, String> IdColumn = new TableColumn<>("MANAGER ID");
        IdColumn.setMinWidth(50);
        IdColumn.setCellValueFactory(new PropertyValueFactory<>("managerid"));

        //Price column
        TableColumn<manager, String> nameColumn = new TableColumn<>("NAME");
        nameColumn.setMinWidth(100);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        //Quantity column
        TableColumn<manager, String> emailColumn = new TableColumn<>("EMAIL");
        emailColumn.setMinWidth(100);
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        
        
        //Quantity column
        TableColumn<manager, String> phoneColumn = new TableColumn<>("PHONE NO");
        phoneColumn.setMinWidth(100);
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phoneno"));
        
        
        //Quantity column
        TableColumn<manager, String> addColumn = new TableColumn<>("ADDRESS");
        addColumn.setMinWidth(100);
        addColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        
        TableColumn<manager, String> desColumn = new TableColumn<>("DESIGNATION");
        desColumn.setMinWidth(100);
        desColumn.setCellValueFactory(new PropertyValueFactory<>("designation"));
        
         //Quantity column
        TableColumn<manager, Integer> salColumn = new TableColumn<>("SALARY");
        salColumn.setMinWidth(100);
        salColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));
        
         //Quantity column
        TableColumn<manager, Integer> commColumn = new TableColumn<>("COMMISSION PCT");
        commColumn.setMinWidth(100);
        commColumn.setCellValueFactory(new PropertyValueFactory<>("commpct"));
        
        TableColumn<manager, Integer> sdateColumn = new TableColumn<>("START DATE");
        sdateColumn.setMinWidth(100);
        sdateColumn.setCellValueFactory(new PropertyValueFactory<>("Sdate"));
       
        TableColumn<manager, Integer> edateColumn = new TableColumn<>("END DATE");
        edateColumn.setMinWidth(100);
        edateColumn.setCellValueFactory(new PropertyValueFactory<>("Edate"));
       
        table.getColumns().addAll(IdColumn,nameColumn,desColumn,emailColumn,phoneColumn,addColumn,salColumn,commColumn,sdateColumn,edateColumn);
        table.getItems().setAll(getmanager("$"));
       
        Label searchbyname=new Label("SEARCH BY NAME:");
        searchbyname.setPadding(new Insets(10, 0, 0, 0));
        JFXTextField search=new JFXTextField();
        search.setText("ENTER A NAME");
        search.setLabelFloat(true);
        JFXButton search2 =new JFXButton();
         //search2.setContentDisplay(ContentDisplay.TOP);
        Image image = new Image(getClass().getResourceAsStream("/icons/icons8-search-50.png"));
        //Button button1 = new Button("Accept");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(28);
        imageView.setFitWidth(30);
        search2.setGraphic(imageView);
        
        search2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    table.getItems().setAll(getmanager(search.getText()));
                   
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(GeneralManagerHomeController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(GeneralManagerHomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
        
       //search2.setPadding(new Insets(10, 0, 0, 0));
        HBox hbox =new HBox(30,searchbyname,search,search2);
        hbox.setPadding(new Insets(20, 0, 0, 20));
        vbox2.getChildren().addAll(table,hbox);
         table.setRowFactory(tv -> {
            TableRow<manager> row = new TableRow<>();
            row.setOnMouseClicked(e -> {
                if (!row.isEmpty() && e.getButton() == MouseButton.PRIMARY
                        && e.getClickCount() == 2) {

                    manager clickedRow = row.getItem();
                    ArrayList<String> temp=new ArrayList<>();
                    
                     Format formatter = new SimpleDateFormat("yyyy-MM-dd");
                    String s = formatter.format(clickedRow.getSdate());
                    String end=null;
                    if(clickedRow.getEdate()==null)
                    {
                        end = "null";
                    } else {
                        Format formatter1 = new SimpleDateFormat("yyyy-MM-dd");
                        end = formatter1.format(clickedRow.getEdate());

                    }
                    //for (int i = 0; i < 6; i++) {
                    Collections.addAll(temp,clickedRow.getManagerid());
                    Collections.addAll(temp,clickedRow.getName());
                    Collections.addAll(temp,clickedRow.getAddress());
                    Collections.addAll(temp,clickedRow.getEmail());
                    Collections.addAll(temp,clickedRow.getPhoneno());
                    Collections.addAll(temp,clickedRow.getDesignation());
                    Collections.addAll(temp,Integer.toString(clickedRow.getSalary()));
                    Collections.addAll(temp,Integer.toString(clickedRow.getCommpct()));
                    Collections.addAll(temp,s);
                    Collections.addAll(temp,end);
                   
                    //Integer.toString(clickedRow.getAmount()),clickedRow.);
                    //}
                    printRow(temp,"manager");
                }
            });
            return row;
        });
        
    }

    @FXML
    private void handleSupplier(ActionEvent event) throws ClassNotFoundException, SQLException {
         if (!vbox2.getChildren().isEmpty()) {
            vbox2.getChildren().clear();
        }
        TableView<supplierS> table = new TableView<>();
        //table = new TableView<>();
        //Name column
        TableColumn<supplierS, String> nameColumn = new TableColumn<>("SUPPLIER ID");
        nameColumn.setMinWidth(100);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("suppid"));

        //Price column
        TableColumn<supplierS, Integer> amountColumn = new TableColumn<>("COMPANY NAME");
        amountColumn.setMinWidth(100);
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("compname"));

        TableColumn<supplierS, Integer> EDIColumn = new TableColumn<>("ADDRESS");
        EDIColumn.setMinWidth(100);
        EDIColumn.setCellValueFactory(new PropertyValueFactory<>("add"));
        
        TableColumn<supplierS, Integer> ADDColumn = new TableColumn<>("PHONE NO");
        ADDColumn.setMinWidth(100);
        ADDColumn.setCellValueFactory(new PropertyValueFactory<>("phoneno"));

        TableColumn<supplierS, Integer> EMColumn = new TableColumn<>("EMAIL ID");
        EMColumn.setMinWidth(100);
        EMColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
 
        table.getColumns().addAll(nameColumn, amountColumn, EDIColumn,ADDColumn,EMColumn);
        table.getItems().setAll(getsupp("$"));

        Label searchbyname = new Label("SEARCH BY COMPANY:");
        searchbyname.setPadding(new Insets(10, 0, 0, 0));
        JFXTextField search = new JFXTextField();
        search.setText("ENTER A NAME");
        search.setLabelFloat(true);
        JFXButton search2 = new JFXButton();
        //search2.setContentDisplay(ContentDisplay.TOP);
        Image image = new Image(getClass().getResourceAsStream("/icons/icons8-search-50.png"));
        //Button button1 = new Button("Accept");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(28);
        imageView.setFitWidth(30);
        search2.setGraphic(imageView);

        search2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    table.getItems().setAll(getsupp(search.getText()));

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(GeneralManagerHomeController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(GeneralManagerHomeController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        //search2.setPadding(new Insets(10, 0, 0, 0));
        HBox hbox = new HBox(30, searchbyname, search, search2);
        hbox.setPadding(new Insets(20, 0, 0, 20));
        vbox2.getChildren().addAll(table, hbox);
        table.setRowFactory(tv -> {
            TableRow<supplierS> row = new TableRow<>();
            row.setOnMouseClicked(e -> {
                if (!row.isEmpty() && e.getButton() == MouseButton.PRIMARY
                        && e.getClickCount() == 2) {

                    supplierS clickedRow = row.getItem();
                    ArrayList<String> temp = new ArrayList<>();
                    //for (int i = 0; i < 6; i++) {
                    Collections.addAll(temp, clickedRow.getSuppid());
                    Collections.addAll(temp,clickedRow.getCompname());
                    Collections.addAll(temp, clickedRow.getAdd());
                    Collections.addAll(temp,clickedRow.getPhoneno());
                    Collections.addAll(temp,clickedRow.getEmail());

                    printRow(temp, "supplier");
                }
            });
            return row;
        });
    }

    @FXML
    private void handlepublisher(ActionEvent event) throws ClassNotFoundException, SQLException {
        if (!vbox2.getChildren().isEmpty()) {
            vbox2.getChildren().clear();
        }
        TableView<publisher> table = new TableView<>();
        //table = new TableView<>();
        //Name column
        TableColumn<publisher, String> nameColumn = new TableColumn<>("PUBLISHER ID");
        nameColumn.setMinWidth(100);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("pubid"));

        //Price column
        TableColumn<publisher, Integer> amountColumn = new TableColumn<>("COMPANY NAME");
        amountColumn.setMinWidth(100);
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("compname"));

        TableColumn<publisher, Integer> EDIColumn = new TableColumn<>("ADDRESS");
        EDIColumn.setMinWidth(100);
        EDIColumn.setCellValueFactory(new PropertyValueFactory<>("add"));
        
        TableColumn<publisher, Integer> ADDColumn = new TableColumn<>("PHONE NO");
        ADDColumn.setMinWidth(100);
        ADDColumn.setCellValueFactory(new PropertyValueFactory<>("phoneno"));

        TableColumn<publisher, Integer> EMColumn = new TableColumn<>("EMAIL ID");
        EMColumn.setMinWidth(100);
        EMColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
 
        table.getColumns().addAll(nameColumn, amountColumn, EDIColumn,ADDColumn,EMColumn);
        table.getItems().setAll(getpub("$"));

        Label searchbyname = new Label("SEARCH BY COMPANY:");
        searchbyname.setPadding(new Insets(10, 0, 0, 0));
        JFXTextField search = new JFXTextField();
        search.setText("ENTER A NAME");
        search.setLabelFloat(true);
        JFXButton search2 = new JFXButton();
        //search2.setContentDisplay(ContentDisplay.TOP);
        Image image = new Image(getClass().getResourceAsStream("/icons/icons8-search-50.png"));
        //Button button1 = new Button("Accept");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(28);
        imageView.setFitWidth(30);
        search2.setGraphic(imageView);

        search2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    table.getItems().setAll(getpub(search.getText()));

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(GeneralManagerHomeController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(GeneralManagerHomeController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        //search2.setPadding(new Insets(10, 0, 0, 0));
        HBox hbox = new HBox(30, searchbyname, search, search2);
        hbox.setPadding(new Insets(20, 0, 0, 20));
        vbox2.getChildren().addAll(table, hbox);
        table.setRowFactory(tv -> {
            TableRow<publisher> row = new TableRow<>();
            row.setOnMouseClicked(e -> {
                if (!row.isEmpty() && e.getButton() == MouseButton.PRIMARY
                        && e.getClickCount() == 2) {

                    publisher clickedRow = row.getItem();
                    ArrayList<String> temp = new ArrayList<>();
                    //for (int i = 0; i < 6; i++) {
                    Collections.addAll(temp, clickedRow.getPubid());
                    Collections.addAll(temp,clickedRow.getCompname());
                    Collections.addAll(temp, clickedRow.getAdd());
                    Collections.addAll(temp,clickedRow.getPhoneno());
                    Collections.addAll(temp,clickedRow.getEmail());

                    printRow(temp, "publisher");
                }
            });
            return row;
        });
    }

    @FXML
    private void handleperday(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
        Class.forName("oracle.jdbc.OracleDriver");
        Connection conn = new DBConnection().getConnection();
        String getDBUSERByUserIdSql = "{call PER_DAY_SALE_PROFIT(?,?,?)}";
        CallableStatement callableStatement = null;
        callableStatement  = conn.prepareCall(getDBUSERByUserIdSql);
        callableStatement.setString(1,day.getText());
        callableStatement.registerOutParameter(2, java.sql.Types.NUMERIC);
        callableStatement.registerOutParameter(3, java.sql.Types.NUMERIC);
        //callableStatement.registerOutParameter(4, java.sql.Types.DATE);

// execute getDBUSERByUserId store procedure
        callableStatement.executeUpdate();

        int sale = callableStatement.getInt(2);
        int profit = callableStatement.getInt(3);
       // Date createdDate = callableStatement.getDate(4);
//       this.sale.setText("TOTAL SALE: "+Integer.toString(sale));
//       this.profitperday.setText("PROFIT: "+Integer.toString(profit));

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("dailyPro.fxml"));
        Parent root = loader.load();
        DailyProController controller = loader.getController();
        controller.setVariables(Integer.toString(sale),Integer.toString(profit));

        Scene scene = new Scene(root);
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("daily Pro");
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void handlepermonth(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
        
        String mon=monthcombo.getValue();
        String moninno=null;
        if(mon.equals("JANUARY")) moninno="01";
        else if(mon.equals("FEBRUARY")) moninno="02";
         else if(mon.equals("MARCH")) moninno="03";
        else if(mon.equals("APRIL")) moninno="04";
        else if(mon.equals("MAY")) moninno="05";
        else if(mon.equals("JUNE")) moninno="06";
        else if(mon.equals("JULY")) moninno="07";
        else if(mon.equals("AUGUST")) moninno="08";
        else if(mon.equals("SEPTEMBER")) moninno="09";
        else if(mon.equals("OCTOBER")) moninno="10";
        else if(mon.equals("NOVEMBER")) moninno="11";
        else  moninno="12";
        Class.forName("oracle.jdbc.OracleDriver");
        Connection conn = new DBConnection().getConnection();
        String getDBUSERByUserIdSql = "{call PER_MONTH_SALE_PROFIT(?,?,?,?,?,?,?,?,?)}";
        CallableStatement callableStatement = null;
        callableStatement  = conn.prepareCall(getDBUSERByUserIdSql);
        callableStatement.setString(1,moninno);
        callableStatement.setString(2,year.getText());
        callableStatement.registerOutParameter(3, java.sql.Types.NUMERIC);
        callableStatement.registerOutParameter(4, java.sql.Types.NUMERIC);
        callableStatement.registerOutParameter(5, java.sql.Types.NUMERIC);
        callableStatement.registerOutParameter(6, java.sql.Types.NUMERIC);
        callableStatement.registerOutParameter(7, java.sql.Types.NUMERIC);
        callableStatement.registerOutParameter(8, java.sql.Types.NUMERIC);
        callableStatement.registerOutParameter(9, java.sql.Types.NUMERIC);

// execute getDBUSERByUserId store procedure
        callableStatement.executeUpdate();

        String totalsale=Integer.toString(callableStatement.getInt(4));
        String income=Integer.toString(callableStatement.getInt(3));
        String bookBuyCost=Integer.toString(callableStatement.getInt(5));
        String stfSal=Integer.toString(callableStatement.getInt(6));
        String manSal=Integer.toString(callableStatement.getInt(7));
        String gmSal=Integer.toString(callableStatement.getInt(8));
        String Profit=Integer.toString(callableStatement.getInt(9));
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("monthlyPro.fxml"));
        Parent root = loader.load();
        MonthlyProController controller = loader.getController();
        controller.setVariables(totalsale,income,bookBuyCost,stfSal,manSal,gmSal,Profit);

        Scene scene = new Scene(root);
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("Monthly Pro");
        stage.setScene(scene);
        stage.show();
        
    }

    @FXML
    private void handlesalaryC(ActionEvent event) {
        Parent root;
        Stage stage = null;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("salaryC.fxml"));
            root = loader.load();
            Scene scene = new Scene(root);
            stage = new Stage(StageStyle.DECORATED);
            //stage.setTitle("home");
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }

    @FXML
    private void handleManageC(ActionEvent event) {
         Parent root;
        Stage stage = null;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("changeManaging.fxml"));
            root = loader.load();
              Scene scene = new Scene(root);
            stage = new Stage(StageStyle.DECORATED);
            //stage.setTitle("home");
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }

    @FXML
    private void handleDesignationC(ActionEvent event) {
           Parent root;
        Stage stage = null;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("changeDes.fxml"));
            root = loader.load();
              Scene scene = new Scene(root);
            stage = new Stage(StageStyle.DECORATED);
            //stage.setTitle("home");
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }

    @FXML
    private void dischargeE(ActionEvent event) {
           Parent root;
        Stage stage = null;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("dismiss.fxml"));
            root = loader.load();
              Scene scene = new Scene(root);
            stage = new Stage(StageStyle.DECORATED);
            //stage.setTitle("home");
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }

    @FXML
    private void handlepermonthbuy(ActionEvent event) throws ClassNotFoundException, SQLException {
         String mon=monthcombo1.getValue();
        String moninno=null;
        if(mon.equals("JANUARY")) moninno="01";
        else if(mon.equals("FEBRUARY")) moninno="02";
        else if(mon.equals("MARCH")) moninno="03";
        else if(mon.equals("APRIL")) moninno="04";
        else if(mon.equals("MAY")) moninno="05";
        else if(mon.equals("JUNE")) moninno="06";
        else if(mon.equals("JULY")) moninno="07";
        else if(mon.equals("AUGUST")) moninno="08";
        else if(mon.equals("SEPTEMBER")) moninno="09";
        else if(mon.equals("OCTOBER")) moninno="10";
        else if(mon.equals("NOVEMBER")) moninno="11";
        else  moninno="12";
        System.out.println(moninno);
        Class.forName("oracle.jdbc.OracleDriver");
        Connection conn = new DBConnection().getConnection();
        String getDBUSERByUserIdSql = "{call PER_MONTH_TOTAL_BUY_EXPENSE(?,?,?,?)}";
        CallableStatement callableStatement = null;
        callableStatement  = conn.prepareCall(getDBUSERByUserIdSql);
        callableStatement.setString(1,moninno);
        callableStatement.setString(2,year1.getText());
        callableStatement.registerOutParameter(3, java.sql.Types.NUMERIC);
        callableStatement.registerOutParameter(4, java.sql.Types.NUMERIC);
        
        
        callableStatement.executeUpdate();
        System.out.println( callableStatement.getInt(3));

        totalbuy.setText("TOTAL BUY: " + callableStatement.getInt(3));
        cost.setText("TOTAL COST: " + callableStatement.getInt(4));
    }

    @FXML
    private void handlePurchase(ActionEvent event) throws ClassNotFoundException, SQLException {
        if (!vbox2.getChildren().isEmpty()) {
            vbox2.getChildren().clear();
        }
        TableView<purchase> table = new TableView<>();
        //table = new TableView<>();
        //Name column
        TableColumn<purchase, String> nameColumn = new TableColumn<>("PURCHASE ID");
        nameColumn.setMinWidth(100);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("purid"));

        //Price column
        TableColumn<purchase, Integer> amountColumn = new TableColumn<>("SUPPLIER ID");
        amountColumn.setMinWidth(100);
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("suppid"));

        TableColumn<purchase, Integer> EDIColumn = new TableColumn<>("BOOK ID");
        EDIColumn.setMinWidth(100);
        EDIColumn.setCellValueFactory(new PropertyValueFactory<>("bookid"));
        
        TableColumn<purchase, Integer> ADDColumn = new TableColumn<>("DATE");
        ADDColumn.setMinWidth(100);
        ADDColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        TableColumn<purchase, Integer> EMColumn = new TableColumn<>("AMOUNT");
        EMColumn.setMinWidth(100);
        EMColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        
        TableColumn<purchase, Integer> EDITColumn = new TableColumn<>("EDITION");
        EDITColumn.setMinWidth(100);
        EDITColumn.setCellValueFactory(new PropertyValueFactory<>("edi"));
        
        TableColumn<purchase, Integer> PRICEColumn = new TableColumn<>("PRICE PER BOOK");
        PRICEColumn.setMinWidth(100);
        PRICEColumn.setCellValueFactory(new PropertyValueFactory<>("buyingprice"));
 
 
        table.getColumns().addAll(nameColumn, amountColumn, EDIColumn,ADDColumn,EMColumn,EDITColumn,PRICEColumn);
        table.getItems().setAll(getpurchase("$"));

        Label searchbyname = new Label("SEARCH BY DATE:");
        searchbyname.setPadding(new Insets(10, 0, 0, 0));
        JFXTextField search = new JFXTextField();
        search.setText("ENTER A DATE");
        search.setLabelFloat(true);
        JFXButton search2 = new JFXButton();
        //search2.setContentDisplay(ContentDisplay.TOP);
        Image image = new Image(getClass().getResourceAsStream("/icons/icons8-search-50.png"));
        //Button button1 = new Button("Accept");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(28);
        imageView.setFitWidth(30);
        search2.setGraphic(imageView);

        search2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    table.getItems().setAll(getpurchase(search.getText()));

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(GeneralManagerHomeController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(GeneralManagerHomeController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        //search2.setPadding(new Insets(10, 0, 0, 0));
        HBox hbox = new HBox(30, searchbyname, search, search2);
        hbox.setPadding(new Insets(20, 0, 0, 20));
        vbox2.getChildren().addAll(table, hbox);
        table.setRowFactory(tv -> {
            TableRow<purchase> row = new TableRow<>();
            row.setOnMouseClicked(e -> {
                if (!row.isEmpty() && e.getButton() == MouseButton.PRIMARY
                        && e.getClickCount() == 2) {

                    purchase clickedRow = row.getItem();
                    ArrayList<String> temp = new ArrayList<>();
                    //for (int i = 0; i < 6; i++) {
                    Collections.addAll(temp, clickedRow.getPurid());
                    Collections.addAll(temp,clickedRow.getSuppid());
                    Collections.addAll(temp, clickedRow.getBookid());
                    Collections.addAll(temp,clickedRow.getDate());
                    Collections.addAll(temp,Integer.toString(clickedRow.getAmount()));
                    Collections.addAll(temp,clickedRow.getEdi());
                    Collections.addAll(temp,clickedRow.getBuyingprice());
                    printRow(temp, "purchase");
                }
            });
            return row;
        });
    }

    @FXML
    private void handleManaging(ActionEvent event) throws ClassNotFoundException, SQLException {
         if (!vbox2.getChildren().isEmpty()) {
            vbox2.getChildren().clear();
        }
        TableView<managing> table = new TableView<>();
        //table = new TableView<>();
        //Name column
        TableColumn<managing, String> nameColumn = new TableColumn<>("MANAGING ID");
        nameColumn.setMinWidth(100);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("manageid"));

        //Price column
        TableColumn<managing, Integer> amountColumn = new TableColumn<>("MANAGER ID");
        amountColumn.setMinWidth(100);
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("managerid"));

        TableColumn<managing, Integer> EDIColumn = new TableColumn<>("STAFF ID");
        EDIColumn.setMinWidth(100);
        EDIColumn.setCellValueFactory(new PropertyValueFactory<>("staffid"));
        
        TableColumn<managing, Integer> ADDColumn = new TableColumn<>("START DATE");
        ADDColumn.setMinWidth(100);
        ADDColumn.setCellValueFactory(new PropertyValueFactory<>("startdate"));

        TableColumn<managing, Integer> EMColumn = new TableColumn<>("END DATE");
        EMColumn.setMinWidth(100);
        EMColumn.setCellValueFactory(new PropertyValueFactory<>("enddate"));
 
        table.getColumns().addAll(nameColumn, amountColumn, EDIColumn,ADDColumn,EMColumn);
        table.getItems().setAll(getmanaging("$"));

        Label searchbyname = new Label("SEARCH BY STAFF ID:");
        searchbyname.setPadding(new Insets(10, 0, 0, 0));
        JFXTextField search = new JFXTextField();
        search.setText("ENTER AN ID");
        search.setLabelFloat(true);
        JFXButton search2 = new JFXButton();
        //search2.setContentDisplay(ContentDisplay.TOP);
        Image image = new Image(getClass().getResourceAsStream("/icons/icons8-search-50.png"));
        //Button button1 = new Button("Accept");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(28);
        imageView.setFitWidth(30);
        search2.setGraphic(imageView);

        search2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    table.getItems().setAll(getmanaging(search.getText()));

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(GeneralManagerHomeController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(GeneralManagerHomeController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        //search2.setPadding(new Insets(10, 0, 0, 0));
        HBox hbox = new HBox(30, searchbyname, search, search2);
        hbox.setPadding(new Insets(20, 0, 0, 20));
        vbox2.getChildren().addAll(table, hbox);
        table.setRowFactory(tv -> {
            TableRow<managing> row = new TableRow<>();
            row.setOnMouseClicked(e -> {
                if (!row.isEmpty() && e.getButton() == MouseButton.PRIMARY
                        && e.getClickCount() == 2) {

                    managing clickedRow = row.getItem();
                    ArrayList<String> temp = new ArrayList<>();
                    Format formatter = new SimpleDateFormat("yyyy-MM-dd");
                    String s = formatter.format(clickedRow.getStartdate());
                    String end=null;
                    if(clickedRow.getEnddate()==null)
                    {
                        end = "null";
                    } else {
                        Format formatter1 = new SimpleDateFormat("yyyy-MM-dd");
                        end = formatter1.format(clickedRow.getEnddate());

                    }
                 
                    //for (int i = 0; i < 6; i++) {
                    Collections.addAll(temp, Integer.toString(clickedRow.getManageid()));
                    Collections.addAll(temp,Integer.toString(clickedRow.getManagerid()));
                    Collections.addAll(temp, Integer.toString(clickedRow.getStaffid()));
                    Collections.addAll(temp,s);
                    Collections.addAll(temp,end);

                    printRow(temp, "managing");
                }
            });
            return row;
        });
    }

    @FXML
    private void handleworkingyear(ActionEvent event) throws ClassNotFoundException, SQLException {
        boolean is=false; 
        try {
            int a = Integer.parseInt(employeeid.getText());
        } catch (NumberFormatException e) {
            is = true;
            employeeid.setText("* ENTER A NUMBER");
            employeeid.setStyle("-fx-text-fill:#E74C3C;-fx-faint-focus-color: transparent; -fx-focus-color:rgba(255,0,0,0.2);");

        }
        if (!is) {
            Class.forName("oracle.jdbc.OracleDriver");
            Connection conn = new DBConnection().getConnection();
          
            if (employeeCombo.getValue().equals("STAFF")) {
                String getDBUSERByUserIdSql = "{call HOW_LONG_STAFF_WORKING(?,?,?)}";
                CallableStatement callableStatement = null;
                callableStatement = conn.prepareCall(getDBUSERByUserIdSql);
                callableStatement.setInt(1, Integer.parseInt(employeeid.getText()));
                //callableStatement.setInt(2, Integer.parseInt(managerid.getText()));
                callableStatement.registerOutParameter(2, java.sql.Types.NUMERIC);
                callableStatement.registerOutParameter(3, java.sql.Types.VARCHAR);
                
                callableStatement.executeUpdate();
                
                if(callableStatement.getString(3).equals("NOT FOUND"))
                {
                    Alert al = new Alert(Alert.AlertType.ERROR);
                    al.setHeaderText(null);
                    al.setContentText("STAFF DOESN'T EXIST!!!");
                    al.showAndWait();
                }
                 else if(callableStatement.getString(3).equals("WORKED")) 
                {
                    howlong.setText(employeeCombo.getValue()+" WORKED "+callableStatement.getInt(2)+" YEARS!");
                }
                else
                {
                     howlong.setText(employeeCombo.getValue()+"IS WORKING "+callableStatement.getInt(2)+" YEARS!");
              
                }

            }
            else {
                String getDBUSERByUserIdSql = "{call HOW_LONG_MANAGER_WORKING(?,?,?)}";
                CallableStatement callableStatement = null;
                callableStatement = conn.prepareCall(getDBUSERByUserIdSql);
                callableStatement.setInt(1, Integer.parseInt(employeeid.getText()));
                //callableStatement.setInt(2, Integer.parseInt(managerid.getText()));
                callableStatement.registerOutParameter(2, java.sql.Types.NUMERIC);
                callableStatement.registerOutParameter(3, java.sql.Types.VARCHAR);
                
                callableStatement.executeUpdate();
                
                if(callableStatement.getString(3).equals("NOT FOUND"))
                {
                    Alert al = new Alert(Alert.AlertType.ERROR);
                    al.setHeaderText(null);
                    al.setContentText("MANAGER DOESN'T EXIST!!!");
                    al.showAndWait();
                }
                else if(callableStatement.getString(3).equals("WORKED")) 
                {
                    howlong.setText(employeeCombo.getValue()+" WORKED "+callableStatement.getInt(2)+" YEARS!");
                }
                else
                {
                     howlong.setText(employeeCombo.getValue()+"IS WORKING "+callableStatement.getInt(2)+" YEARS!");
              
                }

            }
            
        }
    }

    @FXML
    private void handleAddEmp(ActionEvent event) throws SQLException, ClassNotFoundException {

        Class.forName("oracle.jdbc.OracleDriver");
        Connection conn = new DBConnection().getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        boolean is=false; 
        boolean is2=false;
        try {
            int a = Integer.parseInt(salE.getText());
        } catch (NumberFormatException e) {
            is = true;
            salE.setText("* ENTER A NUMBER");
            salE.setStyle("-fx-text-fill:#E74C3C;-fx-faint-focus-color: transparent; -fx-focus-color:rgba(255,0,0,0.2);");

        }
       
        try {
            int a = Integer.parseInt(phoneE.getText());
        } catch (NumberFormatException e) {
            is2 = true;
            phoneE.setText("* ENTER A NUMBER");
            phoneE.setStyle("-fx-text-fill:#E74C3C;-fx-faint-focus-color: transparent; -fx-focus-color:rgba(255,0,0,0.2);");

        }
        if (!is && !is2) {
            if (stafformanC.getValue().equals("STAFF")) {
                String sql = "INSERT INTO STAFFS(STAFF_ID,STF_NATIONALITY,STF_DESIGNATION,STF_NAME,STF_ADDRESS,STF_EMAIL_ID,STF_PHONE_NO,STF_SALARY,STF_COMMISSION_PCT,STF_JOINING_DATE)VALUES(STAFF_ID_SEQ.NEXTVAL,?,?,?,?,?,?,?,?,SYSDATE)";
                pst = conn.prepareStatement(sql);
                pst.setString(1,nationaE.getText());
                pst.setString(2,desiE.getText());
                pst.setString(3,nameE.getText());
                pst.setString(4,addE.getText());
                pst.setString(5,emailE.getText());
                pst.setString(6,phoneE.getText());
                pst.setString(7, salE.getText());
                pst.setString(8, commE.getText());
                pst.executeUpdate();
            }
            if (stafformanC.getValue().equals("MANAGER")) {
                String sql = "INSERT INTO MANAGERS(MANAGER_ID,STF_NATIONALITY,STF_DESIGNATION,MGR_NAME,MGR_ADDRESS,MGR_EMAIL_ID,MGR_PHONE_NO,MGR_SALARY,MGR_COMMISSION_PCT,MGR_JOINING_DATE)VALUES(MANAGER_ID_SEQ.NEXTVAL,?,?,?,?,?,?,?,?,SYSDATE)";
                pst = conn.prepareStatement(sql);
                pst.setString(1,nationaE.getText());
                pst.setString(2,desiE.getText());
                pst.setString(3,nameE.getText());
                pst.setString(4,addE.getText());
                pst.setString(5,emailE.getText());
                pst.setString(6,phoneE.getText());
                pst.setString(7, salE.getText());
                pst.setString(8, commE.getText());
                pst.executeUpdate();
            }
            Alert al = new Alert(Alert.AlertType.CONFIRMATION);
            al.setHeaderText(null);
            al.setContentText("INSERTED SUCCESSFULLY!!!");
            al.showAndWait();
            nameE.clear();
            addE.clear();
            emailE.clear();
            phoneE.clear();
            salE.clear();
            commE.clear();
            desiE.clear();
            nationaE.clear();
        }
        
        
    }


}
