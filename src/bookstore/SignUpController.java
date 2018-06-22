
package bookstore;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.effects.JFXDepthManager;
import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;


public class SignUpController implements Initializable {

    @FXML
    private JFXPasswordField signUpPassword;
    @FXML
    private JFXTextField signUpID;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    

   public void signUpButtonPressed(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        String user_id = signUpID.getText();
        String user_password = signUpPassword.getText();

        if (user_id.isEmpty() && user_password.isEmpty()) {
            //alert_message.display("Invalid sign up", "Please Enter ID and Password");
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setHeaderText(null);
            al.setContentText("Invalid sign up\n.Please Enter ID and Password");
            al.showAndWait();

        }

        if (user_password.isEmpty()) {
            //alert_message.display("Invalid sign up", "Please Create a Password");
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setHeaderText(null);
            al.setContentText("Invalid sign up\n.Please Create a Password");
            al.showAndWait();

        } else {

            if (!user_id.isEmpty()) {
                Class.forName("oracle.jdbc.OracleDriver");
                Connection myConn = new DBConnection().getConnection();
           
                String sql = "{ ? = call ID_VALIDATION(?) }";
                CallableStatement call_statement = myConn.prepareCall(sql);
                call_statement.setString(2, user_id);
                call_statement.registerOutParameter(1, Types.VARCHAR);
                call_statement.execute();
                String validity = call_statement.getString(1);

                String sql2 = "{ ? = call JOB_VALIDATION(?) }";
                CallableStatement job_statement = myConn.prepareCall(sql2);
                job_statement.setString(2, user_id);
                job_statement.registerOutParameter(1, Types.VARCHAR);
                job_statement.execute();
                String job = job_statement.getString(1);

                if ("Valid".equals(validity)) {
                    if (null != job) {
                        switch (job) {
                            case "GM": {
                                String sql3 = "INSERT INTO GENERAL_MANAGER_USER_PASS VALUES( ?, ? )";
                                PreparedStatement myStmt = myConn.prepareStatement(sql3);
                                myStmt.setString(1, user_id);
                                myStmt.setString(2, user_password);
                                myStmt.execute();
                                String sql_str = "COMMIT";
                                PreparedStatement stmt = myConn.prepareStatement(sql_str);
                                stmt.execute();
                                break;
                            }
                            case "Manager": {
                                String sql3 = "INSERT INTO MANAGER_USER_PASS VALUES( ?, ? )";
                                PreparedStatement myStmt = myConn.prepareStatement(sql3);
                                myStmt.setString(1, user_id);
                                myStmt.setString(2, user_password);
                                myStmt.execute();
                                String sql_str = "COMMIT";
                                PreparedStatement stmt = myConn.prepareStatement(sql_str);
                                stmt.execute();
                                break;
                            }
                            case "Staff": {
                                String sql3 = "INSERT INTO EMPLOYEE_USER_PASS VALUES( ?, ? )";
                                PreparedStatement myStmt = myConn.prepareStatement(sql3);
                                myStmt.setString(1, user_id);
                                myStmt.setString(2, user_password);
                                myStmt.execute();
                                String sql_str = "COMMIT";
                                PreparedStatement stmt = myConn.prepareStatement(sql_str);
                                stmt.execute();
                                break;
                            }
                            default:
                                break;
                        }
                    }
                } else {
                    //alert_message.display("Invalid sign up", "Please Enter a Valid ID");
                    Alert al = new Alert(Alert.AlertType.ERROR);
                    al.setHeaderText(null);
                    al.setContentText("Invalid sign up\n.Please Enter a Valid ID");
                    al.showAndWait();

                }

            } else {
                // alert_message.display("Invalid sign up", "Please Enter a ID");
                Alert al = new Alert(Alert.AlertType.ERROR);
                al.setHeaderText(null);
                al.setContentText("Invalid sign up\n.Please Enter A ID");
                al.showAndWait();

            }
        }

    }

    @FXML
    private void handleClose(MouseEvent event) {
        System.exit(0);
    }

    
}
