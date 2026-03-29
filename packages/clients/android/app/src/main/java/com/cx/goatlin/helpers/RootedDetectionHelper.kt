package com.cx.goatlin.helpers

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import java.io.File

object RootedDetectionHelper {
    fun check(ctx: Context): Boolean{
        detectDeveloperBuild()
        detectOTACertificates()

        detectRootedAPKs(ctx)
        detectforSUBinaries()

        return false
    }

    private  fun detectDeveloperBuild():Boolean{
        val buildTags: String = Build.TAGS
        return buildTags.contains("test-keys")
    }

    private fun detectOTACertificates(): Boolean {
        val otaCerts: File = File("/etc/security/otacerts.zip")
        return  otaCerts.exists()
    }

    private fun detectRootedAPKs(ctx: Context): Boolean{
        val knownRootedAPKs: Array<String> = arrayOf(
                "com.noshufou.android.su",
                "com.thirdparty.superuser",
                "eu.chainfire.supersu",
                "com.koushikdutta.superuser",
                "com.zachspong.temprootremovejb",
                "com.ramdroid.appquarantine"
        )

        val pm: PackageManager = ctx.packageManager

        for(uri in knownRootedAPKs){
            try{
                pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES)
                return true
            } catch (e:PackageManager.NameNotFoundException){

            }
        }

        return false
    }

    private fun detectforSUBinaries(): Boolean{
        var suBinaries: Array<String> = arrayOf(
                "/system/bin/su",
                "/system/xbin/su",
                "/sbin/su",
                "/system/su",
                "/system/bin/.ext/.su",
                "/system/usr/we-need-root/su-backup",
                "/system/xbin/mu"
        )

        for(bin in suBinaries){
            if(File(bin).exists()){
                return true
            }
        }

        return false
    }
}

