package Elderly.People.Project.dao;

import Elderly.People.Project.model.Company;
import Elderly.People.Project.model.Contract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ContractDao {

    private JdbcTemplate jdbcTemplate;


    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public void addContract(/*Company company, */Contract contract) {
        String sql = "INSERT INTO contract (number, dateBeginning, dateEnding, description, quantityServices, unitsOfMeasure, priceUnit) values (?, ?, ?, ?, ?, ?, ?)";
        this.jdbcTemplate.update(sql,
                contract.getNumber(),
                contract.getDateBeginning(),
                contract.getDateEnding(),
                contract.getDescription(),
                contract.getQuantityServices(),
                contract.getUnitsOfMeasure(),
                contract.getPriceUnit()
        );
/*
        sql = "INSERT INTO signs (numberc, name) values (?, ?)";
        this.jdbcTemplate.update(sql,
                contract.getNumber(),
                company.getCIF()
        );

 */
    }


    public void deleteContract(String number) {
        String sql = "DELETE FROM contract WHERE number = ?";
        this.jdbcTemplate.update(sql, number);
    }


    public void updateContract(Contract contract) {
        String sql = "UPDATE contract SET dateBeginning = ?, dateEnding = ?, description = ?, quantityServices = ?, unitsOfMeasure = ?, priceUnit = ? WHERE number = ?";
        this.jdbcTemplate.update(sql,
                contract.getDateBeginning(),
                contract.getDateEnding(),
                contract.getDescription(),
                contract.getQuantityServices(),
                contract.getUnitsOfMeasure(),
                contract.getPriceUnit(),
                contract.getNumber()
        );
    }


    public Contract getContract(String numContract) {
        String sql = "SELECT * FROM contract WHERE number = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{numContract}, new ContractRowMapper());
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
    }


    public List<Contract> getContracts() {
        String sql = "SELECT * FROM contract";
        try {
            List<Contract> contracts = jdbcTemplate.query(sql, new ContractRowMapper());
            return contracts;
        }
        catch(EmptyResultDataAccessException e) {
            return new ArrayList<Contract>();
        }
    }
}
