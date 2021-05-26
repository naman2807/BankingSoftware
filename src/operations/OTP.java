package operations;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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

    public static boolean verifyOTP(long returnedOTP){
        return otp == returnedOTP;
    }

    public static void sendOTPToLinkedAccountNumber(){
        try {
            // Construct data
            String apiKey = "apikey=" + "MGJkMTc3NWQxZTBlNDYwMjk4OWJlNzhlZTJhYzI5ZGI=";
            String message = "&message=" + "This is your message";
            String sender = "&sender=" + "Bank Of India";
            String numbers = "&numbers=" + "919838164810";

            // Send data
            HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
            String data = apiKey + numbers + message + sender;
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
            conn.getOutputStream().write(data.getBytes("UTF-8"));
//            final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//            final StringBuffer stringBuffer = new StringBuffer();
//            String line;
//            while ((line = rd.readLine()) != null) {
//                stringBuffer.append(line);
//            }
//            rd.close();
        } catch (Exception e) {
            System.out.println("Error SMS "+e);

        }
    }
}
