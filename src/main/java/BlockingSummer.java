public class BlockingSummer {

    private int n;
    private long delay;
    public BlockingSummer(int n, long delay) {
        this.n = n;
        this.delay = delay;
    }

    public int computeSum() {
        int i = 0;
        int sum = 0;
        while (i < n) {
            i++;
            sum += i;
            try {
                Thread.sleep(delay);
            } catch(Exception ex) {

            }
        }
        return sum;
    }
}
