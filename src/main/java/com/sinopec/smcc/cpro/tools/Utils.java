/**
* @Title Utils.java
* @Package com.sinopec.smcc.cpro.tools
* @Description: TODO:
* @author eric
* @date 2018年5月25日下午8:05:12
* @version V1.0
*/
package com.sinopec.smcc.cpro.tools;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sinopec.smcc.base.result.RetResult;
import com.sinopec.smcc.cpro.ClassProtectionApplication;
import com.sinopec.smcc.depends.region.dto.CproResultParam;
import com.sinopec.smcc.depends.region.util.CproTemplate;

/**
 * @Title Utils.java
 * @Package com.sinopec.smcc.cpro.tools
 * @Description: TODO:
 * @author eric
 * @date 2018年5月25日下午8:05:12
 * @version V1.0
 */
@RunWith(JUnit4.class)  
@SpringBootTest(classes=ClassProtectionApplication.class)
public class Utils {
  
  @Autowired
  private CproTemplate cproTemplate;
  /**
   * 返回32位UUID，不包含中划线
   * @author eric
   * @date 2017年6月16日下午5:38:36
   * @return 去掉中划线的uuid
   */
  public static String getUuidFor32(){
    return UUID.randomUUID().toString().replace("-", "");
  }
  
  /**
   * 返回36位UUID，包含中划线
   * @author eric
   * @date 2017年6月16日下午5:38:11
   * @return uuid
   */
  public static String getUuidFor36(){
    return UUID.randomUUID().toString();
  }

  
  @Test
  public void testCpro(){
    String c= "asdasdad";
    System.out.println(c);
    RetResult<CproResultParam> a = cproTemplate.querySystemTrendByYear(1, "3501561", "2018");
    String ads = "asda";
    
  }
}
