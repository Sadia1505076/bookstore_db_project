
package bookstore;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;


public class DismissController implements Initializable {

    @FXML
    private JFXComboBox<String> ecombo;
    @FXML
    private JFXTextField EID;

     ObservableList<String> SCombo=FXCollections.observableArrayList("STAFF","MANAGER");
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ecombo.setValue("STAFF");
        ecombo.setItems(SCombo);
        ecombo.setValue("MANAGER");
        ecombo.setItems(SCombo);
  
    }    

    @FXML
    private void HANDLESAVE(ActionEvent event) throws ClassNotFoundException, SQLException {
        boolean is=false; 
        try {
            int a = Integer.parseInt(EID.getText());
        } catch (NumberFormatException e) {
            is = true;
            EID.setText("* ENTER A NUMBER");
            EID.setStyle("-fx-text-fill:#E74C3C;-fx-faint-focus-color: transparent; -fx-focus-color:rgba(255,0,0,0.2);");

        }
        if(!is)
        {
            Class.forName("oracle.jdbc.OracleDriver");
            Connection conn = new DBConnection().getConnection();
            ResultSet rs = null,rs2=null;
            //String nlikethis = "%" + name.toLowerCase() + "%";
            PreparedStatement pst = null;
            String sql=null;
          
            if (ecombo.getValue().equals("STAFF")) {
                sql = "SELECT STAFF_ID FROM STAFFS WHERE STAFF_ID=?";
                pst = conn.prepareStatement(sql);
                pst.setInt(1, Integer.parseInt(EID.getText()));
                pst.executeUpdate();
                rs = pst.executeQuery();
                if (rs.next()) {

                    System.out.println(EID.getText());
                    String getDBUSERByUserIdSql = "{call DELETE_STAFF(?,?)}";
                    CallableStatement callableStatement = null;
                    callableStatement = conn.prepareCall(getDBUSERByUserIdSql);
                    callableStatement.setInt(1, Integer.parseInt(EID.getText()));
                   callableStatement.registerOutParameter(2, java.sql.Types.VARCHAR);
                   callableStatement.executeUpdate();
                   if(callableStatement.getString(2).equals("DOESNT EXIST"))
                   {
                        Alert al = new Alert(Alert.AlertType.ERROR);
                    al.setHeaderText(null);
                    al.setContentText("STAFF DOESN'T WORK HERE ANYMORE!!!");
                    al.showAndWait();
                   }
                   else
                   {
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
                    al.setContentText("STAFF DOESN'T EXIST!!!");
                    al.showAndWait();
                    EID.clear();
                   // DESIGNATION.clear();

                }
            }
            if (ecombo.getValue().equals("MANAGER")) {
           
                sql = "SELECT MANAGER_ID FROM MANAGERS WHERE MANAGER_ID=?";
                pst = conn.prepareStatement(sql);
                pst.setInt(1, Integer.parseInt(EID.getText()));
                pst.executeUpdate();
                rs2 = pst.executeQuery();
                if (rs2.next()) {

                    String getDBUSERByUserIdSql = "{call DELETE_MANAGER(?,?)}";
                    CallableStatement callableStatement = null;
                    callableStatement = conn.prepareCall(getDBUSERByUserIdSql);
                    callableStatement.setInt(1, Integer.parseInt(EID.getText()));
                    callableStatement.registerOutParameter(2, java.sql.Types.VARCHAR);
                   callableStatement.executeUpdate();
                   if(callableStatement.getString(2).equals("DOESNT EXIST"))
                   {
                        Alert al = new Alert(Alert.AlertType.ERROR);
                    al.setHeaderText(null);
                    al.setContentText("STAFF DOESN'T WORK HERE ANYMORE!!!");
                    al.showAndWait();
                   }
                   else
                   {
                        Alert al = new Alert(Alert.AlertType.CONFIRMATION);
                    al.setHeaderText(null);
                    al.setContentText("CHANGED SUCCESSFULLY!!!");
                    al.showAndWait();
                    EID.clear();
                    //DESIGNATION.clear();

                   }
                   
                } else {
                    Alert al = new Alert(Alert.AlertType.ERROR);
                    al.setHeaderText(null);
                    al.setContentText("MANAGER DOESN'T EXIST!!!");
                    al.showAndWait();
                    EID.clear();
                    //DESIGNATION.clear();

                }
            }
           
        }
    }
    
}
