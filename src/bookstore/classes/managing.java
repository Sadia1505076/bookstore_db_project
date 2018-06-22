 
package bookstore.classes;

import java.sql.Date;

public class managing {
    Integer manageid;
    Integer managerid;
    Integer staffid;
    Date startdate;
    Date enddate;

    public managing(Integer manageid, Integer managerid, Integer staffid, Date startdate, Date enddate) {
        this.manageid = manageid;
        this.managerid = managerid;
        this.staffid = staffid;
        this.startdate = startdate;
        this.enddate = enddate;
    }

    public Integer getManageid() {
        return manageid;
    }

    public void setManageid(Integer manageid) {
        this.manageid = manageid;
    }

    public Integer getManagerid() {
        return managerid;
    }

    public void setManagerid(Integer managerid) {
        this.managerid = managerid;
    }

    public Integer getStaffid() {
        return staffid;
    }

    public void setStaffid(Integer staffid) {
        this.staffid = staffid;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }
    
            
}
