package com.weaverboot.frame.ioc.resource.resource.inte;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.channels.ReadableByteChannel;

public interface WeaResource {

    /**
     * 资源是否可读
     */
    boolean isReadable();

    /**
     * 资源所代表的句柄是否被一个 stream 打开了
     */
    boolean isOpen();

    /**
     * 是否为 File
     */
    boolean isFile();

    /**
     * 返回资源的 URL 的句柄
     */
    URL getURL() throws IOException;

    /**
     * 返回资源的 URI 的句柄
     */
    URI getURI() throws IOException;

    /**
     * 返回资源的 File 的句柄
     */
    File getFile() throws IOException;

    /**
     * 资源内容的长度
     */
    long contentLength() throws IOException;

    /**
     * 资源最后的修改时间
     */
    long lastModified() throws IOException;

    /**
     * 资源的文件名
     */
    String getFilename();

    /**
     * 资源的描述
     */
    String getDescription();

}
