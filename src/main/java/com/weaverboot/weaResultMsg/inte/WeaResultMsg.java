package com.weaverboot.weaResultMsg.inte;

import weaver.hrm.User;

public interface WeaResultMsg {

    String resultToSerialization();

    void checkRight(User user);

    void setApi_status(boolean api_status);

    boolean isApi_status();

    void setHasRight(boolean hasRight);

    boolean isHasRight();

}
