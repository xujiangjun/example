package com.xujiangjun.example.common.util;

import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
import sun.misc.BASE64Encoder;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author xujiangjun
 * @date 2017-07-27 15:46
 */
public class ZXingUtilsTest {

    /**
     * 生成二维码（无logo）
     */
    @Test
    public void testBuildQRCode() throws IOException, WriterException {
        ZXingUtils.Encode.buildQRCode(300, "https://www.baidu.com?source=example",
                "/Users/xujiangjun", "qrCode.png", ZXingUtils.ImageType.PNG);
    }

    /**
     * 生成二维码的Base64编码
     */
    @Test
    public void testBuildQRCodeEncodeBase64() throws IOException, WriterException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZXingUtils.Encode.buildQRCode("https://www.baidu.com", outputStream, ZXingUtils.ImageType.PNG);
        // 使用Java原生的Base64工具类
        String sunMiscEncoder = new BASE64Encoder().encode(outputStream.toByteArray());
        // 使用commons-codec包的Base64工具类
        String codecEncoder = Base64.encodeBase64String(outputStream.toByteArray());
        if(!sunMiscEncoder.equalsIgnoreCase(codecEncoder)){
            System.out.println("两种Base64工具类：sunMiscEncoder会比codecEncoder多一些\n字符，但不影响图片的输出");
        }
    }

    /**
     * 生成带logo的二维码
     */
    @Test
    public void testBuildQRCodeWithLogo() throws IOException, WriterException {
        ZXingUtils.Encode.buildQRCodeWithLogo(300, "https://www.baidu.com", "/Users/xujiangjun", "qrCode.png", ZXingUtils.ImageType.PNG,
                new FileInputStream(new File("/Users/xujiangjun/Downloads/alipay.png")));

    }

    /**
     * 识别二维码
     */
    @Test
    public void testReadQRCode() throws IOException, ReaderException {
        Result result = ZXingUtils.Decode.readQRCodeResult(new File("/Users/xujiangjun/qrCode.png"));
        System.out.println(result);
    }


}
