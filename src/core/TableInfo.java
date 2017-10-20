package core;

import java.util.Vector;

public class TableInfo {

	private String typeName;
	
	private String tableName;
	
	private Vector<FieldInfo> fieldInfos;

	public TableInfo() {
		// TODO Auto-generated constructor stub
		this.fieldInfos = new Vector<FieldInfo>();
	}
	
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public Vector<FieldInfo> getFieldInfos() {
		return fieldInfos;
	}

	public void setFieldInfos(Vector<FieldInfo> fieldInfos) {
		this.fieldInfos = fieldInfos;
	}
	
	public void addFieldInfo(FieldInfo fieldInfo){
		this.fieldInfos.add(fieldInfo);
	}

	@Override
	public String toString() {
		return "TableInfo [typeName=" + typeName + ", tableName=" + tableName + ", fieldInfos=" + fieldInfos + "]";
	}
	
	
}
