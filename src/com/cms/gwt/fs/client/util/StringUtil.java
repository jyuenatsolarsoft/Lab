package com.cms.gwt.fs.client.util;

public class StringUtil {

	public static String makeId(String info) {
		if (info != null) {
			return info.replaceAll("\\s+", "_");
		}
		return "";
	}

	public static String formatNumber(String number, int decimal) {
		return formatNumber(number, decimal, true);
	}

	public static String formatNumber(String number, int decimal,
			boolean decimalRequired) {
		String rval = "";

		if (number == null || "".equals(number) || decimal < 0) {
			return rval;
		}

		int decIndex = number.indexOf(".");
		if (!decimalRequired && decIndex < 0) {
			return number;
		}

		String num = (decIndex < 0) ? number + ".0" : number;
		String[] arrNum = num.split("\\.", 2);
		if ("".equals(arrNum[0])) {
			arrNum[0] = "0";
		}
		if ("".equals(arrNum[1])) {
			arrNum[1] = "0";
		}

		if (decimal > 0) {
			int lenDecimal = arrNum[1].length();
			int magnitude = (decimal > lenDecimal) ? 1 : -1;
			while (lenDecimal != decimal) {
				if (magnitude > 0) {
					arrNum[1] += "0";

				} else if (lenDecimal > 0) {
					arrNum[1] = arrNum[1].substring(0, lenDecimal - 1);
				}
				lenDecimal = arrNum[1].length();
			}
			rval = arrNum[0] + "." + arrNum[1];

		} else {
			rval = arrNum[0];
		}

		return rval;
	}

	public static String formatNumber(Double number, int decimal) {
		try {
			String value = String.valueOf(number);
			return formatNumber(value, decimal);
		} catch (Exception e) {
			return "";
		}
	}

}
