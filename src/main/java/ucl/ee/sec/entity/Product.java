package ucl.ee.sec.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private int productid;
    private String productname;
    private int productnum;


}


