package animationEngine;

import java.util.ArrayList;

/**
 *
 * @author alexisvincent
 */
public class TimeLine {

    private final ArrayList<Segment> segmentTimeLine;
    private boolean compiled;
    private boolean complete;
    
    private int currentFrame = 0;
    private int totalFrames = 0;

    public TimeLine() {
        this.segmentTimeLine = new ArrayList<>();
    }

    public void addSegment(Segment segment) {
        segmentTimeLine.add(segment);
        setCompiled(false);
    }
    
    public void addSubsequentSegment(Segment segment) {
        
        segmentTimeLine.add(segment);
        setCompiled(false);
    }

    public boolean doAnimation() {

        if (isCompiled() && currentFrame <= totalFrames) {
            
            for (Segment segment : segmentTimeLine) {
                    segment.doAnimation(currentFrame - segment.getFrameQue());
            }
            currentFrame++;
            return true;
        } else if (currentFrame >= totalFrames) {
            setComplete(true);
        }
        return false;
    }

    public void clear() {
        segmentTimeLine.clear();
        setCompiled(false);
    }

    @SuppressWarnings("empty-statement")
    public void removeSegment(Segment segment) {
        while (segmentTimeLine.remove(segment));
        setCompiled(false);
    }

    public void compile() {

        for (Segment segment : segmentTimeLine) {
            segment.compile();

            if (segment.getFrameQue() + segment.getTotalFrames() > getTotalFrames()) {
                setTotalFrames(segment.getFrameQue() + segment.getTotalFrames());
            }
        }
        setCompiled(true);

    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public int getTotalFrames() {
        return totalFrames;
    }

    public int getCurrentFrame() {
        return currentFrame;
    }

    public void setTotalFrames(int totalFrames) {
        this.totalFrames = totalFrames;
    }

    public boolean isCompiled() {
        return compiled;
    }

    private void setCompiled(boolean compiled) {
        this.compiled = compiled;
    }
}
