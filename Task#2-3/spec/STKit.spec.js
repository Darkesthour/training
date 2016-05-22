describe('STKit testing', function() {
	var STKit = require('../STKit');

	describe('Memoizer testing', function() {
		var memoizer = STKit.memoizer,
			factorial = function(n) {
				var res = 1;
				
				while(n !== 1) {
					res *= n--;
				}
				
				return res;
			};

		it('Testing on factorial function', function() {
			var factorialCalc = memoizer(factorial);

			expect(6).toEqual(factorialCalc(3));
			expect(1).toEqual(factorialCalc.count);

			expect(120).toEqual(factorialCalc(5));
			expect(2).toEqual(factorialCalc.count);

			expect(6).toEqual(factorialCalc(3));
			expect(2).toEqual(factorialCalc.count);
		});
	});

	describe('ArrayLikeChecker testing', function() {
		var arrayLikeChecker = STKit.arrayLikeChecker;

		it('Test 1', function() {
			var obj = {
				length: 1,
				'a': 5
			};

			expect(arrayLikeChecker(obj)).toBeTruthy();
		});

		it('Test 2', function() {
			var obj = null;

			expect(arrayLikeChecker(obj)).toBeFalsy();
		});

		it('Test 3', function() {
			var obj = {
				length: -1,
				'0': 1
			};

			expect(arrayLikeChecker(obj)).toBeFalsy();
		});

		it('Test 4', function() {
			var obj = {	length: 5.4	};

			expect(arrayLikeChecker(obj)).toBeFalsy();
		});
	});
});