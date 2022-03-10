/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.stockfx.utils;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 *
 * @author Asus
 */
public class SmsApi {
   public static final String ACCOUNT_SID="AC679c57d39b08669791aff42ccf065a4b";
            public static final String AUTH_TOKEN="3a61c9d6f234c4ffc00e093f6ea455dd";

    public  SmsApi (){
         Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }
    
    public void  sendSMS ( String sendTo,String msgText){
      
          Message message= Message.creator(
                  new PhoneNumber(sendTo),
                  new PhoneNumber("+19107271734")
                  , msgText).create();
    
}
}
