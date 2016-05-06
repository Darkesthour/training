;(function(){

	// Sum of digits from strings
	function getDigitFromString(str) {
		if (!str) return 0;

		// Match can return null, so check it or give default value
		var digits = str.match(/(0x)?[A-F0-9]+/gi),
			hexPtrn = /(0x|[A-F])/i;

		if (!digits) return 0;

		return +digits.reduce(function(res, cur){
			var num = hexPtrn.test(cur) ? parseInt(cur, 16) : parseInt(cur);
			
			return res + num;
		}, '');
	}

	function sumDigitsFromStrings(str1, str2) {
		var digitFromStr1 = getDigitFromString(str1),
			digitFromStr2 = getDigitFromString(str2);

		return digitFromStr1 + digitFromStr2;
	}
	
	// CommaColonSON
	function commaColonSON(str) {
		if (typeof str != 'string') return;

		var res = {},
			nextRow,
			ptrn = /(\w+)\s*,\s*(\w+)/g;

		while(nextRow = ptrn.exec(str)) {
			res[nextRow[1]] = nextRow[2];
		}

		return res;
	}

	// Tests
	// -- Sum of digits from strings
	console.log('Testing ---> Sum of digits from strings');
	console.log(sumDigitsFromStrings(
		'12every2e-1thing is0x10 so comple0x1G',
		'01every45-e12e2day 0.1is like 1.a-3 5t5est'));
	console.log(sumDigitsFromStrings('1 2 3', 'a5 b6 c-17'));
	console.log(sumDigitsFromStrings('___', '0'));
	console.log(sumDigitsFromStrings('', ''));
	console.log(sumDigitsFromStrings('123x1z13', 'a123'));

	// CommaColonSON
	console.log('\nTesting ---> CommaColonSON');
	console.log(commaColonSON('17,minsk;232,gomel;162,brest;212,vitebsk;1522,grodno;222,mogilev'));
	console.log(commaColonSON('key')); // is it right? or it should output {key: ''}?

})();
