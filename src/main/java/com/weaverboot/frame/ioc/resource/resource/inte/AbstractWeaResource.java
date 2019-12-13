package com.weaverboot.frame.ioc.resource.resource.inte;

public abstract class AbstractWeaResource implements WeaResource {

    /**
     * 资源是否可读
     */
    public boolean isReadable(){

        return true;

    }

    /**
     * 资源所代表的句柄是否被一个 stream 打开了
     */
    public boolean isOpen(){

        return false;

    }

    /**
     * 是否为 File
     */
    public boolean isFile(){

        return true;

    }

}
