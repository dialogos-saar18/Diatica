package de.saar.coli.jython_dialogos_plugin;

import org.json.JSONObject;
import org.json.*;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
<<<<<<< HEAD
import java.util.*;
import java.util.stream.*;
import javax.net.ssl.HttpsURLConnection;
import org.json.*;
import org.json.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.JSONObject;
=======
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.*;
import javax.net.ssl.HttpsURLConnection;
import java.io.DataOutputStream;
import java.util.regex.Pattern;
import java.text.ParseException;
>>>>>>> 2446ced753ca24bfdde585ef488fbfd5bfecd6ca

public class HttpURLConnectionExample {

	public static void main(String[] args) throws Exception {

	//HttpURLConnectionExample http = new HttpURLConnectionExample();
	//	System.out.println("Testing 1 - Send Http GET request");
	//	http.sendGet();
	//	System.out.println("\nTesting 2 - Send Http POST request");
	//	http.sendPost();

	}

	// HTTP GET request
<<<<<<< HEAD
	// TODO changed type from void to STRING

	public String sendGet(String user, String key) throws Exception {

		String url = "https://habitica.com/api/v3/tasks/cc5d85be-964f-4cd3-a1db-8130958f01ba"; // for one specific daily
		//"https://habitica.com/api/v3/tasks/user?type=dailys"; //a request for all dailys 
		//"https://habitica.com/api/v3/user?userFields=stats.hp"; //for hp request
=======

	public String sendGet(String user, String key, String url) throws Exception {

		//String url = "https://habitica.com/api/v3/user?userFields=stats.hp";
>>>>>>> 2446ced753ca24bfdde585ef488fbfd5bfecd6ca

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		con.setRequestProperty("x-api-user", user);
		con.setRequestProperty("x-api-key", key);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
				new InputStreamReader(con.getInputStream()));
		

		String inputLine;
		StringBuffer response = new StringBuffer();

		//String str = response.toString();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
<<<<<<< HEAD
		System.out.println(response.substring(0).toString());
		System.out.println("vor json");
		JSONObject test = new JSONObject(response.substring(0));
		System.out.println(test.toString());
		String frq = (test.getJSONArray("data")).toString();
		System.out.println(frq);


		//System.out.println(test);^
		//System.out.println(frq);
		//System.out.println("start of test1:" + "\n" + response[1]);
		//System.out.println("start of test2:" + "\n"+ response.substring(1));
		//String httpresquest[];
		//httpresquest = response.substring(1);
		//in.close();
		/*
		int hp_index = response.indexOf("hp");
		//int str_length = ("hp").length();
		String stat = response.substring(hp_index,hp_index+2);
		String wert = response.substring((hp_index + 4),(hp_index+6));

		//print result
		String result = "";
		return result = "du hast noch " + wert +" "+ (stat.toUpperCase());
		//System.out.println(response.toString());
		*/
		//System.out.println(response.toString());
		return response.toString();
=======

		if (url.equals("https://habitica.com/api/v3/user?userFields=stats.hp")){
			int hp_index = response.indexOf("hp");

			String stat = response.substring(hp_index,hp_index+2);
			String wert = response.substring((hp_index + 4),(hp_index+6));

			//print result
			String result = "";
			in.close();
			return result = "du hast noch " + wert +" "+ (stat.toUpperCase());

		} else if(url.equals("https://habitica.com/api/v3/user?userFields=stats.exp")) {
			JSONObject jo = new JSONObject(response.substring(0));
			int foo = ((jo.getJSONObject("data")).getJSONObject("stats")).getInt("exp");
			String foob = jo.getJSONObject("data").toString();
			System.out.println(foob);

			String responsestring = response.substring(0);
			Pattern reg = Pattern.compile("\\{\"exp\"\\:(\\d+)}");
			Matcher m = reg.matcher(responsestring);
			if(m.find()){
				in.close();
				return "du besitzt, " + foo + " Erfahrungspunkte"; //m.group(1);

			}else{
				return null; //new ParseException("Failed to retrieve Account information");
				}
		}else{
			return null;
		}

>>>>>>> 2446ced753ca24bfdde585ef488fbfd5bfecd6ca
	}

	// HTTP POST request
	public String sendPost(String user, String key, String url) throws Exception {

		//String url = "https://habitica.com/api/v3/user/sleep";
		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

		//add reuqest header
		con.setRequestMethod("POST");
		con.setRequestProperty("x-api-user",user);//);
		con.setRequestProperty("x-api-key", key);//);

		String urlParameters = "";

		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
//		System.out.println("\nSending 'POST' request to URL : " + url);
//		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		String responsestring = response.substring(0);
		System.out.println(responsestring);
		Pattern reg = Pattern.compile("\"data\"\\:(true|false)");
		Matcher m = reg.matcher(responsestring);
		if(m.find()){
			String s = m.group(1);
			if (s.equals("false")){
				return "Willkommen zurück!";
			}
			else{
				return "Gute Nacht";
			}
		}else{
			return null;
		}

		//print result
		//return response.toString();

	}

}
