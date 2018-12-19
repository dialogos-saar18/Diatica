package de.saar.coli.jython_dialogos_plugin;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class HttpURLConnectionExample {

	public static void main(String[] args) throws Exception {

		HttpURLConnectionExample http = new HttpURLConnectionExample();

	//	System.out.println("Testing 1 - Send Http GET request");
		http.sendGet();

	//	System.out.println("\nTesting 2 - Send Http POST request");
	//	http.sendPost();

	}

	// HTTP GET request
	// TODO changed type from void to STRING
	public String sendGet() throws Exception {

		String url = "https://habitica.com/api/v3/user?userFields=stats.hp";

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");
		// set request header
		con.setRequestProperty("x-api-user", "02ee1e91-da26-4492-99bb-a1df1fabec3d");
		con.setRequestProperty("x-api-key", "fd7eb04d-3c02-4249-90d7-53bb912a5a13");


		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		int hp_index = response.indexOf("hp");
		//int str_length = ("hp").length();
		String stat = response.substring(hp_index,hp_index+2);
		String wert = response.substring((hp_index + 4),(hp_index+6));

		//print result
		String result = "";
		return result = "du hast noch " + wert +" "+ (stat.toUpperCase());
		//System.out.println(response.toString());

	}


	// HTTP POST request
	public String sendPost() throws Exception {

		String url = "https://habitica.com/api/v3/user/sleep";
		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

		//add reuqest header
		con.setRequestMethod("POST");
		con.setRequestProperty("x-api-user", "02ee1e91-da26-4492-99bb-a1df1fabec3d");
		con.setRequestProperty("x-api-key", "fd7eb04d-3c02-4249-90d7-53bb912a5a13");

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
//		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		return response.toString();

	}

}
