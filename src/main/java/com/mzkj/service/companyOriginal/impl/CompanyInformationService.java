package com.mzkj.service.companyOriginal.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageInfo;
import com.mzkj.bean.CompanyInformationBean;
import com.mzkj.bean.OriginalBean;
import com.mzkj.domain.Original;
import com.mzkj.mapper.companyOriginal.OriginalMapper;
import com.mzkj.util.Const;
import com.mzkj.util.ConvertUtil;
import com.mzkj.util.DateUtil;
import com.mzkj.util.Jurisdiction;
import com.mzkj.util.PageUtil;
import com.mzkj.vo.BaseVo;
import com.mzkj.vo.companyOriginal.CompanyInformationQueryVo;
import com.mzkj.vo.companyOriginal.CompanyInformationVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;

import com.mzkj.service.companyOriginal.CompanyInformationManager;
import com.mzkj.mapper.companyOriginal.CompanyInformationMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.swagger.models.auth.In;

/**
 * 说明： 原件管理客户信息
 * 创建人：CDCXH
 * 创建时间：2019-04-17
 */
@Service("companyinformationService")
public class CompanyInformationService implements CompanyInformationManager {

    @Autowired
    private CompanyInformationMapper companyinformationMapper;
    @Autowired
    private OriginalMapper originalMapper;

    /**
     * 新增
     */
    @Override
    public CompanyInformationVo save(CompanyInformationVo companyinformationVo) throws Exception {
        CompanyInformationBean companyinformationBean = ConvertUtil.objectCopyParams(companyinformationVo, CompanyInformationBean.class);
        //设置租户ID
        companyinformationBean.setTenantId(Jurisdiction.getTenant());
        companyinformationBean.setCreateUser(Jurisdiction.getUsername());
        companyinformationBean.setCreateDate(DateUtil.getTime());
        companyinformationMapper.save(companyinformationBean);
        companyinformationVo = ConvertUtil.objectCopyParams(companyinformationBean, CompanyInformationVo.class);
        return companyinformationVo;
    }

    /**
     * 删除
     */
    @Override
    public void delete(String COMPANYINFORMATION_ID) throws Exception {
        companyinformationMapper.delete(COMPANYINFORMATION_ID);
    }

    /**
     * 修改
     */
    @Override
    public void edit(CompanyInformationVo companyinformationVo) throws Exception {
        CompanyInformationBean companyinformationBean = ConvertUtil.objectCopyParams(companyinformationVo, CompanyInformationBean.class);
        //设置租户ID
        companyinformationBean.setTenantId(Jurisdiction.getTenant());
        companyinformationBean.setUpdateUser(Jurisdiction.getUsername());
        companyinformationBean.setUpdateDate(DateUtil.getTime());
        companyinformationMapper.edit(companyinformationBean);
    }

    /**
     * 列表
     */
    @Override
    public PageInfo<CompanyInformationQueryVo> list(CompanyInformationQueryVo companyinformationQueryVo) throws Exception {
        //将vo转DO并将分页信息传到pageHelper
        CompanyInformationBean companyinformationBean = PageUtil.startPageAndObjectCopyParams(companyinformationQueryVo, CompanyInformationBean.class);
        //设置租户ID
        companyinformationBean.setTenantId(Jurisdiction.getTenant());
        List<CompanyInformationBean> companyinformationPageBean = companyinformationMapper.list(companyinformationBean);
        selectOriginalByInformationBeanList(companyinformationPageBean);
        PageInfo<CompanyInformationQueryVo> pageInfo = new PageInfo(companyinformationPageBean);
        //DO转VO
        List<CompanyInformationQueryVo> informationQueryVoList = (List<CompanyInformationQueryVo>) ConvertUtil.castListObjectToTargetList(companyinformationPageBean, CompanyInformationQueryVo.class);
        pageInfo.setList(informationQueryVoList);
        return pageInfo;
    }

    /**
     * 根据传入的CompanyInformationBean 查询对应的原件List
     * return
     * Author luosc
     * param
     * Date 2019-04-23 14:56
     */
    public void selectOriginalByInformationBeanList(List<CompanyInformationBean> CompanyInformationBeanList) throws Exception {
        for (CompanyInformationBean companyInformationBean : CompanyInformationBeanList) {
            List<OriginalBean> originalBeans = originalMapper.findByCompanyInformationId(companyInformationBean.getCompanyInformationId());
            if (originalBeans != null && originalBeans.size() > 0) {
                companyInformationBean.setOriginalList(originalBeans);
            }
        }
    }

    @Override
    public CompanyInformationVo findById(String CompanyInformationId) throws Exception {
        CompanyInformationBean companyInformationBean = new CompanyInformationBean();
        companyInformationBean = companyinformationMapper.findById(CompanyInformationId);
        CompanyInformationVo companyinformationVo = ConvertUtil.objectCopyParams(companyInformationBean, CompanyInformationVo.class);
        return companyinformationVo;
    }

    @Override
    public Map<String, Integer> holdCountAndToBeConfirmedCountAndOutgoingCountAndLoanInCount() throws Exception {
        int holdCount = 0;//当前登录人持有原件数量
        int toBeConfirmedCount = 0;//需确认条数
        int outgoingCount = 0;//出库中数量
        int loanInCount = 0;//待借入数量
        OriginalBean originalBean = new OriginalBean();
        originalBean.setTenantId(Jurisdiction.getTenant());
        List<OriginalBean> list = originalMapper.holdCountAndToBeConfirmedCountAndOutgoingCountAndLoanInCount(originalBean);
        for (OriginalBean originalBean1 : list
                ) {
            //如果原件持有人和当前登录人相同
            if (!StringUtils.isEmpty(originalBean1.getOriginalHolder())&&originalBean1.getOriginalHolder().equals(Jurisdiction.getUsername())) {
                //如果流转状态为入库
                if (!StringUtils.isEmpty(originalBean1.getOriginalOutStatus()) && originalBean1.getOriginalOutStatus().equals(Const.ORIGINAL_OUT_STATUS_2)) {
                    holdCount++;
                }
                //如果流转状态为出库中
                if (!StringUtils.isEmpty(originalBean1.getOriginalOutStatus()) && originalBean1.getOriginalOutStatus().equals(Const.ORIGINAL_OUT_STATUS_1)) {
                    outgoingCount++;
                }
                //如果流转状态为待借入
                if (!StringUtils.isEmpty(originalBean1.getOriginalOutStatus()) && originalBean1.getOriginalOutStatus().equals(Const.ORIGINAL_OUT_STATUS_3)) {
                    toBeConfirmedCount++;
                }
            }
            //如果流转对象和当前登录人相同
            if (!StringUtils.isEmpty(originalBean1.getOriginalOutTo())&&originalBean1.getOriginalOutTo().equals(Jurisdiction.getUsername())) {
                //如果流转状态为出库中
                if (!StringUtils.isEmpty(originalBean1.getOriginalOutStatus()) && originalBean1.getOriginalOutStatus().equals(Const.ORIGINAL_OUT_STATUS_1)) {
                    toBeConfirmedCount++;
                }
                //如果流转状态为待借入
                if (!StringUtils.isEmpty(originalBean1.getOriginalOutStatus()) && originalBean1.getOriginalOutStatus().equals(Const.ORIGINAL_OUT_STATUS_3)) {
                    loanInCount++;
                }
            }
        }

        Map<String, Integer> map = new HashMap<>();
        map.put("holdCount", holdCount);
        map.put("toBeConfirmedCount", toBeConfirmedCount);
        map.put("outgoingCount", outgoingCount);
        map.put("loanInCount", loanInCount);

        return map;
    }


}

