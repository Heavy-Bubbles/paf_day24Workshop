package sg.edu.nus.iss.paf_day24workshop.payload;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import sg.edu.nus.iss.paf_day24workshop.model.Order;
import sg.edu.nus.iss.paf_day24workshop.model.OrderDetails;

@Data
@NoArgsConstructor
public class OrderRequest {
    
    private Order order;

    private List<OrderDetails> orderDetails;
}
