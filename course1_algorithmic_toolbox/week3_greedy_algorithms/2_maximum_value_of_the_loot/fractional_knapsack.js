const readline = require('readline');
const rl = readline.createInterface({
	input: process.stdin,
	terminal: false
});

process.stdin.setEncoding('utf8');

const readLine = (line) => {
	const v = parseInt(line.toString().split(' ')[0], 10);
	const w = parseInt(line.toString().split(' ')[1], 10);

	return [v, w];
};

/**
 * Sort both given values and weights arrays at the same time, the most valuable item is the first
 *
 * @param values
 * @param weights
 */
const sortValuableDesc = (values, weights) => {
	if (values == null || values.length <= 1) {
		return;
	}

	for (let i = 0; i < values.length - 1; i++) {
		for (let j = i + 1; j < values.length; j++) {
			if (values[j] / weights[j] > values[i] / weights[i]) {
				const valueTmp = values[i];
				const weightTmp = weights[i];
				values[i] = values[j];
				weights[i] = weights[j];
				values[j] = valueTmp;
				weights[j] = weightTmp;
			}
		}
	}
};

const max = (count, capacity, values, weights) => {
	let value = 0;
	sortValuableDesc(values, weights);

	for (let i = 0; i < values.length; i++) {
		let maxWeightCanTake = Math.min(weights[i], capacity);
		value += (maxWeightCanTake * (values[i] / weights[i]));

		capacity -= maxWeightCanTake;

		if (capacity === 0) {
			break;
		}
	}

	return value;
};

rl.once('line', line => {
	const [itemsCount, knapsackCapacity] = line.toString().split(' ').map(Number);
	const values = [];
	const weights = [];
	let count = 0;

	rl.on('line', line => {
		const [v, w] = readLine(line);
		values.push(v);
		weights.push(w);

		if (++count >= itemsCount) {
			console.log(max(itemsCount, knapsackCapacity, values, weights));
			process.exit();
		}
	});
});

module.exports = max;
