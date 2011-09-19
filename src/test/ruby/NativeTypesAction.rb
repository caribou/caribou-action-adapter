require 'java'
require 'TrifaceJRubyAction.rb'

# dummy action to set some basic values for testing.
# sets basic ruby types
class NativeTypesAction < TrifaceJRubyAction
  
	def execute()
		@objectmap["string"] = "hello world!"
		@objectmap["boolean"] = true
		@objectmap["int"] = 1
		@objectmap["float"] = 1.0
		@objectmap["list"] = [1,1,2,3,5]
		@objectmap["map"] = {"key1" => "val1", "key2" => 2}
		return getMap()
	end
	
end