
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class NumberContainers {

    private static Map<Integer, Integer> indexToValue;
    private static Map<Integer, TreeSet<Integer>> valueToIndex;
    private static final int NOT_FOUND = -1;

    public NumberContainers() {
        indexToValue = new HashMap<>();
        valueToIndex = new HashMap<>();
    }

    public void change(int index, int number) {
        if (indexToValue.containsKey(index)) {

            int valueToBeReplaced = indexToValue.get(index);
            valueToIndex.get(valueToBeReplaced).remove(index);

            if (valueToIndex.get(valueToBeReplaced).isEmpty()) {
                valueToIndex.remove(valueToBeReplaced);
            }
        }
        indexToValue.put(index, number);
        valueToIndex.putIfAbsent(number, new TreeSet<>());
        valueToIndex.get(number).add(index);
    }

    public int find(int number) {
        return valueToIndex.containsKey(number) ? valueToIndex.get(number).first() : NOT_FOUND;
    }
}
