package ucl.ee.sec.mapper;

import org.apache.ibatis.annotations.*;
import ucl.ee.sec.entity.Product;

import java.util.List;

@Mapper
public interface ProductMapper {

    @Insert("INSERT INTO product(productid,productname,productnum) VALUES (${productid},${productname},${productnum});")
    @Options(useGeneratedKeys = true, keyProperty = "productid")
    @Deprecated
    int insertProduct(Product product);

    @ResultType(Product.class)
    @Select("SELECT * FROM product WHERE productid=${productid};")
    Product getProductById(@Param("productid") int productid);


    @Results(
            id = "productList", value = {
            @Result(property = "productid", column = "productid"),
            @Result(property = "productname", column = "productname"),
            @Result(property = "productnum", column = "productnum")
    }
    )
    @Select("SELECT * FROM product LIMIT 0,100;")
    Product getTopProduct();
//
//
//    @ResultMap("productList")
//    @Select("SELECT * FROM product LIMIT 0,${num};")
//    Product getTopProduct(@Param("num") int num);

    @ResultType(Integer.class)
    @Select("SELECT productnum FROM product WHERE productname=${productname};")
    Integer getProductNumByProductname(@Param("productname") String productname);

    //如果已经定义过@Results，可以直接用@ResultMap来调取
    @ResultMap("productList")
    @Select("SELECT * FROM product ORDER BY ${order_by_sql};")
    List<Product> getProductListOrderly(@Param("order_by_sql") String order_by_sql);

    @Deprecated
    @Delete("DELETE FROM product WHERE productid=${productid};")
    int deleteProductById(int productid);

    @Update("UPDATE product SET productnum=${productnum} where productid=${productid};")
    int updateProductNumById(@Param("productid") int productid, @Param("productnum") int productnum);
}
