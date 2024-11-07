package com.app.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class RazorpayResponse {
	
		private String razorpay_payment_id;
		private String razorpay_order_id;
		private String razorpay_signature;
		 
}
