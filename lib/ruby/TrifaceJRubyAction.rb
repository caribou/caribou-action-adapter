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
    	# TODO: check type and use alternate map impl (HashMap, RubyMap?)	
    	@objectmap = ClojureMap.new(m)
	end
	
	def getMapInternal()
		if @objectmap.is_a?(ClojureMap)
			return @objectmap.getMap()			 
		else
			return @objectmap
		end
  	end
	
end