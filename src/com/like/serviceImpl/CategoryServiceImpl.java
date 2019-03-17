package com.like.serviceImpl;

import com.like.dao.CategoryDao;
import com.like.daoimpl.CategoryDaoImpl;
import com.like.domain.Category;
import com.like.service.CategoryService;
import com.like.utils.JedisUtils;
import net.sf.json.JSONArray;
import redis.clients.jedis.Jedis;

import java.sql.SQLException;
import java.util.List;

public class CategoryServiceImpl implements CategoryService
{
    @Override
    public String findAll() throws SQLException
    {
        CategoryDao    categoryDao = new CategoryDaoImpl();
        List<Category> all         = categoryDao.findAll();
        JSONArray      jsonArray   = JSONArray.fromObject(all);
        return jsonArray.toString();
    }

    public String findAllFromRedis() throws SQLException
    {
        Jedis  jedis = JedisUtils.getJedis();
        String nav   = jedis.get("nav");
        if (nav == null) {
            nav = findAll();
            jedis.set("nav", nav);
        }

        return nav;
    }
}
