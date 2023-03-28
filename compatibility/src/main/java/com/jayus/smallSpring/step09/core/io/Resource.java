package com.jayus.smallSpring.step09.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author : h zk
 * @date : 2023/3/28 9:57
 * @description :
 **/
public interface Resource {

    InputStream getInputStream() throws IOException;

}
