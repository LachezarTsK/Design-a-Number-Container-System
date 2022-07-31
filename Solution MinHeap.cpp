
#include <queue>
#include <unordered_map>
using namespace std;

class NumberContainers {
    
    unordered_map<int, int> indexToValue;
    unordered_map<int, priority_queue<int, vector<int>, greater<int>>> valueToIndex;
    inline static const int NOT_FOUND = -1;

public:
    NumberContainers() = default;

    void change(int index, int number) {
        indexToValue[index] = number;
        valueToIndex[number].push(index);
    }

    int find(int number) {

        //C++20: valueToIndex.contains(number). Done the old-fashioned way for compatibility.
        if (valueToIndex.find(number) == valueToIndex.end()) {
            return NOT_FOUND;
        }

        priority_queue<int, vector<int>, greater<int>>& indexesForCurrentValue = valueToIndex[number];
        while (!indexesForCurrentValue.empty()) {
            int index = indexesForCurrentValue.top();
            if (indexToValue[index] == number) {
                return index;
            }
            indexesForCurrentValue.pop();
        }
        return NOT_FOUND;
    }
};
