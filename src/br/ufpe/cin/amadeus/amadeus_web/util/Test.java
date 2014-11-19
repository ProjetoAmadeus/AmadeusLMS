package br.ufpe.cin.amadeus.amadeus_web.util;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.collections.MultiMap;

public class Test {

	/**
	 * @param args
	 * @throws InvalidAlgorithmParameterException 
	 * @throws NoSuchAlgorithmException 
	 * @throws IllegalBlockSizeException 
	 * @throws NoSuchPaddingException 
	 * @throws BadPaddingException 
	 * @throws InvalidKeyException 
	 */
	public static void main(String[] args) throws InvalidKeyException, BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidAlgorithmParameterException {
		// TODO Auto-generated method stub
//		System.out.println(Cryptography.encrypt("25093692"));
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date inicio = format.parse("2012-11-23");
			Date fim = format.parse("2012-11-26");
			System.out.println("Chegou aqui");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
