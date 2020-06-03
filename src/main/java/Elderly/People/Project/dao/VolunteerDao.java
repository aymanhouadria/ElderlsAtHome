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
        String sql = "insert into volunteer (userv, pwd, address, name, phonenubmer, email, hobbies, applicationdate, acceptationdate, accepted, birth_date) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        this.jdbcTemplate.update(sql,
                volunteer.getUser(),
                volunteer.getPwd(),
                volunteer.getAddress(),
                volunteer.getName(),
                volunteer.getPhoneNumber(),
                volunteer.getEmail(),
                volunteer.getHobbies(),
                volunteer.getApplicationDate(),
                volunteer.getAcceptationDate(),
                volunteer.getAccepted(),
                volunteer.getBirthDate()
        );
    }

    public void deleteVolunteer(String userv) {
        String sql = "delete from volunteer where userv = ?";
        this.jdbcTemplate.update(sql, userv);
    }

    public void updateVolunteer(Volunteer volunteer) {
        String sql = "update request set  pwd = ?, address = ?, name = ?, phonenumber = ?, email = ?, hobbies = ?, applicationdate = ?, acceptationdate = ?, accepted = ?, birthdate= ?";
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
                volunteer.getBirthDate()
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
        String sql = "SELECT * FROM volunteer";
        try {
            List<Volunteer> volunteers = jdbcTemplate.query(sql, new VolunteerRowMapper());
            return volunteers;
        }

        catch(EmptyResultDataAccessException e) {
            return new ArrayList<Volunteer>();
        }
    }


}
