const readline = require('readline');
const rl = readline.createInterface({
	input: process.stdin,
	terminal: false
});

const binarySearch = (arr = [], key) => {
	let left = 0, right = arr.length - 1;

	while (right >= left) {
		let middle = Math.floor((right + left) / 2);

		if (key === arr[middle]) {
			return middle;
		}

		if (key > arr[middle]) {
			left = middle + 1;
		}

		if (key < arr[middle]) {
			right = middle - 1;
		}
	}

	return -1;
};


process.stdin.setEncoding('utf8');

rl.once('line', line => {
	const arr = line.toString().split(' ').slice(1).map(Number);

	rl.once('line', line => {
		const keys = line.toString().split(' ').slice(1).map(Number);
		const result = [];

		for (let key of keys) {
			result.push(binarySearch(arr, key));
		}

		for (let res of result) {
			process.stdout.write(res + ' ');
		}

		process.stdout.write('\n');
		process.exit();
	})
});

module.exports = binarySearch;
