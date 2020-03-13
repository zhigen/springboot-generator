# springboot-generator
> 本项目使用springboot框架，通过mybatis进行数据库连接后进行代码生成

# 目录
* [1 创建项目](#01)
* [2 启动数据库](#02)
* [3 编写代码](#03)
* [4 测试](#04)

## <div id="01"></div>
## 1 创建项目
> 参照或复制springboot-maven项目

## <div id="02"></div>
## 2 启动数据库
    2.1、docker pull mysql
    2.2、docker run -d -p 3306:3306 -v /F/data/docker/mysql:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=123456 mysql
    2.3、数据库增加zglu库，zglu库增加user表
    
## <div id="03"></div>
## 3 编写代码    
    3.1、pom.xml文件引入依赖
    3.2、application.properties添加配置项
    3.3、编写mapper.java文件
    3.4、指定需扫描mapper包
    3.5、编写代码生成相关
        3.5.1、编写配置类GeneratorConfig
        3.5.2、添加配置项application.properties
        3.5.3、编写java字段对象类FieldVo
        3.5.4、编写实体对象类EntityVo
        3.5.5、编写模版
        3.5.6、编写字符替换工具类ReplaceUtils
        3.5.7、编写文件生成工具类FileUtils
        3.5.8、编写代码生成实现类GeneratorService

## <div id="04"></div>
## 4 测试  
> 运行测试类ApplicationTests