package Elderly.People.Project.model;

public class Firma {
    String dni ;
    String number;
    String number_r;

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber_r() {
        return number_r;
    }

    public void setNumber_r(String number_r) {
        this.number_r = number_r;
    }

    @Override
    public String toString() {
        return "Firma{" +
                "dni='" + dni + '\'' +
                ", number='" + number + '\'' +
                ", number_r='" + number_r + '\'' +
                '}';
    }
}