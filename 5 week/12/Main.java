package Croc.work12;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Main extends Thread{
    private static final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();

    private static String toHexString(byte[] bytes) {
        StringBuilder hex = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) {
            hex.append(HEX_DIGITS[(b & 0xff) >> 4]);
            hex.append(HEX_DIGITS[b & 0x0f]);
        }
        return hex.toString();
    }

    private static String hashPassword(String password) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        digest.update(password.getBytes());
        byte[] bytes = digest.digest();
        return toHexString(bytes);
    }

    public static String isPassword(String passwordHash) {
        char[] temp = {'a', 'a', 'a', 'a', 'a', 'a', 'a'};
        // метод очень грубой силы
        for (int a = 0; a < 26; a++) {
            for (int b = 0; b < 26; b++) {
                for (int c = 0; c < 26; c++) {
                    for (int d = 0; d < 26; d++) {
                        for (int e = 0; e < 26; e++) {
                            for (int f = 0; f < 26; f++) {
                                for (int g = 0; g < 26; g++) {
                                    String tempStr = new String(temp);
                                    if (hashPassword(tempStr) == passwordHash) {
                                        return tempStr;
                                    }
                                    temp[6]++;
                                }
                                temp[6] = 'a';
                                temp[5]++;
                            }
                            temp[5] = 'a';
                            temp[4]++;
                        }
                        temp[4] = 'a';
                        temp[3]++;
                    }
                    temp[3] = 'a';
                    temp[2]++;
                }
                temp[2] = 'a';
                temp[1]++;
            }
            temp[1] = 'a';
            temp[0]++;
        }
        return null;
    }

    @Override
    public void run() {
        System.out.println(isPassword("40682260CC011947FC2D0B1A927138C5"));
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int numberOfThreads = in.nextInt();
        for (int i = 0; i<numberOfThreads; i++){
            Thread thread = new Main();
            thread.start();
        }
    }
}
