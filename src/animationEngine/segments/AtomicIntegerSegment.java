package animationEngine.segments;

import animationEngine.Motion;
import animationEngine.Segment;
import java.util.concurrent.atomic.AtomicInteger;
import swing.components.AColor;

/**
 *
 * @author alexisvincent
 */
public class AtomicIntegerSegment extends Segment {

        private final int initialValue;
        private final AtomicInteger object;

        private final int delta;

        public AtomicIntegerSegment(Motion motion, int frameQue, int totalFrames, int initialValue, int finalValue, AtomicInteger integer) {
            super(motion, frameQue, totalFrames, "AtomicIntegerSegment");

            this.initialValue = initialValue;
            this.delta = finalValue - initialValue;

            this.object = integer;
        }

        @Override
        protected Integer getState(double motionCoEF) {
            return initialValue + (int) (motionCoEF * delta);
        }

        @Override
        public boolean doAnimation(int currentFrame) {
            if (isCompiled()) {
                
                for(Segment.StateMap stateMap : compiledSegment) {
                    if (stateMap.getFrame() == currentFrame) {
                        object.set((int)stateMap.getState());
                    }
                }
                return true;
            }
            return false;
        }
    }