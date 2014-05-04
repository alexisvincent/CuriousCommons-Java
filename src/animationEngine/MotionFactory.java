package animationEngine;


/**
 *
 * @author alexisvincent
 */
public class MotionFactory {

    public static Motion getLinear() {
        return new Motion() {

            @Override
            protected double doEquasion(int x) {
                return x;
            }
        };
    }
    
    public static Motion getLOL() {
        return new Motion() {

            @Override
            protected double doEquasion(int x) {
                return Math.sin(Math.toRadians(x));
            }
        };
    }
    
    public static Motion getExponential(final double power) {
        return new Motion() {

            @Override
            protected double doEquasion(int x) {
                return Math.pow(x, power);
            }
        };
    }
}
