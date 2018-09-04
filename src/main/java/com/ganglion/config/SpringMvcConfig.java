package com.ganglion.config;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import tk.mybatis.spring.annotation.MapperScan;

import javax.servlet.MultipartConfigElement;
import javax.sql.DataSource;


@Configuration
@EnableWebMvc
@EnableAsync
@EnableScheduling
@EnableTransactionManagement(proxyTargetClass=true)
@MapperScan({"com.ganglion.mapper"})
public class SpringMvcConfig{

    @Value("${spring.datasource.url}")
    private String dataSourceURL;
    @Value("${spring.datasource.username}")
    private String dataSourceUser;
    @Value("${spring.datasource.password}")
    private String dataSourcePassword;

    /**
     * 文件上传配置
     * @return
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //单个文件最大
        factory.setMaxFileSize("10240KB"); //KB,MB
        /// 设置总上传数据总大小
        factory.setMaxRequestSize("102400KB");
        return factory.createMultipartConfig();
    }

    @Bean(name = "dataSource")
    public DataSource dataSource() {
        // 使用Sql Server的DataSource
        SQLServerDataSource dataSource = new SQLServerDataSource();
        dataSource.setURL(dataSourceURL);
        dataSource.setUser(dataSourceUser);
        dataSource.setPassword(dataSourcePassword);
        return dataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception{
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource());
        // 设置mybatis-config的路径
        sqlSessionFactory.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
        // 获取当前package并切换到model的路径
        sqlSessionFactory.setTypeAliasesPackage("com.ganglion.model");

        return sqlSessionFactory.getObject();
    }

}