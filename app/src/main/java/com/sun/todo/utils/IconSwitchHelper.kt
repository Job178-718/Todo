package com.sun.todo.utils

import android.content.ComponentName
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.drawable.Icon
import com.sun.todo.view.Launch1Activity
import com.sun.todo.view.Launch2Activity
import com.sun.todo.view.MainActivity

class IconSwitchHelper(context: Context) {

    var context:Context
    init {
        this.context = context
    }
    val pk:PackageManager = context.packageManager
    val iconList = arrayListOf(
        ComponentName(context,Launch1Activity::class.java)
        ,ComponentName(context,Launch2Activity::class.java)
    )
    companion object{
        private const val icon1 = 1
        private const val icon2 = 2
        var hasEnabled = false
        var iconSwitchHelper:IconSwitchHelper? = null
        fun getInstance(context: Context):IconSwitchHelper{
            return iconSwitchHelper?: IconSwitchHelper(context)
        }

    }

    fun switchIcon(context: Context,icon:String){
        //作用切换Icon
        iconList.forEach {
            if(icon.equals(it.className.substringAfterLast("."))){
                hasEnabled = true
                pk.setComponentEnabledSetting(
                  it,
                  PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                  PackageManager.DONT_KILL_APP
              )
            }else{
                pk.setComponentEnabledSetting(
                    it,
                    PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                    PackageManager.DONT_KILL_APP
                )
            }
        }

        //主入口是否需要切换
        if (hasEnabled) {
            pk?.setComponentEnabledSetting(
                ComponentName(context, MainActivity::class.java.name),
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP
            )
        } else {
            pk?.setComponentEnabledSetting(
                ComponentName(context, MainActivity::class.java.name),
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP
            )
        }
    }

}


