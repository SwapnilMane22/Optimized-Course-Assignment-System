package courseSequencer.state;

import courseSequencer.util.CourseRegistrationHelper;
import courseSequencer.util.Logger;

public class StateThree implements State {

    @Override
    public State changeState(CourseRegistrationHelper crHelper) {
        return crHelper.countGroupState();
    }

    @Override
    public void processCourse(char CourseIn, CourseRegistrationHelper crHelper) {
        Logger.writeMessage("Process S3 " + CourseIn + " ", Logger.getDebugLevel(), crHelper.getLogFile());
        crHelper.assignCourse(CourseIn, crHelper);
    }

}