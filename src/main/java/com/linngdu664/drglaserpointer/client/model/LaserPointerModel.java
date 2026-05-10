// Made with Blockbench 4.12.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


public class LaserPointerModel<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "laserpointermodel"), "main");
	private final ModelPart body;
	private final ModelPart bone3;
	private final ModelPart bone2;
	private final ModelPart bone4;
	private final ModelPart bone5;
	private final ModelPart bulb;
	private final ModelPart screen;

	public LaserPointerModel(ModelPart root) {
		this.body = root.getChild("body");
		this.bone3 = this.body.getChild("bone3");
		this.bone2 = this.body.getChild("bone2");
		this.bone4 = this.body.getChild("bone4");
		this.bone5 = this.body.getChild("bone5");
		this.bulb = root.getChild("bulb");
		this.screen = root.getChild("screen");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition bone3 = body.addOrReplaceChild("bone3", CubeListBuilder.create().texOffs(293, 88).addBox(-16.0F, -96.0F, -17.0F, 32.0F, 96.0F, 25.0F, new CubeDeformation(0.0F))
		.texOffs(350, 92).addBox(-16.0F, -144.0F, -1.0F, 32.0F, 49.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(300, 112).addBox(-16.0F, -161.0F, -8.0F, 32.0F, 17.0F, 33.0F, new CubeDeformation(0.0F))
		.texOffs(329, 81).addBox(-57.0F, -241.0F, -14.0F, 1.0F, 73.0F, 28.0F, new CubeDeformation(0.0F))
		.texOffs(326, 79).addBox(56.0F, -241.0F, -14.0F, 1.0F, 73.0F, 28.0F, new CubeDeformation(0.0F))
		.texOffs(339, 130).addBox(-9.0F, -167.0F, -2.0F, 18.0F, 8.0F, 14.0F, new CubeDeformation(0.0F))
		.texOffs(278, 101).addBox(-13.0F, -166.0F, 30.0F, 26.0F, 27.0F, 58.0F, new CubeDeformation(0.0F))
		.texOffs(312, 114).addBox(-13.0F, -62.0F, 80.0F, 26.0F, 26.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(302, 101).addBox(-13.0F, -36.0F, 80.0F, 26.0F, 16.0F, 25.0F, new CubeDeformation(0.0F))
		.texOffs(302, 90).addBox(-11.0F, -168.0F, 30.0F, 22.0F, 4.0F, 50.0F, new CubeDeformation(0.0F))
		.texOffs(291, 87).addBox(-14.0F, -163.0F, 29.0F, 28.0F, 25.0F, 39.0F, new CubeDeformation(0.0F))
		.texOffs(289, 97).addBox(-16.0F, -10.0F, -1.0F, 32.0F, 10.0F, 33.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r1 = bone3.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(315, 108).addBox(-16.0F, -26.0F, -1.0F, 32.0F, 26.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -95.0F, -16.0F, -0.6545F, 0.0F, 0.0F));

		PartDefinition cube_r2 = bone3.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(302, 121).addBox(-16.0F, -22.0F, 9.0F, 32.0F, 17.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -147.0F, -18.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r3 = bone3.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(297, 109).addBox(-15.0F, -22.0F, -15.0F, 30.0F, 26.0F, 37.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -135.0F, 30.0F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r4 = bone3.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(318, 119).addBox(-13.0F, 5.0F, -7.0F, 26.0F, 18.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -32.0F, 101.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r5 = bone3.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(270, 94).addBox(-14.0F, 5.0F, -42.0F, 28.0F, 11.0F, 53.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -41.0F, 105.0F, -1.1781F, 0.0F, 0.0F));

		PartDefinition cube_r6 = bone3.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(368, 113).addBox(-14.0F, -12.0F, 2.0F, 28.0F, 11.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(323, 87).addBox(-13.0F, -22.0F, -14.0F, 26.0F, 27.0F, 25.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -141.0F, 86.0F, 0.3927F, 0.0F, 0.0F));

		PartDefinition bone2 = body.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(456, 80).addBox(-37.2F, -82.5F, -22.5F, 2.0F, 18.0F, 18.0F, new CubeDeformation(0.0F))
		.texOffs(444, 86).addBox(-52.2F, -81.5F, -21.5F, 15.0F, 16.0F, 16.0F, new CubeDeformation(0.0F))
		.texOffs(450, 84).addBox(-52.2F, -46.5F, -21.5F, 15.0F, 16.0F, 16.0F, new CubeDeformation(0.0F))
		.texOffs(460, 81).addBox(-37.2F, -47.5F, -22.5F, 2.0F, 18.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offset(-21.8F, -148.5F, 13.5F));

		PartDefinition cube_r7 = bone2.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(448, 103).addBox(-9.0F, -1.5F, -1.5F, 18.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-43.3984F, -20.97F, -10.1876F, -1.9965F, 0.1863F, -0.2787F));

		PartDefinition cube_r8 = bone2.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(451, 102).addBox(-9.0F, -1.5F, -1.5F, 18.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-50.6945F, -14.8525F, -2.4472F, -2.6128F, 1.0432F, -1.8945F));

		PartDefinition cube_r9 = bone2.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(456, 103).addBox(-9.0F, -1.5F, -1.5F, 18.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-43.111F, -5.8376F, 4.3773F, 3.124F, -0.0571F, -2.4966F));

		PartDefinition cube_r10 = bone2.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(456, 105).addBox(-9.0F, -1.5F, -1.5F, 18.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-33.1171F, -6.3339F, -1.1515F, -0.1175F, 0.7145F, -0.9348F));

		PartDefinition cube_r11 = bone2.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(450, 109).addBox(-9.0F, -1.5F, -1.5F, 18.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-36.4937F, -13.0096F, -4.1421F, -0.946F, -0.2918F, -2.8547F));

		PartDefinition cube_r12 = bone2.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(450, 102).addBox(-9.0F, -1.5F, -1.5F, 18.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-41.8204F, -12.1899F, 4.494F, -0.2177F, -1.0166F, 0.9931F));

		PartDefinition cube_r13 = bone2.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(451, 103).addBox(-9.0F, -1.5F, -1.5F, 18.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-32.1495F, -6.9267F, 11.6589F, 0.0786F, -0.058F, 0.2741F));

		PartDefinition cube_r14 = bone2.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(449, 105).addBox(-9.0F, -1.5F, -1.5F, 18.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.8034F, -3.5621F, 7.9516F, 0.1483F, 0.504F, 0.1255F));

		PartDefinition cube_r15 = bone2.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(449, 102).addBox(-9.0F, -1.5F, -1.5F, 18.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.2F, -4.0F, 4.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition cube_r16 = bone2.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(457, 102).addBox(-9.0F, -1.5F, -1.5F, 18.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-36.7003F, -22.2305F, -17.5783F, 0.0483F, 0.2407F, 2.2031F));

		PartDefinition cube_r17 = bone2.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(460, 106).addBox(-9.0F, -1.5F, -1.5F, 18.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-41.7629F, -8.1937F, -16.9765F, 0.3261F, -0.3701F, 1.6609F));

		PartDefinition cube_r18 = bone2.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(458, 103).addBox(-9.0F, -1.5F, -1.5F, 18.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-36.0065F, 2.2231F, -10.718F, 1.0252F, -0.4283F, 0.4614F));

		PartDefinition cube_r19 = bone2.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(458, 103).addBox(-9.0F, -1.5F, -1.5F, 18.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-25.6924F, 0.0594F, -3.1108F, 1.6161F, -0.5549F, -0.9079F));

		PartDefinition cube_r20 = bone2.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(453, 104).addBox(-9.0F, -1.5F, -1.5F, 18.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-20.8596F, -8.1519F, -5.4953F, 2.4368F, 1.2047F, -1.3067F));

		PartDefinition cube_r21 = bone2.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(468, 103).addBox(-9.0F, -1.5F, -1.5F, 18.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.0312F, -4.697F, -13.5468F, -1.2801F, 0.0658F, 1.0573F));

		PartDefinition cube_r22 = bone2.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(460, 107).addBox(-9.0F, -1.5F, -1.5F, 18.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.167F, 3.425F, -7.5121F, -0.6548F, -1.1175F, 0.3997F));

		PartDefinition cube_r23 = bone2.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(459, 105).addBox(-9.0F, -1.5F, -1.5F, 18.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.6981F));

		PartDefinition bone4 = body.addOrReplaceChild("bone4", CubeListBuilder.create().texOffs(188, 113).addBox(33.0F, -84.0F, -3.0F, 15.0F, 81.0F, 21.0F, new CubeDeformation(0.0F))
		.texOffs(188, 111).addBox(10.0F, -84.0F, -3.0F, 15.0F, 81.0F, 21.0F, new CubeDeformation(0.0F))
		.texOffs(188, 110).addBox(-13.0F, -84.0F, -3.0F, 15.0F, 81.0F, 21.0F, new CubeDeformation(0.0F))
		.texOffs(188, 110).addBox(56.0F, -84.0F, -3.0F, 15.0F, 81.0F, 21.0F, new CubeDeformation(0.0F)), PartPose.offset(-29.0F, -161.0F, 0.0F));

		PartDefinition bone5 = body.addOrReplaceChild("bone5", CubeListBuilder.create().texOffs(0, 0).addBox(-56.0F, -11.0F, -18.0F, 112.0F, 6.0F, 34.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(50.0F, -78.0F, -18.0F, 6.0F, 67.0F, 34.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-56.0F, -78.0F, -18.0F, 6.0F, 67.0F, 34.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-56.0F, -84.0F, -18.0F, 112.0F, 6.0F, 34.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-53.0F, -81.0F, -14.0F, 106.0F, 73.0F, 31.0F, new CubeDeformation(0.0F))
		.texOffs(26, 19).addBox(-10.0F, -53.0F, 14.0F, 20.0F, 20.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(4, 6).addBox(-13.0F, 36.0F, 90.0F, 26.0F, 88.0F, 15.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-13.0F, 36.0F, 80.0F, 26.0F, 62.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-8.0F, 96.0F, 77.0F, 16.0F, 13.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-15.0F, 4.0F, 54.0F, 30.0F, 7.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-14.0F, 136.0F, 89.0F, 28.0F, 7.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-18.0F, 16.0F, 8.0F, 36.0F, 134.0F, 17.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -160.0F, 0.0F));

		PartDefinition cube_r24 = bone5.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(0, 0).addBox(-18.0F, 2.0F, 10.0F, 36.0F, 14.0F, 21.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 55.0F, 38.0F, -1.9635F, 0.0F, 0.0F));

		PartDefinition cube_r25 = bone5.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(0, 0).addBox(-18.0F, 2.0F, 10.0F, 36.0F, 14.0F, 21.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 34.0F, 22.0F, -1.1781F, 0.0F, 0.0F));

		PartDefinition cube_r26 = bone5.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(0, 0).addBox(-18.0F, 2.0F, 10.0F, 36.0F, 14.0F, 21.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 97.0F, 38.0F, -1.9635F, 0.0F, 0.0F));

		PartDefinition cube_r27 = bone5.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(0, 0).addBox(-18.0F, 2.0F, 10.0F, 36.0F, 14.0F, 21.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 76.0F, 22.0F, -1.1781F, 0.0F, 0.0F));

		PartDefinition cube_r28 = bone5.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(0, 0).addBox(-18.0F, 2.0F, 10.0F, 36.0F, 14.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 122.0F, 17.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r29 = bone5.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(0, 0).addBox(-15.0F, -3.5F, -3.5F, 30.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 102.5F, 86.5F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r30 = bone5.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(0, 0).addBox(-13.0F, 5.0F, -14.0F, 26.0F, 18.0F, 25.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-15.0F, -10.0F, 4.0F, 30.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 19.0F, 86.0F, 0.3927F, 0.0F, 0.0F));

		PartDefinition bulb = partdefinition.addOrReplaceChild("bulb", CubeListBuilder.create().texOffs(134, 145).addBox(39.0F, -59.0F, 8.0F, 14.0F, 14.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-46.0F, -127.0F, 16.0F));

		PartDefinition screen = partdefinition.addOrReplaceChild("screen", CubeListBuilder.create().texOffs(294, 0).addBox(-53.0F, -84.0F, -14.0F, 106.0F, 73.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -133.0F, -3.0F));

		return LayerDefinition.create(meshdefinition, 512, 256);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		bulb.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		screen.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}