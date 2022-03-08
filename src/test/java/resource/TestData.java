package resource;

import java.util.ArrayList;
import java.util.List;

import io.restassured.RestAssured;
import pojo.AddPlace;
import pojo.Location;

public class TestData {
	
	public AddPlace addPlacePayload(String name,String language,String address) {
		
		AddPlace p = new AddPlace();
		//RestAssured.baseURI= "https://rahulshettyacademy.com";
		//use serialisation concept tobuild our java objects  based on pojo files convert to json
	   p.setAccuracy(60);
	   p.setAddress(address);
	   p.setLanguage(language);
	   p.setPhone_number("07412346776");
	   p.setWebsite("https://rahulshettyacademy.com");
	   p.setName(name);
	   List<String> myList = new ArrayList <String>();
	   myList.add("shoe");
	   myList.add("shop");
	   p.setTypes(myList);
	   
	   Location l = new Location();
	   l.setLat(-38.384394);
	   l.setLng(33.42);
	   p.setLocation(l);
	   return p;
	}
	
	public String deletePlacePayload(String place_id) {
		return "{ \"place_id\": \""+place_id+"\"}";
	}

}
