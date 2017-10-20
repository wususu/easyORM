package core;

public enum TableAttribute {
	
	UNIQUE("UNIQUE (%s)"),
	PRIMARY_KEY("PRIMARY KEY (%s)"),
	NOT_NULL("NOT NULL"),
	AUTO_INCREMENT("AUTO_INCREMENT");
	
	private String template;
	
	private TableAttribute(String sql) {
		// TODO Auto-generated constructor stub
		this.template = sql;
	}
	
	public String getTemplate(){
		return this.template;
	}
}
