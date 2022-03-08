package resource;
//

//enum is special class in java which has collection of constants and methods
public enum APIResources {
	
	AddplaceAPI("/maps/api/place/add/json"),
	getPlaceAPI("maps/api/place/get/json"),
	//updatePlaceAPI("maps/api/place/update/json"),
	deletePlaceAPI("maps/api/place/delete/json");
	
	private String resource;
	APIResources(String resource) {
		this.resource= resource;
		
	}
	
	public String getResource() {
		return resource;
	}

}
