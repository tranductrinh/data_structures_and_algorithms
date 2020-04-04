const readline = require('readline');
const rl = readline.createInterface({
	input: process.stdin,
	terminal: false
});

process.stdin.setEncoding('utf8');

const countMatch = (d, a, b) => {
	let count = 0;
	let i = d.length - 1;
	let j = d[0].length - 1;

	while (i > 0 && j > 0) {
		if (a[i - 1] === b[j - 1]) {
			count++;
			i = i - 1;
			j = j - 1;
		} else if (d[i - 1][j] > d[i][j - 1]) {
			i = i - 1;
		} else {
			j = j - 1;
		}
	}

	return count;
};

const memorize = (a, b) => {
	const n = a.length;
	const m = b.length;

	const d = new Array(n + 1);
	for (let i = 0; i <= n; i++) {
		d[i] = new Array(m + 1).fill(0);
	}

	for (let j = 1; j <= m; j++) {
		for (let i = 1; i <= n; i++) {
			if (a[i - 1] === b[j - 1]) {
				d[i][j] = d[i - 1][j - 1] + 1;
			} else {
				d[i][j] = Math.max(d[i][j - 1], d[i - 1][j]);
			}
		}
	}

	return d;
};

const lcs2 = (a, b) => {
	const d = memorize(a, b);
	return countMatch(d, a, b);

};

rl.once('line', line => {
	parseInt(line, 10);
	let a = [];
	let b = [];

	rl.once('line', line => {
		a = line.toString().split(' ').map(Number);

		rl.once('line', line => {
			parseInt(line, 10);

			rl.once('line', line => {
				b = line.toString().split(' ').map(Number);

				process.stdout.write('' + lcs2(a, b));
				process.exit();
			});
		});
	})
});

module.exports = lcs2;
