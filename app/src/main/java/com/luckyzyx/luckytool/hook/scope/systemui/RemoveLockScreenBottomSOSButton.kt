package com.luckyzyx.luckytool.hook.scope.systemui

import android.widget.Button
import androidx.core.view.isVisible
import com.highcapable.yukihookapi.hook.entity.YukiBaseHooker

object RemoveLockScreenBottomSOSButton : YukiBaseHooker() {
    override fun onHook() {
        //Source OplusEmergencyButtonControllExImpl
        findClass("com.oplus.systemui.keyguard.OplusEmergencyButtonControllExImpl").hook {
            injectMember {
                method {
                    name = "updateClickState"
                    paramCount = 1
                }
                afterHook {
                    field { name = "mEmergencyButton" }.get(instance).cast<Button>()?.isVisible = false
                }
            }
        }
    }
}