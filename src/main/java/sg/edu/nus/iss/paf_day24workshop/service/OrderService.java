package sg.edu.nus.iss.paf_day24workshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.nus.iss.paf_day24workshop.model.Order;
import sg.edu.nus.iss.paf_day24workshop.model.OrderDetails;
import sg.edu.nus.iss.paf_day24workshop.repository.OrderDetailsRepo;
import sg.edu.nus.iss.paf_day24workshop.repository.OrderRepo;

@Service
public class OrderService {
    
    @Autowired
    OrderRepo orderRepo;

    @Autowired
    OrderDetailsRepo orderDetailsRepo;

    @Transactional
    public Boolean makeOrder(Order order, List<OrderDetails> details){

        // // simulate error before erforming any actions
        // if (true){
        //     throw new IllegalArgumentException("Exception before performing any action");
        // }
        
        // 1. create the order and get the returned primary key of the order
        Integer createdOrderId = orderRepo.insertOrder(order);

        // simulate error after creating a new order.

        // 2. set the fk for the order details that links to the created order
        for (OrderDetails od : details){
            od.setOrderId(createdOrderId);
        }

        // 3. create the order details
        // int [] iAdded = orderDetailsRepo.add(details);
        orderDetailsRepo.add(details);

        return true;

    }
}
