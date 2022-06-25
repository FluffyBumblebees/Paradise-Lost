package net.id.paradiselost.client.rendering.particle;


import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;

import java.util.SplittableRandom;

//I did not steal this from that one falling leaves mod, you have no proof!
@Environment(EnvType.CLIENT)
public class GoldenOakLeafParticle extends SpriteBillboardParticle {

    private static final SplittableRandom random = new SplittableRandom();
    private final float rotateFactor;
    private final double velocityComposite, velocityDown;

    protected GoldenOakLeafParticle(ClientWorld clientWorld, double d, double e, double f, double g, double h, double i, SpriteProvider provider) {
        super(clientWorld, d, e, f);
        setSprite(provider);

        collidesWithWorld = true;
        gravityStrength = 0.09F;
        maxAge = 1200;

        velocityX *= 0.325F;
        velocityY *= 0.0F;
        velocityZ *= 0.325F;

        velocityComposite = g / 50;
        velocityDown = h;

        rotateFactor = ((float) Math.random() - 0.5F) * 0.01F;
        scale = (float) (0.06 + (random.nextDouble() / 14));
    }

    @Override
    public void tick() {
        velocityY = velocityDown;
        super.tick();
        velocityZ = velocityComposite / 2;
        velocityX = velocityComposite / 2;
        if (age < 2) {
            velocityY = 0;
        }
        if (age > maxAge - 1 / 0.06F) {
            if (alpha > 0.06F) {
                alpha -= 0.06F;
            } else {
                markDead();
            }
        }
        prevAngle = angle;
        if (!onGround && !world.getFluidState(new BlockPos(x, y, z)).isIn(FluidTags.WATER)) {
            angle += Math.PI * Math.sin(rotateFactor * age) / 2;
        }
        if (world.getFluidState(new BlockPos(x, y, z)).isIn(FluidTags.WATER)) {
            velocityY = 0;
            gravityStrength = 0;
        } else {
            gravityStrength = 0.1F;
        }
    }

    @Override
    public int getBrightness(float tint) {
        return 200;
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Environment(EnvType.CLIENT)
    public static class DefaultFactory implements ParticleFactory<DefaultParticleType> {
        private final SpriteProvider provider;

        public DefaultFactory(SpriteProvider provider) {
            this.provider = provider;
        }

        @Override
        public Particle createParticle(DefaultParticleType parameters, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
            return new GoldenOakLeafParticle(world, x, y, z, velocityX, velocityY, velocityZ, provider);
        }
    }
}
