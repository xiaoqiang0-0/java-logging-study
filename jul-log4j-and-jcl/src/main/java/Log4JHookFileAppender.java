import org.apache.log4j.FileAppender;
import org.apache.log4j.Layout;

import java.io.IOException;

public class Log4JHookFileAppender extends FileAppender {
    public Log4JHookFileAppender(){
        super();
        Runtime.getRuntime().addShutdownHook(new Log4jHockThread());
    }
    public Log4JHookFileAppender(Layout layout, String filename) throws IOException {
        super(layout,filename);
        Runtime.getRuntime().addShutdownHook(new Log4jHockThread());
    }
    public Log4JHookFileAppender(Layout layout, String filename, boolean append) throws IOException {
        super(layout,filename,append);
        Runtime.getRuntime().addShutdownHook(new Log4jHockThread());
    }
    public Log4JHookFileAppender(Layout layout, String filename, boolean append, boolean bufferedIO,
                                 int bufferSize) throws IOException {
        super(layout,filename,append,bufferedIO,bufferSize);
        Runtime.getRuntime().addShutdownHook(new Log4jHockThread());
    }

    class Log4jHockThread extends Thread{
        @Override
        public void run() {
            if(qw != null){
                qw.flush();
            }
        }
    }
}
