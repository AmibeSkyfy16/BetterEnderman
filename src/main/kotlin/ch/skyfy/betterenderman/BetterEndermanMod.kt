package ch.skyfy.betterenderman


import ch.skyfy.betterenderman.config.Configs
import ch.skyfy.betterenderman.utils.setupConfigDirectory
import ch.skyfy.tomlconfiglib.ConfigManager
import net.fabricmc.api.ModInitializer
import net.fabricmc.loader.api.FabricLoader
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.nio.file.Path

class BetterEndermanMod : ModInitializer {

    companion object {
        private const val MOD_ID: String = "betterenderman"
        val CONFIG_DIRECTORY: Path = FabricLoader.getInstance().configDir.resolve(MOD_ID)
        val LOGGER: Logger = LogManager.getLogger(BetterEndermanMod::class.java)
    }

    init {
        setupConfigDirectory()
        ConfigManager.loadConfigs(arrayOf(Configs.javaClass))
    }

    override fun onInitialize() {}

}