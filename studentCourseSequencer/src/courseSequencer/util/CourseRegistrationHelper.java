package courseSequencer.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import courseSequencer.state.State;
import courseSequencer.state.StateFive;
import courseSequencer.state.StateFour;
import courseSequencer.state.StateOne;
import courseSequencer.state.StateThree;
import courseSequencer.state.StateTwo;

/**
 * The CourseRegistrationHelper class provides methods to assist in course
 * registration,
 * including managing the current semester's courses, handling waitlists,
 * tracking group counts,
 * and checking graduation eligibility.
 * 
 * It utilizes a Queue data structure to implement the waitlist, ensuring a
 * first-in-first-out order
 * for processing courses when spots become available.
 * 
 * 
 */
public class CourseRegistrationHelper {

    private ContextCourseSequencer courseSequencer;
    private Map<Integer, List<Character>> currentSemesterCourses = new HashMap<>();
    private Queue<Character> waitlist = new LinkedList<>();
    private int[] groupCounts = new int[5];
    private FileProcessorInterface logFileHandler;
    private int countChangeinState = 0;
    private int coursesInCurrentSemester = 0;
    private int totalCourses = 0;
    private int indexSem = 0;

    /**
     * Gets the waitlist.
     * 
     * @return The waitlist queue.
     */
    public Queue<Character> getWaitlist() {
        return waitlist;
    }

    /**
     * Sets the file processor for logging.
     * 
     * @param logFileIn The file processor for logging.
     */
    public void setLogFileHandler(FileProcessorInterface logFileIn) {
        try {
            if (logFileIn != null) {
                logFileHandler = logFileIn;
            }
        } catch (Exception e) {
            System.err.println("Error: " + e);
            e.printStackTrace();
        }
    }

    /**
     * Gets the file processor for logging.
     * 
     * @return The file processor for logging.
     */
    public FileProcessorInterface getLogFile() {
        return logFileHandler;
    }

    /**
     * Gets the context course sequencer.
     * 
     * @return The context course sequencer.
     */
    public ContextCourseSequencer getContextCourse() {
        return courseSequencer;
    }

    /**
     * Gets the context state.
     * 
     * @return The context state.
     */
    public State getContextState() {
        return courseSequencer.getState();
    }

    /**
     * Sets the context course sequencer.
     * 
     * @param contextClassIn The context course sequencer.
     */
    public void setContextCourse(ContextCourseSequencer contextClassIn) {
        try {
            if (contextClassIn != null) {
                courseSequencer = contextClassIn;
            }
        } catch (Exception e) {
            System.err.println("Error: " + e);
            e.printStackTrace();
            Logger.writeMessage("Error: " + e, Logger.getDebugLevel(), logFileHandler);
        }
    }

    /**
     * Gets the current semester courses.
     * 
     * @return The current semester courses map.
     */
    public Map<Integer, List<Character>> getCurrSemCourses() {
        return currentSemesterCourses;
    }

    /**
     * Sets the context state.
     * 
     * @param stateIn The context state.
     */
    public void setContextState(State stateIn) {
        courseSequencer.setState(stateIn);
    }

    /**
     * Sets the current semester courses for a given index.
     * 
     * @param index    The index of the semester.
     * @param courseIn The course to add to the current semester.
     * @return The updated current semester courses map.
     */
    public Map<Integer, List<Character>> setCurrSemCourses(int index, char courseIn) {
        try {
            Logger.writeMessage("Adding course to currentSemesterCourses, Index: " + index + "course: "
                    + courseIn, Logger.getDebugLevel(), logFileHandler);
            List<Character> semesterCourses = currentSemesterCourses.get(index);
            if (semesterCourses == null) {
                semesterCourses = new ArrayList<>();
                currentSemesterCourses.put(index, semesterCourses);
            }
            semesterCourses.add(courseIn);
            currentSemesterCourses.put(index, semesterCourses);
            // printCurrentSemesterCourses();

            coursesInCurrentSemester++;
            return currentSemesterCourses;
        } catch (Exception e) {
            System.err.println("Error: " + e);
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Prints the current semester courses.
     */
    public void printCurrentSemesterCourses() {
        System.out.println("Allotted Current Semester Courses: ");
        for (Map.Entry<Integer, List<Character>> entry : currentSemesterCourses.entrySet()) {
            int semesterNumber = entry.getKey();
            List<Character> semesterCourses = entry.getValue();

            System.out.print("Semester " + semesterNumber + " Courses: ");
            for (char course : semesterCourses) {
                System.out.print(course + " ");
            }
            System.out.println();
        }
    }

    /**
     * Checks if the waitlist is empty.
     * 
     * @return True if the waitlist is empty; otherwise, false.
     */
    public boolean checkWaitlist() {
        return waitlist.isEmpty();
    }

    /**
     * Gets the group counts array.
     * 
     * @return The group counts array.
     */
    public int[] getGroupCounts() {
        return groupCounts;
    }

    /**
     * Sets the group count for a given course.
     * 
     * @param course The course for which to set the group count.
     */
    public void setGroup(char course) {
        try {
            Logger.writeMessage("Set group count for course: "
                    + course, Logger.getDebugLevel(), logFileHandler);
            char group = determineGroup(course);
            Logger.writeMessage("Group: " + group, Logger.getDebugLevel(), logFileHandler);
            switch (group) {
                case 'A':
                    groupCounts[0]++;
                    Logger.writeMessage("Updated count for group 0: " + groupCounts[0], Logger.getDebugLevel(),
                            logFileHandler);
                    break;
                case 'E':
                    groupCounts[1]++;
                    Logger.writeMessage("Updated count for group 1: "
                            + groupCounts[1], Logger.getDebugLevel(), logFileHandler);
                    break;
                case 'I':
                    groupCounts[2]++;
                    Logger.writeMessage("Updated count for group 2: "
                            + groupCounts[2], Logger.getDebugLevel(), logFileHandler);
                    break;
                case 'M':
                    groupCounts[3]++;
                    Logger.writeMessage("Updated count for group 3: "
                            + groupCounts[3], Logger.getDebugLevel(), logFileHandler);
                    break;
                case 'Q':
                    groupCounts[4]++;
                    Logger.writeMessage("Updated count for group 4: "
                            + groupCounts[4], Logger.getDebugLevel(), logFileHandler);
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            System.err.println("Error: " + e);
            e.printStackTrace();
            Logger.writeMessage("Error: " + e, Logger.getDebugLevel(), logFileHandler);
        }
    }

    /**
     * Adds a course to the waitlist.
     * 
     * @param CourseIn The course to add to the waitlist.
     */
    public void setWaitlist(char CourseIn) {
        try {
            if (!waitlist.contains(CourseIn)) {
                waitlist.add(CourseIn);
            }
        } catch (Exception e) {
            System.err.println("Error: " + e);
            e.printStackTrace();
            Logger.writeMessage("Error: " + e, Logger.getDebugLevel(), logFileHandler);
        }
    }

    /**
     * Retrieves and removes the head of the waitlist.
     * 
     * @return The head of the waitlist, or '\0' if the waitlist is empty.
     */
    public char pollWaitlist() {
        try {
            if (!checkWaitlist()) {
                return waitlist.poll();
            } else {
                return '\0'; // Return a default value when the waitlist is empty
            }
        } catch (Exception e) {
            System.err.println("Error: " + e);
            e.printStackTrace();
            Logger.writeMessage("Error: " + e, Logger.getDebugLevel(), logFileHandler);
            return '\0';
        }
    }

    /**
     * Gets the number of courses in the current semester.
     * 
     * @return The number of courses in the current semester.
     */
    public int getCoursesInCurrentSemester() {
        return coursesInCurrentSemester;
    }

    /**
     * Gets the count of state changes.
     * 
     * @return The count of state changes.
     */
    public int getCountChangeinState() {
        return countChangeinState;
    }

    /**
     * Determines the group of a given course.
     * 
     * @param CourseIn The course for which to determine the group.
     * @return The group of the course.
     */
    public char determineGroup(char CourseIn) {
        if (CourseIn >= 'A' && CourseIn <= 'D') {
            return 'A';
        } else if (CourseIn >= 'E' && CourseIn <= 'H') {
            return 'E';
        } else if (CourseIn >= 'I' && CourseIn <= 'L') {
            return 'I';
        } else if (CourseIn >= 'M' && CourseIn <= 'P') {
            return 'M';
        } else if (CourseIn >= 'Q' && CourseIn <= 'Z') {
            return 'Q';
        } else {
            return '\0';
        }
    }

    /**
     * Gets the index of the given state.
     * 
     * @param stateIn The state for which to get the index.
     * @return The index of the state.
     */
    public int getStateIndex(State stateIn) {
        if (stateIn instanceof StateOne) {
            return 0;
        } else if (stateIn instanceof StateTwo) {
            return 1;
        } else if (stateIn instanceof StateThree) {
            return 2;
        } else if (stateIn instanceof StateFour) {
            return 3;
        } else if (stateIn instanceof StateFive) {
            return 4;
        } else {
            return -1;
        }
    }

    /**
     * Counts the group state and updates the context state if necessary.
     * 
     * @return The updated state.
     */
    public State countGroupState() {
        int maxGroupCount = -1;
        int maxGroupIndex = -1;
        State newState = getContextState();
        int stateIndex = getStateIndex(newState);

        for (int i = 0; i < getGroupCounts().length; i++) {
            if (getGroupCounts()[i] > maxGroupCount) {
                maxGroupCount = getGroupCounts()[i];

                Logger.writeMessage("\nGroup: " + i + " has MAX group count as: " + maxGroupCount,
                        Logger.getDebugLevel(), logFileHandler);
                maxGroupIndex = i;
            }
        }
        if (maxGroupIndex != stateIndex && getGroupCounts()[maxGroupIndex] == getGroupCounts()[stateIndex]) {
            setContextState(newState);
            return newState;
        } else if (maxGroupIndex == stateIndex) {
            setContextState(newState);
            return newState;
        } else {
            switch (maxGroupIndex) {
                case 0:
                    if (!(newState instanceof StateOne)) {
                        newState = new StateOne();
                        setContextState(newState);
                    }
                    return newState;
                case 1:
                    if (!(newState instanceof StateTwo)) {
                        newState = new StateTwo();
                        setContextState(newState);
                    }
                    return newState;
                case 2:
                    if (!(newState instanceof StateThree)) {
                        newState = new StateThree();
                        setContextState(newState);
                    }
                    return newState;
                case 3:
                    if (!(newState instanceof StateFour)) {
                        newState = new StateFour();
                        setContextState(newState);
                    }
                    return newState;
                case 4:
                    if (!(newState instanceof StateFive)) {
                        newState = new StateFive();
                        setContextState(newState);
                    }
                    return newState;
                default:
                    return newState;
            }
        }

    }

    /**
     * Checks if the course meets the prerequisite and adds it to the waitlist if
     * necessary.
     * 
     * @param Course The course to check.
     * @return True if the prerequisite is met; otherwise, false.
     */
    public boolean passPrerequisite(char Course) {
        boolean flag = false;
        int totalSemesters = 0;
        if (Course != '\0') {
            char firstCourse = determineGroup(Course);
            Logger.writeMessage("Check Pre-requisite for Course: " + Course, Logger.getDebugLevel(), logFileHandler);
            if (firstCourse == Course || firstCourse == 'Q') {
                flag = true;
            } else {
                totalSemesters = currentSemesterCourses.entrySet().size();
                char reqCourse = (char) (Course - 1);

                if (totalSemesters > 0) {
                    for (Map.Entry<Integer, List<Character>> entry : currentSemesterCourses.entrySet()) {
                        int semesterNumber = entry.getKey();
                        List<Character> semesterCourses = entry.getValue();

                        if (semesterNumber < indexSem) {
                            for (char course : semesterCourses) {
                                Logger.writeMessage(" Sem Num: " + semesterNumber + " req Course: " +
                                        reqCourse + " Course: " + Course + " found course: "
                                        + course, Logger.getDebugLevel(), logFileHandler);
                                if (course != reqCourse) {
                                    flag = false;
                                } else {
                                    Logger.writeMessage("Condition passed for course: "
                                            + Course, Logger.getDebugLevel(), logFileHandler);
                                    flag = true;
                                    break;
                                }
                            }
                        }
                        if (flag) {
                            break;
                        }
                    }
                } else {
                    flag = false;
                }
            }
        }
        if (!flag && !waitlist.contains(Course)) {
            Logger.writeMessage("Adding: " + Course, Logger.getDebugLevel(), logFileHandler);
            waitlist.add(Course);
        }
        return flag;
    }

    /**
     * Checks whether a student is eligible for graduation based on completed
     * courses and group requirements.
     *
     * @return true if the student is eligible for graduation, false otherwise.
     */
    public boolean checkGraduation() {

        // Check if the student has completed 10 courses
        if (totalCourses < 10) {
            return false;
        }

        // Check if the student has taken 2 courses per group
        for (int count : groupCounts) {
            if (count < 2) {
                return false; // Student hasn't taken 2 courses from each group
            }
        }

        return true;
    }

    /**
     * Performs a comprehensive check for course assignment, including checking
     * prerequisites,
     * graduation eligibility, and updating the current semester courses.
     *
     * @param courseIn   The course to be assigned.
     * @param crHelperIn The CourseRegistrationHelper instance.
     */
    public void completeCheck(char courseIn, CourseRegistrationHelper crHelperIn) {
        if (courseIn != '\0') {
            if (passPrerequisite(courseIn) && !checkGraduation()) {
                Logger.writeMessage("Sem: " + indexSem + " Course No: " +
                        coursesInCurrentSemester + " Course: " + courseIn, Logger.getDebugLevel(), logFileHandler);
                currentSemesterCourses = setCurrSemCourses(indexSem, courseIn);
                totalCourses++;
                setGroup(courseIn);
                State currState = courseSequencer.getState();
                State changeState = currState.changeState(crHelperIn);

                if (!(currState.getClass().equals(changeState.getClass()))) {
                    countChangeinState++;
                }
                if (coursesInCurrentSemester == 3) {
                    coursesInCurrentSemester = 0;
                    indexSem++;
                }
            }
        }
    }

    /**
     * Assigns a course to the student, processing the waitlist and performing a
     * complete check for each course.
     *
     * @param CourseIn   The course to be assigned.
     * @param crHelperIn The CourseRegistrationHelper instance.
     */
    public void assignCourse(char CourseIn, CourseRegistrationHelper crHelperIn) {
        processWaitlist(crHelperIn);

        completeCheck(CourseIn, crHelperIn);
    }

    /**
     * Processes the waitlist by iterating through the courses and performing a
     * complete check for each course.
     *
     * @param crHelperIn The CourseRegistrationHelper instance.
     */
    public void processWaitlist(CourseRegistrationHelper crHelperIn) {
        int sizeWaitlist = waitlist.size();
        for (int i = 0; i < sizeWaitlist; i++) {
            char waitListCourse = pollWaitlist();
            Logger.writeMessage("Go to waitlist course: " + waitListCourse, Logger.getDebugLevel(), logFileHandler);
            completeCheck(waitListCourse, crHelperIn);
        }
    }
}