import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.Key;
import java.util.Base64;

public class EncryptAndDecrypt {
    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/ECB/PKCS5Padding";
    private static final String SECRET_KEY = "MySecretKey12345";

    public void FileEncrypt(String filename) throws Exception {
        try {
            File inputFile = new File(filename);
            File encryptedFile = new File(filename + ".encrypted");

            // Create encryption key
            Key key = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), ALGORITHM);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, key);

            // Read file content as string
            String content = new String(Files.readAllBytes(inputFile.toPath()), StandardCharsets.UTF_8);

            // Encrypt and encode
            byte[] encryptedBytes = cipher.doFinal(content.getBytes(StandardCharsets.UTF_8));
            String encodedContent = Base64.getEncoder().encodeToString(encryptedBytes);

            // Write encrypted and encoded data
            try (FileWriter writer = new FileWriter(encryptedFile)) {
                writer.write(encodedContent);
            }

            System.out.println("File encrypted successfully! Saved as: " + encryptedFile.getName());

        } catch (Exception e) {
            System.out.println("Error during encryption: " + e.getMessage());
            throw e;
        }
    }

    public void FileDecrypt(String encryptedFileName) throws Exception {
        try {
            File encryptedFile = new File(encryptedFileName);
            String decryptedFileName = encryptedFileName.replace(".encrypted", ".decrypted");
            File decryptedFile = new File(decryptedFileName);

            // Create decryption key
            Key key = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), ALGORITHM);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, key);

            // Read encrypted content
            String encodedContent = new String(Files.readAllBytes(encryptedFile.toPath()), StandardCharsets.UTF_8);

            // Decode and decrypt
            byte[] encryptedBytes = Base64.getDecoder().decode(encodedContent);
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
            String decryptedContent = new String(decryptedBytes, StandardCharsets.UTF_8);

            // Write decrypted content
            try (FileWriter writer = new FileWriter(decryptedFile)) {
                writer.write(decryptedContent);
            }

            System.out.println("File decrypted successfully! Saved as: " + decryptedFile.getName());

        } catch (Exception e) {
            System.out.println("Error during decryption: " + e.getMessage());
            throw e;
        }
    }
}