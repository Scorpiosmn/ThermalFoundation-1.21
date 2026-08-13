package cofh.thermal.foundation.init.data;

import cofh.thermal.foundation.init.data.providers.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

import static cofh.lib.util.constants.ModIds.ID_THERMAL_FOUNDATION;

public class TFndDataGen {

    @SubscribeEvent
    public static void gatherData(final GatherDataEvent event) {

        DataGenerator gen = event.getGenerator();
        PackOutput output = gen.getPackOutput();
        ExistingFileHelper exFileHelper = event.getExistingFileHelper();

        TFndDatapackRegistryProvider datapackRegistry = new TFndDatapackRegistryProvider(output, event.getLookupProvider());
        gen.addProvider(event.includeServer(), datapackRegistry);
        CompletableFuture<HolderLookup.Provider> lookup = datapackRegistry.getRegistryProvider();

        TFndTagsProvider.Block blockTags = new TFndTagsProvider.Block(output, lookup, exFileHelper);
        gen.addProvider(event.includeServer(), blockTags);
        gen.addProvider(event.includeServer(), new TFndTagsProvider.Item(output, lookup, blockTags.contentsGetter(), exFileHelper));

        gen.addProvider(event.includeServer(), new TFndLootTableProvider(output, lookup));
        gen.addProvider(event.includeServer(), new TFndRecipeProvider(output, lookup));

        gen.addProvider(event.includeClient(), new TFndBlockStateProvider(output, exFileHelper));
        gen.addProvider(event.includeClient(), new TFndItemModelProvider(output, exFileHelper));
    }

}
