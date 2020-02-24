package com.api;

import com.weaverboot.frame.ioc.anno.classAnno.WeaAutoTransParam;
import com.weaverboot.frame.ioc.anno.parameterAnno.WeaParamBean;
import com.weaverboot.tools.baseTools.BaseTools;
import com.weaverboot.tools.classTools.ReflectTools;
import com.weaverboot.tools.frameTools.basedao.DataORMTools;
import com.weaverboot.tools.logTools.LogTools;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

@Provider
public class WeaParamBeanTransProvider implements MessageBodyReader<Object> {

    @Context
    private HttpServletRequest request;

    @Context
    private HttpServletResponse response;

    @Override
    public boolean isReadable(Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType) {

        for (int i = 0; i < annotations.length; i++) {

            if (annotations[i] instanceof WeaParamBean){

                return true;

            }

        }

        return false;
    }

    @Override
    public Object readFrom(Class<Object> aClass, Type type, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> multivaluedMap, InputStream inputStream) throws IOException, WebApplicationException {

        Class clazz = (Class) type;

        if (request == null && response == null){

            return null;

        }

        Map<String,Object> paramMap = request.getParameterMap();

        try {

            Object model = clazz.newInstance();

            Field[] fields = clazz.getDeclaredFields();

            for (Field field : fields
            ) {

                String name = field.getName();

                if (paramMap.get(name) != null){

                    Method method = clazz.getMethod("set" + BaseTools.toUpperCaseFirstOne(name),field.getType());

                    if (method != null) {

                        method.invoke(model, transType(paramMap.get(name),field.getType()));

                    }

                }

            }

            return model;

        } catch (Exception e) {

            LogTools.error("传输字段注入bean中出现错误，原因为:" + e.getMessage());

            return null;

        }

    }

    private <T>T transType(Object o,Class<T> tClass){

        if (o instanceof String[]){

            String[] resultStrings = (String[]) o;

            String resultString = resultStrings[resultStrings.length - 1];

            return DataORMTools.tarnsData(resultString,tClass);

        } else {

            LogTools.error("存储对象失败，request数据类型不为String数组");

            return null;

        }

    }

}
