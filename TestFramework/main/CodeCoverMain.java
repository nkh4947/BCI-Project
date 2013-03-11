package org.tizen.common.util.asm.main;

import java.lang.reflect.Method;

import org.tizen.common.util.asm.classloader.CodeCoverageClassLoader;

public class CodeCoverMain {

    /**
     * @param args
     * @throws Exception 
     * @throws SecurityException 
     */
    public static void main(String[] args) throws Exception {
        CodeCoverageClassLoader classLoader = new CodeCoverageClassLoader(ClassLoader.getSystemClassLoader());
       
        
        
        Class<?> mainClass = null;
        mainClass = classLoader.loadClass("org.tizen.common.util.asm.main.Main");
        Method method = mainClass.getMethod("main", String[].class);
        method.invoke(null, (Object)args);
        
    }

}
