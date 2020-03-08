const readline = require('readline');
const rl = readline.createInterface({
	input: process.stdin,
	terminal: false
});

process.stdin.setEncoding('utf8');
rl.on('line', readLine);

function readLine(line) {
	if (line !== "\n") {
		const a = parseInt(line.toString().split(' ')[0], 10);
		const b = parseInt(line.toString().split(' ')[1], 10);

		console.log(lcm(a, b));
		process.exit();
	}
}

function lcm(a, b) {
	return a*b / gcd(a,b);
}

function gcd(a, b) {
	if (a == b) {
		return a;
	}

	if (a == 0) {
		return b;
	}

	if (b == 0) {
		return a;
	}

	if (a > b) {
		return gcd(b, a % b);
	} else {
		return gcd(b % a, a);
	}
}

module.exports = lcm;
