package core;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DataTypeMapper {
	
	private static Map<Class<?>, String> typeMap = new HashMap<>();
	static{
		typeMap.put(String.class, "VARCHAR(255)");
		typeMap.put(Character.class, "CHAR");
		typeMap.put(Short.class, "MEDIUMINT(9)");
		typeMap.put(Integer.class, "INT(11)");
		typeMap.put(Long.class, "BIGINT(20)");
		typeMap.put(Boolean.class, "TINYINT(1)");
		typeMap.put(Double.class, "DOUBLE");
		typeMap.put(Float.class, "FLOAT");
		typeMap.put(Character.TYPE, "CHAR");
		typeMap.put(Short.TYPE, "MEDIUMINT(9)");
		typeMap.put(Integer.TYPE, "INT(11)");
		typeMap.put(Long.TYPE, "BIGINT(20)");
		typeMap.put(Boolean.TYPE, "TINYINT(1)");
		typeMap.put(Double.TYPE, "DOUBLE");
		typeMap.put(Float.TYPE, "FLOAT");
		typeMap.put(Enum.class, "SMALLINT");
		typeMap.put(Date.class, "DATETIME");
	}
	
	public static String getTypeFor(Class<?> clazz){
		return typeMap.get(clazz);
	}
	
	public static void setTypeFor(Class<?> clazz, String type) {
		typeMap.put(clazz, type);
	}
	
}
