require 'java'
require 'TrifaceJRubyAction.rb'

# dummy action to set some basic values for testing.
# sets basic ruby types
class NestedTypesAction < TrifaceJRubyAction
  
  def execute()
    @objectmap["list"] = [[1,1],[2,3]]
    @objectmap["map"] = {"key1" => {"foo","bar"}, "key2" => {"bar","baz"}}
    return getMap()
  end
  
end