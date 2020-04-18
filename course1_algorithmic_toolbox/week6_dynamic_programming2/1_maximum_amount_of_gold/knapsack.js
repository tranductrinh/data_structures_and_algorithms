const readline = require('readline');
const rl = readline.createInterface({
	input: process.stdin,
	terminal: false
});

process.stdin.setEncoding('utf8');

const optimalWeight = (W, weights) => {
	const table = new Array(weights.length + 1);
	for (let i = 0; i <= weights.length; i++) {
		table[i] = new Array(W + 1).fill(0);
	}

	for (let i = 1; i <= weights.length; i++) {
		for (let w = 1; w <= W; w++) {
			table[i][w] = table[i - 1][w];

			if (weights[i - 1] <= w) {
				let value = table[i - 1][w - weights[i - 1]] + weights[i - 1];
				if (table[i][w] < value) {
					table[i][w] = value;
				}
			}
		}
	}

	return table[weights.length][W];

};

rl.once('line', (line) => {
	let nums = line.toString().split(' ').map(Number);
	let W = nums[0], n = nums[1];
	rl.once('line', (line) => {
		let w = line.toString().split(' ').map(Number);
		process.stdout.write(optimalWeight(W, w) + ' ');
		process.exit();
	})
});

module.exports = optimalWeight;
