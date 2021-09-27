package com.kondangan.service.common;

import com.kondangan.domain.model.datatables.mapping.DataTablesOutput;
import com.kondangan.domain.table.DocumentFile;
import com.kondangan.repository.DocumentFileRepo;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
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
        if (!file.isEmpty()) {
            String filePath = "";

            // normalize the file path
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

            // save the file on the local file system
            try {
                filePath = root + File.separator + idDetailDocument + File.separator + idJenisDocument + File.separator + fileName;
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
            // deleting all files
            for (DocumentFile documentFile : documentFileList){
                Path path = FileSystems.getDefault().getPath(documentFile.getPath());
                try {
                    Files.deleteIfExists(path);
                } catch (IOException x) {
                    System.err.println(x);
                }
            }

            // deleting directory
            Path pathToBeDeleted = Paths.get(root + File.separator + idDetailDocument);
            Files.walk(pathToBeDeleted)
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);

            // deleting all documentFiles
            documentFileRepo.deleteAll(documentFileList);
        }
    }
}
