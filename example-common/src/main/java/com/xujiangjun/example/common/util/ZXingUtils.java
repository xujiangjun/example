package com.xujiangjun.example.common.util;

import com.google.zxing.*;
import com.google.zxing.Reader;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.multi.GenericMultipleBarcodeReader;
import com.google.zxing.multi.MultipleBarcodeReader;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.*;

/**
 * QRCode生成与解析工具类
 *
 * 生成二维码带logo：http://www.jianshu.com/p/39c5bca32e3e
 *
 * @author xujiangjun
 * @date 2017-07-06 14:22
 */
public class ZXingUtils {

    /** 图片枚举类型 **/
    public enum ImageType {
        JPEG("jpeg"),PNG("png"),GIF("gif");
        private String value;

        ImageType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    /** QRCode编码 **/
    public static class Encode {

        // 用于设置QR二维码参数
        private static Map<EncodeHintType, Object> HINTS;

        static {
            HINTS = new EnumMap<EncodeHintType,Object>(EncodeHintType.class);
            HINTS.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            // 设置QR二维码的纠错级别（H为最高级别）具体级别信息
            HINTS.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
            HINTS.put(EncodeHintType.MARGIN, 1);
        }

        public static void buildQRCode(String content, OutputStream os, ImageType imageType)
                throws WriterException, IOException {
            buildQRCode(200, content, os, imageType);
        }

        /**
         * 生成二维码
         * @param widthAndHeight    高宽
         * @param content           二维码内容
         * @param os                输出流
         */
        public static void buildQRCode(int widthAndHeight, String content, OutputStream os, ImageType imageType)
                throws WriterException, IOException {
            BitMatrix bitMatrix = new MultiFormatWriter()
                    .encode(content, BarcodeFormat.QR_CODE, widthAndHeight, widthAndHeight, HINTS);// 生成矩阵
            MatrixToImageWriter.writeToStream(bitMatrix, imageType.getValue(), os);
        }

        /**
         * 生成二维码带logo
         *
         * @param widthAndHeight    高宽
         * @param content           二维码内容
         * @param os                输出流
         * @param imageType         图片类型（自定义枚举）
         * @param logoStream        logo输入流
         */
        public static void buildQRCodeWithLogo(int widthAndHeight, String content, OutputStream os, ImageType imageType,
                                               InputStream logoStream) throws WriterException, IOException {
            BitMatrix bitMatrix = new MultiFormatWriter()
                    .encode(content, BarcodeFormat.QR_CODE, widthAndHeight, widthAndHeight, HINTS);// 生成矩阵
            MatrixToImageConfig config = new MatrixToImageConfig();
            BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix, config);
            BufferedImage logo = ImageIO.read(logoStream);
            int deltaHeight = widthAndHeight - logo.getHeight();
            int deltaWidth = widthAndHeight - logo.getWidth();
            BufferedImage combined = new BufferedImage(widthAndHeight, widthAndHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics2D = combined.createGraphics();
            graphics2D.drawImage(image, 0, 0, null);
            graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
            graphics2D.drawImage(logo, Math.round(deltaWidth / 2), Math.round(deltaHeight / 2), null);
            ImageIO.write(combined, imageType.getValue(), os);
        }

        public static void buildQRCode(String content, String filePath, String fileName, ImageType imageType)
                throws WriterException, IOException {
            buildQRCode(200, content,filePath,fileName,imageType);
        }

        /**
         * 生成二维码
         * @param widthAndHeight    高宽
         * @param content           二维码内容
         * @param filePath          输出目录
         * @param fileName          输出文件名
         * @param imageType         输出文件类型
         */
        public static void buildQRCode(int widthAndHeight, String content, String filePath, String fileName,
                                       ImageType imageType) throws WriterException, IOException {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content,
                    BarcodeFormat.QR_CODE, widthAndHeight, widthAndHeight, HINTS);
            Path path = FileSystems.getDefault().getPath(filePath, fileName);
            MatrixToImageWriter.writeToPath(bitMatrix, imageType.getValue(), path);// 输出图像
        }

        /**
         * 生成二维码带logo
         *
         * @param widthAndHeight    高宽
         * @param content           二维码内容
         * @param filePath          输出目录
         * @param fileName          输出文件名
         * @param imageType         图片类型（自定义枚举）
         * @param logoStream        logo输入流
         */
        public static void buildQRCodeWithLogo(int widthAndHeight, String content, String filePath, String fileName,
                                               ImageType imageType, InputStream logoStream) throws WriterException, IOException {
            BitMatrix bitMatrix = new MultiFormatWriter()
                    .encode(content, BarcodeFormat.QR_CODE, widthAndHeight, widthAndHeight, HINTS);// 生成矩阵
            MatrixToImageConfig config = new MatrixToImageConfig();
            BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix, config);
            BufferedImage logo = ImageIO.read(logoStream);
            int deltaHeight = widthAndHeight - logo.getHeight();
            int deltaWidth = widthAndHeight - logo.getWidth();
            BufferedImage combined = new BufferedImage(widthAndHeight, widthAndHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics2D = (Graphics2D) combined.getGraphics();
            graphics2D.drawImage(image, 0, 0, null);
            graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
            graphics2D.drawImage(logo, Math.round(deltaWidth / 2), Math.round(deltaHeight / 2), null);
            Path path = FileSystems.getDefault().getPath(filePath, fileName);
            ImageIO.write(combined, imageType.getValue(), path.toFile());
        }
    }


    /** QRCode解码 **/
    public static class Decode {

        private static final Map<DecodeHintType,Object> HINTS;
        private static final Map<DecodeHintType,Object> HINTS_PURE;
        static {
            HINTS = new EnumMap<DecodeHintType,Object>(DecodeHintType.class);
            HINTS.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
            HINTS.put(DecodeHintType.POSSIBLE_FORMATS, EnumSet.allOf(BarcodeFormat.class));
            HINTS_PURE = new EnumMap<DecodeHintType,Object>(HINTS);
            HINTS_PURE.put(DecodeHintType.PURE_BARCODE, Boolean.TRUE);
        }

        /**
         * 解析二维码
         */
        public static Collection<Result> readQRCode(File qrCode) throws ReaderException, IOException {
            FileInputStream inputStream = new FileInputStream(qrCode);
            return readQRCode(inputStream);
        }

        public static Collection<Result> readQRCode(InputStream inputStream) throws ReaderException, IOException {
            LuminanceSource source = new BufferedImageLuminanceSource(ImageIO.read(inputStream));
            BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(source));

            Collection<Result> results = new ArrayList<Result>(1);
            ReaderException savedException = null;
            Reader reader = new MultiFormatReader();
            try {
                //寻找多个条码
                MultipleBarcodeReader multiReader = new GenericMultipleBarcodeReader(reader);
                Result[] theResults = multiReader.decodeMultiple(binaryBitmap, HINTS);
                if (theResults != null) {
                    results.addAll(Arrays.asList(theResults));
                }
            } catch (ReaderException re) {
                savedException = re;
            }

            if (results.isEmpty()) {
                try {
                    //寻找纯条码
                    Result theResult = reader.decode(binaryBitmap, HINTS_PURE);
                    if (theResult != null) {
                        results.add(theResult);
                    }
                } catch (ReaderException re) {
                    savedException = re;
                }
            }

            if (results.isEmpty()) {
                try {
                    //寻找图片中的正常条码
                    Result theResult = reader.decode(binaryBitmap, HINTS);
                    if (theResult != null) {
                        results.add(theResult);
                    }
                } catch (ReaderException re) {
                    savedException = re;
                }
            }

            if (results.isEmpty()) {
                try {
                    //再次尝试其他特殊处理
                    BinaryBitmap hybridBitmap = new BinaryBitmap(new HybridBinarizer(source));
                    Result theResult = reader.decode(hybridBitmap, HINTS);
                    if (theResult != null) {
                        results.add(theResult);
                    }
                } catch (ReaderException re) {
                    savedException = re;
                }
            }
            if (results.isEmpty()){
                throw savedException;
            }else {
                return results;
            }
        }

        public static Result readQRCodeResult(File qrCode) throws ReaderException, IOException {
            FileInputStream inputStream = new FileInputStream(qrCode);
            return readQRCodeResult(inputStream);
        }

        public static Result readQRCodeResult(InputStream inputStream) throws ReaderException, IOException {
            Collection<Result> results = readQRCode(inputStream);
            if (!results.isEmpty()){
                //寻找结果集中非空的结果
                for (Result result : results){
                    if (result != null){
                        return result;
                    }
                }
            }
            throw NotFoundException.getNotFoundInstance();
        }
    }
}
