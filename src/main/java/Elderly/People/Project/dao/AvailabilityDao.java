package Elderly.People.Project.dao;

import Elderly.People.Project.model.Company;
import Elderly.People.Project.model.availability;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AvailabilityDao {

    private JdbcTemplate jdbcTemplate;


    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void addAvailability(availability availability) {
        String sql = "insert into availability (userv , dni, dia, horainicio, horafinal, disponible) values (?, ?, ?, ?, ?, FALSE )";
        this.jdbcTemplate.update(sql,
                availability.getUserv(),
                availability.getDni(),
                availability.getDia(),
                availability.getHoraComienzo(),
                availability.getHoraFinal(),
                availability.getState()
        );
    }

    public void deleteAvailability(String userv) {
        String sql = "delete from availability where userv = ?";
        this.jdbcTemplate.update(sql, userv);
    }







    public void updateAvailability(availability availability) {
        String sql = "update availability set  dni= ?, disponible = False WHERE userv = ?";
        this.jdbcTemplate.update(sql,

                availability.getDni(),
                availability.getState(),
                availability.getUserv()

        );
    }

    public availability getAvailability(String userv) {
        String sql = "SELECT * FROM availability WHERE userv = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new AvailabilityRowMapper(), userv);
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    public availability getAvailability() {
        String sql = "SELECT userv FROM volunteer WHERE  acceptationdate = (SELECT max(ACCEPTATIONDATE) FROM volunteer)";
        try {
            availability availability= jdbcTemplate.queryForObject(sql, new AvailabilityRowMapper());
            return availability;
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
    }




    public List<availability> getAvailabilities() {
        String sql = "SELECT * FROM availability where disponible IS NOT True ";
        try {
            List<availability> availabilities = jdbcTemplate.query(sql, new AvailabilityRowMapper());
            return availabilities;
        }

        catch(EmptyResultDataAccessException e) {
            return new ArrayList<availability>();
        }
    }




}
