const readline = require('readline');
const rl = readline.createInterface({
	input: process.stdin,
	terminal: false
});

process.stdin.setEncoding('utf8');
rl.on('line', readLine);

function readLine(line) {
	console.log(getFibonacciLastDigit(parseInt(line, 10)));
	process.exit();
}

/**
 * Fibonacci numbers: 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, ...
 *
 * Their last digits: 0, 1, 1, 2, 3, 5, 8, 3, 1, 4, 5, 9, 4, 3, 7, 0, 7, ...
 *
 * @param n
 * @returns {number|*}
 */
function getFibonacciLastDigit(n) {
	if (n < 0) {
		throw new Error("n can not be a negative number!");
	}

	if (n <= 1) {
		return n;
	}

	let previousDigit = 0, currentDigit = 1, temp;
	for (let i = 2; i <= n; i++) {
		temp = previousDigit;
		previousDigit = currentDigit;
		currentDigit = (currentDigit + temp) % 10;
	}

	return currentDigit;
}

module.exports = getFibonacciLastDigit;
