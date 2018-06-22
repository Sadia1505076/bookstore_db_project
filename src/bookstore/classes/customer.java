
package bookstore.classes;

public class customer {
    String custid;
    String custname; 
    String custemail;
    String custphone;
    String custadd;

    public customer(String custid, String custname, String custemail, String custphone, String custadd) {
        this.custid = custid;
        this.custname = custname;
        this.custadd = custadd;
        this.custemail = custemail;
        this.custphone = custphone;
    }

    public String getCustid() {
        return custid;
    }

    public void setCustid(String custid) {
        this.custid = custid;
    }

    public String getCustname() {
        return custname;
    }

    public void setCustname(String custname) {
        this.custname = custname;
    }

    public String getCustadd() {
        return custadd;
    }

    public void setCustadd(String custadd) {
        this.custadd = custadd;
    }

    public String getCustemail() {
        return custemail;
    }

    public void setCustemail(String custemail) {
        this.custemail = custemail;
    }

    public String getCustphone() {
        return custphone;
    }

    public void setCustphone(String custphone) {
        this.custphone = custphone;
    }
    
}
