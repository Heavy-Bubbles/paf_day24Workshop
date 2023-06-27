package sg.edu.nus.iss.paf_day24workshop.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.paf_day24workshop.exception.ResourceNotFoundException;
import sg.edu.nus.iss.paf_day24workshop.model.Order;

@Repository
public class OrderRepo {
    @Autowired
    JdbcTemplate jdbctemplate;

    private final String findAllSQL = "select * from orders";
    private final String findByIdSQL = "select * from orders where order_id = ?";
    private final String insertSQL = "insert into orders (order_date, customer_name, ship_address, notes, tax) values (?, ?, ?, ?, ?)";

    public Order findOrderById (Integer orderId){
        List<Order> orders = jdbctemplate.query(findByIdSQL, 
            BeanPropertyRowMapper.newInstance(Order.class), orderId);

        if (orders.isEmpty()){
            // throw custom exception
            throw new ResourceNotFoundException("Order not found.");
        }

        return orders.get(0);
    }

    public List<Order> findAllOrders(){
        List<Order> orders = jdbctemplate.query(findAllSQL, 
            BeanPropertyRowMapper.newInstance(Order.class));

        if (orders.isEmpty()){
            // throw custom exception
            throw new ResourceNotFoundException("Order not found.");
        }

        return orders;
    }

    public Boolean createOrder(Order order){
        Integer result = jdbctemplate.update(insertSQL, order.getOrderDate(),
            order.getCustomerName(), order.getShipAddress(),
            order.getNotes(), order.getTax());
        
        return result > 0 ? true : false;
    }

    public Integer insertOrder(Order order){
        KeyHolder generatedKey = new GeneratedKeyHolder();

        PreparedStatementCreator psc = new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
               PreparedStatement ps = con.prepareStatement(insertSQL, new String[] {"order_id"});
               ps.setDate(1, order.getOrderDate());
               ps.setString(2, order.getCustomerName());
               ps.setString(3, order.getShipAddress());
               ps.setString(4, order.getNotes());
               ps.setFloat(5, order.getTax());
               return ps;
            }
            
        };

        jdbctemplate.update(psc, generatedKey);

        Integer returnValue = generatedKey.getKey().intValue();
        return returnValue;
    }
}
