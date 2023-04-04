package com.jayus.smallSpring.step11.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author : h zk
 * @date : 2023/4/4 17:18
 * @description :
 **/
public interface Resource {

    InputStream getInputStream() throws IOException;

}
