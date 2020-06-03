package Elderly.People.Project.model;


import java.time.LocalDate;
import java.util.Date;

public class Invoice {
    private String number;
    private String numberr;
    private Date date;
    private String DNI;
    private int amount;
    private String concept;


    public Invoice() {
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number =number;
    }

    public String getNumberr() {
        return numberr;
    }

    public void setNumberr(String numberr) {
        this.numberr = numberr;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "number='" + number + '\'' +
                ", numberr='" + numberr + '\'' +
                ", date=" + date +
                ", DNI='" + DNI + '\'' +
                ", amount=" + amount +
                ", concept='" + concept + '\'' +
                '}';
    }
}