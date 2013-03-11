package org.tizen.common.util.asm.classloader;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

import org.tizen.common.util.asm.util.IOUtils;

public class DefineClassLoader2 extends ClassLoader{

    private URLClassLoader resourceLoader;
    
    public DefineClassLoader2(ClassLoader sysClassLoader) {
        this(((URLClassLoader) sysClassLoader).getURLs(), ((URLClassLoader) sysClassLoader).getParent());
    }
    
    public DefineClassLoader2(URL[] urls, ClassLoader parent) {
        super(parent);
//        this.urls = urls;
        resourceLoader = new URLClassLoader(urls);
    }
    
    public void defineClass(String name, byte[] bytes) throws Exception {
        if(findLoadedClass(name) == null) {
            defineClass(name, bytes, 0, bytes.length);
        }
    }
    
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        
        String path = name.replace('.', '/').concat(".class");
        InputStream is = resourceLoader.getResourceAsStream(path);
        
        if(is == null) {
            throw new ClassNotFoundException();
        }
        
        byte[] bytes = null;
        
        try {
            bytes = IOUtils.toByteArray(is);
        } catch (IOException e1) {
            throw new ClassNotFoundException();
        }
        
        return defineClass(name, bytes, 0, bytes.length);
    }

    public void invoke(Class<?> cls, String name) throws Exception {
        Class<?> mainClass = this.loadClass(cls.getCanonicalName());
        Method method = mainClass.getMethod(name);
        method.invoke(null);
    }

    public void setContextClassLoader() {
        Thread.currentThread().setContextClassLoader(this);
    }
}
