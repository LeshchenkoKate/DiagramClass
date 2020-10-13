package src;

import com.mysql.cj.util.StringUtils;

public class ElementClass {

	private static String privat = "private ";
	private static String publi = "public ";
	private static String protecte = "protected ";

	public static String attributes(String atr) {
		boolean isContain1 = false;
		String s1 = null;
		String s2 = null;

		if (atr.startsWith("-")) {
			isContain1 = true;
			//return atr;
		} else if (atr.startsWith("+")) {
			isContain1 = true;
			//return atr;
		} else if (atr.startsWith("~")) {
			isContain1 = true;
			//return atr;
		} else if (atr.startsWith(privat)) {
			atr = atr.replace (privat, "");
			s1 = "-" + atr;
			//return s1;
		} else if (atr.startsWith(publi)) {
			atr = atr.replace (publi, "");
			s1 = "+" + atr;
			//return s1;
		} else if (atr.startsWith(protecte)) {
			atr = atr.replace (protecte, "");
			s1 = "~" + atr;
			//return s1;
		} else {
			s1 = "-" + atr;
			//return s1;
		}
		
		if (isContain1 == true) {
			if (atr.endsWith(";"))
				return atr;
			else {
				s2 = atr + ";";
				return s2;
			}
		} else {
			if (s1.endsWith(";"))
				return s1;
			else {
				s2 = s1 + ";";
				return s2;
			}
		}
	}

	public static String methods(String meth) {

		boolean isContain1 = false;
		boolean isContain3 = false;
		String s1 = null;
		//boolean isContain2 = false;

		if (meth.startsWith("-")) {
			isContain1 = true;

		} else if (meth.startsWith("+")) {
			isContain1 = true;

		} else if (meth.startsWith("~")) {
			isContain1 = true;
		} else if (meth.startsWith(privat)) {
			meth = meth.replace (privat, "");
			s1 = "-" + meth;
			isContain3 = true;

		} else if (meth.startsWith(publi)) {
			meth = meth.replace (publi, "");
			s1 = "+" + meth;
			isContain3 = true;

		} else if (meth.startsWith(protecte)) {
			meth = meth.replace (protecte, "");
			s1 = "~" + meth;
			isContain3 = true;

		} else {
			s1 = "-" + meth;
			isContain3 = true;
		}

		if (isContain1 == true) {
			if (meth.contains("();")) {
				//isContain2 = true;
				return meth;
			} else if (meth.contains("(") && !meth.contains(")") && !meth.contains(";")) {
				String input = null;
				input = meth.substring(meth.indexOf("(") + 1, meth.lastIndexOf(")"));
				return input;
			} else if (!meth.contains("(") && !meth.contains(")") && !meth.contains(";")) {
				String input = null;
				input = meth + "();";
				return input;

			}else return meth;
		} else if (isContain3 == true) {
			if (s1.contains("();")) {
				//isContain2 = true;
				return s1;
			} else if (s1.contains("(") && !s1.contains(")") && !s1.contains(";")) {
				String input = null;
				input = s1.substring(s1.indexOf("(") + 1, s1.lastIndexOf(")"));
				return input;
			} else if (!s1.contains("(") && !s1.contains(")") && !s1.contains(";")) {
				String input = null;
				input = s1 + "();";
				return input;

			}else return null;
		}else return null;


	}
}
