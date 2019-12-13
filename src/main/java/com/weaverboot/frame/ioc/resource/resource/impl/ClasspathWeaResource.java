package com.weaverboot.frame.ioc.resource.resource.impl;

import com.weaverboot.frame.ioc.resource.resource.inte.AbstractWeaResource;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;

public class ClasspathWeaResource extends AbstractWeaResource {

    private String path;

    private Class clazz;

    private ClassLoader loader;

    public ClasspathWeaResource (String path,Class clazz,ClassLoader loader){

        this.path = path;

        this.clazz = clazz;

        this.loader = loader;

    }

    @Override
    public URL getURL() throws IOException {
        return resolveURL();
    }

    @Override
    public URI getURI() throws IOException {
        return null;
    }

    @Override
    public File getFile() throws IOException {
        return new File(getURL().getFile());
    }

    protected URL resolveURL() {
        if (this.clazz != null) {
            return this.clazz.getResource(this.path);
        }
        else if (this.loader != null) {
            return this.loader.getResource(this.path);
        }
        else {
            return ClassLoader.getSystemResource(this.path);
        }
    }

    @Override
    public long contentLength() throws IOException {
        return 0;
    }

    @Override
    public long lastModified() throws IOException {
        return 0;
    }

    @Override
    public String getFilename() {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }



}
