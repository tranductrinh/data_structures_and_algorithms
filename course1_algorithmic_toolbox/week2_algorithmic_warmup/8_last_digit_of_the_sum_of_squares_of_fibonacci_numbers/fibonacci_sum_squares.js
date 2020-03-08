const readline = require('readline');
const rl = readline.createInterface({
	input: process.stdin,
	terminal: false
});
process.stdin.setEncoding('utf8');

/**
 * Sun squares of an array until the given index
 *
 * @param digits
 * @param until
 * @returns {number}
 */
const sumSquaresUntil = (digits, until) => {
	if (until >= digits.length) {
		throw new Error("i can not be greater than or equal " + digits.length);
	}

	let sum = 0;
	for (let i = 0; i <= until; i++) {
		sum += digits[i] * digits[i];
	}

	return sum;
};

/**
 * Sum square of an array
 *
 * @param digits
 * @returns {number}
 */
const sumSquares = (digits) => {
	let sum = 0;
	digits.forEach(digit => sum += digit * digit);
	return sum;
};

/**
 * The last digits repeat every 60 numbers.
 */
const firstSixtyOfLastDigits = () => {
	const digits = [];
	digits[0] = 0;
	digits[1] = 1;

	for (let i = 2; i < 60; i++) {
		digits[i] = (digits[i - 1] + digits[i - 2]) % 10;
	}

	return digits;
};

/**
 * Get Fibonacci sum of last digits from F0 to Fn
 *
 * @param n
 * @returns {number|*}
 */
const getFibonacciSumSquares = (n) => {
	if (n <= 1) {
		return n;
	}

	const digits = firstSixtyOfLastDigits();
	const quotient = Math.floor(n / 60);
	const remain = n % 60;

	return (quotient * sumSquares(digits) + sumSquaresUntil(digits, remain)) % 10;

};

const readLine = (line) => {
	console.log(getFibonacciSumSquares(parseInt(line, 10)));
	process.exit();
};


rl.on('line', readLine);

module.exports = getFibonacciSumSquares;
