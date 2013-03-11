package org.tizen.common.util.asm;

import java.util.ArrayList;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import static org.objectweb.asm.Opcodes.*;
import org.tizen.common.util.asm.model.Method;
import org.tizen.common.util.asm.model.Parameter;
import org.tizen.common.util.asm.model.StaticParameter;

public class ClassGenerator {
    
    private ClassWriter cw;
    private ArrayList<Method> generateMethodList = new ArrayList<Method>();
    private String FQCN;
    private int accessor;
    private String superName;
    private final int _flags;
    
    public ClassGenerator(int flags, String FQCN, int accessor, String superName) {
        cw = new ClassWriter(flags);
        this._flags = flags;
        this.FQCN = FQCN;
        this.accessor = accessor;
        
        this.superName = superName == null ? "java/lang/Object" : superName;
    }
    
    public ClassWriter getCw() {
        return cw;
    }
    
    public void clearGeneratedMethod() {
        this.generateMethodList.clear();
    }
    
    public void addGeneratedMehtod(Method method) {
        this.generateMethodList.add(method);
    }
    
    public ArrayList<Method> getGenerateMethodList() {
        return generateMethodList;
    }
    
    public byte[] generateClass() {
        cw.visit(V1_6, this.accessor, this.FQCN, null, this.superName, null);
        cw.visitSource(null, null);
//        cw.visitOuterClass(null, null, null);
        
        generateConstructor();
        
        generateMethod();
        
        cw.visitEnd();
        return cw.toByteArray();
    }
    
    public void generateMethod() {
        for(Method method: generateMethodList) {
            MethodVisitor mv = cw.visitMethod(method.getAccessor(), method.getName(), method.getType(), null, null);
            generateMethod(mv, method);
        }
    }

    protected void generateMethod(MethodVisitor mv, Method method) {
        mv.visitCode();
        
        Parameter rv = method.getReturnValue();
        
        if(rv instanceof StaticParameter) {
            StaticParameter srv = (StaticParameter) rv;
            mv.visitFieldInsn(GETSTATIC, srv.getFQCN(), srv.getName(), srv.getType());
            mv.visitInsn(ARETURN);
            
            int maxFrame = 0;
            int maxLocal = 0;
            
            if(_flags != ClassWriter.COMPUTE_FRAMES) {
                maxFrame = getMaxFrame();
                maxLocal = getMaxLocal();
            }
            
            mv.visitMaxs(maxFrame, maxLocal);
            mv.visitEnd();
        }
    }

    private int getMaxFrame() {
        // TODO Auto-generated method stub
        return 0;
    }

    private int getMaxLocal() {
        // TODO Auto-generated method stub
        return 0;
    }

    public void generateConstructor() {
        
        MethodVisitor mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
        mv.visitCode();
        mv.visitVarInsn(ALOAD, 0);
        mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V");
        mv.visitInsn(RETURN);
        mv.visitMaxs(1, 1);
        mv.visitEnd();
        
    }
    

}
