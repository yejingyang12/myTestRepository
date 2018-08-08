/**
* 2018. 
* @Title SystemMapper.java
* @Package com.sinopec.smcc.cpro.system.mapper
* @Description: TODO:
* @author hanxin
* @date 2018年5月25日上午11:43:13
* @version V1.0
*/
package com.sinopec.smcc.cpro.system.mapper;

import java.util.List;

import com.sinopec.smcc.cpro.evaluation.entity.EvaluationParam;
import com.sinopec.smcc.cpro.evaluation.entity.EvaluationResult;
import com.sinopec.smcc.cpro.grading.entity.AttachMaterialsListResult;
import com.sinopec.smcc.cpro.grading.entity.AttachMaterialsParam;
import com.sinopec.smcc.cpro.grading.entity.GradingListResult;
import com.sinopec.smcc.cpro.grading.entity.GradingParam;
import com.sinopec.smcc.cpro.records.entity.RecordsParam;
import com.sinopec.smcc.cpro.records.entity.RecordsResult;
import com.sinopec.smcc.cpro.review.entity.CheckParam;
import com.sinopec.smcc.cpro.review.entity.CheckResult;
import com.sinopec.smcc.cpro.selfexamination.entity.SelfexaminationParam;
import com.sinopec.smcc.cpro.selfexamination.entity.SelfexaminationResult;
import com.sinopec.smcc.cpro.system.entity.SystemGradingChangeResult;
import com.sinopec.smcc.cpro.system.entity.SystemAllInfoResult;
import com.sinopec.smcc.cpro.system.entity.SystemKeyResult;
import com.sinopec.smcc.cpro.system.entity.SystemListResult;
import com.sinopec.smcc.cpro.system.entity.SystemParam;
import com.sinopec.smcc.cpro.system.entity.SystemResult;
import com.sinopec.smcc.cpro.system.entity.SystemSubResult;
import com.sinopec.smcc.cpro.system.entity.SystemTemplateListResult;
import com.sinopec.smcc.cpro.system.entity.SystemUseResult;

/**
 * @Title SystemMapper.java
 * @Package com.sinopec.smcc.cpro.system.mapper
 * @Description: TODO:
 * @author hanxin
 * @date 2018年5月25日上午11:43:13
 * @version V1.0
 */
public interface SystemMapper {

  /**
   * @Descrption
   * @author hanxin
   * @date 2018年5月25日下午2:55:13
   * @param systemParam
   * @return
   */
  List<SystemListResult> selectAllBySystemParam(SystemParam systemParam);
  
  /**
   * @Descrption
   * @author hanxin
   * @date 2018年5月25日下午6:52:45
   * @param systemListResult
   */
  void insertSystem(SystemParam systemParam);
  
  /**
   * @Descrption
   * @author dongxu
   * @date 2018年5月27日下午11:19:28
   * @param systemCodeParam
   * @return
   */
//  List<SystemCodeListResult> selectSystemCodeListByParam(SystemCodeParam systemCodeParam);
  
  /**
   * @Descrption
   * @author hanxin
   * @date 2018年5月28日下午3:52:46
   * @param systemParam
   * @return
   */
  SystemResult selectDetailsSystem(SystemParam systemParam);
  
  /**
   * @Descrption
   * @author hanxin
   * @date 2018年5月28日下午5:42:01
   * @param systemParam
   * @return
   */
  SystemResult selectEditSystem(SystemParam systemParam);
  
  /**
   * @Descrption
   * @author zhouyu
   * @date 2018年5月28日下午4:47:58
   */
  void updateKeySystem(SystemParam systemParam);
  
  /**
   * @Descrption
   * @author hanxin
   * @date 2018年5月30日下午6:21:26
   * @param standardizedCode
   * @return
   */
  String selectSystemByStandardizedCode(String standardizedCode);
  
  /**
   * @Descrption
   * @author dongxu
   * @date 2018年5月30日上午10:28:20
   * @param systemParam
   */
  void updateSystemStatusBySystemId(SystemParam systemParam);
  
  /**
   * @author changmingyong
   * @date 2018年8月5日下午20:06:20
   * @param codeName1
   * @return
   */
  String selectSystemByComCode(String codeName);

  /**
   * @Descrption
   * @author hanxin
   * @date 2018年6月5日上午10:33:34
   * @param systemParam
   * @return
   */
  List<SystemTemplateListResult> selectSystemTemPlate(SystemParam systemParam);

  /**
   * @Descrption
   * @author hanxin
   * @date 2018年6月5日下午4:14:48
   * @return
   */
  List<SystemTemplateListResult> selectSystemCode();

  /**
   * @Descrption
   * @author hanxin
   * @param systemTemplateListResult 
   * @date 2018年6月5日下午4:46:26
   */
  void insertSystemTemplate(List<SystemTemplateListResult> systemTemplateListResult);
  

  /**
   * @Descrption
   * @author hanxin
   * @date 2018年6月7日下午3:28:52
   * @param subSystemList
   */
  void insertBatchSystem(List<SystemParam> subSystemList);

  /**
   * @Descrption
   * @author hanxin
   * @date 2018年6月7日下午9:42:22
   * @param systemParam
   * @return
   */
  List<EvaluationResult> selectAllByEvaluation(SystemParam systemParam);

  /**
   * @Descrption
   * @author hanxin
   * @date 2018年6月7日下午9:42:38
   * @param systemParam
   * @return
   */
  List<SelfexaminationResult> selectAllBySelf(SystemParam systemParam);

  /**
   * @Descrption
   * @author hanxin
   * @date 2018年6月7日下午9:42:48
   * @param systemParam
   * @return
   */
  GradingListResult selectAllByGrading(SystemParam systemParam);

  /**
   * @Descrption
   * @author hanxin
   * @date 2018年6月7日下午9:42:53
   * @param systemParam
   * @return
   */
  RecordsResult selectAllByRecord(SystemParam systemParam);

  /**
   * @Descrption
   * @author hanxin
   * @date 2018年6月7日下午9:42:58
   * @param systemParam
   * @return
   */
  AttachMaterialsListResult selectAllByMaterial(SystemParam systemParam);

  /**
   * @Descrption
   * @author hanxin
   * @date 2018年6月7日下午9:43:07
   * @param systemParam
   * @return
   */
  CheckResult selectAllByCheck(SystemParam systemParam);

  /**
   * @Descrption
   * @author hanxin
   * @date 2018年6月8日上午9:37:02
   * @param systemParam
   * @return
   */
 // CheckNodeParam selectAllByCheckNode(SystemParam systemParam);

  /**
   * @Descrption
   * @author hanxin
   * @date 2018年6月8日下午12:06:06
   * @param evaluationList
   */
  void insertEvaluationTemp(List<EvaluationParam> evaluationList);

  /**
   * @Descrption
   * @author hanxin
   * @date 2018年6月8日下午12:06:17
   * @param selfexaminationList
   */
  void insertSelfexaminationTemp(List<SelfexaminationParam> selfexaminationList);

  /**
   * @Descrption
   * @author hanxin
   * @date 2018年6月8日下午12:06:23
   * @param gradingTemParam
   */
  void insertGradingTemp(GradingParam gradingTemParam);

  /**
   * @Descrption
   * @author hanxin
   * @date 2018年6月8日下午12:06:31
   * @param attachMaterialsParam
   */
  void insertAttachMaterialsTemp(AttachMaterialsParam attachMaterialsParam);

  /**
   * @Descrption
   * @author hanxin
   * @date 2018年6月8日下午12:06:36
   * @param recordsParam
   */
  void insertRecordsTemp(RecordsParam recordsParam);

  /**
   * @Descrption
   * @author hanxin
   * @date 2018年6月8日下午12:06:47
   * @param checkParam
   */
  void insertCheckTemp(CheckParam checkParam);

  /**
   * @Descrption
   * @author hanxin
   * @date 2018年6月8日下午12:06:52
   * @param checkNodeParam
   */
//  void insertCheckNodeTemp(CheckNodeParam checkNodeParam);

  /**
   * @Descrption
   * @author hanxin
   * @date 2018年6月8日下午3:48:52
   * @param systemParam
   * @return
   */
  List<SystemSubResult> selectEditBySub(SystemParam systemParam);

  /**
   * @Descrption
   * @author hanxin
   * @date 2018年6月9日下午5:31:42
   * @param systemParam
   * @return
   */
  List<SystemKeyResult> selectKeyTemp(SystemParam systemParam);

  /**
   * @Descrption
   * @author hanxin
   * @date 2018年6月9日下午5:41:12
   * @param systemParam
   * @return
   */
  List<SystemUseResult> selectUseTemp(SystemParam systemParam);

  /**
   * @Descrption
   * @author hanxin
   * @date 2018年6月12日上午9:40:07
   * @param systemParam
   * @return 
   */
  SystemGradingChangeResult selectgradingEditAudit(SystemParam systemParam);


  /**
   * @Descrption
   * @author hanxin
   * @date 2018年6月12日下午3:34:09
   * @param systemParam
   */
  void updateSystemEdit(SystemParam systemParam);

  /**
   * @Descrption
   * @author hanxin
   * @date 2018年6月12日下午5:27:50
   * @param systemParam
   * @return
   */
  List<SystemResult> selectSubSystem(SystemParam systemParam);

  /**
   * @Descrption
   * @author hanxin
   * @date 2018年6月13日上午10:42:33
   * @param subUpdateSystem
   */
  void updateSystemSub(List<SystemResult> subUpdateSystem);

  /**
   * @Descrption
   * @author hanxin
   * @date 2018年6月14日上午9:29:53
   * @param systemParam
   * @return
   */
  SystemResult selectSystem(SystemParam systemParam);

  /**
   * @Descrption
   * @author hanxin
   * @date 2018年6月15日下午1:56:12
   * @param systemDelete
   */
  void updateSubStat(List<SystemParam> systemDelete);

  /**
   * @Descrption
   * @author hanxin
   * @date 2018年6月16日上午10:54:47
   * @param subUpdateSystemList
   */
  void updateSystem(List<SystemResult> subUpdateSystemList);

  /**
   * @Descrption
   * @author Aran
   * @date 2018年6月21日下午6:11:25
   * @param systemParamAddList
   */
  void updateSystemInfo(List<SystemParam> systemParamAddList);

  /**
   * @Descrption
   * @author Aran
   * @date 2018年6月21日下午6:22:55
   * @param systemParam
   * @return
   */
  List<SystemParam> selectSubSystemInfo(SystemParam systemParam);

  /**
   * @Descrption
   * @author dongxu
   * @date 2018年7月31日下午5:38:56
   * @param systemParam
   * @return
   */
  SystemResult selectSystemByCheck(SystemParam systemParam);
  
  /**
   * @Descrption  根据系统名称和标准化代码查询系统信息
   * @author yejingyang
   * @date 2018年7月30日下午6:40:22
   * @param systemParam
   */
  SystemResult selectSystemBySystemNameAndStandardizedCode(SystemParam systemParam);
  
  /**
   * @Descrption  根据条件获取系统全部信息列表（主要用systemIds）
   * @author yejingyang
   * @date 2018年8月2日上午10:02:35
   * @param systemParam
   * @return
   */
  List<SystemAllInfoResult> selectSystemAllInfoBySystemParam(SystemParam systemParam);
  
  /**
   * @Descrption 通过systemCode查询系统信息
   * @author dongxu
   * @date 2018年8月6日下午7:28:25
   * @param systemParam
   * @return
   */
  SystemResult selectSystemBysystemCode(SystemParam systemParam);
}
