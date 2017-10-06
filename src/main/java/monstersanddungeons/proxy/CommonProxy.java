package monstersanddungeons.proxy;

import java.io.File;
import java.io.IOException;

import monstersanddungeons.MonstersAndDungeons;
import monstersanddungeons.blocks.MaDBlocksHandler;
import monstersanddungeons.client.gui.MaDGuiHandler;
import monstersanddungeons.entity.MaDEntityHandler;
import monstersanddungeons.events.CommonEventHandler;
import monstersanddungeons.items.MaDItemsHandler;
import monstersanddungeons.packet.MaDPacketHandler;
import monstersanddungeons.sound.MaDSoundsHandler;
import monstersanddungeons.tileentity.MaDTileEntityHandler;
import monstersanddungeons.util.Reference;
import monstersanddungeons.world.MaDWorldGenerationHandler;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;




public class CommonProxy {

	public static File ConfigDir;

	public void preInit(FMLPreInitializationEvent event) {
		// TODO Auto-generated method stub


		this.ConfigDir = event.getModConfigurationDirectory();

		try {
			MaDBlocksHandler.genBlocks(ConfigDir.getPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		MaDBlocksHandler.init();
		MaDItemsHandler.init();
		MaDPacketHandler.init();
		MaDTileEntityHandler.register();
		MaDEntityHandler.registerEntities();
		MaDEntityHandler.addSpawns();
		MaDSoundsHandler.init();
		NetworkRegistry.INSTANCE.registerGuiHandler(MonstersAndDungeons.instance, new MaDGuiHandler());
	}

	public void init(FMLInitializationEvent event) {
		// TODO Auto-generated method stub
		GameRegistry.registerWorldGenerator(new MaDWorldGenerationHandler(), 9000);
	}

	public void postInit(FMLPostInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new CommonEventHandler());
	}

	public void RegisterRenders() {
		// TODO Auto-generated method stub

	}

	public ModelBiped getArmorModel(int id) {
		return null;
	}

}
