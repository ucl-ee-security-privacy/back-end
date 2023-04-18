package ucl.ee.vulnerable.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ucl.ee.vulnerable.mapper.ProductMapper;

@Controller
@Slf4j
public class AdminModifyController {

    @Autowired
    private ProductMapper productMapper;


    @GetMapping("admin_modify")
    @ResponseBody
    public JSONObject modifyProduct(@RequestParam("productid") int productId, @RequestParam("productnum") int productNum) {
        int result = productMapper.updateProductNumById(productId, productNum);
        JSONObject object = new JSONObject();
        object.put("status", result);
        return object;
    }


}
