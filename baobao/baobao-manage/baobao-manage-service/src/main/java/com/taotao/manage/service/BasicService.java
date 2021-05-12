package com.taotao.manage.service;


import com.github.abel533.entity.Example;
import com.github.abel533.mapper.Mapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.manage.pojo.BasePojo;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author Qin PengCheng
 * @date 2018/5/13
 */
public abstract class BasicService<T extends BasePojo> {

    @Autowired
    private Mapper<T> mapper;

    private Class<T> clazz;

    {
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        this.clazz = (Class<T>) type.getActualTypeArguments()[0];
    }

    /**
     * 1、queryById,根据主键查询信息
     */
    public T queryById(Long id) {
        return this.mapper.selectByPrimaryKey(id);
    }

    /**
     * queryAll
     * @return
     */
    public List<T> queryAll(){
        return this.mapper.select(null);
    }


    /**
     * queryOne,查询一条数据
     * @param record
     * @return
     */
    public T queryOne(T record){
        return this.mapper.selectOne(record);
    }

    /**
     * 根据条件查询数据
     * @param record
     * @return
     */
    public List<T> queryListByWhere(T record){
        return this.mapper.select(record);
    }

    /**
     * 根据条件分页查询数据
     * @return
     */
    public PageInfo<T> queryPageListByWhere(Integer page, Integer rows){
        //开始分页查询,拦截器开始起作用
        PageHelper.startPage(page,rows);
        //查询数据
        List<T> list = this.queryAll();
        return new PageInfo<>(list);
    }

    /**
     * 新增数据
     * @param record
     * @return
     */
    public Integer save(T record){
        Date date = new Date();
        record.setCreated(date);
        record.setUpdated(date);
        return this.mapper.insert(record);
    }

    /**
     * 更新数据
     * @param record
     * @return
     */
    public Integer update(T record){
        Date date = new Date();
        record.setUpdated(date);
        Example example = new Example(clazz);
        return this.mapper.updateByExampleSelective(record,example);
    }

    /**
     * 根据id删除数据
     * @param id
     * @return
     */
    public Integer deleteById(Long id){
      return this.mapper.deleteByPrimaryKey(id);
    }


    /**
     * 通过id删除
     * @param properties
     * @param ids
     * @return
     */
    public Integer deleteByIds(String properties,Long[] ids){
        //删除多个数据，应该用in
        List<Object> list = new ArrayList<>();
        Collections.addAll(list,ids);
        Example example = new Example(clazz);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn(properties, list);
        return this.mapper.deleteByExample(example);
    }


    /**
     * 通过条件删除
     * @param record
     * @return
     */
    public Integer deleteByWhere(T record){
        return this.mapper.delete(record);
    }
}
