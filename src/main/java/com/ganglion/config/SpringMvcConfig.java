package com.ganglion.config;

import com.ganglion.converter.MyMessageConverter;
import com.ganglion.Interceptor.DemoInterceptor;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.MultipartConfigElement;
import javax.sql.DataSource;
import java.util.List;


@Configuration
@EnableWebMvc
@EnableAsync
@EnableScheduling
@EnableTransactionManagement(proxyTargetClass=true)
@ComponentScan({"com.ganglion.*"})
public class SpringMvcConfig implements WebMvcConfigurer {

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

    /**
     * 配置拦截器的Bean
     */
    @Bean
    public DemoInterceptor demoInterceptor(){
        return new DemoInterceptor();
    }
    /**
     * 重写addInterceptor，注册拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(demoInterceptor());
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("/index");
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(converter());
    }
    @Bean
    public MyMessageConverter converter(){
        return new MyMessageConverter();
    }
}