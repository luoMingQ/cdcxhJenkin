package com.mzkj.service.insurance.impl;

import com.github.pagehelper.PageInfo;
import com.mzkj.bean.SocialSecurityBean;
import com.mzkj.domain.User;
import com.mzkj.facade.vo.SocialSecurityQueryVo;
import com.mzkj.facade.vo.SocialSecurityVo;
import com.mzkj.mapper.insurance.SocialSecurityMapper;
import com.mzkj.mapper.system.UserMapper;
import com.mzkj.service.insurance.SocialSecurityManager;
import com.mzkj.service.oainterface.ProcessInterface;
import com.mzkj.util.ConvertUtil;
import com.mzkj.util.DateUtil;
import com.mzkj.util.Jurisdiction;
import com.mzkj.util.PageUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

/**
 * 说明： 社保工单
 * 创建人：CDCXH
 * 创建时间：2019-05-13
 */
@Service("socialSecurityService")
public class SocialSecurityService implements SocialSecurityManager {

    @Autowired
    private SocialSecurityMapper socialSecurityMapper;

    @Autowired
    private UserMapper userMapper;

    @Resource
    private ProcessInterface processInterfaceService;

    /**
     * 新增
     */
    @Override
    public SocialSecurityVo save(SocialSecurityVo socialSecurityVo) throws Exception {
        SocialSecurityBean socialSecurityBean = ConvertUtil.objectCopyParams(socialSecurityVo, SocialSecurityBean.class);
        if (StringUtils.isEmpty(socialSecurityBean.getTenantId())) {
            socialSecurityBean.setTenantId(Jurisdiction.getTenant());
        }
        if (StringUtils.isEmpty(socialSecurityBean.getCreateUser())) {
            socialSecurityBean.setCreateUser(Jurisdiction.getUsername());
        }
        socialSecurityBean.setCreateDate(DateUtil.getTime());
        //发起流程
        String procInstId =processInterfaceService.processStart(socialSecurityBean);
        if (!StringUtils.isEmpty(procInstId)) {
            socialSecurityBean.setProcInstId(procInstId);
            socialSecurityMapper.save(socialSecurityBean);
            socialSecurityVo = ConvertUtil.objectCopyParams(socialSecurityBean, SocialSecurityVo.class);
            return socialSecurityVo;
        }
        return null;

    }

    /**
     * 删除
     */
    @Override
    public void delete(String socialSecurityId) throws Exception {
        socialSecurityMapper.delete(socialSecurityId);
    }

    /**
     * 修改
     */
    @Override
    public void edit(SocialSecurityVo socialSecurityVo) throws Exception {
        SocialSecurityBean socialSecurityBean = ConvertUtil.objectCopyParams(socialSecurityVo, SocialSecurityBean.class);
        socialSecurityBean.setUpdateUser(Jurisdiction.getUsername());
        socialSecurityBean.setUpdateDate(DateUtil.getTime());
        socialSecurityMapper.edit(socialSecurityBean);
    }

    /**
     * 根据ID查询
     * return
     * Author luosc
     * param
     * Date 2019-05-13 15:41
     */
    @Override
    public SocialSecurityQueryVo findById(String socialSecurityId) throws Exception {
        SocialSecurityBean socialSecurityBean = socialSecurityMapper.findById(socialSecurityId);
        SocialSecurityQueryVo socialSecurityQueryVo = ConvertUtil.objectCopyParams(socialSecurityBean, SocialSecurityQueryVo.class);
        return socialSecurityQueryVo;
    }

    @Override
    public SocialSecurityQueryVo findByCode(String businessId) throws Exception {
        SocialSecurityBean socialSecurityBean = socialSecurityMapper.findByCode(businessId);
        SocialSecurityQueryVo socialSecurityQueryVo = ConvertUtil.objectCopyParams(socialSecurityBean, SocialSecurityQueryVo.class);
        return socialSecurityQueryVo;
    }

    /**
     * 列表
     */
    @Override
    public PageInfo<SocialSecurityQueryVo> list(SocialSecurityQueryVo socialSecurityQueryVo) throws Exception {
        //将vo转DO并将分页信息传到pageHelper
        SocialSecurityBean socialSecurityBean = PageUtil.startPageAndObjectCopyParams(socialSecurityQueryVo, SocialSecurityBean.class);
        //设置租户ID
        socialSecurityBean.setTenantId(Jurisdiction.getTenant());
        List<SocialSecurityBean> socialSecurityPageBean = socialSecurityMapper.list(socialSecurityBean);
        PageInfo<SocialSecurityQueryVo> pageInfo = new PageInfo(socialSecurityPageBean);
        //DO转VO
        List<SocialSecurityQueryVo> list = new ArrayList<>();
        for (SocialSecurityBean securityBean : socialSecurityPageBean) {
            SocialSecurityQueryVo securityQueryVo = ConvertUtil.objectCopyParams(securityBean, SocialSecurityQueryVo.class);
            //签单人username转name
            if (!StringUtils.isEmpty(securityQueryVo.getSaler())) {
                User user = userMapper.findByUsername(securityQueryVo.getSaler());
                if (null != user && !StringUtils.isEmpty(user.getName())) {
                    securityQueryVo.setSaler(user.getName());
                }
            }
            list.add(securityQueryVo);
        }
        pageInfo.setList(list);
        return pageInfo;
    }

}

