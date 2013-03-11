package org.tizen.common.util.asm;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

public class CodeCoverageTransformer implements ClassFileTransformer{

    public static void premain(String args, Instrumentation inst) {
        System.out.println("Enter in premain");
        
        inst.addTransformer(new CodeCoverageTransformer());
    }
    
    @Override
    public byte[] transform(ClassLoader loader, String className,
            Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
            byte[] classfileBuffer) throws IllegalClassFormatException {
        
        if( loader != ClassLoader.getSystemClassLoader()) {
            return classfileBuffer;
        }
        
        ClassReader reader = new ClassReader(classfileBuffer);
        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_FRAMES + ClassWriter.COMPUTE_MAXS);
        CodeCoverageClassAdapter adapter = new CodeCoverageClassAdapter(Opcodes.ASM4, writer);
        reader.accept(adapter, ClassReader.SKIP_FRAMES);
        
        return writer.toByteArray();
    }

}
