/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datlp.utilities;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

/**
 *
 * @author DATLPSE62823
 */
public class StringUtils {

    public static int computeMatchingDensity(String a, String b) {

        int n = a.length();

        int m = b.length();
        int dp[][] = new int[n + 1][m + 1];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (a.charAt(i) == b.charAt(j)) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                } else {
                    dp[i + 1][j + 1] = Math.max(dp[i + 1][j], dp[i][j + 1]);
                }
            }
        }

        return dp[n][m];
    }
    
    public static float computeMatchingPercent(String s1, String s2) {
        float density = computeMatchingDensity(s1, s2);
        float matchingPercent = density / Math.min(s1.length(), s2.length()) * 100;
        
        return matchingPercent;
    }
    
    public static int hashingString(String content) {
        int mod = 1000_000_007;
        int base = 30757; //random prime number
        
        int hashValue = 0;
        for(int i = 0; i < content.length(); i++) {
            hashValue = (int)(((long) hashValue * base + (long) content.charAt(i)) % mod);
        }
        
        return hashValue;
    }
    
    public static String hashPassword(String password) 
            throws NoSuchAlgorithmException, InvalidKeySpecException {
//        SecureRandom random = new SecureRandom();
//        byte[] salt = new byte[16];
//        random.nextBytes(salt);
        
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        BigInteger bigInteger = new BigInteger(1, md.digest());
        
        return bigInteger.toString();
    }
}
