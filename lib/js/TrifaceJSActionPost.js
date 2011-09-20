/*
 * Triface action JS post script. Takes the action and forces it to an 
 * abstract class. Mad hacky.
 */
importPackage(com.instrument.triface.action)
new JavaAdapter(com.instrument.triface.action.ATrifaceAction,action);