package com.tabooicicle.frolicdangit.screen;

import com.tabooicicle.frolicdangit.FrolicDangIt;
import com.tabooicicle.frolicdangit.block.entity.PearlProcessorBlockEntity;
import com.tabooicicle.frolicdangit.screen.custom.PearlProcessorMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.network.IContainerFactory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(Registries.MENU, FrolicDangIt.MOD_ID);

    public static final DeferredHolder<MenuType<?>, MenuType<PearlProcessorMenu>> PEARL_PROCESSOR_MENU =
            MENUS.register("pearl_processor_menu", () -> IMenuTypeExtension.create((windowId, inv, data)
                    -> {
                BlockPos pos = data.readBlockPos();
                PearlProcessorBlockEntity entity = (PearlProcessorBlockEntity) inv.player.level().getBlockEntity(pos);
                return new PearlProcessorMenu(windowId, inv, entity, entity.getData());
            } ));


    private static <T extends AbstractContainerMenu>DeferredHolder<MenuType<?>, MenuType<T>> registerMenuType(String name, IContainerFactory<T> factory){
        return MENUS.register(name, () -> IMenuTypeExtension.create(factory));
    }

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
