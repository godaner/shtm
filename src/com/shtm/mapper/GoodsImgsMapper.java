package com.shtm.mapper;

import com.shtm.po.GoodsImgs;
import com.shtm.po.GoodsImgsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GoodsImgsMapper {
    long countByExample(GoodsImgsExample example);

    int deleteByExample(GoodsImgsExample example);

    int deleteByPrimaryKey(String id);

    int insert(GoodsImgs record);

    int insertSelective(GoodsImgs record);

    List<GoodsImgs> selectByExample(GoodsImgsExample example);

    GoodsImgs selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") GoodsImgs record, @Param("example") GoodsImgsExample example);

    int updateByExample(@Param("record") GoodsImgs record, @Param("example") GoodsImgsExample example);

    int updateByPrimaryKeySelective(GoodsImgs record);

    int updateByPrimaryKey(GoodsImgs record);
}