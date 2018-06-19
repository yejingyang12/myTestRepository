/**
* 2018. 
* @Title HomeServiceImpl.java
* @Package com.sinopec.smcc.cpro.home.server.impl
* @Description: TODO:
* @author zhouyu
* @date 2018年6月6日下午12:17:12
* @version V1.0
*/
package com.sinopec.smcc.cpro.home.server.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sinopec.smcc.common.exception.classify.BusinessException;
import com.sinopec.smcc.cpro.home.entity.HomeParam;
import com.sinopec.smcc.cpro.home.entity.HomeResult;
import com.sinopec.smcc.cpro.home.mapper.HomeMapper;
import com.sinopec.smcc.cpro.home.server.HomeService;

/**
 * @Title HomeServiceImpl.java
 * @Package com.sinopec.smcc.cpro.home.server.impl
 * @Description: TODO:
 * @author zhouyu
 * @date 2018年6月6日下午12:17:12
 * @version V1.0
 */
@Service
public class HomeServiceImpl implements HomeService {
  
  @Autowired
  private HomeMapper homeMapper;

  /**
   * 查询首页top10备案统计
   */
  @Override
  public PageInfo<HomeResult> homeRecordStatisticsList(HomeParam hParam) throws BusinessException {
    
    // 初始化分页拦截器
    PageHelper.startPage(hParam.getCurrentPage(), hParam.getPageSize());
    // 获得响应列表数据
    List<HomeResult> homeResultList = 
        this.homeMapper.selectHomeRecordStatistics(hParam);
    // 装载列表数据
    PageInfo<HomeResult> pageInfo = new PageInfo<>(homeResultList);
    return pageInfo;
  }

  /**
   * 
   */
  @Override
  public PageInfo<HomeResult> homeScoreStatisticsList(HomeParam hParam) throws BusinessException {

 // 初始化分页拦截器
    PageHelper.startPage(hParam.getCurrentPage(), hParam.getPageSize());
    // 获得响应列表数据
    List<HomeResult> homeResultList = 
        this.homeMapper.selectScoreStatisticsList(hParam);
    // 装载列表数据
    PageInfo<HomeResult> pageInfo = new PageInfo<>(homeResultList);
    return pageInfo;
  }


}
