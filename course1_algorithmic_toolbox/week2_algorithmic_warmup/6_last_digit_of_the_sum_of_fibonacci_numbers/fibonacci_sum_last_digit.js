const readline = require('readline');
const rl = readline.createInterface({
	input: process.stdin,
	terminal: false
});
process.stdin.setEncoding('utf8');

/**
 * Sun of an array until the given index
 *
 * @param digits
 * @param until
 * @returns {number}
 */
const sumLastDigits = (digits, until) => {
	if (until >= digits.length) {
		throw new Error("i can not be greater than or equal " + digits.length);
	}

	let sum = 0;
	for (let i = 0; i <= until; i++) {
		sum += digits[i];
	}

	return sum;
};

/**
 * Sum of an array
 *
 * @param digits
 * @returns {number}
 */
const sum = (digits) => {
	let sum = 0;
	digits.forEach(digit => sum += digit);
	return sum;
};

/**
 * The last digits repeat every 60 numbers.
 */
const firstSixtyLastDigits = () => {
	const digits = [];
	digits[0] = 0;
	digits[1] = 1;

	for (let i = 2; i < 60; i++) {
		digits[i] = (digits[i - 1] + digits[i - 2]) % 10;
	}

	return digits;
};

const getFibonacciSum = (n) => {
	if (n <= 1) {
		return n;
	}

	const digits = firstSixtyLastDigits();
	const quotient = Math.floor(n / 60);
	const remain = n % 60;

	return (quotient * sum(digits) + sumLastDigits(digits, remain)) % 10;

};

const readLine = (line) => {
	console.log(getFibonacciSum(parseInt(line, 10)));
	process.exit();
};


rl.on('line', readLine);

module.exports = getFibonacciSum;
