
package bookstore.classes;

import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;


public class book4order {
      private final SimpleStringProperty bookid;
        private final SimpleStringProperty name;
        private final  SimpleIntegerProperty amount;
        private final  SimpleIntegerProperty sellingprice;
        private final  SimpleIntegerProperty discount;
        private final  SimpleStringProperty edition;
        private final  SimpleStringProperty vat;
        private ListView<String> author;
        private CheckBox select;
        private JFXTextField amountt;
        final int ROW_HEIGHT =36;
        private ObservableList items ;
        
        //private final SimpleStringProperty 
        public book4order(String bookid,String name,Integer amount,Integer sellingprice,Integer discount,String edition,String amountt,String vat)
        {
            this.bookid=new SimpleStringProperty(bookid);
            this.name=new SimpleStringProperty(name);
            this.amount=new SimpleIntegerProperty(amount);
            this.sellingprice=new SimpleIntegerProperty(sellingprice);
            this.discount=new SimpleIntegerProperty(discount);
            this.edition=new SimpleStringProperty(edition);
            this.vat=new SimpleStringProperty(vat);
            
           // this.author.getItems().addAll("hllo","hii");
            
            items= FXCollections.observableArrayList();
            //final ListView list = new ListView(items);
            this.author=new ListView<>(items);

// This sets the initial height of the ListView:
            author.setPrefHeight(items.size()*ROW_HEIGHT);
            this.select = new CheckBox();
            this.amountt=new JFXTextField(amountt);
            this.amountt.setLabelFloat(true);
            
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

        public String getEdition() {
            return edition.get();
        }
        public String getVat()
        {
            return vat.get();
        }
    public ListView<String> getAuthor() {
        return author;
    }

    public void setAuthor(ListView<String> author) {
        this.author = author;
    }

    public CheckBox getSelect() {
        return select;
    }

    public void setSelect(CheckBox select) {
        this.select = select;
    }

    public JFXTextField getAmountt() {
        return amountt;
    }

    public void setAmountt(JFXTextField amountt) {
        this.amountt = amountt;
    }
    public void settingauthor(String author)
    {
        items=FXCollections.observableArrayList(author);
    }
    
}
