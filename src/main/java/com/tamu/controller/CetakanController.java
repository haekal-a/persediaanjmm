package com.tamu.controller;

import com.tamu.domain.model.ResponseModel;
import com.tamu.service.ICetakanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@RestController
@RequestMapping("/cetakan")
public class CetakanController {

    @Autowired
    ICetakanService cetakanService;

    @GetMapping("/wpop")
    public void getCetakanWpOp(HttpServletResponse response) throws IOException {
        /*ResponseModel res = cetakanService.getCetakanWpOp();
        byte[] xlsxByte = (byte[]) res.getObject();
        try (
                InputStream in = new ByteArrayInputStream(xlsxByte);
                OutputStream out = response.getOutputStream()
        ) {
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition",
                    "attachment;filename=Formulir Perubahan Data OP - .xls");
            byte[] buffer = new byte[1048];
            int numBytesRead;
            while ((numBytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, numBytesRead);
            }
        }
        response.flushBuffer();*/
    }

}
