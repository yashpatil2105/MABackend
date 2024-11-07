package com.app.controller;

import javax.validation.Valid;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.PaymentVerifyDto;
import com.app.services.PaymentService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/pay")
public class PaymentController {
	@Autowired
	public PaymentService paymentservice;
 
	@PostMapping("/order/{amount}")
	public String paymentOrder(@PathVariable Integer amount)
	{
		System.out.println(amount);
		try {
			  var razorpay = new RazorpayClient("rzp_test_qZG2ZZt5Yb7hBG","D03SqDw8IJCcMxGz3RaD91ep");
			  JSONObject orderRequest = new JSONObject();
			  orderRequest.put("amount", amount*100); // amount in the smallest currency unit
			  orderRequest.put("currency", "INR");
			  orderRequest.put("receipt", "order_rcptid_");
			  Order order = razorpay.orders.create(orderRequest);
			  System.out.println(order);
			  return order.toString();
			} catch (RazorpayException e) {
			  System.out.println(e.getMessage());
			}
		 return "Failed";
		
	}


	
	@PostMapping("/verify/{id}")
	public String paymentVerify(@PathVariable Integer id, @RequestBody @Valid PaymentVerifyDto razorpayResponse) throws java.security.SignatureException
	{
		
		  String generated_signature = paymentservice.calculateRFC2104HMAC(razorpayResponse.getRazorpayResponse().getRazorpay_order_id() + "|" + razorpayResponse.getRazorpayResponse().getRazorpay_payment_id(), "D03SqDw8IJCcMxGz3RaD91ep");
		  System.out.println(generated_signature);
		  System.out.println(id);
		  System.out.println(razorpayResponse.getDonar());
		  if (generated_signature.equals(razorpayResponse.getRazorpayResponse().getRazorpay_signature())) {
		    System.out.println( "payment is verified successfull");
		   paymentservice.setDonarDetails(id, razorpayResponse.getDonar());
		    System.out.println(razorpayResponse.getDonar());
		    return "success";
		  }
		  System.out.println( "payment not verified failed");
		    return "failed";
	}
	
   

}
