const readline = require('readline');
const rl = readline.createInterface({
	input: process.stdin,
	terminal: false
});

process.stdin.setEncoding('utf8');
rl.once('line', () => {
	rl.on('line', readLine);
});

/**
 * Enter something like
 *
 * 5
 * 1 2 3 4 5
 *
 * @param line
 */
function readLine(line) {
	const numbers = line.toString().split(' ').map(Number);

	console.log(max_pairwise_product(numbers));
	process.exit();
}

function max_pairwise_product(numbers) {
	const length = numbers.length;

	if (length < 2) {
		throw new Error("Length is too short!");
	}

	if (length > 2 * Math.pow(10, 5)) {
		throw new Error("Length is too long!");
	}

	let biggestNumber = 0;
	let secondBiggestNumber = 0;
	if (numbers[0] >= numbers[1]) {
		biggestNumber = numbers[0];
		secondBiggestNumber = numbers[1];
	} else {
		biggestNumber = numbers[1];
		secondBiggestNumber = numbers[0];
	}

	for (let i = 2; i < length; i++) {
		if (numbers[i] >= biggestNumber) {
			secondBiggestNumber = biggestNumber;
			biggestNumber = numbers[i];
		} else if (numbers[i] >= secondBiggestNumber) {
			secondBiggestNumber = numbers[i];
		}
	}

	return biggestNumber * secondBiggestNumber;

}

module.exports = max_pairwise_product;
