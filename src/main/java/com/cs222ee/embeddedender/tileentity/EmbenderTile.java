package com.cs222ee.embeddedender.tileentity;

import com.cs222ee.embeddedender.block.ModBlocks;
import com.cs222ee.embeddedender.item.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/***
 * Taken from KaupenJoes's tutorial
 * Source: https://www.youtube.com/watch?v=4YgGqJgz2VY&ab_channel=TutorialsbyKaupenjoe
 ***/

// TODO: Create final functionality
public class EmbenderTile extends TileEntity {

    private final ItemStackHandler itemHandeler = createHandler();
    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandeler);

    public EmbenderTile(TileEntityType<?> p_i48289_1_) {
        super(p_i48289_1_);
    }

    public EmbenderTile() {
        this(ModTileEntities.EMBENDER_TILE.get());
    }

    // Defines tile entities behaviors
    private ItemStackHandler createHandler() {
        return new ItemStackHandler(2) {
            @Override
            protected void onContentsChanged(int slot) {
                markDirty();
            }

            // Make it so slot 0 can only hold ender eggs and slot 1 can only hold ender gem ore
            // Will change later for final functionality
            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                switch (slot){
                    case 0: return stack.getItem() == ModItems.ENDER_EGG.get();
                    case 1: return stack.getItem() == ModBlocks.ENDER_GEM_ORE.get().asItem();
                    default:
                        return false;
                }
            }

            // Make it so slot 0 can only hold a single ender egg and all other slots behave normally
            // Will change later for final functionality
            @Override
            public int getSlotLimit(int slot){
                switch (slot) {
                    case 0:
                        return 1;
                    default:
                        return 64;
                }
            }

            // Implement which items can validly be placed into the slot
            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                if(!isItemValid(slot, stack)){
                    return stack;
                }

                return super.insertItem(slot, stack, simulate);
            }
        };
    }

    // Used for when world saves
    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        itemHandeler.deserializeNBT(nbt.getCompound("inv"));
        super.read(state, nbt);
    }

    // Used to read saved world data
    @Override
    public CompoundNBT write(CompoundNBT compund) {
        compund.put("inv", itemHandeler.serializeNBT());
        return super.write(compund);
    }

    // Use default capabilities
    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return handler.cast();
        }

        return super.getCapability(cap, side);
    }
}
