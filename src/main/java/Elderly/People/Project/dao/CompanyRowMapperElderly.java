package Elderly.People.Project.dao;

import Elderly.People.Project.model.Company;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class CompanyRowMapperElderly implements RowMapper<Company> {
    public Company mapRow(ResultSet rs, int rowNum) throws SQLException {
        Company company = new Company();
        company.setServiceType(rs.getString("servicetype"));

        return company;
    }
}