package com.expenses.util;

import java.text.DecimalFormat;
import java.util.Base64;
import java.util.Random;

import com.expenses.exception.DecodingException;
import com.expenses.exception.EncodingException;

/**
 * @author Jagadish Anala
 *
 */
public class ExpensesUtil {

	public static Double Round(Double val) {
		DecimalFormat format = new DecimalFormat("##.00");
		return Double.valueOf(format.format(val));
	}

	public static String encode(String stringToEncode) throws EncodingException {
		Base64.Encoder encoder = Base64.getEncoder();
		return encoder.encodeToString(stringToEncode.getBytes());
	}

	public static String decode(String stringToDecode) throws DecodingException {
		Base64.Decoder decoder = Base64.getDecoder();
		return new String(decoder.decode(stringToDecode));
	}
	
	public static String colorCode() {
		 Random random = new Random();
	     int nextInt = random.nextInt(256*256*256);
         String colorCode = String.format("#%06x", nextInt);
	     return colorCode;
	}
}
