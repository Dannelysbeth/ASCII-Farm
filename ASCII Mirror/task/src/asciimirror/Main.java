package asciimirror;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Input the file path:");
        Scanner scanner = new Scanner(System.in);
        String filePath = scanner.nextLine();
        List<String> lines = new ArrayList<>();
        int maxCharsInLine = 0;
        try {
            File file = new File(filePath);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
                maxCharsInLine = Math.max(maxCharsInLine, line.length());
            }
            for (String lineItem : lines) {
                String modifiedLine = String.format("%-" + maxCharsInLine + "s", lineItem);
                String reverseLine = "";
                for (int i = modifiedLine.length(); i > 0; i--) {
                    char c  = modifiedLine.charAt(i-1);
                    switch (c) {
                        case '<' :
                            c = '>';
                            break;
                        case '>' :
                            c = '<';
                            break;
                        case '(' :
                            c = ')';
                            break;
                        case ')' :
                            c = '(';
                            break;
                        case '{' :
                            c = '}';
                            break;
                        case '}' :
                            c = '{';
                            break;
                        case '[' :
                            c = ']';
                            break;
                        case ']' :
                            c = '[';
                            break;
                        case '/' :
                            c = '\\';
                            break;
                        case '\\' :
                            c = '/';
                            break;
                        default:
                            break;
                    }
                    reverseLine = reverseLine + c;
                }
                System.out.println(modifiedLine + " | " + reverseLine);
            }
        } catch (Exception e) {
            System.out.println("File not found!");
        }
        System.out.println("\n");
    }
}
