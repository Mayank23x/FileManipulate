
import java.io.*;
import java.util.*;

public class Filedata {
    public String filename;
    Scanner sc = new Scanner(System.in);

    public void EditFile() throws InterruptedException {
        File file = new File(filename);

        System.out.println("\n=== File Operations ===");
        System.out.println("C. Change file name");
        System.out.println("R. Read file");
        System.out.println("D. Delete file");
        System.out.println("Q. Quit");

        char ch = sc.next().charAt(0);
        while (ch != 'Q' && ch != 'q') {
            switch (ch) {
                case 'C':
                    System.out.print("Enter new filename: ");
                    file.renameTo(new File(sc.next()));
                    System.out.println("File renamed successfully!");
                    Thread.sleep(1000);
                    break;

                case 'R':
                    try (FileReader reader = new FileReader(filename)) {
                        System.out.println("\n=== File Content ===");
                        int data = reader.read();
                        while (data != -1) {
                            System.out.print((char) data);
                            data = reader.read();
                        }
                        reader.close();
                        System.out.println("\n=== End of File ===");
                    } catch (IOException e) {
                        System.out.println("Error reading file: " + e.getMessage());
                        e.printStackTrace();
                    }
                    break;

                case 'D':
                    if (file.delete()) {
                        System.out.println("File deleted successfully!");
                    } else {
                        System.out.println("Failed to delete the file.");
                    }
                    break;

                default:
                    System.out.println("Invalid Option!");
            }

            System.out.println("\n=== File Operations ===");
            System.out.println("C. Change file name");
            System.out.println("R. Read file");
            System.out.println("D. Delete file");
            System.out.println("Q. Quit");
            ch = sc.next().charAt(0);
        }
    }

    public void FileInfo() throws InterruptedException {
        File file = new File(filename);
        if (file.exists()) {
            System.out.println("\n=== File Information ===");
            System.out.println("File path: " + file.getAbsolutePath());
            System.out.println("File name: " + filename);
            System.out.println("File size: " + file.length() + " Bytes");
            System.out.println("Last modified: " + new Date(file.lastModified()));
            EditFile();
        } else {
            System.out.println("File does not exist!");
        }
    }

    public void CreateNewFile() throws IOException {
        System.out.print("Enter Filename: ");
        filename = sc.next();
        filename = filename + ".txt";
        File file = new File(filename);

        if (!file.exists()) {
            try {
                boolean isFileCreated = file.createNewFile();
                Thread.sleep(1000);
                if (isFileCreated) {
                    System.out.println("\n=== File Created Successfully ===");
                    System.out.println("File path: " + file.getAbsolutePath());
                    System.out.println("File name: " + filename);
                } else {
                    System.out.println("File already exists!");
                }
            } catch (IOException | InterruptedException e) {
                System.out.println("Error occurred: " + e.getMessage());
            }
        } else {
            System.out.println("File already exists!");
        }
    }
}