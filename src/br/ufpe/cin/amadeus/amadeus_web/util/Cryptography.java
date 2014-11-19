package br.ufpe.cin.amadeus.amadeus_web.util;
import javax.crypto.*;  
import javax.crypto.spec.*;  

import br.ufpe.cin.amadeus.amadeus_web.domain.settings.WebSettings;

import java.security.*;  
import java.security.spec.KeySpec;  
import sun.misc.BASE64Encoder; // para simplificar o exemplo. Use alguma outra classe para converter  
import sun.misc.BASE64Decoder; // para Base-64.  
 
public final class Cryptography {  

	private static SecretKey skey;  
    private static KeySpec ks;  
    private static PBEParameterSpec ps;  
    private static final String algorithm = "PBEWithMD5AndDES";  
    private static BASE64Encoder enc = new BASE64Encoder();  
    private static BASE64Decoder dec = new BASE64Decoder();  
      
    private static String CRYPTO_KEY = WebSettings.getInstance().getSecurityCryptographyKey();
      
    static {  
    	try {  
    		SecretKeyFactory skf = SecretKeyFactory.getInstance(algorithm);  
    		ps = new PBEParameterSpec (new byte[]{3,1,4,1,5,9,2,6}, 20);  

    		ks = new PBEKeySpec (CRYPTO_KEY.toCharArray());
    		skey = skf.generateSecret (ks);  
    	} catch (java.security.NoSuchAlgorithmException ex) {  
    		ex.printStackTrace();  
    	} catch (java.security.spec.InvalidKeySpecException ex) {  
    		ex.printStackTrace();  
    	}  
    }
    
    public static final String encrypt(final String text) throws BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException,  
    	InvalidKeyException, NoSuchAlgorithmException, InvalidAlgorithmParameterException {  
        final Cipher cipher = Cipher.getInstance(algorithm);  
        cipher.init(Cipher.ENCRYPT_MODE, skey, ps);  
        return enc.encode(cipher.doFinal(text.getBytes()));  
    }  
      
    public static final String decrypt(final String text) throws BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException,  
		InvalidKeyException, NoSuchAlgorithmException, InvalidAlgorithmParameterException {
	  
    	final Cipher cipher = Cipher.getInstance(algorithm);  
    	cipher.init(Cipher.DECRYPT_MODE, skey, ps);  
    	String ret = null;  
    	try {  
    		ret = new String(cipher.doFinal(dec.decodeBuffer (text)));  
    	} catch (Exception ex) {  
    	}  
    	return ret;  
    }
}  