package com.searshc.hs.psc.catalog.test;

import java.util.HashMap;
import java.util.Map;

public class Test4 {

	public final static String DELETE_ACTION = "D";
	public final static String INSERT_ACTION = "I";
	public final static String UPDATE_ACTION = "U";
	
	private static String actions = "DU";	
	
	public static void main(String[] args) {
		try {
			
			System.out.println("D >>> " + isValidAction(DELETE_ACTION));
			System.out.println("I >>> " + isValidAction(INSERT_ACTION));
			System.out.println("U >>> " + isValidAction(UPDATE_ACTION));
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public static boolean isValidAction(String action) {
		
		return actions.contains(action); 
	}
}
