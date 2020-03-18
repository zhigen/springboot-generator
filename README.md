# springboot-generator
> 本项目使用springboot框架，通过mybatis进行数据库连接后进行代码生成

# 目录
* [1 创建项目](#01)
* [2 启动数据库](#02)
* [4 编写代码](#03)
* [5 测试](#04)

## <div id="01"></div>
## 1 创建项目
> 参照或复制[springboot-mybatis](https://github.com/zhigen/springboot-mybatis)

## <div id="02"></div>
## 2 启动数据库
    2.1、docker pull mysql
    2.2、docker run -d -p 3306:3306 -v /F/data/docker/mysql:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=123456 mysql
    2.3、确认2.2挂载目录后执行，开启服务
    2.4、数据库增加zglu库
    2.5、zglu库增加zglu_test表
> 参考[test.sql](https://github.com/zhigen/springboot-generator/tree/master/src/main/resources/test.sql)

## <div id="03"></div>
## 3 编写代码
    3.1、pom.xml文件引入依赖
    3.2、application.properties添加配置项
    3.3、编写mapper.java文件
    3.4、Application指定需扫描mapper包
    3.5、编写代码生成相关
        3.5.1、编写配置类GeneratorConfig
        3.5.2、添加配置项application.properties
        3.5.3、编写java属性对象类FieldVo
        3.5.4、编写java类对象类ClassVo
        3.5.5、编写模版
        3.5.6、编写字符替换工具类ReplaceUtils
        3.5.7、编写文件生成工具类FileUtils
        3.5.8、编写代码生成实现类GeneratorService

## <div id="04"></div>
## 4 测试
    4.1、http://localhost:8080/swagger-ui.html生成文件
    4.2、文件生成成功，修改application.properties数据库连接属性，将数据库指定为生成类所在库
    4.3、刷新http://localhost:8080/swagger-ui.html