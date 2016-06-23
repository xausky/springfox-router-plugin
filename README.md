# springfox-router-plugin

> springmvc-router use to springfox plugin

## How to use

### 1.Config pom.xml:

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

### 2.config WebAppConfig

change Springmvc-router WebAppConfig extends RouterConfigurationSupport to SpringfoxRouterConfigurationSupport

### 3.run your application

spring-boot:run or jetty:run

## Reference Dome

`springfox-router-plugin-demo`