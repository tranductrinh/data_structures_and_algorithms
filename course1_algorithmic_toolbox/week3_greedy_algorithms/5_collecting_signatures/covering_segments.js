const readline = require('readline');
const rl = readline.createInterface({
	input: process.stdin,
	terminal: false
});

process.stdin.setEncoding('utf8');

class Segment {
	get end() {
		return this._end;
	}

	get start() {
		return this._start;
	}

	constructor(start, end) {
		this._start = start;
		this._end = end;
	}
}

const swap = (segments, i, j) => {
	const tmp = new Segment(segments[i].start, segments[i].end);
	segments[i] = segments[j];
	segments[j] = tmp;
};

const sortSegmentByEndAsc = (segments) => {
	for (let i = 0; i < segments.length - 1; i++) {
		for (let j = i + 1; j < segments.length; j++) {
			if (segments[i].end > segments[j].end) {
				swap(segments, i, j);
			}
		}
	}
};

const optimalPoints = (segments) => {
	if (segments.length === 0) {
		return [];
	}

	if (segments.length === 1) {
		return [segments[0].end];
	}

	sortSegmentByEndAsc(segments);

	let points = [];
	let currentSegment = segments[0];
	let lastConvergedEnd = currentSegment.end;
	for (let i = 1; i < segments.length; i++) {
		if (segments[i].start <= lastConvergedEnd && lastConvergedEnd <= segments[i].end) {
			lastConvergedEnd = Math.min(segments[i].end, lastConvergedEnd);
			continue;
		}

		points.push(lastConvergedEnd);
		currentSegment = segments[i];
		lastConvergedEnd = currentSegment.end;
	}

	if (currentSegment.start <= lastConvergedEnd && lastConvergedEnd <= currentSegment.end) {
		points.push(lastConvergedEnd);
	} else {
		points.push(segments[segments.length - 1].end);
	}

	return points;
};

/**
 * Enter:
 *
 * 3
 * 1 3
 * 2 5
 * 3 6
 *
 */

rl.once('line', line => {
	const n = parseInt(line, 10);
	const segments = [];
	rl.on('line', line => {
		const nums = line.toString().split(' ').map(Number);
		segments.push(new Segment(nums[0], nums[1]));

		if (segments.length === n) {
			const points = optimalPoints(segments);
			console.log(points.length);
			for (const point of points) {
				console.log(point);
			}
			process.exit();
		}
	});

});


module.exports = optimalPoints;
