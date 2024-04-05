package MultithreadingStateMutation.AtomicExperiment;

public class Consumer {
    private final DonutStorage donutStorage;
    public Consumer(DonutStorage donutStorage) {this.donutStorage = donutStorage;}

    /**
     * Subtracts the given number from the DonutStorage's donutsNumber.
     * @param numberOfItemsToConsume Number that will be subtracted from the donutsNumber
     */
    public int consume(int numberOfItemsToConsume) {
        //donutStorage.setDonutsNumber(donutStorage.getDonutsNumber() - numberOfItemsToConsume);
        synchronized (donutStorage) {
            int donutsNumber = donutStorage.getDonutsNumber();
            // if there aren't enough donuts in stock, consume as many as there are
            if (numberOfItemsToConsume > donutsNumber) {
                donutStorage.setDonutsNumber(0);
                return donutsNumber;
            }
            donutStorage.setDonutsNumber(donutsNumber - numberOfItemsToConsume);
            return numberOfItemsToConsume;
        }
    }
}
