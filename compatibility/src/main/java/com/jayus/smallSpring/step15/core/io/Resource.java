package com.jayus.smallSpring.step15.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author : h zk
 * @date : 2023/7/6 14:04
 * @description :
 **/
public interface Resource {

    InputStream getInputStream() throws IOException;

}
