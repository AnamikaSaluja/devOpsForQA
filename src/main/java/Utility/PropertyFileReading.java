package Utility;

import java.io.*;
import java.util.*;


public class PropertyFileReading {
	
	 
    private static Properties prop;
    
    public static HashMap<String, String> propertyMap;

    private void loadProps(String propertyFile) throws FileNotFoundException,IOException {
           File cfgfile = new File(propertyFile);
           if (cfgfile.exists()) {
                  FileInputStream propin = new FileInputStream(cfgfile);
                  prop.load(propin);
           }
    }
    
    
    public String readProperty(String propkey) {           
           return prop.getProperty(propkey);
    }
    

	public  HashMap<String, String> getPropertyMap(String propertyPath ) throws FileNotFoundException, IOException {
		if(propertyMap==null) {
			propertyMap=readProperties(propertyPath);	
		}
    	return propertyMap;
    }
    
	public HashMap<String, String> readProperties(String propertyPath) throws FileNotFoundException, IOException {
		prop=new Properties();  
		HashMap<String, String> map = new HashMap<String, String>();
		String curDir = System.getProperty("user.dir");
	   		loadProps(curDir + propertyPath);
       	Set<Object> keys = prop.keySet();
	   	for(Object k:keys){
	   		String key = (String)k;
	   		map.put(key, (String)prop.getProperty(key));
	   	}
		return map;
	}
	
    

	public HashMap<String, String> getProperties(String propertyPath) throws FileNotFoundException, IOException {
		prop=new Properties();  
		HashMap<String, String> map = new HashMap<String, String>();
		
		loadProps(propertyPath);
       	Set<Object> keys = prop.keySet();
	   	for(Object k:keys){
	   		String key = (String)k;
	   		map.put(key, (String)prop.getProperty(key));
	   	}
		return map;
	}
	
	
} 



