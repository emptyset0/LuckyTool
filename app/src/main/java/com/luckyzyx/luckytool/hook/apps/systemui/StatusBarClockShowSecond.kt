package com.luckyzyx.luckytool.hook.apps.systemui

import com.highcapable.yukihookapi.hook.entity.YukiBaseHooker
import com.highcapable.yukihookapi.hook.type.java.BooleanType

class StatusBarClockShowSecond : YukiBaseHooker() {
    override fun onHook() {
        //Source Clock
        findClass("com.android.systemui.statusbar.policy.Clock").hook {
            injectMember {
                method {
                    name = "setShowSecondsAndUpdate"
                    param(BooleanType)
                }
                beforeHook {
                    args(0).setTrue()
                }
            }
        }
    }
}