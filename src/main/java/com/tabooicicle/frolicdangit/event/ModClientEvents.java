package com.tabooicicle.frolicdangit.event;

import com.tabooicicle.frolicdangit.FrolicDangIt;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;

@EventBusSubscriber(modid = FrolicDangIt.MOD_ID, value = Dist.CLIENT)
public class ModClientEvents {

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Pre event) {
        // remove when adding actual client events (keybinds)
    }
}