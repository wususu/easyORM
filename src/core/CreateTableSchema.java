package core;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import annotation.Column;
import annotation.Id;
import annotation.Table;
import test.Student;
import core.TableAttribute;

public class CreateTableSchema {

	
	public TableInfo getTableInfo(Class<?> clazz){
		Table table = (Table)clazz.getAnnotation(Table.class);
		if (table == null) 
			return null;
		FieldInfo fieldInfo = null;
		TableInfo tableInfo = new TableInfo();
		
		String fullName = clazz.getName();
		
		String tableName = table.name();
		if ( tableName.equals("")) {
			tableName = clazz.getSimpleName();
		}
		tableInfo.setTableName(tableName);
		tableInfo.setTypeName(fullName);
		
		Field[] fields=  clazz.getDeclaredFields();
		for (Field field : fields) {
			fieldInfo = getFieldInfo(field);
			if (fieldInfo != null) 
				tableInfo.addFieldInfo(fieldInfo);		
		}
		
		return tableInfo;
	}
	
	
	public FieldInfo getFieldInfo(Field field){
		Column column = (Column)field.getAnnotation(Column.class);
		Id id = (Id)field.getAnnotation(Id.class);
		String fieldName = field.getName();
		String columnName = null;
		boolean isPrimaryKey = false;
		boolean isNull= true;
		boolean isUnique = false;
		int length = 255;
		Class<?> typeClazz = field.getType();
		String type = DataTypeMapper.getTypeFor(typeClazz);
		
		if (column != null) {
			columnName = (column.name().equals("")) ? fieldName : column.name();
			isNull = column.nullable();
			isUnique = column.unique();
			length = column.length();
		}else {
			columnName = fieldName;
		}
		
		if (id != null) {
			isPrimaryKey = true;
			isNull = false;
			isUnique = false;
		}
		FieldInfo fieldInfo = new FieldInfo(columnName, fieldName, isPrimaryKey, isUnique, isNull, type, length);
		return fieldInfo;
	}
	
	
	public String create(TableInfo tableInfo){
		List<FieldInfo> constrainKeys = new ArrayList<>();
		StringBuffer schemaSQL = new StringBuffer();
		schemaSQL.append("CREATE TABLE ");
		schemaSQL.append(tableInfo.getTableName());
		schemaSQL.append(" ( \n");
		Vector<FieldInfo> fieldInfos = tableInfo.getFieldInfos();
		for (FieldInfo fieldInfo : fieldInfos) {
			if (fieldInfo.isPrimaryKey() || fieldInfo.isUnique()) {
				constrainKeys.add(fieldInfo);
			}
			if (fieldInfo.isPrimaryKey()) {
				schemaSQL.append(fieldInfo.getColumnName() + " " + fieldInfo.getType() + " " + TableAttribute.NOT_NULL.getTemplate() + " " + TableAttribute.AUTO_INCREMENT.getTemplate()  + " ,\n");
			}else {
				schemaSQL.append(fieldInfo.getColumnName() + " " + fieldInfo.getType() + " ,\n");
			}
		}
		
		String constrainField = null;
		for (FieldInfo fieldInfo : constrainKeys) {
			
			if (fieldInfo.isPrimaryKey()) 
				constrainField = TableAttribute.PRIMARY_KEY.getTemplate() + ",\n";		
			if (fieldInfo.isUnique()) {
				constrainField = TableAttribute.UNIQUE.getTemplate() + ",\n";
			}		
			schemaSQL.append(
					String.format( constrainField, fieldInfo.getColumnName() ) 
					);
		}
		String finalSQL =  schemaSQL.substring(0, schemaSQL.length()-2) + ");";
		return finalSQL;
	}
	
	
	public static void main(String[] args) {
		CreateTableSchema createTableSchema = new CreateTableSchema();
		TableInfo tableInfo = createTableSchema.getTableInfo(Student.class);
		String SQL = createTableSchema.create(tableInfo);
		System.out.println(SQL);
	}
}
