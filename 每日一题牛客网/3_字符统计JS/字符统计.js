function count(str) {
	var obj = {};
	for (var i = 0; i < str.length; i++) {
		!obj[str.charAt(i)]?obj[str.charAt(i)]=1:obj[str.charAt(i)]++;
	}
	return obj
}