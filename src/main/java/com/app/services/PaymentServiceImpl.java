package com.app.services;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.transaction.Transactional;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.enities.Campaign;
import com.app.enities.Donar;
import com.app.repository.CampaignRepository;
import com.app.repository.DonarRepository;

import io.jsonwebtoken.SignatureException;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {
	 @Autowired
	 private DonarRepository donarRepo;
	 
	 @Autowired
	 private CampaignRepository campaignRepo;
	 
	 private static final String HMAC_SHA256_ALGORITHM = "HmacSHA256";
	 
	 @Override
		public void setDonarDetails(Integer campId,Donar donar){
			Campaign campaign = campaignRepo.findById(campId).orElseThrow(() -> new UsernameNotFoundException("Invalid Campaign ID"));
			campaign.setCurrentAmount(campaign.getCurrentAmount()+donar.getAmount());
			campaign.setNoOfDonar(campaign.getNoOfDonar()+1);
			campaign.getDonars().add(donar);
			donar.getCampaigns().add(campaign);
			System.out.println(donar);
	        donarRepo.save(donar);
		}
	 
	 
	 public String calculateRFC2104HMAC(String data, String secret)
			    throws java.security.SignatureException
			    {
			        String result;
			        try {

			            // get an hmac_sha256 key from the raw secret bytes
			            SecretKeySpec signingKey = new SecretKeySpec(secret.getBytes(), HMAC_SHA256_ALGORITHM);

			            // get an hmac_sha256 Mac instance and initialize with the signing key
			            Mac mac = Mac.getInstance(HMAC_SHA256_ALGORITHM);
			            mac.init(signingKey);

			            // compute the hmac on input data bytes
			            byte[] rawHmac = mac.doFinal(data.getBytes());

			            // base64-encode the hmac
			            result = DatatypeConverter.printHexBinary(rawHmac).toLowerCase();

			        } catch (Exception e) {
			            throw new SignatureException("Failed to generate HMAC : " + e.getMessage());
			        }
			        return result;
			    }

}
