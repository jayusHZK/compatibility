package com.jayus.smallSpring.step06.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author : h zk
 * @date : 2023/3/14 16:42
 * @description :
 **/
public interface Resource {

    InputStream getInputStream() throws IOException;

}
