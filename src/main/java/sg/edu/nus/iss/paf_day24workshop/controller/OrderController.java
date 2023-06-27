package sg.edu.nus.iss.paf_day24workshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import sg.edu.nus.iss.paf_day24workshop.payload.OrderRequest;
import sg.edu.nus.iss.paf_day24workshop.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping
    public ResponseEntity<Boolean> createOrder(@RequestBody OrderRequest ordReq){

        Boolean result = orderService.makeOrder(ordReq.getOrder(), ordReq.getOrderDetails());

        return ResponseEntity.ok().body(result);
    }
    
}
