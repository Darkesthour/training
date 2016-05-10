;(function(){
	// Sorting algorithms
	// Insertion
	function insertionSort(arr) {
		if (arr.length == 1) return arr; // already sorted
		
		var i = 1,
			s = arr.length,
			curEl,
			pos;

		for (; i < s; i++) {
			pos = i;
			curEl = arr[i];

			while(pos > 0 && arr[pos - 1] > curEl) {
				arr[pos] = arr[pos - 1];
				pos--;
			}

			arr[pos] = curEl;
		}

		return arr;
	}

	function bubbleSort(arr) {
		var i = 0,
			j = 0,
			s = arr.length,
			tmp;

		for(; i < s; i++) {
			for(j = 0; j < s - 1; j++) {
				if (arr[j] > arr[j + 1]) {
					tmp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = tmp;
				}
			}
		}

		return arr;
	}

	function mergeSort(arr) {
		if (arr.length == 1) return arr; // already sorted
		
		var arrPart1 = arr.splice(0, arr.length / 2),
			arrPart2 = arr.splice(0, arr.length);

		arrPart1 = mergeSort(arrPart1);
		arrPart2 = mergeSort(arrPart2);

		return merge(arrPart1, arrPart2);
	}

	// Helper function for Merge Sort
	function merge(a, b) {
		var c = [],
			nextVal;

		/*
		 * Arrays are sorted by ascending, 
		 * so reverse them for access to cut length of the array without lost values
		 * (after pushing it's value into the result array)
		 */
		a.reverse();
		b.reverse();

		while(a.length && b.length) {
			if (a[a.length - 1] < b[b.length - 1]) {
				nextVal = a[a.length - 1];
				a.length -= 1; // why we reverse the array before
			}
			else {
				nextVal = b[b.length - 1];
				b.length -= 1;
			}

			c.push(nextVal);
		}

		while(a.length) {
			nextVal = a[a.length - 1];
			a.length -= 1;
			c.push(nextVal);
		}

		while(b.length) {
			nextVal = b[b.length - 1];
			b.length -= 1;
			c.push(nextVal);
		}

		return c;
	}
	
	// Max, Min, Avg
	// Max
	function max(arr) {
		if (arr.length == 1) return arr;

		for(var i = 0, max = 0, s = arr.length; i < s; i++) {
			max = arr[i].reduce(function(max, cur) {
				return max > cur ? max : cur;
			}, max);
		}

		return max;
	}

	// Min
	function min(arr) {
		if (arr.length == 1) return arr;

		for(var i = 0, min = 0, s = arr.length; i < s; i++) {
			min = arr[i].reduce(function(min, cur) {
				return min < cur ? min : cur;
			}, min);
		}

		return min;
	}

	// Avg
	function avg(arr) {
		if (arr.length == 1) return arr;

		for(var i = 0, sum = 0, s = arr.length; i < s; i++) {
			sum = arr[i].reduce(function(sum, cur) {
				return sum + cur;
			}, sum);
		}

		return sum / (s * s);
	}

	// Print triangles
	function printTriangleV1() {
		var size = 5,
			half = size / 2,
			i = 0,
			j = 0,
			edge = 0,
			row = '';

		for (; i < size; i++, row = '') {
			for (j = 0; j < size; j++) {
				row += (j >= edge && j <= size - 1 - edge) ? 1 : 0;
			}

			console.log(row);
			edge += (i + 1 > half) ? -1 : 1;
		}
	}

	function printTriangleV2() {
		var size = 5,
			half = size / 2,
			i = 0,
			j = 0,
			edge = 0,
			row = '';

		for (; i < size; i++, row = '') {
			for (j = 0; j < size; j++) {
				row += (j > edge) ? 0 : 1;
			}

			console.log(row);
			edge += (i + 1 > half) ? -1 : 1;
		}
	}

	// Object sort
	function objSort(arr, sortType) {
		var getPropCount = function(obj) {
			var count = 0;

			for(var prop in obj) {
				count += 1;
			}

			return count;
		}

		return arr.sort(function(a, b){
			// Assume, function will sort ascending by default
			return (sortType == 'desc') ? getPropCount(b) - getPropCount(a) : getPropCount(a) - getPropCount(b);
		});
	}

	// Tests
	var arr = [[5,2,-1], [11, 93, 0], [1, 1, 22]];
	// -- Max
	console.log('Max');
	console.log(max(arr));
	// -- Min
	console.log('\nMin');
	console.log(min(arr));
	// -- Avg
	console.log('\nAvg');
	console.log(avg(arr));
	
	// -- Print triangle
	console.log('\nPrint triangles');
	printTriangleV1();
	console.log('\n');
	printTriangleV2();
	
	// -- Object sort
	console.log('\nObject sorting');
	var obj1 = { a: 2, c: 3, d: 3};
	var obj2 = { a: 1 };
	var obj3 = { a: 2, c: 3};
	var arrOfObj = [obj1, obj2, obj3];

	console.log('\nAscending');
	console.log(objSort(arrOfObj));
	console.log('\nDescending');
	console.log(objSort(arrOfObj, 'desc'));

	// -- Sorting algorithms
	console.log('\nSorting algorithms');
	var arrForSort = [23, 12, 1, 4, 2, 22, 112, 33];
	console.log('\nInsertion sorting');
	console.log('Before sorting => ' + arrForSort);
	console.log('After sorting  => ' + insertionSort(arrForSort));
	console.log('\nBubble sorting');
	arrForSort = [23, 12, 1, 4, 2, 22, 112, 33];
	console.log('Before sorting => ' + arrForSort);
	console.log('After sorting  => ' + bubbleSort(arrForSort));
	console.log('\nMerge sorting');
	arrForSort = [23, 12, 1, 4, 2, 22, 112, 33];
	console.log('Before sorting => ' + arrForSort);
	console.log('After sorting  => ' + mergeSort(arrForSort));
})();
