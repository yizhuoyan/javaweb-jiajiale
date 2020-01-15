package com.vip.web.controller.spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;


@Controller
public class CommonCssController {

    @Value("classpath:/static/css/common/*.css")
    Resource[] cssFiles;

    @GetMapping("/css/common.css")
    public void commonCss(HttpServletResponse resp) throws IOException {
        resp.setContentType("text/css;charset=utf-8");

        Arrays.sort(cssFiles, (a, b) -> a.getFilename().compareToIgnoreCase(b.getFilename()));

        try (ServletOutputStream out = resp.getOutputStream()) {
            byte[] buff = new byte[10240];
            int readed = 0;
            for (Resource f : cssFiles) {
                try (InputStream in = f.getInputStream()) {
                    while ((readed = in.read(buff)) != -1) {
                        out.write(buff, 0, readed);
                    }
                    out.println();
                    out.flush();
                }
            }
        }

    }
}
