package com.taotao.manage.service.impl;

import com.taotao.manage.mapper.ItemCatMapper;
import com.taotao.manage.pojo.ItemCat;
import com.taotao.manage.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.taotao.manage.service.BasicService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Qin PengCheng
 * @date 2018/5/13
 */
@Service("ItemCatService")
public class ItemCatServiceImpl  extends  BasicService<ItemCat>    {

    @Autowired
    private ItemCatMapper itemCatMapper;



    /**
     * 查询商品分类
     * @param id
     * @return
     */

    public List<ItemCat> queryItemCatByParentId(Long id) {
        /*ItemCat itemCat = new ItemCat();
        itemCat.setParentId(id);
        List<ItemCat> list = itemCatMapper.select(itemCat);
        return list;*/
        ItemCat itemCat = new ItemCat();
        itemCat.setParentId(id);
        List<ItemCat> list = this.queryListByWhere(itemCat);
        return list;
    }
}
