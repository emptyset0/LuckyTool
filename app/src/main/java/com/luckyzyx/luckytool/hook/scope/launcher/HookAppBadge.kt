package com.luckyzyx.luckytool.hook.scope.launcher

import com.highcapable.yukihookapi.hook.entity.YukiBaseHooker
import com.highcapable.yukihookapi.hook.factory.field
import com.highcapable.yukihookapi.hook.factory.method
import com.luckyzyx.luckytool.utils.A14
import com.luckyzyx.luckytool.utils.ModulePrefs
import com.luckyzyx.luckytool.utils.SDK

object HookAppBadge : YukiBaseHooker() {
    override fun onHook() {
        if (SDK >= A14) loadHooker(AppBadge) else loadHooker(AppBadgeC13)
    }

    object AppBadge : YukiBaseHooker() {
        override fun onHook() {
            val isShortcut = prefs(ModulePrefs).getBoolean("remove_app_shortcut_badge", false)
            val isWork = prefs(ModulePrefs).getBoolean("remove_app_work_badge", false)
            val isClone = prefs(ModulePrefs).getBoolean("remove_app_clone_badge", false)

            //Source BitmapInfo
            "com.android.launcher3.icons.BitmapInfo".toClass().apply {
                method { name = "applyFlags";paramCount = 3 }.hook {
                    before {
                        val drawableCreationFlags = args().last().int()
                        val badgeInfo = field { name = "badgeInfo" }.get(instance).any()
                        val flag = field { name = "flags" }.get(instance).int()
                        if ((drawableCreationFlags and 2) == 0) {
                            if (badgeInfo != null) {
                                if (isShortcut) resultNull()
                            } else if ((flag and 2) != 0) {
                                //ic_instant_app_badge
                                //resultNull()
                            } else if ((flag and 4) != 0) {
                                //ic_oplus_clone_app_badge
                                if (isClone) resultNull()
                            } else if ((flag and 1) != 0) {
                                //ic_work_app_badge
                                if (isWork) resultNull()
                            } else if ((flag and 4) != 0) {
                                //ic_clone_app_badge
                                if (isClone) resultNull()
                            }
                        }
                    }
                }
            }
        }
    }

    object AppBadgeC13 : YukiBaseHooker() {
        override fun onHook() {
            val isShortcut = prefs(ModulePrefs).getBoolean("remove_app_shortcut_badge", false)
            val isWork = prefs(ModulePrefs).getBoolean("remove_app_work_badge", false)
            val isClone = prefs(ModulePrefs).getBoolean("remove_app_clone_badge", false)

            //Source BitmapInfo
            "com.android.launcher3.icons.BitmapInfo".toClass().apply {
                method { name = "applyFlags";paramCount = 3 }.hook {
                    before {
                        val drawableCreationFlags = args().last().int()
                        val badgeInfo = field { name = "badgeInfo" }.get(instance).any()
                        val flag = field { name = "flags" }.get(instance).int()
                        if ((drawableCreationFlags and 2) == 0) {
                            if (badgeInfo != null) {
                                if (isShortcut) resultNull()
                            } else if ((flag and 2) != 0) {
                                //ic_instant_app_badge
                                //resultNull()
                            } else if ((flag and 1) != 0) {
                                //ic_work_app_badge
                                if (isWork) resultNull()
                            } else if ((flag and 4) != 0) {
                                //ic_oplus_clone_app_badge
                                if (isClone) resultNull()
                            }
                        }
                    }
                }
            }
        }
    }
}