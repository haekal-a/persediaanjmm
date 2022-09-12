package com.tamu.service.common;

import com.tamu.domain.model.datatables.mapping.DataTablesOutput;
import com.tamu.util.AppException;
import org.apache.tomcat.util.http.fileupload.FileUtils;
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
}
