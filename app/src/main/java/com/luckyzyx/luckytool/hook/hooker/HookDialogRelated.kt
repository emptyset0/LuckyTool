package com.luckyzyx.luckytool.hook.hooker

import com.highcapable.yukihookapi.hook.entity.YukiBaseHooker
import com.luckyzyx.luckytool.hook.scope.securepay.RemoveSecurePayFoundVirusDialog
import com.luckyzyx.luckytool.hook.scope.systemui.DisableDuplicateFloatingWindow
import com.luckyzyx.luckytool.hook.scope.systemui.DisableHeadphoneHighVolumeWarning
import com.luckyzyx.luckytool.hook.scope.systemui.RemoveLowBatteryDialogWarning
import com.luckyzyx.luckytool.hook.scope.systemui.RemoveUSBConnectDialog
import com.luckyzyx.luckytool.hook.scope.systemui.VolumeDialogWhiteBackground
import com.luckyzyx.luckytool.utils.A13
import com.luckyzyx.luckytool.utils.ModulePrefs
import com.luckyzyx.luckytool.utils.SDK

object HookDialogRelated : YukiBaseHooker() {
    override fun onHook() {
        if (packageName == "com.android.systemui") {
            //禁用复制悬浮窗
            if (prefs(ModulePrefs).getBoolean("disable_duplicate_floating_window", false)) {
                if (SDK >= A13) loadHooker(DisableDuplicateFloatingWindow)
            }
            //禁用耳机高音量警告
            if (prefs(ModulePrefs).getBoolean("disable_headphone_high_volume_warning", false)) {
                loadHooker(DisableHeadphoneHighVolumeWarning)
            }
            //移除低电量对话框警告
            if (prefs(ModulePrefs).getBoolean("remove_low_battery_dialog_warning", false)) {
                loadHooker(RemoveLowBatteryDialogWarning)
            }
            //移除USB连接对话框
            if (prefs(ModulePrefs).getBoolean("remove_usb_connect_dialog", false)) {
                loadHooker(RemoveUSBConnectDialog)
            }
            //音量对话框背景透明度
            loadHooker(VolumeDialogWhiteBackground)
        }

        if (packageName == "com.coloros.securepay") {
            //移除支付保护发现病毒对话框
            if (prefs(ModulePrefs).getBoolean("remove_secure_pay_found_virus_dialog", false)) {
                loadHooker(RemoveSecurePayFoundVirusDialog)
            }
        }
    }
}