package ud5;

import java.sql.ClientInfoStatus;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.concurrent.*;

public class MainScheduling {
    public static void main(String[] args) {
        /*
        //ScheduledExecutorService executor = Executors.newScheduledThreadPool();
        //ZonedDateTime zone = ZonedDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofLocalizedDateTime(
                FormatStyle.SHORT, FormatStyle.LONG
        );

        Callable<ZonedDateTime> task = () ->{
            TimeUnit.SECONDS.sleep(2);
            return ZonedDateTime.now();
        };

        //quiero ejecutar una tarea concurrentemente 4 veces a la vez
        ExecutorService executor = Executors.newFixedThreadPool(1);
        List<Callable<ZonedDateTime>> tasklist = List.of(
                task,task,task,task
        );
        try {
            List<Future<ZonedDateTime>> results =
                    executor.invokeAll(tasklist);
            for (Future<ZonedDateTime> r: results) {
                System.out.println(r.get().format(dtf));
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } finally {
            executor.shutdown();
        }

         */
        //ScheduledExecutorService executor = Executors.newScheduledThreadPool();
        //ZonedDateTime zone = ZonedDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofLocalizedDateTime(
                FormatStyle.SHORT, FormatStyle.LONG
        );

        Callable<ZonedDateTime> task = () ->{
            TimeUnit.SECONDS.sleep(2);
            return ZonedDateTime.now();
        };

        //quiero ejecutar una tarea concurrentemente 4 veces a la vez
       ExecutorService executor = Executors.newFixedThreadPool(1);
      //   List<Callable<ZonedDateTime>> tasklist = List.of(
          //      task,task,task,task
   //     );

        ScheduledExecutorService scheduled = Executors
                .newSingleThreadScheduledExecutor();
        ScheduledFuture<ZonedDateTime> scjt = scheduled.schedule(
                task,
                2,
                TimeUnit.SECONDS
        );
        try {

                System.out.println(scjt.get().format(dtf));

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } finally {
            executor.shutdown();
        }
    }
}
