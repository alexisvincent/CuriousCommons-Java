package applicationFramework.core;

import applicationFramework.ui.GUI;
import concurrency.ProcessingQueue;

/**
 *
 * @author alexisvincent
 */
public abstract class Application {

    protected GUI gui;
    protected ProcessingQueue processingQueue;
    protected static Application INSTANCE;
    
    protected Application() {
        processingQueue = new ProcessingQueue();
        processingQueue.start();
        initGUI();
    }
    
    protected abstract void initGUI();
    
    public void quit() {
        getUI().hide();
        System.exit(0);
    }

    public ProcessingQueue getProcessingQueue() {
        return this.processingQueue;
    }

    public GUI getUI() {
        return gui;
    }

}
