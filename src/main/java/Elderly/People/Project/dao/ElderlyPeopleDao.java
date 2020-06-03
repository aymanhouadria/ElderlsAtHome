package Elderly.People.Project.dao;

import Elderly.People.Project.model.ElderlyPeople;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ElderlyPeopleDao {

    private JdbcTemplate jdbcTemplate;


    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public void addElderlyPeople(ElderlyPeople elderlyPeople) {
        String sql = "INSERT INTO elderly (dni, name, surname, address, birthdate, phonenumber,bankaccountnumber, email, userpwd, datecreation, alergies, diseases, usercas) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        this.jdbcTemplate.update(sql,
                elderlyPeople.getDNI(),
                elderlyPeople.getName(),
                elderlyPeople.getSurname(),
                elderlyPeople.getAddress(),
                elderlyPeople.getBirthDate(),
                elderlyPeople.getPhoneNumber(),
                elderlyPeople.getBankAccountNummber(),
                elderlyPeople.getEmail(),
                elderlyPeople.getUserPwd(),
                elderlyPeople.getDateCreation(),
                elderlyPeople.getAlergies(),
                elderlyPeople.getDiseases(),
                elderlyPeople.getUserCas()
        );
    }


   public void deleteElderlyPeople(String dni) {
        String sql = "DELETE FROM elderly WHERE dni = ?";
        this.jdbcTemplate.update(sql, dni);
    }


   public void updateElderlyPeople(ElderlyPeople elderlyPeople) {
        String sql = "update elderly set name = ?,   surname = ?, address = ?, birthdate = ?,  phonenumber = ?, bankaccountnumber = ?, email = ?, userpwd = ?, datecreation = ?, alergies = ?, diseases = ?, usercas = ? WHERE dni = ?";
        this.jdbcTemplate.update(sql,
                elderlyPeople.getName(),
                elderlyPeople.getSurname(),
                elderlyPeople.getAddress(),
                elderlyPeople.getBirthDate(),
                elderlyPeople.getPhoneNumber(),
                elderlyPeople.getBankAccountNummber(),
                elderlyPeople.getEmail(),
                elderlyPeople.getUserPwd(),
                elderlyPeople.getDateCreation(),
                elderlyPeople.getAlergies(),
                elderlyPeople.getDiseases(),
                elderlyPeople.getUserCas(),
                elderlyPeople.getDNI()
        );
    }


    public ElderlyPeople getElderlyPeople(String Dni) {
        String sql = "SELECT * FROM elderly WHERE dni = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{Dni}, new ElderlyPeopleRowMapper());
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
    }


    public List<ElderlyPeople> getElderlyPeople() {
        String sql = "SELECT * FROM elderly";
        try {
            List<ElderlyPeople> elderlies = jdbcTemplate.query(sql, new ElderlyPeopleRowMapper());
            return elderlies;
        }
        catch(EmptyResultDataAccessException e) {
            return new ArrayList<ElderlyPeople>();
        }
    }
}
