package animationEngine;

import java.util.ArrayList;

/**
 *
 * @author alexisvincent
 */
public class SegmentGroup extends Segment {

    private ArrayList<Segment> segments;
    private int currentFrame;

    public enum Position {

        AFTER_LAST_ADDED, WITH_FIRST, WITH_LAST_ADDED, AFTER_LAST_TAG, ON_NEXT_FRAME, NONE
    }

    public SegmentGroup() {
        this(0);
    }

    public SegmentGroup(int frameQue) {
        this(MotionFactory.getLinear(), frameQue);
    }

    public SegmentGroup(Motion motion, int frameQue) {
        super(motion, frameQue, 0, "SegmentGroup");
        init();
    }

    @Override
    public void compile() {
        for (Segment segment : segments) {
            segment.compile();

            if (segment.getFrameQue() + segment.getDuration() > getDuration()) {
                setDuration(segment.getFrameQue() + segment.getDuration());
            }
        }
        setCompiled(true);
    }

    private void init() {
        segments = new ArrayList<>();
        currentFrame = 0;
    }

    @Override
    public ArrayList getCompiledSegment() {
        System.out.println("SegmentGroup.getCompliledSegment()");
        return null;
    }

    @Override
    protected Object getState(double motionCoEF) {
        System.out.println("SegmentGroup.getState()");
        return null;
    }

    public int getCurrentFrame() {
        return currentFrame;
    }

    public void setCurrentFrame(int currentFrame) {
        this.currentFrame = currentFrame;
    }

    public void incrementCurrentFrame() {
        setCurrentFrame(getCurrentFrame() + 1);
    }

    private void addSegment(Segment segment) {
        this.segments.add(segment);
    }

    public void addSegment(Segment segment, Position position, String tag) {
        int segmentFrameQue = 0;

        switch (position) {
            case AFTER_LAST_ADDED:
                if (!segments.isEmpty()) {
                    segmentFrameQue = segments.get(segments.size() - 1).getFrameQue() + segments.get(segments.size() - 1).getDuration();
                }
                break;
            case AFTER_LAST_TAG:
                for (Segment s : segments) {
                    if (s.getTag().equals(segment.getTag()) && s.getFrameQue() + s.getDuration() > segmentFrameQue) {
                        segmentFrameQue = s.getFrameQue() + s.getDuration();
                    }
                }
                break;
            case WITH_FIRST:
                if (!segments.isEmpty()) {
                    segmentFrameQue = segments.get(0).getFrameQue();
                    for (Segment s : segments) {
                        if (s.getFrameQue() < segmentFrameQue) {
                            segmentFrameQue = s.getFrameQue();
                        }
                    }
                }
                break;
            case WITH_LAST_ADDED:
                if (!segments.isEmpty()) {
                    segmentFrameQue = segments.get(segments.size() - 1).getFrameQue();
                }
                break;
            case ON_NEXT_FRAME:
                segmentFrameQue = getCurrentFrame() + 1;
                break;
            case NONE:
                segmentFrameQue = segment.getFrameQue();
                break;
        }

        segment.setFrameQue(segmentFrameQue);
        addSegment(segment);
    }

    public void addSegment(Segment segment, Position position) {
        addSegment(segment, position, "");
    }

    @Override
    public boolean doAnimation(int currentFrame) {
        setCurrentFrame(currentFrame);
        if (getCurrentFrame() <= getDuration()) {
            for (Segment segment : segments) {
                if (segment.isCompiled() && segment.getFrameQue() <= getCurrentFrame() && segment.getFrameQue() + segment.getDuration() >= getCurrentFrame()) {
                    segment.doAnimation(getCurrentFrame() - segment.getFrameQue());
                }
            }
            return true;
        } else {
            //prune();
            return false;
        }
    }

    public void prune() {
        for (int i = 0; i < segments.size(); i++) {
            if (segments.get(i).getFrameQue() + segments.get(i).getDuration() <= getCurrentFrame()) {
                segments.remove(i);
                i--;
            }
        }
    }

    public boolean doAnimation() {
        if (doAnimation(getCurrentFrame())) {
            incrementCurrentFrame();
        }
        return true;
    }

}
