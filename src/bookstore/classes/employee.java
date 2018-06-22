
package bookstore.classes;

import java.sql.Date;

public class employee {
   String empid;
   String name;
   String designation;
   String address;
   String emailid;
   String phoneno;
   String salary;
   String commissionpct;
   Date Sdate;
   Date Edate;

    public employee(String empid, String name,String designation, String address, String emailid, String phoneno, String salary, String commissionpct,Date sdate,Date enddate) {
        this.empid = empid;
        this.name = name;
        this.designation=designation;
        this.address = address;
        this.emailid = emailid;
        this.phoneno = phoneno;
        this.salary = salary;
        this.commissionpct = commissionpct;
        this.Sdate = sdate;
       this.Edate = enddate;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
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
    
    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getCommissionpct() {
        return commissionpct;
    }

    public void setCommissionpct(String commissionpct) {
        this.commissionpct = commissionpct;
    }

//    public String getSdate() {
//        return Sdate;
//    }
//
//    public void setSdate(String Sdate) {
//        this.Sdate = Sdate;
//    }
//
//    public String getEdate() {
//        return Edate;
//    }
//
//    public void setEdate(String Edate) {
//        this.Edate = Edate;
//    }
       
}
