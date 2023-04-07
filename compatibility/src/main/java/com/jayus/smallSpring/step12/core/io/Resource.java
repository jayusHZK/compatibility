package com.jayus.smallSpring.step12.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author : h zk
 * @date : 2023/4/7 14:32
 * @description :
 **/
public interface Resource {

    InputStream getInputStream() throws IOException;

}
