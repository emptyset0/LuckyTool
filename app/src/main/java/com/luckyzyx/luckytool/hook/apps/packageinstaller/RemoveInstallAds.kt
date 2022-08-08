package com.luckyzyx.luckytool.hook.apps.packageinstaller

import android.view.View
import android.widget.LinearLayout
import android.widget.ScrollView
import com.highcapable.yukihookapi.hook.entity.YukiBaseHooker
import com.highcapable.yukihookapi.hook.log.loggerD
import com.highcapable.yukihookapi.hook.type.android.MessageClass

class RemoveInstallAds : YukiBaseHooker() {
    private val suggestLayout = arrayOfNulls<LinearLayout>(3)
    private val suggestLayoutAScrollView = arrayOfNulls<ScrollView>(1)
    override fun onHook() {
        //Source InstallAppProgress
        findClass("com.android.packageinstaller.oplus.InstallAppProgress").hook {
            injectMember {
                method {
                    name = "initView"
                }
                afterHook {
                    suggestLayout[0] = field { name = "mSuggestLayoutA" }.get(instance).self as LinearLayout
                    suggestLayout[1] = field { name = "mSuggestLayoutB" }.get(instance).self as LinearLayout
                    suggestLayout[2] = field { name = "mSuggestLayoutC" }.get(instance).self as LinearLayout
                    suggestLayoutAScrollView[0] = field { name = "mSuggestLayoutAScrollView" }.get(instance).self as ScrollView
                }
            }.onNoSuchMemberFailure {
                loggerD(msg = "NoSuchMember->InstallAppProgress->initView")
            }
        }.onHookClassNotFoundFailure {
            loggerD(msg = "ClassNotFound->InstallAppProgress")
        }
        //Source InstallAppProgress
        findClass("com.android.packageinstaller.oplus.InstallAppProgress$1").hook {
            injectMember {
                method {
                    name = "handleMessage"
                    param(MessageClass)
                }
                afterHook {
                    suggestLayout[0]?.visibility = View.GONE
                    suggestLayout[1]?.visibility = View.GONE
                    suggestLayout[2]?.visibility = View.GONE
                    suggestLayoutAScrollView[0]?.visibility = View.GONE
                }
            }.onNoSuchMemberFailure {
                loggerD(msg = "NoSuchMember->InstallAppProgress$1->handleMessage")
            }
        }.onHookClassNotFoundFailure {
            loggerD(msg = "ClassNotFound->InstallAppProgress\$1")
        }
    }
}