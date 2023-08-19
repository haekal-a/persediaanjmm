package com.tamu.service.common;

import com.tamu.domain.model.datatables.mapping.DataTablesOutput;
import com.tamu.util.AppException;
import com.tamu.util.Constanta;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.*;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;

@Service
public class UtilityService {


    @Value("${app.path.root}")
    private String root;

    public static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(UtilityService.class);

    public DataTablesOutput dataTablesOutputWrapper(List data, Long count, int draw) {
        DataTablesOutput out = new DataTablesOutput();
        out.setDraw(draw);
        out.setRecordsFiltered(count);
        out.setRecordsTotal(count);
        out.setData(data);
        return out;
    }

    public void createDirectories(String path) {
        File d = new File(path);
        if (!d.exists()) {
            d.mkdirs();
        }
    }

    public String stringDdmmyyyToIndonesianDate(String ddmmyyyy, String namaField) {
        if (ddmmyyyy == null || ddmmyyyy.equals("")) {
            return "";
        }
        String[] temp = ddmmyyyy.split("-");
        String res = "";
        if (temp.length != 3) {
            throw new AppException(AppException.LEVEL_INFO, "Format " + namaField + " salah.");
        }
        temp[1] = Constanta.MASA_PAJAK[Integer.parseInt(temp[1])];
        res = temp[0] + " " + temp[1] + " " + temp[2];
        return res;
    }

    public static String cleanParam(String arg0) {
        return Jsoup.clean(arg0.replaceAll("\"", Matcher.quoteReplacement(""))
                , Whitelist.basic());
    }
}
