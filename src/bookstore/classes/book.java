
package bookstore.classes;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

 public class book
    {
        private final SimpleStringProperty bookid;
        private final SimpleStringProperty name;
        private final  SimpleIntegerProperty amount;
        private final  SimpleIntegerProperty sellingprice;
        private final  SimpleIntegerProperty discount;
        private final  SimpleIntegerProperty vat;
        private final  SimpleStringProperty edition;
        //private final SimpleStringProperty 
        public book(String bookid,String name,Integer amount,Integer sellingprice,Integer discount,Integer vat,String edition)
        {
            this.bookid=new SimpleStringProperty(bookid);
            this.name=new SimpleStringProperty(name);
            this.amount=new SimpleIntegerProperty(amount);
            this.sellingprice=new SimpleIntegerProperty(sellingprice);
            this.discount=new SimpleIntegerProperty(discount);
            this.vat=new SimpleIntegerProperty(vat);
            this.edition=new SimpleStringProperty(edition);
        }

        public String getBookid() {
            return bookid.get();
        }

        public String getName() {
            return name.get();
        }

        public Integer getAmount() {
            return amount.get();
        }

        public Integer getSellingprice() {
            return sellingprice.get();
        }

        public Integer getDiscount() {
            return discount.get();
        }
        public Integer getVat() {
            return vat.get();
        }
        public String getEdition() {
            return edition.get();
        }


    }   
    
