const readline = require('readline');
const rl = readline.createInterface({
	input: process.stdin,
	terminal: false
});

process.stdin.setEncoding('utf8');
rl.on('line', readLine);

function readLine(line) {
	console.log(calculateFibonacci(parseInt(line, 10)));
	process.exit();
}

function calculateFibonacci(n) {
	if (n < 0) {
		throw new Error('n can not be a negative number!');
	}

	if (n <= 1) {
		return n;
	}

	let previous = 0, current = 1, temp;
	for (let i = 2; i <= n; i++) {
		temp = previous;
		previous = current;
		current = current + temp;
	}

	return current;

}

module.exports = calculateFibonacci;
