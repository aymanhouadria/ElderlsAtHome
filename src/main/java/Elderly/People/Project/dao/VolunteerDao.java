package Elderly.People.Project.dao;

import Elderly.People.Project.model.Volunteer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class VolunteerDao {

    private JdbcTemplate jdbcTemplate;


    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void addVolunteer(Volunteer volunteer) {
        String sql = "insert into volunteer (userv, pwd, address, name, phonenumber, email, hobbies, applicationdate, acceptationdate, accepted, birthdate) values (?, ?, ?, ?, ?, ?, ?, current_date , ?, ?, ?)";
        this.jdbcTemplate.update(sql,
                volunteer.getUser(),
                volunteer.getPwd(),
                volunteer.getAddress(),
                volunteer.getName(),
                volunteer.getPhoneNumber(),
                volunteer.getEmail(),
                volunteer.getHobbies(),
                volunteer.getAcceptationDate(),
                volunteer.getAccepted(),
                volunteer.getBirthDate()
        );
    }

    public void deleteVolunteer(String userv) {
        String sql = "delete from volunteer where userv = ?";
        this.jdbcTemplate.update(sql, userv);
    }



    public void resolveVolunteer(String userv) {
        String sql = "update volunteer set   acceptationdate = current_date , accepted = True  WHERE userv = ?";
        this.jdbcTemplate.update(sql, userv);
    }



    public void updateVolunteer(Volunteer volunteer) {
        String sql = "update volunteer set  pwd = ?, address = ?, name = ?, phonenumber = ?, email = ?, hobbies = ?, applicationdate = ?, acceptationdate = current_date , accepted = True, birthdate= ? WHERE user = ?";
        this.jdbcTemplate.update(sql,
                volunteer.getPwd(),
                volunteer.getAddress(),
                volunteer.getName(),
                volunteer.getPhoneNumber(),
                volunteer.getEmail(),
                volunteer.getHobbies(),
                volunteer.getApplicationDate(),
                volunteer.getAcceptationDate(),
                volunteer.getAccepted(),
                volunteer.getBirthDate(),
                volunteer.getUser()

        );
    }


    public Volunteer getVolunteer(String userv) {
        String sql = "SELECT * FROM volunteer WHERE userv = ?";

        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{userv}, new VolunteerRowMapper());
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
    }


   public List<Volunteer> getVolunteers() {
        String sql = "SELECT * FROM volunteer where accepted IS null ";
        try {
            List<Volunteer> volunteers = jdbcTemplate.query(sql, new VolunteerRowMapper());
            return volunteers;
        }

        catch(EmptyResultDataAccessException e) {
            return new ArrayList<Volunteer>();
        }
    }


    public List<Volunteer> getVolunteersElderly() {
        String sql = "SELECT * FROM volunteer where accepted is True";
        try {
            List<Volunteer> volunteers = jdbcTemplate.query(sql, new VolunteerRowMapper());
            return volunteers;
        }

        catch(EmptyResultDataAccessException e) {
            return new ArrayList<Volunteer>();
        }
    }


}
