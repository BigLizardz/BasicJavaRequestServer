package main;

public class PageDirectory {

	private static String path = "C:\\dev\\proto\\javaServer\\site\\";
	private static String index = "index.html";
	private static String home = "home.html";
	private static String profile = "profile.html";
	
	public static String getPath(String request) {
		if(request.contains("index")) {
			return path + index;
		} else if(request.contains("home")) {
			return path + home;
		} else if(request.contains("profile")) {
			return path + profile;
		}
		return path + index;
	}
}
