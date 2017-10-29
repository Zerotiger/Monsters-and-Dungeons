package monstersanddungeons.items;

import monstersanddungeons.items.armor.ArmorStat;
import monstersanddungeons.items.armor.ItemQuartzArmor;
import monstersanddungeons.items.armor.ItemQuartzShield;
import monstersanddungeons.items.spawnEggs.ItemSpawnEgg;
import monstersanddungeons.items.spawnEggs.ItemSpawnEggColourHandler;
import monstersanddungeons.items.weapon.ItemQuartzGreatSword;
import monstersanddungeons.items.weapon.ItemQuartzWarHammer;
import monstersanddungeons.stats.Stats;
import monstersanddungeons.util.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber
public class MaDItemsHandler {
	public static ToolMaterial toolMaterialQuartz = EnumHelper.addToolMaterial("quartz", 3, 1000, 15.0F,
			4.0F, 30);

	public static ArmorMaterial armorMaterialQuartz = EnumHelper.addArmorMaterial("quartz",
			"wc:RomanArmor", 16, new int[] { 3, 8, 6, 3 }, 20, null, 1f);

	public static Item quartzHelmet, quartzChest, quartzLegs, quartzBoots, quartzShield, quartzWarhammer,
			quartzGreatSword, spawnPawn;

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		quartzHelmet = new ItemQuartzArmor(armorMaterialQuartz, 1, EntityEquipmentSlot.HEAD, "quartzHelmet",
				new ArmorStat[] { new ArmorStat(Stats.strength), new ArmorStat(Stats.wisdom) });
		quartzChest = new ItemQuartzArmor(armorMaterialQuartz, 1, EntityEquipmentSlot.CHEST, "quartzChest",
				new ArmorStat[] { new ArmorStat(Stats.strength, 3) });
		quartzLegs = new ItemQuartzArmor(armorMaterialQuartz, 1, EntityEquipmentSlot.LEGS, "quartzLegs",
				new ArmorStat[] { new ArmorStat(Stats.strength, 2) });
		quartzBoots = new ItemQuartzArmor(armorMaterialQuartz, 1, EntityEquipmentSlot.FEET, "quartzBoots",
				new ArmorStat[] { new ArmorStat(Stats.strength) });
		quartzShield = new ItemQuartzShield("quartzShield");

		quartzWarhammer = new ItemQuartzWarHammer(toolMaterialQuartz, "quartzWarhammer");
		quartzGreatSword = new ItemQuartzGreatSword(toolMaterialQuartz, "quartzGreatSword");

		spawnPawn = new ItemSpawnEgg("SpawnEgg");
		
		event.getRegistry().register(quartzHelmet);
		event.getRegistry().register(quartzChest);
		event.getRegistry().register(quartzLegs);
		event.getRegistry().register(quartzBoots);
		event.getRegistry().register(quartzShield);
		event.getRegistry().register(quartzWarhammer);
		event.getRegistry().register(quartzGreatSword);
		event.getRegistry().register(spawnPawn);
	}

	public static void registerRenders() {

		registerRender(quartzHelmet);
		registerRender(quartzChest);
		registerRender(quartzLegs);
		registerRender(quartzBoots);
		registerRender(quartzShield);

		registerRender(quartzWarhammer);
		registerRender(quartzGreatSword);

		registerRender(spawnPawn, true);
	}

	public static void registerRender(Item item) {
		registerRender(item, false);
	}

	@SideOnly(Side.CLIENT)
	public static void registerRender(Item item, boolean isEgg) {
		if (!isEgg) {
			Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(
					Reference.MODID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
		} else {
			NonNullList<ItemStack> allitems = NonNullList.create();
			item.getSubItems(item.getCreativeTab(), allitems);

			for (int i = 0; i < allitems.size() + 1; i++) {
				Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, i,
						new ModelResourceLocation("minecraft:spawn_egg", "inventory"));
				Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new ItemSpawnEggColourHandler(),
						item);
			}
		}
	}
}
