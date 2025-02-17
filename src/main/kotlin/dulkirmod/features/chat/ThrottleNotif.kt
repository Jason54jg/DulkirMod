package dulkirmod.features.chat

import dulkirmod.DulkirMod
import dulkirmod.config.Config
import dulkirmod.utils.TabListUtils
import dulkirmod.utils.TextUtils
import net.minecraftforge.client.event.ClientChatReceivedEvent

object ThrottleNotif {
    private var lastThrottle: Long = 0
    fun handle(event: ClientChatReceivedEvent, unformatted: String) {
        if (unformatted == "This menu has been throttled! Please slow down..." && DulkirMod.config.throttleNotifier
            && TabListUtils.isInDungeons
        ) {
            event.isCanceled = true
	        if (!Config.throttleNotifierSpam && System.currentTimeMillis() - lastThrottle > 8000) {
                TextUtils.sendPartyChatMessage(DulkirMod.config.customMessage)
            } else if (Config.throttleNotifierSpam) {
                TextUtils.sendPartyChatMessage(DulkirMod.config.customMessage)
            }
            lastThrottle = System.currentTimeMillis()
        }
    }
}