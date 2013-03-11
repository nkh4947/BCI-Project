package org.tizen.common.util.asm;

import java.util.ArrayList;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.tizen.common.util.asm.model.Method;

public class ClassMockupAdapter extends ClassVisitor {

    private ArrayList<Method> preserveMethodList;
    private ClassMockup generator;
    boolean created = false;
    
    public ClassMockupAdapter(final int api, final ClassVisitor cv, ClassMockup clsMockup) {
        super(api, cv);
        
        this.preserveMethodList = clsMockup.getPreserveMethodList();
        this.generator = clsMockup;
    }
    
    @Override
    public void visit(int version, int access, String name, String signature,
            String superName, String[] interfaces) {
        super.visit(version, access, name, signature, "java/lang/Object", null);
    }
    
    @Override
    public MethodVisitor visitMethod(int access, String name, String desc,
            String signature, String[] exceptions) {
        
        
        if(!created) {
            generator.generateConstructor();
            generator.generateMethod();
            created = true;
        }
        
        for(Method method: preserveMethodList) {
            if(method.getName().equals(name)) {
                return super.visitMethod(access, name, desc, signature, exceptions);
            }
        }
        return null;
    }

}
