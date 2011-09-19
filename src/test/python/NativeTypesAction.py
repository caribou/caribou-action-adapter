from TrifaceJythonAction import TrifaceJythonAction

# Simple class to test java -> jython integration
# Sets basic python types
class NativeTypesAction(TrifaceJythonAction):

    def execute(self):
        self.objectmap['string'] = 'hello world!'
        self.objectmap['boolean'] = True
        self.objectmap['int'] = 1
        self.objectmap['long'] = 1L
        self.objectmap['float'] = 1.0
        self.objectmap['complex'] = 1.0j
        self.objectmap['tuple'] = (1,2)
        self.objectmap['list'] = [1,1,2,3,5]
        self.objectmap['map'] = {'key1': 'val1', 'key2': 2}        
        return self.getMap()