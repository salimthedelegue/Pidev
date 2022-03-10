/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.stockfx;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Card;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.Token;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author nours
 */
public class Payement {
    /*emailCostumer
cardnumber
cardexp_month
cardexp_year
cardcvc
amount*/
    public static boolean payer(String emailUser,String cardnumber,String cardexp_year,String cardexp_month,String cardcvc,String prix) throws StripeException{
        Stripe.apiKey = "sk_test_51KaKn8J6zIUkdclQwyeqhnfpfUhQd0kmp8jIy2EwdXPGw2K0yeGPYTUi6lKX6i6NEuQZZ2DX2PPSV2mF3pN6SD9r00ujoUKsXW"; 
		 
		
		
Map<String, Object> customerParameter= new HashMap<String, Object>();
   customerParameter.put("email", emailUser);
   Customer newCustomer= Customer.create(customerParameter);
             	Map<String, Object> retrieveParams = new HashMap<String, Object>();
		List<String> expandList = new ArrayList<String>();
		expandList.add("sources");
		retrieveParams.put("expand", expandList);
		Customer customer = Customer.retrieve(newCustomer.getId(), retrieveParams, null); //add customer id here : it will start with cus_
		
		Map<String, Object> cardParam = new HashMap<String, Object>(); //add card details
		cardParam.put("number", cardnumber);
		cardParam.put("exp_month", cardexp_month);
		cardParam.put("exp_year", cardexp_year);
		cardParam.put("cvc", cardcvc);

		Map<String, Object> tokenParam = new HashMap<String, Object>();
		tokenParam.put("card", cardParam);

		Token token = Token.create(tokenParam); // create a token

		Map<String, Object> source = new HashMap<String, Object>();
		source.put("source", token.getId()); //add token as source

		Card card = (Card)customer.getSources().create(source); // add the customer details to which card is need to link
		String cardDetails = card.toJson();
		System.out.println("Card Details : " + cardDetails);
		customer = Customer.retrieve(newCustomer.getId());//change the customer id or use to get customer by id.
		System.out.println("After adding card, customer details : " + customer);
		
		 Map<String, Object> chargeParams = new HashMap<String, Object>();
                chargeParams.put("amount", prix);
                chargeParams.put("currency", "usd");
                chargeParams.put("customer", customer.getId());
                Charge ch;
               try{
                    ch=Charge.create(chargeParams);
                     System.out.println(ch.getPaid());
               }catch(Exception e)
               {
                   return false;
               }
             
                Gson gson=new GsonBuilder().setPrettyPrinting().create();
                return ch.getPaid();
    }
    
}
