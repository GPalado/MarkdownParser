import MarkdownProcessor.MarkdownProcessor;
import MarkdownProcessor.Translators.HTMLTranslator;

import java.io.*;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        List<String> argsList = Arrays.asList(args);
        if (argsList.contains("--test")) {
            TestRunner.run();
        } else {
            runApp(args);
        }
    }

    private static void runApp(String[] args) {
        if(args.length < 2) return;
        try {
            Scanner scanner = new Scanner(new File(args[0]));
            File file = new File(args[1]);
            FileWriter writer = new FileWriter(file);
            String output = new MarkdownProcessor().parseMarkdown(scanner).accept(new HTMLTranslator());
            Scanner scanToOut = new Scanner(output);
            String lineSeparator = System.getProperty("line.separator");
            while(scanToOut.hasNextLine()) {
                writer.write(scanToOut.nextLine() + lineSeparator);
            }
            System.out.println("Done Translation.");
            scanner.close();
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}