package com.taotao.manage.service;

import com.taotao.manage.pojo.Item;

/**
 * @author Qin PengCheng
 * @date 2018/5/15
 */
public interface ItemService {

    /**
     * 添加商品和商品描述
     * @param item
     * @param desc
     * @return
     */
     void  saveItemAndItemDesc(Item item,String desc);
}
