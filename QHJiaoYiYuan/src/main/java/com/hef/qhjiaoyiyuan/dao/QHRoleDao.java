package com.hef.qhjiaoyiyuan.dao;

import com.hef.qhjiaoyiyuan.bean.QHRole;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

/**
 * 角色管理的Dao
 *
 * @Date 2020/3/8
 * @Author lifei
 */
@Mapper
public interface QHRoleDao {


    @Select({"select t1.r_id, t1.role_type,t1.role_name from qh_roles t1 " +
            " inner join qh_user_roles t2 on t1.r_id=t2.r_id where t2.u_id=#{uId}"})
    @Results(value = {@Result(column = "r_id", property = "rId", jdbcType = JdbcType.INTEGER),
            @Result(column = "role_type", property = "roleType", jdbcType = JdbcType.VARCHAR),
            @Result(column = "role_name", property = "roleName", jdbcType = JdbcType.VARCHAR)})
    List<QHRole> findAllRoleByUid(@Param("uId") String uId);
}
