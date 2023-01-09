package com.luckyzyx.luckytool.hook.scope.launcher

import android.widget.TextView
import com.highcapable.yukihookapi.hook.entity.YukiBaseHooker

object RemoveAppUpdateDot : YukiBaseHooker() {
    override fun onHook() {
        //Source OplusBubbleTextView
        findClass(name =  "com.android.launcher3.OplusBubbleTextView").hook {
            injectMember {
                method {
                    name = "applyLabel"
                    paramCount = 3
                }
                beforeHook {
                    //Source ItemInfo
                    val field = "com.android.launcher3.model.data.ItemInfo".toClass().getDeclaredField("title")
                    field.isAccessible = true
                    instance<TextView>().text = field[args[0]] as CharSequence
                    resultNull()
                }
            }
        }
    }
}
object RemoveAppUpdateDotV13 : YukiBaseHooker() {
    override fun onHook() {
        //Source BubbleTextView
        findClass(name =  "com.android.launcher3.BubbleTextView").hook {
            injectMember {
                method {
                    name = "applyLabel"
                    paramCount = 1
                }
                beforeHook {
                    //Source ItemInfo
                    val field = "com.android.launcher3.model.data.ItemInfo".toClass().getDeclaredField("title")
                    field.isAccessible = true
                    instance<TextView>().text = field[args[0]] as CharSequence
                    resultNull()
                }
            }
        }
    }
}