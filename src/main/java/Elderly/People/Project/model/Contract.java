package Elderly.People.Project.model;


import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Contract {
    private String number;
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date dateBeginning;
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date dateEnding;
    private String description;
    private String quantityServices;
    private String unitsOfMeasure;
    private String priceUnit;

    public Contract() {
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String Number) {
        this.number = Number;
    }

    public Date getDateBeginning() {
        return dateBeginning;
    }

    public void setDateBeginning(Date dateBeginning) {
        this.dateBeginning = dateBeginning;
    }

    public Date getDateEnding() {
        return dateEnding;
    }

    public void setDateEnding(Date dateEnding) {
        this.dateEnding = dateEnding;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description= description;
    }

    public String getQuantityServices() {
        return quantityServices;
    }

    public void setQuantityServices(String quantityServices) {
        this.quantityServices = quantityServices;
    }
    
    public String getUnitsOfMeasure() {
        return unitsOfMeasure;
    }

    public void setUnitsOfMeasure(String unitsOfMeasure) {
        this.unitsOfMeasure = unitsOfMeasure;
    }

    public String getPriceUnit() {
        return priceUnit;
    }

    public void setPriceUnit(String priceUnit) {
        this.priceUnit = priceUnit;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "number='" + number + "\'" +
                ", dateBeginning='" + dateBeginning + "\'" +
                ", dateEnding='" + dateEnding + "\'" +
                ", description=" + description + "\'" +
                ", quantityServices='" + quantityServices + "\'" +
                ", unitsOfMeasure='" + unitsOfMeasure + "\'" +
                ", priceUnit='" + priceUnit + "\'" +
                "}";
    }


}
