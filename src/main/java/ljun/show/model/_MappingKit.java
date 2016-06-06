package ljun.show.model;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;

/**
 * Generated by JFinal, do not modify this file.
 * <pre>
 * Example:
 * public void configPlugin(Plugins me) {
 *     ActiveRecordPlugin arp = new ActiveRecordPlugin(...);
 *     _MappingKit.mapping(arp);
 *     me.add(arp);
 * }
 * </pre>
 */
public class _MappingKit {

	public static void mapping(ActiveRecordPlugin arp) {
		arp.addMapping("auth", "id", Auth.class);
		arp.addMapping("comment", "id", Comment.class);
		arp.addMapping("po", "id", Po.class);
		arp.addMapping("role", "id", Role.class);
		arp.addMapping("user", "id", User.class);
	}
}

