package animationEngine.segments;

import animationEngine.Motion;
import animationEngine.Segment;
import swing.components.AComponent;

/**
 *
 * @author alexisvincent
 */
public abstract class MotionSegment extends Segment {

        private final int initialPosition;
        protected final AComponent component;

        private int deltaX = 0;

        public MotionSegment(Motion motion, int frameQue, int totalFrames, int initialPosition, int finalPosition, AComponent component) {
            super(motion, frameQue, totalFrames);

            this.initialPosition = initialPosition;
            this.deltaX = finalPosition - initialPosition;
            this.component = component;
        }

        @Override
        protected Integer getState(double motionCoEF) {
            return initialPosition + (int) (motionCoEF * deltaX);
        }

        @Override
        public boolean doAnimation(int currentFrame) {
            if (isCompiled()) {
                
                for(Segment.StateMap stateMap : compiledSegment) {
                    if (stateMap.getFrame() == currentFrame) {
                        updatePosition(stateMap);
                    }
                }
                return true;
            }
            return false;
        }
        
        protected abstract void updatePosition(Segment.StateMap stateMap);
    }
