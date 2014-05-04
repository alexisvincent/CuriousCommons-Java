package swing.components;

import java.awt.BorderLayout;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import javax.swing.JFrame;
import javax.swing.JRootPane;
import javax.swing.WindowConstants;
import swing.components.decoration.AFooter;
import swing.components.decoration.AHeader;
import swing.components.decoration.ALeftSidebar;
import swing.components.decoration.ARightSidebar;

/**
 *
 * @author alexisvincent
 */
public class AFrame extends JFrame {

    private DecorationPane decorationPane;

    public AFrame() {
        this("Default");
    }

    public AFrame(String title) {
        super(title);
        init();
    }

    private void init() {

        this.setLayeredPane(new ALayeredPane());

        //Remove rootpane and wrap it in the decorationPane... Then attach the decorationPane
        //to the frame.
        this.decorationPane = new DecorationPane(getRootPane());
        this.setRootPaneCheckingEnabled(false);
        this.add(decorationPane);
        this.setRootPaneCheckingEnabled(true);
    }

    public void setHeader(AComponent header) {
        getDecorationPane().setHeader(header);
    }
    
    public void setFooter(AComponent footer) {
        getDecorationPane().setFooter(footer);
    }
    
    public void setLeftSidebar(AComponent leftSidebar) {
        getDecorationPane().setLeftSidebar(leftSidebar);
    }
    
    public void setRightSidebar(AComponent rightSidebar) {
        getDecorationPane().setRightSidebar(rightSidebar);
    }
    
    public AComponent getHeader() {
        return getDecorationPane().getHeader();
    }
    
    public AComponent getFooter() {
        return getDecorationPane().getFooter();
    }
    
    public AComponent getLeftSidebar() {
        return getDecorationPane().getLeftSidebar();
    }
    
    public AComponent getRightSidebar() {
        return getDecorationPane().getRightSidebar();
    }
    
    public DecorationPane getDecorationPane() {
        return decorationPane;
    }

    public void setDecorationPane(DecorationPane decorationPane) {
        this.decorationPane = decorationPane;
    }

    public AFrame setGoodDefaults() {
        this.setUndecorated(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(AFrame.EXIT_ON_CLOSE);
        this.setBackground(AColor.DarkGrey);
        return this;
    }

    public void close() {
        switch (getDefaultCloseOperation()) {
            case WindowConstants.DO_NOTHING_ON_CLOSE:
                break;
            case WindowConstants.DISPOSE_ON_CLOSE:
                this.setVisible(false);
                this.dispose();
                break;
            case WindowConstants.EXIT_ON_CLOSE:
                this.setVisible(false);
                this.dispose();
                System.exit(0);
                break;
            case WindowConstants.HIDE_ON_CLOSE:
                this.setVisible(false);
                break;
        }
    }

    public void maximise() {

        GraphicsDevice graphicsDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

        if (graphicsDevice != null) {
            graphicsDevice.setFullScreenWindow(null);
        }

        if (this.getExtendedState() == AFrame.MAXIMIZED_BOTH) {
            this.setExtendedState(AFrame.NORMAL);
        } else {
            this.setExtendedState(AFrame.MAXIMIZED_BOTH);
        }

    }

    public void minimise() {
        GraphicsDevice graphicsDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

        if (graphicsDevice != null) {
            graphicsDevice.setFullScreenWindow(null);
        }

        this.setExtendedState(AFrame.ICONIFIED);
    }

    public class DecorationPane extends AComponent {

        private JRootPane rootPane;
        private AComponent header;
        private AComponent footer;
        private AComponent leftSidebar;
        private AComponent rightSidebar;

        public DecorationPane(JRootPane rootPane) {
            init(rootPane);
        }

        public DecorationPane() {
            this(createRootPane());
        }

        private void init(JRootPane rootPane) {
            this.setLayout(new BorderLayout());

            setRootPane(rootPane);
            
            setHeader(new AHeader(AFrame.this));
            setFooter(new AFooter(AFrame.this));
            setRightSidebar(new ARightSidebar(AFrame.this));
            setLeftSidebar(new ALeftSidebar(AFrame.this));
        }

        @Override
        public JRootPane getRootPane() {
            return this.rootPane;
        }

        public void setRootPane(JRootPane rootPane) {
            this.rootPane = rootPane;
            this.add(rootPane, BorderLayout.CENTER);
        }

        public AComponent getHeader() {
            return header;
        }

        public void setHeader(AComponent header) {
            this.header = header;
            this.add(header, BorderLayout.NORTH);
        }

        public AComponent getFooter() {
            return footer;
        }

        public void setFooter(AComponent footer) {
            this.footer = footer;
            this.add(footer, BorderLayout.SOUTH);
        }

        public AComponent getLeftSidebar() {
            return leftSidebar;
        }

        public void setLeftSidebar(AComponent leftSidebar) {
            this.leftSidebar = leftSidebar;
            this.add(leftSidebar, BorderLayout.WEST);
        }

        public AComponent getRightSidebar() {
            return rightSidebar;
        }

        public void setRightSidebar(AComponent rightSidebar) {
            this.rightSidebar = rightSidebar;
            this.add(rightSidebar, BorderLayout.EAST);
        }

    }
}
