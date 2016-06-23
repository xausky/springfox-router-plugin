# springfox-router-plugin

> springmvc-router use to springfox plugin

## How to use

### 1.java config:

#### 1.1 config pom.xml

```
<properties>
	<router.version>1.2.0</router.version>
	<springfox.version>2.5.0</springfox.version>
</properties>
<dependencies>
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-web</artifactId>
	</dependency>
	<dependency>
		<groupId>org.resthub</groupId>
		<artifactId>springmvc-router</artifactId>
		<version>${router.version}</version>
	</dependency>
	<dependency>
		<groupId>io.springfox</groupId>
		<artifactId>springfox-swagger-ui</artifactId>
		<version>${springfox.version}</version>
	</dependency>
	<dependency>
		<groupId>io.springfox</groupId>
		<artifactId>springfox-swagger2</artifactId>
		<version>${springfox.version}</version>
	</dependency>
	<dependency>
		<groupId>io.github.synfirecloud</groupId>
		<artifactId>springfox-router-plugin-core</artifactId>
		<version>${version}</version>
	</dependency>
</dependencies>
```

#### 1.2.config WebAppConfig

change Springmvc-router WebAppConfig extends RouterConfigurationSupport to SpringfoxRouterConfigurationSupport
```
@Configuration
@ComponentScan
@EnableAutoConfiguration //only use spring-boot need
public class WebAppConfig extends SpringfoxRouterConfigurationSupport {
	private final static Logger logger = LoggerFactory.getLogger(WebAppConfig.class);
	@Override
	public List<String> listRouteFiles() {
		List<String> routeFiles = new ArrayList<String>();
		routeFiles.add("classpath:router.conf");
		return routeFiles;
	}
}
```

### 2.xml config

#### 2.1 config pom.xml

```
<dependency>
	<groupId>commons-logging</groupId>
	<artifactId>commons-logging</artifactId>
	<version>1.2</version>
</dependency>
<dependency>
	<groupId>org.springframework</groupId>
	<artifactId>spring-webmvc</artifactId>
</dependency>
<dependency>
	<groupId>com.fasterxml.jackson.core</groupId>
	<artifactId>jackson-databind</artifactId>
</dependency>
<dependency>
	<groupId>org.resthub</groupId>
	<artifactId>springmvc-router</artifactId>
	<version>${router.version}</version>
</dependency>
<dependency>
	<groupId>io.springfox</groupId>
	<artifactId>springfox-swagger-ui</artifactId>
	<version>${springfox.version}</version>
</dependency>
<dependency>
	<groupId>io.springfox</groupId>
	<artifactId>springfox-swagger2</artifactId>
	<version>${springfox.version}</version>
</dependency>
<dependency>
	<groupId>io.github.synfirecloud</groupId>
	<artifactId>springfox-router-plugin-core</artifactId>
	<version>1.0</version>
</dependency>
```

#### 2.2 config bean

```
<bean id="handlerMapping" class="org.resthub.web.springmvc.router.RouterHandlerMapping">
	<property name="routeFiles">
		<list>
			<value>classpath:router.conf</value>
		</list>
	</property>
</bean>

<mvc:resources mapping="swagger-ui.html" location="classpath:/META-INF/resources/"/>
<mvc:resources mapping="/webjars/**" location="classpath:/META-INF/resources/webjars/"/>


<bean name="applicationSwaggerConfig" class="io.github.synfirecloud.srpd.ApplicationSwaggerConfig"/>
<bean name="springRouterRequestHandlerProvider" class="io.github.synfirecloud.srp.SpringRouterRequestHandlerProvider">
	<constructor-arg ref="handlerMapping" />
</bean>
```

### 3.run your application

`spring-boot:run` or `jetty:run`

## Reference Dome

`springfox-router-plugin-demo-javaconfig` or `springfox-router-plugin-demo-xml`