package com.luckyzyx.luckytool;

interface IFiveGController {
    boolean compatibilityCheck(int subId);
    boolean getFivegEnabled(int subId);
    void setFivegEnabled(int subId, boolean enabled);
}