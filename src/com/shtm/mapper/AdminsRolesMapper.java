package com.shtm.mapper;

import com.shtm.po.AdminsRoles;
import com.shtm.po.AdminsRolesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdminsRolesMapper {
    long countByExample(AdminsRolesExample example);

    int deleteByExample(AdminsRolesExample example);

    int deleteByPrimaryKey(Object id);

    int insert(AdminsRoles record);

    int insertSelective(AdminsRoles record);

    List<AdminsRoles> selectByExample(AdminsRolesExample example);

    AdminsRoles selectByPrimaryKey(Object id);

    int updateByExampleSelective(@Param("record") AdminsRoles record, @Param("example") AdminsRolesExample example);

    int updateByExample(@Param("record") AdminsRoles record, @Param("example") AdminsRolesExample example);

    int updateByPrimaryKeySelective(AdminsRoles record);

    int updateByPrimaryKey(AdminsRoles record);
}