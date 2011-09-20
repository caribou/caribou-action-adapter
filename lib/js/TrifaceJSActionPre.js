/*
 * Triface action JS pre script. Defines default
 * JS object actions. Hacky way of defining an
 * abstract class. 
 */

importPackage(java.lang);
importPackage(java.util);
importPackage(com.instrument.triface.action)

var action = {
	// JS has no native map, so use a Java HashMap
	objectmap: new HashMap(),
	setMapInternal: function(m)
	{
		this.objectmap = m;
	},
	getMapInternal: function()
	{
		return this.objectmap;
	}
};