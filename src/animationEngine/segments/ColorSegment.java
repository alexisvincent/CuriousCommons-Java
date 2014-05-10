package animationEngine.segments;

import animationEngine.Motion;
import animationEngine.Segment;
import swing.components.AColor;

/**
 *
 * @author alexisvincent
 */
public class ColorSegment extends Segment {

        private final AColor initialColor;
        private final AColor finalColor;
        private final AColor color;

        private final int deltaRed;
        private final int deltaGreen;
        private final int deltaBlue;
        private final int deltaAlpha;

        public ColorSegment(Motion motion, int frameQue, int totalFrames, AColor initialColor, AColor finalColor, AColor color) {
            super(motion, frameQue, totalFrames, "ColorSegment");

            this.initialColor = initialColor;
            this.finalColor = finalColor;

            this.deltaRed = finalColor.getRed() - initialColor.getRed();
            this.deltaGreen = finalColor.getGreen() - initialColor.getGreen();
            this.deltaBlue = finalColor.getBlue() - initialColor.getBlue();
            this.deltaAlpha = finalColor.getAlpha() - initialColor.getAlpha();
            
            this.color = color;

        }

        @Override
        protected AColor getState(double motionCoEF) {
            return new AColor(initialColor.getRed() + (int) (motionCoEF * deltaRed),
                    initialColor.getGreen() + (int) (motionCoEF * deltaGreen),
                    initialColor.getBlue() + (int) (motionCoEF * deltaBlue),
                    initialColor.getAlpha() + (int) (motionCoEF * deltaAlpha)
            );
        }

        @Override
        public boolean doAnimation(int currentFrame) {
            if (isCompiled()) {
                
                for(Segment.StateMap stateMap : compiledSegment) {
                    if (stateMap.getFrame() == currentFrame) {
                        color.setColor((AColor)stateMap.getState());
                    }
                }
                return true;
            }
            return false;
        }
    }