package ch.skyfy.customendermanbehavior.config

import ch.skyfy.customendermanbehavior.CustomEndermanBehaviorMod.Companion.CONFIG_DIRECTORY
import ch.skyfy.tomlconfiglib.ConfigData

object Configs {
    val ENDERMAN_CONFIG = ConfigData<EndermanConfig, DefaultEndermanConfig>(CONFIG_DIRECTORY.resolve("config.toml"))
}