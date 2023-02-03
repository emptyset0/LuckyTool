package com.luckyzyx.luckytool.hook.hooker

import com.highcapable.yukihookapi.hook.entity.YukiBaseHooker
import com.luckyzyx.luckytool.hook.scope.oplusgames.*
import com.luckyzyx.luckytool.utils.tools.ModulePrefs

object HookOplusGames : YukiBaseHooker() {
    override fun onHook() {

        loadHooker(CloudConditionFeature)

        //游戏滤镜-->Root检测
        if (prefs(ModulePrefs).getBoolean("remove_root_check", false)) {
            loadHooker(RemoveRootCheck)
        }
        //简洁页面
        if (prefs(ModulePrefs).getBoolean("remove_startup_animation", false)) {
            loadHooker(RemoveStartupAnimation)
        }
        //启用开发者选项
        if (prefs(ModulePrefs).getBoolean("enable_developer_page", false)) {
            loadHooker(EnableDeveloperPage)
        }
        //启用赛事支持模式
        if (prefs(ModulePrefs).getBoolean("enable_support_competition_mode", false)) {
            loadHooker(EnableSupportCompetitionMode)
        }

        //network_speed_vip -> oppo_acc

        //游戏变声VIP (作废)
//        findClass("com.oplus.games.account.bean.VipInfoBean.VipInfosDTO").hook {
//            injectMember {
//                method { name = "getExpireTime" }
//                replaceTo("9999-9999-9999")
//            }
//            injectMember {
//                method { name = "getExpiredVip" }
//                replaceToFalse()
//            }
//            injectMember {
//                method { name = "getSign" }
//                replaceToTrue()
//            }
//            injectMember {
//                method { name = "getVip" }
//                replaceToTrue()
//            }
//        }
    }
}