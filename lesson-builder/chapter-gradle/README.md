

# Gradle



## 基本命令

* `gradle` 初始化 `gradle init`

* 构建 `gradle build`

## 基本使用

* 所有项目

  ```groovy
  allprojects {
  }
  ```

* 子项目

  ```groovy
  subprojects {  
  }
  ```

* 指定项目配置

  ```groovy
  project("gradle-sub1") {
  
      dependencies {
          compile group: 'com.google.guava', name: 'guava', version: '27.0.1-jre'
      }
  
  }
  ```

* 引入外部文件

  ```groovy
  apply from: this.file("common.gradle")
  ```

* 厂库配置

  ```groovy
  repositories {
      // 本地仓库
      mavenLocal()
      // 设置远程
      maven {url  "F:/workspace/apache-maven-3.3.9/repo"}
      maven { url "http://maven.aliyun.com/nexus/content/groups/public/" }
      mavenCentral()
  
      google()
      jcenter()
  }
  ```

* **引入必须插件** 

  ```groovy
  apply plugin: "java"
  ```

  如果不引人该插件基本用不了，文件`build.gradle` 文件执行是从上往下执行的

* 设置源码和编译后使用JDK版本,需要设置在`apply plugin: "java"`后面 

  ```groovy
  sourceCompatibility = 1.8
  targetCompatibility = 1.8
  ```

* 设置`build`编译后文件存储位置

  ```groovy
  buildDir = 'target/'
  ```

* 设置`gradle` 脚本 扩展属性配置 `gradle.properties`

  ```groovy
  buildscript {
      ext {
          // 设置全局变量
          springBootVersion = '2.0.1.RELEASE'
  
      }
  }
  
  println springBootVersion
  println springVersion
  
  ```

  `gradle.properties` 配置

  ```properties
  springVersion=2.0.0
  ```

* 依赖类型

  1. >implementation （gradle4.x） 这个指令的特点就
     >是，对于使用了该命令编译的依赖，对该项目有依
     >赖的项目将无法访问到使用该命令编译的依赖中的
     >任何程序，也就是将该依赖隐藏在内部，而不对外部公开

  2. >api 完全等同于compile指令

  3. >compile （gradle3.x） 这种是我们最常用的方
     >式，使用该方式依赖的库将会参与编译和打包

  4. >testCompile testCompile 只在单元测试代码的编
     >译以及最终打包测试apk时有效

  5. >debugCompile debugCompile 只在debug模式的
     >编译和最终的debug apk打包时有效

  6. >releaseCompile 仅仅针对Release模式的编译和最
     >终的Release apk打包

  7. >runtime配置项中包含的依赖在运行时是必须的

  8. >testRuntime配置项中包含的依赖在运行测试代码时是必须的

  9. >archives配置项中包含项目生成的文件（如Jar文件）

  10. >providedRuntime  和 apply plugin: 'war'共存

  11. >optional 

* 排除依赖

  ```groovy
  compile (group: 'org.springframework.boot', name: 'spring-boot-starter-web', version: '2.0.7.RELEASE') {
          exclude  group: 'org.springframework.boot', module: 'spring-boot-starter-tomcat'
          exclude  group: 'org.springframework.boot', module: 'spring-boot-starter'
  }
  ```

  * 排除全部依赖

    ```groovy
    configurations {
            all*.exclude group: 'org.springframework.boot',module:'spring-boot-starter-logging'
            all*.exclude group: 'ch.qos.logback'
        }
    
    
    ```

    等价于

    ```groovy
    configurations.all {
    
            exclude group: 'org.springframework.boot',module:'spring-boot-starter-logging'
            exclude group: 'ch.qos.logback'
        }
    ```


* 禁止传递依赖

  比如引入一个包会携带很多其他而外的引入

  * 指定依赖禁用传递依赖

    ```groovy
    compile(group: 'org.springframework.boot',name: 'spring-boot-starter-web'){
                transitive = false
            }
    ```

  * 指定全局禁用

    ```groovy
    configurations.all {
            transitive = false
        }
    ```

  * `@`符号表示禁止传递

    ```groovy
    compile 'org.springframework.boot:spring-boot-starter-data-redis@jar'
    ```

* 冲突策略

  * 强制一个版本

    ```groovy
    configurations.all {
       resolutionStrategy{
          failOnVersionConflict();
          force 'org.slf4j:slf4japi:1.7.25'
       }
    }
    ```

  * 默认冲突策略

    ```groovy
    
    ```

  * 始终选择最新的包依赖

    ```groovy
    compile 'org.springframework.boot:spring-boot-starter-data-redis:+'
    ```


* 发布到厂库

  ```groovy
   publishing {
      publications {
          publish(MavenPublication) {
              groupId 'com.gupao.edu.vip.plugin'
              artifactId 'myplugin'
              version '1.0.4'
              from components.java
          }
      }
      repositories {
          maven {
              url uri("D:\\Temp")
          }
      }
  }
  ```

  * 可打包成源码发布

    ```groovy
    
    apply plugin: "maven-publish"
    task apiBaseJar(type:Jar){
        //version apiBaseJarVersion
        //baseName apiBaseJarName
        from sourceSets.main.output
    //    destinationDir file("$buildDir/api-libs")
    //    includes ['com/ai/gcf/api/base/**']
        //manifest {
          //  attributes 'packageName': apiBaseJarName, 'Built-By': builtBy,'Built-date': new Date().format('yyyy-MM-dd HH:mm:ss'),'Manifest-Version':version
       // }
    }
    
    
    task apiBaseSourceJar(type:Jar){
    
        classifier "sources"
        from sourceSets.main.allSource
    //    destinationDir file("${buildDir}/api-libs")
     //   includes ['com/ai/gcf/api/base/**']
    //    manifest {
    //        attributes 'packageName': apiBaseJarName+'-sources', 'Built-By': builtBy,'Built-date': new Date().format('yyyy-MM-dd HH:mm:ss'),'Manifest-Version':version
    //    }
    }
    
     publishing {
        publications {
            publish(MavenPublication) {
                groupId 'com.gupao.edu.vip.plugin'
                artifactId 'myplugin'
                version '1.0.7'
                //from components.java
                artifacts =[apiBaseSourceJar,apiBaseJar]
            }
        }
        repositories {
            maven {
                url uri("D:\\Temp")
            }
        }
    }
    ```

* `指定源码路径设置`

  ```groovy
  sourceSets {
      main {
          java {
              srcDir 'src/java' // 指定
          } resources {
              srcDir 'src/resources' //资
          }
      }
  }
  ```

* 动态引入文件

  ```groovy
  if (!hasProperty('buildProfile')) ext.buildProfile = 'default'
  apply from: "profile-${buildProfile}.gradle"
  ```

* `Spring Boot`依赖管理

  * 单块使用

  ```groovy
  buildscript {
      repositories {
          mavenLocal()
          maven { url "http://maven.aliyun.com/nexus/content/groups/public/" }
          mavenCentral()
      }
      dependencies {
          classpath "org.springframework.boot:spring-boot-gradle-plugin:2.0.7.RELEASE"
      }
  }
  apply plugin: "org.springframework.boot"
  apply plugin: 'io.spring.dependency-management'
  ```

  ```groovy
  plugins {
      id "org.springframework.boot" version "2.0.7.RELEASE"
  }
  apply plugin: 'io.spring.dependency-management'
  ```

  * 全局使用

  ```groovy
  buildscript {
      repositories {
          mavenLocal()
          maven { url "http://maven.aliyun.com/nexus/content/groups/public/" }
          mavenCentral()
      }
      dependencies {
          classpath "org.springframework.boot:spring-boot-gradle-plugin:2.0.7.RELEASE"
      }
  }
  allprojects {
      apply plugin: "java"
      apply plugin: 'io.spring.dependency-management'
      apply plugin: "org.springframework.boot"
      apply plugin: 'io.spring.dependency-management'
   
      dependencies {
  
          compile(group: 'org.springframework.boot',name: 'spring-boot-starter-web')
          compile group: 'org.springframework',name: 'spring-aop'
  
      }
  
  
  }
  ```



* `jar` 插件

  ```groovy
  jar {
    baseName = 'springbootapplication'
    version = '0.0.1-SNAPSHOT'
  }
  ```

* `java`插件

  ```groovy
  plugins {
  id 'java'
  }
  apply plugin: 'java'
  apply plugin: 'war'
  
  ```

* `application`插件

  ```groovy
  apply plugin: 'application'
  mainClassName = appMainClass
  ```

* 自定义插件之创建目录

  ```groovy
  def createDir = {
      path ->
          File dir = new File(path)
          if(!dir.exists()){
              dir.mkdirs()
          }
  }
  
  task createJavaDir() {
      def paths = ['src/main/java','src/main/resources',
                   'src/test/java',
                   'src/test/resources']
      //在任务执行之前运行
      doFirst{
          paths.forEach(createDir)
      }
  }
  
  task createWebDir(){
      def paths = ['src/main/webapp','src/test/webapp']
      dependsOn 'createJavaDir'
      doLast{
          paths.forEach(createDir)
      }
  }
  ```

* `gradle` 事件监听

  ```groovy
  /**
   * 配置阶段开始前的监听
   */
  this.beforeEvaluate {
      println '配置开始前'
  }
  
  /**
   * 配置阶段开始后的监听
   */
  this.afterEvaluate {
      println '配置完成后'
  }
  
  /**
   * 执行完成的监听
   */
  this.gradle.buildFinished {
      println '执行阶段完毕'
  }
  ```

* 模块项目引入

  ```groovy
  project ('base') {
  //	dependencies {
  //		compile project (':springboot-data')
  //		compile project (':springboot-web')
  //		compile project (':springboot-shiro')
  //		compile project (':springboot-base')
  //	}
  
  }
  ```

* 打包插件

  ```groovy
  jar {
      manifest {
          attributes 'Main-Class': 'org.example.dubbo.ConsumerAsync'
      }
      into('lib') {
          from configurations.runtime
      } 
  }
  
  ```

  ```groovy
  
  ```



  * 打包排除

    ```groovy
    task zip(type: Zip) {
        into('lib') {
            from(configurations.runtime) {
                exclude '*unwanted*', '*log*'
            }
        }
        into('') {
            from jar
            from 'doc'
        }
    }
    ```

  * 只选择某些包

    ```groovy
    task zip(type: Zip) {
        into('lib') {
            from(configurations.runtime) {
                include '*ar4j*', '*spring*'
            }
        }
        into('') {
            from jar
            from 'doc'
        }
    }
    ```

  * `dependencyManagement`管理依赖

    ```groovy
    
    
    dependencies {
        compile('org.springframework.cloud:spring-cloud-starter')
    
    }
    dependencyManagement {
        imports {
            mavenBom "org.springframework.cloud:spring-cloud-dependencies:Finchley.RELEASE"
        }
    }
    ```

* `compileJava.dependsOn(processResources)`

  处理 Spring  `additional-spring-configuration-metadata.json`数据,`spring-boot-configuration-processor` 

  在`gradle4.5`之前版本使用

  ```groovy
  dependencies {
  	compileOnly "org.springframework.boot:spring-boot-configuration-processor"
  }
  compileJava.dependsOn(processResources)
  ```

  在`gradle4.6`之后

  ```groovy
  dependencies {
  	annotationProcessor "org.springframework.boot:spring-boot-configuration-processor"
  }
  compileJava.dependsOn(processResources)
  ```

  必须使用`org.springframework.boot.context.properties.ConfigurationProperties`注解

  [参考资料](https://docs.spring.io/spring-boot/docs/2.0.7.RELEASE/reference/html/configuration-metadata.html#configuration-metadata-annotation-processor)
