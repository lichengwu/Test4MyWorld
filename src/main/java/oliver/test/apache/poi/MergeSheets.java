package oliver.test.apache.poi;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.junit.Test;

/**
 * merge sheets
 *
 * @author lichengwu
 * @version 1.0
 * @created 2012-11-15 3:43 PM
 */
public class MergeSheets {

    List<String> urlList = new ArrayList<>(Arrays
            .asList("/attachment/upload", "/attachment/show", "/contract/save", "/audit/commit", "/audit/startAudit", "/predeal/create", "/change/commit", "/contract/edit","/product/edit","/payPlan/edit","/bankInfo/edit"));

    @Test
    public void test() throws IOException {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(
                    "E:\\Dropbox\\document\\meituan\\url_request.xls");
            POIFSFileSystem fs = new POIFSFileSystem(fis);
            HSSFWorkbook wb = new HSSFWorkbook(fs);
            for (String iUrl : urlList) {
                System.out.print(iUrl + ",");
                for (int i = 0; i < wb.getNumberOfSheets(); i++) {
                    int hit = 0;
                    int total = 0;
                    HSSFSheet sheet = wb.getSheetAt(i);
                    for (int j = 0; j <= sheet.getLastRowNum(); j++) {
                        String url = sheet.getRow(j).getCell(0).getStringCellValue();
                        if (url.startsWith(iUrl)) {
                            // System.out.println(i+":"+j);
                            hit += new Double(sheet.getRow(j).getCell(2).getNumericCellValue()).intValue();
                            total += new Double(sheet.getRow(j).getCell(2).getNumericCellValue())
                                    .intValue()
                                    * new Double(sheet.getRow(j).getCell(3).getNumericCellValue())
                                    .intValue();
                        }
                    }
                    System.out.print(hit + "(" + total / Math.max(hit, 1)+")" + ",");
                }
                System.out.println();
            }
        } finally {
            if (fis != null) {
                fis.close();
            }
        }
    }
}
