package de.saar.coli.jython_dialogos_plugin;

import java.nio.charset.StandardCharsets;
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

	public String sendGet(String user, String key, String url, String wtag) throws Exception {

		//String url = "https://habitica.com/api/v3/user?userFields=stats.hp";
		/*"https://habitica.com/api/v3/tasks/cc5d85be-964f-4cd3-a1db-8130958f01ba"; // for one specific daily
		//"https://habitica.com/api/v3/tasks/user?type=dailys"; //a request for all dailys */

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// establishing GET connection
		con.setRequestMethod("GET");
		// reading id and key
		con.setRequestProperty("x-api-user", user);
		con.setRequestProperty("x-api-key", key);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
				new InputStreamReader(con.getInputStream()));
		//Response created
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		//requests processing
		//hp request
		if (url.equals("https://habitica.com/api/v3/user?userFields=stats.hp")){

			int hp_index = response.indexOf("hp");
			String stat = response.substring(hp_index,hp_index+2);
			String wert = response.substring((hp_index + 4),(hp_index+6));
			String result = "";
			in.close();

			return result = "du hast noch " + wert +" "+ (stat.toUpperCase());

		} //exp request
		else if(url.equals("https://habitica.com/api/v3/user?userFields=stats.exp")) {
			JSONObject jo = new JSONObject(response.substring(0));
			int foo = ((jo.getJSONObject("data")).getJSONObject("stats")).getInt("exp");
			String foob = jo.getJSONObject("data").toString();

			String responsestring = response.substring(0);
			Pattern reg = Pattern.compile("\\{\"exp\"\\:(\\d+)}");
			Matcher m = reg.matcher(responsestring);
			if(m.find()){
				in.close();
				return "du besitzt, " + foo + " Erfahrungspunkte"; //m.group(1);

			}else{
				return null; //new ParseException("Failed to retrieve Account information");
			}
		}//Daily task request
		else if (url.equals("https://habitica.com/api/v3/tasks/user?type=dailys")) {
				if (wtag.equals("")){ //if no tags then all tasks
					System.out.println("hi");
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
				} else {//all tasks with Tag
					JSONObject jo = new JSONObject(response.substring(0));
					LinkedList tasks = new LinkedList();
					JSONArray jsonArray = jo.getJSONArray("data");
					for (int i = 0; i<jsonArray.length(); i++){
						JSONObject objt = jsonArray.getJSONObject(i);
						Boolean due = objt.getBoolean("completed");
						if(!due) {

							JSONArray tagarray = objt.getJSONArray("tags");
							if (!tagarray.isNull(0)){
								for (int t = 0; t<tagarray.length(); t++ ){
									if ((tagarray.getString(t)).equals(wtag)) {
										String id = objt.getString("text");
										System.out.println("spec: "+id);
										tasks.add(id);
									}
								}
							}
						}
					}
					if(tasks.isEmpty()) {
						return "Es gibt keine Aufgaben mit diesem Täg für dich zu tun.";
					}else{
						String task_list = "";
						for(int num=0; num<tasks.size(); num++) {
							task_list += ", " + tasks.get(num);
						}
						return task_list;
					}
				}
		} //get Tag ID or Taglist
		else if(url.equals("https://habitica.com/api/v3/tags")){
			if(wtag.equals("taglist")){
				JSONObject otherobj = new JSONObject(response.substring(0));
				JSONArray idarray = otherobj.getJSONArray("data");
				LinkedList tagslist = new LinkedList();
				JSONObject idobj = new JSONObject();

				for (int p = 0; p<idarray.length();p++){
						idobj = idarray.getJSONObject(p);
						String id = idobj.getString("name");
						tagslist.add(id);
				}
				System.out.println(tagslist.toString());
				if(tagslist.isEmpty()) {
					return null;
				}
				else{
					String tags_list = "";
					tags_list += tagslist.get(0);
					for(int num=1; num<tagslist.size(); num++) {
						tags_list += ", " + tagslist.get(num);
					}
					System.out.println(tags_list.toString());
					return tags_list;
				}
			}
			else {
				JSONObject otherobj = new JSONObject(response.substring(0));
				JSONArray idarray = otherobj.getJSONArray("data");
				String idname = new String();
				JSONObject idobj = new JSONObject();
				String result = new String();
				for (int p = 0; p<idarray.length();p++){
						idobj = idarray.getJSONObject(p);
						idname = idobj.getString("name");
						if (wtag.equals(idname)){
							result = idobj.getString("id");
						}
				}
				return result;
			}//create taglist for dynamic grammar

		}
		else {
			return null;
		}
	}

	// HTTP POST request
	public String sendPost(String user, String key, String url, String tag) throws Exception {

		//String url = "https://habitica.com/api/v3/user/sleep";
		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

		if (url.equals("https://habitica.com/api/v3/user/sleep")){
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
			Pattern reg = Pattern.compile("\"data\"\\:(true|false)");
			Matcher m = reg.matcher(responsestring);
			if(m.find()){ //check if already sleeping
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
		}//adding Tags request
		else{
			String urlParameters = "name=" + tag; //necessary for the body
			byte[] postData       = urlParameters.getBytes( StandardCharsets.UTF_8 );
			int    postDataLength = postData.length;
			con.setRequestMethod("POST");
			con.setRequestProperty("x-api-user",user);//);
			con.setRequestProperty("x-api-key", key);


			// Send post request with body
			con.setDoOutput(true);
			con.setInstanceFollowRedirects( false );
			con.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded");
			con.setRequestProperty( "charset", "utf-8");
			con.setRequestProperty( "Content-Length", Integer.toString( postDataLength));
			con.setUseCaches( false );
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.write(postData);

			int responseCode = con.getResponseCode();
			System.out.println("Response Code : " + responseCode);
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			wr.flush();
			wr.close();
			return "Ich habe die Tags hinzugefügt";
		}
		//print result
		//return response.toString();
	}
}
