require 'java'
require 'TrifaceJRubyAction.rb'

# dummy action
class DummyAction < TrifaceJRubyAction
  
	def execute()
		return getMap()
	end
	
end