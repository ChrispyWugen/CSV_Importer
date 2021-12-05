import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * An implementation of CSVImporter
 * in testcsv
 *
 * @author chris
 * @version 1.0
 * @since 2020-Jan-24
 */
public class CSVImporter {

    /* ---------------------------------------- Main ---------------------------------------------------------------- */


    private static final char DEFAULT_SEPARATOR = ',';
    private static final char DEFAULT_QUOTE = '"';

    public static void main(String[] args) throws Exception {

        //path to file
        String csvFile = "EF_test.csv";

        //#1 cut header and print each line + buffer and print final at the end
        File curFile = new File(csvFile);
        Boolean afterHeaderFlag = false;
        StringBuilder builtString = new StringBuilder();
        Scanner scanner = new Scanner(curFile);

        System.out.println("This is the line by line output: ");
        while (scanner.hasNext()) {

            String curLine = scanner.nextLine();
            String sub = curLine.substring(0, 4);
            if (sub.equals("LINK")) {
                afterHeaderFlag = true;
            }
            if (afterHeaderFlag) {
                builtString.append(curLine + "\n");
                System.out.println(curLine);
            }
        }
        scanner.close();
        System.out.println("\nThis is the StringBuilder String: \n" + builtString);

        //#2 create binary string from file
        try {
            StringBuilder sb = new StringBuilder();
            DataInputStream input = new DataInputStream(new FileInputStream(csvFile));
            try {
                while (true) {
                    sb.append(Integer.toBinaryString(input.readByte()));
                }
            } catch (EOFException eof) {
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(sb.toString());
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
        }

    }
}
