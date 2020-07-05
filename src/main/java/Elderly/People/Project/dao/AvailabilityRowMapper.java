package Elderly.People.Project.dao;

import Elderly.People.Project.model.availability;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class AvailabilityRowMapper implements RowMapper<availability> {
    public availability mapRow(ResultSet rs, int rowNum) throws SQLException {

        availability contract = new availability();
        contract.setUserv(rs.getString("userv"));
        contract.setDni(rs.getString("dni"));
        contract.setDia((rs.getDate("dia")));
        contract.setHoraComienzo(rs.getString("horainicio"));
        contract.setHoraFinal(rs.getString("horafinal"));
        contract.setState(rs.getBoolean("disponible"));



        return contract;
    }

}