package ljun.show.route;

import com.jfinal.config.*;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.ehcache.EhCachePlugin;
import com.jfinal.render.ViewType;
import ljun.show.handler.H5Handler;
import ljun.show.model._MappingKit;

/**
 * Created by cocotyty on 16-2-17.
 */
public class App extends JFinalConfig {

    @Override
    public void configConstant(Constants constants) {
        PropKit.use("app.properties");
        loadPropertyFile("app.properties");
        constants.setDevMode(PropKit.getBoolean("mode", false));
        constants.setBaseViewPath("/");
        constants.setViewType(ViewType.FREE_MARKER);
    }

    @Override
    public void configRoute(Routes routes) {
        routes.add(new AdminRoutes());
    }

    @Override
    public void configPlugin(Plugins plugins) {
        plugins.add(new EhCachePlugin());
        DruidPlugin druidPlugin = new DruidPlugin(getProperty("database.url"), getProperty("database.username"), getProperty("database.password"));
        druidPlugin.setMinIdle(getPropertyToInt("database.minIdle"));
        druidPlugin.setMaxActive(getPropertyToInt("database.maxActive"));
        druidPlugin.setInitialSize(getPropertyToInt("database.initSize"));
        plugins.add(druidPlugin);

        ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);
        _MappingKit.mapping(arp);
        plugins.add(arp);
    }

    @Override
    public void configInterceptor(Interceptors interceptors) {

    }

    @Override
    public void configHandler(Handlers handlers) {
        handlers.add(new H5Handler());
    }

}
