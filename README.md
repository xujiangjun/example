* web模块依赖biz模块
* biz模块依赖manager和service模块
* service模块依赖dao模块
* manager模块依赖api、common和service模块
* web模块可直接对biz、manager和service层直接进行操作
* manager模块用于提供和消费dubbo服务

自定义骨架工程：  
1.在根目录example执行命令：mvn archetype:create-from-project  
2.修改target/generated-sources/example的web模块pom文件，修改finalName、另修改archetype-metadata.xml
3.进入目录target/generated-sources/example后执行mvn clean install  
4.在需要生成项目的地方执行命令：mvn archetype:generate -DarchetypeCatalog=local  
5.根据骨架工程生产新的工程后需要作出一些修改：去除api模块pom文件的parent的引用、拷贝.gitignore文件至新项目、修改finalName、修改sql的文件名、
  修改新项目中各模块的版本号等等，最后全局搜索example，看是否有还需替换的东西。

总结：  
1. info级别的日志用于记录调用情况、参数信息和返回值情况。  
2. warn级别的日志用于记录会出现潜在错误的情形。  
3. error级别的日志指出虽然发生错误事件，但仍然不影响系统的继续运行。 
4. fatal级别的日志指出每个严重的错误事件将会导致应用程序的退出。 
5. 如果服务是作为dubbo接口，即使数据项不存在也不应该返回success为false，提供方不做调用方的判断逻辑，只做数据查询工作，但是系统内使用可以自己视情况而定。  
