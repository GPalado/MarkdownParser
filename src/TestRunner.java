
import Tests.HTMLTranslator_Tests;
import Tests.MarkdownProcessor_Tests;
import Tests.ParsingMarkdown_tests;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;

import java.util.Arrays;
import java.util.List;

/**
 * The test runner
 */
public class TestRunner {

    /**
     * List all classes to test here
     */
    private static final Class<?>[] TEST_CLASSES = new Class[]{
            HTMLTranslator_Tests.class,
            MarkdownProcessor_Tests.class,
            ParsingMarkdown_tests.class,
    };

    public static void run() {
        System.out.println("Running tests in: ");
        for (Class<?> testClass : TEST_CLASSES) {
            System.out.println("- " + testClass.getSimpleName());
        }

        System.out.println();

        JUnitCore junit = new JUnitCore();
        junit.addListener(new TextListener(System.out));
        junit.run(TEST_CLASSES);
    }
}

