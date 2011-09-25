from TrifaceJythonAction import TrifaceJythonAction

# Simple class to test java -> jython integration
# Sets basic python types
class NestedTypesAction(TrifaceJythonAction):

  def execute(self):
    self.objectmap['list'] = [[1,1],[2,3]]
    self.objectmap['map'] = {'key1': {'foo' : 'bar'}, 'key2': {'bar' : 'baz'}}
    return self.getMap()