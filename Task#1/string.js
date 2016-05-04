;(function(){
	// Reverse string
	function reverseString(str) {
		if (typeof str != 'string') return;
		if (!str.length) return str;

		var size = str.length,
			steps = Math.floor(size / 2),
			i = 0,
			j = size - 1,
			symbols = [];

		/*
		 * String is immutable, so save symbols in the array, 
		 * which wll be converted into string on return step
		 */
		for(; i < steps; i++, j--) {
			symbols[i] = str[j];
			symbols[j] = str[i];
		}

		/* Don't forget about symbol in the middle of the string 
		 * (only if length of the string is odd)
		 */
		if (size % 2 != 0) {
			symbols[i] = str[i];
		}

		return symbols.reduce(function(res, cur) {
			return res + cur;
		});
	}

	function reverseStringV2(str) {
		if (typeof str != 'string') return;
		if (!str.length) return str;

		var strToArr = str.split('');

		strToArr.reverse();

		return strToArr.reduce(function(res, cur){
			return res + cur;
		});
	}

	// Ends with
	function endsWith(str, symbol) {
		if (typeof str != 'string') return;
		if (!str.length) return str;

		return str.charAt(str.length - 1) === symbol;
	}

	// Starts with
	function startsWith(str, symbol) {
		if (typeof str != 'string') return;
		if (!str.length) return str;

		return str.charAt(0) === symbol;
	}

	// Tests
	// -- Reverse
	console.log('Testing ---> Reverse');
	console.log(reverseString('Crazy Town'));
	console.log(reverseString(''));
	console.log(reverseString('A7X'));
	console.log(reverseString());
	console.log(reverseStringV2('Crazy Town'));

	// -- Starts with
	console.log('\nTesting ---> Start with')
	console.log(startsWith('Crazy Town', 'd'));
	console.log(startsWith('Crazy Town', 'C'));
	console.log(startsWith(null, '0'));

	// -- Ends with
	console.log('\nTesting ---> End with');
	console.log(endsWith(null, 'g'));
	console.log(endsWith(null, null));
	console.log(endsWith('A7x', 'X'));
	console.log(endsWith('A7x', 'x'));
})();
