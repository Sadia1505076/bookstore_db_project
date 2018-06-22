
package bookstore.classes;

import java.sql.Date;

public class manager {
    String managerid;
    String name;
    String designation;
    String address;
    String email;
    String phoneno ;
    Integer salary;
    Integer commpct;
    Date Sdate;
    Date Edate;


    public manager(String managerid, String name, String designation, String address, String email, String phoneno, Integer salary, Integer commpct,Date sdate,Date enddate) {
        this.managerid = managerid;
        this.name = name;
        this.designation = designation;
        this.address = address;
        this.email = email;
        this.phoneno = phoneno;
        this.salary = salary;
        this.commpct = commpct;
        this.Sdate = sdate;
        this.Edate = enddate;
  
    }

    public Date getSdate() {
        return Sdate;
    }

    public void setSdate(Date Sdate) {
        this.Sdate = Sdate;
    }

    public Date getEdate() {
        return Edate;
    }

    public void setEdate(Date Edate) {
        this.Edate = Edate;
    }
   
    public String getManagerid() {
        return managerid;
    }

    public void setManagerid(String managerid) {
        this.managerid = managerid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Integer getCommpct() {
        return commpct;
    }

    public void setCommpct(Integer commpct) {
        this.commpct = commpct;
    }
    
    
}
