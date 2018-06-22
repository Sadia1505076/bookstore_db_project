
package bookstore.classes;

public class publisher {
    String pubid;
    String compname;
    String add;
    String phoneno;
    String email;

    public publisher(String pubid, String compname, String add, String phoneno, String email) {
        this.pubid = pubid;
        this.compname = compname;
        this.add = add;
        this.phoneno = phoneno;
        this.email = email;
    }

    public String getPubid() {
        return pubid;
    }

    public void setPubid(String pubid) {
        this.pubid = pubid;
    }

    public String getCompname() {
        return compname;
    }

    public void setCompname(String compname) {
        this.compname = compname;
    }

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}
