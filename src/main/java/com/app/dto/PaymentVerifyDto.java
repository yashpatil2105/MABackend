package com.app.dto;

import com.app.enities.Donar;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class PaymentVerifyDto {

	private RazorpayResponse razorpayResponse;
	private Donar donar;
	
}
