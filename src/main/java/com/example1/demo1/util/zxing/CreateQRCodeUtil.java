package com.example1.demo1.util.zxing;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.io.File;
import java.nio.file.Path;
import java.util.HashMap;

/**
 * 二维码生成到本地磁盘
 * @author 程二狗
 * @since 2019/7/10 0010 0:36
 */
public class CreateQRCodeUtil {
    public static void main(String[] args){
        // 定义参数：
        int width = 300; // 图片宽度
        int height = 300; // 图片高度
        String format = "png"; // 图片格式
        String content = " http://mm3e93.natappfree.cc/hello";// 二维码内容

        // 定义二维码的参数
        HashMap param = new HashMap();
        param.put(EncodeHintType.CHARACTER_SET, "utf-8");
        param.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        param.put(EncodeHintType.MARGIN, 2);

        // 生成二维码
        // 1.定义HashMap hints
        // 2.hints调用put函数设置字符集、间距以及纠错度为M
        // 3.最后用MultiformatWriter函数类调用echoed函数并返回一个值 然后写入文件

        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, param);
            // 这里路径后面的img.png不可省略，前面是自己选取生成的图片地址，直接在磁盘上生成
            Path file = new File("G:/img.png").toPath();
            MatrixToImageWriter.writeToPath(bitMatrix, format, file);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
