package com.zglu.generator;

import com.zglu.generator.entity.Columns;
import com.zglu.generator.mapper.ColumnsMapper;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 代码生成实现类
 *
 * @author zglu
 */
@Log4j2
@Service
@AllArgsConstructor
public class GeneratorService {

    private final ColumnsMapper columnsMapper;
    private final GeneratorConfig generatorConfig;

    public void generate() {
        // 查询表字段
        List<Columns> list;
        if (generatorConfig.getTables().length > 0) {
            list = columnsMapper.findByTableSchemaAndTableNameIn(generatorConfig.getDatabase(), String.join(",", generatorConfig.getTables()));
        } else {
            list = columnsMapper.findByTableSchema(generatorConfig.getDatabase());
        }
        // 按表分组字段
        Map<String, List<Columns>> map = list.stream().collect(Collectors.groupingBy(Columns::getTableName));
        // 按表生成
        map.forEach((k, v) -> {
            try {
                this.createEntity(generatorConfig, k, v);
                this.createRepo(generatorConfig, k);
            } catch (IOException e) {
                log.info(e);
            }
        });
    }

    /**
     * 创建实体类
     *
     * @param generatorConfig 生成配置
     * @param tableName       表名
     * @param list            字段数组
     * @throws IOException io异常
     */
    public void createEntity(GeneratorConfig generatorConfig, String tableName, List<Columns> list) throws IOException {
        String first = generatorConfig.getFirst();
        log.info("读取Entity模版");
        String content = FileUtils.getTemplate(first + "/src/main/resources/template/dao", "Template.java");
        log.info("生成Entity数据");
        EntityVo entityData = new EntityVo(tableName, list, generatorConfig);
        content = ReplaceUtils.replaceContent(content, entityData);
        log.info("生成Entity文件");
        String targetDir = FileUtils.createDir(first + "/target", entityData.getPackageName(), "dao");
        Path path = FileUtils.create(targetDir, entityData.getFileName(), false);
        log.info("写入Entity文件");
        FileUtils.write(path, content);
    }

    /**
     * 创建仓储类
     *
     * @param generatorConfig 生成配置
     * @param tableName       表名
     * @throws IOException io异常
     */
    public void createRepo(GeneratorConfig generatorConfig, String tableName) throws IOException {
        String first = generatorConfig.getFirst();
        log.info("读取Repo模版");
        String content = FileUtils.getTemplate(first + "/src/main/resources/template/dao", "TemplateRepo.java");
        log.info("生成Repo数据");
        String packageName = ReplaceUtils.getPackageName(tableName);
        String entityName = ReplaceUtils.getClassName(tableName);
        String className = entityName + "Repo";
        content = ReplaceUtils.replaceContent(content, packageName, className, entityName);
        log.info("生成Repo文件");
        String targetDir = FileUtils.createDir(first + "/target", packageName, "dao");
        String fileName = className + ".java";
        Path path = FileUtils.create(targetDir, fileName, false);
        log.info("写入Repo文件");
        FileUtils.write(path, content);
    }


}
