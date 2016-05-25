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

	// Debehaviorizer function
	STKit.debehaviorizer = function(o) {
		var returnBehaviours = arguments[1] || false,
			res = returnBehaviours ? [] : {},
			_res,
			p,
			isFunction;

		for(p in o) {
			// Prevent work with properties from prototype
			if (!o.hasOwnProperty(p)) {
				continue;
			}

			// If next value is object call debehaviorizer recursively
			if (o[p] && typeof o[p] === 'object' && !Array.isArray(o[p])) {
				_res = this.debehaviorizer(o[p], returnBehaviours);
				
				this.insertRes(res, _res, p);

				// So, res was updated go to the next property
				continue;
			}

			// Otherwise, values is function or simple type
			isFunction = typeof o[p] === 'function';

			this.insertRes(res, o, p, isFunction, returnBehaviours);
		}

		return res;
	};

	/*
	 * It's helper function for STKit.debehaviorizer
	 * Take res from recursion and insert it into the actual result
	 */
	STKit.insertResFromRecursion = function(res, _res, p) {
		if (Array.isArray(_res)) {
			/*
			 * Work with res like array
			 * Using this construction instead of using concat method,
			 * because it need to update existing result instead of creating a new.
			 * It allow not use return statement
			 */
			Array.prototype.push.apply(res, _res);
		}
		else {
			// Work with res like object
			res[p] = _res;
		}
	};

	/*
	 * It's helper function for STKit.debehaviorizer
	 * Update res with new key/value considering the type of value and
	 * need of return of behaviours
	 */
	STKit.insertRes = function(res, o, p, isFunction, returnBehaviours) {
		// If value of property is function and it need to return behaviours
		if (isFunction && returnBehaviours) {
			// Work with res like array
			res.push(o[p]);

			// For strict mode
			try {
				delete o[p];
			}
			catch(e) {/* Well, specified object is sealed or frozen; just ignore it */}
		}

		// Otherwise, value isn't function and it don't need to return behaviours
		if (!isFunction && !returnBehaviours) {
			// Work with res like object
			res[p] = o[p];
		}
	};

	module.exports = STKit;
})();
