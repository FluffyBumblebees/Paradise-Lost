package net.id.aether.entities.block;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.id.aether.blocks.AetherBlocks;
import net.id.aether.tag.AetherBlockTags;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.AutomaticItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.state.property.Properties;
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.crash.CrashReportSection;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.*;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.GameRules;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;

import java.util.List;

public class BlockLikeEntity extends Entity {
    protected static final TrackedData<BlockPos> ORIGIN = DataTracker.registerData(BlockLikeEntity.class, TrackedDataHandlerRegistry.BLOCK_POS);
    public int moveTime;
    public boolean dropItem = true;
    public NbtCompound blockEntityData;
    private BlockState blockState = Blocks.DIRT.getDefaultState();
    private boolean dontSetBlock;
    private boolean hurtEntities;
    private int fallHurtMax = 40;
    private float fallHurtAmount = 2.0f;
    private boolean collides;
    protected boolean partOfSet = false;

    public BlockLikeEntity(EntityType<? extends BlockLikeEntity> entityType, World world) {
        super(entityType, world);
        this.moveTime = 0;
    }

    public BlockLikeEntity(EntityType<? extends BlockLikeEntity> entityType, World world, double x, double y, double z, BlockState blockState) {
        this(entityType, world);
        this.blockState = blockState;
        this.intersectionChecked = true;
        this.setPosition(x, y, z);
        this.setVelocity(Vec3d.ZERO);
        this.prevX = x;
        this.prevY = y;
        this.prevZ = z;
        this.setOrigin(new BlockPos(this.getPos()));
    }

    public BlockLikeEntity(EntityType<? extends BlockLikeEntity> entityType, World world, BlockPos pos, BlockState blockState, boolean partOfSet) {
        this(entityType, world, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, blockState);
        this.partOfSet = partOfSet;
    }

    @Override
    protected Box calculateBoundingBox() {
        if (this.dataTracker == null || this.blockState == null) {
            return super.calculateBoundingBox();
        }
        BlockPos origin = this.dataTracker.get(ORIGIN);
        VoxelShape shape = this.blockState.getCollisionShape(world, origin);
        if (shape.isEmpty()) {
            this.collides = false;
            shape = this.blockState.getOutlineShape(world, origin);
            if (shape.isEmpty()) {
                return super.calculateBoundingBox();
            }
        } else {
            this.collides = true;
        }
        Box box = shape.getBoundingBox();
        return box.offset(getPos().subtract(new Vec3d(0.5, 0, 0.5)));
    }

    @Override
    public boolean isAttackable() {
        return false;
    }

    @Environment(EnvType.CLIENT)
    public BlockPos getOrigin() {
        return this.dataTracker.get(ORIGIN);
    }

    public void setOrigin(BlockPos origin) {
        this.dataTracker.set(ORIGIN, origin);
        this.setPosition(getX(), getY(), getZ());
    }

    public void markPartOfSet() {
        partOfSet = true;
    }

    @Override
    protected void initDataTracker() {
        this.dataTracker.startTracking(ORIGIN, BlockPos.ORIGIN);
    }

    @Override
    public boolean collides() {
        return !this.isRemoved() && collides;
    }

    @Override
    public boolean isCollidable() {
        return this.collides();
    }

    @Override
    public boolean collidesWith(Entity other) {
        return !(other instanceof BlockLikeEntity) && super.collidesWith(other);
    }

    @Override
    public void tick() {
        if (this.blockState.isIn(AetherBlockTags.HURTABLE_FLOATERS)) {
            double verticalVel = this.getVelocity().getY();
            if (verticalVel < 0.0D) {
                verticalVel = Math.abs(verticalVel);
            }
            this.hurtEntities = true;
            this.fallHurtAmount = this.blockState.getBlock().getHardness() * (float)verticalVel;
            this.fallHurtMax = Math.max(Math.round(this.fallHurtAmount), this.fallHurtMax);
        }
    }

    public void postTickMovement() {
    }

    public void postTickEntityCollision(Entity entity) {
        if (!(entity instanceof BlockLikeEntity) && !entity.noClip && this.collides()) {
            entity.fallDistance = 0F;
            entity.setPosition(entity.getPos().x, getBoundingBox().maxY, entity.getPos().z);
            entity.setOnGround(true);
        }
        if (!(entity instanceof BlockLikeEntity fbe && fbe.partOfSet)) {
            this.blockState.getBlock().onEntityCollision(blockState, world, this.getBlockPos(), entity);
        }
    }

    public boolean shouldCease(double impact) {
        if (this.world.isClient) {
            return false;
        }

        BlockPos blockPos = this.getBlockPos();
        boolean isConcrete = this.blockState.getBlock() instanceof ConcretePowderBlock;
        boolean shouldSolidify = isConcrete && this.world.getFluidState(blockPos).isIn(FluidTags.WATER);
        double speed = this.getVelocity().lengthSquared();

        if (isConcrete && speed > 1.0D) {
            BlockHitResult blockHitResult = this.world
                    .raycast(new RaycastContext(new Vec3d(this.prevX, this.prevY, this.prevZ),
                            new Vec3d(this.getX(), this.getY(), this.getZ()), RaycastContext.ShapeType.COLLIDER,
                            RaycastContext.FluidHandling.SOURCE_ONLY, this));

            if (blockHitResult.getType() != HitResult.Type.MISS
                    && this.world.getFluidState(blockHitResult.getBlockPos()).isIn(FluidTags.WATER)) {
                blockPos = blockHitResult.getBlockPos();
                shouldSolidify = true;
            }
        }

        if ((this.verticalCollision && !this.onGround) || shouldSolidify) {
            return true;
        }

        if (blockPos.getY() < this.world.getBottomY() || blockPos.getY() > this.world.getTopY()) {
            if (this.dropItem && this.world.getGameRules().getBoolean(GameRules.DO_ENTITY_DROPS)) {
                Block.dropStacks(this.blockState, this.world, this.getBlockPos());
            }
            this.discard();
        }
        return false;
    }

    public void postTick() {
        if (this.blockState.isAir()) {
            this.discard();
            return;
        }

        double impact = this.getVelocity().length();
        this.prevX = this.getX();
        this.prevY = this.getY();
        this.prevZ = this.getZ();
        if (this.moveTime++ == 0) {
            BlockPos blockPos = this.getBlockPos();
            Block block = this.blockState.getBlock();
            if (this.world.getBlockState(blockPos).isOf(block)) {
                this.world.removeBlock(blockPos, false);
            } else if (!this.world.isClient && !this.partOfSet) {
                this.discard();
                return;
            }
        }

        this.postTickMovement();

        this.move(MovementType.SELF, this.getVelocity());

        if (!FallingBlock.canFallThrough(this.blockState)) {
            List<Entity> otherEntities = this.world.getOtherEntities(this, getBoundingBox().union(getBoundingBox().offset(0, 1 + -2 * this.getVelocity().getY(), 0)));
            otherEntities.forEach(this::postTickEntityCollision);
        }

        if (this.shouldCease(impact)) {
            this.cease();
        }

        // Drag
        this.setVelocity(this.getVelocity().multiply(0.98D));
    }

    @Override
    public boolean handleFallDamage(float distance, float multiplier, DamageSource damageSource) {
        int i = MathHelper.ceil(distance - 1.0F);

        if (!this.hurtEntities || i <= 0) {
            return false;
        }

        boolean flag = this.blockState.isIn(BlockTags.ANVIL);
        DamageSource damageSource2 = flag ? DamageSource.ANVIL : DamageSource.FALLING_BLOCK;
        float f = Math.min(MathHelper.floor((float)i * this.fallHurtAmount), this.fallHurtMax);

        this.world.getOtherEntities(this, getBoundingBox().union(getBoundingBox().offset(0, 1 + -2 * this.getVelocity().getY(), 0))).forEach(entity -> entity.damage(damageSource2, f));

        if (flag && f > 0.0F && this.random.nextFloat() < 0.05F + i * 0.05F) {
            BlockState blockstate = AnvilBlock.getLandingState(this.blockState);
            if (blockstate == null) {
                this.dontSetBlock = true;
            } else this.blockState = blockstate;
        }
        return false;
    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound compound) {
        compound.put("BlockState", NbtHelper.fromBlockState(this.blockState));
        compound.putInt("Time", this.moveTime);
        compound.putBoolean("DropItem", this.dropItem);
        compound.putBoolean("HurtEntities", this.hurtEntities);
        compound.putFloat("FallHurtAmount", this.fallHurtAmount);
        compound.putInt("FallHurtMax", this.fallHurtMax);
        if (this.blockEntityData != null) {
            compound.put("TileEntityData", this.blockEntityData);
        }
    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound compound) {
        this.blockState = NbtHelper.toBlockState(compound.getCompound("BlockState"));
        this.moveTime = compound.getInt("Time");
        if (compound.contains("HurtEntities", 99)) {
            this.hurtEntities = compound.getBoolean("HurtEntities");
            this.fallHurtAmount = compound.getFloat("FallHurtAmount");
            this.fallHurtMax = compound.getInt("FallHurtMax");
        } else if (this.blockState.isIn(BlockTags.ANVIL)) {
            this.hurtEntities = true;
        }

        if (compound.contains("DropItem", 99)) this.dropItem = compound.getBoolean("DropItem");

        if (compound.contains("TileEntityData", 10)) this.blockEntityData = compound.getCompound("TileEntityData");

        if (this.blockState.isAir()) this.blockState = AetherBlocks.GRAVITITE_ORE.getDefaultState();
    }

    @Environment(EnvType.CLIENT)
    public World getWorldObj() {
        return this.world;
    }

    @Override
    public boolean doesRenderOnFire() {
        return false;
    }

    @Override
    public void populateCrashReport(CrashReportSection section) {
        super.populateCrashReport(section);
        section.add("Imitating BlockState", this.blockState.toString());
    }

    public BlockState getBlockState() {
        return this.blockState;
    }

    public void setHurtEntities(boolean hurtEntities) {
        this.hurtEntities = hurtEntities;
    }

    /**
     * End entity movememnt and become a block in the world (Removes this entity).
     */
    public void cease() {
        if (this.isRemoved()) {
            return;
        }
        BlockPos blockPos = this.getBlockPos();
        BlockState blockState = this.world.getBlockState(blockPos);
        if (blockState.isOf(Blocks.MOVING_PISTON)) {
            this.setVelocity(this.getVelocity().multiply(0.7, 0.5, 0.7));
            return;
        }
        if (!this.trySetBlock()) {
            this.breakApart();
        }
    }

    /**
     * Tries to set the block
     * @return {@code true} if the block can be set
     */
    public boolean trySetBlock() {
        BlockPos blockPos = this.getBlockPos();
        BlockState blockState = this.world.getBlockState(blockPos);
        boolean canReplace = blockState.canReplace(new AutomaticItemPlacementContext(this.world, blockPos, Direction.UP, ItemStack.EMPTY, Direction.DOWN));
        boolean canPlace = this.blockState.canPlaceAt(this.world, blockPos);

        if (this.dontSetBlock || !canPlace || !canReplace)
            return false;

        if (this.blockState.contains(Properties.WATERLOGGED) && this.world.getFluidState(blockPos).getFluid() == Fluids.WATER) {
            this.blockState = this.blockState.with(Properties.WATERLOGGED, true);
        }

        if (this.world.setBlockState(blockPos, this.blockState, Block.NOTIFY_ALL)) {
            this.discard();
            if (this.blockEntityData != null && this.blockState.hasBlockEntity()) {
                BlockEntity blockEntity = this.world.getBlockEntity(blockPos);
                if (blockEntity != null) {
                    NbtCompound compoundTag = blockEntity.createNbt();
                    for (String keyName : this.blockEntityData.getKeys()) {
                        NbtElement tag = this.blockEntityData.get(keyName);
                        if (tag != null && !"x".equals(keyName) && !"y".equals(keyName) && !"z".equals(keyName)) {
                            compoundTag.put(keyName, tag.copy());
                        }
                    }
                    blockEntity.readNbt(compoundTag);
                    blockEntity.markDirty();
                }
            }
            return true;
        }
        return false;
    }

    public void breakApart() {
        this.discard();
        if (this.dropItem && this.world.getGameRules().getBoolean(GameRules.DO_ENTITY_DROPS)) {
            Block.dropStacks(this.blockState, this.world, this.getBlockPos());
        }
        // spawn break particles
        world.syncWorldEvent(null, WorldEvents.BLOCK_BROKEN, getBlockPos(), Block.getRawIdFromState(blockState));
    }

    @Override
    public boolean entityDataRequiresOperator() {
        return true;
    }

    @Override
    public Packet<?> createSpawnPacket() {
        return new EntitySpawnS2CPacket(this, Block.getRawIdFromState(this.getBlockState()) * (this.partOfSet ? -1 : 1));
    }

    @Override
    public void onSpawnPacket(EntitySpawnS2CPacket packet) {
        super.onSpawnPacket(packet);
        int data = packet.getEntityData();
        this.partOfSet = data < 0;
        this.blockState = Block.getStateFromRawId(packet.getEntityData() * (this.partOfSet ? -1 : 1));
        this.intersectionChecked = true;
        double d = packet.getX();
        double e = packet.getY();
        double f = packet.getZ();
        this.setPosition(d, e + (double) ((1.0F - this.getHeight()) / 2.0F), f);
        this.setOrigin(this.getBlockPos());
    }

    public void alignWith(BlockLikeEntity other, Vec3i offset) {
        if (this == other) return;
        Vec3d newPos = other.getPos().add(Vec3d.of(offset));
        this.setPos(newPos.x, newPos.y, newPos.z);
        this.setVelocity(other.getVelocity());
    }
}
