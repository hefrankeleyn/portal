package com.hef.qhjiaoyiyuan.service.impl;

import com.hef.qhjiaoyiyuan.bean.Channel;
import com.hef.qhjiaoyiyuan.bean.QHUser;
import com.hef.qhjiaoyiyuan.dao.ChannelDao;
import com.hef.qhjiaoyiyuan.service.ChannelService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 栏目管理
 * @Date 2020/3/11
 * @Author lifei
 */
@Service
public class ChannelServiceImpl implements ChannelService {

    private ChannelDao channelDao;

    @Autowired(required = false)
    public void setChannelDao(ChannelDao channelDao) {
        this.channelDao = channelDao;
    }

    @Override
    public List<Channel> findAllChannel() {
        return channelDao.findAllChannel();
    }

    @Override
    public Channel findChannelByName(String channelName) {
        if (StringUtils.isEmpty(channelName)) return null;
        return channelDao.findChannelByName(channelName);
    }

    @Override
    public List<Channel> findAllChannelByUser(QHUser qhUser) {
        return channelDao.findAllChannelByUser(qhUser);
    }

    @Override
    public void saveOrUpdateChannel(Channel channel) {
        if (channel==null) return;
        if (channel.getCid()!=null){
            Channel oldChannel = channelDao.findChannelByCid(channel.getCid());
            if (oldChannel!=null && oldChannel.getCid()!=null){
                BeanUtils.copyProperties(channel,oldChannel, "cid");
                channelDao.updateChannel(oldChannel);
            }
        }else {
            channelDao.saveChannel(channel);
        }
    }

    @Override
    public void deleteChannel(Integer cId) {
        channelDao.deleteChannelByCid(cId);
    }
}
