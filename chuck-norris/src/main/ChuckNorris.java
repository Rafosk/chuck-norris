package main;

public class ChuckNorris {
	public static void main(String[] args)  {

		String MESSAGE = "CC";

		String binary = toBytes(MESSAGE);

		System.out.println("'" + MESSAGE + "' to binary: " + binary);
		
		System.out.println(decode(binary));

	}

	private static String decode(String message) {

		int block2counter = 1;
		int counter = 0;
		String prevString = "";
		StringBuilder result = new StringBuilder();

		String[] tmp = message.split("(?!^)");

		for (String string : tmp) {

			if (counter == 0) {
				prevString = string;
				putZero(result, string);
				counter++;
			} else {

				if (prevString.equals(string)) {
					block2counter++;
				} else {
					for (int i = 1; i <= block2counter; i++) {
						result.append("0");
					}
					block2counter = 1;
					result.append(" ");

					prevString = string;
					putZero(result, string);					
				}
			}
		}
		for (int i = 1; i <= block2counter; i++) {
			result.append("0");
		}

		return result.toString();
	}

	private static void putZero(StringBuilder result, String string) {
		if (string.equals("0")) {
			result.append("00 ");
		} else {
			result.append("0 ");
		}
	}
	
	private static String toBytes(String MESSAGE) {
		byte[] bytes = MESSAGE.getBytes();

		StringBuilder binary = new StringBuilder();
		for (byte b : bytes) {
			int val = b;
			for (int i = 0; i < 8; i++) {				
				if (i == 0) {
					binary.append((val & 128) == 0 ? "" : 1);
				} else {
					binary.append((val & 128) == 0 ? 0 : 1);
				}
				
				val <<= 1;
			}
		}
					
		return binary.toString();
	}
}
