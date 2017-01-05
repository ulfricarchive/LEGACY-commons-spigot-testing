package com.ulfric.commons.spigot.testing.plugin;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.junit.jupiter.api.Test;

import com.ulfric.verify.Verify;

public abstract class JavaPluginTestBase {

	protected JavaPluginTestBase()
	{
		this.pluginClass = this.getClass().getAnnotation(Plugin.class).value();
	}

	protected final Class<? extends JavaPlugin> pluginClass;

	@Test
	void testPluginClassIsNotInstantiable()
	{
		Verify.that(() -> this.pluginClass.newInstance()).doesThrow(IllegalStateException.class,
				"JavaPlugin requires org.bukkit.plugin.java.PluginClassLoader");
	}

	@Test
	void testPluginClassIsNotListener()
	{
		Verify.that(Listener.class).isNotAssignableTo(this.pluginClass);
	}

}