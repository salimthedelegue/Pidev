/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.stockfx.utils;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import static com.example.stockfx.utils.SmsApimed.ACCOUNT_SID;
/**
 *
 * @author Asus
 */
public class SmsApimed {
    public static final String ACCOUNT_SID = "AC6d45e72872be4dc89b31f64632235722";
    public static final String AUTH_TOKEN = "c5ef5821850f7285abc3ee132f9609a9";

    public SmsApimed(){
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }
    
    public  void  sendSMSmed ( String sendTo,String msgText){
      
          Message message= Message.creator(
                  new PhoneNumber(sendTo),
                  new PhoneNumber("+17372523729")
                  , msgText).create();

    
}
}
