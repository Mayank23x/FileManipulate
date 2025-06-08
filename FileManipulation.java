import java.io.File;
import java.util.Scanner;

public class FileManipulation {

    public static void main(String[] args) throws Exception {
        try (Scanner sc = new Scanner(System.in)) {
            Filedata filedata = new Filedata();

            while (true) {
                System.out.println("==========FILE==========");
                System.out.println("1.Create new File");
                System.out.println("2.View Existing File");
                System.out.println("3.Encrypt/Decrypt the File");
                System.out.println("4.Quit");
                System.out.print("Choose Your option : ");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        filedata.CreateNewFile();
                        break;
                    case 2:
                        System.out.println("=======View Existing File=======");
                        System.out.print("Enter Filename : ");
                        filedata.filename = sc.next();
                        System.out.println("File Information");
                        filedata.FileInfo();
                        break;
                    case 3:
                        System.out.println("=======Encrypt/Decrypt the File=======");
                        System.out.println("1. Encrypt File");
                        System.out.println("2. Decrypt File");
                        System.out.print("Choose option (1/2): ");
                        int cryptChoice = sc.nextInt();

                        EncryptAndDecrypt encryptAndDecrypt = new EncryptAndDecrypt();

                        switch (cryptChoice) {
                            case 1:
                                System.out.print("Enter Filename to encrypt: ");
                                String filename = sc.next();
                                File file = new File(filename);
                                if (file.exists()) {
                                    encryptAndDecrypt.FileEncrypt(filename);
                                    System.out.println("File is saving .....");
                                } else {
                                    System.out.println("File does not exist!");
                                }
                                break;

                            case 2:
                                System.out.print("Enter encrypted filename to decrypt: ");
                                String encryptedFile = sc.next();
                                File encFile = new File(encryptedFile);
                                if (encFile.exists()) {
                                    encryptAndDecrypt.FileDecrypt(encryptedFile);
                                    System.out.println("File is saving .....");
                                } else {
                                    System.out.println("Encrypted file does not exist!");
                                }
                                break;

                            default:
                                System.out.println("Invalid option!");
                        }
                        break;
                    case 4:
                        System.out.println("Exiting program...");
                        return; // Exit the program
                    default:
                        System.out.println("Invalid Option");
                }
            }
        }
    }
}