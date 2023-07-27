package com.jayus.smallSpring.step17.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author : h zk
 * @date : 2023/7/27 11:48
 * @description :
 **/
public interface Resource {

    InputStream getInputStream() throws IOException;

}
