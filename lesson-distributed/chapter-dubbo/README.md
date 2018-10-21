
# Dubbo

## SPI

* 定位文件夹

  `META-INF/services/`，`META-INF/dubbo/` , `META-INF/internal/`

* 文件名规则 通过接口名定义文件名

### 扩展规则

  **标记该类为扩展到  必须是接口 必须 标记`@SPI ` ** 

* 自适应可以是接口方法，也可以是实现类。

  * 如果是接口方法

    ```java
    @SPI("productService")
    public interface IProductService {
        @Adaptive("customAdaptive")
        String getName(String name,URL url);
    }
    
    ```

    `dubbo spi` 会自动生成 接口名+`$Adpative` 类

    ```java
    public class IProductService$Adpative implements IProductService {
        public java.lang.String getName(String arg0, URL arg1) {
            if (arg1 == null) throw new IllegalArgumentException("url == null");
            com.alibaba.dubbo.common.URL url = arg1;
            String extName = url.getParameter("customAdaptive");
            if (extName == null)
                throw new IllegalStateException("Fail to get extension(IProductService) name from url(" + url.toString() + ") use keys([i.product.service])");
           IProductService extension =
                    ExtensionLoader.getExtensionLoader(IProductService.class)
                                    .getExtension(extName);
            return extension.getName(arg0, arg1);
        }
    }
    ```

    在`i.product.service`可以通过`Adaptive("自定义名称修改")`

    在`@SPI("默认值得值")` 那么生成的`String extName = url.getParameter("customAdaptive");`会添加默认值 `url.getParameter("customAdaptive", "productService")`

    **可以通过URL参数指定`customAdaptive`**

    ```java
           URL url = new URL("zep","localhost",33);
            url = url.addParameter("i.product.service","buyProductService");
            System.out.println(productService.getName("",url));
    ```

    ps : *设置值之后返回的是一个新对象必须重新赋值*

  * 通过实现类实现

    ```java
    @Adaptive
    public class BuyProductService implements IProductService {
    
        @Override
        public String getName(String name, URL url) {
            return "购买";
        }
    }
    ```

    **适配器都会走该类如果配置了 接口适配方法也会优先走适配实现类 ，如果有多个`@Adaptive` 标记实现类会优先取第一个**

* 自适应的使用

  ```java
  IProductService productService = ExtensionLoader.getExtensionLoader(IProductService.class).getAdaptiveExtension();
  ```

  调用该类会走以上两种适应方法

* 通过指定名称调用

  ```java
  IProductService productService = ExtensionLoader.getExtensionLoader(IProductService.class).getExtension("productService");
  ```

  **`productService`不能为自适应表示`@Adaptive`类，不然会抛出找不到名为`productService`的类**

  

* `wrapper`的实现主要是 通过制定名称调用来实现 对调用类进行装饰，装饰类必须实现调用类的接口，必须有一个构造参数接收该接口的实现，这样可以通过`wrapper` 调用类的装饰

  ```properties
  productServiceWrapper=com.chapter.distributed.dubbo.spi.impl.ProductServiceWrapper
  ```

  如果有多个`wrapper`类的话，调用返回的是第一个，依次类包装责任链模式，最后一个才是目标类

  实现装饰类

  ```java
  public class ProductServiceWrapper implements IProductService {
  
      private IProductService productService;
  
      public ProductServiceWrapper(IProductService productService) {
  
          this.productService = productService;
  
      }
      @Override
      public String getName(String name,URL url) {
          return "wrapper";
      }
  }
  ```

  必须有一个该接口形参的构造方法，且构造接收的有可能是目标类有可能是下一个`wrapper`类

  

 