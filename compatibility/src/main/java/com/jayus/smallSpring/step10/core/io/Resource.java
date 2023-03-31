package com.jayus.smallSpring.step10.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author : h zk
 * @date : 2023/3/31 11:40
 * @description :
 **/
public interface Resource {

    InputStream getInputStream() throws IOException;

}
