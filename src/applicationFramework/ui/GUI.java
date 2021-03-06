package applicationFramework.ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import javax.swing.Timer;
import swing.components.AFrame;

/**
 *
 * @author alexisvincent
 */
public class GUI {

    private AFrame frame;

    private GraphicsDevice graphicsDevice;
    
    private RepaintQue repaintQue;

    private boolean doubleBuffering;
    private BufferStrategy bufferStratagy;

    private Timer repaintTimer;

    public GUI(String applicationName) {
        init(applicationName);
    }

    private void init(String applicationName) {
        this.frame = new AFrame(applicationName).setGoodDefaults();
        graphicsDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        repaintQue = new RepaintQue();
    }

    public AFrame getFrame() {
        return frame;
    }

    public GUI show() {
        this.getFrame().setVisible(true);
        return this;
    }

    public GUI hide() {
        this.getFrame().setVisible(false);
        return this;
    }

    public GUI setDimensions(Dimension dimensions) {
        this.getFrame().setSize(dimensions);
        return this;
    }

    public GUI setGoodDefaults() {
        this.getFrame().setGoodDefaults();
        return this;
    }

    public boolean startPainting(int frameRate) {
        if (repaintTimer == null) {
            repaintTimer = new Timer(1000 / frameRate, new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    repaint();
                }
            });
            repaintTimer.start();
            this.getFrame().setIgnoreRepaint(true);
            return true;
        }
        return false;
    }

    public boolean stopPainting() {
        if (repaintTimer != null && repaintTimer.isRunning()) {
            repaintTimer.stop();
            repaintTimer = null;
            this.getFrame().setIgnoreRepaint(false);
            return true;
        }
        return false;
    }

    public boolean enableDoubleBuffering(boolean doubleBuffering) {
        if (doubleBuffering && this.getFrame().isVisible()) {
            this.getFrame().createBufferStrategy(2);
            this.getFrame().getDecorationPane().setDoubleBuffered(true);
            this.bufferStratagy = this.getFrame().getBufferStrategy();
            this.doubleBuffering = true;
            return true;
        } else if (!doubleBuffering) {
            this.doubleBuffering = false;
        }
        return false;
    }

    public boolean setFullScreen(boolean fullScreen) {
        int refreshRate = 0;
        boolean successfull = false;

        if (this.repaintTimer != null && this.repaintTimer.isRunning()) {
            refreshRate = 1000 / this.repaintTimer.getDelay();
            this.stopPainting();
        }

        if (graphicsDevice.isFullScreenSupported() && fullScreen) {
            graphicsDevice.setFullScreenWindow(frame);
            successfull = true;
        } else if (!fullScreen) {
            graphicsDevice.setFullScreenWindow(null);
            successfull = true;
        }

        if (refreshRate > 0) {
            this.startPainting(refreshRate);
        }

        return successfull;
    }
    
//    public void repaint(int amount) {
//        repaintQue.repaint(amount);
//    }

    public void repaint() {
//        if (doubleBuffering) {
//            Graphics graphics = bufferStratagy.getDrawGraphics();
//            this.getFrame().getDecorationPane().paint(graphics);
//            graphics.dispose();
//            bufferStratagy.show();
//        } else {
            this.getFrame().repaint(10);
//        }
    }

    private class RepaintQue implements Runnable {

        private final LinkedBlockingQueue<Integer> repaintQue;

        private final Thread thread;
        private boolean running;

        public RepaintQue() {
            repaintQue = new LinkedBlockingQueue<>();
            running = false;
            thread = new Thread(this);
        }

        public void repaint(int amount) {
            repaintQue.add(amount);
        }

        public void start() {
            running = true;
            thread.start();
        }

        public void stop() {
            running = false;
            thread.interrupt();
        }

        @Override
        public void run() {
            while (running) {
                try {
                    int repaint = repaintQue.poll(5, TimeUnit.MINUTES);
                    
                    for (int i = 0; i < repaint; i++) {
                        //GUI.this.paint();
                        Thread.sleep(30);
                    }
                    
                } catch (InterruptedException ex) {
                    System.out.println("Processing Que Interupted");
                }
            }
        }
    }

}
