package courseSequencer.driver;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import courseSequencer.util.ContextCourseSequencer;
import courseSequencer.util.CourseRegistration;
import courseSequencer.util.FileProcessor;
import courseSequencer.util.FileProcessorInterface;
import courseSequencer.util.Logger;
import courseSequencer.util.Results;
import courseSequencer.util.StdoutDisplayInterface;

/**
 * The Driver class is the entry point of the program.
 * It reads data from a file, schedules courses, and
 * determine the graduation status of the student.
 * 
 * @author Swapnil T. Mane
 * @version 1.0
 * @since November 17, 2023
 */
public class Driver {

    /**
     * The main method is the starting point of the program execution.
     * 
     * @param args An array of command-line arguments:
     *             args[0]: Input file name
     *             args[1]: Output file name
     *             args[2]: Error logger file path
     *             args[3]: Logger file path
     *             args[4]: Debug level (0,1, 2, 3)
     */
    public static void main(String[] args) {

        /**
         * Declare global variables
         */
        ContextCourseSequencer courseSequencer = new ContextCourseSequencer();
        FileProcessorInterface file1 = new FileProcessor();
        FileProcessorInterface logFile = new FileProcessor();
        FileProcessorInterface errFile = new FileProcessor();
        FileProcessorInterface outFile = new FileProcessor();
        CourseRegistration cr = new CourseRegistration();
        StdoutDisplayInterface result = new Results();

        // Get the current system time
        LocalDateTime currentTime = LocalDateTime.now();

        // Format the time using DateTimeFormatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM:dd:yyyy:HH:mm:ss");
        String formattedTime = currentTime.format(formatter);

        /**
         * Check if the arguments passed are less than 2 and return
         * error
         */
        try {
            if (args.length != 5 || args[0].equals("${arg0}") || args[1].equals("${arg1}")
                    || args[2].equals("${arg2}") || args[3].equals("${arg3}") || args[4].equals("${arg4}")) {
                System.err.println("Error: Incorrect number of arguments. Program accepts 2 argumnets.");
                // Logger.writeMessage("Error: Incorrect number of arguments.",
                // Logger.getDebugLevel(), logFile);
                System.exit(1);
            } else {

                try {
                    Logger.setDebugValue(Integer.parseInt(args[4]));
                } catch (NumberFormatException e) {
                    System.err.println(e);
                    e.printStackTrace();
                } catch (Exception e) {
                    System.err.println(e);
                    e.printStackTrace();
                }

                /**
                 * Open the logger file for writing
                 */
                logFile.openFileWriting(args[3]);
                logFile.writeToFile("Start logging at: " + formattedTime);

                /**
                 * Set default debug level to 0
                 */
                Logger.setDebugValue(0);

                // Open the error log file for writing
                errFile.openFileWriting("errorLog.txt");

                Logger.writeMessage("Driver logic begins.", Logger.getDebugLevel(), logFile);

                // Open the input file for reading
                File inputFilePath = file1.parseArgument(args[0], errFile);
                file1.openFile(inputFilePath, errFile);

                // Open the output file for writing
                outFile.openFileWriting(args[1]);

                // register courses
                cr.registerCourses(courseSequencer, file1, outFile, errFile, logFile);

                // display output
                result.displayOutput(cr, outFile, errFile, logFile);

            }
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            Logger.writeMessage("Error: Invalid number of exceptions. " + e, Logger.getDebugLevel(), logFile);
            e.printStackTrace(); // Print the stack trace for debugging
            System.exit(1);
        } catch (Exception e) {
            // Handle exceptions and display an error message
            System.err.println("Error: " + e);
            Logger.writeMessage("Error: Invalid execution of the code. " + e, Logger.getDebugLevel(), logFile);
            e.printStackTrace(); // Print the stack trace for debugging
            System.exit(1);
        } finally {
            // Close the writer files
            outFile.closeFileWriting();
            errFile.closeFileWriting();
            logFile.writeToFile("\nEnd logging at: " + formattedTime);
            logFile.closeFileWriting();
            System.out.println("Code is executed!");
        }
    }
}
