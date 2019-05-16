/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gisainvestment.test;

import com.gisainvestment.wService.Sender;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

/**
 *
 * @author LTD
 */
public class Test_home {

    public static void main(String... a) throws ParseException {
        //PhoneNumberGenerator.getPhoneNumber();
//        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//        Date start = sdf.parse("2/8/2014");
//        Date end = sdf.parse("5/9/2014");
//        //FrequencyAnalyser freAn = new FrequencyAnalyser(Variable.MESSAGE_STATUS,start, end);
//       // for(Object[] row : freAn.getData()){
//         //   System.out.println(row[0]+"\t"+row[1]+"\t"+row[2]);
//        //}
////       // UssdFlow flow = new UssdFlow();
////        flow.setInput("5");
////        flow.setInput("2");
////        flow.setInput("2");
//////        System.out.println(flow.isInputValid());
////        System.out.println("==================");
////        System.out.println(flow.getMessage());
////        System.out.println(flow.getQuestion());
////        int i=1;
////        for(Object ch: flow.getChoices()){
////            System.out.println(i++ +"-"+ch);
////        }
//
//
// 
//    //create an ArrayList object
//    ArrayList arrayList = new ArrayList();
//   
//    //Add elements to Arraylist
//    arrayList.add("time A");
//    arrayList.add("city B");
//    arrayList.add("kigali C");
//    arrayList.add("Abdul D");
//    arrayList.add("Kevin E");
//   
//    System.out.println("Before Reverse Order, ArrayList Contains : " + arrayList);
//   
//    /*
//      To reverse the order of all elements of Java ArrayList use,
//      static void reverse(List list) method of Collections class.
//     
//      This method reverse the order of elements of specified list.
//    */
//   
//    Collections.reverse(arrayList);
//   
//    System.out.println("After Reverse Order, ArrayList Contains : " + arrayList);
//   
// 
        Sender s = new Sender("Mukiriya mwiza,GISA INVESTMENT Ltd irabamenyesha ko iri muri test");
        Sender.sendRouteSMS("250783453253", s);

    }
}
