package net.id.aether.world.gen.structure;

import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import net.id.aether.Aether;
import net.id.aether.world.gen.structure.generator.SkyrootTowerGenerator;
import net.minecraft.structure.*;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.structure.Structure;
import net.minecraft.world.gen.structure.StructureType;

import java.util.Optional;

public class SkyrootTowerStructure extends Structure {
    public static final Codec<SkyrootTowerStructure> CODEC = createCodec(SkyrootTowerStructure::new);
    
    public SkyrootTowerStructure(Structure.Config config) {
        super(config);
    }

    private static final int X_OFFSET = 4;
    private static final int Z_OFFSET = 4;

    private static void addPieces(StructurePiecesCollector collector, Context context) {
        StructureTemplate structure = context.structureTemplateManager().getTemplateOrBlank(Aether.locate("skyroot_tower"));
        BlockRotation blockRotation = BlockRotation.NONE;
        ChunkPos pos = context.chunkPos();
        BlockPos pivot = new BlockPos(structure.getSize().getX() / 2, 0, structure.getSize().getZ() / 2);
        BlockBox boundingBox = structure.calculateBoundingBox(pos.getStartPos(), blockRotation, pivot, BlockMirror.NONE);
        BlockPos center = boundingBox.getCenter();
        int y = context.chunkGenerator().getHeight(pos.getStartPos().getX() - X_OFFSET, pos.getStartPos().getZ() - Z_OFFSET, Heightmap.Type.WORLD_SURFACE_WG, context.world(), context.noiseConfig());
        if (y < 0) { // DON'T PLACE ON THE BOTTOM OF THE WORLD
            return;
        }
        BlockPos newPos = new BlockPos(pos.getStartPos().getX() - X_OFFSET, y, pos.getStartPos().getZ() - Z_OFFSET);
        SkyrootTowerGenerator.addPieces(context.structureTemplateManager(), collector, BlockRotation.NONE, newPos);
    }
    
    @Override
    public Optional<StructurePosition> getStructurePosition(Context context) {
        context.random().nextDouble();
        ChunkPos chunkPos = context.chunkPos();
        BlockPos blockPos = new BlockPos(chunkPos.getCenterX(), 50, chunkPos.getStartZ());
        StructurePiecesCollector structurePiecesCollector = new StructurePiecesCollector();
        addPieces(structurePiecesCollector, context);
        return Optional.of(new Structure.StructurePosition(blockPos, Either.right(structurePiecesCollector)));
    }
    
    @Override
    public StructureType<?> getType() {
        return ParadiseLostStructures.SKYROOT_TOWER;
    }
}
