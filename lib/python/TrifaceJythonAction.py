from com.instrument.triface.action import ATrifaceAction
from ClojureMap import ClojureMap

#
# An abstract action class defining common getters and setters.
# Extending classes must implement execute().
#
# Currently designed to handle clojure maps (IPersistantMap)
#
class TrifaceJythonAction(ATrifaceAction):        
    
    def __init__(self):
        self.objectmap = {}
    
    # abstract function
    def execute(self): abstract
    
    # getters / setters 
    def setMapInternal(self,m):
    	# TODO: check type and use alternate map impl (HashMap, RubyMap?)
        self.objectmap = ClojureMap(m)        
        
    def getMapInternal(self):
        if(type(self.objectmap) is not dict):
            return self.objectmap.getmap()
        else:
            return self.objectmap