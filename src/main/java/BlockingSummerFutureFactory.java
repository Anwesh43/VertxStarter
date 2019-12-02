import io.vertx.core.Future;

public class BlockingSummerFutureFactory {

    public static Future<Integer> createFuture(int n, long delay) {
        return Future.future(promise -> {
           BlockingSummer blockingSummer = new BlockingSummer(n, delay);
           promise.complete(blockingSummer.computeSum());
        });
    }
}
