package com.jayus.jvm.classLoaderTest;

import java.io.*;

public class MyClassLoader extends ClassLoader {

    String path;

    public MyClassLoader(String path) {
        this.path = path;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            this.path = this.path + name + ".class";
            File f = new File(path);
            InputStream in = new FileInputStream(f);
            byte[] bys = new byte[(int) f.length()];
            int len = 0;
            while ((len = in.read(bys)) != -1){}
            return defineClass(name,bys,0,bys.length);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        return super.findClass(name);
    }
}
