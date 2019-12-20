package com.weaverboot.http.dom4j;

import com.weaverboot.tools.logTools.LogTools;
import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import weaver.general.BaseBean;

import java.io.IOException;
import java.io.StringWriter;

@Deprecated
public class Dom4jTools{

    private Dom4jTools(){



    }

    public static String buildXML(Document document){

        OutputFormat format = OutputFormat.createCompactFormat();// createPrettyPrint() 层次格式化

        StringWriter writer = new StringWriter();

        XMLWriter output = new XMLWriter(writer, format);

        try {

            output.write(document);

            writer.close();

            output.close();

        } catch (IOException e) {

            BaseBean baseBean = new BaseBean();

            LogTools.writeLog("创建xml文档错误，原因为:" + e.getMessage());

            throw new RuntimeException(e.getMessage());

        }

        String result = writer.toString();

        return result;

    }

}
