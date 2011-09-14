from com.instrument.triface.action import ATrifaceJythonAction
from ClojureMap import ClojureMap

#Simple class to test java -> jython integration
class TrifaceJythonAction(ATrifaceJythonAction):        
    
    # abstract function
    def mangle(self): abstract
    
    # getters / setters 
    def setMap(self,m):
        self.maps = ClojureMap(m)        
        
    def getMap(self):
        return self.maps.getmap()