package Elderly.People.Project.dao;

import Elderly.People.Project.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CompanyDao {

    private JdbcTemplate jdbcTemplate;


    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public void addCompany(Company company) {
        String sql = "INSERT INTO company (CIF, name, address, CPName, CPPhoneNumber, CPPersonEmail, userc, password, servicetype) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        this.jdbcTemplate.update(sql,
                company.getCIF(),
                company.getName(),
                company.getAddress(),
                company.getCPName(),
                company.getCPPhoneNumber(),
                company.getCPPersonEmail(),
                company.getUserc(),
                company.getPassword(),
                company.getServiceType()
        );
    }

    public void deleteCompany(String cif) {
        jdbcTemplate.update("DELETE FROM company WHERE cif = ?", cif);
    }

    public void updateCompany(Company company) {
        String sql = "UPDATE company SET name = ?, address = ?, CPName = ?, CPPhoneNumber = ?, CPPersonEmail = ?, userc = ?, password = ?, servicetype = ? WHERE cif = ?";
        this.jdbcTemplate.update(sql,
                company.getName(),
                company.getAddress(),
                company.getCPName(),
                company.getCPPhoneNumber(),
                company.getCPPersonEmail(),
                company.getUserc(),
                company.getPassword(),
                company.getServiceType(),
                company.getCIF()
        );
    }


    public Company getCompany(String cif) {
        String sql = "SELECT * FROM Company WHERE cif = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new CompanyRowMapper(), cif);
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
    }


    public List<Company> getCompanies() {
        String sql = "SELECT * FROM Company";
        try {
            List<Company> companies = jdbcTemplate.query(sql, new CompanyRowMapper());
            return companies;
        }
        catch(EmptyResultDataAccessException e) {
            return new ArrayList<Company>();
        }
    }




    public List<Company> getCompaniesElderly() {
        String sql = "SELECT DISTINCT servicetype FROM Company WHERE servicetype IS NOT NULL AND cif NOT IN (SELECT cif from contract)";
        try {
            List<Company> companies = jdbcTemplate.query(sql, new CompanyRowMapperElderly());
            return companies;
        }
        catch(EmptyResultDataAccessException e) {
            return new ArrayList<Company>();
        }
    }
}
