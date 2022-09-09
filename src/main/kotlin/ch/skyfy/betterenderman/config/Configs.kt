package ch.skyfy.betterenderman.config

import ch.skyfy.betterenderman.BetterEndermanMod.Companion.CONFIG_DIRECTORY
import ch.skyfy.tomlconfiglib.ConfigData

object Configs {
    val ENDERMAN_CONFIG = ConfigData<EndermanConfig, DefaultEndermanConfig>(CONFIG_DIRECTORY.resolve("enderman-config.toml"))
}