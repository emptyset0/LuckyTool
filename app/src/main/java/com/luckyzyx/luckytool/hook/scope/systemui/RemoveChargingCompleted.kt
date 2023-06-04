package com.luckyzyx.luckytool.hook.scope.systemui

import com.highcapable.yukihookapi.hook.bean.VariousClass
import com.highcapable.yukihookapi.hook.entity.YukiBaseHooker

object RemoveChargingCompleted : YukiBaseHooker() {
    override fun onHook() {
        VariousClass(
            "com.coloros.systemui.notification.power.ColorosPowerNotificationWarnings", //A11
            "com.oplusos.systemui.notification.power.OplusPowerNotificationWarnings",
            "com.coloros.systemui.notification.power.ColorosPowerNotificationWarnings"
        ).hook {
            injectMember {
                method {
                    name = "showChargeErrorDialog"
                    paramCount = 1
                }
                beforeHook { if (args(0).int() == 7) resultNull() }
            }
        }
    }
}