importPackage(java.lang);
importPackage(java.util);
importPackage(com.instrument.triface.action)

/**
 * OH GOD THIS IS HACKY.
 * 
 * So get this:
 * Once we create an instance of a java class here in JS,
 * we can't further extend it here in JS land. 
 * 
 * Therefore, if I want to define some default JS methods. 
 * I have to define them in a generic object, 
 * then override them downstream, finally using
 * the Rhino JavaAdapter to force it into the
 * appropriate java type.
 */
var action = {
	objectMap: new HashMap(),
	setMapInternal: function(m)
	{
		this.objectMap = m;
	},
	getMapInternal: function()
	{
		return this.objectMap;
	}
};