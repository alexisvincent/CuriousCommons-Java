package animationEngine;

/**
 *
 * @author alexisvincent
 */
public abstract class Motion {

    public double getMotionCoefficient(int currentFrame, int totalFrames) {
        return doEquasion(currentFrame) / doEquasion(totalFrames);
    }

    protected abstract double doEquasion(int x);
}
