package Elderly.People.Project.dao;

import Elderly.People.Project.model.Contract;
import Elderly.People.Project.model.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RequestDao {

    private JdbcTemplate jdbcTemplate;
    private JdbcTemplate jdbcTemplate2;


    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void addRequest(Request request) {
        String sql = "insert into request (number, DNI,  servicetype, creationdate, state, approveddate, rejecteddate, comments, enddate) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        this.jdbcTemplate.update(sql,
                request.getNumber(),
                request.getDNI(),

                request.getServiceType(),
                request.getCreationDate(),
                request.getState(),
                request.getApprovedDate(),
                request.getRejectedDate(),
                request.getComments(),
                request.getEndDate()
        );
    }

    public void deleteRequest(String number) {
        String sql = "delete from request where number = ?";
        this.jdbcTemplate.update(sql, number);
    }

    public void updateRequest(Request request) {
        String sql = "update request set  servicetype = ?, creationdate = ?, state = ?, approveddate = ?, rejecteddate = ?, comments = ?, enddate = ? WHERE number = ?";
        String sql2 = "SELECT number from contract where cif = (SELECT max(cif) from company where servicetype = (SELECT servicetype from request WHERE number =(SELECT MAX(NUMBER) FROM REQUEST)) )";
        String sql3 = "insert into firma (DNI, number_C, number_r) values (?, ?, ?)";

        Contract contract = new Contract();







        this.jdbcTemplate.update(sql,

                request.getServiceType(),
                request.getCreationDate(),
                request.getState(),
                request.getApprovedDate(),
                request.getRejectedDate(),
                request.getComments(),
                request.getEndDate(),
                request.getNumber()

        );

        contract = this.jdbcTemplate.queryForObject(sql2, new Object[]{}, new ContractRowMapperNumber());

        this.jdbcTemplate.update(sql3,
                request.getDNI(),
                contract.getNumber(),
                request.getNumber()

        );


    }


    public Request getRequest(String reqNumber) {
        String sql = "SELECT * FROM request WHERE number = ?";

        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{reqNumber}, new RequestRowMapper());
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
    }


    public List<Request> getRequests() {
        String sql = "SELECT * FROM request";
        try {
            List<Request> requests = jdbcTemplate.query(sql, new RequestRowMapper());
            return requests;
        }

        catch(EmptyResultDataAccessException e) {
            return new ArrayList<Request>();
        }
    }


    public Request getRequest() {
        String sql = "SELECT MAX(number) as number FROM request ";
        Request request =  new Request();
        Request requestAux= new Request();
        try {
            request = jdbcTemplate.queryForObject(sql, new Object[]{}, new RequestRowMapperNumber());
            long numero = Long.parseLong(request.getNumber()) + 1;
            String numeroAux = numero+"";
            requestAux.setNumber(numeroAux);
            return requestAux;
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Request> getRequestsCas() {
        String sql = "SELECT * FROM request WHERE state IS NULL";
        try {
            List<Request> requests = jdbcTemplate.query(sql, new RequestRowMapper());

            return requests;
        }

        catch(EmptyResultDataAccessException e) {
            return new ArrayList<Request>();
        }
    }





}
