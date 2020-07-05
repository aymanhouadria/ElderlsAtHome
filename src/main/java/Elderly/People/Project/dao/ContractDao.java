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
        String sql = "INSERT INTO contract (number, dateBeginning, dateEnding, description, quantityServices, unitsOfMeasure, priceUnit,  cif) values (?, ?, ?, ?, ?, ?, ?, ?)";
        this.jdbcTemplate.update(sql,
                contract.getNumber(),
                contract.getDateBeginning(),
                contract.getDateEnding(),
                contract.getDescription(),
                contract.getQuantityServices(),
                contract.getUnitsOfMeasure(),
                contract.getPriceUnit(),
                contract.getCif()
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
        String sql = "UPDATE contract SET dateBeginning = ?, dateEnding = ?, description = ?, quantityServices = ?, unitsOfMeasure = ?, priceUnit = ?, cif = ? WHERE number = ?";
        this.jdbcTemplate.update(sql,
                contract.getDateBeginning(),
                contract.getDateEnding(),
                contract.getDescription(),
                contract.getQuantityServices(),
                contract.getUnitsOfMeasure(),
                contract.getPriceUnit(),
                contract.getCif(),

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


    public Contract getContractData() {

        String sql2 = "SELECT  cif FROM company WHERE company.cif NOT IN  (SELECT CIF from CONTRACT )";
        String sql3 =  "SELECT MAX(number) as number FROM contract ";
        Contract contract = new Contract();
        Contract contractAux = new Contract();



        try {
            contract = jdbcTemplate.queryForObject(sql3, new Object[]{}, new ContractRowMapperNumber());
            long numero = Long.parseLong(contract.getNumber()) + 1;
            String numeroAux = numero+"";
            contractAux.setNumber(numeroAux);
            contract = jdbcTemplate.queryForObject(sql2, new Object[]{}, new ContractRowMapperCif());
            contractAux.setCif(contract.getCif());

            return contractAux;

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
