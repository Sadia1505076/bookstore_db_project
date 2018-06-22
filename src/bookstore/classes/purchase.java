
package bookstore.classes;

public class purchase {
    String purid;
    String suppid;
    String bookid;
    String buyingprice;
    String date;
    Integer amount;
    String edi;

    public purchase(String purid, String suppid, String bookid, String date, Integer amount, String edi,String buy) {
        this.purid = purid;
        this.suppid = suppid;
        this.bookid = bookid;
        this.date = date;
        this.amount = amount;
        this.edi = edi;
        this.buyingprice=buy;
    }

    public String getBuyingprice() {
        return buyingprice;
    }

    public void setBuyingprice(String buyingprice) {
        this.buyingprice = buyingprice;
    }
   
    public String getPurid() {
        return purid;
    }

    public void setPurid(String purid) {
        this.purid = purid;
    }

    public String getSuppid() {
        return suppid;
    }

    public void setSuppid(String suppid) {
        this.suppid = suppid;
    }

    public String getBookid() {
        return bookid;
    }

    public void setBookid(String bookid) {
        this.bookid = bookid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getEdi() {
        return edi;
    }

    public void setEdi(String edi) {
        this.edi = edi;
    }
    
    
}
