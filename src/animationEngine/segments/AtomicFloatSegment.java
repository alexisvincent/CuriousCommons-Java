package animationEngine.segments;

import animationEngine.Motion;
import animationEngine.Segment;
import dataTypes.AtomicFloat;

/**
 *
 * @author alexisvincent
 */
public class AtomicFloatSegment extends Segment {

        private final float initialValue;
        private final AtomicFloat object;

        private final float delta;

        public AtomicFloatSegment(Motion motion, int frameQue, int totalFrames, float initialValue, float finalValue, AtomicFloat atomicFloat) {
            super(motion, frameQue, totalFrames);

            this.initialValue = initialValue;
            this.delta = finalValue - initialValue;

            this.object = atomicFloat;
        }

        @Override
        protected Float getState(double motionCoEF) {
            return initialValue + (float) (motionCoEF * delta);
        }

        @Override
        public boolean doAnimation(int currentFrame) {
            if (isCompiled()) {
                
                for(Segment.StateMap stateMap : compiledSegment) {
                    if (stateMap.getFrame() == currentFrame) {
                        object.set((float)stateMap.getState());
                    }
                }
                return true;
            }
            return false;
        }
    }