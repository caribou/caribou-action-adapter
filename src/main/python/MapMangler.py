from com.instrument.triface.interfaces import IMapMangler
from java.lang import System as JSystem

#Simple class to test java -> jython integration
class MapMangler(IMapMangler):
    
    def __init__(self):
        self.maps = {}
        
    def mangle(self):
        self.maps['string'] = 'hello world!'
        self.maps['boolean'] = True
        self.maps['int'] = 1
        self.maps['long'] = 1L
        self.maps['float'] = 1.0
        self.maps['complex'] = 1.0j
        self.maps['tuple'] = (1,2)
        self.maps['list'] = [1,1,2,3,5]
        self.maps['dict'] = {'key1': 'val1', 'key2': 2}
        
        # dump the map
        JSystem.out.println(self.maps)
        
        return self.maps        
    
    
    # getters / setters 
        
    def setMap(self,m):
        self.maps = m
        
    def getMap(self):
        return self.maps