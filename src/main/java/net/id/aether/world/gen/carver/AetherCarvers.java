package net.id.aether.world.gen.carver;

import net.id.aether.Aether;
import net.id.aether.blocks.AetherBlocks;
import net.id.aether.blocks.natural.aercloud.PurpleAercloudBlock;
import net.minecraft.block.BlockState;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.floatprovider.ConstantFloatProvider;
import net.minecraft.util.math.floatprovider.UniformFloatProvider;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.carver.Carver;
import net.minecraft.world.gen.carver.CarverConfig;
import net.minecraft.world.gen.carver.ConfiguredCarver;
import net.minecraft.world.gen.heightprovider.UniformHeightProvider;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;

@SuppressWarnings("unused")
public class AetherCarvers {
    private static final BlockState DEFAULT_PURPLE_AERCLOUD = AetherBlocks.PURPLE_AERCLOUD.getStateManager().getDefaultState();
    private static final WeightedBlockStateProvider PURPLE_AERCLOUD_STATE_PROVIDER = new WeightedBlockStateProvider(
            DataPool.<BlockState>builder()
                    .add(DEFAULT_PURPLE_AERCLOUD.with(PurpleAercloudBlock.FACING, Direction.UP), 1)
                    .add(DEFAULT_PURPLE_AERCLOUD.with(PurpleAercloudBlock.FACING, Direction.DOWN), 1)
                    .add(DEFAULT_PURPLE_AERCLOUD.with(PurpleAercloudBlock.FACING, Direction.NORTH), 1)
                    .add(DEFAULT_PURPLE_AERCLOUD.with(PurpleAercloudBlock.FACING, Direction.EAST), 1)
                    .add(DEFAULT_PURPLE_AERCLOUD.with(PurpleAercloudBlock.FACING, Direction.SOUTH), 1)
                    .add(DEFAULT_PURPLE_AERCLOUD.with(PurpleAercloudBlock.FACING, Direction.WEST), 1)
                    .build());

    public static final Carver<CloudCarverConfig> AERCLOUD_CARVER = register("aercloud_carver", new CloudCarver(CloudCarverConfig.CODEC));

    //    public static final ConfiguredCarver<?> GIANT_COLD_AERCLOUD_CARVER = register("cold_aercloud_giant", AERCLOUD_CARVER.configure(new CloudCarverConfig(0.00105F, UniformHeightProvider.create(YOffset.aboveBottom(112), YOffset.fixed(240)), UniformFloatProvider.create(0.5F, 1.1F), YOffset.aboveBottom(32), UniformFloatProvider.create(1.0F, 1.75F), UniformFloatProvider.create(0.6F, 1.0F), AetherBlocks.COLD_AERCLOUD.getDefaultState(), ConstantFloatProvider.create(0.0125F), UniformFloatProvider.create(0.35F, 0.65F), UniformIntProvider.create(12, 18), ConstantFloatProvider.create(1), ConstantIntProvider.create(10), ConstantFloatProvider.create(2.75F))));
    public static final RegistryEntry<ConfiguredCarver<?>> LARGE_COLD_AERCLOUD_CARVER = register("large_cold_aercloud", AERCLOUD_CARVER.configure(new CloudCarverConfig(0.0003F, UniformHeightProvider.create(YOffset.fixed(260), YOffset.fixed(340)), UniformFloatProvider.create(0.5F, 1.1F), YOffset.aboveBottom(32), UniformFloatProvider.create(1.0F, 2.15F), UniformFloatProvider.create(0.6F, 1.0F), BlockStateProvider.of(AetherBlocks.COLD_AERCLOUD), ConstantFloatProvider.create(0.1F), UniformFloatProvider.create(0.285F, 0.45F), UniformIntProvider.create(8, 9), ConstantFloatProvider.create(0.3F), ConstantIntProvider.create(16), ConstantFloatProvider.create(1F))));
    public static final RegistryEntry<ConfiguredCarver<?>> COLD_AERCLOUD_CARVER = register("cold_aercloud", AERCLOUD_CARVER.configure(new CloudCarverConfig(0.0105F, UniformHeightProvider.create(YOffset.aboveBottom(4), YOffset.fixed(112)), UniformFloatProvider.create(0.5F, 1.1F), YOffset.aboveBottom(0), UniformFloatProvider.create(0.3F, 0.75F), UniformFloatProvider.create(0.36F, 0.6F), BlockStateProvider.of(AetherBlocks.COLD_AERCLOUD), ConstantFloatProvider.create(0.15F), UniformFloatProvider.create(0.285F, 0.45F), UniformIntProvider.create(3, 4), ConstantFloatProvider.create(2), ConstantIntProvider.create(3), ConstantFloatProvider.create(0.25F))));
    public static final RegistryEntry<ConfiguredCarver<?>> TINY_COLD_AERCLOUD_CARVER = register("tiny_cold_aercloud", AERCLOUD_CARVER.configure(new CloudCarverConfig(0.01F, UniformHeightProvider.create(YOffset.aboveBottom(100), YOffset.fixed(260)), UniformFloatProvider.create(0.5F, 1.1F), YOffset.aboveBottom(0), UniformFloatProvider.create(0.3F, 0.75F), UniformFloatProvider.create(0.36F, 0.6F), BlockStateProvider.of(AetherBlocks.COLD_AERCLOUD), ConstantFloatProvider.create(0.15F), UniformFloatProvider.create(0.785F, 1.25F), UniformIntProvider.create(1, 2), ConstantFloatProvider.create(3), ConstantIntProvider.create(1), ConstantFloatProvider.create(0.065F))));

    public static final RegistryEntry<ConfiguredCarver<?>> LARGE_STORM_AERCLOUD_CARVER = register("large_storm_aercloud", AERCLOUD_CARVER.configure(new CloudCarverConfig(0.001F, UniformHeightProvider.create(YOffset.fixed(230), YOffset.fixed(310)), UniformFloatProvider.create(0.5F, 1.1F), YOffset.aboveBottom(32), UniformFloatProvider.create(1F, 2F), UniformFloatProvider.create(0.3F, 0.5F), BlockStateProvider.of(AetherBlocks.STORM_AERCLOUD), ConstantFloatProvider.create(0.233F), UniformFloatProvider.create(0.285F, 0.35F), UniformIntProvider.create(6, 7), ConstantFloatProvider.create(0.6F), ConstantIntProvider.create(16), ConstantFloatProvider.create(1F))));
    public static final RegistryEntry<ConfiguredCarver<?>> STORM_AERCLOUD_CARVER = register("storm_aercloud", AERCLOUD_CARVER.configure(new CloudCarverConfig(0.002F, UniformHeightProvider.create(YOffset.aboveBottom(4), YOffset.fixed(32)), UniformFloatProvider.create(0.5F, 1.1F), YOffset.aboveBottom(0), UniformFloatProvider.create(0.25F,0.5F), UniformFloatProvider.create(0.3F, 0.5F), BlockStateProvider.of(AetherBlocks.STORM_AERCLOUD), ConstantFloatProvider.create(0.175F), UniformFloatProvider.create(0.15F, 0.3F), UniformIntProvider.create(3, 4), ConstantFloatProvider.create(2), ConstantIntProvider.create(3), ConstantFloatProvider.create(0.25F))));
    public static final RegistryEntry<ConfiguredCarver<?>> TINY_STORM_AERCLOUD_CARVER = register("tiny_storm_aercloud", AERCLOUD_CARVER.configure(new CloudCarverConfig(0.003F, UniformHeightProvider.create(YOffset.aboveBottom(100), YOffset.fixed(260)), UniformFloatProvider.create(0.5F, 1.1F), YOffset.aboveBottom(0), UniformFloatProvider.create(0.3F, 0.5F), UniformFloatProvider.create(0.18F, 0.3F), BlockStateProvider.of(AetherBlocks.STORM_AERCLOUD), ConstantFloatProvider.create(0.15F), UniformFloatProvider.create(0.785F, 1.25F), UniformIntProvider.create(1, 2), ConstantFloatProvider.create(3), ConstantIntProvider.create(1), ConstantFloatProvider.create(0.065F))));

    public static final RegistryEntry<ConfiguredCarver<?>> LARGE_BLUE_AERCLOUD_CARVER = register("large_blue_aercloud", AERCLOUD_CARVER.configure(new CloudCarverConfig(0.001F, UniformHeightProvider.create(YOffset.fixed(230), YOffset.fixed(310)), UniformFloatProvider.create(0.5F, 1.1F), YOffset.aboveBottom(32), UniformFloatProvider.create(5.48F, 6.75F), UniformFloatProvider.create(0.3F, 0.5F), BlockStateProvider.of(AetherBlocks.BLUE_AERCLOUD), ConstantFloatProvider.create(0.233F), UniformFloatProvider.create(0.285F, 0.35F), UniformIntProvider.create(6, 7), ConstantFloatProvider.create(0.6F), ConstantIntProvider.create(16), ConstantFloatProvider.create(1F))));
    public static final RegistryEntry<ConfiguredCarver<?>> BLUE_AERCLOUD_CARVER = register("blue_aercloud", AERCLOUD_CARVER.configure(new CloudCarverConfig(0.007F, UniformHeightProvider.create(YOffset.aboveBottom(4), YOffset.fixed(32)), UniformFloatProvider.create(0.5F, 1.1F), YOffset.aboveBottom(0), UniformFloatProvider.create(0.5F, 1.25F), UniformFloatProvider.create(0.6F, 1.0F), BlockStateProvider.of(AetherBlocks.BLUE_AERCLOUD), ConstantFloatProvider.create(0.175F), UniformFloatProvider.create(0.15F, 0.3F), UniformIntProvider.create(3, 4), ConstantFloatProvider.create(2), ConstantIntProvider.create(3), ConstantFloatProvider.create(0.25F))));
    public static final RegistryEntry<ConfiguredCarver<?>> TINY_BLUE_AERCLOUD_CARVER = register("tiny_blue_aercloud", AERCLOUD_CARVER.configure(new CloudCarverConfig(0.009F, UniformHeightProvider.create(YOffset.aboveBottom(100), YOffset.fixed(260)), UniformFloatProvider.create(0.5F, 1.1F), YOffset.aboveBottom(0), UniformFloatProvider.create(0.3F, 0.75F), UniformFloatProvider.create(0.36F, 0.6F), BlockStateProvider.of(AetherBlocks.BLUE_AERCLOUD), ConstantFloatProvider.create(0.15F), UniformFloatProvider.create(0.785F, 1.25F), UniformIntProvider.create(1, 2), ConstantFloatProvider.create(3), ConstantIntProvider.create(1), ConstantFloatProvider.create(0.065F))));

    public static final RegistryEntry<ConfiguredCarver<?>> LARGE_PINK_AERCLOUD_CARVER = register("large_pink_aercloud", AERCLOUD_CARVER.configure(new CloudCarverConfig(0.001F, UniformHeightProvider.create(YOffset.fixed(230), YOffset.fixed(310)), UniformFloatProvider.create(0.5F, 1.1F), YOffset.aboveBottom(32), UniformFloatProvider.create(1F, 2F), UniformFloatProvider.create(0.3F, 0.5F), BlockStateProvider.of(AetherBlocks.PINK_AERCLOUD), ConstantFloatProvider.create(0.233F), UniformFloatProvider.create(0.285F, 0.35F), UniformIntProvider.create(6, 7), ConstantFloatProvider.create(0.6F), ConstantIntProvider.create(16), ConstantFloatProvider.create(1F))));
    public static final RegistryEntry<ConfiguredCarver<?>> PINK_AERCLOUD_CARVER = register("pink_aercloud", AERCLOUD_CARVER.configure(new CloudCarverConfig(0.002F, UniformHeightProvider.create(YOffset.aboveBottom(4), YOffset.fixed(32)), UniformFloatProvider.create(0.5F, 1.1F), YOffset.aboveBottom(0), UniformFloatProvider.create(0.25F,0.5F), UniformFloatProvider.create(0.3F, 0.5F), BlockStateProvider.of(AetherBlocks.PINK_AERCLOUD), ConstantFloatProvider.create(0.175F), UniformFloatProvider.create(0.15F, 0.3F), UniformIntProvider.create(3, 4), ConstantFloatProvider.create(2), ConstantIntProvider.create(3), ConstantFloatProvider.create(0.25F))));
    public static final RegistryEntry<ConfiguredCarver<?>> TINY_PINK_AERCLOUD_CARVER = register("tiny_pink_aercloud", AERCLOUD_CARVER.configure(new CloudCarverConfig(0.003F, UniformHeightProvider.create(YOffset.aboveBottom(100), YOffset.fixed(260)), UniformFloatProvider.create(0.5F, 1.1F), YOffset.aboveBottom(0), UniformFloatProvider.create(0.3F, 0.5F), UniformFloatProvider.create(0.18F, 0.3F), BlockStateProvider.of(AetherBlocks.PINK_AERCLOUD), ConstantFloatProvider.create(0.15F), UniformFloatProvider.create(0.785F, 1.25F), UniformIntProvider.create(1, 2), ConstantFloatProvider.create(3), ConstantIntProvider.create(1), ConstantFloatProvider.create(0.065F))));

    public static final RegistryEntry<ConfiguredCarver<?>> LARGE_GOLDEN_AERCLOUD_CARVER = register("large_golden_aercloud", AERCLOUD_CARVER.configure(new CloudCarverConfig(0.0006F, UniformHeightProvider.create(YOffset.fixed(290), YOffset.fixed(360)), UniformFloatProvider.create(0.5F, 1.1F), YOffset.aboveBottom(32), UniformFloatProvider.create(1.6F, 2.0F), UniformFloatProvider.create(1.5F, 1.85F), BlockStateProvider.of(AetherBlocks.GOLDEN_AERCLOUD), ConstantFloatProvider.create(0.322F), UniformFloatProvider.create(0.585F, 0.75F), UniformIntProvider.create(7, 9), ConstantFloatProvider.create(0.4F), ConstantIntProvider.create(16), ConstantFloatProvider.create(1F))));
    public static final RegistryEntry<ConfiguredCarver<?>> GOLDEN_AERCLOUD_CARVER = register("golden_aercloud", AERCLOUD_CARVER.configure(new CloudCarverConfig(0.0085F, UniformHeightProvider.create(YOffset.aboveBottom(14), YOffset.fixed(68)), UniformFloatProvider.create(0.5F, 1.1F), YOffset.aboveBottom(32), UniformFloatProvider.create(0.5F, 1.25F), UniformFloatProvider.create(0.6F, 1.0F), BlockStateProvider.of(AetherBlocks.GOLDEN_AERCLOUD), ConstantFloatProvider.create(0.225F), UniformFloatProvider.create(0.35F, 0.5F), UniformIntProvider.create(2, 5), ConstantFloatProvider.create(1.5F), ConstantIntProvider.create(3), ConstantFloatProvider.create(0.35F))));
    public static final RegistryEntry<ConfiguredCarver<?>> TINY_GOLDEN_AERCLOUD_CARVER = register("tiny_golden_aercloud", AERCLOUD_CARVER.configure(new CloudCarverConfig(0.00875F, UniformHeightProvider.create(YOffset.aboveBottom(100), YOffset.fixed(260)), UniformFloatProvider.create(0.5F, 1.1F), YOffset.aboveBottom(0), UniformFloatProvider.create(0.3F, 0.75F), UniformFloatProvider.create(0.36F, 0.6F), BlockStateProvider.of(AetherBlocks.GOLDEN_AERCLOUD), ConstantFloatProvider.create(0.15F), UniformFloatProvider.create(0.785F, 1.25F), UniformIntProvider.create(1, 2), ConstantFloatProvider.create(3), ConstantIntProvider.create(1), ConstantFloatProvider.create(0.065F))));

    public static final RegistryEntry<ConfiguredCarver<?>> LARGE_PURPLE_AERCLOUD_CARVER = register("large_purple_aercloud", AERCLOUD_CARVER.configure(new CloudCarverConfig(0.0006F, UniformHeightProvider.create(YOffset.fixed(290), YOffset.fixed(360)), UniformFloatProvider.create(0.5F, 1.1F), YOffset.aboveBottom(32), UniformFloatProvider.create(1.6F, 2.0F), UniformFloatProvider.create(1.5F, 1.85F), PURPLE_AERCLOUD_STATE_PROVIDER, ConstantFloatProvider.create(0.322F), UniformFloatProvider.create(0.585F, 0.75F), UniformIntProvider.create(7, 9), ConstantFloatProvider.create(0.4F), ConstantIntProvider.create(16), ConstantFloatProvider.create(1F))));
    public static final RegistryEntry<ConfiguredCarver<?>> PURPLE_AERCLOUD_CARVER = register("purple_aercloud", AERCLOUD_CARVER.configure(new CloudCarverConfig(0.0085F, UniformHeightProvider.create(YOffset.aboveBottom(14), YOffset.fixed(68)), UniformFloatProvider.create(0.5F, 1.1F), YOffset.aboveBottom(32), UniformFloatProvider.create(0.5F, 1.25F), UniformFloatProvider.create(0.6F, 1.0F), PURPLE_AERCLOUD_STATE_PROVIDER, ConstantFloatProvider.create(0.225F), UniformFloatProvider.create(0.35F, 0.5F), UniformIntProvider.create(2, 5), ConstantFloatProvider.create(1.5F), ConstantIntProvider.create(3), ConstantFloatProvider.create(0.35F))));
    public static final RegistryEntry<ConfiguredCarver<?>> TINY_PURPLE_AERCLOUD_CARVER = register("tiny_purple_aercloud", AERCLOUD_CARVER.configure(new CloudCarverConfig(0.00875F, UniformHeightProvider.create(YOffset.aboveBottom(100), YOffset.fixed(260)), UniformFloatProvider.create(0.5F, 1.1F), YOffset.aboveBottom(0), UniformFloatProvider.create(0.3F, 0.75F), UniformFloatProvider.create(0.36F, 0.6F), PURPLE_AERCLOUD_STATE_PROVIDER, ConstantFloatProvider.create(0.15F), UniformFloatProvider.create(0.785F, 1.25F), UniformIntProvider.create(1, 2), ConstantFloatProvider.create(3), ConstantIntProvider.create(1), ConstantFloatProvider.create(0.065F))));

    public static final RegistryEntry<ConfiguredCarver<?>> LARGE_GREEN_AERCLOUD_CARVER = register("large_green_aercloud", AERCLOUD_CARVER.configure(new CloudCarverConfig(0.0006F, UniformHeightProvider.create(YOffset.fixed(290), YOffset.fixed(360)), UniformFloatProvider.create(0.5F, 1.1F), YOffset.aboveBottom(32), UniformFloatProvider.create(1.6F, 2.0F), UniformFloatProvider.create(1.5F, 1.85F), BlockStateProvider.of(AetherBlocks.GREEN_AERCLOUD), ConstantFloatProvider.create(0.322F), UniformFloatProvider.create(0.585F, 0.75F), UniformIntProvider.create(7, 9), ConstantFloatProvider.create(0.4F), ConstantIntProvider.create(16), ConstantFloatProvider.create(1F))));
    public static final RegistryEntry<ConfiguredCarver<?>> GREEN_AERCLOUD_CARVER = register("green_aercloud", AERCLOUD_CARVER.configure(new CloudCarverConfig(0.0085F, UniformHeightProvider.create(YOffset.aboveBottom(14), YOffset.fixed(68)), UniformFloatProvider.create(0.5F, 1.1F), YOffset.aboveBottom(32), UniformFloatProvider.create(0.5F, 1.25F), UniformFloatProvider.create(0.6F, 1.0F), BlockStateProvider.of(AetherBlocks.GREEN_AERCLOUD), ConstantFloatProvider.create(0.225F), UniformFloatProvider.create(0.35F, 0.5F), UniformIntProvider.create(2, 5), ConstantFloatProvider.create(1.5F), ConstantIntProvider.create(3), ConstantFloatProvider.create(0.35F))));
    public static final RegistryEntry<ConfiguredCarver<?>> TINY_GREEN_AERCLOUD_CARVER = register("tiny_green_aercloud", AERCLOUD_CARVER.configure(new CloudCarverConfig(0.00875F, UniformHeightProvider.create(YOffset.aboveBottom(100), YOffset.fixed(260)), UniformFloatProvider.create(0.5F, 1.1F), YOffset.aboveBottom(0), UniformFloatProvider.create(0.3F, 0.75F), UniformFloatProvider.create(0.36F, 0.6F), BlockStateProvider.of(AetherBlocks.GREEN_AERCLOUD), ConstantFloatProvider.create(0.15F), UniformFloatProvider.create(0.785F, 1.25F), UniformIntProvider.create(1, 2), ConstantFloatProvider.create(3), ConstantIntProvider.create(1), ConstantFloatProvider.create(0.065F))));

    @SuppressWarnings("unchecked")
    public static <T extends CarverConfig> Carver<T> register(String name, Carver<?> carver) {
        return (Carver<T>) Registry.register(Registry.CARVER, Aether.locate(name), carver);
    }

    public static RegistryEntry<ConfiguredCarver<?>> register(String name, ConfiguredCarver<?> carver) {
        return BuiltinRegistries.add(BuiltinRegistries.CONFIGURED_CARVER, Aether.locate(name), carver);
    }

    public static void init() {
    }
}
