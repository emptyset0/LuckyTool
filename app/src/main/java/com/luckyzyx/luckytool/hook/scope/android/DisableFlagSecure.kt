package com.luckyzyx.luckytool.hook.scope.android

import com.highcapable.yukihookapi.hook.entity.YukiBaseHooker
import com.luckyzyx.luckytool.utils.tools.ModulePrefs

object DisableFlagSecure : YukiBaseHooker() {
    override fun onHook() {
        //SOurce WindowState
        findClass("com.android.server.wm.WindowState").hook {
            injectMember {
                method {
                    name = "isSecureLocked"
                }
                if (prefs(ModulePrefs).getBoolean("disable_flag_secure", false)) replaceToFalse()
            }
        }
    }
}