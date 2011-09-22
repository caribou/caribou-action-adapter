importPackage(com.instrument.triface.action)
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

action.execute = function()
{
	var foo = "bar";
	return this.getMapInternal();
}
new JavaAdapter(com.instrument.triface.action.ATrifaceAction,action);