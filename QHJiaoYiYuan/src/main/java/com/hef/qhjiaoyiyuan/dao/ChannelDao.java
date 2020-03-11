package com.hef.qhjiaoyiyuan.dao;

import com.hef.qhjiaoyiyuan.bean.Channel;
import com.hef.qhjiaoyiyuan.bean.QHUser;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

/**
 * 频道管理
 */
@Mapper
public interface ChannelDao {

    /**
     * 保存栏目信息
     *
     * @param channel
     */
    @Insert(value = {"insert into qh_channels(channel_name,channel_des,u_id) " +
            " values(#{channelName},#{channelDes},#{qhUser.uId}) "})
    void saveChannel(Channel channel);


    /**
     * 根据期货的名称查询栏目信息
     *
     * @param channelName
     * @return
     */
    @Select(value = {"select c_id,channel_name,channel_des from qh_channels " +
            " where channel_name=#{channelName} "})
    @Results(value = {@Result(column = "c_id", property = "cid", jdbcType = JdbcType.INTEGER),
            @Result(column = "channel_name", property = "channelName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "channel_des", property = "channelDes", jdbcType = JdbcType.VARCHAR),
            @Result(property = "qhUser", column = "c_id", javaType = QHUser.class,
                    one = @One(select = "com.hef.qhjiaoyiyuan.dao.QHUserDao.findQHUserByUid"))})
    Channel findChannelByName(@Param("channelName") String channelName);


    /**
     * 根据cid查询栏目信息
     * @param cid
     * @return
     */
    @Select(value = {"select c_id,channel_name,channel_des from qh_channels " +
            " where c_id=#{cid} "})
    @Results(value = {@Result(column = "c_id", property = "cid", jdbcType = JdbcType.INTEGER),
            @Result(column = "channel_name", property = "channelName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "channel_des", property = "channelDes", jdbcType = JdbcType.VARCHAR),
            @Result(property = "qhUser", column = "c_id", javaType = QHUser.class,
                    one = @One(select = "com.hef.qhjiaoyiyuan.dao.QHUserDao.findQHUserByUid"))})
    Channel findChannelByCid(@Param("cid") Integer cid);



    /**
     * 查询所有的栏目
     * @return
     */
    @Select(value = {"select c_id,channel_name,channel_des from qh_channels"})
    @Results(value = {@Result(column = "c_id", property = "cid", jdbcType = JdbcType.INTEGER),
            @Result(column = "channel_name", property = "channelName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "channel_des", property = "channelDes", jdbcType = JdbcType.VARCHAR),
            @Result(property = "qhUser", column = "c_id", javaType = QHUser.class,
                    one = @One(select = "com.hef.qhjiaoyiyuan.dao.QHUserDao.findQHUserByUid"))})
    List<Channel> findAllChannel();

    /**
     * 查询用户下的所有栏目
     * @param qhUser
     * @return
     */
    @Select(value = {"select t1.c_id,t1.channel_name,t1.channel_des from " +
            " qh_channels t1 inner join qh_users t2 on t1.u_id=t2.u_id " +
            " <where> " +
            " <if test='uId!=null'>and t2.u_id=#{uId}</if> " +
            " <if test='loginUserName!=null'>and t2.login_username=#{loginUserName}</if> " +
            " <if test='nickname!=null'>and t2.nickname=#{nickname}</if> " +
            " </where> "})
    @Results(value = {@Result(column = "c_id", property = "cid", jdbcType = JdbcType.INTEGER),
            @Result(column = "channel_name", property = "channelName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "channel_des", property = "channelDes", jdbcType = JdbcType.VARCHAR),
            @Result(property = "qhUser", column = "c_id", javaType = QHUser.class,
                    one = @One(select = "com.hef.qhjiaoyiyuan.dao.QHUserDao.findQHUserByUid"))})
    List<Channel> findAllChannelByUser(QHUser qhUser);


    /**
     * 更新栏目信息
     * @param channel
     */
    @Update(value = {" <script> " +
            " update qh_channels " +
            " <set> " +
            " channel_des=#{channelDes}, " +
            " <if test='channelName!=null'>channel_name=#{channelName},</if> " +
            " </set> " +
            " where c_id=#{cid} " +
            " </script> "})
    void updateChannel(Channel channel);

    /**
     * 根据id删除栏目
     * @param cid
     */
    @Delete(value = {"delete from qh_channels where c_id=#{cid}"})
    void deleteChannelByCid(@Param("cid") Integer cid);


}
