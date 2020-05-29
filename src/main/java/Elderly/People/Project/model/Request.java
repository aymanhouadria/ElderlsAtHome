package Elderly.People.Project.model;


import java.util.Date;

public class Request {
    private String number;
    private String DNI;
    private String numberc;
    private String serviceType;
    private Date creationDate;
    private String state;
    private Date approvedDate;
    private Date rejectedDate;
    private String comments;
    private Date endDate;


    public Request() {
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number =number;
    }

    public String getDNI() { return DNI; }

    public void setDNI(String DNI) { this.DNI = DNI; }

    public String getNumberc() {
        return numberc;
    }

    public void setNumberc(String numberc) {
        this.numberc = numberc;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getApprovedDate() {
        return approvedDate;
    }

    public void setApprovedDate(Date approvedDate) {
        this.approvedDate = approvedDate;
    }

    public Date getRejectedDate() {
        return rejectedDate;
    }

    public void setRejectedDate(Date rejectedDATE) {
        this.rejectedDate = rejectedDATE;
    }
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Request{" +
                "number='" + number + '\'' +
                ", DNI='" + DNI + '\'' +
                ", numberc='" + numberc + '\'' +
                ", serviceType='" + serviceType + '\'' +
                ", creationDate=" + creationDate +
                ", state='" + state + '\'' +
                ", approvedDate=" + approvedDate +
                ", rejectedDate=" + rejectedDate +
                ", comments='" + comments + '\'' +
                ", endDate=" + endDate +
                '}';
    }
}