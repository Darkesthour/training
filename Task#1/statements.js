;(function(){
	
	// Sum of digits from strings
	function getDigitsFromStr(str) {
		return str.match(/0x[A-F0-9]+|\-?[0-9]+\.?[0-9]*(e-?[0-9]+)*/gi);
	}

	function safeSummarize(arr) {
		// Match can return null, so check it
		if (!arr) return 0;

		return arr.reduce(function(res, cur){
			return res + parseInt(cur);
		}, 0);
	}

	function sumDigitsFromStrings(str1, str2) {
		var digitsSet1 = getDigitsFromStr(str1),
			digitsSet2 = getDigitsFromStr(str2);

		var digitsSet1Sum = safeSummarize(digitsSet1),
			digitsSet2Sum = safeSummarize(digitsSet2);

		return digitsSet1Sum + digitsSet2Sum;
	}

	// Tests
	// -- Sum of digits from strings
	console.log(sumDigitsFromStrings(
		'12every2e-1thing is0xA5F so comple0x1c1G',
		'01every45-e12e2day 0.1is like 1.a-3 5t5est'));
	console.log(sumDigitsFromStrings('1 2 3', 'a5 b6 c-17'));
	console.log(sumDigitsFromStrings('___', '0'));

})();
