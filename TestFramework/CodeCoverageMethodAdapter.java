package org.tizen.common.util.asm;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import static org.objectweb.asm.Opcodes.*;

public class CodeCoverageMethodAdapter extends MethodVisitor {

    private String methodName;
    
    public CodeCoverageMethodAdapter(int api, MethodVisitor mv, String name) {
        super(api, mv);
        this.methodName = name;
    }
    
    @Override
    public void visitLineNumber(int line, Label start) {
        super.visitLineNumber(line, start);
        super.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        super.visitLdcInsn("Method Name: " + methodName + "/ Line Number:" + line);
        super.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V");
    }

}
