
package bookstore.classes;

public class bookrequired {
    String bookid;
    Integer amount;
    String edition;

    public bookrequired(String bookid, Integer amount,String edi) {
        this.bookid = bookid;
        this.amount = amount;
        this.edition=edi;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }
    
    public String getBookid() {
        return bookid;
    }

    public void setBookid(String bookid) {
        this.bookid = bookid;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
    
}
