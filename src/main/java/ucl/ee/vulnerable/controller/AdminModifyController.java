package ucl.ee.vulnerable.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ucl.ee.vulnerable.entity.Product;
import ucl.ee.vulnerable.mapper.ProductMapper;

@RestController
@Slf4j
public class AdminModifyController {

    @Autowired
    private ProductMapper productMapper;


    @GetMapping("admin_modify")
    public JSONObject modifyProduct(@RequestParam("productid") int productId, @RequestParam("changenum") int changeNum) {
        Product product = productMapper.getProductById(productId);
        int productNum = product.getProductnum() + changeNum;
        int result = productMapper.updateProductNumById(productId, productNum);
        JSONObject object = new JSONObject();
        object.put("status", result);
        return object;
    }

    @GetMapping("/admin_modify/")
    public Product getProductInfo(@RequestParam("productid") int productId) {
        Product product = productMapper.getProductById(productId);
        return product;
    }


}
