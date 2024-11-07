package com.app.services;

import com.app.enities.Donar;

public interface PaymentService {

	String calculateRFC2104HMAC(String data, String secret)  throws java.security.SignatureException;

	void setDonarDetails(Integer campId, Donar donar);

}
