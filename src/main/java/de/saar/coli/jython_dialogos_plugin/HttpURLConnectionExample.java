package de.saar.coli.jython_dialogos_plugin;

import org.json.JSONObject;
import org.json.*;
import org.json.JSONArray;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.*;
import javax.net.ssl.HttpsURLConnection;
import java.io.DataOutputStream;
import java.util.regex.Pattern;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Iterator;
import java.util.Scanner;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

public class HttpURLConnectionExample {

	public static void main(String[] args) throws Exception {

	HttpURLConnectionExample http = new HttpURLConnectionExample();
	//	System.out.println("Testing 1 - Send Http GET request");
	//	http.sendGet();
	//	System.out.println("\nTesting 2 - Send Http POST request");
	//	http.sendPost();

	}

	// HTTP GET request

	public String sendGet(String user, String key, String url) throws Exception {

		//String url = "https://habitica.com/api/v3/user?userFields=stats.hp";
		/*"https://habitica.com/api/v3/tasks/cc5d85be-964f-4cd3-a1db-8130958f01ba"; // for one specific daily
		//"https://habitica.com/api/v3/tasks/user?type=dailys"; //a request for all dailys */

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
		}else if(url.equals("https://habitica.com/api/v3/tasks/user?type=dailys")) {
			JSONObject jo = new JSONObject(response.substring(0));
			LinkedList tasks = new LinkedList();
			JSONArray jsonArray = jo.getJSONArray("data");
			for (int i = 0; i<jsonArray.length(); i++){
				JSONObject objt = jsonArray.getJSONObject(i);
				Boolean due = objt.getBoolean("completed");
				if(!due) {
					String id = objt.getString("text");
					tasks.add(id);
				}
			}

			if(tasks.isEmpty()) {
				return null;
			}else{
				String task_list = "";
				for(int num=0; num<tasks.size(); num++) {
					task_list += ", " + tasks.get(num);
      			}
			return task_list;
			}

		}
		else{
			return null;
		}

	}

	// HTTP POST request
	public String sendPost(String user, String key, String url) throws Exception {

		//String url = "https://habitica.com/api/v3/user/sleep";
		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

		if (url.equals("https://habitica.com/api/v3/tags")){
			System.out.println("I am in http post tag");
			//add reuqest header
			con.setRequestMethod("POST");
			con.setRequestProperty("x-api-user",user);//);
			con.setRequestProperty("x-api-key", key);//);
			BufferedWriter httpRequestBodyWriter = 
            new BufferedWriter(new OutputStreamWriter(con.getOutputStream()));
   
			byte[] out = "{\"name\":\"30min\"}".getBytes();
			int length = out.length;

			con.setFixedLengthStreamingMode(length);		
			
			//con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			con.connect();
			//con.setRequestProperty("name", "30min");
			
			String urlParameters = "";

			// Indicate that we want to write to the HTTP request body
			con.setDoOutput(true);
			con.setRequestMethod("POST");

			// Send post request
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.close();

			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'POST' request to URL : " + url);
			System.out.println("Post parameters : " + urlParameters);
			System.out.println("Response Code : " + responseCode);

			// Writing the post data to the HTTP request body
			BufferedReader in = new BufferedReader(
					new InputStreamReader(con.getInputStream()));
			httpRequestBodyWriter.write("name=30min");
			httpRequestBodyWriter.close();
				 
			// Reading from the HTTP response body
			Scanner httpResponseScanner = new Scanner(con.getInputStream());
			while(httpResponseScanner.hasNextLine()) {
				System.out.println(httpResponseScanner.nextLine());
			}
			httpResponseScanner.close();

			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			return response.substring(0);
		}

		else{
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
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + urlParameters);
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
				return "wach";
			}
			else{
				return "schlaf";
			}
		}else{
			return null;
		}

		//print result
		//return response.toString();

	}
}
}
