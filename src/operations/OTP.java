package operations;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Random;

/**
 * Created By: Naman Agarwal
 * User ID: naman2807
 * Package Name: operations
 * Project Name: BankingApplication
 * Date: 26-05-2021
 */

public final class OTP {
    private static long otp;

    private OTP(){}

    public static long generateOTP(){
        otp = new Random().nextInt(9);
        for(int i = 1; i< 4; i++){
            otp = (otp * 10) + new Random().nextInt(9);
        }
        return otp;
    }

    public static boolean verifyOTP(String returnedOTP){
        return String.valueOf(otp).equals(returnedOTP);
    }

    public static void sendOTPToLinkedAccountNumber(){
        try {
            // Construct data
            String apiKey = "apikey=" + "MTdjMjgxMWNiOGRkYTk4OGQ1ODZkYzlhOGQwMjBjODc=";
            String message = "&message=" + "Hello";
//            String sender = "&sender=" + "Bank Of India";
            String numbers = "&numbers=" + "919140321247";

            // Send data
            HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
            String data = apiKey + numbers + message;
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
            conn.getOutputStream().write(data.getBytes(StandardCharsets.US_ASCII));
            final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            final StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = rd.readLine()) != null) {
                stringBuffer.append(line);
            }
            System.out.println(stringBuffer.toString());
            rd.close();
        } catch (Exception e) {
            System.out.println("Error SMS "+e);

        }
    }
}
