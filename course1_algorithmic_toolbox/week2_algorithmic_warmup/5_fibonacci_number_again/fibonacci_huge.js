const readline = require('readline');
const rl = readline.createInterface({
	input: process.stdin,
	terminal: false
});

process.stdin.setEncoding('utf8');
rl.on('line', readLine);

function readLine(line) {
	if (line !== "\n") {
		const n = parseInt(line.toString().split(' ')[0], 10);
		const m = parseInt(line.toString().split(' ')[1], 10);

		console.log(getFibMod(n, m));
		process.exit();
	}
}


function pisanoPeriod(n, m) {
	if (n < 0) {
		throw new Error("n can not be a negative number!");
	}

	if (n == 0) {
		return [0];
	}

	if (n == 1) {
		return [0, 1];
	}

	const period = [];
	period[0] = 0;
	period[1] = 1;

	let i = 2;
	while (i < m * m) {
		period[i] = (period[i - 1] + period[i - 2]) % m;

		if (period[i] === 1 && period[i - 1] === 0) {
			break;
		}

		i++;
	}

	return period.splice(0, i-1);
}

function getFibMod(n, m) {
	const period = pisanoPeriod(n, m);
	const index = n % period.length;
	return period[index];
}

module.exports = getFibMod;
