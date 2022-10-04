//package com.tamu.service.common;
//
//import org.docx4j.XmlUtils;
//import org.docx4j.convert.in.xhtml.XHTMLImporter;
//import org.docx4j.convert.in.xhtml.XHTMLImporterImpl;
//import org.docx4j.dml.wordprocessingDrawing.Inline;
//import org.docx4j.jaxb.Context;
//import org.docx4j.openpackaging.contenttype.ContentType;
//import org.docx4j.openpackaging.exceptions.Docx4JException;
//import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
//import org.docx4j.openpackaging.parts.PartName;
//import org.docx4j.openpackaging.parts.WordprocessingML.AlternativeFormatInputPart;
//import org.docx4j.openpackaging.parts.WordprocessingML.BinaryPartAbstractImage;
//import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
//import org.docx4j.org.apache.poi.util.IOUtils;
//import org.docx4j.relationships.Relationship;
//import org.docx4j.wml.*;
//
//import javax.xml.bind.JAXBElement;
//import javax.xml.bind.JAXBException;
//import java.io.*;
//import java.util.*;
//
///**
// * @className	： WMLPackageUtils
// * @description	： TODO(描述这个类的作用)
// * @author 		： <a href="https://github.com/vindell">vindell</a>
// * @date		： 2017年5月24日 下午10:28:03
// * @version 	V1.0
// */
//@SuppressWarnings("unchecked")
//public class WMLPackageUtils {
//
//    protected static String CONTENT_TYPE = "";
//
//    /**
//     * 首先，我们创建一个可用作模版的简单的word文档。对于此只需打开Word，创建新文档然后保存为template.docx，这就是我们将要用于添加内容的word文档。
//     * 我们需要做的第一件事是使用docx4j将这个文档加载进来，你可以使用下面的几行代码做这件事：
//     */
//    public static WordprocessingMLPackage getWMLPackageTemplate(String filepath) throws Docx4JException, FileNotFoundException {
//        WordprocessingMLPackage template = WordprocessingMLPackage.load(new FileInputStream(new File(filepath)));
//        return template;
//    }
//
//    /**
//     * 这样会返回一个表示完整的空白（在此时）文档Java对象。现在我们可以使用Docx4J API添加、删除以及更新这个word文档的内容，Docx4J有一些你可以用于遍历该文档的工具类。
//     * 我自己写了几个助手方法使查找指定占位符并用真实内容进行替换的操作变地很简单。让我们来看一下其中的一个，这个计算是几个JAXB计算的包装器，
//     * 允许你针对一个特定的类来搜索指定元素以及它所有的孩子，例如，你可以用它获取文档中所有的表格、表格中所有的行以及其它类似的操作。
//     */
//    public static List<Object> getTargetElements(Object source, Class<?> targetClass) {
////        List<T> result = new ArrayList<T>();
////        //获取真实的对象
////        Object target = XmlUtils.unwrap(source);
////        //if (target.getClass().equals(targetClass)) {
////        if (targetClass.isAssignableFrom(target.getClass())) {
////            result.add((T) target);
////        } else if (target instanceof ContentAccessor) {
////            List<?> children = ((ContentAccessor) target).getContent();
////            for (Object child : children) {
////                result.addAll(getTargetElements(child, targetClass));
////            }
////        }
//        List<Object> result = new ArrayList<Object>();
//        if (source instanceof JAXBElement) {
//            source = ((JAXBElement<?>) source).getValue();
//        }
//
//        if (source != null && source.getClass().equals(targetClass)) {
//            result.add(source);
//        } else if (source instanceof ContentAccessor) {
//            List<?> children = ((ContentAccessor) source).getContent();
//            for (Object child : children) {
//                result.addAll(getTargetElements(child, targetClass));
//            }
//        }
//
//        return result;
//    }
//
//    public static List getInnerTable(Tbl mainTable) {
//        List res = new ArrayList();
//        List<?> rows = getTargetElements(mainTable, Tr.class);
//        for (Object row : rows) {
//            List cols = getTargetElements(row, Tc.class);
//            for (Object tcObj : cols) {
//                Tc tc = (Tc) tcObj;
//                res.addAll(getTargetElements(tc, Tbl.class));
//            }
//        }
//        return res;
//    }
//
//    /**
//     * 该方法找到表格，获取第一行并且遍历提供的map向表格添加新行，在将其返回之前删除模版行。这个方法用到了两个助手方法：addRowToTable 和 getTemplateTable。我们首先看一下后面的那个：
//     */
//    public static Tbl getTable(List<Tbl> tables, String placeholder) throws Docx4JException {
////        System.out.println("tables size " + tables.size());
//        int i = 0;
//
//        for (Iterator<Tbl> iterator = tables.iterator(); iterator.hasNext(); ) {
////            System.out.println("row index " + i++);
//            Tbl tbl = iterator.next();
//            //查找当前table下面的text对象
//            List<?> textElements = getTargetElements(tbl, Text.class);
//            int j = 0;
//            for (Object text : textElements) {
//                Text textElement = (Text) text;
//                //
//                if (textElement.getValue() != null && textElement.getValue().equals(placeholder))  {
//                    return tbl;
//                }
//            }
//        }
//        return null;
//    }
//
//    /**
//     *
//     * created by ripato
//     *
//     * @param placeholders : placeholder tabel colom pertama row data (bukan row header)
//     * @param textToAdd : hashmap data yang akan dimasukkan ke dalam tabel
//     * @param template : template document
//     * @param startRowData : urutan mulai row data, dari 0, jika ada header berarti mulai dari row 1
//     * @param footerIsExist : jika dibagian bawah tabel ada footer, misalnya total
//     * @throws Docx4JException
//     * @throws JAXBException
//     */
//    public static void replaceTable(String[] placeholders, List<Map<String, String>> textToAdd,
//                                    WordprocessingMLPackage template, int startRowData, boolean footerIsExist) throws Docx4JException, JAXBException {
//
//        List<?> tables = getTargetElements(template.getMainDocumentPart(), Tbl.class);
//        // clean Non Printale ASCII char
//        for (Map<String, String> replacements : textToAdd) {
//            for (Map.Entry<String, String> entry : replacements.entrySet()) {
//                if(entry.getValue() != null) {
//                    if(entry.getValue().indexOf("\n")!=-1) {
//                        entry.setValue(entry.getValue().replaceAll("\n","<enter>"));
//                    }
//                    entry.setValue(entry.getValue().replaceAll("\\P{Print}", ""));
//                }
//            }
//        }
//
//        // 1. find the table
//        Tbl tempTable = getTable((List<Tbl>) tables, placeholders[0]);
//        List<?> rows = getTargetElements(tempTable, Tr.class);
//
//        // if first row is not header
//        if (rows.size() == 1) {
//            // this is our template row
//            Tr templateRow = (Tr) rows.get(0);
//
//            for (Map<String, String> replacements : textToAdd) {
//                // 2 and 3 are done in this method
//                addRowToTable(tempTable, templateRow, replacements);
//            }
//
//            // 4. remove the template row
//            tempTable.getContent().remove(templateRow);
//        }
//        // first row is header, second row is content
//        else if (rows.size() == 2) {
//
//            // this is our template row
//            Tr templateRow = (Tr) rows.get(startRowData);
//            Tr footerRow = null;
//            if (footerIsExist) {
//                // third row
//                footerRow = (Tr) rows.get(startRowData + 1);
//            }
//
//            for (Map<String, String> replacements : textToAdd) {
//                // 2 and 3 are done in this method
//                addRowToTable(tempTable, templateRow, replacements);
//            }
//
//            // 4. remove the template row
//            tempTable.getContent().remove(templateRow);
//            if (footerIsExist) {
//                // third row
//                tempTable.getContent().remove(footerRow);
//                tempTable.getContent().add(footerRow);
//            }
//
//        }
//        // if third row is total
//        else if (rows.size() > 2) {
//            // this is our template row
//            Tr templateRow = (Tr) rows.get(startRowData);
//            // third row
//            Tr footerRow = null;
//            if (footerIsExist) {
//                footerRow = (Tr) rows.get(startRowData + 1);
//            }
//
//            for (Map<String, String> replacements : textToAdd) {
//                // 2 and 3 are done in this method
//                addRowToTable(tempTable, templateRow, replacements);
//            }
//
//            // 4. remove the template row
//            tempTable.getContent().remove(templateRow);
//            if (footerIsExist) {
//                tempTable.getContent().remove(footerRow);
//                // 5. insert the third row
//                tempTable.getContent().add(footerRow);
//            }
//        }
//    }
//
//    /**
//     * created by ripato
//     *
//     * @param placeholders  : placeholder tabel colom pertama row data (bukan row header)
//     * @param textToAdd     : hashmap data yang akan dimasukkan ke dalam tabel
//     * @param template      : template document
//     * @param startRowData  : urutan mulai row data, dari 0, jika ada header berarti mulai dari row 1
//     * @param footerIsExist : jika dibagian bawah tabel ada footer, misalnya total
//     * @throws Docx4JException
//     * @throws JAXBException
//     */
//    public static void replaceTableInNestedTable(String[] placeholders, List<Map<String, String>> textToAdd,
//                                                 WordprocessingMLPackage template, int startRowData, boolean footerIsExist) throws Docx4JException, JAXBException {
//
//        List<?> tablesOuter = getTargetElements(template.getMainDocumentPart(), Tbl.class);
//
//        // 1. find the table
//        Tbl tempTable = null;
//        int i = 1;
//        for (Object temp : tablesOuter) {
////            System.out.println("tabel ke " + i++);
//            List<?> tables = getInnerTable((Tbl) temp);
//            tempTable = getTable((List<Tbl>) tables, placeholders[0]);
//            if (tempTable != null) break;
//        }
//
//        List<?> rows = getTargetElements(tempTable, Tr.class);
//
//        // if first row is not header
//        if (rows.size() == 1) {
//            // this is our template row
//            Tr templateRow = (Tr) rows.get(0);
//
//            for (Map<String, String> replacements : textToAdd) {
//                // 2 and 3 are done in this method
//                addRowToTable(tempTable, templateRow, replacements);
//            }
//
//            // 4. remove the template row
//            tempTable.getContent().remove(templateRow);
//        }
//        // first row is header, second row is content
//        else if (rows.size() == 2) {
//
//            // this is our template row
//            Tr templateRow = (Tr) rows.get(startRowData);
//            Tr footerRow = null;
//            if(footerIsExist){
//                // third row
//                footerRow = (Tr) rows.get(startRowData+1);
//            }
//
//            for (Map<String, String> replacements : textToAdd) {
//                // 2 and 3 are done in this method
//                addRowToTable(tempTable, templateRow, replacements);
//            }
//
//            // 4. remove the template row
//            tempTable.getContent().remove(templateRow);
//            if(footerIsExist){
//                // third row
//                tempTable.getContent().remove(footerRow);
//                tempTable.getContent().add(footerRow);
//            }
//
//        }
//        // if third row is total
//        else if (rows.size() > 2 ) {
//            // this is our template row
//            Tr templateRow = (Tr) rows.get(startRowData);
//            // third row
//            Tr footerRow = null;
//            if(footerIsExist) {
//                footerRow = (Tr) rows.get(startRowData + 1);
//            }
//
//            for (Map<String, String> replacements : textToAdd) {
//                // 2 and 3 are done in this method
//                addRowToTable(tempTable, templateRow, replacements);
//            }
//
//            // 4. remove the template row
//            tempTable.getContent().remove(templateRow);
//            if(footerIsExist) {
//                tempTable.getContent().remove(footerRow);
//                // 5. insert the third row
//                tempTable.getContent().add(footerRow);
//            }
//        }
//    }
//
//    /**
//     * created by ripato
//     *
//     * @param reviewtable : tabel yang telah diselect
//     * @param templateRow : row yang telah diselect
//     * @param replacements : hashmap data row yang akan ditambahkan
//     */
//    public static void addRowToTable(Tbl reviewtable, Tr templateRow, Map<String, String> replacements) {
//        Tr workingRow = XmlUtils.deepCopy(templateRow);
//
//        List cols = getTargetElements(workingRow, Tc.class);
//
//        for(Object tcObj : cols){
//            Tc tc = (Tc) tcObj;
//            if(tc.getContent().size() > 0) {
//                for(int i = 0 ; i < tc.getContent().size() ; i++){
//                    String replacementValue = replacements.get(tc.getContent().get(i).toString());
//                    if(replacementValue != null){
//                        P copy = (P) tc.getContent().get(i);
//                        String[] temp = replacementValue.split("<enter>");
//                        List texts = getTargetElements(copy, Text.class);
//                        if(temp.length == 1){
//                            if (texts.size() > 0) {
//                                Text oldText = (Text) texts.get(0);
//                                oldText.setValue(temp[0]);
//                                oldText.setSpace("preserve");
//                                for(int j = 1 ; j < texts.size() ; j++) {
//                                    ((Text) texts.get(j)).setValue("");
//                                }
//                            }
//                        }
//                        if(temp.length > 1) {
//                            if (texts.size() > 0) {
//                                for(int j = 0 ; j < texts.size() ; j++) {
//                                    ((Text) texts.get(j)).setValue("");
//                                }
//                            }
//                            ObjectFactory factory = Context.getWmlObjectFactory();
//                            List r = getTargetElements(copy, R.class);
//                            if(r.size() > 0){
//                                R runOld = (R) r.get(0);
//                                R run =  factory.createR();
//                                //kalo ada <enter> maka tambah nye line di P ini
//                                for(int k = 0 ; k < temp.length ; k++){
//                                    Text t2 = factory.createText();
//                                    t2.setValue(temp[k]);
//                                    run.getContent().add(t2);
//                                    if(k != temp.length-1){
//                                        Br br = factory.createBr(); // this Br element is used break the current and go for next line
//                                        run.getContent().add(br);
//                                    }
//                                }
//                                runOld.getContent().remove(0);
//                                runOld.getContent().addAll(run.getContent());
//                            }
//                        }
//
//                    }
//                }
//            }
//        }
//
////        List<?> textElements = getTargetElements(workingRow, Text.class);
////        for (Object object : textElements) {
////            Text text = (Text) object;
////            String replacementValue = replacements.get(text.getValue());
////            if (replacementValue != null)
////                text.setValue(replacementValue);
////        }
//        reviewtable.getContent().add(workingRow);
//    }
//
//    public static InputStream mergeDocx(final List<InputStream> streams)  throws Docx4JException, IOException {
//
//        WordprocessingMLPackage target = null;
//        final File generated = File.createTempFile("generated", ".docx");
//
//        int chunkId = 0;
//        Iterator<InputStream> it = streams.iterator();
//        while (it.hasNext()) {
//            InputStream is = it.next();
//            if (is != null) {
//                if (target == null) {
//                    // Copy first (master) document
//                    OutputStream os = new FileOutputStream(generated);
//                    os.write(IOUtils.toByteArray(is));
//                    os.close();
//                    target = WordprocessingMLPackage.load(generated);
//                } else {
//                    // Attach the others (Alternative input parts)
//                    insertDocx(target.getMainDocumentPart(),IOUtils.toByteArray(is), chunkId++);
//                }
//            }
//        }
//
//        if (target != null) {
//            target.save(generated);
//            return new FileInputStream(generated);
//        } else {
//            return null;
//        }
//    }
//
//    // 插入文档
//    private static void insertDocx(MainDocumentPart main, byte[] bytes, int chunkId) {
//        try {
//            AlternativeFormatInputPart afiPart = new AlternativeFormatInputPart(new PartName("/part" + chunkId + ".docx"));
//            afiPart.setContentType(new ContentType(CONTENT_TYPE));
//            afiPart.setBinaryData(bytes);
//            Relationship altChunkRel = main.addTargetPart(afiPart);
//
//            CTAltChunk chunk = Context.getWmlObjectFactory().createCTAltChunk();
//            chunk.setId(altChunkRel.getId());
//
//            main.addObject(chunk);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static P addInlineImageToParagraph(Inline inline) {
//        // Now add the in-line image to a paragraph
//        ObjectFactory factory = new ObjectFactory();
//        P paragraph = factory.createP();
//        R run = factory.createR();
//        paragraph.getContent().add(run);
//        Drawing drawing = factory.createDrawing();
//        run.getContent().add(drawing);
//        drawing.getAnchorOrInline().add(inline);
//        return paragraph;
//    }
//
//    private static Inline createInlineImage(InputStream file, float weightInCm,WordprocessingMLPackage wordMLPackage) throws Exception {
//        Long size = new Long(Math.round(weightInCm * 568));
//        byte[] bytes = convertIstoByteArray(file);
//        BinaryPartAbstractImage imagePart =
//                BinaryPartAbstractImage.createImagePart(wordMLPackage, bytes);
//        int docPrId = 1;
//        int cNvPrId = 2;
//        return imagePart.createImageInline("Filename hint",
//                "Alternative text", docPrId, cNvPrId, size,false);
//    }
//
//    private static byte[] convertIstoByteArray(InputStream in) throws IOException {
//
//        ByteArrayOutputStream os = new ByteArrayOutputStream();
//
//        byte[] buffer = new byte[1024];
//        int len;
//
//        // read bytes from the input stream and store them in buffer
//        while ((len = in.read(buffer)) != -1) {
//            // write bytes from the buffer into output stream
//            os.write(buffer, 0, len);
//        }
//
//        return os.toByteArray();
//    }
//
//    /**
//     * created by ripato
//     *
//     * @param wordMLPackage
//     * @param img
//     * @param imgSizeInCm
//     * @param placeholder
//     * @throws Exception
//     */
//    public static void addImageToPlaceholder(WordprocessingMLPackage wordMLPackage, InputStream img, float imgSizeInCm,String placeholder) throws Exception {
//        List elemetns = getTargetElements(wordMLPackage.getMainDocumentPart(), Tbl.class);
//        for(Object obj : elemetns){
//            if(obj instanceof Tbl){
//                Tbl table = (Tbl) obj;
//                List rows = getTargetElements(table, Tr.class);
//                for(Object trObj : rows){
//                    Tr tr = (Tr) trObj;
//                    List cols = getTargetElements(tr, Tc.class);
//                    for(Object tcObj : cols){
//                        Tc tc = (Tc) tcObj;
//                        if(tc.getContent().size() > 0) {
//                            for(int i = 0 ; i < tc.getContent().size() ; i++){
//                                if( tc.getContent().get(i).toString().equalsIgnoreCase(placeholder)){
//                                    P paragraphWithImage = addInlineImageToParagraph(createInlineImage(img, imgSizeInCm, wordMLPackage));
//                                    tc.getContent().remove(i);
//                                    tc.getContent().add(paragraphWithImage);
//                                    return;
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }
//
//    public static String newlineToBreak(String r) {
//
//        StringTokenizer st = new StringTokenizer(r, "\n\r\f"); // tokenize on the newline character, the carriage-return character, and the form-feed character
//        StringBuilder sb = new StringBuilder();
//
//        boolean firsttoken = true;
//        while (st.hasMoreTokens()) {
//            String line = st.nextToken();
//            if (firsttoken) {
//                firsttoken = false;
//            } else {
//                sb.append("</w:t><w:br/><w:t>");
//            }
//            sb.append(line);
//        }
//        return sb.toString();
//    }
//
//    /**
//     * created by ripato
//     *
//     * contoh cara pakai
//     *  String xhtml = "<table style=\"font-family:Arial; vertical-align: top; text-align: justify;\">" +
//     *                     "<tr style=\"vertical-align:top\">" +
//     *                     "<td>1.</td>" +
//     *                     "<td colspan=\"2\">" +
//     *                     "Melansir CNBC, berbagai platform sosial media dalam beberapa waktu terakhir telah menjadi tempat bersosialisasi secara digital yang memungkinkan penggunanya terhubung secara profesional. Oleh karena itu, " +
//     *                     "kini masyarakat bisa dengan mudah menemukan akun dan konten-konten dari para ahli keuangan yang bisa memberi solusi keuangan serta menambah literasi dan wawasan soal finansial." +
//     *                     "Baca artikel detikfinance, \"Tips  Trick Finansial yang Tidak Diajarkan di Sekolah!\" selengkapnya https://finance.detik.com/advertorial-news-block/d-5795686/tips--trick-finansial-yang-tidak-diajarkan-di-sekolah." +
//     *                     "Download Apps Detikcom Sekarang https://apps.detik.com/detik/" +
//     *                     "</td>" +
//     *                     "</tr>" +
//     *                     "<tr style=\"vertical-align:top\">" +
//     *                     "<td></td>" +
//     *                     "<td>a.</td>" +
//     *                     "<td>" +
//     *                     "Melansir CNBC, berbagai platform sosial media dalam beberapa waktu terakhir telah menjadi tempat bersosialisasi secara digital yang memungkinkan penggunanya terhubung secara profesional. Oleh karena itu, " +
//     *                     "kini masyarakat bisa dengan mudah menemukan akun dan konten-konten dari para ahli keuangan yang bisa memberi solusi keuangan serta menambah literasi dan wawasan soal finansial." +
//     *                     "Baca artikel detikfinance, \"Tips Trick Finansial yang Tidak Diajarkan di Sekolah!\" selengkapnya https://finance.detik.com/advertorial-news-block/d-5795686/tips--trick-finansial-yang-tidak-diajarkan-di-sekolah." +
//     *                     "Download Apps Detikcom Sekarang https://apps.detik.com/detik/" +
//     *                     "</td>" +
//     *                     "</tr>" +
//     *                     "<tr style=\"vertical-align:top\">" +
//     *                     "<td>2.</td>" +
//     *                     "<td colspan=\"2\">" +
//     *                     "Melansir CNBC, berbagai platform sosial media dalam beberapa waktu terakhir telah menjadi tempat bersosialisasi secara digital yang memungkinkan penggunanya terhubung secara profesional. Oleh karena itu, " +
//     *                     "kini masyarakat bisa dengan mudah menemukan akun dan konten-konten dari para ahli keuangan yang bisa memberi solusi keuangan serta menambah literasi dan wawasan soal finansial." +
//     *                     "Baca artikel detikfinance, \"Tips Trick Finansial yang Tidak Diajarkan di Sekolah!\" selengkapnya https://finance.detik.com/advertorial-news-block/d-5795686/tips--trick-finansial-yang-tidak-diajarkan-di-sekolah." +
//     *                     "Download Apps Detikcom Sekarang https://apps.detik.com/detik/" +
//     *                     "</td>" +
//     *                     "</tr>" +
//     *                     "</table>";
//     *
//     *             WMLPackageUtils.insertHtmlToDoc(wordMLPackage, xhtml, "PlaceholderForTable1");
//     *
//     *
//     * @param wordMLPackage
//     * @param xhtml
//     * @param placeholder
//     * @throws Docx4JException
//     */
//
//    public static void insertHtmlToDoc(WordprocessingMLPackage wordMLPackage, String xhtml, String placeholder) throws Docx4JException {
//        XHTMLImporter XHTMLImporter = new XHTMLImporterImpl(wordMLPackage);
//        int ct = 0;
//        List<Integer> tableIndexes = new ArrayList<>();
//        MainDocumentPart documentPart = wordMLPackage.getMainDocumentPart();
//        List<Object> documentContents = documentPart.getContent();
//        for (Object o: documentContents) {
//
//            if (o.toString().contains(placeholder)) {
//                tableIndexes.add(ct);
//            }
//            ct++;
//        }
//
//        for (Integer i: tableIndexes) {
//            documentPart.getContent().remove(i.intValue());
//            documentPart.getContent().addAll(i.intValue(), XHTMLImporter.convert( xhtml, null));
//        }
//    }
//
//}
