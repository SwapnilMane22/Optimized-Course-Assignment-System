package courseSequencer.util;

import java.util.List;
import java.util.Map;

public class Results implements StdoutDisplayInterface {

    public void displayOutput(CourseRegistration crIn,
            FileProcessorInterface outFileIn, FileProcessorInterface errFileIn, FileProcessorInterface logFileIn) {

        Map<Integer, Map<Integer, List<Character>>> studentCoursesIn = crIn.getStudentCourses();
        try {
            for (Map.Entry<Integer, Map<Integer, List<Character>>> entry : studentCoursesIn.entrySet()) {

                int studentId = entry.getKey();

                Map<Integer, List<Character>> coursesBySemester = entry.getValue();

                int totalSemesters = coursesBySemester.size();

                // Print the output for each student
                // System.out.print(studentId + ": ");
                outFileIn.writeToFile(studentId + ": ");
                for (List<Character> courses : coursesBySemester.values()) {
                    for (char course : courses) {
                        // System.out.print(course + " ");
                        outFileIn.writeToFile(course + " ");
                    }
                }
                if (crIn.getGraduated().equals("Graduated!")) {
                    // System.out.println(
                    //         "-- " + totalSemesters + " " + crIn.getCountOfStateChange() + " " + crIn.getGraduated());
                    outFileIn.writeToFile(
                            "-- " + totalSemesters + " " + crIn.getCountOfStateChange() + " " + crIn.getGraduated()
                                    + "\n");

                } else {

                    // System.out.println(
                    //         "-- " + 0 + " " + crIn.getCountOfStateChange() + " " + crIn.getGraduated());
                    outFileIn.writeToFile(
                            "-- " + 0 + " " + crIn.getCountOfStateChange() + " " + crIn.getGraduated() + "\n");
                }

                for (Map.Entry<Integer, List<Character>> semesterEntry : coursesBySemester.entrySet()) {
                    int semesterNumber = semesterEntry.getKey();
                    List<Character> semesterCourses = semesterEntry.getValue();

                    // Check if semester number is greater than 0 and courses are not blank
                    if (semesterNumber >= 0 && !semesterCourses.isEmpty()) {
                        // System.out.print("Semester " + semesterNumber + " Courses: ");
                        outFileIn.writeToFile("Semester " + semesterNumber + " Courses: ");
                        for (char course : semesterCourses) {
                            // System.out.print(course + " ");
                            outFileIn.writeToFile(course + " ");
                        }
                        // System.out.println();
                        outFileIn.writeToFile("\n");
                    }
                }
            }
        } catch (Exception e) {
            // Handle exceptions related to file operations
            System.err.println("Error during file operations: " + e.getMessage());
            e.printStackTrace(); // Print the stack trace for debugging
            errFileIn.writeToFile("Error during file operations: " + e.getMessage() + "\n");
        } finally {
            // Close files in the finally block to ensure proper cleanup
            try {
                outFileIn.closeFileWriting();
                errFileIn.closeFileWriting();
                logFileIn.closeFileWriting();
            } catch (Exception e) {
                // Handle exceptions related to file closing
                System.err.println("Error closing files: " + e.getMessage());
                e.printStackTrace(); // Print the stack trace for debugging
            }
        }
    }
}