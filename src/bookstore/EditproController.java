
package bookstore;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class EditproController implements Initializable {

    @FXML
    private JFXTextField name;
    @FXML
    private JFXTextField designation;
    @FXML
    private JFXTextField address;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXTextField phone;
    @FXML
    private JFXButton save;
    String userid;
    String who;
    void settext(String who,String name,String designation,String email,String address,String phone,String user)
    {
        this.name.setText(name);
        this.designation.setText(designation);
        this.address.setText(address);
        this.email.setText(email);
        this.phone.setText(phone);
        userid=user;
        this.who=who;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleSave(ActionEvent event) throws SQLException {
        Connection conn = new DBConnection().getConnection();
        if (who.equals("manager")) {
            String sql = "UPDATE MANAGERS SET MGR_NAME=?,MGR_ADDRESS=?,MGR_EMAIL_ID=?,MGR_PHONE_NO=?  WHERE MANAGER_ID=?";
            PreparedStatement pst = null;
            pst = conn.prepareStatement(sql);
            pst.setString(1, name.getText());
            pst.setString(2, address.getText());
            pst.setString(3, email.getText());
            pst.setString(4, phone.getText());
            pst.setString(5, userid);
            pst.executeUpdate();
        }
        else if(who.equals("gm"))
        {
            String sql = "UPDATE GM SET GM_NAME=?,GM_ADDRESS=?,GM_EMAIL_ID=?,GM_PHONE_NO=?  WHERE GM_ID=?";
            PreparedStatement pst = null;
            pst = conn.prepareStatement(sql);
            pst.setString(1, name.getText());
            pst.setString(2, address.getText());
            pst.setString(3, email.getText());
            pst.setString(4, phone.getText());
            pst.setString(5, userid);
            pst.executeUpdate();
        }
        name.clear();
        address.clear();
        email.clear();
        phone.clear();
        designation.clear();

    }
    
}
