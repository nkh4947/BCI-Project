package org.tizen.common.util.asm.model;

public class StaticParameter extends Parameter {

    private String FQCN;
    private String name;
    
    public StaticParameter(String type, String FQCN, String name) {
        super(type);
        this.FQCN = FQCN;
        this.name = name;
    }
    
    public String getFQCN() {
        return FQCN;
    }
    
    public String getName() {
        return name;
    }

}
