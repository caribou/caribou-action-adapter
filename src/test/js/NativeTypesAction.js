// map -- this.objectMap
action.execute=function(){
	this.objectmap.put("string","hello world!");
	this.objectmap.put("boolean", true);
	this.objectmap.put("int",1);
	this.objectmap.put("float",1.0);
	this.objectmap.put("list", [1,1,2,3,5]);
	
	tmpMap = new HashMap();
	tmpMap.put("key1","val1");
	tmpMap.put("key2",2)
	
	this.objectmap.put("map", tmpMap)
	return this.getMapInternal();
};
