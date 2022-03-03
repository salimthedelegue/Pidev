/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 *
 * @author Asus
 */
public class SmsApi {
    public static String ACCOUNT_SID = "AC679c57d39b08669791aff42ccf065a4b";
    public static String AUTH_TOKEN = "54c1749000a895124d7f4495dea4fc56";

    public  SmsApi (){
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }
    
    public void  sendSMS (String msgText, String sendTo){
        Message message = Message.creator(new PhoneNumber(sendTo),
        new PhoneNumber("+19107271734"), 
        msgText).create();
        
        System.out.println(message.getSid());
    
}
}
