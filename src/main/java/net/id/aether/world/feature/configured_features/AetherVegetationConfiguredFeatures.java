package net.id.aether.world.feature.configured_features;

import net.id.aether.world.feature.configs.GroundcoverFeatureConfig;
import net.id.aether.world.feature.configs.ProjectedOrganicCoverConfig;
import net.id.aether.world.feature.features.HoneyNettleFeature;
import net.id.aether.world.feature.placed_features.AetherPlacedFeatures;
import net.id.aether.world.feature.placed_features.AetherVegetationPlacedFeatures;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

import static net.id.aether.blocks.AetherBlocks.*;

public class AetherVegetationConfiguredFeatures extends AetherConfiguredFeatures{
    /*
    Highlands
     */
    // Default
    public static final ConfiguredFeature<?, ?> AETHER_BUSH = register("aether_bush", Feature.RANDOM_PATCH.configure(Configs.AETHER_BUSH_CONFIG));
    public static final ConfiguredFeature<?, ?> AETHER_DENSE_BUSH = register("aether_dense_bush", Feature.RANDOM_PATCH.configure(Configs.AETHER_DENSE_BUSH_CONFIG));
    public static final ConfiguredFeature<?, ?> AETHER_GRASS_BUSH = register("aether_grass", Feature.RANDOM_PATCH.configure(Configs.AETHER_GRASS_BUSH_CONFIG));
    public static final ConfiguredFeature<?, ?> AETHER_TALL_GRASS_BUSH = register("aether_tall_grass", Feature.RANDOM_PATCH.configure(Configs.AETHER_TALL_GRASS_BUSH_CONFIG));
    public static final ConfiguredFeature<?, ?> FLUTEGRASS = register("flutegrass", Feature.RANDOM_PATCH.configure(Configs.FLUTEGRASS_CONFIG));
    public static final ConfiguredFeature<?, ?> AETHER_FLOWERS = register("aether_flowers", Feature.FLOWER.configure(Configs.AETHER_FLOWER_CONFIG));
    public static final ConfiguredFeature<?, ?> PATCH_BLUEBERRY = register("patch_blueberry", Feature.RANDOM_PATCH.configure(new RandomPatchFeatureConfig(42, 5, 5, ()->Feature.SIMPLE_BLOCK.configure(new SimpleBlockFeatureConfig(BlockStateProvider.of(BLUEBERRY_BUSH.getDefaultState().with(Properties.AGE_3, 3)))).withPlacement(AetherPlacedFeatures.ON_SOLID_GROUND))));
    // Plato
    public static final ConfiguredFeature<?, ?> PLATEAU_FOLIAGE = register("plateau_foliage", Feature.RANDOM_PATCH.configure(new RandomPatchFeatureConfig(96, 7, 3, () -> AetherVegetationPlacedFeatures.Configs.PLATEAU_FOLIAGE_SINGLE_BLOCK)));
    public static final ConfiguredFeature<?, ?> PLATEAU_FLOWERING_GRASS = register("plateau_flowering_grass", Configs.ORGANIC_GROUNDCOVER_FEATURE.configure(new ProjectedOrganicCoverConfig(BlockStateProvider.of(AETHER_GRASS_FLOWERING), UniformIntProvider.create(3, 10), ConstantIntProvider.create(5), UniformIntProvider.create(3, 6), 1.5)));
    public static final ConfiguredFeature<?, ?> PLATEAU_SHAMROCK = register("plateau_shamrock", Configs.ORGANIC_GROUNDCOVER_FEATURE.configure(new ProjectedOrganicCoverConfig(BlockStateProvider.of(MALT_SPRIG), UniformIntProvider.create(2, 6), ConstantIntProvider.create(5), UniformIntProvider.create(4, 7), 1.4)));
    // Shield
    public static final ConfiguredFeature<?, ?> SHIELD_FOLIAGE = register("shield_foliage", Feature.RANDOM_PATCH.configure(new RandomPatchFeatureConfig(64, 7, 3, () -> AetherVegetationPlacedFeatures.Configs.SHIELD_FOLIAGE_SINGLE_BLOCK)));
    public static final ConfiguredFeature<?, ?> SHIELD_FLAX = register("shield_flax", Feature.RANDOM_PATCH.configure(new RandomPatchFeatureConfig(96, 12, 5, () -> AetherVegetationPlacedFeatures.Configs.SHIELD_FLAX_SINGLE_BLOCK)));
    public static final ConfiguredFeature<?, ?> SHIELD_NETTLES = register("shield_nettles", Configs.HONEY_NETTLE_FEATURE.configure(new DefaultFeatureConfig()));
    // Thicket
    public static final ConfiguredFeature<?, ?> THICKET_LIVERWORT = register("thicket_liverwort", Configs.GROUNDCOVER_FEATURE.configure(new GroundcoverFeatureConfig(BlockStateProvider.of(LIVERWORT), UniformIntProvider.create(0, 2), UniformIntProvider.create(0, 1))));
    public static final ConfiguredFeature<?, ?> THICKET_LIVERWORT_CARPET = register("thicket_liverwort_carpet", Configs.ORGANIC_GROUNDCOVER_FEATURE.configure(new ProjectedOrganicCoverConfig(BlockStateProvider.of(LIVERWORT_CARPET), UniformIntProvider.create(1, 4), ConstantIntProvider.create(5), UniformIntProvider.create(5, 8), 1.3)));
    public static final ConfiguredFeature<?, ?> THICKET_SHAMROCK = register("thicket_shamrock", Configs.ORGANIC_GROUNDCOVER_FEATURE.configure(new ProjectedOrganicCoverConfig(BlockStateProvider.of(SHAMROCK), UniformIntProvider.create(2, 6), ConstantIntProvider.create(5), UniformIntProvider.create(4, 7), 1.3)));
    // Tundra
    public static final ConfiguredFeature<?, ?> TUNDRA_FOLIAGE = register("tundra_foliage", Feature.RANDOM_PATCH.configure(new RandomPatchFeatureConfig(32, 7, 3, () -> AetherVegetationPlacedFeatures.Configs.TUNDRA_FOLIAGE_SINGLE_BLOCK)));
    // ?
    public static final ConfiguredFeature<?, ?> RAINBOW_MALT_SPRIGS = register("rainbow_malt_sprigs", Configs.ORGANIC_GROUNDCOVER_FEATURE.configure(new ProjectedOrganicCoverConfig(BlockStateProvider.of(MALT_SPRIG), UniformIntProvider.create(3, 13), ConstantIntProvider.create(5), UniformIntProvider.create(3, 4), 1.4)));

    public static class Configs extends AetherConfiguredFeatures.Configs{
        /*
        Highlands
         */
        // Default
        public static final RandomPatchFeatureConfig AETHER_BUSH_CONFIG = (new RandomPatchFeatureConfig(128, 16, 7, () -> AetherVegetationPlacedFeatures.Configs.AETHER_BUSH_SINGLE_BLOCK));
        public static final RandomPatchFeatureConfig AETHER_DENSE_BUSH_CONFIG = new RandomPatchFeatureConfig(16, 7, 3, () -> AetherVegetationPlacedFeatures.Configs.AETHER_BUSH_SINGLE_BLOCK);
        public static final RandomPatchFeatureConfig AETHER_GRASS_BUSH_CONFIG = new RandomPatchFeatureConfig(32, 7, 3, () -> AetherVegetationPlacedFeatures.Configs.AETHER_GRASS_PLANT_SINGLE_BLOCK);
        public static final RandomPatchFeatureConfig AETHER_TALL_GRASS_BUSH_CONFIG = new RandomPatchFeatureConfig(32, 7, 3, () -> AetherVegetationPlacedFeatures.Configs.AETHER_TALL_GRASS_SINGLE_BLOCK);
        public static final RandomPatchFeatureConfig FLUTEGRASS_CONFIG = new RandomPatchFeatureConfig(32, 7, 3, () -> AetherVegetationPlacedFeatures.Configs.FLUTEGRASS_SINGLE_BLOCK);

        public static final RandomPatchFeatureConfig AETHER_FLOWER_CONFIG = new RandomPatchFeatureConfig(64, 7, 3, () -> AetherVegetationPlacedFeatures.Configs.AETHER_FLOWER_SINGLE_BLOCK);
        // Shield
        public static final Feature<DefaultFeatureConfig> HONEY_NETTLE_FEATURE = register("honey_nettle_feature", new HoneyNettleFeature(DefaultFeatureConfig.CODEC));

    }

    public static void init(){}
}
