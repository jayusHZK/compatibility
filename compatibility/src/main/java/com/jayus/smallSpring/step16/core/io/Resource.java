package com.jayus.smallSpring.step16.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author : h zk
 * @date : 2023/7/14 10:20
 * @description :
 **/
public interface Resource {

    InputStream getInputStream() throws IOException;

}
