package sample.recaptcha;


import java.io.OutputStream;
import java.net.URL;


import javax.net.ssl.HttpsURLConnection;

public class VerifyRecaptcha {

	public static final String SITE_VERIFY_URL = //
			"https://www.google.com/recaptcha/api/siteverify";

	public static boolean verify(String gRecaptchaResponse) {
		if (gRecaptchaResponse == null || gRecaptchaResponse.length() == 0) {
			return false;
		}

		try {
			URL verifyUrl = new URL(SITE_VERIFY_URL);


			HttpsURLConnection conn = (HttpsURLConnection) verifyUrl.openConnection();


			conn.setRequestMethod("POST");
			conn.setRequestProperty("User-Agent", "Mozilla/5.0");
			conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");


			String postParams = "secret=" + MyKey.SECRET_KEY //
					+ "&response=" + gRecaptchaResponse;


			conn.setDoOutput(true);


			OutputStream outStream = conn.getOutputStream();
			outStream.write(postParams.getBytes());

			outStream.flush();
			outStream.close();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}

