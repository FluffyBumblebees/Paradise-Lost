package net.id.paradiselost.world.feature.tree.generator;

import net.id.paradiselost.world.feature.configuredfeatures.ParadiseLostTreeConfiguredFeatures;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

public class OrangeSaplingGenerator extends SaplingGenerator {
    @Override
    protected @Nullable RegistryEntry<? extends ConfiguredFeature<?, ?>> getTreeFeature(Random random, boolean bl) {
        return ParadiseLostTreeConfiguredFeatures.ORANGE_TREE;
    }
}
