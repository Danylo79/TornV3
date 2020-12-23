package dev.dankom.torn.settings;

import dev.dankom.torn.Torn;
import dev.dankom.torn.module.base.Module;

import java.util.ArrayList;

//Your Imports

/**
 *  Made by HeroCode
 *  it's free to use
 *  but you have to credit him
 *
 *  @author HeroCode
 */
public class SettingsManager {
	
	private ArrayList<Setting> settings;
	
	public SettingsManager(){
		this.settings = new ArrayList<Setting>();
	}
	
	public void rSetting(Setting in){
		this.settings.add(in);
	}
	
	public ArrayList<Setting> getSettings(){
		return this.settings;
	}
	
	public ArrayList<Setting> getSettingsByMod(Module mod){
		ArrayList<Setting> out = new ArrayList<Setting>();
		for(Setting s : getSettings()){
			if(s.getParentMod().equals(mod)){
				out.add(s);
			}
		}
		if(out.isEmpty()){
			return null;
		}
		return out;
	}
	
	public Setting getSettingByName(String name){
		for(Setting set : getSettings()){
			if(set.getName().equalsIgnoreCase(name)){
				return set;
			}
		}
		System.err.println("[Torn] Error Setting NOT found: '" + name +"'!");
		return null;
	}

	public Setting getSetting(Module module, String name){
		for(Setting set : getSettings()){
			if(set.getName().equalsIgnoreCase(name) && set.getParentMod() == module){
				return set;
			}
		}
		System.err.println("[Torn] Error Setting NOT found: '" + name +"'!");
		return null;
	}

	public Setting getSetting(String moduleName, String name){
		for(Setting set : getSettings()){
			if(set.getName().equalsIgnoreCase(name) && set.getParentMod() == Torn.getModuleManager().getModule(moduleName)){
				return set;
			}
		}
		System.err.println("[Torn] Error Setting NOT found: '" + name +"'!");
		return null;
	}
}