package resource;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utility {
	public static RequestSpecification reqSpec;
	public RequestSpecification requestSpecification() throws IOException {
		
		
		 //log will push all our request into new file
		//capture the log and write it into file reason for fileoutputstream..bc data coming from outside
		if (reqSpec ==null) {
		PrintStream log = new PrintStream(new FileOutputStream("logging.html"));
		
		reqSpec = new  RequestSpecBuilder().setBaseUri(getGlobalValue("baseURI")).addQueryParam("key","qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.setContentType(ContentType.JSON).build();
		return  reqSpec;
	}
		//you can run the block logging every time
		return reqSpec;
	}
	//Create a method to extract values present in global.properties
	//need to read the value from inside  fileinputstream
	public static String getGlobalValue(String key) throws IOException {
	 
		Properties prop = new Properties();
	FileInputStream fs =new FileInputStream("C:\\Users\\covie\\eclipse-workspace\\MyRestAssuredFrameworkBDD4\\src\\test\\java\\resource\\global.properties");
	prop.load(fs);
	return prop.getProperty(key);
	
	}
	public String getJsonPath(Response response,String key) {
		String resp1=response.asString();
		JsonPath    js = new JsonPath(resp1);
	return	js.get(key).toString();
	}

}
