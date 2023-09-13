package com.jayus.smallSpring.step19.core;

public abstract class GraalDetector {

    private static final boolean imageCode = (System.getProperty("org.graalvm.nativeimage.imagecode") != null);

    public static boolean isImageCode(){
        return imageCode;
    }

}
