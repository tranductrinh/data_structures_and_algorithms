const readline = require('readline');
const rl = readline.createInterface({
	input: process.stdin,
	terminal: false
});

process.stdin.setEncoding('utf8');

const sortDesc = (numbers) => {
	if (numbers == null || numbers.length <= 1) {
		return;
	}

	for (let i = 0; i < numbers.length - 1; i++) {
		for (let j = i + 1; j < numbers.length; j++) {
			if (numbers[j] > numbers[i]) {
				let valueTmp = numbers[i];
				numbers[i] = numbers[j];
				numbers[j] = valueTmp;
			}
		}
	}

};

const maxDotProduct = (a, b) => {
	sortDesc(a);
	sortDesc(b);

	let product = 0;
	for (let i = 0; i < a.length; i++) {
		product += (a[i] * b[i]);
	}

	return product;
};

/**
 * Enter:
 *
 * 3
 * 1 3 -5
 * -2 4 1
 *
 */
rl.on('line', line => {
	const n = parseInt(line, 10);
	rl.on('line', line => {
		const a = line.toString().split(' ').map(Number);
		rl.on('line', line => {
			const b = line.toString().split(' ').map(Number);
			console.log(maxDotProduct(a, b));
			process.exit();
		});
	});
});


module.exports = maxDotProduct;
