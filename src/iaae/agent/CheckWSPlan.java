package iaae.agent;

import jadex.bdi.runtime.Plan;

import java.io.IOException;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class CheckWSPlan extends Plan {
	// -------- methods --------

	/**
	 * Verifica se o serviço está ativo
	 */
	public void body() {
		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpGet httpget = new HttpGet("http://localhost:8000/");
			httpclient.execute(httpget);
			System.out.println("WS no ar");
		} catch (IOException e) {
			// killAgent();
		}
	}
}