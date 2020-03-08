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
const sumUntil = (digits, until) => {
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
const getFibonacciSum = (n) => {
	if (n <= 1) {
		return n;
	}

	const digits = firstSixtyOfLastDigits();
	const quotient = Math.floor(n / 60);
	const remain = n % 60;

	return quotient * sum(digits) + sumUntil(digits, remain);

};

/**
 * Get Fibonacci sum of last digits from F_from to F_to
 *
 * @param from
 * @param to
 * @returns {number}
 */
const getFibonacciPartialSum = (from, to) => {
	return (getFibonacciSum(to) - getFibonacciSum(from - 1 > 0 ? from - 1 : 0)) % 10;
};

const readLine = (line) => {
	if (line !== "\n") {
		const a = parseInt(line.toString().split(' ')[0], 10);
		const b = parseInt(line.toString().split(' ')[1], 10);

		console.log(getFibonacciPartialSum(a, b));
		process.exit();
	}
};


rl.on('line', readLine);

module.exports = getFibonacciPartialSum;
