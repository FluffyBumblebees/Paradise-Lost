package net.id.paradiselost.blocks.decorative;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.block.WallTorchBlock;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3f;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class AmbrosiumWallTorchBlock extends WallTorchBlock {
    public AmbrosiumWallTorchBlock(Settings settings) {
        super(settings, new DustParticleEffect(new Vec3f(0.886f, 0.871f, 0.125f), 0.7f));
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        int max = random.nextInt(3) + 2;
        for (int i = 0; i <= max; i++) {
            Direction direction = state.get(FACING).getOpposite();
            double e = (double) pos.getX() + 0.5D + (random.nextDouble() - 0.5D) * 0.23D + 0.3D * (double) direction.getOffsetX();
            double f = (double) pos.getY() + 0.6D + (random.nextDouble() - 0.5D) * 0.25D + 0.22D;
            double g = (double) pos.getZ() + 0.5D + (random.nextDouble() - 0.5D) * 0.23D + 0.3D * (double) direction.getOffsetZ();
            world.addParticle(this.particle, e, f, g, 0.0D, -4.0D, 0.0D);
        }
    }
}
