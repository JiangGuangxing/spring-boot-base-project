## 工程搭建过程：

### 一、创建spring boot工程 

>1. IDEA中选择file->new->project->spring initializr,点击next
	 
>2. 填写maven坐标信息，type选择gralde project，点击next
	 
>3. 搜索框输入freemarker，选中freemarker templeting engine，点击next，finish。
	 
>4. spring boot默认读取application.properties或者application.yml内容，将application.properties配置文件改为application.yml，因为.yml格式配置文件内容更加清晰，配置freemarker属性，application.yml内容最后统一说明
	
*说明：spring boot默认模版引擎为thymeleaf，语法比较复杂，spring boot 1.5以上版本移除了对velocity的支持，所以在创建工程的时候选择了freemarker。*

### 二、集成mybatis
>1. 添加依赖：compile('org.springframework.boot:spring-boot-starter-jdbc')、compile group: 'org.mybatis.spring.boot', name: 'mybatis-spring-boot-starter', version: '1.3.1'、 compile 'mysql:mysql-connector-java:6.0.6'、compile group: 'com.zaxxer', name: 'HikariCP', version: '2.6.0'
>2. 配置领域模型包路径以及mapper配置文件路径
>3. 配置HikariDataSource数据源
>4. 新建AppConf.java，加入@Configuration、@MapperScan("com.nxin.price2.mapper")，配置mapper扫描路径
>5. 加入mybatis-config.xml配置文件
	
### 三、集成mapper、pagehelper插件
>1. 添加依赖：compile group: 'com.github.pagehelper', name: 'pagehelper-spring-boot-starter', version: '1.2.3'、 compile group: 'tk.mybatis', name: 'mapper-spring-boot-starter', version: '1.1.5'	
>2. 配置mapper、pagehelper插件属性

### 四、其它
>1. configurations {
		compile.exclude group: "org.springframework.boot", module: "spring-boot-starter-logging"
		compile.exclude group: "org.springframework.boot", module: "spring-boot-starter-tomcat"
	}
	compile 'org.springframework.boot:spring-boot-starter-undertow'
    compile 'org.springframework.boot:spring-boot-starter-log4j2'，compile group: 'org.apache.logging.log4j', name: 'log4j-1.2-api', version: '2.8.2'
	将内嵌tomcat替换为undertow（undertow性能优于tomcat，但是不支持jsp），将spring默认logback日志改为logfj2，加入log4j2.xml配置文件


   
### application.yml说明：
```java
mybatis:
  type-aliases-package: com.github.JiangGuangxing.model	领域模型包路径
  mapper-locations: classpath:mapper/*.xml		mapper配置文件路径
mapper:
  mappers: com.github.JiangGuangxing.core.BaseMapper		mapper插件基类路径，不要放在mapper扫描路径下
  not-empty: false	
  identity: MYSQL
pagehelper:		分页插件配置
  helperDialect: mysql
  reasonable: true
  supportMethodsArguments: true
  params: count=countSql
spring:
  jackson:
    date-format: yyyy-MM-dd HH:mm:ss
  datasource:
    type: com.zaxxer.hikari.HikariDataSource	HikariDataSource数据源配置
    url: jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull
    username: root
    password: 
    driver-class-name: com.mysql.jdbc.Driver


 
  
