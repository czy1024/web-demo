package com.luna;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;


/**
 * @SpringBootApplication(scanBasePackages = "com.luna")
 * <p>
 * SpringBootApplication 扫描子包和同级包
 * 1. 开启注解缓存 EnableCaching 2. 开启RabbitMq+RabbitListener
 * 2. 注解缓存方法
 * TODO 实际开发大都使用缓存中间件 redis 等..
 * 1. 引入starter
 * 2. 配置主机地址
 * 3. 测试缓存
 */
//开启缓存
@EnableCaching
//开启消息中间件
@EnableRabbit
//开启异步功能
@EnableAsync
//开启定时任务功能
@EnableScheduling
@SpringBootApplication
public class WebApplication {

	static{
		System.out.println("启动加载属性--start");
		System.setProperty("es.set.netty.runtime.available.processors", "false");
	}

	public static void main(String[] args) {
		/**
		 * Springboot整合Elasticsearch 在项目启动前设置一下的属性，防止报错
		 * 解决netty冲突后初始化client时还会抛出异常
		 * java.lang.IllegalStateException: availableProcessors is already set to [8], rejecting [8]
		 */
		System.setProperty("es.set.netty.runtime.available.processors", "false");
		SpringApplication.run(WebApplication.class, args);
	}

}
