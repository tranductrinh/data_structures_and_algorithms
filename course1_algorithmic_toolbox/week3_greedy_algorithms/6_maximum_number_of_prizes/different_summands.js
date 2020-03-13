const readline = require('readline');
const rl = readline.createInterface({
	input: process.stdin,
	terminal: false
});

process.stdin.setEncoding('utf8');

const optimalSummands = (n) => {
	const summands = new Set();

	let sum = 0;

	for (let i = 1; i <= n; i++) {
		const left = n - (sum + i);

		if (left === i || summands.has(left)) {
			continue;
		}

		if (left < 0) {
			break;
		}

		summands.add(i);
		sum += i;
	}

	return [...summands];
};

/**
 * Enter:
 *
 * 8
 *
 */

rl.once('line', line => {
	const summands = optimalSummands(parseInt(line, 10));
	console.log(summands.length);
	for (const summand of summands) {
		process.stdout.write(`${summand} `);
	}
	process.exit();

});


module.exports = optimalSummands;
