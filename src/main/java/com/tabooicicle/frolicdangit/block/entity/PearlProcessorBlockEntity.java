package com.tabooicicle.frolicdangit.block.entity;

import com.tabooicicle.frolicdangit.item.ModItems;
import com.tabooicicle.frolicdangit.screen.custom.PearlProcessorMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;

public class PearlProcessorBlockEntity extends BlockEntity implements MenuProvider {

    public final ItemStackHandler itemHandler = new ItemStackHandler(5) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if (!level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }
    };

    private static final int INPUT_ITEM_SLOT = 0;
    private static final int INPUT_PEARL_SLOT = 1;
    private static final int INPUT_FUEL_SLOT = 2;
    private static final int INPUT_BOTTLE_SLOT = 3;
    private static final int OUTPUT_PEARL_SLOT = 4;

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 72;

    public PearlProcessorBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.PEARL_PROCESSOR_BE.get(), pos, blockState);
        data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> PearlProcessorBlockEntity.this.progress;
                    case 1 -> PearlProcessorBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0:
                        PearlProcessorBlockEntity.this.progress = value;
                    case 1:
                        PearlProcessorBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.frolicdangitptone.pearl_processor");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int i, Inventory playerInventory, Player player) {
        return new PearlProcessorMenu(i, playerInventory, this, this.data);
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        pTag.put("inventory", itemHandler.serializeNBT(pRegistries));
        pTag.putInt("pearl_processer.progress", progress);
        pTag.putInt("pearl_processor.max_progress", maxProgress);

        super.saveAdditional(pTag, pRegistries);
    }

    @Override
    protected void loadAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.loadAdditional(pTag, pRegistries);

        itemHandler.deserializeNBT(pRegistries, pTag.getCompound("inverntory"));
        progress = pTag.getInt("pearl_processor.progress");
        maxProgress = pTag.getInt("pearl_processor.max_progress");
    }


    // LOGIC
    public void tick(Level level, BlockPos blockPos, BlockState blockState){
        if (hasRecipe()){
            increaseProcessingProgress();
            setChanged(level, blockPos, blockState);

            if(hasCraftingFinished()){
                processItems();
                resetProgress();
            }
        } else {
            resetProgress();
        }
    }

    private void processItems() {
        ItemStack output = new ItemStack(ModItems.PEARL_STAGE_4.get());

        itemHandler.extractItem(INPUT_PEARL_SLOT,1,false);
        itemHandler.extractItem(INPUT_FUEL_SLOT, 1,false);
        itemHandler.extractItem(INPUT_ITEM_SLOT,1,false);
        itemHandler.setStackInSlot(OUTPUT_PEARL_SLOT, new ItemStack(output.getItem(),
                itemHandler.getStackInSlot(OUTPUT_PEARL_SLOT).getCount() + output.getCount()));
    }


    private void resetProgress() {
        progress = 0;
        maxProgress = 72;
    }

    private boolean hasCraftingFinished() {
        return this.progress >= this.maxProgress;
    }

    private void increaseProcessingProgress() {
        progress++;
    }

    private boolean hasRecipe() {
        ItemStack output = new ItemStack(ModItems.PEARL_STAGE_3.get());
        return itemHandler.getStackInSlot(INPUT_ITEM_SLOT).is(ModItems.ELDER_EYE) &&
                itemHandler.getStackInSlot(INPUT_BOTTLE_SLOT).is(ModItems.GROUND_SHARD) &&
                itemHandler.getStackInSlot(INPUT_PEARL_SLOT).is(ModItems.PEARL_STAGE_3) &&
                canInsertAmountIntoOutputSlot(output.getCount()) && canInsertItemIntoOutputSlot(output);

    }

    private boolean canInsertItemIntoOutputSlot(ItemStack output) {
return itemHandler.getStackInSlot(OUTPUT_PEARL_SLOT).isEmpty() ||
        itemHandler.getStackInSlot(OUTPUT_PEARL_SLOT).getItem() == output.getItem();
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        int maxCount = itemHandler.getStackInSlot(OUTPUT_PEARL_SLOT).isEmpty() ? 16 : itemHandler.getStackInSlot(OUTPUT_PEARL_SLOT).getMaxStackSize();
        int currentCount = itemHandler.getStackInSlot(OUTPUT_PEARL_SLOT).getCount();

        return maxCount >= currentCount + count;
    }


    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider pRegistries) {
        return saveWithoutMetadata(pRegistries);
    }

    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }
}
