;(function(){
	
	function arrayLikeChecker(obj) {
		
	}

	// Testing
	var objsForCheck = [
		{0: 0, 1: 1, 2: 2, 3: 3},
		[0,1,2,3],
		[],
		{},
		null,
		{'a': 0, 'b': 1, 'c': 2, 'd': 3}
	];

	for(var i = 0, s = objsForCheck.length; i < s; i++) {
		console.log(objsForCheck[i]);
		console.log('Do this obj like array? ' + arrayLikeChecker(objsForCheck[i]) + '\n');
	}
})();
