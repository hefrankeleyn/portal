package com.hef.qhjiaoyiyuan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 这个类有两个作用： 配置和启动引导
 * {@code @SpringBootApplication} 是为启动组件扫描和自动配置
 */
@SpringBootApplication
@EnableTransactionManagement
public class QHTraderApplication {

	public static void main(String[] args) {
		/** 这行代码是负责启动引导应用程序 */
		SpringApplication.run(QHTraderApplication.class, args);
	}

}
