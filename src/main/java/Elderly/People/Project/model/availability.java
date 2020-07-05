package Elderly.People.Project.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class availability {

    private String userv;
    private String dni;
    private Date dia;
    private String HoraComienzo;
    private String HoraFinal;
    private Boolean state;

    public String getUserv() {
        return userv;
    }

    public void setUserv(String userv) {
        this.userv = userv;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }

    public String getHoraComienzo() {
        return HoraComienzo;
    }

    public void setHoraComienzo(String horaComienzo) {
        HoraComienzo = horaComienzo;
    }

    public String getHoraFinal() {
        return HoraFinal;
    }

    public void setHoraFinal(String horaFinal) {
        HoraFinal = horaFinal;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "availability{" +
                "userv='" + userv + '\'' +
                ", dni='" + dni + '\'' +
                ", dia=" + dia +
                ", HoraComienzo='" + HoraComienzo + '\'' +
                ", HoraFinal='" + HoraFinal + '\'' +
                ", state=" + state +
                '}';
    }
}