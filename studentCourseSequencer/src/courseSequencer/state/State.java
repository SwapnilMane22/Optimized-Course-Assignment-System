package courseSequencer.state;

import courseSequencer.util.CourseRegistrationHelper;

public interface State {

    public void processCourse(char CourseIn, CourseRegistrationHelper crHelper);

    public State changeState(CourseRegistrationHelper crHelper);

}
