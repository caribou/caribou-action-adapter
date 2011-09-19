from org.python.core import PyDictionary
from org.python.core import PyList
from clojure.lang import PersistentArrayMap
from clojure.lang import PersistentVector

#
# wrapper class for clojure maps (IPersistantMap), 
# provides native syntax emulation and dict-like behavior.
#
# The only reason this exists is that adding to a clojure
# map via assoc is both fast and cheap.
#
class ClojureMap(dict):
    
    def __init__(self, dict = None):
        if dict is None: 
            raise NotImplementedError
        else: 
            self.data = dict

    def __getitem__(self, key):
        return self.data.get(key)

    def __setitem__(self, key, item):
        self.data = self.data.assoc(key, item)
        
    def __delitem__(self, key):
        self.data = self.data.without(key)
           
    def keys(self):
        return self.data.keySet()
    
    def has_key(self, key):
         return key in self.keys()
     
    def __iter__(self):
        for k in self.keys():
            yield k    
            
    def __contains__(self, key):
        return self.has_key(key)
                
    def iteritems(self):
        for k in self.data.iterator():
            yield (k, self[k])
            
    def iterkeys(self):
        return self.__iter__()
    
    def itervalues(self):
        for _, v in self.iteritems():
            yield v
            
    def values(self):
        return list(self.itervalues())
    
    def items(self):
        return list(self.iteritems())
    
    def clear(self):
        for key in self.keys():
            del self[key]
            
    def setdefault(self, key, default):
        if key not in self:
            self[key] = default
            return default
        return self[key]
    
    def popitem(self):
        key = self.keys()[0]
        value = self[key]
        del self[key]
        return (key, value)
    
    def update(self, other):
        for key in other.keys():
            self[key] = other[key]
            
    def get(self, key, default):
        if key in self:
            return self[key]
        return default
    
    def __repr__(self):
        return repr(dict(self.items()))   
    
    # return the underlying map
    def getmap(self):             
        return self.data              