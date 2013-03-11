package org.tizen.common.util.asm.model;

public class Method {
    
    private String name;
    private String type;
    private Parameter returnValue;
    private int accessor;
    
    public Method(String name, String type, Parameter returnValue, int accessor) {
        super();
        this.name = name;
        this.type = type;
        this.returnValue = returnValue;
        this.accessor = accessor;
    }
    
    public String getName() {
        return name;
    }
    
//    public Parameter[] getArgs() {
//        return args;
//    }
    
    public String getType() {
        return type;
    }
    
    public Parameter getReturnValue() {
        return returnValue;
    }
    
    public int getAccessor() {
        return accessor;
    }

}
