require 'java'

class ClojureMap < Hash
  
  def initialize(cljMap)
  	@persistentMap = cljMap
  end
  
  def [](key)
    @persistentMap.get(key)
  end

  def []=(key, value)
    @persistentMap = @persistentMap.assoc(key, value)
  end
  
  def getMap()
  	return @persistentMap
  end
end