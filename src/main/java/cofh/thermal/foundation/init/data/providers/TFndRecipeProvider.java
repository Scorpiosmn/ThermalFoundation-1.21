package cofh.thermal.foundation.init.data.providers;

import cofh.lib.init.data.RecipeProviderCoFH;
import cofh.lib.init.tags.ItemTagsCoFH;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.Tags;

import static cofh.lib.util.constants.ModIds.ID_THERMAL;
import static cofh.thermal.core.ThermalCore.ITEMS;
import static cofh.thermal.foundation.init.registries.TFndIDs.*;
import static net.minecraft.data.recipes.RecipeCategory.MISC;

public class TFndRecipeProvider extends RecipeProviderCoFH {

    public TFndRecipeProvider(PackOutput output) {

        super(output, ID_THERMAL);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {

        var reg = ITEMS;

        generateTypeRecipes(reg, recipeOutput, "lead");
        generateTypeRecipes(reg, recipeOutput, "nickel");
        generateTypeRecipes(reg, recipeOutput, "silver");
        generateTypeRecipes(reg, recipeOutput, "tin");

        generateTypeRecipes(reg, recipeOutput, "bronze");
        generateTypeRecipes(reg, recipeOutput, "constantan");
        generateTypeRecipes(reg, recipeOutput, "electrum");
        generateTypeRecipes(reg, recipeOutput, "invar");

        generateSmeltingAndBlastingRecipes(reg, recipeOutput, reg.get(ID_APATITE_ORE), reg.get("apatite"), 0.5F, "smelting");
        generateSmeltingAndBlastingRecipes(reg, recipeOutput, reg.get(ID_CINNABAR_ORE), reg.get("cinnabar"), 0.5F, "smelting");
        generateSmeltingAndBlastingRecipes(reg, recipeOutput, reg.get(ID_NITER_ORE), reg.get("niter"), 0.5F, "smelting");
        generateSmeltingAndBlastingRecipes(reg, recipeOutput, reg.get(ID_SULFUR_ORE), reg.get("sulfur"), 0.5F, "smelting");

        generateSmeltingAndBlastingRecipes(reg, recipeOutput, "lead", 0.8F);
        generateSmeltingAndBlastingRecipes(reg, recipeOutput, "nickel", 1.0F);
        generateSmeltingAndBlastingRecipes(reg, recipeOutput, "silver", 1.0F);
        generateSmeltingAndBlastingRecipes(reg, recipeOutput, "tin", 0.6F);

        generateSmeltingAndBlastingRecipes(reg, recipeOutput, "bronze", 0);
        generateSmeltingAndBlastingRecipes(reg, recipeOutput, "constantan", 0);
        generateSmeltingAndBlastingRecipes(reg, recipeOutput, "electrum", 0);
        generateSmeltingAndBlastingRecipes(reg, recipeOutput, "invar", 0);

        generateAlloyRecipes(recipeOutput);
    }

    // region HELPERS
    private void generateAlloyRecipes(RecipeOutput consumer) {

        var reg = ITEMS;

        ShapelessRecipeBuilder.shapeless(MISC, reg.get("bronze_dust"), 4)
                .requires(ItemTagsCoFH.DUSTS_COPPER)
                .requires(ItemTagsCoFH.DUSTS_COPPER)
                .requires(ItemTagsCoFH.DUSTS_COPPER)
                .requires(ItemTagsCoFH.DUSTS_TIN)
                .unlockedBy("has_copper_dust", has(ItemTagsCoFH.DUSTS_COPPER))
                .unlockedBy("has_tin_dust", has(ItemTagsCoFH.DUSTS_TIN))
                .save(consumer, ID_THERMAL + ":bronze_dust_4");

        ShapelessRecipeBuilder.shapeless(MISC, reg.get("constantan_dust"), 2)
                .requires(ItemTagsCoFH.DUSTS_COPPER)
                .requires(ItemTagsCoFH.DUSTS_NICKEL)
                .unlockedBy("has_copper_dust", has(ItemTagsCoFH.DUSTS_COPPER))
                .unlockedBy("has_nickel_dust", has(ItemTagsCoFH.DUSTS_NICKEL))
                .save(consumer, ID_THERMAL + ":constantan_dust_2");

        ShapelessRecipeBuilder.shapeless(MISC, reg.get("electrum_dust"), 2)
                .requires(ItemTagsCoFH.DUSTS_GOLD)
                .requires(ItemTagsCoFH.DUSTS_SILVER)
                .unlockedBy("has_gold_dust", has(ItemTagsCoFH.DUSTS_GOLD))
                .unlockedBy("has_silver_dust", has(ItemTagsCoFH.DUSTS_SILVER))
                .save(consumer, ID_THERMAL + ":electrum_dust_2");

        ShapelessRecipeBuilder.shapeless(MISC, reg.get("invar_dust"), 3)
                .requires(ItemTagsCoFH.DUSTS_IRON)
                .requires(ItemTagsCoFH.DUSTS_IRON)
                .requires(ItemTagsCoFH.DUSTS_NICKEL)
                .unlockedBy("has_iron_dust", has(ItemTagsCoFH.DUSTS_IRON))
                .unlockedBy("has_nickel_dust", has(ItemTagsCoFH.DUSTS_NICKEL))
                .save(consumer, ID_THERMAL + ":invar_dust_3");

        ShapelessRecipeBuilder.shapeless(MISC, reg.get("bronze_ingot"), 4)
                .requires(fromTags(ItemTagsCoFH.DUSTS_COPPER, Tags.Items.INGOTS_COPPER))
                .requires(fromTags(ItemTagsCoFH.DUSTS_COPPER, Tags.Items.INGOTS_COPPER))
                .requires(fromTags(ItemTagsCoFH.DUSTS_COPPER, Tags.Items.INGOTS_COPPER))
                .requires(fromTags(ItemTagsCoFH.DUSTS_TIN, ItemTagsCoFH.INGOTS_TIN))
                .requires(Items.FIRE_CHARGE)
                .unlockedBy("has_copper_dust", has(ItemTagsCoFH.DUSTS_COPPER))
                .unlockedBy("has_copper_ingot", has(Tags.Items.INGOTS_COPPER))
                .unlockedBy("has_tin_dust", has(ItemTagsCoFH.DUSTS_TIN))
                .unlockedBy("has_tin_ingot", has(ItemTagsCoFH.INGOTS_TIN))
                .save(consumer, ID_THERMAL + ":fire_charge/bronze_ingot_4");

        ShapelessRecipeBuilder.shapeless(MISC, reg.get("constantan_ingot"), 2)
                .requires(fromTags(ItemTagsCoFH.DUSTS_COPPER, Tags.Items.INGOTS_COPPER))
                .requires(fromTags(ItemTagsCoFH.DUSTS_NICKEL, ItemTagsCoFH.INGOTS_NICKEL))
                .requires(Items.FIRE_CHARGE)
                .unlockedBy("has_copper_dust", has(ItemTagsCoFH.DUSTS_COPPER))
                .unlockedBy("has_copper_ingot", has(Tags.Items.INGOTS_COPPER))
                .unlockedBy("has_nickel_dust", has(ItemTagsCoFH.DUSTS_NICKEL))
                .unlockedBy("has_nickel_ingot", has(ItemTagsCoFH.INGOTS_NICKEL))
                .save(consumer, ID_THERMAL + ":fire_charge/constantan_ingot_2");

        ShapelessRecipeBuilder.shapeless(MISC, reg.get("electrum_ingot"), 2)
                .requires(fromTags(ItemTagsCoFH.DUSTS_GOLD, Tags.Items.INGOTS_GOLD))
                .requires(fromTags(ItemTagsCoFH.DUSTS_SILVER, ItemTagsCoFH.INGOTS_SILVER))
                .requires(Items.FIRE_CHARGE)
                .unlockedBy("has_gold_dust", has(ItemTagsCoFH.DUSTS_GOLD))
                .unlockedBy("has_gold_ingot", has(Tags.Items.INGOTS_GOLD))
                .unlockedBy("has_silver_dust", has(ItemTagsCoFH.DUSTS_SILVER))
                .unlockedBy("has_silver_ingot", has(ItemTagsCoFH.INGOTS_SILVER))
                .save(consumer, ID_THERMAL + ":fire_charge/electrum_ingot_2");

        ShapelessRecipeBuilder.shapeless(MISC, reg.get("invar_ingot"), 3)
                .requires(fromTags(ItemTagsCoFH.DUSTS_IRON, Tags.Items.INGOTS_IRON))
                .requires(fromTags(ItemTagsCoFH.DUSTS_IRON, Tags.Items.INGOTS_IRON))
                .requires(fromTags(ItemTagsCoFH.DUSTS_NICKEL, ItemTagsCoFH.INGOTS_NICKEL))
                .requires(Items.FIRE_CHARGE)
                .unlockedBy("has_iron_dust", has(ItemTagsCoFH.DUSTS_IRON))
                .unlockedBy("has_iron_ingot", has(Tags.Items.INGOTS_IRON))
                .unlockedBy("has_nickel_dust", has(ItemTagsCoFH.DUSTS_NICKEL))
                .unlockedBy("has_nickel_ingot", has(ItemTagsCoFH.INGOTS_NICKEL))
                .save(consumer, ID_THERMAL + ":fire_charge/invar_ingot_3");
    }
    // endregion
}
