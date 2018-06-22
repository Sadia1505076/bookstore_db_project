
package bookstore.classes;

public class order {
    String bookname;
    Integer amount;
    String customerid;
    String date;

    public order(String bookname, Integer amount, String customerid, String date) {
        this.bookname = bookname;
        this.amount = amount;
        this.customerid = customerid;
        this.date = date;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getCustomerid() {
        return customerid;
    }

    public void setCustomerid(String customerid) {
        this.customerid = customerid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
}
