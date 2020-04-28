package com.ch14;

public class SystemInfo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		java.util.Properties props = System.getProperties();
		props.put("mylibs", "C:\\libs\\");
		props.list(System.out);
	}

}
