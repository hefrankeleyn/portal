package com.hef.qhjiaoyiyuan.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.InstantTypeHandler;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @Date 2020/3/15
 * @Author lifei
 */
@Configuration
@MapperScan(basePackages = {"com.hef.qhjiaoyiyuan.dao"}, sqlSessionFactoryRef = "sqlSessionFactory")
public class MyBatisConfig {

    private DataSource dataSource;

    @Autowired
    public void setDataSource(@Qualifier("dataSource") DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        TypeHandler typeHandler = new InstantTypeHandler();
        TypeHandler<?>[] typeHandlers = new TypeHandler[1];
        typeHandlers[0] = typeHandler;
        sqlSessionFactoryBean.setTypeHandlers(typeHandlers);
        return sqlSessionFactoryBean.getObject();
    }
}
