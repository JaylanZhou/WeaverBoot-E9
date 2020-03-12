package com.weaverboot.weaResultMsg.inte;

import weaver.hrm.User;

public abstract class AbstractWeaGeneralResultMsg implements WeaResultMsg {

    private boolean api_status;

    private boolean hasRight;

    @Override
    public boolean isApi_status() {
        return api_status;
    }

    @Override
    public void setApi_status(boolean api_status) {
        this.api_status = api_status;
    }

    @Override
    public boolean isHasRight() {
        return hasRight;
    }

    @Override
    public void setHasRight(boolean hasRight) {
        this.hasRight = hasRight;
    }

    @Override
    public void checkRight(User user){

        if (user == null){

            this.hasRight = false;

        } else {

            this.hasRight = true;

        }

    }

}
