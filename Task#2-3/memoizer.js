;(function(){
	function memoizer(f) {
		var cache = {};

		return function() {
			// Create key for the function
			// Warning! Function with different number of arguments aren't the same
			var key = f.name + '[' + Array.prototype.join.call(arguments, ';') + ']';

			if (key in cache) {
				// TO-DO: remove, beacus it's for debug only
				console.log('\nGot from cache. Number of properties is ' + calcProp(cache));

				return cache[key];
			}

			// Otherwise cache the function and return it
			cache[key] = f.apply(this, arguments);

			// TO-DO: remove, beacus it's for debug only
			console.log('\nCache this call. Number of properties is ' + calcProp(cache));

			return cache[key];
		};
	}

	// Helper function for calculating number of properties
	function calcProp(obj) {
		var p, i = 0;
		
		for (p in obj) {
			if (obj.hasOwnProperty(p)) {
				i++;
			}
		}

		return i;
	}

	// Testing
	function factorial(n) {
		var res = 1;
		
		while(n !== 1) {
			res *= n--;
		}
	
		return res;
	}

	function commaColonSON(str) {
		if (str.length < 2) {
			return str;
		}

		var res = {},
			ptrn = /(\w+)\s*,\s*(\w+)/g,
			nextRow = ptrn.exec(str);

		while(nextRow) {
			res[nextRow[1]] = nextRow[2];

			nextRow = ptrn.exec(str);
		}

		return res;
	}

	function commaColonSONAdv(str) {
		if (str.length < 2) {
			return str;
		}

		var res = {},
			ptrn = /(\w+)(:.+|,\w+)/g,
			nextRow = ptrn.exec(str);

		while(nextRow) {
			// Check if row has array
			if (nextRow[0].search(':') != -1) {
				var vals = nextRow[2].split(';'),
					valsToArr = [],
					nextVal;

				for(var j = 0, s = vals.length; j < s; j++) {
					// Build a object from every row from the array with commaColonSON function
					nextVal = commaColonSON(vals[j]);

					if (nextVal) {
						valsToArr.push(nextVal);
					}
				}

				res[nextRow[1]] = valsToArr;
			}
			
			/*
			 * nextRow[2] will be contain a value from pair key-value, 
			 * but with leading ',', so cut it with slice()
			 */
			else {
				res[nextRow[1]] = nextRow[2].slice(1);
			}

			nextRow = ptrn.exec(str);
		}

		return res;
	}

	function segmentLength(x,y,x1,y1) {
		var pow = function(a) {
			return function(p) {
				return Math.pow(a, p);
			};
		};

		return pow(pow(x - x1)(2) + pow(y - y1)(2))(0.5);
	}

	var ccSONAdvCalculator = memoizer(commaColonSONAdv);

	console.log('\nDefined ccSONAdvCalculator');
	console.log(ccSONAdvCalculator('17,minsk;232,gomel;162,brest;212,vitebsk;1522,grodno;222,mogilev'));
	console.log(
		ccSONAdvCalculator('country,belarus;cities:17,minsk;232,gomel;162,brest;212,vitebsk;1522,grodno;222,mogilev'));
	console.log('\nSo let\'s call this function again with parameters which already cached');
	console.log(
		ccSONAdvCalculator('country,belarus;cities:17,minsk;232,gomel;162,brest;212,vitebsk;1522,grodno;222,mogilev'));
	console.log(ccSONAdvCalculator('17,minsk;232,gomel;162,brest;212,vitebsk;1522,grodno;222,mogilev'));
	console.log(ccSONAdvCalculator('key,val'));

	var factorialCalculator = memoizer(factorial);
	
	console.log('\nDefined factorialCalculator');
	console.log('Result is ' + factorialCalculator(5));
	console.log('Result is ' + factorialCalculator(4));
	console.log('Result is ' + factorialCalculator(4));
	console.log('Result is ' + factorialCalculator(3));

	var seglenCalculator = memoizer(segmentLength);

	console.log('\nDefined seglenCalculator. It takes 4 arguments');
	console.log('Result is ' + seglenCalculator(1,1,1,1));
	console.log('Result is ' + seglenCalculator(1,2,3,4));
	console.log('Result is ' + seglenCalculator(4,3,2,1));
	// TO-DO: does last two calls work right? Result is the same but arguments aren't equals, I'm confused
	// So, should i check for the result value in cache?
})();
