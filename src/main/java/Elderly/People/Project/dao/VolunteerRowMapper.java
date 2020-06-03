package Elderly.People.Project.dao;
import Elderly.People.Project.model.Volunteer;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class VolunteerRowMapper implements RowMapper<Volunteer> {
    public Volunteer mapRow(ResultSet rs, int rowNum) throws SQLException {
        Volunteer volunteer = new Volunteer();
        volunteer.setUser(rs.getString("userv"));
        volunteer.setPwd(rs.getString("pwd"));
        volunteer.setAddress(rs.getString("address"));
        volunteer.setName(rs.getString("name"));
        volunteer.setPhoneNumber(rs.getString("phonenumber"));
        volunteer.setEmail(rs.getString("email"));
        volunteer.setHobbies(rs.getString("hobbies"));
        volunteer.setApplicationDate(rs.getDate("applicationdate"));
        volunteer.setAcceptationDate(rs.getDate("acceptationdate"));
        volunteer.setAccepted(rs.getBoolean("accepted"));
        volunteer.setBirthDate(rs.getDate("birthdate"));

        return volunteer;
    }

}