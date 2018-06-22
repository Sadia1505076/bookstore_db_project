package bookstore;

import bookstore.classes.book4order;
import bookstore.classes.book_author4order;
import bookstore.classes.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class OrderByBookIdController implements Initializable {

    ArrayList<JFXTextField> customer = new ArrayList<>();
    ArrayList<receipt> receipt = new ArrayList<>();
    book_author4order temp = null;
    ObservableList<book4order> books = FXCollections.observableArrayList();
    ArrayList<JFXTextField> newBook = new ArrayList<>();
    ArrayList<String> bookname=new ArrayList<>();

    @FXML
    private JFXTextField custname;
    @FXML
    private JFXTextField custadd;
    @FXML
    private JFXTextField custemail;
    @FXML
    private JFXTextField custphone;
    @FXML
    private DatePicker orderdate;
    @FXML
    private VBox vboxmain;
    @FXML
    private VBox vboxbookauthor;
    @FXML
    private JFXTextField bookid;
    @FXML
    private JFXButton addbook;
    @FXML
    private JFXButton search;
    @FXML
    private VBox vboxtable;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    void settingreceipt() throws SQLException
    {
        String text = "";
        int flag =-1;
        //int flag1 = 0, flaf2 = 0;

        for (book4order rec : books) {
            if(rec.getSelect().isSelected())
            {
                flag=0;
            }
            if(rec.getSelect().isSelected() && rec.getAmountt().getText().isEmpty())
            {
                text="GIVE A QUANTITY OF "+ rec.getName();
                text=text+'\n';
                flag=1;
            } 
            else if (rec.getSelect().isSelected() && Integer.parseInt(rec.getAmountt().getText()) > rec.getAmount()) {
                text = text + "ORDERED " + rec.getAmountt().getText() + " '" + rec.getName() + "' OUT OF " + rec.getAmount() + " '" + rec.getName() + "'.";
                flag = 1;
                text = text + '\n';
            }
            
        }
        if (custname.getText().isEmpty()) {
            custname.setText("* REQUIRED");
            custname.setStyle("-fx-text-fill:#E74C3C;-fx-faint-focus-color: transparent; -fx-focus-color:rgba(255,0,0,0.2); ");

        }
        if (custphone.getText().isEmpty()) {
            custphone.setText("* REQUIRED");
            custphone.setStyle("-fx-text-fill:#E74C3C;-fx-faint-focus-color: transparent; -fx-focus-color:rgba(255,0,0,0.2); ");

        }
        try {
            int a = Integer.parseInt(custphone.getText());
        } catch (NumberFormatException e) {
            //is = true;
            custphone.setText("* ENTER A PHONE NUMBER");
            custphone.setStyle("-fx-text-fill:#E74C3C;-fx-faint-focus-color: transparent; -fx-focus-color:rgba(255,0,0,0.2);");

        }
        if (flag == 1) {
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setHeaderText(null);
            al.setContentText("NOT ENOUGH ITEMS IN THE INVENTORY OR DIDNT GIVE A QUANTITY!!!!");
            TextArea error = new TextArea(text);
            al.getDialogPane().setExpandableContent(error);
            al.showAndWait();
        } 
        else if(flag==-1)
        {
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setHeaderText(null);
            al.setContentText("PLEAZE SELECT ONE!");
            //TextArea error = new TextArea(text);
            //al.getDialogPane().setExpandableContent(error);
            al.showAndWait();
        }
        else {
            //if()
            Collections.addAll(customer, custname, custadd, custemail, custphone);
            int i=0;
            for (book4order rec : books) {
                if (rec.getSelect().isSelected()) {

                    Collections.addAll(receipt, new receipt(rec.getName(), rec.getAmountt().getText(), Integer.toString(rec.getSellingprice()), Integer.toString(rec.getDiscount()), bookname.get(i), rec.getVat(),rec.getEdition()));
                    i++;
                }
            }

            Parent root;
            Stage stage = null;
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("intermidiateOrder.fxml"));
                root = loader.load();
                IntermidiateOrderController controller = loader.getController();
                controller.settingvariable(customer, receipt, orderdate);
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
    public ObservableList<book4order> getProduct() throws ClassNotFoundException {

        try {
            ListView<String> author = new ListView<>();
            ObservableList items = null;
            Class.forName("oracle.jdbc.OracleDriver");
            Connection conn = new DBConnection().getConnection();
            PreparedStatement pst = null;
            ResultSet rs = null;
            ResultSet rs2 = null;
            String sql = null;
            sql = "SELECT A.BOOK_ID,C.NAME FROM AUTHOR_BOOK_RELATION A,ID_PER_BOOK I,AUTHOR C WHERE I.BOOK_ID=A.BOOK_ID AND A.AUTHOR_ID=C.AUTHOR_ID AND I.BOOK_ID_PER_BOOK=? ";
            pst = conn.prepareStatement(sql);
            pst.setString(1, bookid.getText());
            System.out.println(bookid.getText());
            pst.executeUpdate();
            rs = pst.executeQuery();
            while (rs.next()) {
                System.out.println("in");
                items = FXCollections.observableArrayList(rs.getString("NAME"));
                Collections.addAll(bookname,rs.getString("BOOK_ID"));
            }

            author.setEditable(true);
            author.setCellFactory(TextFieldListCell.forListView());
            author = new ListView<>(items);

            author.setPrefHeight(items.size() * 36 + 2);
            //System.out.println(bookid);

            sql = "SELECT I.BOOK_ID_PER_BOOK,B.NAME,B.SELLING_PRICE,B.DISCOUNT,B.VAT FROM BOOK B,ID_PER_BOOK I WHERE B.BOOK_ID=I.BOOK_ID AND I.BOOK_ID_PER_BOOK=?";
            String sql2 = "SELECT E.AMOUNT,E.EDITION FROM BOOK_WITH_EDITION E,ID_PER_BOOK I WHERE E.BOOK_ID=I.BOOK_ID AND I.BOOK_ID_PER_BOOK=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, bookid.getText());
            pst.executeUpdate();
            rs = pst.executeQuery();
            //System.out.println(rs.next());
            pst = conn.prepareStatement(sql2);
            pst.setString(1, bookid.getText());
            pst.executeUpdate();
            rs2 = pst.executeQuery();

            book4order temp = null;
            while (rs.next()) {
                if(rs2.next()) {
                    System.out.println("in 2");
                    System.out.println(rs.getString("BOOK_ID_PER_BOOK"));
                    System.out.println(rs.getString("NAME"));
                    System.out.println(rs2.getInt("AMOUNT"));
                    System.out.println(rs.getInt("SELLING_PRICE"));
                    System.out.println(rs.getString("VAT"));
                    System.out.println(rs.getInt("DISCOUNT"));
                    System.out.println(rs2.getString("EDITION"));
                    temp = new book4order(rs.getString("BOOK_ID_PER_BOOK"), rs.getString("NAME"), rs2.getInt("AMOUNT"), rs.getInt("SELLING_PRICE"), rs.getInt("DISCOUNT"), rs2.getString("EDITION"), "", rs.getString("VAT"));
                    temp.setAuthor(author);
                    temp.getAmountt().setText(Integer.toString(1));
                    books.add(temp);
                }

            }

            for (int i = 0; i < newBook.size(); i++) {
                ListView<String> authormore = new ListView<>();
                ObservableList itemsmore = null;
            
                sql = "SELECT A.BOOK_ID,C.NAME FROM AUTHOR_BOOK_RELATION A,ID_PER_BOOK I,AUTHOR C WHERE I.BOOK_ID=A.BOOK_ID AND A.AUTHOR_ID=C.AUTHOR_ID AND I.BOOK_ID_PER_BOOK=? ";
                pst = conn.prepareStatement(sql);
                pst.setString(1, newBook.get(i).getText());
                pst.executeUpdate();
                rs = pst.executeQuery();
                while (rs.next()) {
                    items = FXCollections.observableArrayList(rs.getString("NAME"));
                    Collections.addAll(bookname,rs.getString("BOOK_ID"));
                }

                author.setEditable(true);
                author.setCellFactory(TextFieldListCell.forListView());
                author = new ListView<>(items);

                author.setPrefHeight(items.size() * 36 + 2);
                //System.out.println(bookid);

                sql = "SELECT I.BOOK_ID_PER_BOOK,B.NAME,B.SELLING_PRICE,B.DISCOUNT,B.VAT FROM BOOK B,ID_PER_BOOK I WHERE B.BOOK_ID=I.BOOK_ID AND I.BOOK_ID_PER_BOOK=?";
                sql2 = "SELECT E.AMOUNT,E.EDITION FROM BOOK_WITH_EDITION E,ID_PER_BOOK I WHERE E.BOOK_ID=I.BOOK_ID AND I.BOOK_ID_PER_BOOK=?";
                pst = conn.prepareStatement(sql);
                pst.setString(1,newBook.get(i).getText());
                pst.executeUpdate();
                rs = pst.executeQuery();
                //System.out.println(rs.next());
                pst = conn.prepareStatement(sql2);
                pst.setString(1, newBook.get(i).getText());
                pst.executeUpdate();
                rs2 = pst.executeQuery();

                book4order temp1 = null;
                while (rs.next()) {
                    if (rs2.next()) {
                        temp1 = new book4order(rs.getString("BOOK_ID_PER_BOOK"), rs.getString("NAME"), rs2.getInt("AMOUNT"), rs.getInt("SELLING_PRICE"), rs.getInt("DISCOUNT"), rs2.getString("EDITION"), "", rs.getString("VAT"));
                        temp1.setAuthor(author);
                        temp1.getAmountt().setText(Integer.toString(1));
                        books.add(temp1);
                    }

                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(OrderingBookController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return books;
    }

    @FXML
    private void handleAddB(ActionEvent event) {
        JFXTextField bookname = new JFXTextField();
        //JFXTextField authorname=new JFXTextField();
        bookname.setPromptText("BOOK NAME");
        //authorname.setPromptText("AUTHOR NAME");
        bookname.setPrefWidth(167);
        // authorname.setPrefWidth(167);

        bookname.setLabelFloat(true);
        HBox hboxau = new HBox();

        hboxau.setSpacing(15);
        hboxau.setPadding(new Insets(10, 12, 15, 20));

        Collections.addAll(newBook, bookname);
        hboxau.getChildren().addAll(bookname);
        vboxbookauthor.getChildren().addAll(hboxau);

    }

    @FXML
    private void handleSearch(ActionEvent event) throws ClassNotFoundException {
        if (!vboxtable.getChildren().isEmpty()) {
            vboxtable.getChildren().clear();
        }

        TableView<book4order> table;
        table = new TableView<>();
        //Name column
        TableColumn<book4order, String> IdColumn = new TableColumn<>("BOOK ID");
        IdColumn.setMinWidth(200);
        IdColumn.setCellValueFactory(new PropertyValueFactory<>("bookid"));

        //Price column
        TableColumn<book4order, String> nameColumn = new TableColumn<>("NAME");
        nameColumn.setMinWidth(100);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        //Quantity column
        TableColumn<book4order, Integer> quantityColumn = new TableColumn<>("AMOUNT");
        quantityColumn.setMinWidth(100);
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));

        //Quantity column
        TableColumn<book4order, Integer> priceColumn = new TableColumn<>("SELLING PRICE");
        priceColumn.setMinWidth(100);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("sellingprice"));

        //Quantity column
        TableColumn<book4order, Integer> discountColumn = new TableColumn<>("DISCOUNT");
        discountColumn.setMinWidth(100);
        discountColumn.setCellValueFactory(new PropertyValueFactory<>("discount"));

        //Quantity column
        TableColumn<book4order, String> ediColumn = new TableColumn<>("EDITION");
        ediColumn.setMinWidth(100);
        ediColumn.setCellValueFactory(new PropertyValueFactory<>("edition"));

        TableColumn<book4order, ListView<String>> listColumn = new TableColumn<>("AUTHOR");
        listColumn.setMinWidth(200);
        listColumn.setCellValueFactory(new PropertyValueFactory<>("author"));

        TableColumn<book4order, String> selectColumn = new TableColumn<>("SELECT");
        selectColumn.setMinWidth(50);
        selectColumn.setCellValueFactory(new PropertyValueFactory<>("select"));

        TableColumn<book4order, JFXTextField> giveamColumn = new TableColumn<>("ORDER");
        giveamColumn.setMinWidth(20);
        giveamColumn.setCellValueFactory(new PropertyValueFactory<>("amountt"));

        TableColumn<book4order, JFXTextField> vatColumn = new TableColumn<>("VAT");
        vatColumn.setMinWidth(80);
        vatColumn.setCellValueFactory(new PropertyValueFactory<>("vat"));

        table.getColumns().addAll(selectColumn, giveamColumn, IdColumn, nameColumn, listColumn, quantityColumn, priceColumn, discountColumn, vatColumn, ediColumn);
        table.getItems().setAll(getProduct());
        JFXButton addbook = new JFXButton();
        addbook.setText("SUBMIT");
        //addbook.getStyleClass().clear();
        addbook.getStyleClass().add("button_salary");
        addbook.setPadding(new Insets(10, 10, 10, 10));

        addbook.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    settingreceipt();
                } catch (SQLException ex) {
                    Logger.getLogger(OrderingBookController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

//        addbook.setPadding(new Insets(10, 10, 10, 10));
//        addbook.setOpaqueInsets(new Insets(20, 20, 20, 10));
        vboxtable.getChildren().addAll(table, addbook);

    }

}
