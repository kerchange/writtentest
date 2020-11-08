package test2;

public class Address extends Test2{
	String[] district = {
			"Whampoa",
			"Ho Man Tin",
			"Yau Ma Tei",
			"Mong Kok",
			"Prince Edward",
			"Shek Kip Mei",
			"Kowloon Tong",
			"Lok Fu",
			"Wong Tai Sin",
			"Diamond Hill",
			"Choi Hung",
			"Kowloon Bay",
			"Ngau Tau Kok",
			"Kwun Tong",
			"Lam Tin",
			"Yau Tong",
			"Tiu Keng Leng"
	};
	String[] building = {
			"Monday Building",
			"Tuesday Building",
			"Wednesday Building",
			"Thursday Building",
			"Friday Building",
			"Saturday Building",
			"Sunday Building"
	};
	String[] road = {
			"Star Street",
			"Moon Street",
			"Sun Street",
			"Sunny Road",
			"Rainy Road"
	};
	

	public String getDistrict() {
		int y = randBetween(0, district.length-1);
		return district[y];
	}
	public String getBuilding() {
		int y = randBetween(0, building.length-1);
		return building[y];
	}
	public String getRoad() {
		int y = randBetween(0, road.length-1);
		return road[y];
	}
	
	public String getAddress() {
		String dis = getDistrict();
		String build = getBuilding();
		String st = getRoad();
		
		return build + ", " + Integer.toString(randBetween(1,100)) + " " + st + ", " + dis ;
	}
}
