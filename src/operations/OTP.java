package operations;

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
}
