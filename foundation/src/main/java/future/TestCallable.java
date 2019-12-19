package future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class TestCallable implements Callable<String> {
    private final static Logger logger = LoggerFactory.getLogger(TestCallable.class);
    private static ExecutorService service= Executors.newCachedThreadPool();
    @Override
    public String call() throws Exception {
        TimeUnit.SECONDS.sleep(1);
        return "1";
    }

    public static void main(String[] args) {
        long start=System.currentTimeMillis();
        List<Future> futureList=new ArrayList();
        for (int i = 0; i <10 ; i++) {
            Future<String> future = service.submit(new TestCallable());
            try {
                //情况3  跟情况2一致
                logger.info(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            futureList.add(future);
//            try {
//                //情况2
//                future.get();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            }
        }
        //情况1
//        for (Future f : futureList) {
//            try {
//                f.get();
//            } catch (Exception e) {
//            }
//        }
        long end=System.currentTimeMillis();
        System.out.println(end-start);
    }
}
