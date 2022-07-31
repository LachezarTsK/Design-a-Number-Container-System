
#include <set>
#include <unordered_map>
using namespace std;

class NumberContainers {
    
    unordered_map<int, int> indexToValue;
    unordered_map<int, set<int>> valueToIndex;
    inline static const int NOT_FOUND = -1;

public:
    NumberContainers() = default;

    void change(int index, int number) {

        //C++20: indexToValue.contains(index). Done the old-fashioned way for compatibility.
        if (indexToValue.find(index) != indexToValue.end()) {

            int valueToBeReplaced = indexToValue[index];
            valueToIndex[valueToBeReplaced].erase(index);

            if (valueToIndex[valueToBeReplaced].empty()) {
                valueToIndex.erase(valueToBeReplaced);
            }
        }
        indexToValue[index] = number;
        valueToIndex[number].insert(index);
    }

    int find(int number) {
        return valueToIndex.find(number) != valueToIndex.end() ? *valueToIndex[number].begin() : NOT_FOUND;
    }
};
