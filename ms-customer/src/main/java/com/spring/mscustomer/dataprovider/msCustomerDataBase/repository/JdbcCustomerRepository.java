package com.spring.mscustomer.dataprovider.msCustomerDataBase.repository;

import com.spring.mscustomer.domain.model.Customer;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JdbcCustomerRepository implements CustomerRepository{

    private static final Logger LOG = LoggerFactory.getLogger(JdbcCustomerRepository.class);
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Optional<Customer> findById(Long id) {
        Customer customer = null;
        try {
            customer =  jdbcTemplate.queryForObject("SELECT * FROM customer WHERE id=?",
                    BeanPropertyRowMapper.newInstance(Customer.class), id);

        } catch (IncorrectResultSizeDataAccessException e) {
            LOG.info("Customer not found");
        }

        return Optional.ofNullable(customer);
    }

    @Override
    public Optional<Customer> findByDocument(String document) {
        Customer customer = null;
        try {
            customer =  jdbcTemplate.queryForObject("SELECT * FROM customer WHERE document=?",
                    BeanPropertyRowMapper.newInstance(Customer.class), document);

        } catch (IncorrectResultSizeDataAccessException e) {
            LOG.info("Customer not found");
        }

        return Optional.ofNullable(customer);
    }

    @Override
    public List<Customer> findAll() {
        String sql = "SELECT * FROM customer";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Customer.class));
    }

    @Override
    public int save(Customer customer) {
        int insert = jdbcTemplate.update("INSERT INTO customer (name, document, email, phone) VALUES(?,?,?,?)",
                new Object[]{customer.getName(), customer.getDocument(), customer.getEmail(), customer.getPhone()});

        if (insert == 1){
            LOG.info("New customer created");
        }

        return insert;
    }

    @Override
    public int update(Customer customer) {
        int update =  jdbcTemplate.update("UPDATE customer SET name=?, document=?, email=?, phone=? WHERE id=?",
                new Object[] {customer.getName(), customer.getDocument(), customer.getEmail(), customer.getPhone(), customer.getId()});

        if (update == 1){
            LOG.info("Customer with id "+customer.getId()+" was updated");
        }

        return update;
    }

    @Override
    public int delete(Long id) {
        return jdbcTemplate.update("DELETE FROM customer WHERE id=?", id);
    }
}
