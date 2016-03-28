package co.codehaven.json2java;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.json.JSONObject;

public class Json2Java {
	
	private JavaObject rootObject;
	private HashMap<String, JavaObject> classes;
	private Queue<JavaObject> toProcess;

	public static JSONObject parseJsonString(String json){
		
		JSONObject jsonObject = new JSONObject(json);
		
		return jsonObject;
	}
	
	public Json2Java (String json){
		
		this(json, "RootObject");
		process();
	}

	public Json2Java (String json, String name){
		
		this.rootObject = new JavaObject(name, parseJsonString(json));
		
		this.toProcess = new LinkedList<JavaObject>();
		this.classes = new HashMap<String, JavaObject>();
		
		process();		
	}
	
	private void process(){
		toProcess.add(rootObject);
		classes.putIfAbsent(rootObject.getName(), rootObject);
		
		while(!toProcess.isEmpty()){
			List<JavaObject> objects = toProcess.remove().getObjectAttributes();
			for(JavaObject ja : objects){
				if (!classes.containsKey(ja.getName())){
					classes.put(ja.getName(), ja);
					toProcess.add(ja);
				}
				
			}
		}
	}
	
	public List<String> getClassesDefinitions(){
		List<String> classesDefinitions = new LinkedList<String>();
		
		for(JavaObject object : this.classes.values()){
			classesDefinitions.add(object.toString());
		}
		
		return classesDefinitions;
	}
	
	public List<String> getPossibleDuplicatedClasses(){
		List<String> duplicatedClasses = new LinkedList<String>();
		
		for(JavaObject object : this.classes.values()){
			for (JavaObject jo : this.classes.values()){
				if(object.equals(jo) && !object.getName().equals(jo.getName())){
					duplicatedClasses.add(object.toString());
				}
			}
		}
		
		return duplicatedClasses;
	}
	
}
