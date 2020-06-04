package Elderly.People.Project.dao;

import Elderly.People.Project.model.Contract;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class ContractRowMapperCif implements RowMapper<Contract> {
    public Contract mapRow(ResultSet rs, int rowNum) throws SQLException {

        Contract contract = new Contract();

        contract.setCif(rs.getString("cif"));


        return contract;
    }

}