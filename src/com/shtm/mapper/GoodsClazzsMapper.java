package com.shtm.mapper;

import com.shtm.po.GoodsClazzs;
import com.shtm.po.GoodsClazzsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GoodsClazzsMapper {
    long countByExample(GoodsClazzsExample example);

    int deleteByExample(GoodsClazzsExample example);

    int deleteByPrimaryKey(Object id);

    int insert(GoodsClazzs record);

    int insertSelective(GoodsClazzs record);

    List<GoodsClazzs> selectByExample(GoodsClazzsExample example);

    GoodsClazzs selectByPrimaryKey(Object id);

    int updateByExampleSelective(@Param("record") GoodsClazzs record, @Param("example") GoodsClazzsExample example);

    int updateByExample(@Param("record") GoodsClazzs record, @Param("example") GoodsClazzsExample example);

    int updateByPrimaryKeySelective(GoodsClazzs record);

    int updateByPrimaryKey(GoodsClazzs record);
}