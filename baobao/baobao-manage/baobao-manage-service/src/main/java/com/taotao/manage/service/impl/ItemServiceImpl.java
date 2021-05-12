package com.taotao.manage.service.impl;

import com.taotao.manage.pojo.Item;
import com.taotao.manage.pojo.ItemDesc;
import com.taotao.manage.service.BasicService;
import com.taotao.manage.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Qin PengCheng
 * @date 2018/5/15
 */

@Service("itemService")
public class ItemServiceImpl extends BasicService<Item> {

    @Autowired
    private ItemDescServiceImpl itemDescServiceImpl;


    public void saveItemAndItemDesc(Item item, String desc) {
        //防止人为破坏，传一个id，导致数据库id混乱，将id设置为null
        item.setId(null);
        //初始化商品状态
        item.setStatus(1);
        //保存商品信息
        this.save(item);
        //通过mybatis的id回显功能，得到商品的id
        Long id = item.getId();
        ItemDesc itemDesc = new ItemDesc();
        itemDesc.setItemId(id);
        itemDesc.setItemDesc(desc);
        this.itemDescServiceImpl.save(itemDesc);
    }
}
