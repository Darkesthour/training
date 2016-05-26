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

	describe('Debehaviorizer testing', function() {
		var obj,
			expectedObj = {
				'1': 'abc',
				'3': {
					'1': 'abc',
					'2': 'def'
				},
				'4': {},
				'5': null,
				'6': {
					'0': 'test',
					'1': 123,
				},
				'7': [1,2,3],
				'8': {}
			},
			expectedArr = [
				function() {},
				function() { console.log('test'); },
				function() {},
				function() { console.log('just do it'); },
				function() { console.log('just someone else do it'); }
			],
			arrFuncEquality = function(a, b) {
				if (!Array.isArray(a) || !Array.isArray(b) || a.length != b.length) {
					return;
				}

				for(var i = 0, s = a.length; i < s; i++) {
					if (a[i].toString() !== b[i].toString()) {
						
						return false;
					}
				}

				return true;
			};

		beforeEach(function() {
			obj = {
				'0': function() {},
				'1': 'abc',
				'2': function() { console.log('test'); },
				'3': {
					'0': function() {},
					'1': 'abc',
					'2': 'def'
				},
				'4': {},
				'5': null,
				'6': {
					'0': 'test',
					'1': 123,
				},
				'7': [1,2,3],
				'8': {
					'doit': function() { console.log('just do it'); },
					'someonedoit': function() { console.log('just someone else do it'); }
				}
			};

			/*
			 * Register custom equality tester which helps to 
			 * compare two arrays with function elements
			 */
			jasmine.addCustomEqualityTester(arrFuncEquality);
		});

		it('Without return behaviours', function() {
			var res = STKit.debehaviorizer(obj);

			expect(res).toEqual(expectedObj);
		});

		it('With return behaviours', function() {
			expect(STKit.debehaviorizer(obj, true)).toEqual(expectedArr);

			// Don't forget about modified obj
			expect(obj).toEqual(expectedObj);
		});
	});
});