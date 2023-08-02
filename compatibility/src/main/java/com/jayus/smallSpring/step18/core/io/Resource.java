package com.jayus.smallSpring.step18.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author : h zk
 * @date : 2023/8/2 14:40
 * @description :
 **/
public interface Resource {

    InputStream getInputStream() throws IOException;

}
