package com.jayus.smallSpring.step07.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: h zk
 * @Description:
 * @Date: 2023/3/16 23:11
 * @Version: 1.0
 */
public interface Resource {

    InputStream getInputStream() throws IOException;

}