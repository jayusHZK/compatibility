package com.jayus.smallSpring.step14.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author : h zk
 * @date : 2023/6/20 17:34
 * @description :
 **/
public interface Resource {

    InputStream getInputStream() throws IOException;

}
