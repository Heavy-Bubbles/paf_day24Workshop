package sg.edu.nus.iss.paf_day24workshop.exception;

import java.util.Date;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiError {
    
    private int status;
    private Date timeStamp;
    private String message;
}
