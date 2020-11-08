package test2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.*;
import org.jsoup.Jsoup; 
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class Test2 {
	public static void main(String[] args) {
		
		//question 1 dictionary DONE
		System.out.println("Question 1: ");
		Map<String, String> dict = dictionaryFunc();
		
		//question 2 regex
		boolean x = regexChecking("4234123412341234");
		System.out.println("\nQuestion 2: ");
		if(x) {
			System.out.println("Valid Card Num.");
		}else {
			System.out.println("Invalid Card Num.");
		}
		
		//question 3 times
		System.out.println("\nQuestion 3: ");
		try {
			List<String> articles = scrap();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//random
    public static int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }
    
    //dob
    public static String dobGen() {
    	GregorianCalendar gc = new GregorianCalendar();

        int year = randBetween(1910, 2020);

        gc.set(gc.YEAR, year);

        int dayOfYear = randBetween(1, gc.getActualMaximum(gc.DAY_OF_YEAR));

        gc.set(gc.DAY_OF_YEAR, dayOfYear);
        
        String dob = gc.get(gc.DAY_OF_MONTH) + "/" + (gc.get(gc.MONTH) + 1) + "/" + gc.get(gc.YEAR);
        
        return dob;
    }
    
    //email
    public static String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 10) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }
    
    //Q1
    public static Map<String, String> dictionaryFunc(){
    	Map<String, String> dic = new HashMap<String, String>();
    	//name
    	Character ch = new Character();
    	dic.put("name", ch.getFullName());
    	System.out.println(ch.getFullName());
    	//gender
    	int gender = randBetween(0,1);
    	String fm = "";
    	if(gender == 0) {
    		fm = "M";
    	}else {
    		fm = "F";
    	}    	
    	dic.put("gender", fm);
    	System.out.println(fm);
    	//dob
    	dic.put("dateOfBirth", dobGen());
    	System.out.println(dobGen());
    	//phone
    	dic.put("phoneNumber", Integer.toString(randBetween(50000000,99999999)));
    	System.out.println(Integer.toString(randBetween(50000000,99999999)));
    	//email
    	dic.put("email", getSaltString()+ "@gmail.com");
    	System.out.println(getSaltString()+ "@gmail.com");
    	//address
    	Address address = new Address();
    	dic.put("address", address.getAddress());
    	System.out.println(address.getAddress());
    	
		return dic;
    }

    //Q2
    public static boolean regexChecking(String str) {
    	Pattern p = Pattern.compile("^(([456]{1})([0-9]{3})-?([0-9]{4})-?([0-9]{4})-?([0-9]{4}))$");
    	Matcher m = p.matcher(str);
    	boolean b = m.matches();
    	
    	return b;
    	/*
    	if(b) {
    		str = str.replaceAll("-", "");
    		String[] strSplit = str.split(" ");
    		
    		int[] arr = new int[str.length()];
    		
    		Integer temp1 = 0 , temp2 = 0;
    		
    		System.out.println(strSplit.length);
    		
    		for(int i = 0 ; i < strSplit.length -1; i++) {
    			arr[i] = Integer.parseInt(strSplit[i]);
    		}
    		
    		for(Integer i = 0 ; i < arr.length ; i++) {
    			if(i!=arr.length) {
    				if(arr[i] == arr[i+1]) {
        				temp1++;
        				if(temp1 == 3) {
        					return !b;
        				}
        			}else {
        				temp1 = 0;
        			}
    			}
       		}
    		
    		return b;
    	}else {
    		return b;
    	}*/
    }
    
    //Q3
    public static List<String> scrap() throws IOException {
    	Document doc = Jsoup.connect("https://www.nytimes.com/").timeout(6000).get();
    	Elements body = doc.select("div.css-db9e03");
    	//System.out.println(body.select("div>a").size());
    	
    	List<String> articles = new ArrayList<>();
    	
    	//headline
    	String hl = body.select("div.e-big-banner-headline>a").text();
    	System.out.println(hl);
    	articles.add(hl);
    	
    	//small articles
    	for(Element e : body.select("a")) {
    		String article = e.select("h3:not(.e-custom-hed)").text();
    		if(article.trim().length() > 0) {
    			System.out.println(article);
    			articles.add(article);
    		}
    	}
		return articles;
    	
    }
}
