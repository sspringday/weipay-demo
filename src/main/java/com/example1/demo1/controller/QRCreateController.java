package com.example1.demo1.controller;

import com.example1.demo1.util.zxing.QuickMarkUtil;
import com.google.zxing.WriterException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author 程二狗
 * @since 2019/7/10 0010 10:43
 */

@Controller
public class QRCreateController {

    @RequestMapping("/getQr")
    public void getQr(HttpServletRequest requset, HttpServletResponse response)  throws ServletException, IOException {
//        HttpSession session = requset.getSession(true);
//        User ordinary_user = (User) session.getAttribute("ordinary_user");
//        String parameter = requset.getParameter("url");
        String url = "https://www.baidu.com";
//        System.out.println(url);

        // 设置响应的类型格式为图片格式
        response.setContentType("image/jpeg");
        // 禁止图像缓存
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        try {
            QuickMarkUtil.createQrCode(response.getOutputStream(), url, 900, "JPEG");
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

}
