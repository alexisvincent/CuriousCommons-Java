package animationEngine.segments;

import animationEngine.Motion;
import swing.components.AComponent;

/**
 *
 * @author alexisvincent
 */
public class HorizontalMotionSegment extends MotionSegment{

    public HorizontalMotionSegment(Motion motion, int frameQue, int totalFrames, int initialPosition, int finalPosition, AComponent component) {
        super(motion, frameQue, totalFrames, initialPosition, finalPosition, component);
    }

    @Override
    protected void updatePosition(StateMap stateMap) {
        component.setLocation((int)stateMap.getState(), component.getLocation().y);
    }
}
