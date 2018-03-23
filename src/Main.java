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
        //first arg -> input source
        //second arg -> output file name
        try {
            Scanner scanner = new Scanner(new File(args[0]));
            File file = new File(args[1]);
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            writer.write(new MarkdownProcessor().parseMarkdown(scanner).accept(new HTMLTranslator()));
            System.out.println("Done Translation.");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}