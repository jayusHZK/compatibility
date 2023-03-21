package com.jayus.smallSpring.step08.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: h zk
 * @Description:
 * @Date: 2023/3/21 22:58
 * @Version: 1.0
 */
public interface Resource {

    InputStream getInputStream() throws IOException;

}