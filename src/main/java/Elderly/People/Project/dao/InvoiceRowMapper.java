package Elderly.People.Project.dao;

import Elderly.People.Project.model.Invoice;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class InvoiceRowMapper implements RowMapper<Invoice> {
    public Invoice mapRow(ResultSet rs, int rowNum) throws SQLException {
        
        Invoice invoice = new Invoice();
        invoice.setNumber(rs.getString("number"));
        invoice.setNumberr(rs.getString("numberr"));
        invoice.setDate((rs.getDate("date")));
        invoice.setDNI(rs.getString("dni"));
        invoice.setAmount(rs.getInt("amount"));
        invoice.setConcept(rs.getString("concept"));



        return invoice;
    }

}