package com.taotao.manage.service;

import com.taotao.manage.pojo.ItemCat;

import java.util.List;

/**
 * @author Qin PengCheng
 * @date 2018/5/13
 */
public interface ItemCatService {
    /**
     * 查询商品分类列表的方法
     * @param id
     * @return
     */
    List<ItemCat> queryItemCatByParentId(Long id);
}
