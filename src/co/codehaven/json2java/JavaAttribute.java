package co.codehaven.json2java;

public class JavaAttribute {

	private String name;
	private String type;
	private boolean isList;

	public JavaAttribute(String name, String type, boolean isList, boolean isObject) {
		this.name = name;
		this.isList = isList;
		if (isObject) {
			this.type = Util.toPascalCase(type);
		} else {
			this.type = Util.inferType(type);
		}
	}
	
	@Override
	public boolean equals(Object object){
		
		boolean isEqual = false;
		
		if(object instanceof JavaAttribute){
			JavaAttribute jo = (JavaAttribute) object;
			isEqual = this.name.equals(jo.name) && this.type.equals(jo.type) && this.isList == jo.isList;
		}
		
		return isEqual;
	}

	@Override
	public String toString() {
		
		if (isList){
			return String.format(StringResources.LIST_ATTRIBUTE_DEFINITION, type, name);
		}else{
			return String.format(StringResources.ATTRIBUTE_DEFINITION, type, name);
		}
	}

}
