package com.gn.lab.model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import com.google.gson.Gson;

public class Google {
	private static final String URL_GOOGL_SERVICE = "https://www.googleapis.com/urlshortener/v1/url";

	private String apiKey;

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public Google(String apiKey) {
		setApiKey(apiKey);
	}

	public String shorten(String longUrl) {
		Gson gson = new Gson();
		String result = new String();
		GsonGooGl gsonGooGl = new GsonGooGl(longUrl);

		try {
			URL url = new URL(URL_GOOGL_SERVICE + "?key=" + getApiKey());

			URLConnection urlConn = url.openConnection();
			// Let the run-time system (RTS) know that we want input.
			urlConn.setDoInput(true);
			// Let the RTS know that we want to do output.
			urlConn.setDoOutput(true);
			// No caching, we want the real thing.
			urlConn.setUseCaches(false);
			// Specify the content type.
			urlConn.setRequestProperty("Content-Type", "application/json");

			// Send POST output.
			DataOutputStream printout = new DataOutputStream(urlConn.getOutputStream());
			String content = gson.toJson(gsonGooGl);
			printout.writeBytes(content);
			printout.flush();
			printout.close();

			// Get response data.
			DataInputStream input = new DataInputStream(urlConn.getInputStream());

			Scanner sc = new Scanner(input);
			while (sc.hasNext()) {
				result += sc.next();
			}

			GooGlResult gooGlResult = gson.fromJson(result, GooGlResult.class);

			return gooGlResult.getId();
		} catch (Exception ex) {
			System.out.println(ex);
			return null;
		}
	}
}

class GsonGooGl {
	public GsonGooGl() {
	}

	public GsonGooGl(String longUrl) {
		this.longUrl = longUrl;
	}

	private String longUrl;

	public String getLongUrl() {
		return longUrl;
	}

	public void setLongUrl(String longUrl) {
		this.longUrl = longUrl;
	}

}

class GooGlResult {
	public GooGlResult() {
	}

	private String kind;
	private String id;
	private String longUrl;

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLongUrl() {
		return longUrl;
	}

	public void setLongUrl(String longUrl) {
		this.longUrl = longUrl;
	}

}