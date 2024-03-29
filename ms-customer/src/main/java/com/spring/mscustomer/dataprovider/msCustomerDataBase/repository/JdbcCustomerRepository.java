package com.spring.mscustomer.dataprovider.msCustomerDataBase.repository;

import com.spring.mscustomer.domain.model.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class JdbcCustomerRepository implements CustomerRepository{

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Optional<Customer> findById(Long id) {
        Customer customer = null;
        try {
            customer =  jdbcTemplate.queryForObject("SELECT * FROM tb_customer WHERE id=?",
                    BeanPropertyRowMapper.newInstance(Customer.class), id);

        } catch (IncorrectResultSizeDataAccessException e) {
            log.info("c=JdbcCustomerRepository m= findById id={}, customer not found", id);
        }

        return Optional.ofNullable(customer);
    }

    @Override
    public Optional<Customer> findByEmail(String email) {
        Customer customer = null;
        try {
            customer =  jdbcTemplate.queryForObject("SELECT * FROM tb_customer WHERE email=?",
                    BeanPropertyRowMapper.newInstance(Customer.class), email);

        } catch (IncorrectResultSizeDataAccessException e) {
            log.info("c=JdbcCustomerRepository m= findByEmail email={}, customer not found", email);
        }

        return Optional.ofNullable(customer);
    }

    @Override
    public Optional<Customer> findByDocument(String document) {
        Customer customer = null;
        try {
            customer =  jdbcTemplate.queryForObject("SELECT * FROM tb_customer WHERE document=?",
                    BeanPropertyRowMapper.newInstance(Customer.class), document);

        } catch (IncorrectResultSizeDataAccessException e) {
            log.info("c=JdbcCustomerRepository m= findByDocument document={}, customer not found", document);
        }

        return Optional.ofNullable(customer);
    }

    @Override
    public List<Customer> findAll() {
        String sql = "SELECT * FROM tb_customer";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Customer.class));
    }

    @Override
    public int save(Customer customer) {

        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO tb_customer (name, document, email, phone) VALUES(?,?,?,?)";

        int rowsAffected = jdbcTemplate.update(conn -> {
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getDocument());
            preparedStatement.setString(3, customer.getEmail());
            preparedStatement.setString(4, customer.getPhone());

            return preparedStatement;
        }, generatedKeyHolder);

        int generatedId = generatedKeyHolder.getKey().intValue();

        log.info("c=JdbcCustomerRepository, m=save, rowsAffected = {}, id={}", rowsAffected, generatedId);
        return generatedId;
    }

    @Override
    public int update(Customer customer) {
        int update =  jdbcTemplate.update("UPDATE tb_customer SET name=?, document=?, email=?, phone=? WHERE id=?",
                new Object[] {customer.getName(), customer.getDocument(), customer.getEmail(), customer.getPhone(), customer.getId()});

        if (update == 1){
            log.info("c=JdbcCustomerRepository m= update, customer with id={} was updated", customer.getId());
        }

        return update;
    }

    @Override
    public int delete(Long id) {
        return jdbcTemplate.update("DELETE FROM tb_customer WHERE id=?", id);
    }
}
