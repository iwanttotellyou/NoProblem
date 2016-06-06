package ljun.show.config;

import com.jfinal.kit.PathKit;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.activerecord.generator.Generator;
import com.jfinal.plugin.druid.DruidPlugin;

import javax.sql.DataSource;

/**
 * Created by LJun on 16/5/27.
 */
public class DataBaseGenerator {
    public static DataSource getDataSource() {
        Prop p = PropKit.use("app.properties");
        DruidPlugin druidPlugin = new DruidPlugin(p.get("database.url"), p.get("database.username"), p.get("database.password"));
        druidPlugin.start();
        return druidPlugin.getDataSource();
    }

    public static void main(String[] args) {
        // base model 所使用的包名
        String baseModelPackageName = "ljun.show.model.base";
        // base model 文件保存路径
        String baseModelOutputDir = PathKit.getWebRootPath() + "/src/main/java/ljun/show/model/base";
        System.out.println(baseModelOutputDir);
        // model 所使用的包名 (MappingKit 默认使用的包名)
        String modelPackageName = "ljun.show.model";
        // model 文件保存路径 (MappingKit 与 DataDictionary 文件默认保存路径)
        String modelOutputDir = baseModelOutputDir + "/..";

        // 创建生成器
        Generator gen = new Generator(getDataSource(), baseModelPackageName, baseModelOutputDir, modelPackageName, modelOutputDir);
        // 设置数据库方言
        gen.setDialect(new MysqlDialect());
        // 添加不需要生成的表名
        gen.addExcludedTable("adv");
        // 设置是否在 Model 中生成 dao 对象
        gen.setGenerateDaoInModel(true);
        // 设置是否生成字典文件
        gen.setGenerateDataDictionary(false);
        // 设置需要被移除的表名前缀用于生成modelName。例如表名 "osc_user"，移除前缀 "osc_"后生成的model名为 "User"而非 OscUser
        gen.setRemovedTableNamePrefixes("");
        // 生成
        gen.generate();
    }
}
