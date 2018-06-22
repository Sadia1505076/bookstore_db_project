package bookstore;

import bookstore.classes.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.effects.JFXDepthManager;
import java.io.IOException;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class StaffhomeController implements Initializable {

    ObservableList<String> eCombo = FXCollections.observableArrayList("ANY PARTICULAR BOOK","ALL BOOKS");

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
    private VBox vbox2;
    String userid = null;
    String who = null;
    @FXML
    private VBox vatVbox;
    @FXML
    private VBox disVbox;
    @FXML
    private AnchorPane DIS;
    @FXML
    private HBox hboxVat;
    @FXML
    private JFXComboBox<String> vatCombo;
    @FXML
    private HBox hVat;
    @FXML
    private HBox hboxDis;
    @FXML
    private JFXComboBox<String> DISCombo;
    @FXML
    private HBox hDis;
    @FXML
    private JFXTextField bookidVat;
    @FXML
    private JFXTextField bookidDis;
    @FXML
    private JFXTextField vatStaff;
    @FXML
    private JFXTextField disStaff;

    void setVariable(String user, String who) {
        userid = user;
        this.who = who;
        System.out.println(userid);
        try {
            System.out.println("userid:" + userid);
            Connection conn = new DBConnection().getConnection();
            String sql = "SELECT STF_NAME,STF_ADDRESS,STF_EMAIL_ID,STF_PHONE_NO,STF_DESIGNATION FROM STAFFS WHERE STAFF_ID=?";
            PreparedStatement pst = null;
            ResultSet rs = null;
            pst = conn.prepareStatement(sql);
            pst.setString(1, userid);
            pst.executeUpdate();
            rs = pst.executeQuery();
            //System.out.println(rs.next());
            while (rs.next()) {
                System.out.println("inside");
                name.setText(rs.getString("STF_NAME"));
                name.setFocusTraversable(true);
                //name.setFont("16px");
                System.out.println("name:" + name.getText());
                designation.setText(rs.getString("STF_DESIGNATION"));

                address.setText(rs.getString("STF_ADDRESS"));
                emailid.setText(rs.getString("STF_EMAIL_ID"));
                phoneno.setText(rs.getString("STF_PHONE_NO"));

            }

        } catch (SQLException ex) {
            Logger.getLogger(EmployeeHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        JFXDepthManager.setDepth(vatVbox, 30);
        JFXDepthManager.setDepth(disVbox, 30);

        
        vatCombo.setValue("ANY PARTICULAR BOOK");
        vatCombo.setItems(eCombo);
        vatCombo.setValue("ALL BOOKS");
        vatCombo.setItems(eCombo);

        DISCombo.setValue("ANY PARTICULAR BOOK");
        DISCombo.setItems(eCombo);
        DISCombo.setValue("ALL BOOKS");
        DISCombo.setItems(eCombo);
        
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

    @FXML
    private void handleeditPro(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("editpro.fxml"));
        Parent root = loader.load();
        EditproController controller = loader.getController();
        controller.settext("employee", name.getText(), designation.getText(), emailid.getText(), address.getText(), phoneno.getText(), userid);

        Scene scene = new Scene(root);
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("insert book");
        stage.setScene(scene);
        stage.show();
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

        table.getColumns().addAll(IdColumn, nameColumn, quantityColumn, priceColumn, discountColumn,vatColumn, ediColumn);
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
    private void buyBook(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("bookstore.fxml"));
        Parent root = loader.load();
        //EditproController controller = loader.getController();
        //controller.settext("employee", name.getText(), designation.getText(), emailid.getText(), address.getText(), phoneno.getText(), userid);

        Scene scene = new Scene(root);
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("buy book");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void orderBook(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("orderingBook.fxml"));
        Parent root = loader.load();
        //EditproController controller = loader.getController();
        //controller.settext("employee", name.getText(), designation.getText(), emailid.getText(), address.getText(), phoneno.getText(), userid);

        Scene scene = new Scene(root);
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("insert book");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void orderList(ActionEvent event) throws ClassNotFoundException, SQLException {
        if (!vbox2.getChildren().isEmpty()) {
            vbox2.getChildren().clear();
        }
        TableView<order> table = new TableView<>();
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

        table.getColumns().addAll(nameColumn, amountColumn, custidColumn, dateColumn);
        table.getItems().setAll(getords());

        vbox2.getChildren().addAll(table);
        table.setRowFactory(tv -> {
            TableRow<order> row = new TableRow<>();
            row.setOnMouseClicked(e -> {
                if (!row.isEmpty() && e.getButton() == MouseButton.PRIMARY
                        && e.getClickCount() == 2) {

                    order clickedRow = row.getItem();
                    ArrayList<String> temp = new ArrayList<>();
                    //for (int i = 0; i < 6; i++) {
                    Collections.addAll(temp, clickedRow.getBookname());
                    Collections.addAll(temp, Integer.toString(clickedRow.getAmount()));
                    Collections.addAll(temp, clickedRow.getCustomerid());
                    Collections.addAll(temp, clickedRow.getDate());
                    //Collections.addAll(temp,clickedRow.getCustphone());

                    printRow(temp, "order");
                }
            });
            return row;
        });
    }

    @FXML
    private void handleVat(ActionEvent event) throws ClassNotFoundException, SQLException {
        if (vatCombo.getValue().equals("ANY PARTICULAR BOOK")) {
            boolean is = false;
            //bookidVat.setEditable(true);
            //bookidVat.sete
            Class.forName("oracle.jdbc.OracleDriver");
            Connection conn = new DBConnection().getConnection();
            String sql = null;
            ResultSet rs = null;
            //String nlikethis = "%" + name.toLowerCase() + "%";
            PreparedStatement pst = null;
            Statement s = conn.createStatement();

            sql = "SELECT BOOK_ID FROM BOOK WHERE BOOK_ID=?";
            pst = conn.prepareStatement(sql);
            // pst.setString(1,vatStaff.getText());
            pst.setString(1, bookidVat.getText());
            pst.executeUpdate();
            rs = pst.executeQuery();
            if (rs.next()) {

                try {
                    int a = Integer.parseInt(vatStaff.getText());
                } catch (NumberFormatException e) {
                    is = true;
                    vatStaff.setText("* ENTER A NUMBER");
                    vatStaff.setStyle("-fx-text-fill:#E74C3C;-fx-faint-focus-color: transparent; -fx-focus-color:rgba(255,0,0,0.2);");

                }
                if (!is) {
                    sql = "UPDATE BOOK SET VAT=? WHERE BOOK_ID=? ";
                    pst = conn.prepareStatement(sql);
                    pst.setString(1, vatStaff.getText());
                    pst.setString(2, bookidVat.getText());
                    pst.executeUpdate();

                    sql = "COMMIT";
                    rs = s.executeQuery(sql);
                    
                    Alert al = new Alert(Alert.AlertType.INFORMATION);
                    al.setHeaderText(null);
                    al.setContentText("CHANGED SUCCESSFULLY!");
                    al.showAndWait();
                    bookidVat.clear();
                    vatStaff.clear();

                } else {
                    Alert al = new Alert(Alert.AlertType.ERROR);
                    al.setHeaderText(null);
                    al.setContentText("ENTER A NUMBER!");
                    al.showAndWait();
                }

            } else {
                Alert al = new Alert(Alert.AlertType.ERROR);
                al.setHeaderText(null);
                al.setContentText("BOOK NOT FOUND!");
                al.showAndWait();
            }

        } else {
            boolean is = false;
            //bookidVat.setEditable(true);
            Class.forName("oracle.jdbc.OracleDriver");
            Connection conn = new DBConnection().getConnection();
            String sql = null;
            ResultSet rs = null;
            //String nlikethis = "%" + name.toLowerCase() + "%";
            PreparedStatement pst = null;
            Statement s = conn.createStatement();

            try {
                int a = Integer.parseInt(vatStaff.getText());
            } catch (NumberFormatException e) {
                is = true;
                vatStaff.setText("* ENTER A NUMBER");
                vatStaff.setStyle("-fx-text-fill:#E74C3C;-fx-faint-focus-color: transparent; -fx-focus-color:rgba(255,0,0,0.2);");

            }
            if (!is) {
                sql = "UPDATE BOOK SET VAT=?";
                pst = conn.prepareStatement(sql);
                pst.setString(1, vatStaff.getText());
               // pst.setString(2, bookidVat.getText());
                pst.executeUpdate();

                sql = "COMMIT";
                rs = s.executeQuery(sql);
                 Alert al = new Alert(Alert.AlertType.INFORMATION);
                    al.setHeaderText(null);
                    al.setContentText("CHANGED SUCCESSFULLY!");
                    al.showAndWait();
                    bookidVat.clear();
                    vatStaff.clear();

            } else {
                Alert al = new Alert(Alert.AlertType.ERROR);
                al.setHeaderText(null);
                al.setContentText("ENTER A NUMBER!");
                al.showAndWait();
            }

        }
    }

    @FXML
    private void handleDis(ActionEvent event) throws ClassNotFoundException, SQLException {
        if (DISCombo.getValue().equals("ANY PARTICULAR BOOK")) {
            boolean is = false;
            //bookidVat.setEditable(true);
            //bookidVat.sete
            Class.forName("oracle.jdbc.OracleDriver");
            Connection conn = new DBConnection().getConnection();
            String sql = null;
            ResultSet rs = null;
            //String nlikethis = "%" + name.toLowerCase() + "%";
            PreparedStatement pst = null;
            Statement s = conn.createStatement();

            sql = "SELECT BOOK_ID FROM BOOK WHERE BOOK_ID=?";
            pst = conn.prepareStatement(sql);
            // pst.setString(1,vatStaff.getText());
            pst.setString(1,bookidDis.getText());
            pst.executeUpdate();
            rs = pst.executeQuery();
            if (rs.next()) {

                try {
                    int a = Integer.parseInt(disStaff.getText());
                } catch (NumberFormatException e) {
                    is = true;
                    disStaff.setText("* ENTER A NUMBER");
                    disStaff.setStyle("-fx-text-fill:#E74C3C;-fx-faint-focus-color: transparent; -fx-focus-color:rgba(255,0,0,0.2);");

                }
                if (!is) {
                    sql = "UPDATE BOOK SET DISCOUNT=? WHERE BOOK_ID=? ";
                    pst = conn.prepareStatement(sql);
                    pst.setString(1, disStaff.getText());
                    pst.setString(2, bookidDis.getText());
                    pst.executeUpdate();

                    sql = "COMMIT";
                    rs = s.executeQuery(sql);
                    
                    Alert al = new Alert(Alert.AlertType.INFORMATION);
                    al.setHeaderText(null);
                    al.setContentText("CHANGED SUCCESSFULLY!");
                    al.showAndWait();
                    bookidDis.clear();
                   disStaff.clear();

                } else {
                    Alert al = new Alert(Alert.AlertType.ERROR);
                    al.setHeaderText(null);
                    al.setContentText("ENTER A NUMBER!");
                    al.showAndWait();
                }

            } else {
                Alert al = new Alert(Alert.AlertType.ERROR);
                al.setHeaderText(null);
                al.setContentText("BOOK NOT FOUND!");
                al.showAndWait();
            }

        } else {
            boolean is = false;
            //bookidVat.setEditable(true);
            Class.forName("oracle.jdbc.OracleDriver");
            Connection conn = new DBConnection().getConnection();
            String sql = null;
            ResultSet rs = null;
            //String nlikethis = "%" + name.toLowerCase() + "%";
            PreparedStatement pst = null;
            Statement s = conn.createStatement();

            try {
                int a = Integer.parseInt(disStaff.getText());
            } catch (NumberFormatException e) {
                is = true;
                disStaff.setText("* ENTER A NUMBER");
                disStaff.setStyle("-fx-text-fill:#E74C3C;-fx-faint-focus-color: transparent; -fx-focus-color:rgba(255,0,0,0.2);");

            }
            if (!is) {
                sql = "UPDATE BOOK SET DISCOUNT=?";
                pst = conn.prepareStatement(sql);
                pst.setString(1, disStaff.getText());
               // pst.setString(2, bookidVat.getText());
                pst.executeUpdate();

                sql = "COMMIT";
                rs = s.executeQuery(sql);
                 Alert al = new Alert(Alert.AlertType.INFORMATION);
                    al.setHeaderText(null);
                    al.setContentText("CHANGED SUCCESSFULLY!");
                    al.showAndWait();
                    bookidDis.clear();
                    disStaff.clear();

            } else {
                Alert al = new Alert(Alert.AlertType.ERROR);
                al.setHeaderText(null);
                al.setContentText("ENTER A NUMBER!");
                al.showAndWait();
            }

        }
    }

    @FXML
    private void addbookidV(ActionEvent event) {
        if(vatCombo.getValue().equals("ANY PARTICULAR BOOK"))
        {
            bookidVat.setEditable(true);
        }
        else
        {
            bookidVat.setEditable(false);
        }
    }

    @FXML
    private void addbookidD(ActionEvent event) {
        if(DISCombo.getValue().equals("ANY PARTICULAR BOOK"))
        {
            bookidDis.setEditable(true);
        }
        else
        {
            bookidDis.setEditable(false);
        }
    }

    @FXML
    private void orderBookById(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("orderByBookId.fxml"));
        Parent root = loader.load();
        //EditproController controller = loader.getController();
        //controller.settext("employee", name.getText(), designation.getText(), emailid.getText(), address.getText(), phoneno.getText(), userid);

        Scene scene = new Scene(root);
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("insert book");
        stage.setScene(scene);
        stage.show();
    }

}
