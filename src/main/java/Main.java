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
            vertx.close();
        });
        vertx.setPeriodic(1000, (res) -> {
            System.out.println("waiting for summation " + new Date().toString());
        });
    }
}
