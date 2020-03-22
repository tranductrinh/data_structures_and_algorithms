const readline = require('readline');
const rl = readline.createInterface({
	input: process.stdin,
	terminal: false
});
process.stdin.setEncoding('utf8');

/**
 * Count for given num in array a from left to right
 * @param a
 * @param left
 * @param right
 * @param num
 */
const count = (a, left, right, num) => {
	let count = 0;
	for (let i = left; i < right; i++) {
		if (a[i] === num) {
			count++;
		}
	}
	return count;
};

const getMajorityElement = (a, left, right) => {
	if (left + 1 === right) {
		return a[left];
	}

	const mid = Math.floor((left + right) / 2);
	const majorityCandidateOfFirstHalve = getMajorityElement(a, left, mid);
	const majorityCandidateOfSecondHalve = getMajorityElement(a, mid, right);

	if (majorityCandidateOfFirstHalve !== -1) {
		if (count(a, left, right, majorityCandidateOfFirstHalve) > Math.floor(((right - left) / 2))) {
			return majorityCandidateOfFirstHalve;
		}
	}

	if (majorityCandidateOfSecondHalve !== -1) {
		if (count(a, left, right, majorityCandidateOfSecondHalve) > Math.floor(((right - left) / 2))) {
			return majorityCandidateOfSecondHalve;
		}
	}

	return -1;
};

rl.once('line', line => {
	const n = parseInt(line, 10);

	rl.once('line', line => {
		const a = line.toString().split(' ').map(Number);

		if (getMajorityElement(a, 0, a.length) !== -1) {
			process.stdout.write('1');
		} else {
			process.stdout.write('0');
		}

		process.exit();
	})
});

module.exports = getMajorityElement;
