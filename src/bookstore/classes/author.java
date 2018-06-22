
package bookstore.classes;

import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.DatePicker;

public class author {
    JFXTextField authorname;
    JFXTextField nationality;
    DatePicker birthdate;
    //JFXButton birthdate;
    JFXTextField writingYear;
    String nullW;
    String nullB;

   
   public author(JFXTextField authorname,JFXTextField nationality,DatePicker birthdate,JFXTextField writingYear)
    {
        this.authorname=authorname;
        this.nationality=nationality;
        this.birthdate=birthdate;
        this.writingYear=writingYear;   
   }

    public String getNullW() {
        return nullW;
    }

    public void setNullW(String nullW) {
        this.nullW = nullW;
    }

    public String getNullB() {
        return nullB;
    }

    public void setNullB(String nullB) {
        this.nullB = nullB;
    }
    public JFXTextField getAuthorname() {
        return authorname;
    }

    public void setAuthorname(JFXTextField authorname) {
        this.authorname = authorname;
    }

    public JFXTextField getNationality() {
        return nationality;
    }

    public void setNationality(JFXTextField nationality) {
        this.nationality = nationality;
    }

    public DatePicker getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(DatePicker birthdate) {
        this.birthdate = birthdate;
    }

    public JFXTextField getWritingYear() {
        return writingYear;
    }

    public void setWritingYear(JFXTextField writingYear) {
        this.writingYear = writingYear;
    }
}
