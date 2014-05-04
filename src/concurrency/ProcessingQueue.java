package concurrency;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author alexisvincent
 */
public class ProcessingQueue implements Runnable {

    private final LinkedBlockingQueue<Job> que;

    private final Thread thread;
    private boolean running;

    public ProcessingQueue() {
        que = new LinkedBlockingQueue<>();
        running = false;
        thread = new Thread(this);
    }

    public void addJob(Job j) {
        que.add(j);
    }

    public Job peek() {
        return que.peek();
    }
    
    private Job poll() {
        return que.poll();
    }
    
    private Job poll(long unit, TimeUnit timeUnit) throws InterruptedException {
        return que.poll(unit, timeUnit);
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
                Job job = poll(5, TimeUnit.MINUTES);
                if (job!=null) {
                    job.doJob();
                }
            } catch (InterruptedException ex) {
                System.out.println("Processing Que Interupted");
            }
        }
    }
    
    public interface Job {
        public boolean doJob() ;
        public boolean mustBeRemoved();
    }
    
    
}
