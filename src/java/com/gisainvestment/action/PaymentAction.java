/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gisainvestment.action;

import com.gisainvestment.DAO.InvestmentDAOimp;
import com.gisainvestment.DAO.PaymentDAO;
import com.gisainvestment.domain.Investment;
import com.gisainvestment.domain.Payment;
import com.gisainvestment.utility.Locator;
import com.gisainvestment.utility.SimpleDateConverter;
import com.gisainvestment.utility.Validation;
import com.gisainvestment.wService.Sender;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author mcpaul
 */
public class PaymentAction extends ActionSupport {

    private static Logger LOGGER = Logger.getLogger(PaymentAction.class);
    private File ExcelFile; // uploaded files
    private String excelFileFileName;
    private String errorMsg;
    private String successMsg;
    private List<Payment> payments = new ArrayList<>();

    public String upload() {

        try {
            //Create Workbook instance holding reference to .xlsx file            
            Workbook workbook = new HSSFWorkbook(new FileInputStream(ExcelFile));

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
                    Investment investment = InvestmentDAOimp.checkSuccessTicket(inv.getTicket());
                    if (investment != null) {
                        investment.setStatus("success");
                        double tax = 0;
                        if (investment.getSource().startsWith("25078")) {
                            tax = Validation.formatDouble(investment.getAmount() * Locator.GISA_SYSTEM_TAX_RATE_MTN_TIGO);
                            LOGGER.error("MTN Tax=" + tax + " Amount=" + investment.getAmount() + " From: " + investment.getSource());
                        } else if (investment.getSource().startsWith("25073")) {
                            tax = Validation.formatDouble(investment.getAmount() * Locator.GISA_SYSTEM_TAX_RATE_AIRTEL);
                            LOGGER.error("AIRTEL Tax=" + tax + " Amount=" + investment.getAmount() + " From: " + investment.getSource());
                        } else if (investment.getSource().startsWith("25072")) {
                            tax = Validation.formatDouble(investment.getAmount() * Locator.GISA_SYSTEM_TAX_RATE_MTN_TIGO);
                            LOGGER.error("TIGO Tax=" + tax + " Amount=" + investment.getAmount() + " From: " + investment.getSource());
                        }
                        if (InvestmentDAOimp.updateTicket(investment)) {
                            Payment payment = new Payment(
                                    PaymentDAO.countPayments() + 1,
                                    investment.getTicket(),
                                    SimpleDateConverter.toLocalTZReturnDate(new Date()),
                                    NONE,
                                    SUCCESS,
                                    tax
                            );
                            if (PaymentDAO.create(payment)) {
                                Sender s = new Sender("GISA Investment irashimira +" + investment.getSource() + " kuyiguriza "
                                        + "amafaranga: " + investment.getAmount() + "Frw.\r\n"
                                        + "Wishyuwe: " + investment.getReceivableAmount() + "Frw\r\n"
                                        + "Itike yawe: " + investment.getTicket() + "\r\n"
                                        + "Wishyuwe mu minsi: " + InvestmentDAOimp.daysPassed(investment.getInv_date()));
                                Sender.sendRouteSMS(investment.getSource(), s);
                                addActionMessage("Payment uploaded successfully");
                            } else {
                                LOGGER.error("====Fail to update payment");
                                addActionError("Fail to update payments.");
                            }
                        } else {
                            LOGGER.error("====Fail to update ticket status");
                            addActionError("Fail to update payments.");
                        }
                    } else {
                        LOGGER.error("====Investment in successTicket() is null");
                        addActionError("Fail to update payments.");
                    }
                }//END row greater than 0
            }//END row iteration

        } catch (Exception e) {
            LOGGER.error(e.toString());
            addActionError("Fail to upload.");
        }
        return "upload";
    }

    public String viewAll() {
        payments = PaymentDAO.getAllPayment();
        return "view";
    }

//====================GETTER AND SETTER===========================
    public static Logger getLOGGER() {
        return LOGGER;
    }

    public static void setLOGGER(Logger LOGGER) {
        PaymentAction.LOGGER = LOGGER;
    }

    public File getExcelFile() {
        return ExcelFile;
    }

    public void setExcelFile(File ExcelFile) {
        this.ExcelFile = ExcelFile;
    }

    public String getExcelFileFileName() {
        return excelFileFileName;
    }

    public void setExcelFileFileName(String excelFileFileName) {
        this.excelFileFileName = excelFileFileName;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getSuccessMsg() {
        return successMsg;
    }

    public void setSuccessMsg(String successMsg) {
        this.successMsg = successMsg;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public static void main(String[] args) {
        Sender s = new Sender("GISA Investment irashimira +250788546013 kuyiguriza\r\n"
                + "Amafaranga: 10,000Frw.\r\n"
                + "wishyuwe: 10,380Frw\r\n"
                + "itike yawe: CFF220B30120604A\r\n"
                + "iminsi: 22");
        Sender.sendRouteSMS("250788546013", s);
    }
}
