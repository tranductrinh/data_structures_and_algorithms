const readline = require('readline');
const rl = readline.createInterface({
	input: process.stdin,
	terminal: false
});
process.stdin.setEncoding('utf8');

const merge = (numbers, resultSortedNumbers, left, middle, right) => {
	let numberOfInversions = 0;

	let i = left;

	let iFirstHalve = left;
	let iSecondHalve = middle;

	while (iFirstHalve < middle || iSecondHalve < right) {
		const b = iFirstHalve === middle ? 0 : numbers[iFirstHalve];
		const c = iSecondHalve === right ? 0 : numbers[iSecondHalve];

		if (b !== 0) {
			if (c !== 0 && b > c) {
				numberOfInversions += (middle - iFirstHalve);
				resultSortedNumbers[i++] = c;
				iSecondHalve++;
			} else {
				resultSortedNumbers[i++] = b;
				iFirstHalve++;
			}
		} else {
			resultSortedNumbers[i++] = c;
			iSecondHalve++;
		}

	}

	for (i = left; i < right; i++) {
		numbers[i] = resultSortedNumbers[i];
	}

	return numberOfInversions;
};

const getNumberOfInversions = (numbers, resultSortedNumbers, left, right) => {
	let numberOfInversions = 0;

	if (left + 1 >= right) {
		return numberOfInversions;
	}

	const middle = Math.floor((left + right) / 2);
	numberOfInversions += getNumberOfInversions(numbers, resultSortedNumbers, left, middle);
	numberOfInversions += getNumberOfInversions(numbers, resultSortedNumbers, middle, right);
	numberOfInversions += merge(numbers, resultSortedNumbers, left, middle, right);

	return numberOfInversions;
};

rl.once('line', line => {
	const n = parseInt(line, 10);

	rl.once('line', line => {
		const numbers = line.toString().split(' ').map(Number);
		const resultSortedNumbers = [];
		for (let i = 0; i < numbers.length; i++) {
			resultSortedNumbers[i] = 0;
		}

		process.stdout.write(`${getNumberOfInversions(numbers, resultSortedNumbers, 0, numbers.length)}`);

		process.exit();
	})
});

module.exports = getNumberOfInversions;
