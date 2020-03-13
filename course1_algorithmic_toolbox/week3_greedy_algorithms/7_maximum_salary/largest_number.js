const readline = require('readline');
const rl = readline.createInterface({
	input: process.stdin,
	terminal: false
});

process.stdin.setEncoding('utf8');

const isGreaterOrEqual = (x, y) => {
	return parseInt(`${x}${y}`, 10) >= parseInt(`${y}${x}`, 10);
};

const swap = (a, i, j) => {
	const tmp = a[i];
	a[i] = a[j];
	a[j] = tmp;
};

const largestNumber = (a) => {
	for (let i = 0; i < a.length - 1; i++) {
		for (let j = i + 1; j < a.length; j++) {
			if (isGreaterOrEqual(a[j], a[i])) {
				swap(a, i, j);
			}
		}
	}

	return a.join('');
};

/**
 * Enter:
 *
 * 2
 * 21 2
 *
 */

rl.once('line', line => {
	rl.once('line', line => {
		const nums = line.toString().split(' ').map(Number);
		console.log(largestNumber(nums));
		process.exit();
	});

});


module.exports = largestNumber;
