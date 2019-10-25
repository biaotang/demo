package com.macrowing.mybatis.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DruidConfig {

    @Value("${spring.druidDatasource.url}")
    private String dbUrl;

    @Value("${spring.druidDatasource.username}")
    private String username;

    @Value("${spring.druidDatasource.password}")
    private String password;

    @Value("${spring.druidDatasource.driver-class-name}")
    private String driverClassName;

    @Value("${spring.druidDatasource.initialSize}")
    private int initialSize;

    @Value("${spring.druidDatasource.minIdle}")
    private int minIdle;

    @Value("${spring.druidDatasource.maxActive}")
    private int maxActive;

    @Value("${spring.druidDatasource.maxWait}")
    private int maxWait;

    @Value("${spring.druidDatasource.timeBetweenEvictionRunsMillis}")
    private int timeBetweenEvictionRunsMillis;

    @Value("${spring.druidDatasource.minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis;

    @Value("${spring.druidDatasource.validationQuery}")
    private String validationQuery;

    @Value("${spring.druidDatasource.testWhileIdle}")
    private boolean testWhileIdle;

    @Value("${spring.druidDatasource.testOnBorrow}")
    private boolean testOnBorrow;

    @Value("${spring.druidDatasource.testOnReturn}")
    private boolean testOnReturn;

    @Value("${spring.druidDatasource.filters}")
    private String filters;

    @Value("${spring.druidDatasource.logSlowSql}")
    private String logSlowSql;

    @Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean reg = new ServletRegistrationBean();
        reg.setServlet(new StatViewServlet());
        reg.addUrlMappings("/druid/*");
        reg.addInitParameter("loginUsername", username);
        reg.addInitParameter("loginPassword", password);
        reg.addInitParameter("logSlowSql", logSlowSql);
        return reg;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        filterRegistrationBean.addInitParameter("profileEnable", "true");
        return filterRegistrationBean;
    }

    @Bean
    public DataSource druidDataSource() {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(dbUrl);
        datasource.setUsername(username);
        datasource.setPassword(password);
        datasource.setDriverClassName(driverClassName);
        datasource.setInitialSize(initialSize);
        datasource.setMinIdle(minIdle);
        datasource.setMaxActive(maxActive);
        datasource.setMaxWait(maxWait);
        datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
//        datasource.setValidationQuery(validationQuery);
        datasource.setTestWhileIdle(testWhileIdle);
        datasource.setTestOnBorrow(testOnBorrow);
        datasource.setTestOnReturn(testOnReturn);
        datasource.setLogAbandoned(false);
        datasource.setRemoveAbandoned(true);
        datasource.setPoolPreparedStatements(false);

//        try {
//            datasource.setFilters(filters);
//        } catch (SQLException e) {
//            logger.error("druid configuration initialization filter", e);
//        }
        return datasource;
    }

}
