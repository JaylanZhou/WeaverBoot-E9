package com.weaverboot.tools.serializableTools;

import java.io.*;

/**
 *
 * 序列化工具类
 *
 * @Author : Jaylan Zhou
 *
 */

public class SerializableTools {

    private SerializableTools(){



    }

    /**
     *
     * 序列化对象
     *
     * @param object 需要序列化的对象
     * @return
     * @throws IOException
     */

    public static byte[] serializableObject(Object object) throws IOException {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);

        objectOutputStream.writeObject(object);

        byte[] result = byteArrayOutputStream.toByteArray();

        objectOutputStream.close();

        byteArrayOutputStream.close();

        return result;

    }

    /**
     *
     * 反序列化对象
     *
     * @param bytes 经过序列化后的对象数组
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */

    public static Object unserializableObject(byte[] bytes) throws IOException, ClassNotFoundException {

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);

        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);

        Object object = objectInputStream.readObject();

        byteArrayInputStream.close();

        objectInputStream.close();

        return object;

    }

}
