/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gisainvestment.utility;

import com.gisainvestment.DAO.InvestmentDAOimp;
import com.gisainvestment.domain.Investment;
import com.opensymphony.xwork2.ActionSupport;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author mcpaul
 */
public class FileHandler extends ActionSupport {

    private static Logger LOGGER = Logger.getLogger(FileHandler.class);
    private InputStream excelStream;
    private String excelFileName;

    public static InputStream generateExcel() {
        try {
            int rowNum = 1;
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet("DUE PAYABLE");
            HSSFRow header = sheet.createRow(0);
            HSSFCellStyle style = wb.createCellStyle();
            style.setAlignment(HorizontalAlignment.CENTER);

            HSSFCell cell;
            cell = header.createCell(0);
            cell.setCellValue("ID");
            cell.setCellStyle(style);

            cell = header.createCell(1);
            cell.setCellValue("REFERENCE");
            cell.setCellStyle(style);

            cell = header.createCell(2);
            cell.setCellValue("TICKET");
            cell.setCellStyle(style);

            cell = header.createCell(3);
            cell.setCellValue("DEPOSIT DATE");
            cell.setCellStyle(style);

            cell = header.createCell(4);
            cell.setCellValue("RENDERED AMOUNT");
            cell.setCellStyle(style);

            cell = header.createCell(5);
            cell.setCellValue("PAYMENT DATE");
            cell.setCellStyle(style);

            cell = header.createCell(6);
            cell.setCellValue("DUE PAYMENT");
            cell.setCellStyle(style);

            cell = header.createCell(7);
            cell.setCellValue("TOTAL");
            cell.setCellStyle(style);

            cell = header.createCell(8);
            cell.setCellValue("PHONE");
            cell.setCellStyle(style);
            cell.setCellStyle(style);

            cell = header.createCell(9);
            cell.setCellValue("TRANSACTION STATUS");
            cell.setCellStyle(style);
            cell.setCellStyle(style);

            cell = header.createCell(10);
            cell.setCellValue("TICKET STATUS");
            cell.setCellStyle(style);

            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (Investment inv : InvestmentDAOimp.calculateInvestment()) {
                HSSFRow row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(inv.getId());
                row.createCell(1).setCellValue(inv.getReference());
                row.createCell(2).setCellValue(inv.getTicket());
                row.createCell(3).setCellValue(fmt.format(inv.getInv_date()));
                row.createCell(4).setCellValue(inv.getAmount());
                row.createCell(5).setCellValue(fmt.format(inv.getInv_returnDate()));
                row.createCell(6).setCellValue(inv.getReceivableAmount());
                row.createCell(7).setCellValue(inv.getBalance());
                row.createCell(8).setCellValue(inv.getSource());
                row.createCell(9).setCellValue(inv.getTransactionStatus());
                row.createCell(10).setCellValue(inv.getStatus());
            }
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            wb.write(os);
            byte[] fileContent = os.toByteArray();
            ByteArrayInputStream is = new ByteArrayInputStream(fileContent);
            return is;
        } catch (IOException e) {
            LOGGER.error(e.toString());
            return null;
        }
    }

    public static InputStream generateExcelMinified() {
        try {
            int rowNum = 1;
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet("DUE PAYABLE MINIFIED");
            HSSFRow header = sheet.createRow(0);
            HSSFCellStyle style = wb.createCellStyle();
            style.setAlignment(HorizontalAlignment.CENTER);
            
            HSSFCell cell;
            cell = header.createCell(0);
            cell.setCellValue("TICKET");
            cell.setCellStyle(style);

            cell = header.createCell(1);
            cell.setCellValue("PHONE");
            cell.setCellStyle(style);
            cell.setCellStyle(style);

            cell = header.createCell(2);
            cell.setCellValue("RENDERED AMOUNT");
            cell.setCellStyle(style);

            cell = header.createCell(3);
            cell.setCellValue("DUE PAYMENT");
            cell.setCellStyle(style);
            
            cell = header.createCell(4);
            cell.setCellValue("DEPOSIT DATE");
            cell.setCellStyle(style);
            
            cell = header.createCell(5);
            cell.setCellValue("PAYMENT DATE");
            cell.setCellStyle(style);

            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (Investment inv : InvestmentDAOimp.calculateInvestment()) {
                HSSFRow row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(inv.getTicket());
                row.createCell(1).setCellValue(inv.getSource());
                row.createCell(2).setCellValue(inv.getAmount());
                row.createCell(3).setCellValue(inv.getReceivableAmount());
                row.createCell(4).setCellValue(fmt.format(inv.getInv_date()));
                row.createCell(5).setCellValue(fmt.format(inv.getInv_returnDate()));
            }
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            wb.write(os);

            byte[] fileContent = os.toByteArray();
            ByteArrayInputStream is = new ByteArrayInputStream(fileContent);
            return is;
        } catch (IOException e) {
            LOGGER.error(e.toString());
            return null;
        }
    }

    public static List<Investment> extractInvestmentsFromFile(File file) {
        try {
            List<Investment> listInvestment = new ArrayList<>();

            //Create Workbook instance holding reference to .xlsx file            
            Workbook workbook = new HSSFWorkbook(new FileInputStream(file));

            //Get first/desired sheet from the workbook
            Sheet sheet = workbook.getSheetAt(0);

            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                //For each row, iterate through all the columns
                Iterator<Cell> cellIterator = row.cellIterator();
                Investment inv = new Investment();

                if (row.getRowNum() > 0) {
                    while (cellIterator.hasNext()) {
                        Cell cell = cellIterator.next();
                        //Check the cell type and format accordingly
                        switch (cell.getColumnIndex()) {
                            case 0:
                                inv.setTicket(cell.getStringCellValue());
                                break;
                            case 1:
                                cell.setCellType(Cell.CELL_TYPE_STRING);
                                BigInteger cellValue = new BigInteger(cell.getStringCellValue());
                                inv.setSource(cellValue.toString());
                                break;
                            case 2:
                                inv.setAmount(cell.getNumericCellValue());
                                break;
                            case 3:
                                inv.setReceivableAmount(cell.getNumericCellValue());
                                break;

                        }
                    }
                    listInvestment.add(inv);
                
                }
            }

            return listInvestment;
        } catch (IOException e) {
            LOGGER.error(e.toString());
            return null;
        }
    }

    public static Logger getLOGGER() {
        return LOGGER;
    }

    public static void setLOGGER(Logger LOGGER) {
        FileHandler.LOGGER = LOGGER;
    }

    public InputStream getExcelStream() {
        return excelStream;
    }

    public void setExcelStream(InputStream excelStream) {
        this.excelStream = excelStream;
    }

    public String getExcelFileName() {
        return excelFileName;
    }

    public void setExcelFileName(String excelFileName) {
        this.excelFileName = excelFileName;
    }

}
