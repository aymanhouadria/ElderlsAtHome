package Elderly.People.Project.dao;

import Elderly.People.Project.model.ElderlyPeople;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class ElderlyPeopleRowMapper implements RowMapper<ElderlyPeople> {
    public ElderlyPeople mapRow(ResultSet rs, int rowNum) throws SQLException {

        ElderlyPeople elderly = new ElderlyPeople();
        elderly.setDNI(rs.getString("dni"));
        elderly.setName(rs.getString("name"));
        elderly.setSurname(rs.getString("surname"));
        elderly.setAddress(rs.getString("address"));
        elderly.setBirthDate((rs.getDate("birthDate")));
        elderly.setPhoneNumber(rs.getString("phonenumber"));
        elderly.setBankAccountNummber(rs.getString("bankaccountnumber"));
        elderly.setEmail(rs.getString("email"));
        elderly.setUserPwd(rs.getString("userPwd"));
        elderly.setDateCreation((rs.getDate("dateCreation")));
        elderly.setAlergies(rs.getString("alergies"));
        elderly.setDiseases(rs.getString("diseases"));
        elderly.setUserCas(rs.getString("usercas"));

        return elderly;
    }
}