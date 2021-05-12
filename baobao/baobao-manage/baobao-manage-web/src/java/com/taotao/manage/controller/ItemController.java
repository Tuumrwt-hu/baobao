package com.taotao.manage.controller;

import com.taotao.manage.pojo.Item;
import com.taotao.manage.service.impl.ItemServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Qin PengCheng
 * @date 2018/5/15
 */
@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemServiceImpl itemServiceImpl;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> saveItem(@RequestParam("desc") String desc, Item item) {
        try {
            String title = item.getTitle();
            if (StringUtils.isBlank(title)) {
                //参数为空返回400
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            //保存商品信息
            this.itemServiceImpl.saveItemAndItemDesc(item, desc);
            //添加成功，返回201
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            e.printStackTrace();
            //异常返回500
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
