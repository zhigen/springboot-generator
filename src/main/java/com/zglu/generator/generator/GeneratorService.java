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
    private final TablesMapper tablesMapper;
    private final GeneratorConfig generatorConfig;

    public void generate() {
        // 查询表字段
        List<Columns> list;
        List<Tables> tablesList;
        if (generatorConfig.getTables().length > 0) {
            list = columnsMapper.findByTableSchemaAndTableNameIn(generatorConfig.getDatabase(), String.join(",", generatorConfig.getTables()));
            tablesList = tablesMapper.findByTableSchemaAndTableNameIn(generatorConfig.getDatabase(), String.join(",", generatorConfig.getTables()));
        } else {
            list = columnsMapper.findByTableSchema(generatorConfig.getDatabase());
            tablesList = tablesMapper.findByTableSchema(generatorConfig.getDatabase());
        }
        // 按表分组字段
        Map<String, List<Columns>> map = list.stream().collect(Collectors.groupingBy(Columns::getTableName));
        Map<String, String> tablesMap = tablesList.stream().collect(Collectors.toMap(Tables::getTableName, Tables::getTableComment));
        // 按表生成
        map.forEach((k, v) -> {
            try {
                if ("work".equals(generatorConfig.getUse())) {
                    this.createFilesWork(generatorConfig, k, v, tablesMap.get(k));
                } else {
                    this.createFiles(generatorConfig, k, v, tablesMap.get(k));
                }
            } catch (IOException e) {
                log.info(e);
            }
        });
    }

    /**
     * 创建类
     *
     * @param generatorConfig 生成配置
     * @param tableName       表名
     * @param list            字段数组
     * @throws IOException io异常
     */
    public void createFiles(GeneratorConfig generatorConfig, String tableName, List<Columns> list, String tableComment) throws IOException {
        log.info("开始生成" + tableName + "数据");
        log.info("生成Class数据");
        ClassVo classVo = new ClassVo(tableName, list, generatorConfig, tableComment);

        log.info("读取Entity模版");
        String content = FileUtils.getTemplate(generatorConfig.getTemplatePath() + "/dao", "Temp.java");
        content = ReplaceUtils.replaceContent(content, classVo);
        log.info("生成Entity文件");
        String targetDir = FileUtils.createDir(generatorConfig.getTargetPath(), generatorConfig.getTargetDir(), classVo.getPackageName(), "dao");
        Path path = FileUtils.create(targetDir, classVo.getClassName() + ".java", false);
        log.info("写入Entity文件");
        FileUtils.write(path, content);

        log.info("读取mapper模版");
        content = FileUtils.getTemplate(generatorConfig.getTemplatePath() + "/dao", "TempMapper.java");
        content = ReplaceUtils.replaceContent(content, classVo);
        log.info("生成mapper文件");
        targetDir = FileUtils.createDir(generatorConfig.getTargetPath(), generatorConfig.getTargetDir(), classVo.getPackageName(), "dao");
        path = FileUtils.create(targetDir, classVo.getClassName() + "Mapper.java", false);
        log.info("写入mapper文件");
        FileUtils.write(path, content);

        log.info("读取dao模版");
        content = FileUtils.getTemplate(generatorConfig.getTemplatePath() + "/dao", "TempDao.java");
        content = ReplaceUtils.replaceContent(content, classVo);
        log.info("生成dao文件");
        targetDir = FileUtils.createDir(generatorConfig.getTargetPath(), generatorConfig.getTargetDir(), classVo.getPackageName(), "dao");
        path = FileUtils.create(targetDir, classVo.getClassName() + "Dao.java", false);
        log.info("写入dao文件");
        FileUtils.write(path, content);

        log.info("读取service模版");
        content = FileUtils.getTemplate(generatorConfig.getTemplatePath() + "/service", "TempService.java");
        content = ReplaceUtils.replaceContent(content, classVo);
        log.info("生成service文件");
        targetDir = FileUtils.createDir(generatorConfig.getTargetPath(), generatorConfig.getTargetDir(), classVo.getPackageName(), "service");
        path = FileUtils.create(targetDir, classVo.getClassName() + "Service.java", false);
        log.info("写入service文件");
        FileUtils.write(path, content);

        log.info("读取controller模版");
        content = FileUtils.getTemplate(generatorConfig.getTemplatePath() + "/controller", "TempController.java");
        content = ReplaceUtils.replaceContent(content, classVo);
        log.info("生成controller文件");
        targetDir = FileUtils.createDir(generatorConfig.getTargetPath(), generatorConfig.getTargetDir(), classVo.getPackageName(), "controller");
        path = FileUtils.create(targetDir, classVo.getClassName() + "Controller.java", false);
        log.info("写入controller文件");
        FileUtils.write(path, content);
    }

    /**
     * 创建类
     *
     * @param generatorConfig 生成配置
     * @param tableName       表名
     * @param list            字段数组
     * @throws IOException io异常
     */
    public void createFilesWork(GeneratorConfig generatorConfig, String tableName, List<Columns> list, String tableComment) throws IOException {
        log.info("开始生成" + tableName + "数据");
        log.info("生成Class数据");
        ClassVo classVo = new ClassVo(tableName, list, generatorConfig, tableComment);

        log.info("读取Entity模版");
        String content = FileUtils.getTemplate(generatorConfig.getTemplatePath() + "/work/impl/domain", "Temp.java");
        content = ReplaceUtils.replaceContent(content, classVo);
        log.info("生成Entity文件");
        String targetDir = FileUtils.createDir(generatorConfig.getTargetPath(), generatorConfig.getTargetDir(), classVo.getPackageName(), "impl", classVo.getPackageName(), "domain");
        Path path = FileUtils.create(targetDir, classVo.getClassName() + ".java", false);
        log.info("写入Entity文件");
        FileUtils.write(path, content);

        log.info("读取mapper模版");
        content = FileUtils.getTemplate(generatorConfig.getTemplatePath() + "/work/impl/domain", "TempMapper.java");
        content = ReplaceUtils.replaceContent(content, classVo);
        log.info("生成mapper文件");
        targetDir = FileUtils.createDir(generatorConfig.getTargetPath(), generatorConfig.getTargetDir(), classVo.getPackageName(), "impl", classVo.getPackageName(), "domain");
        path = FileUtils.create(targetDir, classVo.getClassName() + "Mapper.java", false);
        log.info("写入mapper文件");
        FileUtils.write(path, content);

        log.info("读取dao模版");
        content = FileUtils.getTemplate(generatorConfig.getTemplatePath() + "/work/impl/domain", "TempDao.java");
        content = ReplaceUtils.replaceContent(content, classVo);
        log.info("生成dao文件");
        targetDir = FileUtils.createDir(generatorConfig.getTargetPath(), generatorConfig.getTargetDir(), classVo.getPackageName(), "impl", classVo.getPackageName(), "domain");
        path = FileUtils.create(targetDir, classVo.getClassName() + "Dao.java", false);
        log.info("写入dao文件");
        FileUtils.write(path, content);

        log.info("读取service模版");
        content = FileUtils.getTemplate(generatorConfig.getTemplatePath() + "/work/impl/service", "TempService.java");
        content = ReplaceUtils.replaceContent(content, classVo);
        log.info("生成service文件");
        targetDir = FileUtils.createDir(generatorConfig.getTargetPath(), generatorConfig.getTargetDir(), classVo.getPackageName(), "impl", classVo.getPackageName(), "service");
        path = FileUtils.create(targetDir, classVo.getClassName() + "Service.java", false);
        log.info("写入service文件");
        FileUtils.write(path, content);

        log.info("读取controller模版");
        content = FileUtils.getTemplate(generatorConfig.getTemplatePath() + "/work/impl/controller", "TempController.java");
        content = ReplaceUtils.replaceContent(content, classVo);
        log.info("生成controller文件");
        targetDir = FileUtils.createDir(generatorConfig.getTargetPath(), generatorConfig.getTargetDir(), classVo.getPackageName(), "impl", classVo.getPackageName(), "controller");
        path = FileUtils.create(targetDir, classVo.getClassName() + "Controller.java", false);
        log.info("写入controller文件");
        FileUtils.write(path, content);

        log.info("读取api模版");
        content = FileUtils.getTemplate(generatorConfig.getTemplatePath() + "/work/api", "TempApi.java");
        content = ReplaceUtils.replaceContent(content, classVo);
        log.info("生成api文件");
        targetDir = FileUtils.createDir(generatorConfig.getTargetPath(), generatorConfig.getTargetDir(), classVo.getPackageName(), "api", classVo.getPackageName());
        path = FileUtils.create(targetDir, classVo.getClassName() + "Api.java", false);
        log.info("写入api文件");
        FileUtils.write(path, content);

        log.info("读取dto模版");
        content = FileUtils.getTemplate(generatorConfig.getTemplatePath() + "/work/api", "TempDto.java");
        content = ReplaceUtils.replaceContent(content, classVo);
        log.info("生成dto文件");
        targetDir = FileUtils.createDir(generatorConfig.getTargetPath(), generatorConfig.getTargetDir(), classVo.getPackageName(), "api", classVo.getPackageName());
        path = FileUtils.create(targetDir, classVo.getClassName() + "Dto.java", false);
        log.info("写入dto文件");
        FileUtils.write(path, content);

        log.info("读取feign模版");
        content = FileUtils.getTemplate(generatorConfig.getTemplatePath() + "/work/api", "TempFeign.java");
        content = ReplaceUtils.replaceContent(content, classVo);
        log.info("生成feign文件");
        targetDir = FileUtils.createDir(generatorConfig.getTargetPath(), generatorConfig.getTargetDir(), classVo.getPackageName(), "api", classVo.getPackageName());
        path = FileUtils.create(targetDir, classVo.getClassName() + "Feign.java", false);
        log.info("写入feign文件");
        FileUtils.write(path, content);
    }

}
