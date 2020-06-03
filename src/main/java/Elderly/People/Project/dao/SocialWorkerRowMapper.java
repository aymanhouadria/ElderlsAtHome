package Elderly.People.Project.dao;

import Elderly.People.Project.model.SocialWorker;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class SocialWorkerRowMapper implements RowMapper<SocialWorker> {
    public SocialWorker mapRow(ResultSet rs, int rowNum) throws SQLException {

        SocialWorker socialworker = new SocialWorker();
        socialworker.setUsercas(rs.getString("usercas"));
        socialworker.setName(rs.getString("name"));
        socialworker.setPwd(rs.getString("pwd"));
        socialworker.setPhonenumber(rs.getString("phonenumber"));
        socialworker.setEmail(rs.getString("email"));


        return socialworker;
    }
}