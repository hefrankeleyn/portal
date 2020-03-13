package com.hef.qhjiaoyiyuan.dao;

import com.hef.qhjiaoyiyuan.bean.Article;
import com.hef.qhjiaoyiyuan.bean.Channel;
import com.hef.qhjiaoyiyuan.bean.exchange.ArticleCondition;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

/**
 * 文章管理的
 *
 * @Date 2020/3/11
 * @Author lifei
 */
@Mapper
public interface ArticleDao {


    /**
     * 添加文章
     *
     * @param article
     */
    @Insert(value = {" insert into qh_article(content_title,content_text,about,content,author,cover_url,issue_time,status) " +
            " values(#{contentTitle},#{contentText},#{about},#{content},#{author},#{coverUrl},#{issueTime},#{status}) "})
    void saveArticle(Article article);

    /**
     * 查询所有的文章
     *
     * @return
     */
    @Select(value = {"SELECT `a_id`, `content_title`, `content_text`, `about`, `content`, " +
            " `author`, `cover_url`, `issue_time`, `status`, `c_id` FROM `qh_article` where `status`!=2 "})
    @Results(value = {@Result(column = "a_id", property = "aid", jdbcType = JdbcType.INTEGER),
            @Result(column = "content_title", property = "contentTitle", jdbcType = JdbcType.VARCHAR),
            @Result(column = "content_text", property = "contentText", jdbcType = JdbcType.VARCHAR),
            @Result(column = "about", property = "about", jdbcType = JdbcType.VARCHAR),
            @Result(column = "content", property = "content", jdbcType = JdbcType.VARCHAR),
            @Result(column = "author", property = "author", jdbcType = JdbcType.VARCHAR),
            @Result(column = "cover_url", property = "coverUrl", jdbcType = JdbcType.VARCHAR),
            @Result(column = "issue_time", property = "issueTime", jdbcType = JdbcType.DATE),
            @Result(column = "status", property = "status", jdbcType = JdbcType.INTEGER),
            @Result(column = "c_id", property = "channel", javaType = Channel.class,
                    one = @One(select = "com.hef.qhjiaoyiyuan.dao.ChannelDao.findChannelByCid"))})
    List<Article> findAllArticle();

    /**
     * 根据id 查询文章
     *
     * @param aid
     * @return
     */
    @Select(value = {"SELECT `a_id`, `content_title`, `content_text`, `about`, `content`, " +
            " `author`, `cover_url`, `issue_time`, `status`, `c_id` FROM `qh_article` where a_id=#{aid} "})
    @Results(value = {@Result(column = "a_id", property = "aid", jdbcType = JdbcType.INTEGER),
            @Result(column = "content_title", property = "contentTitle", jdbcType = JdbcType.VARCHAR),
            @Result(column = "content_text", property = "contentText", jdbcType = JdbcType.VARCHAR),
            @Result(column = "about", property = "about", jdbcType = JdbcType.VARCHAR),
            @Result(column = "content", property = "content", jdbcType = JdbcType.VARCHAR),
            @Result(column = "author", property = "author", jdbcType = JdbcType.VARCHAR),
            @Result(column = "cover_url", property = "coverUrl", jdbcType = JdbcType.VARCHAR),
            @Result(column = "issue_time", property = "issueTime", jdbcType = JdbcType.DATE),
            @Result(column = "status", property = "status", jdbcType = JdbcType.INTEGER),
            @Result(column = "c_id", property = "channel", javaType = Channel.class,
                    one = @One(select = "com.hef.qhjiaoyiyuan.dao.ChannelDao.findChannelByCid"))})
    Article findArticleByAid(@Param("aid") Integer aid);

    /**
     * 根据 channel查询所有的文章
     * @param channel
     * @return
     */
    @Select(value = {"<script>" +
            " SELECT `t1.a_id`, `t1.content_title`, `t1.content_text`, `t1.about`, `t1.content`, " +
            " `t1.author`, `t1.cover_url`, `t1.issue_time`, `t1.status`, `t1.c_id` FROM `qh_article` t1 " +
            " inner join " +
            " qh_channels t2 on t1.c_id=t2.c_id " +
            " <where> " +
            " <if test='cId!=null'>and t2.c_id=#{cId}</if> " +
            " <if test='channelName!=null'>and t2.channel_name=#{channelName}</if>" +
            " </where> " +
            " </script> "})
    @Results(value = {@Result(column = "a_id", property = "aid", jdbcType = JdbcType.INTEGER),
            @Result(column = "content_title", property = "contentTitle", jdbcType = JdbcType.VARCHAR),
            @Result(column = "content_text", property = "contentText", jdbcType = JdbcType.VARCHAR),
            @Result(column = "about", property = "about", jdbcType = JdbcType.VARCHAR),
            @Result(column = "content", property = "content", jdbcType = JdbcType.VARCHAR),
            @Result(column = "author", property = "author", jdbcType = JdbcType.VARCHAR),
            @Result(column = "cover_url", property = "coverUrl", jdbcType = JdbcType.VARCHAR),
            @Result(column = "issue_time", property = "issueTime", jdbcType = JdbcType.DATE),
            @Result(column = "status", property = "status", jdbcType = JdbcType.INTEGER),
            @Result(column = "c_id", property = "channel", javaType = Channel.class,
                    one = @One(select = "com.hef.qhjiaoyiyuan.dao.ChannelDao.findChannelByCid"))})
    List<Article> findAllArticleByChannel(Channel channel);

    /**
     * 更新文章
     *
     * @param article
     */
    @Update(value = {" UPDATE `qh_article` " +
            " SET " +
            " `content_title` = #{contentTitle}, " +
            " `content_text` = #{contentText}, " +
            " `about` = #{about}, " +
            " `content` = #{content}, " +
            " `author` = #{author}, " +
            " `cover_url` = #{coverUrl}, " +
            " `issue_time` = #{issueTime}, " +
            " `status` = #{status}, " +
            " WHERE `a_id` = #{aid} "})
    void updateArticle(Article article);

    /**
     * 根据文章的id删除文章
     *
     * @param aid
     */
    @Delete(value = {"DELETE FROM `qh_article` " +
            "WHERE a_id=#{aid)} "})
    void deleteArticleByAid(@Param("aid") Integer aid);


    /**
     * 根据条件查询数据
     * @param articleCondition
     * @return
     */
    @Select(value = {"<script>" +
            " SELECT `t1.a_id`, `t1.content_title`, `t1.content_text`, `t1.about`, `t1.content`, " +
            " `t1.author`, `t1.cover_url`, `t1.issue_time`, `t1.status`, `t1.c_id` FROM `qh_article` t1 " +
            " inner join " +
            " qh_channels t2 on t1.c_id=t2.c_id " +
            " <where> " +
            " <if test='articleTitle!=null'>and t1.content_title=#{articleTitle}</if> " +
            " <if test='author!=null'>and t1.author=#{author}</if>" +
            " <if test='status!=null'>and t1.status=#{status}</if>" +
            " <if test='channelName!=null'>and t2.channel_name=#{channelName}</if>" +
            " </where> " +
            " </script> "})
    @Results(value = {@Result(column = "a_id", property = "aid", jdbcType = JdbcType.INTEGER),
            @Result(column = "content_title", property = "contentTitle", jdbcType = JdbcType.VARCHAR),
            @Result(column = "content_text", property = "contentText", jdbcType = JdbcType.VARCHAR),
            @Result(column = "about", property = "about", jdbcType = JdbcType.VARCHAR),
            @Result(column = "content", property = "content", jdbcType = JdbcType.VARCHAR),
            @Result(column = "author", property = "author", jdbcType = JdbcType.VARCHAR),
            @Result(column = "cover_url", property = "coverUrl", jdbcType = JdbcType.VARCHAR),
            @Result(column = "issue_time", property = "issueTime", jdbcType = JdbcType.DATE),
            @Result(column = "status", property = "status", jdbcType = JdbcType.INTEGER),
            @Result(column = "c_id", property = "channel", javaType = Channel.class,
                    one = @One(select = "com.hef.qhjiaoyiyuan.dao.ChannelDao.findChannelByCid"))})
    List<Article> findArticlesByConditions(ArticleCondition articleCondition);
}
