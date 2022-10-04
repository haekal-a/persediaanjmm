package com.tamu.service.implementation;

import com.tamu.domain.model.ResponseModel;
import com.tamu.domain.table.Mfwp;
import com.tamu.service.ICetakanService;
import com.tamu.service.common.UtilityService;
import com.tamu.util.Constanta;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Transactional(rollbackOn = {Exception.class, RuntimeException.class})
public class CetakanService implements ICetakanService {

    @Autowired
    UtilityService utilityService;

    @Override
    public ResponseModel getCetakanWpOp(Mfwp mfwp) {
        ResponseModel res = new ResponseModel("Get data cetak WP OP");

        try (
                InputStream templateInputStream =
                        this.getClass().getClassLoader().getResourceAsStream(Constanta.TEMPLATE_UBAHDATA_WP_OP)
        ) {
            Workbook workbook = WorkbookFactory.create(templateInputStream);
            Sheet halaman1 = workbook.getSheetAt(0);
            Sheet halaman2 = workbook.getSheetAt(1);

            //  Cell npwp
            setCellNpwp(mfwp.getNpwp15(), halaman1.getRow(13), 16);

            //  cell Nama
            setGeneralCellValues(mfwp.getNamaWp(), halaman1, 15, 16, 26);
            setGeneralCellValues(mfwp.getNamaWp(), halaman1, 33, 16, 26);

            //  tgl Lahir
            SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
            if (mfwp.getTanggalLahir() != null) {
                String tgl = sdf.format(mfwp.getTanggalLahir());
                Row rowtglLahir = halaman1.getRow(38);
                rowtglLahir.getCell(32).setCellValue(String.valueOf(tgl.charAt(0)));
                rowtglLahir.getCell(33).setCellValue(String.valueOf(tgl.charAt(1)));
                rowtglLahir.getCell(35).setCellValue(String.valueOf(tgl.charAt(2)));
                rowtglLahir.getCell(36).setCellValue(String.valueOf(tgl.charAt(3)));
                rowtglLahir.getCell(38).setCellValue(String.valueOf(tgl.charAt(4)));
                rowtglLahir.getCell(39).setCellValue(String.valueOf(tgl.charAt(5)));
                rowtglLahir.getCell(40).setCellValue(String.valueOf(tgl.charAt(6)));
                rowtglLahir.getCell(41).setCellValue(String.valueOf(tgl.charAt(7)));
            }

            //  NIK
            setGeneralCellValues(mfwp.getNomorIdentitas(), halaman1, 45, 25, 17);

            //  No Telepon
            setGeneralCellValues(mfwp.getNomorTelepon(), halaman1, 53, 16, 26);
            setGeneralCellValues(mfwp.getNomorTelepon(), halaman2, 56, 15, 12);

            //  Email
            setGeneralCellValues(mfwp.getEmail().toUpperCase(), halaman1, 55, 16, 26);

            //  Jalan
            setGeneralCellValues(mfwp.getAlamat(), halaman2, 39, 15, 26);

            //  Kelurahan
            setGeneralCellValues(mfwp.getKelurahan(), halaman2, 46, 15, 26);

            //  Kecamatan
            setGeneralCellValues(mfwp.getKecamatan(), halaman2, 48, 15, 26);

            //  Kota
            setGeneralCellValues(mfwp.getKota(), halaman2, 50, 15, 26);

            //  Propinsi
            setGeneralCellValues(mfwp.getPropinsi(), halaman2, 52, 15, 26);

            //  Kode Pos
            setGeneralCellValues(mfwp.getKodePos(), halaman2, 54, 15, 5);

            //  No Fax
            setGeneralCellValues(mfwp.getNomorFax(), halaman2, 56, 31, 10);

            SimpleDateFormat formatTgl = new SimpleDateFormat("dd-MM-yyyy");
            String tgl = utilityService.stringDdmmyyyToIndonesianDate(formatTgl.format(new Date()), "Tanggal TTD");
            halaman2.getRow(65).getCell(34).setCellValue(String.valueOf(tgl));
            halaman2.getRow(70).getCell(28).setCellValue(String.valueOf(mfwp.getNamaWp()));

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            byte[] xlsxByte = outputStream.toByteArray();
            res.setObject(xlsxByte);
        } catch (IOException | EncryptedDocumentException | InvalidFormatException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public ResponseModel getCetakanWpBadan(Mfwp mfwp) {
        ResponseModel res = new ResponseModel("Get data cetak WP Badan");

        try (
                InputStream templateInputStream =
                        this.getClass().getClassLoader().getResourceAsStream(Constanta.TEMPLATE_UBAHDATA_WP_BADAN)
        ) {
            Workbook workbook = WorkbookFactory.create(templateInputStream);
            Sheet halaman1 = workbook.getSheetAt(0);
            Sheet halaman2 = workbook.getSheetAt(1);

            //  Cell npwp
            setCellNpwp(mfwp.getNpwp15(), halaman1.getRow(9), 16);

            //  cell Nama
            setGeneralCellValues(mfwp.getNamaWp(), halaman1, 11, 16, 26);
            setGeneralCellValues(mfwp.getNamaWp(), halaman1, 18, 16, 26);

            //  Jalan
            setGeneralCellValues(mfwp.getAlamat(), halaman1, 23, 16, 26);

            //  Kelurahan
            setGeneralCellValues(mfwp.getKelurahan(), halaman1, 30, 16, 26);

            //  Kecamatan
            setGeneralCellValues(mfwp.getKecamatan(), halaman1, 32, 16, 26);

            //  Kota
            setGeneralCellValues(mfwp.getKota(), halaman1, 34, 16, 26);

            //  Kode Pos
            setGeneralCellValues(mfwp.getKodePos(), halaman1, 36, 16, 5);

            //  Propinsi
            setGeneralCellValues(mfwp.getPropinsi(), halaman1, 38, 16, 26);

            //  No Telepon
            setGeneralCellValues(mfwp.getNomorTelepon(), halaman1, 40, 16, 12);
            setGeneralCellValues(mfwp.getNomorTelepon(), halaman1, 42, 16, 26);

            //  No Fax
            setGeneralCellValues(mfwp.getNomorFax(), halaman1, 40, 32, 10);

            //  Email
            setGeneralCellValues(mfwp.getEmail().toUpperCase(), halaman1, 45, 16, 26);

            SimpleDateFormat formatTgl = new SimpleDateFormat("dd-MM-yyyy");
            String tgl = utilityService.stringDdmmyyyToIndonesianDate(formatTgl.format(new Date()), "Tanggal TTD");
            halaman2.getRow(35).getCell(33).setCellValue(String.valueOf(tgl));

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            byte[] xlsxByte = outputStream.toByteArray();
            res.setObject(xlsxByte);
        } catch (IOException | EncryptedDocumentException | InvalidFormatException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public ResponseModel getCetakanWpPemerintah(Mfwp mfwp) {
        ResponseModel res = new ResponseModel("Get data cetak ");

        try (
                InputStream templateInputStream =
                        this.getClass().getClassLoader().getResourceAsStream(Constanta.TEMPLATE_UBAHDATA_WP_PEMERINTAH)
        ) {
            Workbook workbook = WorkbookFactory.create(templateInputStream);
            Sheet halaman1 = workbook.getSheetAt(0);

            //  Cell npwp
            setCellNpwp(mfwp.getNpwp15(), halaman1.getRow(14), 13);

            //  cell Nama
            setGeneralCellValues(mfwp.getNamaWp(), halaman1, 16, 13, 26);
            setGeneralCellValues(mfwp.getNamaWp(), halaman1, 29, 13, 26);

            //  Jalan
            setGeneralCellValues(mfwp.getAlamat(), halaman1, 34, 13, 26);

            //  Kelurahan
            setGeneralCellValues(mfwp.getKelurahan(), halaman1, 41, 13, 26);

            //  Kecamatan
            setGeneralCellValues(mfwp.getKecamatan(), halaman1, 43, 13, 26);

            //  Kota
            setGeneralCellValues(mfwp.getKota(), halaman1, 45, 13, 26);

            //  Propinsi
            setGeneralCellValues(mfwp.getPropinsi(), halaman1, 47, 13, 26);

            //  Kode Pos
            setGeneralCellValues(mfwp.getKodePos(), halaman1, 49, 13, 5);

            //  No Telepon
            setGeneralCellValues(mfwp.getNomorTelepon(), halaman1, 51, 13, 12);
            setGeneralCellValues(mfwp.getNomorTelepon(), halaman1, 53, 13, 26);

            //  No Fax
            setGeneralCellValues(mfwp.getNomorFax(), halaman1, 51, 29, 10);

            //  Email
            setGeneralCellValues(mfwp.getEmail().toUpperCase(), halaman1, 55, 13, 26);

            SimpleDateFormat formatTgl = new SimpleDateFormat("dd-MM-yyyy");
            String tgl = utilityService.stringDdmmyyyToIndonesianDate(formatTgl.format(new Date()), "Tanggal TTD");
            halaman1.getRow(91).getCell(33).setCellValue(String.valueOf(tgl));

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            byte[] xlsxByte = outputStream.toByteArray();
            res.setObject(xlsxByte);
        } catch (IOException | EncryptedDocumentException | InvalidFormatException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    private void setGeneralCellValues(String values, Sheet sheet, int row, int colStart, int colLength) {
        if (!(values.equals("") || values == null)) {
            char[] chars = values.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                if (i < colLength) {
                    sheet.getRow(row).getCell(colStart + i).setCellValue(String.valueOf(chars[i]));
                } else {
                    sheet.getRow(row + 1).getCell(colStart).setCellValue(String.valueOf(chars[i]));
                    colStart += 1;
                }
            }
        }
    }

    private void setCellNpwp(String npwp, Row row, int colStart) {
        row.getCell(colStart).setCellValue(String.valueOf(npwp.charAt(0)));
        row.getCell(colStart + 1).setCellValue(String.valueOf(npwp.charAt(1)));
        row.getCell(colStart + 3).setCellValue(String.valueOf(npwp.charAt(2)));
        row.getCell(colStart + 4).setCellValue(String.valueOf(npwp.charAt(3)));
        row.getCell(colStart + 5).setCellValue(String.valueOf(npwp.charAt(4)));
        row.getCell(colStart + 7).setCellValue(String.valueOf(npwp.charAt(5)));
        row.getCell(colStart + 8).setCellValue(String.valueOf(npwp.charAt(6)));
        row.getCell(colStart + 9).setCellValue(String.valueOf(npwp.charAt(7)));
        row.getCell(colStart + 11).setCellValue(String.valueOf(npwp.charAt(8)));
        row.getCell(colStart + 13).setCellValue(String.valueOf(npwp.charAt(9)));
        row.getCell(colStart + 14).setCellValue(String.valueOf(npwp.charAt(10)));
        row.getCell(colStart + 15).setCellValue(String.valueOf(npwp.charAt(11)));
        row.getCell(colStart + 17).setCellValue(String.valueOf(npwp.charAt(12)));
        row.getCell(colStart + 18).setCellValue(String.valueOf(npwp.charAt(13)));
        row.getCell(colStart + 19).setCellValue(String.valueOf(npwp.charAt(14)));
    }
}
