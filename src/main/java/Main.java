import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;
import io.vertx.core.Vertx;

import java.util.Date;

public class Main {

    public static void main(String args[]) {
        Vertx vertx = Vertx.vertx();
        vertx.executeBlocking(promise -> {
            BlockingSummer summer = new BlockingSummer(10, 1000);
            int sum = summer.computeSum();
            promise.complete(sum);
           }, res -> {
            System.out.println("the result is below");
            System.out.println(res.result());
            System.out.println("closing vertx");
        });
        vertx.setPeriodic(1000, (res) -> {
            System.out.println("waiting for summation " + new Date().toString());
        });
        Future<Integer> f1 = BlockingSummerFutureFactory.createFuture(10, 1000);
        Future<Integer> f2 = BlockingSummerFutureFactory.createFuture(20, 1000);
        Future<Integer> f3 = BlockingSummerFutureFactory.createFuture(15, 1000);
        CompositeFuture.all(f1, f2, f3).setHandler(res -> {
            if (res.succeeded()) {
                System.out.println("future is completed");
            } else {
                System.out.println("future failed");
            }
            vertx.close();
        });
    }
}
