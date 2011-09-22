/*
 * Triface action JS pre script. Defines default
 * JS object actions. Hacky way of defining an
 * abstract class. 
 */
importPackage(java.util);
importPackage(com.instrument.triface.action)

var clojureMap = function(m)
{
	this.persistentMap = m;
	this.get = function(key)
	{
		this.persistentMap.get(key);
	};
	this.put = function(key, val)
	{
		this.persistentMap = this.persistentMap.assoc(key, val);
	};
	this.getMap = function()
	{
		return this.persistentMap;
	};
	this.getClass = function()
	{
		return this.persistentMap.getClass();
	};
}

var action = {
	// JS has no native map, so use a Java HashMap
	objectmap: new HashMap(),
	setMapInternal: function(m)
	{
		// for some reason, import clojure.lang doesn't appear to work,
		// so no direct references...
		// TODO: fix this
		if(m.getClass() == "class clojure.lang.PersistentArrayMap")
		{
			this.objectmap = new clojureMap(m);
		}
		else this.objectmap = m;
	},
	getMapInternal: function()
	{
		if(this.objectmap.getClass() == "class clojure.lang.PersistentArrayMap")
		{
			return this.objectmap.getMap();
		}
		else return this.objectmap;
	}
};