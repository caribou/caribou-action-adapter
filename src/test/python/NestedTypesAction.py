from TrifaceJythonAction import TrifaceJythonAction

# Simple class to test java -> jython integration
# Sets basic python types
class NestedTypesAction(TrifaceJythonAction):

  def execute(self):
    self.objectmap['list'] = [["a","b"],["c","d"]]
    self.objectmap['map'] = {'key1': {'foo' : 'bar'}, 'key2': {'bar' : 'baz'}}
    return self.getMap()