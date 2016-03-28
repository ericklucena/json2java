package co.codehaven.json2java;

public class Util {

	private static final String TYPE_INT = "Integer";
	private static final String TYPE_DOUBLE = "Double";
	private static final String TYPE_STRING = "String";
	private static final String TYPE_BOOLEAN = "Boolean";

	public static String inferType(String info){
		
		String type = TYPE_STRING;

		if(Boolean.parseBoolean(info)){
			type = TYPE_BOOLEAN;
		}
		// Try numeric values
		try{
			Double.parseDouble(info);
			type = TYPE_DOUBLE;
			Integer.parseInt(info);
			type = TYPE_INT;
		}catch(NumberFormatException nfe){
			
		}
		
		return type;
	}
	
	public static String toPascalCase(String s){
		return Character.toUpperCase(s.charAt(0)) + s.substring(1);
	}
}
