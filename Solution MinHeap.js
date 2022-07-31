
var NumberContainers = function () {
    const {PriorityQueue} = require('@datastructures-js/priority-queue');
    this.indexToValue = new Map();//Map <Number, Number>
    this.valueToIndex = new Map();//Map <Number, MinPriorityQueue<Number>>
    this.NOT_FOUND = -1;
};

/** 
 * @param {number} index 
 * @param {number} number
 * @return {void}
 */
NumberContainers.prototype.change = function (index, number) {
    this.indexToValue.set(index, number);
    if (!this.valueToIndex.has(number)) {
        this.valueToIndex.set(number, new MinPriorityQueue({compare: (x, y) => x - y}));
    }
    this.valueToIndex.get(number).enqueue(index);
};

/** 
 * @param {number} number
 * @return {number}
 */
NumberContainers.prototype.find = function (number) {
    if (!this.valueToIndex.has(number)) {
        return this.NOT_FOUND;
    }

    const indexesForCurrentValue = this.valueToIndex.get(number);
    while (!indexesForCurrentValue.isEmpty()) {
        const index = indexesForCurrentValue.front();
        if (this.indexToValue.get(index) === number) {
            return index;
        }
        indexesForCurrentValue.dequeue();
    }
    return this.NOT_FOUND;
};
