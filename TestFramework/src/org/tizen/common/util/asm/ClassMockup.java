package org.tizen.common.util.asm;

import java.util.ArrayList;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Opcodes;
import org.tizen.common.util.asm.model.Method;

public class ClassMockup extends ClassGenerator {
    
    private ClassReader cr;
    private ArrayList<Method> preserveMethodList = new ArrayList<Method>();
    
    public ClassMockup(int flags, String FQCN, int accessor, String superName) throws Exception {
        super(flags, FQCN, accessor, superName);
        
//        ClassVisitor ca = new ClassMockupAdapter(V1_6, this.getCw(), this);
        cr = new ClassReader(FQCN);
    }
    
    public void clearPreserveMethodList() {
        preserveMethodList.clear();
    }
    
    public void addPreserveMethodList(Method method) {
        this.preserveMethodList.add(method);
    }
    
    public ArrayList<Method> getPreserveMethodList() {
        return preserveMethodList;
    }
    
    @Override
    public byte[] generateClass() {
        ClassMockupAdapter ca = new ClassMockupAdapter(Opcodes.ASM4, this.getCw(), this);
        
        cr.accept(ca , ClassReader.SKIP_FRAMES);
        
        return this.getCw().toByteArray();
        
    }

}
