
package bookstore.classes;

public class receipt {
    String bookname;
    String amount;
    String price;
    String discount;
    String bookid;
    String vat;
    String edition;

    public receipt(String bookname, String amount, String price, String discount,String bookid,String vat,String edition) {
        this.bookname = bookname;
        this.amount = amount;
        this.price = price;
        this.discount = discount;
        this.bookid=bookid;
        this.vat=vat;
        this.edition=edition;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getBookid() {
        return bookid;
    }

    public void setBookid(String bookid) {
        this.bookid = bookid;
    }

    public String getVat() {
        return vat;
    }

    public void setVat(String vat) {
        this.vat = vat;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }
    
    
}
