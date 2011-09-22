require 'java'
require 'ClojureMap.rb'
import com.instrument.triface.action.ATrifaceAction

#
# An abstract action class defining common getters and setters.
# Extending classes must implement execute().
#
# Currently designed to handle clojure maps (IPersistantMap)
#
class TrifaceJRubyAction < ATrifaceAction
  
  	def initialize
  		@objectmap = Hash.new
  	end
  
	def execute()
		raise NotImplementedError.new
	end
	
	def setMapInternal(m)
    	# TODO: figure out how to do better type checking. this sucks.
    	if m.getClass().toString() == "class clojure.lang.PersistentArrayMap"
    		@objectmap = ClojureMap.new(m)
    	else
    		@objectmap = m
    	end
	end
	
	def getMapInternal()
		if @objectmap.is_a?(ClojureMap)
			return @objectmap.getMap()			 
		else
			return @objectmap
		end
  	end
	
end