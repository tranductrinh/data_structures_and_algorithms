const readline = require('readline');
const rl = readline.createInterface({
	input: process.stdin,
	terminal: false
});

process.stdin.setEncoding('utf8');

const computeMinRefills = (dist, tank, stops) => {
	if (dist <= tank) {
		return 0;
	}

	let numOfRefilling = 0;
	let lastRefillingStop = 0;
	for (let i = 0; i < stops.length; i++) {
		let currentStop = stops[i];
		let nextStop;

		if (i + 1 === stops.length) {
			nextStop = dist;
		} else {
			nextStop = stops[i + 1];
		}

		if (nextStop - currentStop > tank) {
			return -1;
		}

		if (currentStop - lastRefillingStop <= tank && nextStop - lastRefillingStop <= tank) {
			continue;
		}

		lastRefillingStop = currentStop;
		numOfRefilling++;
	}

	return numOfRefilling;
};

/**
 * Enter:
 *
 * 950
 * 400
 * 4
 * 200 375 550 750
 *
 */
rl.once('line', line => {
	const dist = parseInt(line, 10);
	rl.on('line', line => {
		const tank = parseInt(line, 10);
		rl.on('line', line => {
			const n = parseInt(line, 10);
			rl.on('line', line => {
				const stops = line.toString().split(' ').map(Number);
				console.log(computeMinRefills(dist, tank, stops));
				process.exit();
			});
		});
	});

});

module.exports = computeMinRefills;
