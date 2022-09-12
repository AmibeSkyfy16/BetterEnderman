package ch.skyfy.customendermanbehavior


import ch.skyfy.customendermanbehavior.config.Configs
import ch.skyfy.customendermanbehavior.utils.setupConfigDirectory
import ch.skyfy.tomlconfiglib.ConfigManager
import net.fabricmc.api.ModInitializer
import net.fabricmc.loader.api.FabricLoader
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.nio.file.Path

class CustomEndermanBehaviorMod : ModInitializer {

    companion object {
        private const val MOD_ID: String = "customendermanbehavior"
        val CONFIG_DIRECTORY: Path = FabricLoader.getInstance().configDir.resolve(MOD_ID)
        val LOGGER: Logger = LogManager.getLogger(CustomEndermanBehaviorMod::class.java)
    }

    init {
        setupConfigDirectory()
        ConfigManager.loadConfigs(arrayOf(Configs.javaClass))
    }

    override fun onInitialize() {}

}