

# Gradle



## 基本命令

* `gradle` 初始化 `gradle init`

* 构建 `gradle build`

## 基本使用

### 全局控制

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

* 设置源码和编译后使用JDK版本,需要设置在后面

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

* 排除依赖

  ```groovy
  compile (group: 'org.springframework.boot', name: 'spring-boot-starter-web', version: '2.0.7.RELEASE') {
          exclude  group: 'org.springframework.boot', module: 'spring-boot-starter-tomcat'
          exclude  group: 'org.springframework.boot', module: 'spring-boot-starter'
      }
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
