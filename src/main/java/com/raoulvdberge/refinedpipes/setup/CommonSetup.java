package com.raoulvdberge.refinedpipes.setup;

import com.raoulvdberge.refinedpipes.RefinedPipes;
import com.raoulvdberge.refinedpipes.RefinedPipesBlocks;
import com.raoulvdberge.refinedpipes.block.PipeBlock;
import com.raoulvdberge.refinedpipes.item.AttachmentItem;
import com.raoulvdberge.refinedpipes.item.BlockItemBase;
import com.raoulvdberge.refinedpipes.network.pipe.attachment.AttachmentRegistry;
import com.raoulvdberge.refinedpipes.network.pipe.attachment.AttachmentType;
import com.raoulvdberge.refinedpipes.network.pipe.attachment.ExtractorAttachmentType;
import com.raoulvdberge.refinedpipes.tile.PipeTileEntity;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class CommonSetup {
    public CommonSetup() {
        AttachmentRegistry.INSTANCE.addType(new ExtractorAttachmentType());
    }

    @SubscribeEvent
    public void onRegisterBlocks(RegistryEvent.Register<Block> e) {
        e.getRegistry().register(new PipeBlock());
    }

    @SubscribeEvent
    public void onRegisterItems(RegistryEvent.Register<Item> e) {
        e.getRegistry().register(new BlockItemBase(RefinedPipesBlocks.PIPE));

        for (AttachmentType type : AttachmentRegistry.INSTANCE.getTypes()) {
            e.getRegistry().register(new AttachmentItem(type));
        }
    }

    @SubscribeEvent
    public void onRegisterTileEntities(RegistryEvent.Register<TileEntityType<?>> e) {
        e.getRegistry().register(TileEntityType.Builder.create(() -> new PipeTileEntity(), RefinedPipesBlocks.PIPE).build(null).setRegistryName(RefinedPipes.ID, "pipe"));
    }
}
