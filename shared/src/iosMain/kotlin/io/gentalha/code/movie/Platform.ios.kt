package io.gentalha.code.movie

import org.koin.dsl.module
import platform.Foundation.NSBundle.Companion.mainBundle
import platform.Foundation.NSLocale
import platform.Foundation.currentLocale
import platform.Foundation.languageCode
import platform.UIKit.UIDevice

class IOSPlatform : IPlatform {
    override val name: String = UIDevice.currentDevice.systemName()
    override val version: String get() = UIDevice.currentDevice.systemVersion
    override val appVersion: String
        get() = mainBundle.infoDictionary?.get("CFBundleVersion") as String
    override val language: String
        get() = NSLocale.currentLocale.languageCode
}

actual fun getPlatform(): IPlatform = IOSPlatform()

actual fun platformModule() = module {}