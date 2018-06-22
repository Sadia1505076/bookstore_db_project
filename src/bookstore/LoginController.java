
package bookstore;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoginController implements Initializable {
     ObservableList<String> SCombo=FXCollections.observableArrayList("EMPLOYEE","MANAGER","GENERAL MANAGER");
    @FXML
    private JFXComboBox<String> logincombo;
    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXButton login;
    @FXML
    private JFXButton register;
    @FXML
    private Label labeloferror;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        logincombo.setValue("EMPLOYEE");
        logincombo.setItems(SCombo);
        logincombo.setValue("MANAGER");
        logincombo.setItems(SCombo);
        logincombo.setValue("GENERAL MANAGER");
        logincombo.setItems(SCombo);
    }    
    void errorPrint(String s)
    {
        username.clear();
            password.clear();
            labeloferror.setText(s);
            labeloferror.setTextFill(Color.web("#ffffff"));
            labeloferror.setTextAlignment(TextAlignment.CENTER);
            labeloferror.setFont(Font.font("Cambria", 17));
            labeloferror.setWrapText(true);
            labeloferror.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    labeloferror.setScaleX(1.5);
                    labeloferror.setScaleY(1.5);
                }
            });

            labeloferror.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    labeloferror.setScaleX(1);
                    labeloferror.setScaleY(1);
                }
            });
        
    }
    @FXML
    private void handleLogin(ActionEvent event) throws ClassNotFoundException, SQLException {
        
        PreparedStatement pst = null;
        ResultSet rs=null;
        String designation=logincombo.getValue();
        String user=username.getText();
        String pass=password.getText();
        Class.forName("oracle.jdbc.OracleDriver");
        Connection conn=new DBConnection().getConnection();
        String sql=null;
        if(user.isEmpty()|| pass.isEmpty())
        {
            errorPrint("FIELDS ARE EMPTY");
        }
       
        else if(designation.equals("EMPLOYEE"))
        {
            sql="SELECT EMPLOYEE_USER_ID FROM EMPLOYEE_USER_PASS WHERE EMPLOYEE_USER_ID=? AND PASSWORD=?";
        
        pst=conn.prepareStatement(sql);
        pst.setString(1,user);
        pst.setString(2,pass);
        rs=pst.executeQuery();
        if(!rs.next()) 
        {
            errorPrint("INVALID USERNAME OR PASSWORD");
        }
        else 
        {
               Parent root;
                Stage stage = null;
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("staffhome.fxml"));
                    root = loader.load();
                    StaffhomeController controller = loader.getController();
                    controller.setVariable(user,"EMPLOYEE");
                    System.out.println(user);

                    //controller.setMain(this);
                    Scene scene = new Scene(root);
                    stage = new Stage(StageStyle.DECORATED);
                    stage.setTitle("insert book");
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException ex) {
                    System.out.println(ex.toString());
                }
        }
        
        }
        
        
       /////--------------------------------------manager------------------------------ 
        else if(designation.equals("MANAGER"))
        {
            sql="SELECT MANAGER_USER_ID FROM MANAGER_USER_PASS WHERE MANAGER_USER_ID=? AND PASSWORD=?";
        
        pst=conn.prepareStatement(sql);
        pst.setString(1,user);
        pst.setString(2,pass);
        rs=pst.executeQuery();
        if(!rs.next()) 
        {
            errorPrint("INVALID USERNAME OR PASSWORD");
        }
        else 
        {
                Parent root;
                Stage stage = null;
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("employeeHome.fxml"));
                    root = loader.load();
                    EmployeeHomeController controller = loader.getController();
                    controller.setVariable(user,"MANAGER");
                    System.out.println(user);

                    //controller.setMain(this);
                    Scene scene = new Scene(root);
                    stage = new Stage(StageStyle.DECORATED);
                    stage.setTitle("insert book");
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException ex) {
                    System.out.println(ex.toString());
                }
            }

        } ///--------------------GM----------------------------------
        else if (designation.equals("GENERAL MANAGER")) {
            sql = "SELECT GM_USER_ID FROM GENERAL_MANAGER_USER_PASS WHERE GM_USER_ID=? AND PASWORD=?";

            pst = conn.prepareStatement(sql);
            pst.setString(1, user);
            pst.setString(2, pass);
            rs = pst.executeQuery();
            if (!rs.next()) {
                errorPrint("INVALID USERNAME OR PASSWORD");
            } else {
                Parent root;
                Stage stage = null;
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("generalManagerHome.fxml"));
                    root = loader.load();
                    GeneralManagerHomeController controller = loader.getController();
                    controller.setVariable(user,"GM");
                    System.out.println(user);

                    //controller.setMain(this);
                    Scene scene = new Scene(root);
                    stage = new Stage(StageStyle.DECORATED);
                    stage.setTitle("home");
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException ex) {
                    System.out.println(ex.toString());
                }
            }

        }

    }

    @FXML
    private void handleRegister(ActionEvent event) {
        
        Parent root;
                Stage stage = null;
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("signUp.fxml"));
                    root = loader.load();
                    //GeneralManagerHomeController controller = loader.getController();
                    //controller.setVariable(user,"GM");
                    //System.out.println(user);

                    //controller.setMain(this);
                    Scene scene = new Scene(root);
                    stage = new Stage(StageStyle.DECORATED);
                    stage.setTitle("sign up");
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException ex) {
                    System.out.println(ex.toString());
                }
    }
    
}
