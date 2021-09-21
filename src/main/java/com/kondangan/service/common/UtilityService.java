package com.kondangan.service.common;

import com.kondangan.domain.model.datatables.mapping.DataTablesOutput;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class UtilityService {

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
