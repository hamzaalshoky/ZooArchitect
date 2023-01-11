package net.itshamza.za.misc;

import net.itshamza.za.misc.ZAAdvancementTrigger;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.resources.ResourceLocation;

public class ZAAdvancementTriggerRegistry {

    public static ZAAdvancementTrigger TOOTHLESS = new ZAAdvancementTrigger(new ResourceLocation("za:toothless"));

    public static void init(){
        CriteriaTriggers.register(TOOTHLESS);
    }

}
