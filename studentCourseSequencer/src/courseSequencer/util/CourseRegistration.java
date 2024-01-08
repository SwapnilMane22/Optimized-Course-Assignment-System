package courseSequencer.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseRegistration {
    private CourseRegistrationHelper crHelper = new CourseRegistrationHelper();
    private Map<Integer, Map<Integer, List<Character>>> studentCourses = new HashMap<>();

    public Map<Integer, Map<Integer, List<Character>>> getStudentCourses() {
        return studentCourses;
    }

    public int getCountOfStateChange() {
        return crHelper.getCountChangeinState();
    }

    public String getGraduated() {
        if (crHelper.checkGraduation()) {
            return "Graduated!";
        } else {
            return "Not Graduated!";
        }
    }

    public void registerCourses(ContextCourseSequencer courseSequencer, FileProcessorInterface fIn,
            FileProcessorInterface outFileIn,
            FileProcessorInterface errFileIn, FileProcessorInterface logFileIn) {

        String line = new String();
        int studentId = 0;
        crHelper.setLogFileHandler(logFileIn);

        // Get the current system time
        LocalDateTime currentTime = LocalDateTime.now();

        // Format the time using DateTimeFormatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM:dd:yyyy:HH:mm:ss");
        String formattedTime = currentTime.format(formatter);

        try {
            if (!fIn.hasNextLine()) {
                errFileIn.writeToFile("Error: Blank input file with filename: input.txt");
                System.err.println("Error: Blank input file with filename: input.txt");
                Logger.writeMessage("Error: Blank input file with filename: input.txt",
                        Logger.getDebugLevel(), logFileIn);
                logFileIn.writeToFile("\nEnd logging at: " + formattedTime);
                System.exit(1);
            }
            // parse the update value
            try {
                // Read and process each line from the input file
                while ((line = fIn.readLine()) != null) {
                    String[] splitLine = line.split(":", -2);

                    try {
                        studentId = Integer.parseInt(splitLine[0]);
                    } catch (NumberFormatException e) {
                        System.err.println(e);
                        e.printStackTrace();
                        Logger.writeMessage("Error: " + e, Logger.getDebugLevel(), logFileIn);
                        errFileIn.writeToFile("Error: Incorrect parsing " + e);
                        System.exit(1);
                    } catch (Exception e) {
                        System.err.println(e);
                        e.printStackTrace();
                        Logger.writeMessage("Error: " + e, Logger.getDebugLevel(), logFileIn);
                        errFileIn.writeToFile("Error: " + e);
                        System.exit(1);
                    }

                    String[] prefStrings = splitLine[1].split(" ", -2);

                    for (int i = 0; i < prefStrings.length; i++) {
                        if (prefStrings[i].length() > 0) {
                            // Now, you can use studentId and pref to update the course sequencer
                            char currCourse = prefStrings[i].charAt(0);

                            crHelper.setContextCourse(courseSequencer);

                            courseSequencer.processCourses(currCourse, crHelper);
                        }
                    }
                    studentCourses.put(studentId, crHelper.getCurrSemCourses());
                }

            } catch (Exception e) {
                // Handle exceptions and display an error message
                System.err.println("Error: " + e);
                errFileIn.writeToFile("Error: " + e);
                Logger.writeMessage("Error: Invalid execution of the code. " + e, Logger.debugLevel, logFileIn);
                e.printStackTrace(); // Print the stack trace for debugging
            }
        } catch (Exception e) {
            // Handle exceptions and display an error message
            System.err.println("Error: " + e);
            errFileIn.writeToFile("Error: " + e);
            Logger.writeMessage("Error: Invalid execution of the code. " + e, Logger.debugLevel, logFileIn);
            e.printStackTrace(); // Print the stack trace for debugging
            System.exit(1);
        } finally {
            logFileIn.writeToFile("\nEnd logging at: " + formattedTime);
        }
    }
}