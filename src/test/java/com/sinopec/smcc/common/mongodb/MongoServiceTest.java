package com.sinopec.smcc.common.mongodb;

import com.sinopec.smcc.cpro.ClassProtectionApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.FileInputStream;

/**
 * </p>
 *
 * @author Bull Demon King
 * @version 1.0
 * @date 2018/7/24 14:55
 * @email rd-liuwei@pcitc.com
 */
@SpringBootTest(classes = ClassProtectionApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@RunWith(SpringJUnit4ClassRunner.class)
public class MongoServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(MongoServiceTest.class);

    @Autowired
    private MongoService mongoService;

    @Test
    public void uploadFile() throws Exception {
        String fileName = "D:\\10-Examples\\echarts-2.2.7.zip";
        FileInputStream fileInputStream = new FileInputStream(fileName);
       String fileId =  mongoService.uploadFile(fileInputStream,"测试");
       LOGGER.info("FileID:[{}]",fileId);
    }

    @Test
    public void downloadFile() throws Exception {
        File downFile = new File("D:\\10-Examples\\mongo-echarts.zip");
        mongoService.downloadFile("5b56f31b9f2a9232286c854d",downFile);
    }

    @Test
    public void downloadFile1() throws Exception {
    }

    @Test
    public void downloadFile2() throws Exception {
    }

    @Test
    public void downloadFileByName() throws Exception {
    }

    @Test
    public void downloadFileUseStream() throws Exception {
    }

    @Test
    public void downloadFileUseStream1() throws Exception {
    }

    @Test
    public void downloadFileUseStream2() throws Exception {
    }

    @Test
    public void downloadFileUseStream3() throws Exception {
    }

    @Test
    public void deleteByObjectId() throws Exception {
    }

}
