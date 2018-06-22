
package bookstore.classes;

import com.jfoenix.controls.JFXTextField;

public class supplier {
    JFXTextField comname;
    JFXTextField address;
    JFXTextField email;
    JFXTextField phoneno;
    JFXTextField edition;
    JFXTextField amountofbook;
    JFXTextField buyingPricePerBook;

    public supplier(JFXTextField comname, JFXTextField address, JFXTextField email, JFXTextField phoneno, JFXTextField edition, JFXTextField amountofbook, JFXTextField buyingPricePerBook) {
        this.comname = comname;
        this.address = address;
        this.email = email;
        this.phoneno = phoneno;
        this.edition = edition;
        this.amountofbook = amountofbook;
        this.buyingPricePerBook = buyingPricePerBook;
    }
    
    public JFXTextField getComname() {
        return comname;
    }

    public void setComname(JFXTextField comname) {
        this.comname = comname;
    }

    public JFXTextField getAddress() {
        return address;
    }

    public void setAddress(JFXTextField address) {
        this.address = address;
    }

    public JFXTextField getEmail() {
        return email;
    }

    public void setEmail(JFXTextField email) {
        this.email = email;
    }

    public JFXTextField getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(JFXTextField phoneno) {
        this.phoneno = phoneno;
    }

    public JFXTextField getEdition() {
        return edition;
    }

    public void setEdition(JFXTextField edition) {
        this.edition = edition;
    }

    public JFXTextField getAmountofbook() {
        return amountofbook;
    }

    public void setAmountofbook(JFXTextField amountofbook) {
        this.amountofbook = amountofbook;
    }

    public JFXTextField getBuyingPricePerBook() {
        return buyingPricePerBook;
    }

    public void setBuyingPricePerBook(JFXTextField buyingPricePerBook) {
        this.buyingPricePerBook = buyingPricePerBook;
    }

}
