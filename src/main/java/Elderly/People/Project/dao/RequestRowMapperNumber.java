package Elderly.People.Project.dao;
import Elderly.People.Project.model.Request;

import org.springframework.jdbc.core.RowMapper;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public final class RequestRowMapperNumber implements RowMapper<Request> {
    public Request mapRow(ResultSet rs, int rowNum) throws SQLException {

        Request request = new Request();
        request.setNumber(rs.getString("number"));


        return request;
    }

}