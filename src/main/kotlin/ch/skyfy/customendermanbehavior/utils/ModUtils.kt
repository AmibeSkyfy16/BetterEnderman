package ch.skyfy.customendermanbehavior.utils

import ch.skyfy.customendermanbehavior.CustomEndermanBehaviorMod.Companion.CONFIG_DIRECTORY
import ch.skyfy.customendermanbehavior.CustomEndermanBehaviorMod.Companion.LOGGER
import kotlin.io.path.createDirectory
import kotlin.io.path.exists

fun setupConfigDirectory(){
    try {
        if(!CONFIG_DIRECTORY.exists()) CONFIG_DIRECTORY.createDirectory()
    } catch (e: java.lang.Exception) {
        LOGGER.fatal("An exception occurred. Could not create the root folder that should contain the configuration files")
        throw RuntimeException(e)
    }
}