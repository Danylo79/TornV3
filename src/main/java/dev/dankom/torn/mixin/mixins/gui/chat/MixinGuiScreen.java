package dev.dankom.torn.mixin.mixins.gui.chat;

import dev.dankom.torn.Torn;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiScreen.class)
@SideOnly(Side.CLIENT)
public class MixinGuiScreen {
    @Shadow
    public Minecraft mc;

    @Inject(method = "sendChatMessage(Ljava/lang/String;Z)V", at = @At("HEAD"), cancellable = true)
    private void onChat(String msg, boolean addToChat, CallbackInfo ci) {
        if (msg.startsWith(".") && msg.length() > 1) {
            if (Torn.getCommandManager().executeCommand(msg)) {
                this.mc.ingameGUI.getChatGUI().addToSentMessages(msg);
            }

            ci.cancel();
        }
    }
}
