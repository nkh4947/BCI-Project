package org.tizen.common.util.asm.util;

import java.lang.reflect.Method;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

public class ASMUtil {
    
    public static final int ACC_PUBLIC = Opcodes.ACC_PUBLIC;
    public static final int COMPUTE_FRAMES = ClassWriter.COMPUTE_FRAMES;
    public static final int ACC_SUPER = Opcodes.ACC_SUPER;
    public static final int COMPUTE_MAXS = ClassWriter.COMPUTE_MAXS;
    
    
    
    
    
    public static String getASMFQCN(Class<?> c) {
        return c.getCanonicalName().replace('.', '/');
    }
    
    public static String getASMParamType(Class<?> c) {
        return "L" + getASMFQCN(c) + ";";
    }
    
    public static void defineClass(String name, byte[] bytes) throws Exception {
        ClassLoader clsLoader = ASMUtil.class.getClassLoader();
        
        Class<?> loaderCls = ClassLoader.class;
        Class<?>[] defineClassParam = { String.class, byte[].class, int.class, int.class };
        Method defineClassMethod = loaderCls.getDeclaredMethod("defineClass", defineClassParam);
        
        Class<?>[] definePackageParam = {};
//        Method definePackageMethod = loadercls
        
        defineClassMethod.setAccessible(true);
        
        defineClassMethod.invoke(clsLoader, name, bytes, 0, bytes.length);
    }
    
//    public static String getASMMethodType(Class<?>[] args, Class<?> rv) {
//        String type = "(";
//        if(args != null || args.length > 0) {
//            for(Class<?> cls: args) {
//                type += getASMParamType(cls);
//            } 
//        }
//        type += ")";
//        
//        if(rv != null) {
//            type += getASMParamType(c)
//        }
//    }
    
    
//"(Ljava/lang/String;Ljava/lang/Integer;)Ljava/lang/Integer;");
//    ()V
    
//    public static String getASM
}
