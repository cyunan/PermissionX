package com.permissionx.chen

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import java.security.Permissions

/**
 * Author by CYN, Date on 2022/1/4
 */
object PermissionX {
    private const val TAG = "InvisibleFragment"

    fun request(activity: FragmentActivity,
                vararg permissions: String,
                callback: PermissionCallback){
        val fragmentManger = activity.supportFragmentManager
        val existsFragment = fragmentManger.findFragmentByTag(TAG)
        val fragment = if (existsFragment != null){
            existsFragment as InvisibleFragment
        }else{
            val invisibleFragment = InvisibleFragment()
            fragmentManger.beginTransaction()
                .add(invisibleFragment, TAG)
                .commitNow()
            invisibleFragment
        }
        fragment.requestNow(callback, *permissions)
    }
}