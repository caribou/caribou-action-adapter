from TrifaceJythonAction import TrifaceJythonAction

# implementation test. does nothing
class DummyAction(TrifaceJythonAction):

    def execute(self):      
        return self.getMap()