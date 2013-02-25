package iaae;

import iaae.models.Discussion;
import iaae.models.Thesis;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class GenerateThesisService {

	public static String URL_TO_GENERATE = "http://localhost:8000/vle/discussion/to_generate/";
	public static String URL_GENERATE = "http://localhost:8000/vle/discussion/generate/";
	public static String URL_ONTOLOGY = "http://localhost:8000/vle/ontology/ornitologia/export";
	public static String URL_LOG = "http://localhost:8000/vle/ws/log/add/";

	public String readUrl(String url) throws Exception {
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet(url);
		HttpResponse response = httpclient.execute(httpget);
		HttpEntity entity = response.getEntity();
		return EntityUtils.toString(entity);
	}

	public InputStream getOntology(String slug) throws Exception {
		InputStream ontology = null;
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet(URL_ONTOLOGY);
		HttpResponse response = httpclient.execute(httpget);
		HttpEntity entity = response.getEntity();
		return entity.getContent();

	}

	public void log(String message, String slug) throws Exception {
		HttpClient client = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(URL_LOG + slug + "/");

		httppost.setHeader("Content-Type", "application/json");

		JSONObject t = new JSONObject();
		t.put("message", message);

		List<NameValuePair> list = new ArrayList<NameValuePair>();
		list.add(new BasicNameValuePair("data", t.toString()));

		httppost.setEntity(new UrlEncodedFormEntity(list, "UTF-8"));
		HttpResponse r = client.execute(httppost);
	}

	public void postData(String url, int pk, List<Thesis> theses)
			throws Exception {
		HttpClient client = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(url + String.valueOf(pk) + "/");
		httppost.setHeader("Content-Type", "application/json");
		List<NameValuePair> list = new ArrayList<NameValuePair>();

		JSONArray theses_json = new JSONArray();
		for (Thesis thesis : theses) {
			JSONObject t = new JSONObject();
			t.put("valid", thesis.isValid());
			t.put("description", thesis.getDescription());
			theses_json.put(t);
		}
		list.add(new BasicNameValuePair("data", theses_json.toString()));
		httppost.setEntity(new UrlEncodedFormEntity(list, "UTF-8"));

		HttpResponse r = client.execute(httppost);
	}

	public List<Discussion> getDiscussions() {
		List<Discussion> result = new ArrayList<Discussion>();

		try {
			String jsonTxt = this.readUrl(URL_TO_GENERATE);

			JSONObject json = new JSONObject(jsonTxt);
			JSONArray discussions = json.getJSONArray("discussions");

			for (int i = 0; i < discussions.length(); ++i) {
				JSONObject rec = discussions.getJSONObject(i);
				if ((!rec.isNull("category")) && (!rec.isNull("pk"))) {
					Discussion discussion = new Discussion();
					discussion.setCategory(rec.getString("category"));
					discussion.setPk(rec.getInt("pk"));
					discussion.setNumber_theses(rec.getInt("number_theses"));
					result.add(discussion);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;

	}

	public boolean saveTheses(Discussion discussion, List<Thesis> theses) {
		try {
			this.postData(URL_GENERATE, discussion.getPk(), theses);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
