public class BigInteger {

	private String absValue;
	private String decimalValue;
	private String digits;
	private boolean isNegative;

	public BigInteger ( String val ) {
		String s = "";
		this.absValue = val;
		this.decimalValue = val;
		this.digits = "";
		this.isNegative = false;

		if ((absValue.substring(0, 1)).equals("-")) {
			this.isNegative = true;
			absValue = absValue.substring(1, absValue.length());
		}

		while (absValue.length() > 2 && (absValue.substring(0, 1)).equals("0")) {
			absValue = absValue.substring(1, absValue.length());
		}

		if (isNegative) {
			decimalValue = "-" + absValue;
		}
		else {
			decimalValue = absValue;
		}
		s = absValue;

		for (int i = 0; i < absValue.length(); i++) {
			if (!(Character.isDigit(absValue.charAt(i)))) {
				throw new IllegalArgumentException();
			}
		}

		if (absValue.length() == 1 && Character.getNumericValue(absValue.charAt(0)) == 0) {
			digits = "0";
		}
		else {
			while (!(absValue.length() == 1 && Character.getNumericValue(absValue.charAt(0)) == 0)) {
				if (absValue.length() > 0 && Character.getNumericValue(absValue.charAt(absValue.length()-1)) % 2 == 0) {
					digits = "0" + digits;
				}
				else if (absValue.length() > 0 && Character.getNumericValue(absValue.charAt(absValue.length()-1)) % 2 != 0) {
					digits = "1" + digits;
				}
				absValue = divideByTwo(absValue);
			}
		}
		
		absValue = s;
	}

	public BigInteger add ( BigInteger val ) { // returns a BigInteger whose value is this + val
		String bin1 = this.digits;
		String bin2 = val.digits;
		String result = "";
		int max = bin1.length();
		int carry = 0;

		if (bin1.compareTo(bin2) == 0 && this.isNegative != val.isNegative) {
			return new BigInteger("0");
		}
		else if (!(this.isNegative) && val.isNegative) {
			return this.subtract(new BigInteger(val.absValue));
		}
		else if (this.isNegative && !(val.isNegative)) {
			return val.subtract(new BigInteger(this.absValue));
		}
		else {
			if (bin2.length() > max) {
				max = bin2.length();

				for (int i = 0; i < max - bin1.length(); ) {
					bin1 = "0" + bin1;
				}
			}
			else {
				for (int i = 0; i < max - bin2.length(); ) {
					bin2 = "0" + bin2;
				}
			}

			for (int i = max - 1; i >= 0; i--) {
				if (Character.getNumericValue(bin1.charAt(i)) + Character.getNumericValue(bin2.charAt(i)) + carry == 3) {
					carry = 1;
					result = "1" + result;
				}
				else if (Character.getNumericValue(bin1.charAt(i)) + Character.getNumericValue(bin2.charAt(i)) + carry == 2) {
					carry = 1;
					result = "0" + result;
				}
				else if (Character.getNumericValue(bin1.charAt(i)) + Character.getNumericValue(bin2.charAt(i)) + carry == 1) {
					carry = 0;
					result = "1" + result;
				}
				else {
					carry = 0;
					result = "0" + result;
				}

				if (i == 0 && carry == 1) {
					result = "1" + result;
				}
			}
			return new BigInteger(binaryToDecimal(result));
		}
	}

	public BigInteger subtract ( BigInteger val ) { // returns a BigInteger whose value is this - val
    	String bin1 = this.digits;
		String bin2 = val.digits;
		String result = "";
		int max = bin1.length();
		int carry = 0;
		boolean resultIsNegative = false;

		if (!(this.isNegative) && val.isNegative) {
			return this.add(new BigInteger(val.absValue));
		}
		else if (bin1.compareTo(bin2) == 0 && this.isNegative == val.isNegative) {
			return new BigInteger("0");
		}
		else {
			if (bin2.length() >= max) {
				max = bin2.length();

				for (int i = 0; i < max - bin1.length(); ) {
					bin1 = "0" + bin1;
				}
				if (bin1.compareTo(bin2) == -1) {
					String s = bin1;
					bin1 = bin2;
					bin2 = s;
					resultIsNegative = true;
				}
			}
			else {
				for (int i = 0; i < max - bin2.length(); ) {
					bin2 = "0" + bin2;
				}
			}

			for (int i = max - 1; i >= 0; i--) {
				if ((Character.getNumericValue(bin1.charAt(i)) + carry) - Character.getNumericValue(bin2.charAt(i)) == -1) {
					carry = -1;
					result = "1" + result;
				}
				else if ((Character.getNumericValue(bin1.charAt(i)) + carry) - Character.getNumericValue(bin2.charAt(i)) == -2) {
					carry = -1;
					result = "0" + result;
				}
				else if ((Character.getNumericValue(bin1.charAt(i)) + carry) - Character.getNumericValue(bin2.charAt(i)) == 1) {
					carry = 0;
					result = "1" + result;
				}
				else {
					carry = 0;
					result = "0" + result;
				}
			}

			if (resultIsNegative) {
				return new BigInteger("-" + binaryToDecimal(result));
			}
			else {
				return new BigInteger(binaryToDecimal(result));
			}
		}
	}

	public BigInteger multiply ( BigInteger val ) { // returns a BigInteger whose value is this * val
    	String bin1 = this.digits;
		String bin2 = val.digits;
		String result = "0";
		boolean resultIsNegative = false;

		if ((!(this.isNegative) && val.isNegative) || (this.isNegative && !(val.isNegative))) {
			resultIsNegative = true;
		}

		if ((bin1.length() == 1 && Character.getNumericValue(bin1.charAt(0)) == 0) || (bin2.length() == 1 && Character.getNumericValue(bin2.charAt(0)) == 0 )) {
			return new BigInteger("0");
		}
		else {
			if (bin1.compareTo(bin2) == -1) {
				String s = bin1;
				bin1 = bin2;
				bin2 = s;
			}

			while (!(bin1.length() == 1 && Character.getNumericValue(bin1.charAt(0)) == 1)) {
				if (Character.getNumericValue(bin1.charAt(bin1.length()-1)) == 1) {
					bin1 = bin1.substring(0, bin1.length()-1);
					result = (new BigInteger(binaryToDecimal(result)).add(new BigInteger(binaryToDecimal(bin2)))).digits;
					bin2 = bin2 + "0";
				}
				else {
					bin1 = bin1.substring(0, bin1.length()-1);
					bin2 = bin2 + "0";
				}
			}

			result = (new BigInteger(binaryToDecimal(result)).add(new BigInteger(binaryToDecimal(bin2)))).digits;

			if (resultIsNegative) {
				return new BigInteger("-" + binaryToDecimal(result));
			}
			else {
				return new BigInteger(binaryToDecimal(result));
			}
		}
  	}

	public BigInteger divide ( BigInteger val ) { // returns a BigInteger whose value is this / val
    	String bin1 = this.digits;
		String bin2 = val.digits;
		String result = "";
		String bin1Parts = "";
		boolean resultIsNegative = false;

		if ((!(this.isNegative) && val.isNegative) || (this.isNegative && !(val.isNegative))) {
			resultIsNegative = true;
		}

		if (bin1.compareTo(bin2) == -1) {
			String s = bin1;
			bin1 = bin2;
			bin2 = s;
		}

		for (int i = 0; i < bin1.length(); i++) {
			bin1Parts = bin1Parts + bin1.substring(i, i+1);

			if (new BigInteger(binaryToDecimal(bin1Parts)).compareTo(new BigInteger(binaryToDecimal(bin2))) == 1 || new BigInteger(binaryToDecimal(bin1Parts)).compareTo(new BigInteger(binaryToDecimal(bin2))) == 0) {
				result = result + 1;
				bin1Parts = (new BigInteger(binaryToDecimal(bin1Parts)).subtract(new BigInteger(binaryToDecimal(bin2)))).digits;
			}
			else {
				result = result + 0;
			}
		}

		if (resultIsNegative) {
			return new BigInteger("-" + binaryToDecimal(result));
		}
		else {
			return new BigInteger(binaryToDecimal(result));
		}
  	}

	public BigInteger remainder ( BigInteger val ) { // returns a BigInteger whose value is this % val
    	String bin1 = this.digits;
		String bin2 = val.digits;
		String result = "";
		String bin1Parts = "";
		boolean resultIsNegative = false;

		//if ((!(this.isNegative) && val.isNegative) || (this.isNegative && !(val.isNegative))) {
		//	resultIsNegative = true;
		//}

		if (bin1.compareTo(bin2) == -1) {
			String s = bin1;
			bin1 = bin2;
			bin2 = s;
		}

		for (int i = 0; i < bin1.length(); i++) {
			bin1Parts = bin1Parts + bin1.substring(i, i+1);

			if (new BigInteger(binaryToDecimal(bin1Parts)).compareTo(new BigInteger(binaryToDecimal(bin2))) == 1 || new BigInteger(binaryToDecimal(bin1Parts)).compareTo(new BigInteger(binaryToDecimal(bin2))) == 0) {
				result = result + 1;
				bin1Parts = (new BigInteger(binaryToDecimal(bin1Parts)).subtract(new BigInteger(binaryToDecimal(bin2)))).digits;
			}
			else {
				result = result + 0;
			}
		}

		//if (resultIsNegative) {
		//	return new BigInteger("-" + binaryToDecimal(bin1Parts));
		//}
		//else {
			return new BigInteger(binaryToDecimal(bin1Parts));
		//}
  	}

	public String toString () { // returns the decimal string represention of this BigInteger
    	return this.decimalValue;
  	}

	public int compareTo ( BigInteger val ) { // returns -1/0/1 as this BigInteger is numerically less than/equal to/greater than val
		int result = 2;
		int counter = 0;
    	if (this.equals(val)) {
    		result = 0;
    	}
    	else if (this.toString().length() > val.toString().length()) {
    		result = 1;
    	}
    	else if (this.toString().length() < val.toString().length()) {
    		result = -1;
    	}
    	else {
    		do {
    			if (Character.getNumericValue(this.toString().charAt(counter)) > Character.getNumericValue(val.toString().charAt(counter))) {
    				result = 1;
    			}
    			else if (Character.getNumericValue(this.toString().charAt(counter)) < Character.getNumericValue(val.toString().charAt(counter))) {
    				result = -1;
    			}
    			counter++;
    		} while (result != -1 && result != 0 && result != 1);
    	}
    	return result;
  	}

	public boolean equals ( Object x ) { // returns true iff x is a BigInteger whose value is numerically equal to this BigInteger
    	return this.toString().equals(x.toString());
  	}

  	public static final BigInteger ZERO = new BigInteger("0"); // a classwide constant for zero
	public static final BigInteger ONE = new BigInteger("1"); // a classwide constant for one
	public static final BigInteger TEN = new BigInteger("10"); // a classwide constant for ten

	public static BigInteger valueOf ( long val ) { // "static factory" for constructing BigIntegers out of longs
    	return new BigInteger("" + val);
  	}

	public static void main (String[] args) {
		BigInteger b = new BigInteger ("12345");	
	}

	public String divideByTwo ( String val ) {
		String result = "";
		String c = "";

		for (int i = val.length() - 1; i >= 0; i--) {
			if (Character.getNumericValue(val.charAt(i)) % 2 == 0) {
				c = "" + (Character.getNumericValue(val.charAt(i)));
				c = "" + (Character.getNumericValue(c.charAt(0)) / 2);
			}
			else {
				c = "" + (Character.getNumericValue(val.charAt(i)) - 1);
				c = "" + ((Character.getNumericValue(c.charAt(0)) / 2));
			}

			if (i != 0 && (Character.getNumericValue(val.charAt(i-1)) % 2 != 0)) {
				c = "" + (Character.getNumericValue(c.charAt(0)) + 5);
			}

			result = c + result;
		}

		if (result.length() > 1  && Character.getNumericValue(result.charAt(0)) == 0) {
			result = result.substring(1, result.length());
		}

		return result;
	}

	public String doubleDecimalString ( String val ) {
		String result = "";
		String c = "";
		int carry = 0;

		for (int i = val.length() - 1; i >= 0; i--) {
			if (Character.getNumericValue(val.charAt(i)) * 2 >= 10) {
				c = "" + ((Character.getNumericValue(val.charAt(i)) * 2 - 10) + carry);
				carry = 1;
			}
			else {
				c = "" + ((Character.getNumericValue(val.charAt(i)) * 2) + carry);
				carry = 0;
			}

			result = c + result;

			if (i == 0 && carry == 1) {
				result = (carry + "") + result;
			}
		}
		return result;
	}

	public String addOne ( String val) {
		String c = "" + (Character.getNumericValue(val.charAt(val.length() - 1)) + 1);
		return val.substring(0, val.length()-1) + c;
	}

	public String binaryToDecimal ( String bin ) {
		String result = "0";
		for (int i = 0; i < bin.length(); i++) {
			if (Character.getNumericValue(bin.charAt(i)) == 0) {
				result = doubleDecimalString(result);
			}
			else {
				result = doubleDecimalString(result);
				result = addOne(result);
			}
		}
		return result;
	}
}