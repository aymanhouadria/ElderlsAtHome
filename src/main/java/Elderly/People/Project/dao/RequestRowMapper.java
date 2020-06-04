package Elderly.People.Project.dao;
import Elderly.People.Project.model.Request;

import org.springframework.jdbc.core.RowMapper;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public final class RequestRowMapper implements RowMapper<Request> {
    public Request mapRow(ResultSet rs, int rowNum) throws SQLException {

        Request request = new Request();
        request.setNumber(rs.getString("number"));
        request.setDNI(rs.getString("dni"));
        request.setServiceType(rs.getString("servicetype"));
        request.setCreationDate(rs.getDate("creationdate"));
        request.setState(rs.getString("state"));
        request.setApprovedDate(rs.getDate("approveddate"));
        request.setRejectedDate(rs.getDate("rejecteddate"));
        request.setComments(rs.getString("comments"));
        request.setEndDate(rs.getDate("enddate"));

        return request;
    }

}