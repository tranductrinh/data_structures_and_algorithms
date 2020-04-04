const readline = require('readline');
const rl = readline.createInterface({
	input: process.stdin,
	terminal: false
});

process.stdin.setEncoding('utf8');

const memorize = (str1, str2) => {
	const n = str1.length;
	const m = str2.length;

	const d = new Array(n + 1);
	for (let i = 0; i <= n; i++) {
		d[i] = new Array(m + 1);
	}

	for (let i = 0; i <= n; i++) {
		d[i][0] = i;
	}

	for (let j = 0; j <= m; j++) {
		d[0][j] = j;
	}

	for (let j = 1; j <= m; j++) {
		for (let i = 1; i <= n; i++) {
			const insertion = d[i][j - 1] + 1;
			const deletion = d[i - 1][j] + 1;

			let mismatch = Number.MAX_VALUE, match = Number.MAX_VALUE;
			if (str1.charAt(i - 1) !== str2.charAt(j - 1)) {
				mismatch = d[i - 1][j - 1] + 1;
			} else {
				match = d[i - 1][j - 1];
			}

			d[i][j] = Math.min(Math.min(insertion, deletion), Math.min(mismatch, match));

		}
	}

	return d;
};

const editDistance = (str1, str2) => {
	const n = str1.length;
	const m = str2.length;

	return memorize(str1, str2)[n][m];

};

rl.once('line', (str1) => {
	rl.once('line', (str2) => {
		process.stdout.write(editDistance(str1, str2) + ' ');
		process.exit();
	})
});

module.exports = editDistance;
