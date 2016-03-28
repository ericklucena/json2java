package co.codehaven.json2java;

import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JavaObject {

	private String name;
	private List<JavaObject> objectAttributes;
	private List<JavaAttribute> attributes;

	public JavaObject(String name, JSONObject jsonObject) {
		this.name = Util.toPascalCase(name);
		this.objectAttributes = new LinkedList<JavaObject>();
		this.attributes = new LinkedList<JavaAttribute>();

		String[] attributes = JSONObject.getNames(jsonObject);
		for (String s : attributes) {
			JSONObject jo = null;
			boolean isList = false;
			try {
				JSONArray ja = jsonObject.getJSONArray(s);
				jo = ja.getJSONObject(0);
				isList = true;
			} catch (JSONException je) {

			}
			try {
				if (jo == null) {
					jo = jsonObject.getJSONObject(s);
				}
				this.objectAttributes.add(new JavaObject(s, jo));
				this.attributes.add(new JavaAttribute(s, s, isList, true));
			} catch (JSONException je2) {
				this.attributes.add(new JavaAttribute(s, jsonObject.get(s).toString(), isList, false));
			}
		}
	}
	
	public String getName() {
		return name;
	}

	public List<JavaObject> getObjectAttributes() {
		return objectAttributes;
	}
	
	@Override
	public boolean equals(Object object){
		
		boolean isEqual = true;
		
		if(object instanceof JavaObject){
			JavaObject jo = (JavaObject) object;
			if(this.attributes.size() == jo.attributes.size()){
				for (JavaAttribute ja : this.attributes){
					if(!jo.attributes.contains(ja)){
						isEqual = false;
						break;
					}
				}
			}else{
				isEqual = false;
			}
		}else{
			isEqual = false;
		}
		
		return isEqual;
	}

	@Override
	public String toString() {
		String declaration = String.format(StringResources.CLASS_DEFINITION, name);
		for (JavaAttribute ja : attributes) {
			declaration += ja;
		}
		declaration += StringResources.CLASS_CLOSING;
		return declaration;
	}

}
