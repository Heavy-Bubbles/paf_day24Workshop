package sg.edu.nus.iss.paf_day24workshop.model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Integer orderId;

    private Date orderDate;

    private String customerName;

    private String shipAddress;

    private String notes;

    private Float tax;
    
}
