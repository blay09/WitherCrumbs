package turkey.witherCrumbs.config;

import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.Level;

import net.minecraftforge.common.config.Configuration;
import turkey.witherCrumbs.WitherCrumbsCore;

public class ConfigLoader
{
	public static Configuration config;
	public static final String genCat = "General Settings";

	public static void loadConfigSettings(File file, File resources)
	{
		File fileFolder = new File(file.getParentFile().getAbsolutePath() + "/WitherCrumbs");
		fileFolder.mkdirs();
		config = new Configuration(new File(fileFolder + "/" + file.getName()));
		config.load();

		WitherCrumbSettings.dropNetherStar = config.getBoolean("DropsNetherStar", genCat, false, "Set to true if the Wither's from wither crumbs should drop nether stars.");
		
		config.save();

		File customWithers = new File(file.getParentFile().getAbsolutePath() + "/WitherCrumbs/CustomWithers.json");

		try
		{
			if(!customWithers.exists())
				customWithers.createNewFile();
		} catch(IOException e)
		{
			WitherCrumbsCore.logger.log(Level.ERROR, "Failed to create the custom WitherCrumbs file!!");
			e.printStackTrace();
		}

		new CustomWitherLoader(customWithers);
	}
}