package com.weaverboot.weaResultMsg.inte;

import weaver.hrm.User;

public abstract class AbstractWeaComponentResultMsg implements WeaResultMsgInte {

    private boolean hasRight = false;

    private boolean api_status;

    private String api_errormsg;

    public boolean isHasRight() {
        return hasRight;
    }

    public void setHasRight(boolean hasRight) {
        this.hasRight = hasRight;
    }

    public boolean isApi_status() {
        return api_status;
    }

    public void setApi_status(boolean api_status) {
        this.api_status = api_status;
    }

    public String getApi_errormsg() {
        return api_errormsg;
    }

    public void setApi_errormsg(String api_errormsg) {
        this.api_errormsg = api_errormsg;
    }

    public void checkRight(User user){

        if (user == null){

            this.hasRight = false;

        } else {

            this.hasRight = true;

        }

    }

}
