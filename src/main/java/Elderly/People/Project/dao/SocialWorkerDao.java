package Elderly.People.Project.dao;

import Elderly.People.Project.model.SocialWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SocialWorkerDao {

    private JdbcTemplate jdbcTemplate;


    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public void addSocialWorker(SocialWorker socialworker) {
        String sql = "INSERT INTO socialworker (usercas, name, pwd, phonenumber, email) values (?, ?, ?, ?, ?)";
        this.jdbcTemplate.update(sql,
                socialworker.getUsercas(),
                socialworker.getName(),
                socialworker.getPwd(),
                socialworker.getPhonenumber(),
                socialworker.getEmail()

        );
    }


    public void deleteSocialWorker(String usercas) {
        String sql = "DELETE FROM socialworker WHERE usercas = ?";
        this.jdbcTemplate.update(sql, usercas);
    }


    public void updateSocialWorker(SocialWorker socialworker) {
        String sql = "update socialworker set name = ?,   pwd = ?, address = ?, phonenumber = ?, email = ? WHERE usercas = ?";
        this.jdbcTemplate.update(sql,

                socialworker.getName(),
                socialworker.getPwd(),
                socialworker.getPhonenumber(),
                socialworker.getEmail(),
                socialworker.getUsercas()

        );
    }


    public SocialWorker getSocialWorker(String usercas) {
        String sql = "SELECT * FROM socialworker WHERE usercas = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{usercas}, new SocialWorkerRowMapper());
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
    }


    public List<SocialWorker> getSocialWorkers() {
        String sql = "SELECT * FROM socialworker";
        try {
            List<SocialWorker> socialworker = jdbcTemplate.query(sql, new SocialWorkerRowMapper());
            return socialworker;
        }
        catch(EmptyResultDataAccessException e) {
            return new ArrayList<SocialWorker>();
        }
    }
}