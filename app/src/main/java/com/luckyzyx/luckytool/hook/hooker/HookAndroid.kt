package com.luckyzyx.luckytool.hook.hooker

import com.highcapable.yukihookapi.hook.entity.YukiBaseHooker
import com.luckyzyx.luckytool.hook.scope.android.ADBInstallConfirm
import com.luckyzyx.luckytool.hook.scope.android.AllowUntrustedTouch
import com.luckyzyx.luckytool.hook.scope.android.AppSplashScreen
import com.luckyzyx.luckytool.hook.scope.android.BatteryOptimizationWhitelist
import com.luckyzyx.luckytool.hook.scope.android.DarkModeService
import com.luckyzyx.luckytool.hook.scope.android.DisableDynamicRefreshRate
import com.luckyzyx.luckytool.hook.scope.android.DisableFlagSecure
import com.luckyzyx.luckytool.hook.scope.android.FixBatteryHealthDataDisplay
import com.luckyzyx.luckytool.hook.scope.android.ForceAllAppsSupportSplitScreen
import com.luckyzyx.luckytool.hook.scope.android.HookNotificationManager
import com.luckyzyx.luckytool.hook.scope.android.HookWindowManagerService
import com.luckyzyx.luckytool.hook.scope.android.MediaVolumeLevel
import com.luckyzyx.luckytool.hook.scope.android.MultiApp
import com.luckyzyx.luckytool.hook.scope.android.RemoveAccessDeviceLogDialog
import com.luckyzyx.luckytool.hook.scope.android.RemoveAppUninstallButtonBlackList
import com.luckyzyx.luckytool.hook.scope.android.RemovePasswordTimeoutVerification
import com.luckyzyx.luckytool.hook.scope.android.RemoveStatusBarTopNotification
import com.luckyzyx.luckytool.hook.scope.android.RemoveSystemScreenshotDelay
import com.luckyzyx.luckytool.hook.scope.android.RemoveVPNActiveNotification
import com.luckyzyx.luckytool.hook.scope.android.ScreenColorTemperatureRGBPalette
import com.luckyzyx.luckytool.hook.scope.android.ScrollToTopWhiteList
import com.luckyzyx.luckytool.hook.scope.android.SystemEnableVolumeKeyControlFlashlight
import com.luckyzyx.luckytool.hook.scope.android.ZoomWindow


object HookAndroid : YukiBaseHooker() {

    override fun onHook() {
        //移除状态栏上层警告
        loadHooker(RemoveStatusBarTopNotification)

        //移除VPN已激活通知
        loadHooker(RemoveVPNActiveNotification)

        //Hook NotificationManager
        loadHooker(HookNotificationManager)

        //Hook HookWindowManagerService
        loadHooker(HookWindowManagerService)

        //HookSystemProperties
        //loadHooker(HookSystemProperties)

        //音量阶数
        loadHooker(MediaVolumeLevel)

        //应用分身限制
        loadHooker(MultiApp)

        //USB安装确认
        loadHooker(ADBInstallConfirm)

        //移除72小时密码验证
        loadHooker(RemovePasswordTimeoutVerification)

        //移除系统截屏延迟
        loadHooker(RemoveSystemScreenshotDelay)

        //移除遮罩Splash Screen
        loadHooker(AppSplashScreen)

        //禁用FLAG_SECURE
        loadHooker(DisableFlagSecure)

        //允许不受信任的触摸
        loadHooker(AllowUntrustedTouch)

        //缩放窗口
        loadHooker(ZoomWindow)

        //暗色模式服务
        loadHooker(DarkModeService)

        //电池优化白名单
        loadHooker(BatteryOptimizationWhitelist)

        //允许APP回到顶部
        loadHooker(ScrollToTopWhiteList)

        //禁用访问设备日志对话框
        loadHooker(RemoveAccessDeviceLogDialog)

        //修复电池健康数据显示
        loadHooker(FixBatteryHealthDataDisplay)

        //禁用动态刷新率
        loadHooker(DisableDynamicRefreshRate)

        //启用音量键控制手电筒手势
        loadHooker(SystemEnableVolumeKeyControlFlashlight)

        //强制所有应用支持分屏
        loadHooker(ForceAllAppsSupportSplitScreen)

        //移除应用禁止卸载黑名单
        loadHooker(RemoveAppUninstallButtonBlackList)

        //屏幕色温RGB调色板
        loadHooker(ScreenColorTemperatureRGBPalette)

        //Source ScanPackageUtils
//        findClass("com.android.server.pm.ScanPackageUtils").hook {
//            injectMember {
//                method { name = "assertMinSignatureSchemeIsValid";paramCount(2) }
//                beforeHook {
//                    val clazz = "com.android.server.pm.pkg.parsing.ParsingPackageUtils"
//                        .toClassOrNull()
//                    val isSystemDir = clazz?.field { name = "PARSE_IS_SYSTEM_DIR";type(IntType) }
//                        ?.get()?.cast<Int>() ?: return@beforeHook
//                    val parseFlags = args().last().cast<Int>() ?: return@beforeHook
//                    if ((parseFlags and isSystemDir) != 0) resultNull()
//                }
//            }
//        }

        //Source ApkSignatureVerifier
//        findClass("android.util.apk.ApkSignatureVerifier").hook {
//            injectMember {
//                method { name = "unsafeGetCertsWithoutVerification";paramCount(3) }
//                beforeHook {
//                    val clazz = "android.content.pm.SigningDetails\$SignatureSchemeVersion"
//                        .toClassOrNull()
//                    val jar = clazz?.field { name = "JAR";type(IntType) }?.get()?.cast<Int>()
//                        ?: return@beforeHook
//                    args().last().set(jar)
//                }
//            }
//        }

        //com.oplus.vrr.OPlusRefreshRateService RefreshRateHandler
        //handleSinglePulseModeChange
        //com.android.server.display.OplusFeatureSinglePulseDinmming
        //display_pwm_settings_switch
//        findClass("com.android.server.wm.OplusRefreshRatePolicyImpl").hook {
//            injectMember {
//                constructor { paramCount(3) }
//                afterHook {
//                    field { name = "mSupportPWMSwitch" }.get(instance).setTrue()
//                }
//            }
//        }
//        findClass("com.android.server.display.OplusSmartBrightnessController").hook {
//            injectMember {
//                method { name = "updateSupportedFeatureState" }
//                afterHook {
//                    field { name = "mPWMBacklightSupport" }.get(instance).setTrue()
//                }
//            }
//        }

        //电源菜单显示延迟
        //loadHooker(ReducePowerMenuDisplayDelay)

        //OPLUS_FEATURE_POWERKEY_SHORT_PRESS_SHUTDOWN = "oplus.software.short_press_powerkey_shutdown";
        //OPLUS_FEATURE_POWERKEY_SHUTDOWN = "oplus.software.long_press_powerkey_shutdown";

        //Share
        //com.android.internal.app.ChooserActivity
        //201589207 查看应用详情 oplus_resolver_target_pin_detail
        //Source OplusResolverPagerAdapter

        //201850903 0x0c080017 oplus_ic_corp_icon_badge_multiapp
        //201850911 0x0c08001f oplus_ic_corp_badge_case_multiapp
        //201850912 0x0c080020 oplus_ic_corp_badge_no_background_multiapp
//        findClass("android.util.IconDrawableFactory").hook {
//            injectMember {
//                method {
//                    name = "getBadgedIcon"
//                    paramCount = 3
//                }
//                intercept()
//            }
//        }
    }
}
