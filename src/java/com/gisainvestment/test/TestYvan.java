/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gisainvestment.test;

import com.gisainvestment.thirdparties.Mtn_Mobile_Mobile;

/**
 *
 * @author McP
 */
public class TestYvan {
    public static void main (String...args){
        String phoneNumber="0788683616";
        System.out.println(Mtn_Mobile_Mobile.placeMoney(phoneNumber, 10000));
        System.out.println(Mtn_Mobile_Mobile.getAccount("0783312146"));
    }
}
