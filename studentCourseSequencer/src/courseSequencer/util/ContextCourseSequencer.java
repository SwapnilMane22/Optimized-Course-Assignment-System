package courseSequencer.util;

import courseSequencer.state.State;
import courseSequencer.state.StateOne;

public class ContextCourseSequencer {
    private State currentState;

    public ContextCourseSequencer() {
        try {
            currentState = new StateOne();
        } catch (Exception e) {
            System.err.println("Error: " + e);
            e.printStackTrace();
        }
    }

    public ContextCourseSequencer(State sIn) {
        try {
            if (sIn != null) {
                currentState = sIn;
            }
        } catch (Exception e) {
            System.err.println("Error: " + e);
            e.printStackTrace();
        }
    }

    public void processCourses(char Course, CourseRegistrationHelper crHelper) {
        if (!crHelper.checkGraduation()) {
            currentState.processCourse(Course, crHelper);
        }
    }

    public void setState(State newState) {
        try {
            if (newState != null) {
                currentState = newState;
            }
        } catch (Exception e) {
            System.err.println("Error: " + e);
            e.printStackTrace();
        }
    }

    public State getState() {
        return currentState;
    }
}
