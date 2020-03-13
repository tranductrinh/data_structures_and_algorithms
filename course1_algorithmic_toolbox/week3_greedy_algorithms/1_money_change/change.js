const readline = require('readline');
const rl = readline.createInterface({
	input: process.stdin,
	terminal: false
});

process.stdin.setEncoding('utf8');

const change = (m) => {
	let num = 0;
	const denominations = [10, 5, 1];

	for (let i = 0; i < denominations.length; i++) {
		let coin = 0;
		while ((coin + 1) * denominations[i] <= m) {
			coin++;
		}

		m = m - coin * denominations[i];
		num += coin;

		if (m === 0) {
			break;
		}
	}

	return num;

};

const readLine = (line) => {
	console.log(change(parseInt(line, 10)));
	process.exit();
};

rl.on('line', readLine);

module.exports = change;
