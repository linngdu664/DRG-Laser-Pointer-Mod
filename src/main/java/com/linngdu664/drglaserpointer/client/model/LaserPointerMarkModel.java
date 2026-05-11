package com.linngdu664.drglaserpointer.client.model;// Made with Blockbench 4.10.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.linngdu664.drglaserpointer.DrgLaserPointer;
import com.linngdu664.drglaserpointer.client.renderer.entity.state.LaserPointerMarkRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class LaserPointerMarkModel extends EntityModel<LaserPointerMarkRenderState> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION_BLUE = new ModelLayerLocation(DrgLaserPointer.makeMyIdentifier("textures/models/laser_pointer_label_blue.png"), "main");
	public static final ModelLayerLocation LAYER_LOCATION_RED = new ModelLayerLocation(DrgLaserPointer.makeMyIdentifier("textures/models/laser_pointer_label_red.png"), "main");
	public static final ModelLayerLocation LAYER_LOCATION_GREEN = new ModelLayerLocation(DrgLaserPointer.makeMyIdentifier("textures/models/laser_pointer_label_green.png"), "main");
	public static final ModelLayerLocation LAYER_LOCATION_YELLOW = new ModelLayerLocation(DrgLaserPointer.makeMyIdentifier("textures/models/laser_pointer_label_yellow.png"), "main");
	public static final ModelLayerLocation LAYER_LOCATION_EMPTY = new ModelLayerLocation(DrgLaserPointer.makeMyIdentifier("textures/models/laser_pointer_label_empty.png"), "main");
	private final ModelPart bone;
	public ModelPart getBody() {
		return bone;
	}

	public LaserPointerMarkModel(ModelPart root) {
		super(root);
		this.bone = root.getChild("bone");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(0, 24).addBox(-5.0F, -11.0F, -5.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-6.0F, -12.0F, -6.0F, 12.0F, 12.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 5.8F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(LaserPointerMarkRenderState state) {

	}
}