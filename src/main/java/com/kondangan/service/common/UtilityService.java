package com.kondangan.service.common;

import com.kondangan.domain.model.datatables.mapping.DataTablesOutput;
import com.kondangan.domain.table.DocumentFile;
import com.kondangan.repository.DocumentFileRepo;
import com.kondangan.util.AppException;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Service
public class UtilityService {


    @Value("${app.path.root}")
    private String root;

    @Autowired
    private DocumentFileRepo documentFileRepo;

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

    public void saveFile(MultipartFile file, String idDetailDocument, String idJenisDocument) throws IOException {
        if (file != null) {
            String filePath = "";

            // normalize the file path
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

            // save the file on the local file system
            try {
                filePath = root + "/" + idDetailDocument + "/" + idJenisDocument + "/" + fileName;
                createDirectories(filePath);
                Path path = Paths.get(filePath);
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }

            DocumentFile documentFile = documentFileRepo.findByIdDetailDocumentAndIdJenisDocument(idDetailDocument, idJenisDocument);
            if (Objects.isNull(documentFile)) {
                documentFile = new DocumentFile();
                documentFile.setIdDetailDocument(idDetailDocument);
                documentFile.setIdJenisDocument(idJenisDocument);
            }
            documentFile.setDocumentName(file.getOriginalFilename());
            documentFile.setPath(filePath);
            documentFileRepo.save(documentFile);
        }
    }

    public void deleteFile(String idDetailDocument) throws IOException {
        List<DocumentFile> documentFileList = documentFileRepo.findAllByIdDetailDocument(idDetailDocument);
        if (!documentFileList.isEmpty()){
            try {
                // deleting file & directory
                FileUtils.deleteDirectory(new File(root + "/" + idDetailDocument));

                // deleting all documentFiles
                documentFileRepo.deleteAll(documentFileList);
            } catch (IOException x) {
                System.err.println(x);
            }
        }
    }

    public DocumentFile getDocumentFile(String idDocument, BigDecimal idUser){
        DocumentFile documentFile = documentFileRepo.findByIdDocument(new BigDecimal(idDocument));
        if (Objects.isNull(documentFile)){ throw new AppException(2, "File tidak ditemukan");}
        return documentFile;
    }
}
