package applicationFramework.core;

import applicationFramework.ui.GUI;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.SwingUtilities;
import swing.components.AColor;
import swing.components.AComponent;
import swing.components.ASwopPane;
import swing.toolkit.UIToolkit;

/**
 *
 * @author alexisvincent
 */
public class ApplicationTest extends Application {

    static {
        INSTANCE = new ApplicationTest();
    }

    private ApplicationTest() {
        super();
    }

    public static Application getINSTANCE() {
        return INSTANCE;
    }

    @Override
    protected void initGUI() {
        //Init Gui
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                gui = new GUI("Application Test").setDimensions(new Dimension(900, 700)).setGoodDefaults();
                gui.getFrame().setContentPane(new ApplicationPane());

                //gui.setFullScreen(true);
                gui.show();
                gui.enableDoubleBuffering(true);
                gui.startPainting(30);
            }
        });
    }

    public static void main(String[] args) {
        getINSTANCE();
    }

    private class ApplicationPane extends ASwopPane {

        public ApplicationPane() {
            super();
            setName("ApplicationPane");
            setBackground(AColor.DarkGreen);
            
            setLayout(null);

            AComponent comp = new AComponent() {

                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Graphics2D g2d = UIToolkit.getPrettyGraphics(g);

                    g2d.setPaint(getBackground());
                    g2d.fillRect(0, 0, getWidth(), getHeight());
                }

            };
            
            comp.setBackground(new AColor(AColor.DarkGrey));
            comp.setSize(100, 100);
//            final TimeLine timeline = new TimeLine();
//            timeline.addSegment(new ColorSegment(MotionFactory.getExponential(2), 50, 300, AColor.DarkGrey, AColor.Red, comp.getBackground()));
//            timeline.addSegment(new ColorSegment(MotionFactory.getLinear(), 350, 500, AColor.Red, AColor.DarkBlue, comp.getBackground()));
//            timeline.addSegment(new HorizontalMotionSegment(MotionFactory.getLinear(), 60, 600, 0, 600, comp));
//            timeline.addSegment(new VerticalMotionSegment(MotionFactory.getExponential(0.5), 60, 300, 0, 300, comp));
//            timeline.addSegment(new VerticalMotionSegment(MotionFactory.getExponential(2), 361, 300, 300, 0, comp));
//            
//            processingQueue.addJob(new ProcessingQueue.Job() {
//
//                @Override
//                public boolean doJob() {
//                    timeline.compile();
//                    return true;
//                }
//
//                @Override
//                public boolean mustBeRemoved() {
//                    return true;
//                }
//            });
//            
//            comp.addTimeline(timeline);
            
            this.add(comp);
            
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = UIToolkit.getPrettyGraphics(g);

            g2d.setPaint(getBackground());
            g2d.fillRect(0, 0, getWidth(), getHeight());
        }

    }

}
