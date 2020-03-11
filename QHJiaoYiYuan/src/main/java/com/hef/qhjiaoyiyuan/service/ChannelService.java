package com.hef.qhjiaoyiyuan.service;

import com.hef.qhjiaoyiyuan.bean.Channel;
import com.hef.qhjiaoyiyuan.bean.QHUser;

import java.util.List;

public interface ChannelService {

    /**
     * 查询所有的栏目
     * @return
     */
    List<Channel> findAllChannel();

    /**
     * 根据 栏目的名称查询栏目
     * @param channelName
     * @return
     */
    Channel findChannelByName(String channelName);

    /**
     * 查询用户管理的栏目信息
     * @param qhUser
     * @return
     */
    List<Channel> findAllChannelByUser(QHUser qhUser);

    /**
     * 保存栏目
     * @param channel
     */
    void saveOrUpdateChannel(Channel channel);

    /**
     * 删除栏目信息
     * @param cId
     */
    void deleteChannel(Integer cId);
}
