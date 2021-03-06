package com.ForgeEssentials.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.minecraft.command.CommandHandler;
import net.minecraft.command.ICommand;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;

import com.ForgeEssentials.commands.util.ConfigCmd;
import com.ForgeEssentials.commands.util.EventHandler;
import com.ForgeEssentials.commands.util.PlayerTrackerCommands;
import com.ForgeEssentials.commands.util.TickHandlerCommands;
import com.ForgeEssentials.commands.vanilla.CommandClearInventory;
import com.ForgeEssentials.commands.vanilla.CommandGameMode;
import com.ForgeEssentials.commands.vanilla.CommandGive;
import com.ForgeEssentials.commands.vanilla.CommandKill;
import com.ForgeEssentials.core.IFEModule;
import com.ForgeEssentials.permission.ForgeEssentialsPermissionRegistrationEvent;
import com.ForgeEssentials.permission.PermissionsAPI;
import com.ForgeEssentials.util.OutputHandler;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class ModuleCommands implements IFEModule
{
	public static ConfigCmd conf;
	public static boolean removeDuplicateCommands;
	
	public ModuleCommands()
	{
		
	}

	public void preLoad(FMLPreInitializationEvent e)
	{
		OutputHandler.SOP("Commands module is enabled. Loading...");
		conf = new ConfigCmd();
	}

	public void load(FMLInitializationEvent e)
	{
		MinecraftForge.EVENT_BUS.register(new EventHandler());
		MinecraftForge.EVENT_BUS.register(this); // for the permissions.
		GameRegistry.registerPlayerTracker(new PlayerTrackerCommands());
	}

	@Override
	public void postLoad(FMLPostInitializationEvent e)
	{
	}

	@Override
	public void serverStarting(FMLServerStartingEvent e)
	{
		//general
		e.registerServerCommand(new CommandMotd());
		e.registerServerCommand(new CommandRules());
		e.registerServerCommand(new CommandModlist());
		//utility
		e.registerServerCommand(new CommandButcher());
		e.registerServerCommand(new CommandRemove());
		e.registerServerCommand(new CommandSpawn());
		e.registerServerCommand(new CommandTPS());
		e.registerServerCommand(new CommandKit());
		e.registerServerCommand(new CommandEnderchest());
		e.registerServerCommand(new CommandVirtualchest());
		e.registerServerCommand(new CommandCapabilities());
		e.registerServerCommand(new CommandSetspawn());
		e.registerServerCommand(new CommandJump());
		e.registerServerCommand(new CommandCraft());
		//op
		e.registerServerCommand(new CommandServerDo());
		//fun
		e.registerServerCommand(new CommandSmite());
		e.registerServerCommand(new CommandBurn());
		e.registerServerCommand(new CommandPotion());
		e.registerServerCommand(new CommandColorize());
		//teleport
		e.registerServerCommand(new CommandBack());
		e.registerServerCommand(new CommandHome());
		e.registerServerCommand(new CommandWarp());
		//cheat
		e.registerServerCommand(new CommandRepair());
		e.registerServerCommand(new CommandHeal());
		//Vanilla Override
		e.registerServerCommand(new CommandKill());
		e.registerServerCommand(new CommandGive());
		e.registerServerCommand(new CommandClearInventory());
		e.registerServerCommand(new CommandGameMode());
		}

	@Override
	public void serverStarted(FMLServerStartedEvent e)
	{
		TickRegistry.registerScheduledTickHandler(new TickHandlerCommands(), Side.SERVER);
		if(removeDuplicateCommands) removeDuplicateCommands(FMLCommonHandler.instance().getMinecraftServerInstance());
	}


	private void removeDuplicateCommands(MinecraftServer server) 
	{
		if(server.getCommandManager() instanceof CommandHandler)
		{
			try
			{
				Set<String> commandNames = new HashSet<String>();
				Set<String> toRemoveNames = new HashSet<String>();
				CommandHandler cmdMng = (CommandHandler) server.getCommandManager();
				
				for(Object cmdObj : cmdMng.commandSet)
				{
					ICommand cmd = (ICommand) cmdObj;
					if(!commandNames.add(cmd.getCommandName()))
					{
						System.out.println("Duplicate command found! Name:" + cmd.getCommandName());
						toRemoveNames.add(cmd.getCommandName());
					}
				}
				Set toRemove = new HashSet();
				for(Object cmdObj : cmdMng.commandSet)
				{
					ICommand cmd = (ICommand) cmdObj;
					if(toRemoveNames.contains(cmd.getCommandName()))
					{
						Class<?> cmdClass = cmd.getClass();
						if(!cmdClass.getPackage().getName().contains("ForgeEssentials"))
						{
							System.out.println("Removing command '" + cmd.getCommandName() + "' from class: " + cmdClass.getName());
							toRemove.add(cmd.getCommandName());
						}
					}
				}
				cmdMng.commandSet.removeAll(toRemove);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	@ForgeSubscribe
	public void registerPermissions(ForgeEssentialsPermissionRegistrationEvent event)
	{
		event.registerPermissionDefault("ForgeEssentials.BasicCommands", false);
		event.registerPermissionDefault("ForgeEssentials.BasicCommands.remove", false);
		event.registerPermissionDefault("ForgeEssentials.BasicCommands.restart", false);
		event.registerPermissionDefault("ForgeEssentials.BasicCommands.rules", false);
		event.registerPermissionDefault("ForgeEssentials.BasicCommands.serverdo", false);
		event.registerPermissionDefault("ForgeEssentials.BasicCommands.smite", false);
		event.registerPermissionDefault("ForgeEssentials.BasicCommands.kill", false);
		event.registerPermissionDefault("ForgeEssentials.BasicCommands.modlist", false);
		event.registerPermissionDefault("ForgeEssentials.BasicCommands.motd", false);
		event.registerPermissionDefault("ForgeEssentials.BasicCommands.burn", false);
		event.registerPermissionDefault("ForgeEssentials.BasicCommands.list", false);
		event.registerPermissionDefault("ForgeEssentials.BasicCommands.compass", false);
		event.registerPermissionDefault("ForgeEssentials.BasicCommands.repair", false);
		event.registerPermissionDefault("ForgeEssentials.BasicCommands.heal", false);
		event.registerPermissionDefault("ForgeEssentials.BasicCommands.tps", false);
		event.registerPermissionDefault("ForgeEssentials.BasicCommands.potion", false);
		event.registerPermissionDefault("ForgeEssentials.BasicCommands.enderchest", false);
		event.registerPermissionDefault("ForgeEssentials.BasicCommands.virtualchest", false);
		event.registerPermissionDefault("ForgeEssentials.BasicCommands.setspawn", false);
		event.registerPermissionDefault("ForgeEssentials.BasicCommands.give", false);
		event.registerPermissionDefault("ForgeEssentials.BasicCommands.clear.self", false);
		event.registerPermissionDefault("ForgeEssentials.BasicCommands.clear.others", false);
		event.registerPermissionDefault("ForgeEssentials.BasicCommands.gamemode.self", false);
		event.registerPermissionDefault("ForgeEssentials.BasicCommands.gamemode.others", false);
		
		event.registerGlobalGroupPermissions(PermissionsAPI.GROUP_OWNERS, "ForgeEssentials.BasicCommands", true);
		event.registerGlobalGroupPermissions(PermissionsAPI.GROUP_MEMBERS, "ForgeEssentials.BasicCommands.compass", true);
		event.registerGlobalGroupPermissions(PermissionsAPI.GROUP_DEFAULT, "ForgeEssentials.BasicCommands.list", true);
		event.registerGlobalGroupPermissions(PermissionsAPI.GROUP_DEFAULT, "ForgeEssentials.BasicCommands.rules", true);
		event.registerGlobalGroupPermissions(PermissionsAPI.GROUP_DEFAULT, "ForgeEssentials.BasicCommands.motd", true);
		event.registerGlobalGroupPermissions(PermissionsAPI.GROUP_DEFAULT, "ForgeEssentials.BasicCommands.tps", true);
		event.registerGlobalGroupPermissions(PermissionsAPI.GROUP_DEFAULT, "ForgeEssentials.BasicCommands.modlist", true);
	}

	@Override
	public void serverStopping(FMLServerStoppingEvent e)
	{
		// TODO Auto-generated method stub

	}
}