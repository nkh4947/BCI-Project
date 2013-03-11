package org.tizen.common.util.asm;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class CodeCoverageClassAdapter extends ClassVisitor {
    
public CodeCoverageClassAdapter(int api, ClassVisitor cv) {
        super(api, cv);
    }
    
    @Override
    public void visit(int version, int access, String name, String signature,
            String superName, String[] interfaces) {
        // TODO Auto-generated method stub
        super.visit(Opcodes.V1_6, access, name, signature, superName, interfaces);
    }
    
    @Override
    public MethodVisitor visitMethod(int access, String name, String desc,
            String signature, String[] exceptions) {
        MethodVisitor _mv = super.visitMethod(access, name, desc, signature, exceptions);
        if(name.equals("<init>")) {
            return _mv;
        }
        return new CodeCoverageMethodAdapter(Opcodes.ASM4, _mv, name);
    }

}
