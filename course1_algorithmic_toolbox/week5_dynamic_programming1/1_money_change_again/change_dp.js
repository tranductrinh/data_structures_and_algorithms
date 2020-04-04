const readline = require('readline');
const rl = readline.createInterface({
	input: process.stdin,
	terminal: false
});

process.stdin.setEncoding('utf8');

const changeDP = (money) => {
	const minNumCoins = Array(money + 1).fill(0);

	const coins = [1, 3, 4];

	for (let m = 1; m <= money; m++) {
		minNumCoins[m] = Number.MAX_VALUE;
		for (const coin of coins) {
			if (m >= coin) {
				const numCoins = minNumCoins[m - coin] + 1;
				if (numCoins < minNumCoins[m]) {
					minNumCoins[m] = numCoins;
				}
			}
		}
	}
	return minNumCoins[money];

};

const readLine = (line) => {
	console.log(changeDP(parseInt(line, 10)));
	process.exit();
};

rl.on('line', readLine);

module.exports = changeDP;
