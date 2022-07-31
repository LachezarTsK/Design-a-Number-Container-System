
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class NumberContainers {

    private final Map<Integer, Integer> indexToValue;
    private final Map<Integer, PriorityQueue<Integer>> valueToIndex;
    private static final int NOT_FOUND = -1;

    public NumberContainers() {
        indexToValue = new HashMap<>();
        valueToIndex = new HashMap<>();
    }

    public void change(int index, int number) {
        indexToValue.put(index, number);
        valueToIndex.putIfAbsent(number, new PriorityQueue<>());
        valueToIndex.get(number).add(index);
    }

    public int find(int number) {
        if (!valueToIndex.containsKey(number)) {
            return NOT_FOUND;
        }

        PriorityQueue<Integer> indexesForCurrentValue = valueToIndex.get(number);
        while (!indexesForCurrentValue.isEmpty()) {
            int index = indexesForCurrentValue.peek();
            if (indexToValue.get(index) == number) {
                return index;
            }
            indexesForCurrentValue.poll();
        }
        return NOT_FOUND;
    }
}
