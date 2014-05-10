package animationEngine;

import java.util.ArrayList;

/**
 *
 * @author alexisvincent
 */
public abstract class Segment {

    private Motion motion;
    private int frameQue;
    private int duration;

    protected ArrayList<StateMap> compiledSegment;
    private boolean compiled;
    
    private String tag;

    protected Segment(Motion motion, int frameQue, int duration, String tag) {
        this.motion = motion;
        this.frameQue = frameQue;
        this.duration = duration;
        this.tag = tag;

        init();
    }

    private void init() {
        this.compiledSegment = new ArrayList<>();
    }

    public Motion getMotion() {
        return motion;
    }

    public void setMotion(Motion motion) {
        this.motion = motion;
        setCompiled(false);
    }

    public int getFrameQue() {
        return frameQue;
    }

    public void setFrameQue(int frameQue) {
        this.frameQue = frameQue;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
        setCompiled(false);
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public ArrayList getCompiledSegment() {
        return compiledSegment;
    }

    public boolean isCompiled() {
        return compiled;
    }

    protected void setCompiled(boolean compiled) {
        this.compiled = compiled;
    }

    public void compile() {
        if (!isCompiled()) {
            getCompiledSegment().clear();
            for (int i = 0; i <= getDuration(); i++) {
                getCompiledSegment().add(new StateMap(i, getState(getMotion().getMotionCoefficient(i, getDuration()))));
            }
            setCompiled(true);
        }
    }

    protected abstract Object getState(double motionCoEF);

    public abstract boolean doAnimation(int currentFrame);

    public class StateMap {

        private int frame;
        private Object state;

        public StateMap(int frame, Object state) {
            this.frame = frame;
            this.state = state;
        }

        public int getFrame() {
            return frame;
        }

        public void setFrame(int frame) {
            this.frame = frame;
        }

        public Object getState() {
            return state;
        }

        public void setState(Object state) {
            this.state = state;
        }
    }
}
