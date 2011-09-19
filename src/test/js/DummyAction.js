importPackage(java.lang);
importPackage(java.util);
importPackage(com.instrument.triface.action)

o = new ATrifaceAction() {
	m: new Array(),
	execute: function() { 
		this.m.assoc("foo","bar");
	},
	setMapInternal: function(m)
	{
		java.lang.System.out.println("set m" + m);
		this.m = m;
	},
	getMapInternal: function()
	{
		java.lang.System.out.println("get map");
		return this.m;
	}
};