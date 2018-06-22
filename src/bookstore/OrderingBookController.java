
package bookstore;
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

public class OrderingBookController implements Initializable {

    ArrayList<book_author4order> bookauthor=new ArrayList<>();
    ArrayList<JFXTextField> authorlist=new ArrayList<>();
    ArrayList<JFXTextField> customer=new ArrayList<>();
    ArrayList<receipt> receipt=new ArrayList<>();
    book_author4order temp=null;
    ObservableList<book4order> books = FXCollections.observableArrayList();
    @FXML
    private VBox vboxmain;
    @FXML
    private VBox vboxtable;
    @FXML
    private JFXButton search;
    @FXML
    private JFXButton addbook;
    @FXML
    private VBox vboxbookauthor;
    @FXML
    private VBox vboxauthor;
    @FXML
    private JFXButton addauhtor;
    @FXML
    private JFXTextField bookname;
    @FXML
    private JFXTextField authorname;
   // book_author4order tempo=new book_author4order(bookname, authorname);
    @FXML
    private JFXButton search2;
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
   void addingA(VBox vbox)
   {
       JFXTextField authorname = new JFXTextField();
       
        //Collections.addAll(Authorlist,authorname);
        
        authorname.setMaxWidth(167);
        //authorname.setStyle(value);
        //authorname.setLabelFloat(true);
        
        authorname.setPromptText("AUTHOR NAME");
        
        authorname.setLabelFloat(true);
        
        
        temp.addingAuthor(authorname);
        vbox.getChildren().addAll(authorname);
   }
   
    public ObservableList<book4order> getProduct() throws ClassNotFoundException{
        
        
        try {
            ListView<String> author=new ListView<>();
            ObservableList items=FXCollections.observableArrayList(authorname.getText());
            author.setEditable(true);
            author.setCellFactory(TextFieldListCell.forListView());
            author=new ListView<>(items);
            
            
            String bookid=bookname.getText()+authorname.getText();
            for(int i=0;i<authorlist.size();i++){
                bookid=bookid+authorlist.get(i).getText();
                author.getItems().add(authorlist.get(i).getText());
            }
            
            author.setPrefHeight(items.size()*36+2);
            System.out.println(bookid);
            Class.forName("oracle.jdbc.OracleDriver");
            Connection conn = new DBConnection().getConnection();
            
            String sql = "SELECT BOOK_ID,NAME,SELLING_PRICE,NVL(DISCOUNT,0),NVL(VAT,0) FROM BOOK WHERE BOOK_ID=?";
            String sql2="SELECT AMOUNT,EDITION FROM BOOK_WITH_EDITION WHERE BOOK_ID=?";
            PreparedStatement pst = null;
            ResultSet rs = null;
            ResultSet rs2 = null;
            pst = conn.prepareStatement(sql);
            pst.setString(1,bookid);
            pst.executeUpdate();
            rs = pst.executeQuery();
            //System.out.println(rs.next());
            pst=conn.prepareStatement(sql2);
            pst.setString(1,bookid);
            pst.executeUpdate();
            rs2 = pst.executeQuery();
            
            book4order temp=null;
            while(rs.next())
            {
                while(rs2.next()){
                    temp = new book4order(rs.getString("BOOK_ID"), rs.getString("NAME"), rs2.getInt("AMOUNT"), rs.getInt("SELLING_PRICE"), rs.getInt("NVL(DISCOUNT,0)"),rs2.getString("EDITION"), "",rs.getString("NVL(VAT,0)"));
                    temp.setAuthor(author);
                    books.add(temp);
                }
               
            }
            
            for(int i=0;i<bookauthor.size();i++)
            {
                
                ListView<String> authormore = new ListView<>();
                ObservableList itemsmore = FXCollections.observableArrayList();
                authormore.setEditable(true);
                authormore.setCellFactory(TextFieldListCell.forListView());
                authormore = new ListView<>(itemsmore);
                bookid=bookauthor.get(i).getBookname().getText();
                for(int j=0;j<bookauthor.get(i).gettingSize();j++)
                {
                    bookid=bookid+bookauthor.get(i).gettingoneauthor(j).getText();
                    authormore.getItems().add(bookauthor.get(i).gettingoneauthor(j).getText());
                }
                System.out.println(bookid);
                authormore.setPrefHeight(itemsmore.size() *36 + 2);
                
                //sql = "SELECT BOOK_ID,NAME,AMOUNT,SELLING_PRICE,DISCOUNT FROM BOOK WHERE BOOK_ID=?";
                pst = conn.prepareStatement(sql);
                pst.setString(1, bookid);
                pst.executeUpdate();
                rs = pst.executeQuery();
                
                
                pst = conn.prepareStatement(sql2);
                pst.setString(1, bookid);
                pst.executeUpdate();
                rs2 = pst.executeQuery();
                while (rs.next()) {
                    while(rs2.next()){
                        temp = new book4order(rs.getString("BOOK_ID"), rs.getString("NAME"), rs2.getInt("AMOUNT"), rs.getInt("SELLING_PRICE"), rs.getInt("NVL(DISCOUNT,0)"), rs2.getString("EDITION"), "",rs.getString("NVL(VAT,0)"));
                        temp.setAuthor(authormore);
                        books.add(temp);
                    }
                    
                }

            }
            
          
            
        } catch (SQLException ex) {
            Logger.getLogger(OrderingBookController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        return books;
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
            for (book4order rec : books) {
                if (rec.getSelect().isSelected()) {

                    Collections.addAll(receipt, new receipt(rec.getName(), rec.getAmountt().getText(), Integer.toString(rec.getSellingprice()), Integer.toString(rec.getDiscount()), rec.getBookid(), rec.getVat(),rec.getEdition()));
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
    
    
   
 
    @FXML
    private void handleSearch(ActionEvent event) throws ClassNotFoundException {
         
        if(!vboxtable.getChildren().isEmpty()) vboxtable.getChildren().clear();

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
        TableColumn<book4order,String> ediColumn = new TableColumn<>("EDITION");
        ediColumn.setMinWidth(100);
        ediColumn.setCellValueFactory(new PropertyValueFactory<>("edition"));
        
        
        TableColumn<book4order,ListView<String>> listColumn = new TableColumn<>("AUTHOR");
        listColumn.setMinWidth(200);
        listColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        
        
        TableColumn<book4order,String> selectColumn = new TableColumn<>("SELECT");
        selectColumn.setMinWidth(50);
        selectColumn.setCellValueFactory(new PropertyValueFactory<>("select"));
        
        
        TableColumn<book4order,JFXTextField> giveamColumn = new TableColumn<>("ORDER");
        giveamColumn.setMinWidth(20);
        giveamColumn.setCellValueFactory(new PropertyValueFactory<>("amountt"));
        
        TableColumn<book4order,JFXTextField> vatColumn = new TableColumn<>("VAT");
        vatColumn.setMinWidth(80);
        vatColumn.setCellValueFactory(new PropertyValueFactory<>("vat"));



    
        table.getColumns().addAll(selectColumn,giveamColumn,IdColumn,nameColumn,listColumn,quantityColumn,priceColumn,discountColumn,vatColumn,ediColumn);
        table.getItems().setAll(getProduct());
        JFXButton addbook=new JFXButton();
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
        vboxtable.getChildren().addAll(table,addbook);
       
        
    }
    

    @FXML
    private void handleAddB(ActionEvent event) {
        JFXTextField bookname=new JFXTextField();
        JFXTextField authorname=new JFXTextField();
        bookname.setPromptText("BOOK NAME");
        authorname.setPromptText("AUTHOR NAME");
        bookname.setPrefWidth(167);
        authorname.setPrefWidth(167);
        
        bookname.setLabelFloat(true);
        authorname.setLabelFloat(true);
        VBox vbau=new VBox(20, authorname);
        vbau.setPadding(new Insets(0, 0, 0, 0));
       // authorname.getStyleClass().addAll("")
        HBox hboxau=new HBox();
        //HBox hboxsupp2=new HBox();
         temp=new book_author4order(bookname,authorname);
        
        
        hboxau.setSpacing(15);
        hboxau.setPadding(new Insets(10, 12, 15, 20));
        JFXButton addau=new JFXButton("ADD AUTHOR");
        
        addau.setContentDisplay(ContentDisplay.TOP);
        Image image = new Image(getClass().getResourceAsStream("/icons/if_sign-add_299068.png"));
        //Button button1 = new Button("Accept");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(28);
        imageView.setFitWidth(30);
        addau.setGraphic(imageView);
        
        addau.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                addingA(vbau);
                
            }
        });
        
        hboxau.getChildren().addAll(bookname,vbau,addau);
        vboxbookauthor.getChildren().addAll(hboxau);
        Collections.addAll(bookauthor,temp);
        System.out.println(bookauthor.size());
    }

    @FXML
    private void handleAddA(ActionEvent event) {
        
       JFXTextField authorname = new JFXTextField();
       
        authorname.setMaxWidth(167);
        //authorname.setStyle(value);
        //authorname.setLabelFloat(true);
        
        authorname.setPromptText("AUTHOR NAME");
        
        authorname.setLabelFloat(true);
        Collections.addAll(authorlist,authorname);
        
        //temp.addingAuthor(authorname);
        vboxauthor.getChildren().addAll(authorname);
    }
    //Collections.addAll(bookauthor,tempo);

    @FXML
    private void handleSearchByBA(ActionEvent event) {
    }
}
