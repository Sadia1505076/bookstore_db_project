
package bookstore;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;


public class ChangeManagingController implements Initializable {

    @FXML
    private JFXTextField staffid;
    @FXML
    private JFXTextField managerid;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleSave(ActionEvent event) throws ClassNotFoundException, SQLException {
        boolean is=false; 
        try {
            int a = Integer.parseInt(staffid.getText());
        } catch (NumberFormatException e) {
            is = true;
            staffid.setText("* ENTER A NUMBER");
            staffid.setStyle("-fx-text-fill:#E74C3C;-fx-faint-focus-color: transparent; -fx-focus-color:rgba(255,0,0,0.2);");

        }
        try {
            int a = Integer.parseInt(managerid.getText());
        } catch (NumberFormatException e) {
            is = true;
            managerid.setText("* ENTER A NUMBER");
            managerid.setStyle("-fx-text-fill:#E74C3C;-fx-faint-focus-color: transparent; -fx-focus-color:rgba(255,0,0,0.2);");

        }
        if(!is)
        {
            Class.forName("oracle.jdbc.OracleDriver");
            Connection conn = new DBConnection().getConnection();
            String sql = "SELECT STAFF_ID FROM STAFFS WHERE STAFF_ID=?";
            ResultSet rs = null,rs2=null;
            //String nlikethis = "%" + name.toLowerCase() + "%";
            PreparedStatement pst = null;
            pst = conn.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(staffid.getText()));
            pst.executeUpdate();
            rs = pst.executeQuery();
            
            sql="SELECT MANAGER_ID FROM MANAGERS WHERE MANAGER_ID=?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(managerid.getText()));
            pst.executeUpdate();
            rs2 = pst.executeQuery();
            if (rs.next() && rs2.next()) {

                String getDBUSERByUserIdSql = "{call CHANGE_MANAGER(?,?,?)}";
                CallableStatement callableStatement = null;
                callableStatement = conn.prepareCall(getDBUSERByUserIdSql);
                callableStatement.setInt(1, Integer.parseInt(staffid.getText()));
                callableStatement.setInt(2, Integer.parseInt(managerid.getText()));
                callableStatement.registerOutParameter(3, java.sql.Types.VARCHAR);
                
                  
                callableStatement.executeUpdate();
                if(callableStatement.getString(3).equals("STAFF"))
                {
                    
                    Alert al = new Alert(Alert.AlertType.ERROR);
                    al.setHeaderText(null);
                    al.setContentText("STAFF DOESN'T WORK HERE ANYMORE!!!");
                    al.showAndWait();

                }
                else if(callableStatement.getString(3).equals("MANAGER"))
                {
                    
                    Alert al = new Alert(Alert.AlertType.ERROR);
                    al.setHeaderText(null);
                    al.setContentText("MANAGER DOESN'T WORK HERE ANYMORE!!!");
                    al.showAndWait();

                }
                else {
                    Alert al = new Alert(Alert.AlertType.CONFIRMATION);
                    al.setHeaderText(null);
                    al.setContentText("CHANGED SUCCESSFULLY!!!");
                    al.showAndWait();
                    staffid.clear();
                    managerid.clear();

                }
                
              
            }
           
            else
            {
                Alert al = new Alert(Alert.AlertType.ERROR);
                al.setHeaderText(null);
                al.setContentText("EMPLOYEE DOESN'T EXIST!!!");
                al.showAndWait();
                staffid.clear();
                managerid.clear();

            }
        }
        
        
        
    }
    
}
