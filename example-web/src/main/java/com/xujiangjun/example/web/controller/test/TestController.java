package com.xujiangjun.example.web.controller.test;

import com.google.zxing.WriterException;
import com.xujiangjun.example.common.domain.Result;
import com.xujiangjun.example.common.util.ZXingUtils;
import com.xujiangjun.example.web.support.OSSHelper;
import org.apache.commons.codec.binary.Base64;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

/**
 * 测试控制类
 *
 * @author xujiangjun
 * @date 2017-07-27 18:26
 */
@RestController
@RequestMapping("test")
public class TestController {

    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public Result<String> upload(HttpServletRequest request){
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        StringBuilder filenameSb = new StringBuilder();
        for(Map.Entry<String, MultipartFile> entry : fileMap.entrySet()){
            MultipartFile file = entry.getValue();
            String originalFilename = file.getOriginalFilename();
            String suffix = originalFilename.substring(originalFilename.indexOf("."));
            String filename = UUID.randomUUID().toString() + suffix;
            OSSHelper.fileUpload("example", filename, file);
            filenameSb.append(filename).append(",");
        }
        String filename = filenameSb.substring(0, filenameSb.lastIndexOf(","));
        return Result.wrapSuccessfulResult(filename);
    }

    /**
     * 生成带logo的二维码
     *
     * @param context 二维码内容
     * @param multipartRequest logo图片
     * @return 图片转换为基于Base64的编码
     */
    @RequestMapping("qrCode")
    public Result<String> qrCode(String context, MultipartRequest multipartRequest) throws IOException, WriterException {
        MultipartFile multipartFile = multipartRequest.getFile("logoPic");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZXingUtils.Encode.buildQRCodeWithLogo(300, context, outputStream,
                ZXingUtils.ImageType.PNG, multipartFile.getInputStream());
        String codecEncoder = Base64.encodeBase64String(outputStream.toByteArray());
        return Result.wrapSuccessfulResult(codecEncoder);
    }
}
