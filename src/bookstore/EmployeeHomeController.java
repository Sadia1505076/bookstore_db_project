package bookstore;

import bookstore.classes.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.effects.JFXDepthManager;
import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
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

public class EmployeeHomeController implements Initializable {

    ObservableList<bookrequired> booksrequtemp = FXCollections.observableArrayList();

    @FXML
    private HBox hbox;
    @FXML
    private JFXButton booklist;
    @FXML
    private JFXButton editPro;
    @FXML
    private VBox vbox2;
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
    String userid = null;
    String who = null;

    
    @FXML
    private JFXTextField EID;
    @FXML
    private JFXTextField newdesi;
    @FXML
    private JFXTextField EID1;
    @FXML
    private VBox desi;
    @FXML
    private VBox fire;
    @FXML
    private JFXButton employee;
    @FXML
    private VBox howlongv1;
    @FXML
    private JFXTextField employeeid1;
    @FXML
    private Label howlong1;

    void setVariable(String user, String who) {
        userid = user;
        this.who = who;
        System.out.println(userid);
        try {
            System.out.println("userid:" + userid);
            Connection conn = new DBConnection().getConnection();
            String sql = "SELECT MGR_NAME,MGR_ADDRESS,MGR_EMAIL_ID,MGR_PHONE_NO,MGR_DESIGNATION FROM MANAGERS WHERE MANAGER_ID=?";
            PreparedStatement pst = null;
            ResultSet rs = null;
            pst = conn.prepareStatement(sql);
            pst.setString(1, userid);
            pst.executeUpdate();
            rs = pst.executeQuery();
            //System.out.println(rs.next());
            while (rs.next()) {
                System.out.println("inside");
                name.setText(rs.getString("MGR_NAME"));
                name.setFocusTraversable(true);
                //name.setFont("16px");
                System.out.println("name:" + name.getText());
                designation.setText(rs.getString("MGR_DESIGNATION"));

                address.setText(rs.getString("MGR_ADDRESS"));
                emailid.setText(rs.getString("MGR_EMAIL_ID"));
                phoneno.setText(rs.getString("MGR_PHONE_NO"));

            }

        } catch (SQLException ex) {
            Logger.getLogger(EmployeeHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
         JFXDepthManager.setDepth(fire, 30);
         JFXDepthManager.setDepth(desi, 30);
         JFXDepthManager.setDepth(howlongv1, 30);
       
    }

    void printRow(ArrayList<String> temp, String what) {
        System.out.println("hi");
        Parent root;
        Stage stage = null;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("showingDetail.fxml"));
            root = loader.load();
            ShowingDetailController controller = loader.getController();
            controller.settingvariable(temp, what);
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

    public ObservableList<customer> getcust(String name) throws ClassNotFoundException, SQLException {
        ObservableList<customer> custs = FXCollections.observableArrayList();
        Class.forName("oracle.jdbc.OracleDriver");
        Connection conn = new DBConnection().getConnection();
        if (name.equals("$")) {
            String sql = "SELECT * FROM CUSTOMER";
            ResultSet rs = null;
            Statement s = conn.createStatement();

            rs = s.executeQuery(sql);
            while (rs.next()) {
                custs.add(new customer(rs.getString("CUSTOMER_ID"), rs.getString("NAME"), rs.getString("EMAIL_ID"), rs.getString("PHONE_NO"), rs.getString("ADDRESS")));
            }
        } else {
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

    public ObservableList<book> getProduct(String name) throws ClassNotFoundException, SQLException {
        ObservableList<book> books = FXCollections.observableArrayList();
        Class.forName("oracle.jdbc.OracleDriver");
        Connection conn = new DBConnection().getConnection();

        if (name.equals("$")) {
            String sql = "SELECT B.BOOK_ID,B.NAME,P.AMOUNT,B.SELLING_PRICE,B.DISCOUNT,B.VAT,P.EDITION FROM BOOK B,BOOK_WITH_EDITION P WHERE B.BOOK_ID=P.BOOK_ID";
            ResultSet rs = null;
            Statement s = conn.createStatement();

            rs = s.executeQuery(sql);
            while (rs.next()) {
                books.add(new book(rs.getString("BOOK_ID"), rs.getString("NAME"), rs.getInt("AMOUNT"), rs.getInt("SELLING_PRICE"), rs.getInt("DISCOUNT"),rs.getInt("VAT") ,rs.getString("EDITION")));
            }
        } else {

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

    public ObservableList<employee> getemps(String name) throws ClassNotFoundException, SQLException {
        ObservableList<employee> emps = FXCollections.observableArrayList();
        Class.forName("oracle.jdbc.OracleDriver");
        Connection conn = new DBConnection().getConnection();

        if (name.equals("$")) {
            String sql = "SELECT S.STAFF_ID, S.STF_NAME,S.STF_DESIGNATION,S.STF_ADDRESS,S.STF_EMAIL_ID, S.STF_PHONE_NO, S.STF_SALARY,S.STF_COMMISSION_PCT,S.STF_JOINING_DATE,S.STF_END_DATE FROM MANAGER_STAFF_RELATION M, STAFFS S WHERE M.MANAGER_ID = ? AND M.MANAGING_END_DATE IS NULL AND M.STAFF_ID = S.STAFF_ID";
            PreparedStatement pst = null;
            ResultSet rs = null;
            pst = conn.prepareStatement(sql);
            pst.setString(1, userid);
            pst.executeUpdate();
            rs = pst.executeQuery();
            while (rs.next()) {
                emps.add(new employee(rs.getString("STAFF_ID"), rs.getString("STF_NAME"), rs.getString("STF_DESIGNATION"), rs.getString("STF_ADDRESS"), rs.getString("STF_EMAIL_ID"), rs.getString("STF_PHONE_NO"), Integer.toString(rs.getInt("STF_SALARY")), Integer.toString(rs.getInt("STF_COMMISSION_PCT")), rs.getDate("STF_JOINING_DATE"), rs.getDate("STF_END_DATE")));
            }
        } else {
            String nlikethis = "%" + name.toLowerCase() + "%";
            String sql = "SELECT S.STAFF_ID, S.STF_NAME,S.STF_DESIGNATION,S.STF_ADDRESS,S.STF_EMAIL_ID, S.STF_PHONE_NO, S.STF_SALARY,S.STF_COMMISSION_PCT,S.STF_JOINING_DATE,S.STF_END_DATE FROM MANAGER_STAFF_RELATION M, STAFFS S WHERE M.MANAGER_ID = ? AND M.MANAGING_END_DATE IS NULL AND M.STAFF_ID = S.STAFF_ID AND LOWER(S.STF_NAME) LIKE ?";
            PreparedStatement pst = null;
            ResultSet rs = null;
            pst = conn.prepareStatement(sql);
            pst.setString(1, userid);
            pst.setString(2, nlikethis);
            pst.executeUpdate();
            rs = pst.executeQuery();
            while (rs.next()) {
                emps.add(new employee(rs.getString("STAFF_ID"), rs.getString("STF_NAME"), rs.getString("STF_DESIGNATION"), rs.getString("STF_ADDRESS"), rs.getString("STF_EMAIL_ID"), rs.getString("STF_PHONE_NO"), Integer.toString(rs.getInt("STF_SALARY")), Integer.toString(rs.getInt("STF_COMMISSION_PCT")), rs.getDate("STF_JOINING_DATE"), rs.getDate("STF_END_DATE")));
            }
        }

        return emps;
    }

    public ObservableList<bookrequired> getbooksreq(String id) throws ClassNotFoundException, SQLException {
        ObservableList<bookrequired> booksrequ = FXCollections.observableArrayList();

        Class.forName("oracle.jdbc.OracleDriver");
        Connection conn = new DBConnection().getConnection();

        if (id.equals("$")) {
            String sql = "SELECT * FROM BOOK_REQUIREMENT";
            ResultSet rs = null;
            Statement s = conn.createStatement();

            rs = s.executeQuery(sql);
            while (rs.next()) {
                booksrequ.add(new bookrequired(rs.getString("BOOK_ID"), rs.getInt("AMOUNT_REQUIRED"), rs.getString("EDITION_REQUIRED")));

            }
        } else {
            String sql = "SELECT BOOK_ID,AMOUNT_REQUIRED,EDITION_REQUIRED FROM BOOK_REQUIREMENT WHERE LOWER(BOOK_ID) LIKE ?";
            String nlikethis = "%" + id.toLowerCase() + "%";
            ResultSet rs = null;
            PreparedStatement pst = null;
            pst = conn.prepareStatement(sql);
            pst.setString(1, nlikethis);
            pst.executeUpdate();
            rs = pst.executeQuery();
            while (rs.next()) {
                booksrequ.add(new bookrequired(rs.getString("BOOK_ID"), rs.getInt("AMOUNT_REQUIRED"), rs.getString("EDITION_REQUIRED")));

            }
        }

        return booksrequ;
    }

    @FXML
    private void handleBookList(ActionEvent event) throws ClassNotFoundException, SQLException {
        if (!vbox2.getChildren().isEmpty()) {
            vbox2.getChildren().clear();
        }
        TableView<book> table = new TableView<>();
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
        TableColumn<book, String> ediColumn = new TableColumn<>("EDITION");
        ediColumn.setMinWidth(100);
        ediColumn.setCellValueFactory(new PropertyValueFactory<>("edition"));

        table.getColumns().addAll(IdColumn, nameColumn, quantityColumn, priceColumn, discountColumn, vatColumn,ediColumn);
        table.getItems().setAll(getProduct("$"));

        Label searchbyname = new Label("SEARCH BY NAME:");
        searchbyname.setPadding(new Insets(10, 0, 0, 0));
        JFXTextField search = new JFXTextField();
        search.setLabelFloat(true);
        search.setText("ENTER A NAME");

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
                    table.getItems().setAll(getProduct(search.getText()));

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
            TableRow<book> row = new TableRow<>();
            row.setOnMouseClicked(e -> {
                if (!row.isEmpty() && e.getButton() == MouseButton.PRIMARY
                        && e.getClickCount() == 2) {

                    book clickedRow = row.getItem();
                    ArrayList<String> temp = new ArrayList<>();
                    //for (int i = 0; i < 6; i++) {
                    Collections.addAll(temp, clickedRow.getBookid());
                    Collections.addAll(temp, clickedRow.getName());
                    Collections.addAll(temp, Integer.toString(clickedRow.getAmount()));
                    Collections.addAll(temp, clickedRow.getEdition());
                    Collections.addAll(temp, Integer.toString(clickedRow.getSellingprice()));
                    Collections.addAll(temp, Integer.toString(clickedRow.getDiscount()));
                    Collections.addAll(temp, Integer.toString(clickedRow.getVat()));
                    //Integer.toString(clickedRow.getAmount()),clickedRow.);
                    //}
                    printRow(temp, "book");
                }
            });
            return row;
        });
    }

    @FXML
    private void handleeditPro(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("editpro.fxml"));
        Parent root = loader.load();
        EditproController controller = loader.getController();
        controller.settext("manager", name.getText(), designation.getText(), emailid.getText(), address.getText(), phoneno.getText(), userid);

        Scene scene = new Scene(root);
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("insert book");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleEmployee(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (!vbox2.getChildren().isEmpty()) {
            vbox2.getChildren().clear();
        }
        TableView<employee> table = new TableView<>();
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

        table.getColumns().addAll(IdColumn, nameColumn, desiColumn, emailColumn, phoneColumn, addColumn, salColumn, commColumn, SDColumn, EDColumn);
        table.getItems().setAll(getemps("$"));

        Label searchbyname = new Label("SEARCH BY NAME:");
        searchbyname.setPadding(new Insets(10, 0, 0, 0));
        JFXTextField search = new JFXTextField();
        search.setLabelFloat(true);
        search.setText("ENTER A NAME");

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
                    table.getItems().setAll(getemps(search.getText()));

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
            TableRow<employee> row = new TableRow<>();
            row.setOnMouseClicked(e -> {
                if (!row.isEmpty() && e.getButton() == MouseButton.PRIMARY
                        && e.getClickCount() == 2) {

                    employee clickedRow = row.getItem();
                    ArrayList<String> temp = new ArrayList<>();

                    Format formatter = new SimpleDateFormat("yyyy-MM-dd");
                    String s = formatter.format(clickedRow.getSdate());
                    String end = null;
                    if (clickedRow.getEdate() == null) {
                        end = "null";
                    } else {
                        Format formatter1 = new SimpleDateFormat("yyyy-MM-dd");
                        end = formatter1.format(clickedRow.getEdate());

                    }

                    //for (int i = 0; i < 6; i++) {
                    Collections.addAll(temp, clickedRow.getEmpid());
                    Collections.addAll(temp, clickedRow.getName());
                    Collections.addAll(temp, clickedRow.getDesignation());
                    Collections.addAll(temp, clickedRow.getAddress());
                    Collections.addAll(temp, clickedRow.getEmailid());
                    Collections.addAll(temp, clickedRow.getPhoneno());
                    Collections.addAll(temp, clickedRow.getSalary());
                    Collections.addAll(temp, clickedRow.getCommissionpct());
                    //Integer.toString(clickedRow.getAmount()),clickedRow.);
                    Collections.addAll(temp, s);
                    Collections.addAll(temp, end);
                    //}
                    printRow(temp, "employee");
                }
            });
            return row;
        });
    }

    @FXML
    private void custmerList(ActionEvent event) throws ClassNotFoundException, SQLException {
        if (!vbox2.getChildren().isEmpty()) {
            vbox2.getChildren().clear();
        }
        TableView<customer> table = new TableView<>();
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

        table.getColumns().addAll(IdColumn, nameColumn, emailColumn, phoneColumn, addColumn);
        table.getItems().setAll(getcust("$"));

        Label searchbyname = new Label("SEARCH BY NAME:");
        searchbyname.setPadding(new Insets(10, 0, 0, 0));
        JFXTextField search = new JFXTextField();
        search.setLabelFloat(true);
        search.setText("ENTER A NAME");

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
                    table.getItems().setAll(getcust(search.getText()));

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
            TableRow<customer> row = new TableRow<>();
            row.setOnMouseClicked(e -> {
                if (!row.isEmpty() && e.getButton() == MouseButton.PRIMARY
                        && e.getClickCount() == 2) {

                    customer clickedRow = row.getItem();
                    ArrayList<String> temp = new ArrayList<>();
                    //for (int i = 0; i < 6; i++) {
                    Collections.addAll(temp, clickedRow.getCustid());
                    Collections.addAll(temp, clickedRow.getCustname());
                    Collections.addAll(temp, clickedRow.getCustadd());
                    Collections.addAll(temp, clickedRow.getCustemail());
                    Collections.addAll(temp, clickedRow.getCustphone());

                    printRow(temp, "customer");
                }
            });
            return row;
        });

    }

    @FXML
    private void bookrequired(ActionEvent event) throws ClassNotFoundException, SQLException {

        if (!vbox2.getChildren().isEmpty()) {
            vbox2.getChildren().clear();
        }
        TableView<bookrequired> table = new TableView<>();
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

        table.getColumns().addAll(nameColumn, amountColumn, EDIColumn);
        booksrequtemp = getbooksreq("$");
        table.getItems().setAll(getbooksreq("$"));

        Label searchbyname = new Label("SEARCH BY BOOKID:");
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
        JFXButton sendrequ = new JFXButton();
        sendrequ.setText("SEND REQUEST");
        sendrequ.getStyleClass().clear();
        sendrequ.getStyleClass().add("button_salary");
        sendrequ.setPadding(new Insets(10, 10, 10, 10));

        search2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    table.getItems().setAll(getbooksreq(search.getText()));
                    booksrequtemp = getbooksreq(search.getText());

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(GeneralManagerHomeController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(GeneralManagerHomeController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        sendrequ.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (booksrequtemp.size() == 0) {
                    Alert al = new Alert(Alert.AlertType.INFORMATION);
                    al.setHeaderText(null);
                    al.setContentText("THERE IS NO BOOK TO BUY!");
                    al.showAndWait();
                } else {
                    Parent root;
                    Stage stage = null;
                    try {
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("interSendrequ.fxml"));
                        root = loader.load();
                        InterSendrequController controller = loader.getController();
                        controller.setVariable(userid, who, booksrequtemp);

                        Scene scene = new Scene(root);
                        stage = new Stage(StageStyle.DECORATED);
                        //stage.setTitle("RECEIPT");
                        stage.setScene(scene);
                        stage.show();

                    } catch (IOException ex) {
                        System.out.println(ex.toString());
                    }

                }

            }
        });

        //search2.setPadding(new Insets(10, 0, 0, 0));
        HBox hbox = new HBox(30, searchbyname, search, search2, sendrequ);
        hbox.setPadding(new Insets(20, 0, 0, 20));
        vbox2.getChildren().addAll(table, hbox);
        table.setRowFactory(tv -> {
            TableRow<bookrequired> row = new TableRow<>();
            row.setOnMouseClicked(e -> {
                if (!row.isEmpty() && e.getButton() == MouseButton.PRIMARY
                        && e.getClickCount() == 2) {

                    bookrequired clickedRow = row.getItem();
                    ArrayList<String> temp = new ArrayList<>();
                    //for (int i = 0; i < 6; i++) {
                    Collections.addAll(temp, clickedRow.getBookid());
                    Collections.addAll(temp, Integer.toString(clickedRow.getAmount()));
                    Collections.addAll(temp, clickedRow.getEdition());
//                    Collections.addAll(temp,clickedRow.getDate());
                    //Collections.addAll(temp,clickedRow.getCustphone());

                    printRow(temp, "bookrequired");
                }
            });
            return row;
        });
    }

    @FXML
    private void handleworkingyear(ActionEvent event) throws ClassNotFoundException, SQLException {
        boolean is = false;
        try {
            int a = Integer.parseInt(employeeid1.getText());
        } catch (NumberFormatException e) {
            is = true;
            employeeid1.setText("* ENTER A NUMBER");
            employeeid1.setStyle("-fx-text-fill:#E74C3C;-fx-faint-focus-color: transparent; -fx-focus-color:rgba(255,0,0,0.2);");

        }
        if (!is) {

            Class.forName("oracle.jdbc.OracleDriver");
            Connection conn = new DBConnection().getConnection();
            String sql = "SELECT STAFF_ID FROM MANAGER_STAFF_RELATION WHERE MANAGER_ID=? AND MANAGING_END_DATE IS NULL AND STAFF_ID=?";
            PreparedStatement pst = null;
            ResultSet rs = null;
            pst = conn.prepareStatement(sql);
            pst.setString(1, userid);
            pst.setString(2, employeeid1.getText());
            pst.executeUpdate();
            rs = pst.executeQuery();
            if (rs.next()) {
                String getDBUSERByUserIdSql = "{call HOW_LONG_STAFF_WORKING(?,?,?)}";
                CallableStatement callableStatement = null;
                callableStatement = conn.prepareCall(getDBUSERByUserIdSql);
                callableStatement.setInt(1, Integer.parseInt(employeeid1.getText()));
                //callableStatement.setInt(2, Integer.parseInt(managerid.getText()));
                callableStatement.registerOutParameter(2, java.sql.Types.NUMERIC);
                callableStatement.registerOutParameter(3, java.sql.Types.VARCHAR);

                callableStatement.executeUpdate();

                if (callableStatement.getString(3).equals("NOT FOUND")) {
                    Alert al = new Alert(Alert.AlertType.ERROR);
                    al.setHeaderText(null);
                    al.setContentText("STAFF DOESN'T EXIST!!!");
                    al.showAndWait();
                } else if (callableStatement.getString(3).equals("WORKED")) {
                    howlong1.setText("STAFF WORKED " + callableStatement.getInt(2) + " YEARS!");
                } else {
                    howlong1.setText("STAFF IS WORKING " + callableStatement.getInt(2) + " YEARS!");

                }

            } else {
                Alert al = new Alert(Alert.AlertType.ERROR);
                al.setHeaderText(null);
                al.setContentText("STAFF ISNT MANAGED BY YOU!!!");
                al.showAndWait();
            }

        }
    }

    @FXML
    private void handleDESI(ActionEvent event) throws ClassNotFoundException, SQLException {
        boolean is = false;
        try {
            int a = Integer.parseInt(EID.getText());
        } catch (NumberFormatException e) {
            is = true;
            EID.setText("* ENTER A NUMBER");
            EID.setStyle("-fx-text-fill:#E74C3C;-fx-faint-focus-color: transparent; -fx-focus-color:rgba(255,0,0,0.2);");

        }
        if (newdesi.getText().isEmpty()) {
            newdesi.setText("* REQUIRED");
            newdesi.setStyle("-fx-text-fill:#E74C3C;-fx-faint-focus-color: transparent; -fx-focus-color:rgba(255,0,0,0.2);");
            is = true;
        }
        if (!is) {
            Class.forName("oracle.jdbc.OracleDriver");
            Connection conn = new DBConnection().getConnection();
            ResultSet rs = null, rs2 = null;
            //String nlikethis = "%" + name.toLowerCase() + "%";
            PreparedStatement pst = null;
            String sql = "SELECT STAFF_ID FROM MANAGER_STAFF_RELATION WHERE MANAGER_ID=? AND MANAGING_END_DATE IS NULL AND STAFF_ID=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, userid);
            pst.setString(2, EID.getText());
            pst.executeUpdate();
            rs = pst.executeQuery();
            if (rs.next()) {

                String getDBUSERByUserIdSql = "{call CHANGE_STAFF_DESIGNATION(?,?)}";
                CallableStatement callableStatement = null;
                callableStatement = conn.prepareCall(getDBUSERByUserIdSql);
                callableStatement.setInt(1, Integer.parseInt(EID.getText()));
                callableStatement.setString(2, newdesi.getText());
                callableStatement.executeUpdate();

                Alert al = new Alert(Alert.AlertType.CONFIRMATION);
                al.setHeaderText(null);
                al.setContentText("CHANGED SUCCESSFULLY!!!");
                al.showAndWait();
                EID.clear();
                newdesi.clear();

            } else {
                Alert al = new Alert(Alert.AlertType.ERROR);
                al.setHeaderText(null);
                al.setContentText("STAFF ISNT MANAGED BY YOU!!!");
                al.showAndWait();
                EID.clear();
                newdesi.clear();

            }
        }
    }

    @FXML
    private void handleFire(ActionEvent event) throws ClassNotFoundException, SQLException {
        boolean is = false;
        try {
            int a = Integer.parseInt(EID1.getText());
        } catch (NumberFormatException e) {
            is = true;
            EID1.setText("* ENTER A NUMBER");
            EID1.setStyle("-fx-text-fill:#E74C3C;-fx-faint-focus-color: transparent; -fx-focus-color:rgba(255,0,0,0.2);");

        }
        if (!is) {
            Class.forName("oracle.jdbc.OracleDriver");
            Connection conn = new DBConnection().getConnection();
            ResultSet rs = null, rs2 = null;
            //String nlikethis = "%" + name.toLowerCase() + "%";
            PreparedStatement pst = null;
           
            String sql = "SELECT STAFF_ID FROM MANAGER_STAFF_RELATION WHERE MANAGER_ID=? AND MANAGING_END_DATE IS NULL AND STAFF_ID=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, userid);
            pst.setString(2, EID1.getText());
            pst.executeUpdate();
            rs = pst.executeQuery();
            if (rs.next()) {

                System.out.println(EID1.getText());
                String getDBUSERByUserIdSql = "{call DELETE_STAFF(?,?)}";
                CallableStatement callableStatement = null;
                callableStatement = conn.prepareCall(getDBUSERByUserIdSql);
                callableStatement.setInt(1, Integer.parseInt(EID1.getText()));
                callableStatement.registerOutParameter(2, java.sql.Types.VARCHAR);
                callableStatement.executeUpdate();
                if (callableStatement.getString(2).equals("DOESNT EXIST")) {
                    Alert al = new Alert(Alert.AlertType.ERROR);
                    al.setHeaderText(null);
                    al.setContentText("STAFF DOESN'T WORK HERE ANYMORE!!!");
                    al.showAndWait();
                } else {
                    Alert al = new Alert(Alert.AlertType.CONFIRMATION);
                    al.setHeaderText(null);
                    al.setContentText("CHANGED SUCCESSFULLY!!!");
                    al.showAndWait();
                    EID.clear();

                }
                // DESIGNATION.clear();

            } else {
                Alert al = new Alert(Alert.AlertType.ERROR);
                al.setHeaderText(null);
                al.setContentText("STAFF ISNT MANAGED BY YOU!!!");
                al.showAndWait();
                EID.clear();
                // DESIGNATION.clear();

            }
        }
    }

}
