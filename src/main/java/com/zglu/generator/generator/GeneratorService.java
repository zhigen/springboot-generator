package com.zglu.generator.generator;

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
                this.createEntityMybatis(generatorConfig, k, v);
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
    public void createEntityMybatis(GeneratorConfig generatorConfig, String tableName, List<Columns> list) throws IOException {
        log.info("开始生成" + tableName + "数据");
        String first = generatorConfig.getFirst();
        log.info("生成Entity数据");
        EntityVo entityData = new EntityVo(tableName, list, generatorConfig);
        log.info("读取Entity模版");
        String content = FileUtils.getTemplate(first + generatorConfig.getTemplatePath() + "/dao", "Temp.java");
        content = ReplaceUtils.replaceContent(content, entityData);
        log.info("生成Entity文件");
        String targetDir = FileUtils.createDir(first + generatorConfig.getTargetPath(), generatorConfig.getTargetDir(), entityData.getPackageName(), "dao");
        Path path = FileUtils.create(targetDir, entityData.getClassName() + ".java", false);
        log.info("写入Entity文件");
        FileUtils.write(path, content);

        log.info("读取mapper模版");
        content = FileUtils.getTemplate(first + generatorConfig.getTemplatePath() + "/dao", "TempMapper.java");
        content = ReplaceUtils.replaceContent(content, entityData);
        log.info("生成mapper文件");
        targetDir = FileUtils.createDir(first + generatorConfig.getTargetPath(), generatorConfig.getTargetDir(), entityData.getPackageName(), "dao");
        path = FileUtils.create(targetDir, entityData.getClassName() + "Mapper.java", false);
        log.info("写入mapper文件");
        FileUtils.write(path, content);

        log.info("读取dao模版");
        content = FileUtils.getTemplate(first + generatorConfig.getTemplatePath() + "/dao", "TempDao.java");
        content = ReplaceUtils.replaceContent(content, entityData);
        log.info("生成dao文件");
        targetDir = FileUtils.createDir(first + generatorConfig.getTargetPath(), generatorConfig.getTargetDir(), entityData.getPackageName(), "dao");
        path = FileUtils.create(targetDir, entityData.getClassName() + "Dao.java", false);
        log.info("写入dao文件");
        FileUtils.write(path, content);

        log.info("读取service模版");
        content = FileUtils.getTemplate(first + generatorConfig.getTemplatePath() + "/service", "TempService.java");
        content = ReplaceUtils.replaceContent(content, entityData);
        log.info("生成service文件");
        targetDir = FileUtils.createDir(first + generatorConfig.getTargetPath(), generatorConfig.getTargetDir(), entityData.getPackageName(), "service");
        path = FileUtils.create(targetDir, entityData.getClassName() + "Service.java", false);
        log.info("写入service文件");
        FileUtils.write(path, content);

        log.info("读取controller模版");
        content = FileUtils.getTemplate(first + generatorConfig.getTemplatePath() + "/controller", "TempController.java");
        content = ReplaceUtils.replaceContent(content, entityData);
        log.info("生成controller文件");
        targetDir = FileUtils.createDir(first + generatorConfig.getTargetPath(), generatorConfig.getTargetDir(), entityData.getPackageName(), "controller");
        path = FileUtils.create(targetDir, entityData.getClassName() + "Controller.java", false);
        log.info("写入controller文件");
        FileUtils.write(path, content);
    }

}
