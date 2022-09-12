package ch.skyfy.customendermanbehavior.config

import ch.skyfy.tomlconfiglib.Defaultable
import ch.skyfy.tomlconfiglib.Validatable
import kotlinx.serialization.Serializable
import net.peanuuutz.tomlkt.TomlComment

@Serializable
data class TeleportationDistance(val minimum: Int, val maximum: Int)

@Serializable
data class EndermanConfig(

    @TomlComment(
        """
        Description: Allow Enderman to pickup block.
        Default: false
    """
    )
    @JvmField val allowEndermanToPickupBlocks: Boolean,

    @TomlComment(
        """
        Description: Enderman will not attack you as long as you haven't attacked them
        Default: false -> mean it's like vanilla minecraft
    """
    )
    @JvmField val disableEndermanFromAttackingFirst: Boolean,

    @TomlComment(
        """
        Description: Enderman will no longer can teleport
        Default: false -> mean it's like vanilla minecraft
    """
    )
    @JvmField val disableEndermanFromTeleporting: Boolean,

    @TomlComment(
        """
        Description: Allow you to customize the distance when an Enderman is teleported randomly. You can set a minimum value and a maximum value (always greater than 0)
        Default: false -> mean it's like vanilla minecraft
    """
    )
    @JvmField val enableTeleportationDistance: Boolean,
    @JvmField val teleportationDistanceXMin: Int,
    @JvmField val teleportationDistanceXMax: Int,
    @JvmField val teleportationDistanceYMin: Int,
    @JvmField val teleportationDistanceYMax: Int,
    @JvmField val teleportationDistanceZMin: Int,
    @JvmField val teleportationDistanceZMax: Int,
) : Validatable {
    override fun validateImpl(errors: MutableList<String>) {
        val list: List<Pair<Int, Int>> = listOf(Pair(teleportationDistanceXMin, teleportationDistanceXMax), Pair(teleportationDistanceYMin, teleportationDistanceYMax), Pair(teleportationDistanceZMin, teleportationDistanceZMax))
        for (entry in list) {
            if (entry.first == entry.second)
                errors.add("ERROR IN CONFIG: minimum and maximum cannot be the same")

            if (entry.second < entry.first)
                errors.add("ERROR IN CONFIG: maximum cannot be lower that minimum")

            if (entry.first <= 0)
                errors.add("ERROR IN CONFIG: minimum must be greater than 0")
        }
    }
}

class DefaultEndermanConfig : Defaultable<EndermanConfig> {
    override fun getDefault() = EndermanConfig(
        allowEndermanToPickupBlocks = false,
        enableTeleportationDistance = false,
        disableEndermanFromAttackingFirst = false,
        disableEndermanFromTeleporting = false,
        teleportationDistanceXMin = 1, teleportationDistanceXMax = 10,
        teleportationDistanceYMin = 1, teleportationDistanceYMax = 10,
        teleportationDistanceZMin = 1, teleportationDistanceZMax = 10,
    )
}