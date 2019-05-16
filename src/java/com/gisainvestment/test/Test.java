/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gisainvestment.test;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
//import org.apache.commons.lang3.time.DateUtils;
import com.gisainvestment.DAO.AccountDAOimp;
import com.gisainvestment.DAO.InvestmentDAOimp;
import com.gisainvestment.DAO.InvestorDAOimp;
import com.gisainvestment.DAO.MenuDAOimp;
import com.gisainvestment.domain.Account;
import com.gisainvestment.domain.Investment;
import com.gisainvestment.domain.Menu;
import com.gisainvestment.home.PhoneNumberGenerator;
import com.gisainvestment.thirdparties.Mtn;
import com.gisainvestment.utility.Locator;
import java.util.Arrays;
import java.util.Collections;

/**
 *
 * @author McP
 */
public class Test {

    public static void main(String... arg) {
//        MenuDAOimp menuDao = new MenuDAOimp();
//        System.out.println(menuDao.getRootMenu());
//        System.out.println(menuDao.getChild(menuDao.getRootMenu()));
//        Menu menu = new Menu(2, "M11", "Welcome","M11");
//        MenuDAOimp menudaoimp = new MenuDAOimp();
//        menudaoimp.getMenuByParent(menu);
//        menudaoimp.getMenuByCode(menu.getCode());
//
//        Choice choice1 = new Choice(1, menu.getCode(), "Register");
//        Choice choice2 = new Choice(2, menu.getCode(), "My Account");
//        ChoiceDAOimp choidaoimp = new ChoiceDAOimp();
//        choidaoimp.create(choice1);
//        choidaoimp.create(choice2);
////       
////
//        Account account = new Account("0783453254", "password");
//        AccountDAOimp accdao = new AccountDAOimp();
////        accdao.create(account);
//        //System.out.println("HOw many account? "+accdao.countFromAccount());

//        System.out.println(getreturndate());
//        MenuDAOimp menudao = new MenuDAOimp();
//
//        for (Menu menu : menudao.getMenuList()) {
//            System.out.println("Menu in list " + menu);
//        }
//        System.out.println("this Menu has null " + menudao.getRootMenu());
//    }
//    public static Date getreturndate() {
//        Date outDate = new Date();
//        Date date=DateUtils.addDays(outDate, 30);
//        return date;
//        Mtn.sendMomo("0783453253", "0788298465", 2000, "paswd1");
//        for (int i = 0; i < 10; i++) {
//            System.out.println(PhoneNumberGenerator.getMtnPhoneNumber()+" "+ PhoneNumberGenerator.getIdCard());
//        }
//        boolean bool = false;
//        int i=0;
//        do {
//            try {
//                System.out.println(InvestmentDAOimp.getAllInvestment());
//                System.out.println("======"+i);
//                bool = true;
//                
//            } catch (Exception e) {
//                System.out.println(e);
//                bool = false;
//            }
//            i++;
//        } while (bool);
//        
//        Random ran=new Random();
//        System.out.println(ran.nextInt());
//        
//        UUID.randomUUID();
//        UUID random = UUID.randomUUID();
//       
//        System.out.println(UUID.randomUUID()+"\n");
//        System.out.println("\n");
//        System.out.println(random.toString().replaceAll("-",""+ran.nextInt(9)).substring(0, 16));
//        
//        System.out.println(random.toString().replaceAll("-", ""+9));
//        System.out.println(random.toString().substring(0, 9).toUpperCase());
//        System.out.println(random.toString());
//        String[] arr = random.toString().split("-");
//        System.out.println(arr[0]);
//        System.out.println(arr[1]);
//        System.out.println(arr[2]);
//        System.out.println(arr[3]);
//        System.out.println(arr[4]);
//        String url = String.format("http://localhost:8080/EquationGeneratorXML/resources/equation/");
//        System.out.println(url);
//   
//        double amount = 10000;
//        double amountInSYS = amount * Locator.GISA_SYSTEM_IN;
//        System.out.println("IN SYSTEM =" + amountInSYS);
//        double amountInCirculationStore = amountInSYS * Locator.GISA_SYSTEM_CILCULATION_STORE;
//        System.out.println("IN STORE =" + amountInCirculationStore);
//        double amountSysOutRate=(amount*Locator.GISA_SYSTEM_OUT_RATE);
//        System.out.println("IN OUT RATE =" + amountSysOutRate);
//        double amountSysOut=(amount*Locator.GISA_SYSTEM_OUT);
//        System.out.println("IN OUT =" + amountSysOut);
//        InvestmentDAOimp inv = new InvestmentDAOimp();
//        System.out.println(InvestmentDAOimp.circulationStore());
//        for (Investment in : InvestmentDAOimp.calculateInvestment()) {
//            System.out.println(in);
//        }
//        System.out.println(44338.4144 - 10539.2);
//        System.out.println(10100 * Locator.TEST);//10644.593
//        System.out.println(10100 * Locator.GISA_SYSTEM_OUT_RATE);//10644.592
//        InvestmentDAOimp inv = new InvestmentDAOimp();
//        System.out.println(Arrays.toString(inv.datediff()));
//        for (Investment in : InvestmentDAOimp.calculateInvestment()) {
//            System.out.println(InvestmentDAOimp.dateDifference(in.getInv_returnDate()));
//        }
        
    }
}
