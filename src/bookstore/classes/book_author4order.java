
package bookstore.classes;

import com.jfoenix.controls.JFXTextField;
import java.util.ArrayList;
import java.util.Collections;

public class book_author4order {
    private JFXTextField bookname;
    private ArrayList<JFXTextField> authorname=new ArrayList<>();
    public book_author4order(JFXTextField book,JFXTextField author)
    {
        bookname=book;
        
        Collections.addAll(authorname,author);
        //
    }

    public JFXTextField getBookname() {
        return bookname;
    }

    public void setBookname(JFXTextField bookname) {
        this.bookname = bookname;
    }
    public void addingAuthor(JFXTextField author)
    {
        Collections.addAll(authorname, author);
    }

    public ArrayList<JFXTextField> getAuthorname() {
        return authorname;
    }
    public int gettingSize()
    {
        return authorname.size();
    }
    public JFXTextField gettingoneauthor(int index)
    {
        return authorname.get(index);
    }
}
