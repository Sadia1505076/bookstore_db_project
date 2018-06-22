
package bookstore;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javax.swing.text.Position;


public class SalaryCController implements Initializable {
    ObservableList<String> SCombo=FXCollections.observableArrayList("STAFF","MANAGER");
  
    @FXML
    private JFXComboBox<String> ecombo;
    @FXML
    private JFXTextField empid;
    @FXML
    private VBox vboxShow;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ecombo.setValue("STAFF");
        ecombo.setItems(SCombo);
        ecombo.setValue("MANAGER");
        ecombo.setItems(SCombo);
       
    }    

    @FXML
    private void handleOk(ActionEvent event) throws ClassNotFoundException, SQLException {
        if(!vboxShow.getChildren().isEmpty()) vboxShow.getChildren().clear();
        boolean is=false; 
        try {
               int a=Integer.parseInt(empid.getText());
           } catch (NumberFormatException e) {
               is=true;
               empid.setText("* ENTER A NUMBER");
               empid.setStyle("-fx-text-fill:#E74C3C;-fx-faint-focus-color: transparent; -fx-focus-color:rgba(255,0,0,0.2);");
                   
               
           }
        if (!is && ecombo.getValue().equals("STAFF") ) {
            
            Label Id = new Label();
            Label Name= new Label();
            Id.setStyle("-fx-font-weight:bold");
            Name.setStyle("-fx-font-weight:bold");
            HBox sal=new HBox();
            sal.setSpacing(20);
            Label sa=new Label("SALARY: ");
            sa.setStyle("-fx-font-weight:bold");
            sa.setPadding(new Insets(10, 0, 0, 0));
            JFXTextField salary= new JFXTextField();
            salary.setLabelFloat(true);
            salary.setStyle("-fx-font-weight:bold");
            sal.getChildren().addAll(sa,salary);
            
            HBox com=new HBox();
            com.setSpacing(20);
            Label c=new Label("COMMISSION: ");
            c.setStyle("-fx-font-weight:bold");
            c.setPadding(new Insets(10, 0, 0, 0));
            JFXTextField comm= new JFXTextField();
            comm.setLabelFloat(true);
            
            
            comm.setStyle("-fx-font-weight:bold");
            
            com.getChildren().addAll(c,comm);
            
            
            
            Class.forName("oracle.jdbc.OracleDriver");
            Connection conn = new DBConnection().getConnection();
            
            String sql = "SELECT STAFF_ID,STF_NAME,STF_SALARY,STF_COMMISSION_PCT FROM STAFFS WHERE STAFF_ID=?";
            ResultSet rs = null;
            //String nlikethis = "%" + name.toLowerCase() + "%";
            PreparedStatement pst = null;
            pst = conn.prepareStatement(sql);
            pst.setString(1, empid.getText());
            pst.executeUpdate();
            rs = pst.executeQuery();
            if(rs.next())
            {
                Id.setText("STAFF ID: " + rs.getInt("STAFF_ID"));
                Name.setText("NAME: " + rs.getString("STF_NAME"));
                comm.setText(Integer.toString(rs.getInt("STF_COMMISSION_PCT")));
                salary.setText(Integer.toString(rs.getInt("STF_SALARY")));

                JFXButton done = new JFXButton();
                //search2.setContentDisplay(ContentDisplay.TOP);
                Image image = new Image(getClass().getResourceAsStream("/icons/checked.png"));
                //Button button1 = new Button("Accept");
                ImageView imageView = new ImageView(image);
                imageView.setFitHeight(42);
                imageView.setFitWidth(42);
                done.setGraphic(imageView);

                done.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            //table.getItems().setAll(getbooksreq(search.getText()));
                            Class.forName("oracle.jdbc.OracleDriver");
                            Connection conn = new DBConnection().getConnection();
                            PreparedStatement pst = null;
                            String sql = "UPDATE STAFFS SET STF_SALARY=?,STF_COMMISSION_PCT=? WHERE STAFF_ID=?";
                            pst = conn.prepareStatement(sql);
                            pst.setString(1, salary.getText());
                            pst.setString(2, comm.getText());
                            pst.setString(3, empid.getText());
                            pst.executeUpdate();
                            Alert al = new Alert(Alert.AlertType.CONFIRMATION);
                            al.setHeaderText(null);
                            al.setContentText("CHANGED SUCCESSFULLY!!!");
                            //TextArea error = new TextArea(text);
                            //al.getDialogPane().setExpandableContent(error);
                            al.showAndWait();
                            vboxShow.getChildren().clear();
                            empid.clear();
                            
                        } catch (SQLException ex) {
                            Logger.getLogger(SalaryCController.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(SalaryCController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                });
                vboxShow.getChildren().addAll(Id, Name, sal, com, done);
            }
            else
            {
                Alert al = new Alert(Alert.AlertType.ERROR);
                al.setHeaderText(null);
                al.setContentText("STAFF DOESN'T EXIST.CHECK THE ID!!");
                //TextArea error = new TextArea(text);
                //al.getDialogPane().setExpandableContent(error);
                al.showAndWait();
            }

        }
        if (!is && ecombo.getValue().equals("MANAGER") ) {
            
            Label Id = new Label();
        
            Label Name= new Label();
            //Name.setText("NAME: ");
            Id.setStyle("-fx-font-weight:bold");
            Name.setStyle("-fx-font-weight:bold");
           
            HBox sal=new HBox();
            sal.setSpacing(20);
            Label sa=new Label("SALARY: ");
            sa.setStyle("-fx-font-weight:bold");
            sa.setPadding(new Insets(10, 0, 0, 0));
            JFXTextField salary= new JFXTextField();
            salary.setLabelFloat(true);
            salary.setStyle("-fx-font-weight:bold");
            
            sal.getChildren().addAll(sa,salary);
            
            HBox com=new HBox();
            com.setSpacing(20);
            Label c=new Label("COMMISSION: ");
            c.setStyle("-fx-font-weight:bold");
            c.setPadding(new Insets(10, 0, 0, 0));
            JFXTextField comm= new JFXTextField();
            comm.setLabelFloat(true);
            comm.setStyle("-fx-font-weight:bold");
            com.getChildren().addAll(c,comm);
            
            
            
            Class.forName("oracle.jdbc.OracleDriver");
            Connection conn = new DBConnection().getConnection();
            
            String sql = "SELECT MANAGER_ID,MGR_NAME,MGR_SALARY,MGR_COMMISSION_PCT FROM MANAGERS WHERE MANAGER_ID=?";
            ResultSet rs = null;
            //String nlikethis = "%" + name.toLowerCase() + "%";
            PreparedStatement pst = null;
            pst = conn.prepareStatement(sql);
            pst.setString(1, empid.getText());
            pst.executeUpdate();
            rs = pst.executeQuery();
            if(rs.next())
            {
                Id.setText("MANAGER ID: " + rs.getInt("MANAGER_ID"));
                Name.setText("NAME: " + rs.getString("MGR_NAME"));
                comm.setText(Integer.toString(rs.getInt("MGR_COMMISSION_PCT")));
                salary.setText(Integer.toString(rs.getInt("MGR_SALARY")));

                JFXButton done = new JFXButton();
                //search2.setContentDisplay(ContentDisplay.TOP);
                Image image = new Image(getClass().getResourceAsStream("/icons/checked.png"));
                //Button button1 = new Button("Accept");
                ImageView imageView = new ImageView(image);
                imageView.setFitHeight(42);
                imageView.setFitWidth(42);
                done.setGraphic(imageView);

                done.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        //table.getItems().setAll(getbooksreq(search.getText()));
                        try {
                            //table.getItems().setAll(getbooksreq(search.getText()));
                            Class.forName("oracle.jdbc.OracleDriver");
                            Connection conn = new DBConnection().getConnection();
                            PreparedStatement pst = null;
                            String sql = "UPDATE MANAGERS SET MGR_SALARY=?,MGR_COMMISSION_PCT=? WHERE MANAGER_ID=?";
                            pst = conn.prepareStatement(sql);
                            pst.setString(1, salary.getText());
                            pst.setString(2, comm.getText());
                            pst.setString(3, empid.getText());
                            pst.executeUpdate();
                            Alert al = new Alert(Alert.AlertType.CONFIRMATION);
                            al.setHeaderText(null);
                            al.setContentText("CHANGED SUCCESSFULLY!!!");
                            //TextArea error = new TextArea(text);
                            //al.getDialogPane().setExpandableContent(error);
                            al.showAndWait();
                            vboxShow.getChildren().clear();
                            empid.clear();
                            
                        } catch (SQLException ex) {
                            Logger.getLogger(SalaryCController.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(SalaryCController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                });
                vboxShow.getChildren().addAll(Id, Name, sal, com, done);
            }
            else
            {
                Alert al = new Alert(Alert.AlertType.ERROR);
                al.setHeaderText(null);
                al.setContentText("MANAGER DOESN'T EXIST.CHECK THE ID!!");
                //TextArea error = new TextArea(text);
                //al.getDialogPane().setExpandableContent(error);
                al.showAndWait();
            }

        }
    }
    
}
