package com.luckyzyx.luckytool.hook.scope.android

import android.util.ArrayMap
import android.util.ArraySet
import com.highcapable.yukihookapi.hook.entity.YukiBaseHooker
import com.highcapable.yukihookapi.hook.factory.current
import com.luckyzyx.luckytool.utils.ModulePrefs

object DarkModeService : YukiBaseHooker() {
    override fun onHook() {
        var isEnable = prefs(ModulePrefs).getBoolean("dark_mode_list_enable", false)
        dataChannel.wait<Boolean>("dark_mode_list_enable") { isEnable = it }
        var supportlistSet = prefs(ModulePrefs).getStringSet("dark_mode_support_list", ArraySet())
        dataChannel.wait<Set<String>>("dark_mode_support_list") { supportlistSet = it }
        //Source OplusDarkModeData
        val darkModeData = "com.oplus.darkmode.OplusDarkModeData".toClass()
        //Source OplusDarkModeServiceManager
        findClass("com.android.server.OplusDarkModeServiceManager").hook {
            injectMember {
                method { name = "updateList";paramCount = 1 }
                afterHook {
                    if (!isEnable) return@afterHook
                    val supportListMap = ArrayMap<String, Int>()
                    supportlistSet.forEach {
                        if (it.contains("|")) {
                            val arr = it.split("|").toMutableList()
                            if (arr.size < 2 || arr[1].isBlank()) arr[1] = (0).toString()
                            supportListMap[arr[0]] = arr[1].toInt()
                        } else supportListMap[it] = 0
                    }
                    val dataMap = ArrayMap<String, Any>()
                    supportListMap.forEach {
                        if (it.value == 0) dataMap[it.key] = darkModeData.newInstance()
                        else dataMap[it.key] = darkModeData.newInstance().current {
                            field { name = "mCurType" }.set(it.value)
                        }
                    }
                    field { name = "mRusAppMap" }.get(instance).set(dataMap.toMap())
//                    field { name = "mOpenApp" }.get(instance).set(supportlist)
//                    field { name = "mClickApp" }.get(instance).set(supportlist)
                }
            }
        }
    }
}