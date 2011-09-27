// Simple class to test java -> JS integration
// Sets basic JS types
action.execute=function(){
  
  this.objectmap.put("list", [["a","b"],["c","d"]]);
  
  m1 = new HashMap();
  m1.put("key1","foo");
  m1.put("key2","bar");
  
  m2 = new HashMap();
  m2.put("key1", "bar");
  m2.put("key2", "baz");
  
  outerMap = new HashMap();
  outerMap.put("key1",m1);
  outerMap.put("key2",m2);
  
  this.objectmap.put("map", outerMap);
  
  return this.getMapInternal();
};
