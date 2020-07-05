package Elderly.People.Project.dao;

import Elderly.People.Project.model.availability;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class AvailabilityUserRowMapper implements RowMapper<availability> {
    public availability mapRow(ResultSet rs, int rowNum) throws SQLException {

        availability contract = new availability();
        contract.setUserv(rs.getString("userv"));




        return contract;
    }

}