;(function() {
	var STKit = {};

	// Memoizer function
	STKit.memoizer = function(f) {
		var cache = {},
			action = function() {
				// Create key for the function
				// Warning! Function with different number of arguments aren't the same
				var key = f.name + '[' + Array.prototype.join.call(arguments, ';') + ']';

				if (key in cache) {
					return cache[key];
				}

				// Otherwise cache the function, increment count property and return it
				cache[key] = f.apply(this, arguments);

				// Increment count of properties
				action.count++;

				return cache[key];
			};

		// Set count property just for testing
		// Makes sure that specified function got from cache or created a new one
		action.count = 0;

		return action;
	};

	/*
	 * Actually, array can contain 0 elements with positiove length, 
	 * or some non-number properties, so just check for correct value
	 * of length property
	 */
	STKit.arrayLikeChecker = function(o) {
		if (o &&
			typeof o === 'object' && // it's a object
			!isNaN(parseFloat(o.length)) && // length is a number and isn't NaN (for parseFloat)
			isFinite(o.length) && // length isn't Infinity, -Infinity or NaN
			o.length > -1 && // length is positive number
			o.length === Math.round(o.length)) { // length is integer number

			return true;
		}

		return false;
	};

	module.exports = STKit;
})();
