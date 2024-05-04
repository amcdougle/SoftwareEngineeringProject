public enum Type {
	LOGIN("login"),
	LOGOUT("logout"),
	LOGINGUI("LOGINGUI"),
	TELLERGUI("TELLERGUI"),
	CUSTOMERGUI("CUSTOMERGUI");
	public final String typeValue;
	
	private Type(String i) {
		this.typeValue = i;
	}
	public String getType() {
		return this.typeValue;
	}

}
