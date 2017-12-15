package com.github.JiangGuangxing.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.interceptor.TransactionInterceptor;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.sql.DataSource;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author 姜广兴
 * @since 2017/11/1
 */
@Configuration
public class AppConfig extends WebMvcConfigurerAdapter {

    /**
     * 添加fastjson视图解析器
     *
     * @param converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonConfig config = new FastJsonConfig();
        config.setCharset(StandardCharsets.UTF_8);
        config.setDateFormat("yyyy-MM-dd HH:mm:ss");
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        config.setSerializerFeatures(SerializerFeature.PrettyFormat);
        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        converter.setSupportedMediaTypes(fastMediaTypes);
        converter.setFastJsonConfig(config);
        converters.add(converter);
    }

    /**
     * 事务拦截器，不用必须配置，可以使用@Transactional声明式事务
     *
     * @param transactionManager
     * @return
     */
    @Bean(name = "tsInterceptor")
    public TransactionAspectSupport transactionInterceptor(DataSourceTransactionManager transactionManager) {
        TransactionInterceptor transactionInterceptor = new TransactionInterceptor();
        transactionInterceptor.setTransactionManager(transactionManager);
        Properties attributes = new Properties();
        attributes.setProperty("*", "PROPAGATION_REQUIRED, -Exception");
        attributes.setProperty("get*", "PROPAGATION_REQUIRED,readOnly");//只读事务
        transactionInterceptor.setTransactionAttributes(attributes);
        return transactionInterceptor;
    }

    /**
     * 代理
     *
     * @return
     */
    @Bean
    public BeanNameAutoProxyCreator transactionAutoProxy() {
        BeanNameAutoProxyCreator autoProxy = new BeanNameAutoProxyCreator();
        autoProxy.setProxyTargetClass(true);// 这个属性为true时，表示被代理的是目标类本身而不是目标类的接口
        autoProxy.setBeanNames("*ServiceImpl");
        autoProxy.setInterceptorNames("tsInterceptor");
        return autoProxy;
    }

    private DataSourceTransactionManager transactionManager(DataSource dataSource) {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }


}
