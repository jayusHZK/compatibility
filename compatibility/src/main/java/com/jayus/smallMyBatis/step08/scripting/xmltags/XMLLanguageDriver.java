package com.jayus.smallMyBatis.step08.scripting.xmltags;

import com.jayus.smallMyBatis.step08.mapping.SqlSource;
import com.jayus.smallMyBatis.step08.scripting.LanguageDriver;
import com.jayus.smallMyBatis.step08.session.Configuration;
import org.dom4j.Element;

/**
 * XML 语言驱动器
 */
public class XMLLanguageDriver implements LanguageDriver {

    @Override
    public SqlSource createSqlSource(Configuration configuration, Element script, Class<?> parameterType) {
        XMLScriptBuilder builder = new XMLScriptBuilder(configuration, script, parameterType);
        return builder.parseScriptNode();
    }
}
