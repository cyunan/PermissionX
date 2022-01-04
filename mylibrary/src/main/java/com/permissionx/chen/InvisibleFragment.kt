package com.permissionx.chen

import android.content.pm.PackageManager
import androidx.fragment.app.Fragment

/**
 * Author by CYN, Date on 2022/1/4
 */
typealias PermissionCallback = (Boolean, List<String>) -> Unit
class InvisibleFragment : Fragment(){
    private var callback: PermissionCallback? = null
    fun requestNow(cb: PermissionCallback, vararg permissions: String){
        callback = cb
        requestPermissions(permissions, 1)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == 1) {
            //用于记录所有被用户拒绝的权限
            val deniedList = ArrayList<String>()
            for ((index, result) in grantResults.withIndex()){
                if (result != PackageManager.PERMISSION_GRANTED){
                    deniedList.add(permissions[index])
                }
            }
            val allGranted = deniedList.isEmpty()
            callback?.let { it(allGranted, deniedList) }

        }
    }
}