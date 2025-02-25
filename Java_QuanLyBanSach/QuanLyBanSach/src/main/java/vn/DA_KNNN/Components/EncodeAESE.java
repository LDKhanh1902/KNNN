package vn.DA_KNNN.Components;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class EncodeAESE {
    
    // Mã hóa
    public static String encrypt(String data, String key) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedData = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedData); // Chuyển dữ liệu mã hóa sang Base64
    }

    // Giải mã
    public static String decrypt(String encryptedData, String key) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decodedData = Base64.getDecoder().decode(encryptedData); // Chuyển Base64 về dạng byte
        byte[] decryptedData = cipher.doFinal(decodedData);
        return new String(decryptedData); // Chuyển dữ liệu giải mã thành chuỗi
    }

    public static void main(String[] args) {
        try {
            String key = "1234567890123456"; // Khóa 16 ký tự cho AES-128
            String originalData = "password123"; // Dữ liệu ban đầu

            // Mã hóa
            String encryptedData = encrypt(originalData, key);
            System.out.println("Encrypted: " + encryptedData);

            // Giải mã
            String decryptedData = decrypt(encryptedData, key);
            System.out.println("Decrypted: " + decryptedData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
