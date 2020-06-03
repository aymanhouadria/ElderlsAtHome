package Elderly.People.Project.model;

import java.util.Date;

public class SocialWorker {
    private String usercas;
    private String name;
    private String pwd;
    private String phonenumber;
    private String email;

    public String getUsercas() {
        return usercas;
    }

    public void setUsercas(String usercas) {
        this.usercas = usercas;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "SocialWorker{" +
                "usercas='" + usercas + '\'' +
                ", name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
