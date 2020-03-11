package com.hef.qhjiaoyiyuan.dao;

import com.hef.qhjiaoyiyuan.bean.QHUser;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface QHUserDao {

    /**
     * 查询所有的用户
     * @return
     */
    @Select({"select u_id,login_username,login_password,nickname,user_des,create_date from qh_users"})
    @Results(value = {@Result(column = "u_id", property = "uId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "login_username", property = "loginUserName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "login_password", property = "loginPassWorld", jdbcType = JdbcType.VARCHAR),
            @Result(column = "nickname", property = "nickname", jdbcType = JdbcType.VARCHAR),
            @Result(column = "user_des", property = "userDes", jdbcType = JdbcType.VARCHAR),
            @Result(column = "create_date", property = "createDate", jdbcType = JdbcType.DATE),
            @Result(property = "roles", column = "u_id", javaType = List.class,
                    many = @Many(select = "com.hef.qhjiaoyiyuan.dao.QHRoleDao.findAllRoleByUid"))})
    List<QHUser> findAllQHUser();

    /**
     * 根据登陆名查询用户
     *
     * @param loginUsername
     * @return
     */
    @Select({"select u_id,login_username,login_password,nickname,user_des,create_date " +
            "from qh_users where login_username=#{loginUsername}"})
    @Results(value = {@Result(column = "u_id", property = "uId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "login_username", property = "loginUserName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "login_password", property = "loginPassWorld", jdbcType = JdbcType.VARCHAR),
            @Result(column = "nickname", property = "nickname", jdbcType = JdbcType.VARCHAR),
            @Result(column = "user_des", property = "userDes", jdbcType = JdbcType.VARCHAR),
            @Result(column = "create_date", property = "createDate", jdbcType = JdbcType.DATE),
            @Result(property = "roles", column = "u_id", javaType = List.class,
            many = @Many(select = "com.hef.qhjiaoyiyuan.dao.QHRoleDao.findAllRoleByUid"))})
    QHUser findQHUserByLoginUsername(@Param("loginUsername") String loginUsername);

    /**
     * 根据uid查询用户
     * @param uId
     * @return
     */
    @Select({"select u_id,login_username,login_password,nickname,user_des,create_date " +
            "from qh_users where u_id=#{uId}"})
    @Results(value = {@Result(column = "u_id", property = "uId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "login_username", property = "loginUserName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "login_password", property = "loginPassWorld", jdbcType = JdbcType.VARCHAR),
            @Result(column = "nickname", property = "nickname", jdbcType = JdbcType.VARCHAR),
            @Result(column = "user_des", property = "userDes", jdbcType = JdbcType.VARCHAR),
            @Result(column = "create_date", property = "createDate", jdbcType = JdbcType.DATE),
            @Result(property = "roles", column = "u_id", javaType = List.class,
                    many = @Many(select = "com.hef.qhjiaoyiyuan.dao.QHRoleDao.findAllRoleByUid"))})
    QHUser findQHUserByUid(@Param("uId") Integer uId);

}
