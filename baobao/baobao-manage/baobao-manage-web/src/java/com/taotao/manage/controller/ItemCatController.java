package com.taotao.manage.controller;

import com.taotao.manage.pojo.ItemCat;
import com.taotao.manage.service.ItemCatService;
import com.taotao.manage.service.impl.ItemCatServiceImpl;
import com.taotao.manage.vo.ItemCatVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Qin PengCheng
 * @date 2018/5/13
 */


@Controller
@RequestMapping("/item/cat")
public class ItemCatController {

    @Autowired
    private ItemCatServiceImpl itemCatService;

    /**
     * 根据父id查询数据
     *
     * @param id
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ItemCatVO>> queryItemCatByParentId(@RequestParam(name = "id", defaultValue = "0") Long id) {
        //判断传入的参数是否小于0
        if (id < 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        List<ItemCat> list = null;
        List<ItemCatVO> arrayList = null;
        try {
            ItemCat itemCat = new ItemCat();
            itemCat.setParentId(id);
            list = this.itemCatService.queryListByWhere(itemCat);
            arrayList = new ArrayList<>();
            for (ItemCat cat : list) {
                ItemCatVO itemCatVO = new ItemCatVO();
                itemCatVO.setId(cat.getId());
                itemCatVO.setState(cat.getState());
                itemCatVO.setText(cat.getText());
                arrayList.add(itemCatVO);
            }
            if (list.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.status(HttpStatus.OK).body(arrayList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
}
