package com.linngdu664.drglaserpointer.client.model;// Made with Blockbench 4.10.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

import com.linngdu664.drglaserpointer.Main;
import com.linngdu664.drglaserpointer.entity.LaserPointerLabelEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public class LaserPointerLabelModel<T extends LaserPointerLabelEntity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION_BLUE = new ModelLayerLocation(ResourceLocation.tryBuild(Main.MODID, "textures/models/laser_pointer_label_blue.png"), "main");
	public static final ModelLayerLocation LAYER_LOCATION_RED = new ModelLayerLocation(ResourceLocation.tryBuild(Main.MODID, "textures/models/laser_pointer_label_red.png"), "main");
	public static final ModelLayerLocation LAYER_LOCATION_GREEN = new ModelLayerLocation(ResourceLocation.tryBuild(Main.MODID, "textures/models/laser_pointer_label_green.png"), "main");
	public static final ModelLayerLocation LAYER_LOCATION_YELLOW = new ModelLayerLocation(ResourceLocation.tryBuild(Main.MODID, "textures/models/laser_pointer_label_yellow.png"), "main");
	public static final ModelLayerLocation LAYER_LOCATION_EMPTY = new ModelLayerLocation(ResourceLocation.tryBuild(Main.MODID, "textures/models/laser_pointer_label_empty.png"), "main");
	private final ModelPart ball;

	public ModelPart getBody() {
		return ball;
	}
	public LaserPointerLabelModel(ModelPart root) {
		this.ball = root.getChild("ball");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition ball = partdefinition.addOrReplaceChild("ball", CubeListBuilder.create(), PartPose.offset(0.0F, 5.8F, 0.0F));

		PartDefinition d30c6x = ball.addOrReplaceChild("d30c6x", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition c = d30c6x.addOrReplaceChild("c", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition b90g3x = c.addOrReplaceChild("b90g3x", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.5F));

		PartDefinition b30c3x = b90g3x.addOrReplaceChild("b30c3x", CubeListBuilder.create().texOffs(34, 35).addBox(-1.9851F, 0.1737F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.4851F, -0.1737F, 0.0F));

		PartDefinition cube_r1 = b30c3x.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(30, 35).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r2 = b30c3x.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(32, 35).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.985F, 0.1737F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x2 = b90g3x.addOrReplaceChild("b30c3x2", CubeListBuilder.create().texOffs(28, 35).addBox(0.485F, 1.0397F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -0.5236F));

		PartDefinition cube_r3 = b30c3x2.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(24, 35).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.4701F, 0.866F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r4 = b30c3x2.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(26, 35).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4852F, 1.0397F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x3 = b90g3x.addOrReplaceChild("b30c3x3", CubeListBuilder.create().texOffs(22, 35).addBox(2.6912F, 2.1587F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition cube_r5 = b30c3x3.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(18, 35).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.6763F, 1.985F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r6 = b30c3x3.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(20, 35).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.6914F, 2.1587F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b90g3x2 = c.addOrReplaceChild("b90g3x2", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.5F, 0.0F, 0.0F, -1.5708F));

		PartDefinition b30c3x4 = b90g3x2.addOrReplaceChild("b30c3x4", CubeListBuilder.create().texOffs(16, 35).addBox(3.7309F, 5.8897F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.4851F, -0.1737F, 0.0F));

		PartDefinition cube_r7 = b30c3x4.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(12, 35).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.716F, 5.716F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r8 = b30c3x4.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(14, 35).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.7311F, 5.8897F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x5 = b90g3x2.addOrReplaceChild("b30c3x5", CubeListBuilder.create().texOffs(10, 35).addBox(2.5772F, 8.8479F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -0.5236F));

		PartDefinition cube_r9 = b30c3x5.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(6, 35).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.5623F, 8.6742F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r10 = b30c3x5.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(8, 35).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5774F, 8.8479F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x6 = b90g3x2.addOrReplaceChild("b30c3x6", CubeListBuilder.create().texOffs(4, 35).addBox(0.599F, 9.9669F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition cube_r11 = b30c3x6.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(0, 35).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5841F, 9.7932F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r12 = b30c3x6.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(2, 35).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5992F, 9.9669F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b90g3x3 = c.addOrReplaceChild("b90g3x3", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.5F, 0.0F, 0.0F, -3.1416F));

		PartDefinition b30c3x7 = b90g3x3.addOrReplaceChild("b30c3x7", CubeListBuilder.create().texOffs(34, 34).addBox(-1.9851F, 11.6048F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.4851F, -0.1737F, 0.0F));

		PartDefinition cube_r13 = b30c3x7.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(34, 32).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 11.4311F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r14 = b30c3x7.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(34, 33).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.985F, 11.6047F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x8 = b90g3x3.addOrReplaceChild("b30c3x8", CubeListBuilder.create().texOffs(32, 34).addBox(-5.2305F, 10.9393F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -0.5236F));

		PartDefinition cube_r15 = b30c3x8.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(34, 30).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.2454F, 10.7656F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r16 = b30c3x8.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(34, 31).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.2304F, 10.9393F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x9 = b90g3x3.addOrReplaceChild("b30c3x9", CubeListBuilder.create().texOffs(30, 34).addBox(-7.2084F, 7.8742F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition cube_r17 = b30c3x9.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(34, 28).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.2232F, 7.7005F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r18 = b30c3x9.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(34, 29).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.2082F, 7.8742F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b90g3x4 = c.addOrReplaceChild("b90g3x4", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.5F, 0.0F, 0.0F, 1.5708F));

		PartDefinition b30c3x10 = b90g3x4.addOrReplaceChild("b30c3x10", CubeListBuilder.create().texOffs(28, 34).addBox(-7.7003F, 5.8897F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.4851F, -0.1737F, 0.0F));

		PartDefinition cube_r19 = b30c3x10.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(34, 26).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.7152F, 5.716F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r20 = b30c3x10.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(34, 27).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.7002F, 5.8897F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x11 = b90g3x4.addOrReplaceChild("b30c3x11", CubeListBuilder.create().texOffs(26, 34).addBox(-7.3225F, 3.1323F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -0.5236F));

		PartDefinition cube_r21 = b30c3x11.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(34, 25).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.3374F, 2.9586F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r22 = b30c3x11.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(4, 5).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.3224F, 3.1323F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x12 = b90g3x4.addOrReplaceChild("b30c3x12", CubeListBuilder.create().texOffs(34, 24).addBox(-5.1166F, 0.0672F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition cube_r23 = b30c3x12.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(34, 23).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.1315F, -0.1065F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r24 = b30c3x12.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(24, 34).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.1164F, 0.0672F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition c2 = d30c6x.addOrReplaceChild("c2", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition b90g3x5 = c2.addOrReplaceChild("b90g3x5", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.5F));

		PartDefinition b30c3x13 = b90g3x5.addOrReplaceChild("b30c3x13", CubeListBuilder.create().texOffs(34, 22).addBox(-1.9851F, 0.1737F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.4851F, -0.1737F, 0.0F));

		PartDefinition cube_r25 = b30c3x13.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(34, 21).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r26 = b30c3x13.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(22, 34).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.985F, 0.1737F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x14 = b90g3x5.addOrReplaceChild("b30c3x14", CubeListBuilder.create().texOffs(34, 20).addBox(0.485F, 1.0397F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -0.5236F));

		PartDefinition cube_r27 = b30c3x14.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(34, 19).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.4701F, 0.866F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r28 = b30c3x14.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(20, 34).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4852F, 1.0397F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x15 = b90g3x5.addOrReplaceChild("b30c3x15", CubeListBuilder.create().texOffs(34, 18).addBox(2.6912F, 2.1587F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition cube_r29 = b30c3x15.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(34, 17).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.6763F, 1.985F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r30 = b30c3x15.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(18, 34).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.6914F, 2.1587F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b90g3x6 = c2.addOrReplaceChild("b90g3x6", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.5F, 0.0F, 0.0F, -1.5708F));

		PartDefinition b30c3x16 = b90g3x6.addOrReplaceChild("b30c3x16", CubeListBuilder.create().texOffs(34, 16).addBox(3.7309F, 5.8897F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.4851F, -0.1737F, 0.0F));

		PartDefinition cube_r31 = b30c3x16.addOrReplaceChild("cube_r31", CubeListBuilder.create().texOffs(34, 15).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.716F, 5.716F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r32 = b30c3x16.addOrReplaceChild("cube_r32", CubeListBuilder.create().texOffs(16, 34).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.7311F, 5.8897F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x17 = b90g3x6.addOrReplaceChild("b30c3x17", CubeListBuilder.create().texOffs(34, 14).addBox(2.5772F, 8.8479F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -0.5236F));

		PartDefinition cube_r33 = b30c3x17.addOrReplaceChild("cube_r33", CubeListBuilder.create().texOffs(34, 13).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.5623F, 8.6742F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r34 = b30c3x17.addOrReplaceChild("cube_r34", CubeListBuilder.create().texOffs(14, 34).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5774F, 8.8479F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x18 = b90g3x6.addOrReplaceChild("b30c3x18", CubeListBuilder.create().texOffs(34, 12).addBox(0.599F, 9.9669F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition cube_r35 = b30c3x18.addOrReplaceChild("cube_r35", CubeListBuilder.create().texOffs(34, 11).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5841F, 9.7932F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r36 = b30c3x18.addOrReplaceChild("cube_r36", CubeListBuilder.create().texOffs(12, 34).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5992F, 9.9669F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b90g3x7 = c2.addOrReplaceChild("b90g3x7", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.5F, 0.0F, 0.0F, -3.1416F));

		PartDefinition b30c3x19 = b90g3x7.addOrReplaceChild("b30c3x19", CubeListBuilder.create().texOffs(34, 10).addBox(-1.9851F, 11.6048F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.4851F, -0.1737F, 0.0F));

		PartDefinition cube_r37 = b30c3x19.addOrReplaceChild("cube_r37", CubeListBuilder.create().texOffs(34, 9).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 11.4311F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r38 = b30c3x19.addOrReplaceChild("cube_r38", CubeListBuilder.create().texOffs(10, 34).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.985F, 11.6047F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x20 = b90g3x7.addOrReplaceChild("b30c3x20", CubeListBuilder.create().texOffs(34, 8).addBox(-5.2305F, 10.9393F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -0.5236F));

		PartDefinition cube_r39 = b30c3x20.addOrReplaceChild("cube_r39", CubeListBuilder.create().texOffs(34, 7).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.2454F, 10.7656F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r40 = b30c3x20.addOrReplaceChild("cube_r40", CubeListBuilder.create().texOffs(8, 34).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.2304F, 10.9393F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x21 = b90g3x7.addOrReplaceChild("b30c3x21", CubeListBuilder.create().texOffs(34, 6).addBox(-7.2084F, 7.8742F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition cube_r41 = b30c3x21.addOrReplaceChild("cube_r41", CubeListBuilder.create().texOffs(34, 5).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.2232F, 7.7005F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r42 = b30c3x21.addOrReplaceChild("cube_r42", CubeListBuilder.create().texOffs(6, 34).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.2082F, 7.8742F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b90g3x8 = c2.addOrReplaceChild("b90g3x8", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.5F, 0.0F, 0.0F, 1.5708F));

		PartDefinition b30c3x22 = b90g3x8.addOrReplaceChild("b30c3x22", CubeListBuilder.create().texOffs(34, 4).addBox(-7.7003F, 5.8897F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.4851F, -0.1737F, 0.0F));

		PartDefinition cube_r43 = b30c3x22.addOrReplaceChild("cube_r43", CubeListBuilder.create().texOffs(34, 3).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.7152F, 5.716F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r44 = b30c3x22.addOrReplaceChild("cube_r44", CubeListBuilder.create().texOffs(4, 34).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.7002F, 5.8897F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x23 = b90g3x8.addOrReplaceChild("b30c3x23", CubeListBuilder.create().texOffs(34, 2).addBox(-7.3225F, 3.1323F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -0.5236F));

		PartDefinition cube_r45 = b30c3x23.addOrReplaceChild("cube_r45", CubeListBuilder.create().texOffs(2, 34).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.3374F, 2.9586F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r46 = b30c3x23.addOrReplaceChild("cube_r46", CubeListBuilder.create().texOffs(2, 5).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.3224F, 3.1323F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x24 = b90g3x8.addOrReplaceChild("b30c3x24", CubeListBuilder.create().texOffs(34, 1).addBox(-5.1166F, 0.0672F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition cube_r47 = b30c3x24.addOrReplaceChild("cube_r47", CubeListBuilder.create().texOffs(0, 34).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.1315F, -0.1065F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r48 = b30c3x24.addOrReplaceChild("cube_r48", CubeListBuilder.create().texOffs(34, 0).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.1164F, 0.0672F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition c3 = d30c6x.addOrReplaceChild("c3", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -2.0944F, 0.0F));

		PartDefinition b90g3x9 = c3.addOrReplaceChild("b90g3x9", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.5F));

		PartDefinition b30c3x25 = b90g3x9.addOrReplaceChild("b30c3x25", CubeListBuilder.create().texOffs(32, 33).addBox(-1.9851F, 0.1737F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.4851F, -0.1737F, 0.0F));

		PartDefinition cube_r49 = b30c3x25.addOrReplaceChild("cube_r49", CubeListBuilder.create().texOffs(28, 33).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r50 = b30c3x25.addOrReplaceChild("cube_r50", CubeListBuilder.create().texOffs(30, 33).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.985F, 0.1737F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x26 = b90g3x9.addOrReplaceChild("b30c3x26", CubeListBuilder.create().texOffs(26, 33).addBox(0.485F, 1.0397F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -0.5236F));

		PartDefinition cube_r51 = b30c3x26.addOrReplaceChild("cube_r51", CubeListBuilder.create().texOffs(22, 33).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.4701F, 0.866F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r52 = b30c3x26.addOrReplaceChild("cube_r52", CubeListBuilder.create().texOffs(24, 33).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4852F, 1.0397F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x27 = b90g3x9.addOrReplaceChild("b30c3x27", CubeListBuilder.create().texOffs(20, 33).addBox(2.6912F, 2.1587F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition cube_r53 = b30c3x27.addOrReplaceChild("cube_r53", CubeListBuilder.create().texOffs(16, 33).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.6763F, 1.985F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r54 = b30c3x27.addOrReplaceChild("cube_r54", CubeListBuilder.create().texOffs(18, 33).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.6914F, 2.1587F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b90g3x10 = c3.addOrReplaceChild("b90g3x10", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.5F, 0.0F, 0.0F, -1.5708F));

		PartDefinition b30c3x28 = b90g3x10.addOrReplaceChild("b30c3x28", CubeListBuilder.create().texOffs(14, 33).addBox(3.7309F, 5.8897F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.4851F, -0.1737F, 0.0F));

		PartDefinition cube_r55 = b30c3x28.addOrReplaceChild("cube_r55", CubeListBuilder.create().texOffs(10, 33).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.716F, 5.716F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r56 = b30c3x28.addOrReplaceChild("cube_r56", CubeListBuilder.create().texOffs(12, 33).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.7311F, 5.8897F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x29 = b90g3x10.addOrReplaceChild("b30c3x29", CubeListBuilder.create().texOffs(8, 33).addBox(2.5772F, 8.8479F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -0.5236F));

		PartDefinition cube_r57 = b30c3x29.addOrReplaceChild("cube_r57", CubeListBuilder.create().texOffs(4, 33).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.5623F, 8.6742F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r58 = b30c3x29.addOrReplaceChild("cube_r58", CubeListBuilder.create().texOffs(6, 33).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5774F, 8.8479F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x30 = b90g3x10.addOrReplaceChild("b30c3x30", CubeListBuilder.create().texOffs(2, 33).addBox(0.599F, 9.9669F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition cube_r59 = b30c3x30.addOrReplaceChild("cube_r59", CubeListBuilder.create().texOffs(32, 32).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5841F, 9.7932F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r60 = b30c3x30.addOrReplaceChild("cube_r60", CubeListBuilder.create().texOffs(0, 33).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5992F, 9.9669F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b90g3x11 = c3.addOrReplaceChild("b90g3x11", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.5F, 0.0F, 0.0F, -3.1416F));

		PartDefinition b30c3x31 = b90g3x11.addOrReplaceChild("b30c3x31", CubeListBuilder.create().texOffs(32, 31).addBox(-1.9851F, 11.6048F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.4851F, -0.1737F, 0.0F));

		PartDefinition cube_r61 = b30c3x31.addOrReplaceChild("cube_r61", CubeListBuilder.create().texOffs(30, 32).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 11.4311F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r62 = b30c3x31.addOrReplaceChild("cube_r62", CubeListBuilder.create().texOffs(32, 30).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.985F, 11.6047F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x32 = b90g3x11.addOrReplaceChild("b30c3x32", CubeListBuilder.create().texOffs(32, 29).addBox(-5.2305F, 10.9393F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -0.5236F));

		PartDefinition cube_r63 = b30c3x32.addOrReplaceChild("cube_r63", CubeListBuilder.create().texOffs(28, 32).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.2454F, 10.7656F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r64 = b30c3x32.addOrReplaceChild("cube_r64", CubeListBuilder.create().texOffs(32, 28).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.2304F, 10.9393F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x33 = b90g3x11.addOrReplaceChild("b30c3x33", CubeListBuilder.create().texOffs(32, 27).addBox(-7.2084F, 7.8742F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition cube_r65 = b30c3x33.addOrReplaceChild("cube_r65", CubeListBuilder.create().texOffs(26, 32).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.2232F, 7.7005F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r66 = b30c3x33.addOrReplaceChild("cube_r66", CubeListBuilder.create().texOffs(32, 26).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.2082F, 7.8742F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b90g3x12 = c3.addOrReplaceChild("b90g3x12", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.5F, 0.0F, 0.0F, 1.5708F));

		PartDefinition b30c3x34 = b90g3x12.addOrReplaceChild("b30c3x34", CubeListBuilder.create().texOffs(32, 25).addBox(-7.7003F, 5.8897F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.4851F, -0.1737F, 0.0F));

		PartDefinition cube_r67 = b30c3x34.addOrReplaceChild("cube_r67", CubeListBuilder.create().texOffs(24, 32).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.7152F, 5.716F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r68 = b30c3x34.addOrReplaceChild("cube_r68", CubeListBuilder.create().texOffs(32, 24).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.7002F, 5.8897F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x35 = b90g3x12.addOrReplaceChild("b30c3x35", CubeListBuilder.create().texOffs(32, 23).addBox(-7.3225F, 3.1323F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -0.5236F));

		PartDefinition cube_r69 = b30c3x35.addOrReplaceChild("cube_r69", CubeListBuilder.create().texOffs(32, 22).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.3374F, 2.9586F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r70 = b30c3x35.addOrReplaceChild("cube_r70", CubeListBuilder.create().texOffs(0, 5).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.3224F, 3.1323F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x36 = b90g3x12.addOrReplaceChild("b30c3x36", CubeListBuilder.create().texOffs(22, 32).addBox(-5.1166F, 0.0672F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition cube_r71 = b30c3x36.addOrReplaceChild("cube_r71", CubeListBuilder.create().texOffs(32, 20).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.1315F, -0.1065F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r72 = b30c3x36.addOrReplaceChild("cube_r72", CubeListBuilder.create().texOffs(32, 21).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.1164F, 0.0672F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition c4 = d30c6x.addOrReplaceChild("c4", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -2.618F, 0.0F));

		PartDefinition b90g3x13 = c4.addOrReplaceChild("b90g3x13", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.5F));

		PartDefinition b30c3x37 = b90g3x13.addOrReplaceChild("b30c3x37", CubeListBuilder.create().texOffs(20, 32).addBox(-1.9851F, 0.1737F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.4851F, -0.1737F, 0.0F));

		PartDefinition cube_r73 = b30c3x37.addOrReplaceChild("cube_r73", CubeListBuilder.create().texOffs(32, 18).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r74 = b30c3x37.addOrReplaceChild("cube_r74", CubeListBuilder.create().texOffs(32, 19).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.985F, 0.1737F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x38 = b90g3x13.addOrReplaceChild("b30c3x38", CubeListBuilder.create().texOffs(18, 32).addBox(0.485F, 1.0397F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -0.5236F));

		PartDefinition cube_r75 = b30c3x38.addOrReplaceChild("cube_r75", CubeListBuilder.create().texOffs(32, 16).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.4701F, 0.866F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r76 = b30c3x38.addOrReplaceChild("cube_r76", CubeListBuilder.create().texOffs(32, 17).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4852F, 1.0397F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x39 = b90g3x13.addOrReplaceChild("b30c3x39", CubeListBuilder.create().texOffs(16, 32).addBox(2.6912F, 2.1587F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition cube_r77 = b30c3x39.addOrReplaceChild("cube_r77", CubeListBuilder.create().texOffs(32, 14).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.6763F, 1.985F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r78 = b30c3x39.addOrReplaceChild("cube_r78", CubeListBuilder.create().texOffs(32, 15).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.6914F, 2.1587F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b90g3x14 = c4.addOrReplaceChild("b90g3x14", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.5F, 0.0F, 0.0F, -1.5708F));

		PartDefinition b30c3x40 = b90g3x14.addOrReplaceChild("b30c3x40", CubeListBuilder.create().texOffs(14, 32).addBox(3.7309F, 5.8897F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.4851F, -0.1737F, 0.0F));

		PartDefinition cube_r79 = b30c3x40.addOrReplaceChild("cube_r79", CubeListBuilder.create().texOffs(32, 12).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.716F, 5.716F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r80 = b30c3x40.addOrReplaceChild("cube_r80", CubeListBuilder.create().texOffs(32, 13).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.7311F, 5.8897F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x41 = b90g3x14.addOrReplaceChild("b30c3x41", CubeListBuilder.create().texOffs(12, 32).addBox(2.5772F, 8.8479F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -0.5236F));

		PartDefinition cube_r81 = b30c3x41.addOrReplaceChild("cube_r81", CubeListBuilder.create().texOffs(32, 10).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.5623F, 8.6742F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r82 = b30c3x41.addOrReplaceChild("cube_r82", CubeListBuilder.create().texOffs(32, 11).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5774F, 8.8479F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x42 = b90g3x14.addOrReplaceChild("b30c3x42", CubeListBuilder.create().texOffs(10, 32).addBox(0.599F, 9.9669F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition cube_r83 = b30c3x42.addOrReplaceChild("cube_r83", CubeListBuilder.create().texOffs(32, 8).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5841F, 9.7932F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r84 = b30c3x42.addOrReplaceChild("cube_r84", CubeListBuilder.create().texOffs(32, 9).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5992F, 9.9669F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b90g3x15 = c4.addOrReplaceChild("b90g3x15", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.5F, 0.0F, 0.0F, -3.1416F));

		PartDefinition b30c3x43 = b90g3x15.addOrReplaceChild("b30c3x43", CubeListBuilder.create().texOffs(8, 32).addBox(-1.9851F, 11.6048F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.4851F, -0.1737F, 0.0F));

		PartDefinition cube_r85 = b30c3x43.addOrReplaceChild("cube_r85", CubeListBuilder.create().texOffs(32, 6).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 11.4311F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r86 = b30c3x43.addOrReplaceChild("cube_r86", CubeListBuilder.create().texOffs(32, 7).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.985F, 11.6047F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x44 = b90g3x15.addOrReplaceChild("b30c3x44", CubeListBuilder.create().texOffs(6, 32).addBox(-5.2305F, 10.9393F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -0.5236F));

		PartDefinition cube_r87 = b30c3x44.addOrReplaceChild("cube_r87", CubeListBuilder.create().texOffs(32, 4).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.2454F, 10.7656F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r88 = b30c3x44.addOrReplaceChild("cube_r88", CubeListBuilder.create().texOffs(32, 5).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.2304F, 10.9393F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x45 = b90g3x15.addOrReplaceChild("b30c3x45", CubeListBuilder.create().texOffs(4, 32).addBox(-7.2084F, 7.8742F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition cube_r89 = b30c3x45.addOrReplaceChild("cube_r89", CubeListBuilder.create().texOffs(32, 2).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.2232F, 7.7005F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r90 = b30c3x45.addOrReplaceChild("cube_r90", CubeListBuilder.create().texOffs(32, 3).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.2082F, 7.8742F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b90g3x16 = c4.addOrReplaceChild("b90g3x16", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.5F, 0.0F, 0.0F, 1.5708F));

		PartDefinition b30c3x46 = b90g3x16.addOrReplaceChild("b30c3x46", CubeListBuilder.create().texOffs(2, 32).addBox(-7.7003F, 5.8897F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.4851F, -0.1737F, 0.0F));

		PartDefinition cube_r91 = b30c3x46.addOrReplaceChild("cube_r91", CubeListBuilder.create().texOffs(32, 0).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.7152F, 5.716F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r92 = b30c3x46.addOrReplaceChild("cube_r92", CubeListBuilder.create().texOffs(32, 1).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.7002F, 5.8897F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x47 = b90g3x16.addOrReplaceChild("b30c3x47", CubeListBuilder.create().texOffs(0, 32).addBox(-7.3225F, 3.1323F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -0.5236F));

		PartDefinition cube_r93 = b30c3x47.addOrReplaceChild("cube_r93", CubeListBuilder.create().texOffs(30, 31).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.3374F, 2.9586F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r94 = b30c3x47.addOrReplaceChild("cube_r94", CubeListBuilder.create().texOffs(4, 4).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.3224F, 3.1323F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x48 = b90g3x16.addOrReplaceChild("b30c3x48", CubeListBuilder.create().texOffs(28, 31).addBox(-5.1166F, 0.0672F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition cube_r95 = b30c3x48.addOrReplaceChild("cube_r95", CubeListBuilder.create().texOffs(24, 31).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.1315F, -0.1065F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r96 = b30c3x48.addOrReplaceChild("cube_r96", CubeListBuilder.create().texOffs(26, 31).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.1164F, 0.0672F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition c5 = d30c6x.addOrReplaceChild("c5", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

		PartDefinition b90g3x17 = c5.addOrReplaceChild("b90g3x17", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.5F));

		PartDefinition b30c3x49 = b90g3x17.addOrReplaceChild("b30c3x49", CubeListBuilder.create().texOffs(22, 31).addBox(-1.9851F, 0.1737F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.4851F, -0.1737F, 0.0F));

		PartDefinition cube_r97 = b30c3x49.addOrReplaceChild("cube_r97", CubeListBuilder.create().texOffs(18, 31).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r98 = b30c3x49.addOrReplaceChild("cube_r98", CubeListBuilder.create().texOffs(20, 31).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.985F, 0.1737F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x50 = b90g3x17.addOrReplaceChild("b30c3x50", CubeListBuilder.create().texOffs(16, 31).addBox(0.485F, 1.0397F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -0.5236F));

		PartDefinition cube_r99 = b30c3x50.addOrReplaceChild("cube_r99", CubeListBuilder.create().texOffs(12, 31).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.4701F, 0.866F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r100 = b30c3x50.addOrReplaceChild("cube_r100", CubeListBuilder.create().texOffs(14, 31).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4852F, 1.0397F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x51 = b90g3x17.addOrReplaceChild("b30c3x51", CubeListBuilder.create().texOffs(10, 31).addBox(2.6912F, 2.1587F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition cube_r101 = b30c3x51.addOrReplaceChild("cube_r101", CubeListBuilder.create().texOffs(6, 31).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.6763F, 1.985F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r102 = b30c3x51.addOrReplaceChild("cube_r102", CubeListBuilder.create().texOffs(8, 31).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.6914F, 2.1587F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b90g3x18 = c5.addOrReplaceChild("b90g3x18", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.5F, 0.0F, 0.0F, -1.5708F));

		PartDefinition b30c3x52 = b90g3x18.addOrReplaceChild("b30c3x52", CubeListBuilder.create().texOffs(4, 31).addBox(3.7309F, 5.8897F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.4851F, -0.1737F, 0.0F));

		PartDefinition cube_r103 = b30c3x52.addOrReplaceChild("cube_r103", CubeListBuilder.create().texOffs(0, 31).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.716F, 5.716F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r104 = b30c3x52.addOrReplaceChild("cube_r104", CubeListBuilder.create().texOffs(2, 31).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.7311F, 5.8897F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x53 = b90g3x18.addOrReplaceChild("b30c3x53", CubeListBuilder.create().texOffs(30, 30).addBox(2.5772F, 8.8479F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -0.5236F));

		PartDefinition cube_r105 = b30c3x53.addOrReplaceChild("cube_r105", CubeListBuilder.create().texOffs(30, 28).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.5623F, 8.6742F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r106 = b30c3x53.addOrReplaceChild("cube_r106", CubeListBuilder.create().texOffs(30, 29).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5774F, 8.8479F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x54 = b90g3x18.addOrReplaceChild("b30c3x54", CubeListBuilder.create().texOffs(28, 30).addBox(0.599F, 9.9669F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition cube_r107 = b30c3x54.addOrReplaceChild("cube_r107", CubeListBuilder.create().texOffs(30, 26).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5841F, 9.7932F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r108 = b30c3x54.addOrReplaceChild("cube_r108", CubeListBuilder.create().texOffs(30, 27).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5992F, 9.9669F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b90g3x19 = c5.addOrReplaceChild("b90g3x19", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.5F, 0.0F, 0.0F, -3.1416F));

		PartDefinition b30c3x55 = b90g3x19.addOrReplaceChild("b30c3x55", CubeListBuilder.create().texOffs(26, 30).addBox(-1.9851F, 11.6048F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.4851F, -0.1737F, 0.0F));

		PartDefinition cube_r109 = b30c3x55.addOrReplaceChild("cube_r109", CubeListBuilder.create().texOffs(30, 24).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 11.4311F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r110 = b30c3x55.addOrReplaceChild("cube_r110", CubeListBuilder.create().texOffs(30, 25).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.985F, 11.6047F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x56 = b90g3x19.addOrReplaceChild("b30c3x56", CubeListBuilder.create().texOffs(24, 30).addBox(-5.2305F, 10.9393F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -0.5236F));

		PartDefinition cube_r111 = b30c3x56.addOrReplaceChild("cube_r111", CubeListBuilder.create().texOffs(30, 22).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.2454F, 10.7656F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r112 = b30c3x56.addOrReplaceChild("cube_r112", CubeListBuilder.create().texOffs(30, 23).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.2304F, 10.9393F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x57 = b90g3x19.addOrReplaceChild("b30c3x57", CubeListBuilder.create().texOffs(22, 30).addBox(-7.2084F, 7.8742F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition cube_r113 = b30c3x57.addOrReplaceChild("cube_r113", CubeListBuilder.create().texOffs(30, 20).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.2232F, 7.7005F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r114 = b30c3x57.addOrReplaceChild("cube_r114", CubeListBuilder.create().texOffs(30, 21).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.2082F, 7.8742F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b90g3x20 = c5.addOrReplaceChild("b90g3x20", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.5F, 0.0F, 0.0F, 1.5708F));

		PartDefinition b30c3x58 = b90g3x20.addOrReplaceChild("b30c3x58", CubeListBuilder.create().texOffs(20, 30).addBox(-7.7003F, 5.8897F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.4851F, -0.1737F, 0.0F));

		PartDefinition cube_r115 = b30c3x58.addOrReplaceChild("cube_r115", CubeListBuilder.create().texOffs(30, 18).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.7152F, 5.716F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r116 = b30c3x58.addOrReplaceChild("cube_r116", CubeListBuilder.create().texOffs(30, 19).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.7002F, 5.8897F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x59 = b90g3x20.addOrReplaceChild("b30c3x59", CubeListBuilder.create().texOffs(18, 30).addBox(-7.3225F, 3.1323F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -0.5236F));

		PartDefinition cube_r117 = b30c3x59.addOrReplaceChild("cube_r117", CubeListBuilder.create().texOffs(30, 17).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.3374F, 2.9586F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r118 = b30c3x59.addOrReplaceChild("cube_r118", CubeListBuilder.create().texOffs(4, 3).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.3224F, 3.1323F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x60 = b90g3x20.addOrReplaceChild("b30c3x60", CubeListBuilder.create().texOffs(30, 16).addBox(-5.1166F, 0.0672F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition cube_r119 = b30c3x60.addOrReplaceChild("cube_r119", CubeListBuilder.create().texOffs(30, 15).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.1315F, -0.1065F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r120 = b30c3x60.addOrReplaceChild("cube_r120", CubeListBuilder.create().texOffs(16, 30).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.1164F, 0.0672F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition c6 = d30c6x.addOrReplaceChild("c6", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

		PartDefinition b90g3x21 = c6.addOrReplaceChild("b90g3x21", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.5F));

		PartDefinition b30c3x61 = b90g3x21.addOrReplaceChild("b30c3x61", CubeListBuilder.create().texOffs(30, 14).addBox(-1.9851F, 0.1737F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.4851F, -0.1737F, 0.0F));

		PartDefinition cube_r121 = b30c3x61.addOrReplaceChild("cube_r121", CubeListBuilder.create().texOffs(30, 13).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r122 = b30c3x61.addOrReplaceChild("cube_r122", CubeListBuilder.create().texOffs(14, 30).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.985F, 0.1737F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x62 = b90g3x21.addOrReplaceChild("b30c3x62", CubeListBuilder.create().texOffs(30, 12).addBox(0.485F, 1.0397F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -0.5236F));

		PartDefinition cube_r123 = b30c3x62.addOrReplaceChild("cube_r123", CubeListBuilder.create().texOffs(30, 11).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.4701F, 0.866F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r124 = b30c3x62.addOrReplaceChild("cube_r124", CubeListBuilder.create().texOffs(12, 30).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4852F, 1.0397F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x63 = b90g3x21.addOrReplaceChild("b30c3x63", CubeListBuilder.create().texOffs(30, 10).addBox(2.6912F, 2.1587F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition cube_r125 = b30c3x63.addOrReplaceChild("cube_r125", CubeListBuilder.create().texOffs(30, 9).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.6763F, 1.985F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r126 = b30c3x63.addOrReplaceChild("cube_r126", CubeListBuilder.create().texOffs(10, 30).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.6914F, 2.1587F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b90g3x22 = c6.addOrReplaceChild("b90g3x22", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.5F, 0.0F, 0.0F, -1.5708F));

		PartDefinition b30c3x64 = b90g3x22.addOrReplaceChild("b30c3x64", CubeListBuilder.create().texOffs(30, 8).addBox(3.7309F, 5.8897F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.4851F, -0.1737F, 0.0F));

		PartDefinition cube_r127 = b30c3x64.addOrReplaceChild("cube_r127", CubeListBuilder.create().texOffs(30, 7).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.716F, 5.716F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r128 = b30c3x64.addOrReplaceChild("cube_r128", CubeListBuilder.create().texOffs(8, 30).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.7311F, 5.8897F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x65 = b90g3x22.addOrReplaceChild("b30c3x65", CubeListBuilder.create().texOffs(30, 6).addBox(2.5772F, 8.8479F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -0.5236F));

		PartDefinition cube_r129 = b30c3x65.addOrReplaceChild("cube_r129", CubeListBuilder.create().texOffs(30, 5).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.5623F, 8.6742F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r130 = b30c3x65.addOrReplaceChild("cube_r130", CubeListBuilder.create().texOffs(6, 30).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5774F, 8.8479F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x66 = b90g3x22.addOrReplaceChild("b30c3x66", CubeListBuilder.create().texOffs(30, 4).addBox(0.599F, 9.9669F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition cube_r131 = b30c3x66.addOrReplaceChild("cube_r131", CubeListBuilder.create().texOffs(30, 3).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5841F, 9.7932F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r132 = b30c3x66.addOrReplaceChild("cube_r132", CubeListBuilder.create().texOffs(4, 30).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5992F, 9.9669F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b90g3x23 = c6.addOrReplaceChild("b90g3x23", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.5F, 0.0F, 0.0F, -3.1416F));

		PartDefinition b30c3x67 = b90g3x23.addOrReplaceChild("b30c3x67", CubeListBuilder.create().texOffs(30, 2).addBox(-1.9851F, 11.6048F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.4851F, -0.1737F, 0.0F));

		PartDefinition cube_r133 = b30c3x67.addOrReplaceChild("cube_r133", CubeListBuilder.create().texOffs(30, 1).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 11.4311F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r134 = b30c3x67.addOrReplaceChild("cube_r134", CubeListBuilder.create().texOffs(2, 30).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.985F, 11.6047F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x68 = b90g3x23.addOrReplaceChild("b30c3x68", CubeListBuilder.create().texOffs(30, 0).addBox(-5.2305F, 10.9393F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -0.5236F));

		PartDefinition cube_r135 = b30c3x68.addOrReplaceChild("cube_r135", CubeListBuilder.create().texOffs(28, 29).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.2454F, 10.7656F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r136 = b30c3x68.addOrReplaceChild("cube_r136", CubeListBuilder.create().texOffs(0, 30).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.2304F, 10.9393F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x69 = b90g3x23.addOrReplaceChild("b30c3x69", CubeListBuilder.create().texOffs(26, 29).addBox(-7.2084F, 7.8742F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition cube_r137 = b30c3x69.addOrReplaceChild("cube_r137", CubeListBuilder.create().texOffs(22, 29).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.2232F, 7.7005F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r138 = b30c3x69.addOrReplaceChild("cube_r138", CubeListBuilder.create().texOffs(24, 29).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.2082F, 7.8742F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b90g3x24 = c6.addOrReplaceChild("b90g3x24", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.5F, 0.0F, 0.0F, 1.5708F));

		PartDefinition b30c3x70 = b90g3x24.addOrReplaceChild("b30c3x70", CubeListBuilder.create().texOffs(20, 29).addBox(-7.7003F, 5.8897F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.4851F, -0.1737F, 0.0F));

		PartDefinition cube_r139 = b30c3x70.addOrReplaceChild("cube_r139", CubeListBuilder.create().texOffs(16, 29).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.7152F, 5.716F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r140 = b30c3x70.addOrReplaceChild("cube_r140", CubeListBuilder.create().texOffs(18, 29).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.7002F, 5.8897F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x71 = b90g3x24.addOrReplaceChild("b30c3x71", CubeListBuilder.create().texOffs(14, 29).addBox(-7.3225F, 3.1323F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -0.5236F));

		PartDefinition cube_r141 = b30c3x71.addOrReplaceChild("cube_r141", CubeListBuilder.create().texOffs(12, 29).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.3374F, 2.9586F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r142 = b30c3x71.addOrReplaceChild("cube_r142", CubeListBuilder.create().texOffs(4, 2).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.3224F, 3.1323F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x72 = b90g3x24.addOrReplaceChild("b30c3x72", CubeListBuilder.create().texOffs(10, 29).addBox(-5.1166F, 0.0672F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition cube_r143 = b30c3x72.addOrReplaceChild("cube_r143", CubeListBuilder.create().texOffs(6, 29).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.1315F, -0.1065F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r144 = b30c3x72.addOrReplaceChild("cube_r144", CubeListBuilder.create().texOffs(8, 29).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.1164F, 0.0672F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition d30c6x2 = ball.addOrReplaceChild("d30c6x2", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.1745F, 0.0F));

		PartDefinition c7 = d30c6x2.addOrReplaceChild("c7", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition b90g3x25 = c7.addOrReplaceChild("b90g3x25", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.5F));

		PartDefinition b30c3x73 = b90g3x25.addOrReplaceChild("b30c3x73", CubeListBuilder.create().texOffs(4, 29).addBox(-1.9851F, 0.1737F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.4851F, -0.1737F, 0.0F));

		PartDefinition cube_r145 = b30c3x73.addOrReplaceChild("cube_r145", CubeListBuilder.create().texOffs(0, 29).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r146 = b30c3x73.addOrReplaceChild("cube_r146", CubeListBuilder.create().texOffs(2, 29).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.985F, 0.1737F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x74 = b90g3x25.addOrReplaceChild("b30c3x74", CubeListBuilder.create().texOffs(28, 28).addBox(0.485F, 1.0397F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -0.5236F));

		PartDefinition cube_r147 = b30c3x74.addOrReplaceChild("cube_r147", CubeListBuilder.create().texOffs(28, 26).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.4701F, 0.866F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r148 = b30c3x74.addOrReplaceChild("cube_r148", CubeListBuilder.create().texOffs(28, 27).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4852F, 1.0397F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x75 = b90g3x25.addOrReplaceChild("b30c3x75", CubeListBuilder.create().texOffs(26, 28).addBox(2.6912F, 2.1587F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition cube_r149 = b30c3x75.addOrReplaceChild("cube_r149", CubeListBuilder.create().texOffs(28, 24).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.6763F, 1.985F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r150 = b30c3x75.addOrReplaceChild("cube_r150", CubeListBuilder.create().texOffs(28, 25).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.6914F, 2.1587F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b90g3x26 = c7.addOrReplaceChild("b90g3x26", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.5F, 0.0F, 0.0F, -1.5708F));

		PartDefinition b30c3x76 = b90g3x26.addOrReplaceChild("b30c3x76", CubeListBuilder.create().texOffs(24, 28).addBox(3.7309F, 5.8897F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.4851F, -0.1737F, 0.0F));

		PartDefinition cube_r151 = b30c3x76.addOrReplaceChild("cube_r151", CubeListBuilder.create().texOffs(28, 22).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.716F, 5.716F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r152 = b30c3x76.addOrReplaceChild("cube_r152", CubeListBuilder.create().texOffs(28, 23).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.7311F, 5.8897F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x77 = b90g3x26.addOrReplaceChild("b30c3x77", CubeListBuilder.create().texOffs(22, 28).addBox(2.5772F, 8.8479F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -0.5236F));

		PartDefinition cube_r153 = b30c3x77.addOrReplaceChild("cube_r153", CubeListBuilder.create().texOffs(28, 20).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.5623F, 8.6742F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r154 = b30c3x77.addOrReplaceChild("cube_r154", CubeListBuilder.create().texOffs(28, 21).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5774F, 8.8479F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x78 = b90g3x26.addOrReplaceChild("b30c3x78", CubeListBuilder.create().texOffs(20, 28).addBox(0.599F, 9.9669F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition cube_r155 = b30c3x78.addOrReplaceChild("cube_r155", CubeListBuilder.create().texOffs(28, 18).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5841F, 9.7932F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r156 = b30c3x78.addOrReplaceChild("cube_r156", CubeListBuilder.create().texOffs(28, 19).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5992F, 9.9669F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b90g3x27 = c7.addOrReplaceChild("b90g3x27", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.5F, 0.0F, 0.0F, -3.1416F));

		PartDefinition b30c3x79 = b90g3x27.addOrReplaceChild("b30c3x79", CubeListBuilder.create().texOffs(18, 28).addBox(-1.9851F, 11.6048F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.4851F, -0.1737F, 0.0F));

		PartDefinition cube_r157 = b30c3x79.addOrReplaceChild("cube_r157", CubeListBuilder.create().texOffs(28, 16).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 11.4311F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r158 = b30c3x79.addOrReplaceChild("cube_r158", CubeListBuilder.create().texOffs(28, 17).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.985F, 11.6047F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x80 = b90g3x27.addOrReplaceChild("b30c3x80", CubeListBuilder.create().texOffs(16, 28).addBox(-5.2305F, 10.9393F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -0.5236F));

		PartDefinition cube_r159 = b30c3x80.addOrReplaceChild("cube_r159", CubeListBuilder.create().texOffs(28, 14).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.2454F, 10.7656F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r160 = b30c3x80.addOrReplaceChild("cube_r160", CubeListBuilder.create().texOffs(28, 15).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.2304F, 10.9393F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x81 = b90g3x27.addOrReplaceChild("b30c3x81", CubeListBuilder.create().texOffs(14, 28).addBox(-7.2084F, 7.8742F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition cube_r161 = b30c3x81.addOrReplaceChild("cube_r161", CubeListBuilder.create().texOffs(28, 12).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.2232F, 7.7005F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r162 = b30c3x81.addOrReplaceChild("cube_r162", CubeListBuilder.create().texOffs(28, 13).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.2082F, 7.8742F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b90g3x28 = c7.addOrReplaceChild("b90g3x28", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.5F, 0.0F, 0.0F, 1.5708F));

		PartDefinition b30c3x82 = b90g3x28.addOrReplaceChild("b30c3x82", CubeListBuilder.create().texOffs(12, 28).addBox(-7.7003F, 5.8897F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.4851F, -0.1737F, 0.0F));

		PartDefinition cube_r163 = b30c3x82.addOrReplaceChild("cube_r163", CubeListBuilder.create().texOffs(28, 10).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.7152F, 5.716F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r164 = b30c3x82.addOrReplaceChild("cube_r164", CubeListBuilder.create().texOffs(28, 11).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.7002F, 5.8897F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x83 = b90g3x28.addOrReplaceChild("b30c3x83", CubeListBuilder.create().texOffs(10, 28).addBox(-7.3225F, 3.1323F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -0.5236F));

		PartDefinition cube_r165 = b30c3x83.addOrReplaceChild("cube_r165", CubeListBuilder.create().texOffs(28, 9).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.3374F, 2.9586F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r166 = b30c3x83.addOrReplaceChild("cube_r166", CubeListBuilder.create().texOffs(2, 4).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.3224F, 3.1323F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x84 = b90g3x28.addOrReplaceChild("b30c3x84", CubeListBuilder.create().texOffs(28, 8).addBox(-5.1166F, 0.0672F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition cube_r167 = b30c3x84.addOrReplaceChild("cube_r167", CubeListBuilder.create().texOffs(28, 7).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.1315F, -0.1065F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r168 = b30c3x84.addOrReplaceChild("cube_r168", CubeListBuilder.create().texOffs(8, 28).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.1164F, 0.0672F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition c8 = d30c6x2.addOrReplaceChild("c8", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition b90g3x29 = c8.addOrReplaceChild("b90g3x29", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.5F));

		PartDefinition b30c3x85 = b90g3x29.addOrReplaceChild("b30c3x85", CubeListBuilder.create().texOffs(28, 6).addBox(-1.9851F, 0.1737F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.4851F, -0.1737F, 0.0F));

		PartDefinition cube_r169 = b30c3x85.addOrReplaceChild("cube_r169", CubeListBuilder.create().texOffs(28, 5).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r170 = b30c3x85.addOrReplaceChild("cube_r170", CubeListBuilder.create().texOffs(6, 28).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.985F, 0.1737F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x86 = b90g3x29.addOrReplaceChild("b30c3x86", CubeListBuilder.create().texOffs(28, 4).addBox(0.485F, 1.0397F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -0.5236F));

		PartDefinition cube_r171 = b30c3x86.addOrReplaceChild("cube_r171", CubeListBuilder.create().texOffs(28, 3).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.4701F, 0.866F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r172 = b30c3x86.addOrReplaceChild("cube_r172", CubeListBuilder.create().texOffs(4, 28).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4852F, 1.0397F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x87 = b90g3x29.addOrReplaceChild("b30c3x87", CubeListBuilder.create().texOffs(28, 2).addBox(2.6912F, 2.1587F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition cube_r173 = b30c3x87.addOrReplaceChild("cube_r173", CubeListBuilder.create().texOffs(28, 1).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.6763F, 1.985F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r174 = b30c3x87.addOrReplaceChild("cube_r174", CubeListBuilder.create().texOffs(2, 28).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.6914F, 2.1587F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b90g3x30 = c8.addOrReplaceChild("b90g3x30", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.5F, 0.0F, 0.0F, -1.5708F));

		PartDefinition b30c3x88 = b90g3x30.addOrReplaceChild("b30c3x88", CubeListBuilder.create().texOffs(28, 0).addBox(3.7309F, 5.8897F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.4851F, -0.1737F, 0.0F));

		PartDefinition cube_r175 = b30c3x88.addOrReplaceChild("cube_r175", CubeListBuilder.create().texOffs(26, 27).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.716F, 5.716F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r176 = b30c3x88.addOrReplaceChild("cube_r176", CubeListBuilder.create().texOffs(0, 28).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.7311F, 5.8897F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x89 = b90g3x30.addOrReplaceChild("b30c3x89", CubeListBuilder.create().texOffs(24, 27).addBox(2.5772F, 8.8479F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -0.5236F));

		PartDefinition cube_r177 = b30c3x89.addOrReplaceChild("cube_r177", CubeListBuilder.create().texOffs(20, 27).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.5623F, 8.6742F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r178 = b30c3x89.addOrReplaceChild("cube_r178", CubeListBuilder.create().texOffs(22, 27).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5774F, 8.8479F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x90 = b90g3x30.addOrReplaceChild("b30c3x90", CubeListBuilder.create().texOffs(18, 27).addBox(0.599F, 9.9669F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition cube_r179 = b30c3x90.addOrReplaceChild("cube_r179", CubeListBuilder.create().texOffs(14, 27).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5841F, 9.7932F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r180 = b30c3x90.addOrReplaceChild("cube_r180", CubeListBuilder.create().texOffs(16, 27).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5992F, 9.9669F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b90g3x31 = c8.addOrReplaceChild("b90g3x31", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.5F, 0.0F, 0.0F, -3.1416F));

		PartDefinition b30c3x91 = b90g3x31.addOrReplaceChild("b30c3x91", CubeListBuilder.create().texOffs(12, 27).addBox(-1.9851F, 11.6048F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.4851F, -0.1737F, 0.0F));

		PartDefinition cube_r181 = b30c3x91.addOrReplaceChild("cube_r181", CubeListBuilder.create().texOffs(8, 27).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 11.4311F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r182 = b30c3x91.addOrReplaceChild("cube_r182", CubeListBuilder.create().texOffs(10, 27).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.985F, 11.6047F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x92 = b90g3x31.addOrReplaceChild("b30c3x92", CubeListBuilder.create().texOffs(6, 27).addBox(-5.2305F, 10.9393F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -0.5236F));

		PartDefinition cube_r183 = b30c3x92.addOrReplaceChild("cube_r183", CubeListBuilder.create().texOffs(2, 27).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.2454F, 10.7656F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r184 = b30c3x92.addOrReplaceChild("cube_r184", CubeListBuilder.create().texOffs(4, 27).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.2304F, 10.9393F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x93 = b90g3x31.addOrReplaceChild("b30c3x93", CubeListBuilder.create().texOffs(0, 27).addBox(-7.2084F, 7.8742F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition cube_r185 = b30c3x93.addOrReplaceChild("cube_r185", CubeListBuilder.create().texOffs(26, 25).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.2232F, 7.7005F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r186 = b30c3x93.addOrReplaceChild("cube_r186", CubeListBuilder.create().texOffs(26, 26).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.2082F, 7.8742F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b90g3x32 = c8.addOrReplaceChild("b90g3x32", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.5F, 0.0F, 0.0F, 1.5708F));

		PartDefinition b30c3x94 = b90g3x32.addOrReplaceChild("b30c3x94", CubeListBuilder.create().texOffs(26, 24).addBox(-7.7003F, 5.8897F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.4851F, -0.1737F, 0.0F));

		PartDefinition cube_r187 = b30c3x94.addOrReplaceChild("cube_r187", CubeListBuilder.create().texOffs(26, 23).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.7152F, 5.716F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r188 = b30c3x94.addOrReplaceChild("cube_r188", CubeListBuilder.create().texOffs(24, 26).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.7002F, 5.8897F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x95 = b90g3x32.addOrReplaceChild("b30c3x95", CubeListBuilder.create().texOffs(26, 22).addBox(-7.3225F, 3.1323F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -0.5236F));

		PartDefinition cube_r189 = b30c3x95.addOrReplaceChild("cube_r189", CubeListBuilder.create().texOffs(22, 26).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.3374F, 2.9586F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r190 = b30c3x95.addOrReplaceChild("cube_r190", CubeListBuilder.create().texOffs(4, 1).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.3224F, 3.1323F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x96 = b90g3x32.addOrReplaceChild("b30c3x96", CubeListBuilder.create().texOffs(26, 21).addBox(-5.1166F, 0.0672F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition cube_r191 = b30c3x96.addOrReplaceChild("cube_r191", CubeListBuilder.create().texOffs(20, 26).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.1315F, -0.1065F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r192 = b30c3x96.addOrReplaceChild("cube_r192", CubeListBuilder.create().texOffs(26, 20).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.1164F, 0.0672F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition c9 = d30c6x2.addOrReplaceChild("c9", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -2.0944F, 0.0F));

		PartDefinition b90g3x33 = c9.addOrReplaceChild("b90g3x33", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.5F));

		PartDefinition b30c3x97 = b90g3x33.addOrReplaceChild("b30c3x97", CubeListBuilder.create().texOffs(26, 19).addBox(-1.9851F, 0.1737F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.4851F, -0.1737F, 0.0F));

		PartDefinition cube_r193 = b30c3x97.addOrReplaceChild("cube_r193", CubeListBuilder.create().texOffs(18, 26).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r194 = b30c3x97.addOrReplaceChild("cube_r194", CubeListBuilder.create().texOffs(26, 18).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.985F, 0.1737F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x98 = b90g3x33.addOrReplaceChild("b30c3x98", CubeListBuilder.create().texOffs(26, 17).addBox(0.485F, 1.0397F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -0.5236F));

		PartDefinition cube_r195 = b30c3x98.addOrReplaceChild("cube_r195", CubeListBuilder.create().texOffs(16, 26).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.4701F, 0.866F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r196 = b30c3x98.addOrReplaceChild("cube_r196", CubeListBuilder.create().texOffs(26, 16).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4852F, 1.0397F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x99 = b90g3x33.addOrReplaceChild("b30c3x99", CubeListBuilder.create().texOffs(26, 15).addBox(2.6912F, 2.1587F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition cube_r197 = b30c3x99.addOrReplaceChild("cube_r197", CubeListBuilder.create().texOffs(14, 26).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.6763F, 1.985F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r198 = b30c3x99.addOrReplaceChild("cube_r198", CubeListBuilder.create().texOffs(26, 14).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.6914F, 2.1587F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b90g3x34 = c9.addOrReplaceChild("b90g3x34", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.5F, 0.0F, 0.0F, -1.5708F));

		PartDefinition b30c3x100 = b90g3x34.addOrReplaceChild("b30c3x100", CubeListBuilder.create().texOffs(26, 13).addBox(3.7309F, 5.8897F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.4851F, -0.1737F, 0.0F));

		PartDefinition cube_r199 = b30c3x100.addOrReplaceChild("cube_r199", CubeListBuilder.create().texOffs(12, 26).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.716F, 5.716F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r200 = b30c3x100.addOrReplaceChild("cube_r200", CubeListBuilder.create().texOffs(26, 12).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.7311F, 5.8897F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x101 = b90g3x34.addOrReplaceChild("b30c3x101", CubeListBuilder.create().texOffs(26, 11).addBox(2.5772F, 8.8479F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -0.5236F));

		PartDefinition cube_r201 = b30c3x101.addOrReplaceChild("cube_r201", CubeListBuilder.create().texOffs(10, 26).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.5623F, 8.6742F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r202 = b30c3x101.addOrReplaceChild("cube_r202", CubeListBuilder.create().texOffs(26, 10).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5774F, 8.8479F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x102 = b90g3x34.addOrReplaceChild("b30c3x102", CubeListBuilder.create().texOffs(26, 9).addBox(0.599F, 9.9669F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition cube_r203 = b30c3x102.addOrReplaceChild("cube_r203", CubeListBuilder.create().texOffs(8, 26).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5841F, 9.7932F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r204 = b30c3x102.addOrReplaceChild("cube_r204", CubeListBuilder.create().texOffs(26, 8).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5992F, 9.9669F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b90g3x35 = c9.addOrReplaceChild("b90g3x35", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.5F, 0.0F, 0.0F, -3.1416F));

		PartDefinition b30c3x103 = b90g3x35.addOrReplaceChild("b30c3x103", CubeListBuilder.create().texOffs(26, 7).addBox(-1.9851F, 11.6048F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.4851F, -0.1737F, 0.0F));

		PartDefinition cube_r205 = b30c3x103.addOrReplaceChild("cube_r205", CubeListBuilder.create().texOffs(6, 26).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 11.4311F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r206 = b30c3x103.addOrReplaceChild("cube_r206", CubeListBuilder.create().texOffs(26, 6).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.985F, 11.6047F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x104 = b90g3x35.addOrReplaceChild("b30c3x104", CubeListBuilder.create().texOffs(26, 5).addBox(-5.2305F, 10.9393F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -0.5236F));

		PartDefinition cube_r207 = b30c3x104.addOrReplaceChild("cube_r207", CubeListBuilder.create().texOffs(4, 26).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.2454F, 10.7656F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r208 = b30c3x104.addOrReplaceChild("cube_r208", CubeListBuilder.create().texOffs(26, 4).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.2304F, 10.9393F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x105 = b90g3x35.addOrReplaceChild("b30c3x105", CubeListBuilder.create().texOffs(26, 3).addBox(-7.2084F, 7.8742F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition cube_r209 = b30c3x105.addOrReplaceChild("cube_r209", CubeListBuilder.create().texOffs(2, 26).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.2232F, 7.7005F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r210 = b30c3x105.addOrReplaceChild("cube_r210", CubeListBuilder.create().texOffs(26, 2).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.2082F, 7.8742F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b90g3x36 = c9.addOrReplaceChild("b90g3x36", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.5F, 0.0F, 0.0F, 1.5708F));

		PartDefinition b30c3x106 = b90g3x36.addOrReplaceChild("b30c3x106", CubeListBuilder.create().texOffs(26, 1).addBox(-7.7003F, 5.8897F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.4851F, -0.1737F, 0.0F));

		PartDefinition cube_r211 = b30c3x106.addOrReplaceChild("cube_r211", CubeListBuilder.create().texOffs(0, 26).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.7152F, 5.716F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r212 = b30c3x106.addOrReplaceChild("cube_r212", CubeListBuilder.create().texOffs(26, 0).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.7002F, 5.8897F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x107 = b90g3x36.addOrReplaceChild("b30c3x107", CubeListBuilder.create().texOffs(24, 25).addBox(-7.3225F, 3.1323F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -0.5236F));

		PartDefinition cube_r213 = b30c3x107.addOrReplaceChild("cube_r213", CubeListBuilder.create().texOffs(22, 25).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.3374F, 2.9586F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r214 = b30c3x107.addOrReplaceChild("cube_r214", CubeListBuilder.create().texOffs(4, 0).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.3224F, 3.1323F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x108 = b90g3x36.addOrReplaceChild("b30c3x108", CubeListBuilder.create().texOffs(20, 25).addBox(-5.1166F, 0.0672F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition cube_r215 = b30c3x108.addOrReplaceChild("cube_r215", CubeListBuilder.create().texOffs(16, 25).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.1315F, -0.1065F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r216 = b30c3x108.addOrReplaceChild("cube_r216", CubeListBuilder.create().texOffs(18, 25).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.1164F, 0.0672F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition c10 = d30c6x2.addOrReplaceChild("c10", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -2.618F, 0.0F));

		PartDefinition b90g3x37 = c10.addOrReplaceChild("b90g3x37", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.5F));

		PartDefinition b30c3x109 = b90g3x37.addOrReplaceChild("b30c3x109", CubeListBuilder.create().texOffs(14, 25).addBox(-1.9851F, 0.1737F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.4851F, -0.1737F, 0.0F));

		PartDefinition cube_r217 = b30c3x109.addOrReplaceChild("cube_r217", CubeListBuilder.create().texOffs(10, 25).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r218 = b30c3x109.addOrReplaceChild("cube_r218", CubeListBuilder.create().texOffs(12, 25).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.985F, 0.1737F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x110 = b90g3x37.addOrReplaceChild("b30c3x110", CubeListBuilder.create().texOffs(8, 25).addBox(0.485F, 1.0397F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -0.5236F));

		PartDefinition cube_r219 = b30c3x110.addOrReplaceChild("cube_r219", CubeListBuilder.create().texOffs(4, 25).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.4701F, 0.866F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r220 = b30c3x110.addOrReplaceChild("cube_r220", CubeListBuilder.create().texOffs(6, 25).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4852F, 1.0397F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x111 = b90g3x37.addOrReplaceChild("b30c3x111", CubeListBuilder.create().texOffs(2, 25).addBox(2.6912F, 2.1587F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition cube_r221 = b30c3x111.addOrReplaceChild("cube_r221", CubeListBuilder.create().texOffs(24, 24).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.6763F, 1.985F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r222 = b30c3x111.addOrReplaceChild("cube_r222", CubeListBuilder.create().texOffs(0, 25).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.6914F, 2.1587F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b90g3x38 = c10.addOrReplaceChild("b90g3x38", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.5F, 0.0F, 0.0F, -1.5708F));

		PartDefinition b30c3x112 = b90g3x38.addOrReplaceChild("b30c3x112", CubeListBuilder.create().texOffs(24, 23).addBox(3.7309F, 5.8897F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.4851F, -0.1737F, 0.0F));

		PartDefinition cube_r223 = b30c3x112.addOrReplaceChild("cube_r223", CubeListBuilder.create().texOffs(22, 24).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.716F, 5.716F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r224 = b30c3x112.addOrReplaceChild("cube_r224", CubeListBuilder.create().texOffs(24, 22).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.7311F, 5.8897F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x113 = b90g3x38.addOrReplaceChild("b30c3x113", CubeListBuilder.create().texOffs(24, 21).addBox(2.5772F, 8.8479F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -0.5236F));

		PartDefinition cube_r225 = b30c3x113.addOrReplaceChild("cube_r225", CubeListBuilder.create().texOffs(20, 24).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.5623F, 8.6742F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r226 = b30c3x113.addOrReplaceChild("cube_r226", CubeListBuilder.create().texOffs(24, 20).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5774F, 8.8479F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x114 = b90g3x38.addOrReplaceChild("b30c3x114", CubeListBuilder.create().texOffs(24, 19).addBox(0.599F, 9.9669F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition cube_r227 = b30c3x114.addOrReplaceChild("cube_r227", CubeListBuilder.create().texOffs(18, 24).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5841F, 9.7932F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r228 = b30c3x114.addOrReplaceChild("cube_r228", CubeListBuilder.create().texOffs(24, 18).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5992F, 9.9669F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b90g3x39 = c10.addOrReplaceChild("b90g3x39", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.5F, 0.0F, 0.0F, -3.1416F));

		PartDefinition b30c3x115 = b90g3x39.addOrReplaceChild("b30c3x115", CubeListBuilder.create().texOffs(24, 17).addBox(-1.9851F, 11.6048F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.4851F, -0.1737F, 0.0F));

		PartDefinition cube_r229 = b30c3x115.addOrReplaceChild("cube_r229", CubeListBuilder.create().texOffs(16, 24).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 11.4311F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r230 = b30c3x115.addOrReplaceChild("cube_r230", CubeListBuilder.create().texOffs(24, 16).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.985F, 11.6047F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x116 = b90g3x39.addOrReplaceChild("b30c3x116", CubeListBuilder.create().texOffs(24, 15).addBox(-5.2305F, 10.9393F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -0.5236F));

		PartDefinition cube_r231 = b30c3x116.addOrReplaceChild("cube_r231", CubeListBuilder.create().texOffs(14, 24).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.2454F, 10.7656F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r232 = b30c3x116.addOrReplaceChild("cube_r232", CubeListBuilder.create().texOffs(24, 14).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.2304F, 10.9393F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x117 = b90g3x39.addOrReplaceChild("b30c3x117", CubeListBuilder.create().texOffs(24, 13).addBox(-7.2084F, 7.8742F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition cube_r233 = b30c3x117.addOrReplaceChild("cube_r233", CubeListBuilder.create().texOffs(12, 24).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.2232F, 7.7005F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r234 = b30c3x117.addOrReplaceChild("cube_r234", CubeListBuilder.create().texOffs(24, 12).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.2082F, 7.8742F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b90g3x40 = c10.addOrReplaceChild("b90g3x40", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.5F, 0.0F, 0.0F, 1.5708F));

		PartDefinition b30c3x118 = b90g3x40.addOrReplaceChild("b30c3x118", CubeListBuilder.create().texOffs(24, 11).addBox(-7.7003F, 5.8897F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.4851F, -0.1737F, 0.0F));

		PartDefinition cube_r235 = b30c3x118.addOrReplaceChild("cube_r235", CubeListBuilder.create().texOffs(10, 24).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.7152F, 5.716F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r236 = b30c3x118.addOrReplaceChild("cube_r236", CubeListBuilder.create().texOffs(24, 10).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.7002F, 5.8897F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x119 = b90g3x40.addOrReplaceChild("b30c3x119", CubeListBuilder.create().texOffs(24, 9).addBox(-7.3225F, 3.1323F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -0.5236F));

		PartDefinition cube_r237 = b30c3x119.addOrReplaceChild("cube_r237", CubeListBuilder.create().texOffs(24, 8).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.3374F, 2.9586F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r238 = b30c3x119.addOrReplaceChild("cube_r238", CubeListBuilder.create().texOffs(0, 4).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.3224F, 3.1323F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x120 = b90g3x40.addOrReplaceChild("b30c3x120", CubeListBuilder.create().texOffs(8, 24).addBox(-5.1166F, 0.0672F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition cube_r239 = b30c3x120.addOrReplaceChild("cube_r239", CubeListBuilder.create().texOffs(24, 6).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.1315F, -0.1065F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r240 = b30c3x120.addOrReplaceChild("cube_r240", CubeListBuilder.create().texOffs(24, 7).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.1164F, 0.0672F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition c11 = d30c6x2.addOrReplaceChild("c11", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

		PartDefinition b90g3x41 = c11.addOrReplaceChild("b90g3x41", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.5F));

		PartDefinition b30c3x121 = b90g3x41.addOrReplaceChild("b30c3x121", CubeListBuilder.create().texOffs(6, 24).addBox(-1.9851F, 0.1737F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.4851F, -0.1737F, 0.0F));

		PartDefinition cube_r241 = b30c3x121.addOrReplaceChild("cube_r241", CubeListBuilder.create().texOffs(24, 4).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r242 = b30c3x121.addOrReplaceChild("cube_r242", CubeListBuilder.create().texOffs(24, 5).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.985F, 0.1737F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x122 = b90g3x41.addOrReplaceChild("b30c3x122", CubeListBuilder.create().texOffs(4, 24).addBox(0.485F, 1.0397F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -0.5236F));

		PartDefinition cube_r243 = b30c3x122.addOrReplaceChild("cube_r243", CubeListBuilder.create().texOffs(24, 2).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.4701F, 0.866F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r244 = b30c3x122.addOrReplaceChild("cube_r244", CubeListBuilder.create().texOffs(24, 3).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4852F, 1.0397F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x123 = b90g3x41.addOrReplaceChild("b30c3x123", CubeListBuilder.create().texOffs(2, 24).addBox(2.6912F, 2.1587F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition cube_r245 = b30c3x123.addOrReplaceChild("cube_r245", CubeListBuilder.create().texOffs(24, 0).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.6763F, 1.985F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r246 = b30c3x123.addOrReplaceChild("cube_r246", CubeListBuilder.create().texOffs(24, 1).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.6914F, 2.1587F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b90g3x42 = c11.addOrReplaceChild("b90g3x42", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.5F, 0.0F, 0.0F, -1.5708F));

		PartDefinition b30c3x124 = b90g3x42.addOrReplaceChild("b30c3x124", CubeListBuilder.create().texOffs(0, 24).addBox(3.7309F, 5.8897F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.4851F, -0.1737F, 0.0F));

		PartDefinition cube_r247 = b30c3x124.addOrReplaceChild("cube_r247", CubeListBuilder.create().texOffs(20, 23).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.716F, 5.716F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r248 = b30c3x124.addOrReplaceChild("cube_r248", CubeListBuilder.create().texOffs(22, 23).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.7311F, 5.8897F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x125 = b90g3x42.addOrReplaceChild("b30c3x125", CubeListBuilder.create().texOffs(18, 23).addBox(2.5772F, 8.8479F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -0.5236F));

		PartDefinition cube_r249 = b30c3x125.addOrReplaceChild("cube_r249", CubeListBuilder.create().texOffs(14, 23).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.5623F, 8.6742F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r250 = b30c3x125.addOrReplaceChild("cube_r250", CubeListBuilder.create().texOffs(16, 23).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5774F, 8.8479F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x126 = b90g3x42.addOrReplaceChild("b30c3x126", CubeListBuilder.create().texOffs(12, 23).addBox(0.599F, 9.9669F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition cube_r251 = b30c3x126.addOrReplaceChild("cube_r251", CubeListBuilder.create().texOffs(8, 23).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5841F, 9.7932F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r252 = b30c3x126.addOrReplaceChild("cube_r252", CubeListBuilder.create().texOffs(10, 23).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5992F, 9.9669F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b90g3x43 = c11.addOrReplaceChild("b90g3x43", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.5F, 0.0F, 0.0F, -3.1416F));

		PartDefinition b30c3x127 = b90g3x43.addOrReplaceChild("b30c3x127", CubeListBuilder.create().texOffs(6, 23).addBox(-1.9851F, 11.6048F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.4851F, -0.1737F, 0.0F));

		PartDefinition cube_r253 = b30c3x127.addOrReplaceChild("cube_r253", CubeListBuilder.create().texOffs(2, 23).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 11.4311F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r254 = b30c3x127.addOrReplaceChild("cube_r254", CubeListBuilder.create().texOffs(4, 23).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.985F, 11.6047F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x128 = b90g3x43.addOrReplaceChild("b30c3x128", CubeListBuilder.create().texOffs(0, 23).addBox(-5.2305F, 10.9393F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -0.5236F));

		PartDefinition cube_r255 = b30c3x128.addOrReplaceChild("cube_r255", CubeListBuilder.create().texOffs(22, 21).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.2454F, 10.7656F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r256 = b30c3x128.addOrReplaceChild("cube_r256", CubeListBuilder.create().texOffs(22, 22).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.2304F, 10.9393F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x129 = b90g3x43.addOrReplaceChild("b30c3x129", CubeListBuilder.create().texOffs(22, 20).addBox(-7.2084F, 7.8742F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition cube_r257 = b30c3x129.addOrReplaceChild("cube_r257", CubeListBuilder.create().texOffs(22, 19).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.2232F, 7.7005F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r258 = b30c3x129.addOrReplaceChild("cube_r258", CubeListBuilder.create().texOffs(20, 22).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.2082F, 7.8742F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b90g3x44 = c11.addOrReplaceChild("b90g3x44", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.5F, 0.0F, 0.0F, 1.5708F));

		PartDefinition b30c3x130 = b90g3x44.addOrReplaceChild("b30c3x130", CubeListBuilder.create().texOffs(22, 18).addBox(-7.7003F, 5.8897F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.4851F, -0.1737F, 0.0F));

		PartDefinition cube_r259 = b30c3x130.addOrReplaceChild("cube_r259", CubeListBuilder.create().texOffs(22, 17).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.7152F, 5.716F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r260 = b30c3x130.addOrReplaceChild("cube_r260", CubeListBuilder.create().texOffs(18, 22).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.7002F, 5.8897F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x131 = b90g3x44.addOrReplaceChild("b30c3x131", CubeListBuilder.create().texOffs(22, 16).addBox(-7.3225F, 3.1323F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -0.5236F));

		PartDefinition cube_r261 = b30c3x131.addOrReplaceChild("cube_r261", CubeListBuilder.create().texOffs(16, 22).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.3374F, 2.9586F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r262 = b30c3x131.addOrReplaceChild("cube_r262", CubeListBuilder.create().texOffs(2, 3).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.3224F, 3.1323F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x132 = b90g3x44.addOrReplaceChild("b30c3x132", CubeListBuilder.create().texOffs(22, 15).addBox(-5.1166F, 0.0672F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition cube_r263 = b30c3x132.addOrReplaceChild("cube_r263", CubeListBuilder.create().texOffs(14, 22).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.1315F, -0.1065F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r264 = b30c3x132.addOrReplaceChild("cube_r264", CubeListBuilder.create().texOffs(22, 14).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.1164F, 0.0672F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition c12 = d30c6x2.addOrReplaceChild("c12", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

		PartDefinition b90g3x45 = c12.addOrReplaceChild("b90g3x45", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.5F));

		PartDefinition b30c3x133 = b90g3x45.addOrReplaceChild("b30c3x133", CubeListBuilder.create().texOffs(22, 13).addBox(-1.9851F, 0.1737F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.4851F, -0.1737F, 0.0F));

		PartDefinition cube_r265 = b30c3x133.addOrReplaceChild("cube_r265", CubeListBuilder.create().texOffs(12, 22).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r266 = b30c3x133.addOrReplaceChild("cube_r266", CubeListBuilder.create().texOffs(22, 12).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.985F, 0.1737F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x134 = b90g3x45.addOrReplaceChild("b30c3x134", CubeListBuilder.create().texOffs(22, 11).addBox(0.485F, 1.0397F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -0.5236F));

		PartDefinition cube_r267 = b30c3x134.addOrReplaceChild("cube_r267", CubeListBuilder.create().texOffs(10, 22).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.4701F, 0.866F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r268 = b30c3x134.addOrReplaceChild("cube_r268", CubeListBuilder.create().texOffs(22, 10).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4852F, 1.0397F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x135 = b90g3x45.addOrReplaceChild("b30c3x135", CubeListBuilder.create().texOffs(22, 9).addBox(2.6912F, 2.1587F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition cube_r269 = b30c3x135.addOrReplaceChild("cube_r269", CubeListBuilder.create().texOffs(8, 22).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.6763F, 1.985F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r270 = b30c3x135.addOrReplaceChild("cube_r270", CubeListBuilder.create().texOffs(22, 8).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.6914F, 2.1587F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b90g3x46 = c12.addOrReplaceChild("b90g3x46", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.5F, 0.0F, 0.0F, -1.5708F));

		PartDefinition b30c3x136 = b90g3x46.addOrReplaceChild("b30c3x136", CubeListBuilder.create().texOffs(22, 7).addBox(3.7309F, 5.8897F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.4851F, -0.1737F, 0.0F));

		PartDefinition cube_r271 = b30c3x136.addOrReplaceChild("cube_r271", CubeListBuilder.create().texOffs(6, 22).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.716F, 5.716F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r272 = b30c3x136.addOrReplaceChild("cube_r272", CubeListBuilder.create().texOffs(22, 6).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.7311F, 5.8897F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x137 = b90g3x46.addOrReplaceChild("b30c3x137", CubeListBuilder.create().texOffs(22, 5).addBox(2.5772F, 8.8479F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -0.5236F));

		PartDefinition cube_r273 = b30c3x137.addOrReplaceChild("cube_r273", CubeListBuilder.create().texOffs(4, 22).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.5623F, 8.6742F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r274 = b30c3x137.addOrReplaceChild("cube_r274", CubeListBuilder.create().texOffs(22, 4).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5774F, 8.8479F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x138 = b90g3x46.addOrReplaceChild("b30c3x138", CubeListBuilder.create().texOffs(22, 3).addBox(0.599F, 9.9669F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition cube_r275 = b30c3x138.addOrReplaceChild("cube_r275", CubeListBuilder.create().texOffs(2, 22).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5841F, 9.7932F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r276 = b30c3x138.addOrReplaceChild("cube_r276", CubeListBuilder.create().texOffs(22, 2).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5992F, 9.9669F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b90g3x47 = c12.addOrReplaceChild("b90g3x47", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.5F, 0.0F, 0.0F, -3.1416F));

		PartDefinition b30c3x139 = b90g3x47.addOrReplaceChild("b30c3x139", CubeListBuilder.create().texOffs(22, 1).addBox(-1.9851F, 11.6048F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.4851F, -0.1737F, 0.0F));

		PartDefinition cube_r277 = b30c3x139.addOrReplaceChild("cube_r277", CubeListBuilder.create().texOffs(0, 22).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 11.4311F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r278 = b30c3x139.addOrReplaceChild("cube_r278", CubeListBuilder.create().texOffs(22, 0).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.985F, 11.6047F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x140 = b90g3x47.addOrReplaceChild("b30c3x140", CubeListBuilder.create().texOffs(20, 21).addBox(-5.2305F, 10.9393F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -0.5236F));

		PartDefinition cube_r279 = b30c3x140.addOrReplaceChild("cube_r279", CubeListBuilder.create().texOffs(16, 21).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.2454F, 10.7656F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r280 = b30c3x140.addOrReplaceChild("cube_r280", CubeListBuilder.create().texOffs(18, 21).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.2304F, 10.9393F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x141 = b90g3x47.addOrReplaceChild("b30c3x141", CubeListBuilder.create().texOffs(14, 21).addBox(-7.2084F, 7.8742F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition cube_r281 = b30c3x141.addOrReplaceChild("cube_r281", CubeListBuilder.create().texOffs(10, 21).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.2232F, 7.7005F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r282 = b30c3x141.addOrReplaceChild("cube_r282", CubeListBuilder.create().texOffs(12, 21).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.2082F, 7.8742F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b90g3x48 = c12.addOrReplaceChild("b90g3x48", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.5F, 0.0F, 0.0F, 1.5708F));

		PartDefinition b30c3x142 = b90g3x48.addOrReplaceChild("b30c3x142", CubeListBuilder.create().texOffs(8, 21).addBox(-7.7003F, 5.8897F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.4851F, -0.1737F, 0.0F));

		PartDefinition cube_r283 = b30c3x142.addOrReplaceChild("cube_r283", CubeListBuilder.create().texOffs(4, 21).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.7152F, 5.716F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r284 = b30c3x142.addOrReplaceChild("cube_r284", CubeListBuilder.create().texOffs(6, 21).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.7002F, 5.8897F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x143 = b90g3x48.addOrReplaceChild("b30c3x143", CubeListBuilder.create().texOffs(2, 21).addBox(-7.3225F, 3.1323F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -0.5236F));

		PartDefinition cube_r285 = b30c3x143.addOrReplaceChild("cube_r285", CubeListBuilder.create().texOffs(0, 21).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.3374F, 2.9586F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r286 = b30c3x143.addOrReplaceChild("cube_r286", CubeListBuilder.create().texOffs(0, 3).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.3224F, 3.1323F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x144 = b90g3x48.addOrReplaceChild("b30c3x144", CubeListBuilder.create().texOffs(20, 20).addBox(-5.1166F, 0.0672F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition cube_r287 = b30c3x144.addOrReplaceChild("cube_r287", CubeListBuilder.create().texOffs(20, 18).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.1315F, -0.1065F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r288 = b30c3x144.addOrReplaceChild("cube_r288", CubeListBuilder.create().texOffs(20, 19).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.1164F, 0.0672F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition d30c6x3 = ball.addOrReplaceChild("d30c6x3", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.3491F, 0.0F));

		PartDefinition c13 = d30c6x3.addOrReplaceChild("c13", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition b90g3x49 = c13.addOrReplaceChild("b90g3x49", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.5F));

		PartDefinition b30c3x145 = b90g3x49.addOrReplaceChild("b30c3x145", CubeListBuilder.create().texOffs(18, 20).addBox(-1.9851F, 0.1737F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.4851F, -0.1737F, 0.0F));

		PartDefinition cube_r289 = b30c3x145.addOrReplaceChild("cube_r289", CubeListBuilder.create().texOffs(20, 16).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r290 = b30c3x145.addOrReplaceChild("cube_r290", CubeListBuilder.create().texOffs(20, 17).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.985F, 0.1737F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x146 = b90g3x49.addOrReplaceChild("b30c3x146", CubeListBuilder.create().texOffs(16, 20).addBox(0.485F, 1.0397F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -0.5236F));

		PartDefinition cube_r291 = b30c3x146.addOrReplaceChild("cube_r291", CubeListBuilder.create().texOffs(20, 14).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.4701F, 0.866F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r292 = b30c3x146.addOrReplaceChild("cube_r292", CubeListBuilder.create().texOffs(20, 15).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4852F, 1.0397F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x147 = b90g3x49.addOrReplaceChild("b30c3x147", CubeListBuilder.create().texOffs(14, 20).addBox(2.6912F, 2.1587F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition cube_r293 = b30c3x147.addOrReplaceChild("cube_r293", CubeListBuilder.create().texOffs(20, 12).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.6763F, 1.985F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r294 = b30c3x147.addOrReplaceChild("cube_r294", CubeListBuilder.create().texOffs(20, 13).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.6914F, 2.1587F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b90g3x50 = c13.addOrReplaceChild("b90g3x50", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.5F, 0.0F, 0.0F, -1.5708F));

		PartDefinition b30c3x148 = b90g3x50.addOrReplaceChild("b30c3x148", CubeListBuilder.create().texOffs(12, 20).addBox(3.7309F, 5.8897F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.4851F, -0.1737F, 0.0F));

		PartDefinition cube_r295 = b30c3x148.addOrReplaceChild("cube_r295", CubeListBuilder.create().texOffs(20, 10).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.716F, 5.716F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r296 = b30c3x148.addOrReplaceChild("cube_r296", CubeListBuilder.create().texOffs(20, 11).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.7311F, 5.8897F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x149 = b90g3x50.addOrReplaceChild("b30c3x149", CubeListBuilder.create().texOffs(10, 20).addBox(2.5772F, 8.8479F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -0.5236F));

		PartDefinition cube_r297 = b30c3x149.addOrReplaceChild("cube_r297", CubeListBuilder.create().texOffs(20, 8).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.5623F, 8.6742F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r298 = b30c3x149.addOrReplaceChild("cube_r298", CubeListBuilder.create().texOffs(20, 9).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5774F, 8.8479F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x150 = b90g3x50.addOrReplaceChild("b30c3x150", CubeListBuilder.create().texOffs(8, 20).addBox(0.599F, 9.9669F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition cube_r299 = b30c3x150.addOrReplaceChild("cube_r299", CubeListBuilder.create().texOffs(20, 6).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5841F, 9.7932F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r300 = b30c3x150.addOrReplaceChild("cube_r300", CubeListBuilder.create().texOffs(20, 7).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5992F, 9.9669F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b90g3x51 = c13.addOrReplaceChild("b90g3x51", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.5F, 0.0F, 0.0F, -3.1416F));

		PartDefinition b30c3x151 = b90g3x51.addOrReplaceChild("b30c3x151", CubeListBuilder.create().texOffs(6, 20).addBox(-1.9851F, 11.6048F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.4851F, -0.1737F, 0.0F));

		PartDefinition cube_r301 = b30c3x151.addOrReplaceChild("cube_r301", CubeListBuilder.create().texOffs(20, 4).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 11.4311F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r302 = b30c3x151.addOrReplaceChild("cube_r302", CubeListBuilder.create().texOffs(20, 5).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.985F, 11.6047F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x152 = b90g3x51.addOrReplaceChild("b30c3x152", CubeListBuilder.create().texOffs(4, 20).addBox(-5.2305F, 10.9393F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -0.5236F));

		PartDefinition cube_r303 = b30c3x152.addOrReplaceChild("cube_r303", CubeListBuilder.create().texOffs(20, 2).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.2454F, 10.7656F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r304 = b30c3x152.addOrReplaceChild("cube_r304", CubeListBuilder.create().texOffs(20, 3).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.2304F, 10.9393F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x153 = b90g3x51.addOrReplaceChild("b30c3x153", CubeListBuilder.create().texOffs(2, 20).addBox(-7.2084F, 7.8742F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition cube_r305 = b30c3x153.addOrReplaceChild("cube_r305", CubeListBuilder.create().texOffs(20, 0).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.2232F, 7.7005F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r306 = b30c3x153.addOrReplaceChild("cube_r306", CubeListBuilder.create().texOffs(20, 1).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.2082F, 7.8742F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b90g3x52 = c13.addOrReplaceChild("b90g3x52", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.5F, 0.0F, 0.0F, 1.5708F));

		PartDefinition b30c3x154 = b90g3x52.addOrReplaceChild("b30c3x154", CubeListBuilder.create().texOffs(0, 20).addBox(-7.7003F, 5.8897F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.4851F, -0.1737F, 0.0F));

		PartDefinition cube_r307 = b30c3x154.addOrReplaceChild("cube_r307", CubeListBuilder.create().texOffs(16, 19).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.7152F, 5.716F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r308 = b30c3x154.addOrReplaceChild("cube_r308", CubeListBuilder.create().texOffs(18, 19).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.7002F, 5.8897F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x155 = b90g3x52.addOrReplaceChild("b30c3x155", CubeListBuilder.create().texOffs(14, 19).addBox(-7.3225F, 3.1323F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -0.5236F));

		PartDefinition cube_r309 = b30c3x155.addOrReplaceChild("cube_r309", CubeListBuilder.create().texOffs(12, 19).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.3374F, 2.9586F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r310 = b30c3x155.addOrReplaceChild("cube_r310", CubeListBuilder.create().texOffs(2, 2).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.3224F, 3.1323F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x156 = b90g3x52.addOrReplaceChild("b30c3x156", CubeListBuilder.create().texOffs(10, 19).addBox(-5.1166F, 0.0672F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition cube_r311 = b30c3x156.addOrReplaceChild("cube_r311", CubeListBuilder.create().texOffs(6, 19).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.1315F, -0.1065F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r312 = b30c3x156.addOrReplaceChild("cube_r312", CubeListBuilder.create().texOffs(8, 19).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.1164F, 0.0672F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition c14 = d30c6x3.addOrReplaceChild("c14", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition b90g3x53 = c14.addOrReplaceChild("b90g3x53", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.5F));

		PartDefinition b30c3x157 = b90g3x53.addOrReplaceChild("b30c3x157", CubeListBuilder.create().texOffs(4, 19).addBox(-1.9851F, 0.1737F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.4851F, -0.1737F, 0.0F));

		PartDefinition cube_r313 = b30c3x157.addOrReplaceChild("cube_r313", CubeListBuilder.create().texOffs(0, 19).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r314 = b30c3x157.addOrReplaceChild("cube_r314", CubeListBuilder.create().texOffs(2, 19).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.985F, 0.1737F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x158 = b90g3x53.addOrReplaceChild("b30c3x158", CubeListBuilder.create().texOffs(18, 18).addBox(0.485F, 1.0397F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -0.5236F));

		PartDefinition cube_r315 = b30c3x158.addOrReplaceChild("cube_r315", CubeListBuilder.create().texOffs(18, 16).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.4701F, 0.866F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r316 = b30c3x158.addOrReplaceChild("cube_r316", CubeListBuilder.create().texOffs(18, 17).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4852F, 1.0397F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x159 = b90g3x53.addOrReplaceChild("b30c3x159", CubeListBuilder.create().texOffs(16, 18).addBox(2.6912F, 2.1587F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition cube_r317 = b30c3x159.addOrReplaceChild("cube_r317", CubeListBuilder.create().texOffs(18, 14).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.6763F, 1.985F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r318 = b30c3x159.addOrReplaceChild("cube_r318", CubeListBuilder.create().texOffs(18, 15).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.6914F, 2.1587F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b90g3x54 = c14.addOrReplaceChild("b90g3x54", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.5F, 0.0F, 0.0F, -1.5708F));

		PartDefinition b30c3x160 = b90g3x54.addOrReplaceChild("b30c3x160", CubeListBuilder.create().texOffs(14, 18).addBox(3.7309F, 5.8897F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.4851F, -0.1737F, 0.0F));

		PartDefinition cube_r319 = b30c3x160.addOrReplaceChild("cube_r319", CubeListBuilder.create().texOffs(18, 12).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.716F, 5.716F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r320 = b30c3x160.addOrReplaceChild("cube_r320", CubeListBuilder.create().texOffs(18, 13).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.7311F, 5.8897F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x161 = b90g3x54.addOrReplaceChild("b30c3x161", CubeListBuilder.create().texOffs(12, 18).addBox(2.5772F, 8.8479F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -0.5236F));

		PartDefinition cube_r321 = b30c3x161.addOrReplaceChild("cube_r321", CubeListBuilder.create().texOffs(18, 10).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.5623F, 8.6742F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r322 = b30c3x161.addOrReplaceChild("cube_r322", CubeListBuilder.create().texOffs(18, 11).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5774F, 8.8479F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x162 = b90g3x54.addOrReplaceChild("b30c3x162", CubeListBuilder.create().texOffs(10, 18).addBox(0.599F, 9.9669F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition cube_r323 = b30c3x162.addOrReplaceChild("cube_r323", CubeListBuilder.create().texOffs(18, 8).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5841F, 9.7932F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r324 = b30c3x162.addOrReplaceChild("cube_r324", CubeListBuilder.create().texOffs(18, 9).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5992F, 9.9669F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b90g3x55 = c14.addOrReplaceChild("b90g3x55", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.5F, 0.0F, 0.0F, -3.1416F));

		PartDefinition b30c3x163 = b90g3x55.addOrReplaceChild("b30c3x163", CubeListBuilder.create().texOffs(8, 18).addBox(-1.9851F, 11.6048F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.4851F, -0.1737F, 0.0F));

		PartDefinition cube_r325 = b30c3x163.addOrReplaceChild("cube_r325", CubeListBuilder.create().texOffs(18, 6).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 11.4311F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r326 = b30c3x163.addOrReplaceChild("cube_r326", CubeListBuilder.create().texOffs(18, 7).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.985F, 11.6047F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x164 = b90g3x55.addOrReplaceChild("b30c3x164", CubeListBuilder.create().texOffs(6, 18).addBox(-5.2305F, 10.9393F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -0.5236F));

		PartDefinition cube_r327 = b30c3x164.addOrReplaceChild("cube_r327", CubeListBuilder.create().texOffs(18, 4).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.2454F, 10.7656F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r328 = b30c3x164.addOrReplaceChild("cube_r328", CubeListBuilder.create().texOffs(18, 5).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.2304F, 10.9393F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x165 = b90g3x55.addOrReplaceChild("b30c3x165", CubeListBuilder.create().texOffs(4, 18).addBox(-7.2084F, 7.8742F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition cube_r329 = b30c3x165.addOrReplaceChild("cube_r329", CubeListBuilder.create().texOffs(18, 2).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.2232F, 7.7005F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r330 = b30c3x165.addOrReplaceChild("cube_r330", CubeListBuilder.create().texOffs(18, 3).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.2082F, 7.8742F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b90g3x56 = c14.addOrReplaceChild("b90g3x56", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.5F, 0.0F, 0.0F, 1.5708F));

		PartDefinition b30c3x166 = b90g3x56.addOrReplaceChild("b30c3x166", CubeListBuilder.create().texOffs(2, 18).addBox(-7.7003F, 5.8897F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.4851F, -0.1737F, 0.0F));

		PartDefinition cube_r331 = b30c3x166.addOrReplaceChild("cube_r331", CubeListBuilder.create().texOffs(18, 0).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.7152F, 5.716F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r332 = b30c3x166.addOrReplaceChild("cube_r332", CubeListBuilder.create().texOffs(18, 1).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.7002F, 5.8897F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x167 = b90g3x56.addOrReplaceChild("b30c3x167", CubeListBuilder.create().texOffs(0, 18).addBox(-7.3225F, 3.1323F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -0.5236F));

		PartDefinition cube_r333 = b30c3x167.addOrReplaceChild("cube_r333", CubeListBuilder.create().texOffs(16, 17).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.3374F, 2.9586F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r334 = b30c3x167.addOrReplaceChild("cube_r334", CubeListBuilder.create().texOffs(2, 1).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.3224F, 3.1323F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x168 = b90g3x56.addOrReplaceChild("b30c3x168", CubeListBuilder.create().texOffs(14, 17).addBox(-5.1166F, 0.0672F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition cube_r335 = b30c3x168.addOrReplaceChild("cube_r335", CubeListBuilder.create().texOffs(10, 17).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.1315F, -0.1065F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r336 = b30c3x168.addOrReplaceChild("cube_r336", CubeListBuilder.create().texOffs(12, 17).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.1164F, 0.0672F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition c15 = d30c6x3.addOrReplaceChild("c15", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -2.0944F, 0.0F));

		PartDefinition b90g3x57 = c15.addOrReplaceChild("b90g3x57", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.5F));

		PartDefinition b30c3x169 = b90g3x57.addOrReplaceChild("b30c3x169", CubeListBuilder.create().texOffs(8, 17).addBox(-1.9851F, 0.1737F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.4851F, -0.1737F, 0.0F));

		PartDefinition cube_r337 = b30c3x169.addOrReplaceChild("cube_r337", CubeListBuilder.create().texOffs(4, 17).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r338 = b30c3x169.addOrReplaceChild("cube_r338", CubeListBuilder.create().texOffs(6, 17).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.985F, 0.1737F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x170 = b90g3x57.addOrReplaceChild("b30c3x170", CubeListBuilder.create().texOffs(2, 17).addBox(0.485F, 1.0397F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -0.5236F));

		PartDefinition cube_r339 = b30c3x170.addOrReplaceChild("cube_r339", CubeListBuilder.create().texOffs(16, 16).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.4701F, 0.866F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r340 = b30c3x170.addOrReplaceChild("cube_r340", CubeListBuilder.create().texOffs(0, 17).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4852F, 1.0397F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x171 = b90g3x57.addOrReplaceChild("b30c3x171", CubeListBuilder.create().texOffs(16, 15).addBox(2.6912F, 2.1587F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition cube_r341 = b30c3x171.addOrReplaceChild("cube_r341", CubeListBuilder.create().texOffs(14, 16).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.6763F, 1.985F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r342 = b30c3x171.addOrReplaceChild("cube_r342", CubeListBuilder.create().texOffs(16, 14).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.6914F, 2.1587F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b90g3x58 = c15.addOrReplaceChild("b90g3x58", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.5F, 0.0F, 0.0F, -1.5708F));

		PartDefinition b30c3x172 = b90g3x58.addOrReplaceChild("b30c3x172", CubeListBuilder.create().texOffs(16, 13).addBox(3.7309F, 5.8897F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.4851F, -0.1737F, 0.0F));

		PartDefinition cube_r343 = b30c3x172.addOrReplaceChild("cube_r343", CubeListBuilder.create().texOffs(12, 16).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.716F, 5.716F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r344 = b30c3x172.addOrReplaceChild("cube_r344", CubeListBuilder.create().texOffs(16, 12).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.7311F, 5.8897F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x173 = b90g3x58.addOrReplaceChild("b30c3x173", CubeListBuilder.create().texOffs(16, 11).addBox(2.5772F, 8.8479F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -0.5236F));

		PartDefinition cube_r345 = b30c3x173.addOrReplaceChild("cube_r345", CubeListBuilder.create().texOffs(10, 16).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.5623F, 8.6742F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r346 = b30c3x173.addOrReplaceChild("cube_r346", CubeListBuilder.create().texOffs(16, 10).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5774F, 8.8479F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x174 = b90g3x58.addOrReplaceChild("b30c3x174", CubeListBuilder.create().texOffs(16, 9).addBox(0.599F, 9.9669F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition cube_r347 = b30c3x174.addOrReplaceChild("cube_r347", CubeListBuilder.create().texOffs(8, 16).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5841F, 9.7932F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r348 = b30c3x174.addOrReplaceChild("cube_r348", CubeListBuilder.create().texOffs(16, 8).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5992F, 9.9669F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b90g3x59 = c15.addOrReplaceChild("b90g3x59", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.5F, 0.0F, 0.0F, -3.1416F));

		PartDefinition b30c3x175 = b90g3x59.addOrReplaceChild("b30c3x175", CubeListBuilder.create().texOffs(16, 7).addBox(-1.9851F, 11.6048F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.4851F, -0.1737F, 0.0F));

		PartDefinition cube_r349 = b30c3x175.addOrReplaceChild("cube_r349", CubeListBuilder.create().texOffs(6, 16).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 11.4311F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r350 = b30c3x175.addOrReplaceChild("cube_r350", CubeListBuilder.create().texOffs(16, 6).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.985F, 11.6047F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x176 = b90g3x59.addOrReplaceChild("b30c3x176", CubeListBuilder.create().texOffs(16, 5).addBox(-5.2305F, 10.9393F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -0.5236F));

		PartDefinition cube_r351 = b30c3x176.addOrReplaceChild("cube_r351", CubeListBuilder.create().texOffs(4, 16).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.2454F, 10.7656F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r352 = b30c3x176.addOrReplaceChild("cube_r352", CubeListBuilder.create().texOffs(16, 4).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.2304F, 10.9393F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x177 = b90g3x59.addOrReplaceChild("b30c3x177", CubeListBuilder.create().texOffs(16, 3).addBox(-7.2084F, 7.8742F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition cube_r353 = b30c3x177.addOrReplaceChild("cube_r353", CubeListBuilder.create().texOffs(2, 16).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.2232F, 7.7005F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r354 = b30c3x177.addOrReplaceChild("cube_r354", CubeListBuilder.create().texOffs(16, 2).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.2082F, 7.8742F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b90g3x60 = c15.addOrReplaceChild("b90g3x60", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.5F, 0.0F, 0.0F, 1.5708F));

		PartDefinition b30c3x178 = b90g3x60.addOrReplaceChild("b30c3x178", CubeListBuilder.create().texOffs(16, 1).addBox(-7.7003F, 5.8897F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.4851F, -0.1737F, 0.0F));

		PartDefinition cube_r355 = b30c3x178.addOrReplaceChild("cube_r355", CubeListBuilder.create().texOffs(0, 16).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.7152F, 5.716F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r356 = b30c3x178.addOrReplaceChild("cube_r356", CubeListBuilder.create().texOffs(16, 0).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.7002F, 5.8897F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x179 = b90g3x60.addOrReplaceChild("b30c3x179", CubeListBuilder.create().texOffs(14, 15).addBox(-7.3225F, 3.1323F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -0.5236F));

		PartDefinition cube_r357 = b30c3x179.addOrReplaceChild("cube_r357", CubeListBuilder.create().texOffs(12, 15).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.3374F, 2.9586F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r358 = b30c3x179.addOrReplaceChild("cube_r358", CubeListBuilder.create().texOffs(2, 0).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.3224F, 3.1323F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x180 = b90g3x60.addOrReplaceChild("b30c3x180", CubeListBuilder.create().texOffs(10, 15).addBox(-5.1166F, 0.0672F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition cube_r359 = b30c3x180.addOrReplaceChild("cube_r359", CubeListBuilder.create().texOffs(6, 15).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.1315F, -0.1065F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r360 = b30c3x180.addOrReplaceChild("cube_r360", CubeListBuilder.create().texOffs(8, 15).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.1164F, 0.0672F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition c16 = d30c6x3.addOrReplaceChild("c16", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -2.618F, 0.0F));

		PartDefinition b90g3x61 = c16.addOrReplaceChild("b90g3x61", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.5F));

		PartDefinition b30c3x181 = b90g3x61.addOrReplaceChild("b30c3x181", CubeListBuilder.create().texOffs(4, 15).addBox(-1.9851F, 0.1737F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.4851F, -0.1737F, 0.0F));

		PartDefinition cube_r361 = b30c3x181.addOrReplaceChild("cube_r361", CubeListBuilder.create().texOffs(0, 15).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r362 = b30c3x181.addOrReplaceChild("cube_r362", CubeListBuilder.create().texOffs(2, 15).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.985F, 0.1737F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x182 = b90g3x61.addOrReplaceChild("b30c3x182", CubeListBuilder.create().texOffs(14, 14).addBox(0.485F, 1.0397F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -0.5236F));

		PartDefinition cube_r363 = b30c3x182.addOrReplaceChild("cube_r363", CubeListBuilder.create().texOffs(14, 12).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.4701F, 0.866F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r364 = b30c3x182.addOrReplaceChild("cube_r364", CubeListBuilder.create().texOffs(14, 13).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4852F, 1.0397F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x183 = b90g3x61.addOrReplaceChild("b30c3x183", CubeListBuilder.create().texOffs(12, 14).addBox(2.6912F, 2.1587F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition cube_r365 = b30c3x183.addOrReplaceChild("cube_r365", CubeListBuilder.create().texOffs(14, 10).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.6763F, 1.985F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r366 = b30c3x183.addOrReplaceChild("cube_r366", CubeListBuilder.create().texOffs(14, 11).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.6914F, 2.1587F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b90g3x62 = c16.addOrReplaceChild("b90g3x62", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.5F, 0.0F, 0.0F, -1.5708F));

		PartDefinition b30c3x184 = b90g3x62.addOrReplaceChild("b30c3x184", CubeListBuilder.create().texOffs(10, 14).addBox(3.7309F, 5.8897F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.4851F, -0.1737F, 0.0F));

		PartDefinition cube_r367 = b30c3x184.addOrReplaceChild("cube_r367", CubeListBuilder.create().texOffs(14, 8).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.716F, 5.716F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r368 = b30c3x184.addOrReplaceChild("cube_r368", CubeListBuilder.create().texOffs(14, 9).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.7311F, 5.8897F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x185 = b90g3x62.addOrReplaceChild("b30c3x185", CubeListBuilder.create().texOffs(8, 14).addBox(2.5772F, 8.8479F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -0.5236F));

		PartDefinition cube_r369 = b30c3x185.addOrReplaceChild("cube_r369", CubeListBuilder.create().texOffs(14, 6).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.5623F, 8.6742F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r370 = b30c3x185.addOrReplaceChild("cube_r370", CubeListBuilder.create().texOffs(14, 7).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5774F, 8.8479F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x186 = b90g3x62.addOrReplaceChild("b30c3x186", CubeListBuilder.create().texOffs(6, 14).addBox(0.599F, 9.9669F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition cube_r371 = b30c3x186.addOrReplaceChild("cube_r371", CubeListBuilder.create().texOffs(14, 4).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5841F, 9.7932F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r372 = b30c3x186.addOrReplaceChild("cube_r372", CubeListBuilder.create().texOffs(14, 5).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5992F, 9.9669F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b90g3x63 = c16.addOrReplaceChild("b90g3x63", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.5F, 0.0F, 0.0F, -3.1416F));

		PartDefinition b30c3x187 = b90g3x63.addOrReplaceChild("b30c3x187", CubeListBuilder.create().texOffs(4, 14).addBox(-1.9851F, 11.6048F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.4851F, -0.1737F, 0.0F));

		PartDefinition cube_r373 = b30c3x187.addOrReplaceChild("cube_r373", CubeListBuilder.create().texOffs(14, 2).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 11.4311F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r374 = b30c3x187.addOrReplaceChild("cube_r374", CubeListBuilder.create().texOffs(14, 3).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.985F, 11.6047F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x188 = b90g3x63.addOrReplaceChild("b30c3x188", CubeListBuilder.create().texOffs(2, 14).addBox(-5.2305F, 10.9393F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -0.5236F));

		PartDefinition cube_r375 = b30c3x188.addOrReplaceChild("cube_r375", CubeListBuilder.create().texOffs(14, 0).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.2454F, 10.7656F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r376 = b30c3x188.addOrReplaceChild("cube_r376", CubeListBuilder.create().texOffs(14, 1).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.2304F, 10.9393F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x189 = b90g3x63.addOrReplaceChild("b30c3x189", CubeListBuilder.create().texOffs(0, 14).addBox(-7.2084F, 7.8742F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition cube_r377 = b30c3x189.addOrReplaceChild("cube_r377", CubeListBuilder.create().texOffs(10, 13).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.2232F, 7.7005F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r378 = b30c3x189.addOrReplaceChild("cube_r378", CubeListBuilder.create().texOffs(12, 13).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.2082F, 7.8742F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b90g3x64 = c16.addOrReplaceChild("b90g3x64", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.5F, 0.0F, 0.0F, 1.5708F));

		PartDefinition b30c3x190 = b90g3x64.addOrReplaceChild("b30c3x190", CubeListBuilder.create().texOffs(8, 13).addBox(-7.7003F, 5.8897F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.4851F, -0.1737F, 0.0F));

		PartDefinition cube_r379 = b30c3x190.addOrReplaceChild("cube_r379", CubeListBuilder.create().texOffs(4, 13).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.7152F, 5.716F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r380 = b30c3x190.addOrReplaceChild("cube_r380", CubeListBuilder.create().texOffs(6, 13).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.7002F, 5.8897F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x191 = b90g3x64.addOrReplaceChild("b30c3x191", CubeListBuilder.create().texOffs(2, 13).addBox(-7.3225F, 3.1323F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -0.5236F));

		PartDefinition cube_r381 = b30c3x191.addOrReplaceChild("cube_r381", CubeListBuilder.create().texOffs(0, 13).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.3374F, 2.9586F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r382 = b30c3x191.addOrReplaceChild("cube_r382", CubeListBuilder.create().texOffs(0, 2).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.3224F, 3.1323F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x192 = b90g3x64.addOrReplaceChild("b30c3x192", CubeListBuilder.create().texOffs(12, 12).addBox(-5.1166F, 0.0672F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition cube_r383 = b30c3x192.addOrReplaceChild("cube_r383", CubeListBuilder.create().texOffs(12, 10).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.1315F, -0.1065F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r384 = b30c3x192.addOrReplaceChild("cube_r384", CubeListBuilder.create().texOffs(12, 11).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.1164F, 0.0672F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition c17 = d30c6x3.addOrReplaceChild("c17", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

		PartDefinition b90g3x65 = c17.addOrReplaceChild("b90g3x65", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.5F));

		PartDefinition b30c3x193 = b90g3x65.addOrReplaceChild("b30c3x193", CubeListBuilder.create().texOffs(10, 12).addBox(-1.9851F, 0.1737F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.4851F, -0.1737F, 0.0F));

		PartDefinition cube_r385 = b30c3x193.addOrReplaceChild("cube_r385", CubeListBuilder.create().texOffs(12, 8).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r386 = b30c3x193.addOrReplaceChild("cube_r386", CubeListBuilder.create().texOffs(12, 9).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.985F, 0.1737F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x194 = b90g3x65.addOrReplaceChild("b30c3x194", CubeListBuilder.create().texOffs(8, 12).addBox(0.485F, 1.0397F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -0.5236F));

		PartDefinition cube_r387 = b30c3x194.addOrReplaceChild("cube_r387", CubeListBuilder.create().texOffs(12, 6).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.4701F, 0.866F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r388 = b30c3x194.addOrReplaceChild("cube_r388", CubeListBuilder.create().texOffs(12, 7).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4852F, 1.0397F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x195 = b90g3x65.addOrReplaceChild("b30c3x195", CubeListBuilder.create().texOffs(6, 12).addBox(2.6912F, 2.1587F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition cube_r389 = b30c3x195.addOrReplaceChild("cube_r389", CubeListBuilder.create().texOffs(12, 4).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.6763F, 1.985F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r390 = b30c3x195.addOrReplaceChild("cube_r390", CubeListBuilder.create().texOffs(12, 5).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.6914F, 2.1587F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b90g3x66 = c17.addOrReplaceChild("b90g3x66", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.5F, 0.0F, 0.0F, -1.5708F));

		PartDefinition b30c3x196 = b90g3x66.addOrReplaceChild("b30c3x196", CubeListBuilder.create().texOffs(4, 12).addBox(3.7309F, 5.8897F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.4851F, -0.1737F, 0.0F));

		PartDefinition cube_r391 = b30c3x196.addOrReplaceChild("cube_r391", CubeListBuilder.create().texOffs(12, 2).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.716F, 5.716F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r392 = b30c3x196.addOrReplaceChild("cube_r392", CubeListBuilder.create().texOffs(12, 3).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.7311F, 5.8897F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x197 = b90g3x66.addOrReplaceChild("b30c3x197", CubeListBuilder.create().texOffs(2, 12).addBox(2.5772F, 8.8479F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -0.5236F));

		PartDefinition cube_r393 = b30c3x197.addOrReplaceChild("cube_r393", CubeListBuilder.create().texOffs(12, 0).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.5623F, 8.6742F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r394 = b30c3x197.addOrReplaceChild("cube_r394", CubeListBuilder.create().texOffs(12, 1).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5774F, 8.8479F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x198 = b90g3x66.addOrReplaceChild("b30c3x198", CubeListBuilder.create().texOffs(0, 12).addBox(0.599F, 9.9669F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition cube_r395 = b30c3x198.addOrReplaceChild("cube_r395", CubeListBuilder.create().texOffs(8, 11).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5841F, 9.7932F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r396 = b30c3x198.addOrReplaceChild("cube_r396", CubeListBuilder.create().texOffs(10, 11).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5992F, 9.9669F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b90g3x67 = c17.addOrReplaceChild("b90g3x67", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.5F, 0.0F, 0.0F, -3.1416F));

		PartDefinition b30c3x199 = b90g3x67.addOrReplaceChild("b30c3x199", CubeListBuilder.create().texOffs(6, 11).addBox(-1.9851F, 11.6048F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.4851F, -0.1737F, 0.0F));

		PartDefinition cube_r397 = b30c3x199.addOrReplaceChild("cube_r397", CubeListBuilder.create().texOffs(2, 11).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 11.4311F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r398 = b30c3x199.addOrReplaceChild("cube_r398", CubeListBuilder.create().texOffs(4, 11).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.985F, 11.6047F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x200 = b90g3x67.addOrReplaceChild("b30c3x200", CubeListBuilder.create().texOffs(0, 11).addBox(-5.2305F, 10.9393F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -0.5236F));

		PartDefinition cube_r399 = b30c3x200.addOrReplaceChild("cube_r399", CubeListBuilder.create().texOffs(10, 9).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.2454F, 10.7656F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r400 = b30c3x200.addOrReplaceChild("cube_r400", CubeListBuilder.create().texOffs(10, 10).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.2304F, 10.9393F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x201 = b90g3x67.addOrReplaceChild("b30c3x201", CubeListBuilder.create().texOffs(10, 8).addBox(-7.2084F, 7.8742F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition cube_r401 = b30c3x201.addOrReplaceChild("cube_r401", CubeListBuilder.create().texOffs(10, 7).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.2232F, 7.7005F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r402 = b30c3x201.addOrReplaceChild("cube_r402", CubeListBuilder.create().texOffs(8, 10).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.2082F, 7.8742F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b90g3x68 = c17.addOrReplaceChild("b90g3x68", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.5F, 0.0F, 0.0F, 1.5708F));

		PartDefinition b30c3x202 = b90g3x68.addOrReplaceChild("b30c3x202", CubeListBuilder.create().texOffs(10, 6).addBox(-7.7003F, 5.8897F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.4851F, -0.1737F, 0.0F));

		PartDefinition cube_r403 = b30c3x202.addOrReplaceChild("cube_r403", CubeListBuilder.create().texOffs(10, 5).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.7152F, 5.716F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r404 = b30c3x202.addOrReplaceChild("cube_r404", CubeListBuilder.create().texOffs(6, 10).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.7002F, 5.8897F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x203 = b90g3x68.addOrReplaceChild("b30c3x203", CubeListBuilder.create().texOffs(10, 4).addBox(-7.3225F, 3.1323F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -0.5236F));

		PartDefinition cube_r405 = b30c3x203.addOrReplaceChild("cube_r405", CubeListBuilder.create().texOffs(4, 10).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.3374F, 2.9586F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r406 = b30c3x203.addOrReplaceChild("cube_r406", CubeListBuilder.create().texOffs(0, 1).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.3224F, 3.1323F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x204 = b90g3x68.addOrReplaceChild("b30c3x204", CubeListBuilder.create().texOffs(10, 3).addBox(-5.1166F, 0.0672F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition cube_r407 = b30c3x204.addOrReplaceChild("cube_r407", CubeListBuilder.create().texOffs(2, 10).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.1315F, -0.1065F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r408 = b30c3x204.addOrReplaceChild("cube_r408", CubeListBuilder.create().texOffs(10, 2).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.1164F, 0.0672F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition c18 = d30c6x3.addOrReplaceChild("c18", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

		PartDefinition b90g3x69 = c18.addOrReplaceChild("b90g3x69", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.5F));

		PartDefinition b30c3x205 = b90g3x69.addOrReplaceChild("b30c3x205", CubeListBuilder.create().texOffs(10, 1).addBox(-1.9851F, 0.1737F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.4851F, -0.1737F, 0.0F));

		PartDefinition cube_r409 = b30c3x205.addOrReplaceChild("cube_r409", CubeListBuilder.create().texOffs(0, 10).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r410 = b30c3x205.addOrReplaceChild("cube_r410", CubeListBuilder.create().texOffs(10, 0).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.985F, 0.1737F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x206 = b90g3x69.addOrReplaceChild("b30c3x206", CubeListBuilder.create().texOffs(8, 9).addBox(0.485F, 1.0397F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -0.5236F));

		PartDefinition cube_r411 = b30c3x206.addOrReplaceChild("cube_r411", CubeListBuilder.create().texOffs(4, 9).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.4701F, 0.866F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r412 = b30c3x206.addOrReplaceChild("cube_r412", CubeListBuilder.create().texOffs(6, 9).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4852F, 1.0397F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x207 = b90g3x69.addOrReplaceChild("b30c3x207", CubeListBuilder.create().texOffs(2, 9).addBox(2.6912F, 2.1587F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition cube_r413 = b30c3x207.addOrReplaceChild("cube_r413", CubeListBuilder.create().texOffs(8, 8).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.6763F, 1.985F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r414 = b30c3x207.addOrReplaceChild("cube_r414", CubeListBuilder.create().texOffs(0, 9).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.6914F, 2.1587F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b90g3x70 = c18.addOrReplaceChild("b90g3x70", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.5F, 0.0F, 0.0F, -1.5708F));

		PartDefinition b30c3x208 = b90g3x70.addOrReplaceChild("b30c3x208", CubeListBuilder.create().texOffs(8, 7).addBox(3.7309F, 5.8897F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.4851F, -0.1737F, 0.0F));

		PartDefinition cube_r415 = b30c3x208.addOrReplaceChild("cube_r415", CubeListBuilder.create().texOffs(6, 8).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.716F, 5.716F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r416 = b30c3x208.addOrReplaceChild("cube_r416", CubeListBuilder.create().texOffs(8, 6).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.7311F, 5.8897F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x209 = b90g3x70.addOrReplaceChild("b30c3x209", CubeListBuilder.create().texOffs(8, 5).addBox(2.5772F, 8.8479F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -0.5236F));

		PartDefinition cube_r417 = b30c3x209.addOrReplaceChild("cube_r417", CubeListBuilder.create().texOffs(4, 8).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.5623F, 8.6742F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r418 = b30c3x209.addOrReplaceChild("cube_r418", CubeListBuilder.create().texOffs(8, 4).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5774F, 8.8479F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x210 = b90g3x70.addOrReplaceChild("b30c3x210", CubeListBuilder.create().texOffs(8, 3).addBox(0.599F, 9.9669F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition cube_r419 = b30c3x210.addOrReplaceChild("cube_r419", CubeListBuilder.create().texOffs(2, 8).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5841F, 9.7932F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r420 = b30c3x210.addOrReplaceChild("cube_r420", CubeListBuilder.create().texOffs(8, 2).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5992F, 9.9669F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b90g3x71 = c18.addOrReplaceChild("b90g3x71", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.5F, 0.0F, 0.0F, -3.1416F));

		PartDefinition b30c3x211 = b90g3x71.addOrReplaceChild("b30c3x211", CubeListBuilder.create().texOffs(8, 1).addBox(-1.9851F, 11.6048F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.4851F, -0.1737F, 0.0F));

		PartDefinition cube_r421 = b30c3x211.addOrReplaceChild("cube_r421", CubeListBuilder.create().texOffs(0, 8).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 11.4311F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r422 = b30c3x211.addOrReplaceChild("cube_r422", CubeListBuilder.create().texOffs(8, 0).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.985F, 11.6047F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x212 = b90g3x71.addOrReplaceChild("b30c3x212", CubeListBuilder.create().texOffs(6, 7).addBox(-5.2305F, 10.9393F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -0.5236F));

		PartDefinition cube_r423 = b30c3x212.addOrReplaceChild("cube_r423", CubeListBuilder.create().texOffs(2, 7).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.2454F, 10.7656F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r424 = b30c3x212.addOrReplaceChild("cube_r424", CubeListBuilder.create().texOffs(4, 7).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.2304F, 10.9393F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x213 = b90g3x71.addOrReplaceChild("b30c3x213", CubeListBuilder.create().texOffs(0, 7).addBox(-7.2084F, 7.8742F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition cube_r425 = b30c3x213.addOrReplaceChild("cube_r425", CubeListBuilder.create().texOffs(6, 5).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.2232F, 7.7005F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r426 = b30c3x213.addOrReplaceChild("cube_r426", CubeListBuilder.create().texOffs(6, 6).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.2082F, 7.8742F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b90g3x72 = c18.addOrReplaceChild("b90g3x72", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.5F, 0.0F, 0.0F, 1.5708F));

		PartDefinition b30c3x214 = b90g3x72.addOrReplaceChild("b30c3x214", CubeListBuilder.create().texOffs(6, 4).addBox(-7.7003F, 5.8897F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.4851F, -0.1737F, 0.0F));

		PartDefinition cube_r427 = b30c3x214.addOrReplaceChild("cube_r427", CubeListBuilder.create().texOffs(6, 3).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.7152F, 5.716F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r428 = b30c3x214.addOrReplaceChild("cube_r428", CubeListBuilder.create().texOffs(4, 6).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.7002F, 5.8897F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x215 = b90g3x72.addOrReplaceChild("b30c3x215", CubeListBuilder.create().texOffs(6, 2).addBox(-7.3225F, 3.1323F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -0.5236F));

		PartDefinition cube_r429 = b30c3x215.addOrReplaceChild("cube_r429", CubeListBuilder.create().texOffs(2, 6).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.3374F, 2.9586F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r430 = b30c3x215.addOrReplaceChild("cube_r430", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.3224F, 3.1323F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition b30c3x216 = b90g3x72.addOrReplaceChild("b30c3x216", CubeListBuilder.create().texOffs(6, 1).addBox(-5.1166F, 0.0672F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4851F, -1.1737F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition cube_r431 = b30c3x216.addOrReplaceChild("cube_r431", CubeListBuilder.create().texOffs(0, 6).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.1315F, -0.1065F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r432 = b30c3x216.addOrReplaceChild("cube_r432", CubeListBuilder.create().texOffs(6, 0).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.1164F, 0.0672F, 0.0F, 0.0F, 0.0F, -0.1745F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(@NotNull T t, float v, float v1, float v2, float v3, float v4) {

	}

	@Override
	public void renderToBuffer(@NotNull PoseStack poseStack, @NotNull VertexConsumer vertexConsumer, int i, int i1, int i2) {
		ball.render(poseStack, vertexConsumer, i, i1, i2);
	}
}