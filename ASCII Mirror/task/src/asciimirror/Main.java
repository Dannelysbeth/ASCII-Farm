package asciimirror;

import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Input the file path:");
        Scanner scanner = new Scanner(System.in);
        String filePath = scanner.nextLine();
        try {
            File file = new File(filePath);
            FileReader fileReader = new FileReader(file);
            while(file.canRead()){
                int character = fileReader.read();
                if(character == -1){
                    break; // End of file has been reached
                }
                String fileOutput = String.valueOf((char) character);
                System.out.print(fileOutput);
            }

            // Do something with file (e.g. read, write, etc.)
        } catch (Exception e) {
            System.out.println("File not found!");
        }
    }
}
