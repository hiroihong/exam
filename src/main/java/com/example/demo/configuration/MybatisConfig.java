package com.example.demo.configuration;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.example.demo.mvc.repository")
public class MybatisConfig {
	
	//@Autowired DataSourceConfig dataSourceConfig;

	@Bean
	public SqlSessionFactory sqlSessionFactory(@Autowired DataSource dataSource, ApplicationContext applicationConextext) throws Exception {
		
		//팩토리 생성
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(dataSource);
		factoryBean.setMapperLocations(applicationConextext.getResources("classpath:mybatis/sql/*.xml"));
		
		//생성된 팩토리에서 설정 추가
		SqlSessionFactory factory = factoryBean.getObject();
		//데이터가 바인딩 될때 카멜표기법 인식
		factory.getConfiguration().setMapUnderscoreToCamelCase(true);
		
		return factoryBean.getObject();
	} 
	
	@Bean
	public SqlSessionTemplate sqlSessionTemplate(@Autowired SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
}
