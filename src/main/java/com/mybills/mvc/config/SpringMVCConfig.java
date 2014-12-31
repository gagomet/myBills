package com.mybills.mvc.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mybills.mvc.service.IBillUserService;
import com.mybills.mvc.service.impl.BillUserServiceImpl;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.beans.PropertyVetoException;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Created by Padonag on 28.12.2014.
 */
@EnableWebMvc
@Configuration
@ComponentScan("com.mybills.mvc")
@EnableJpaRepositories(basePackages = "com.mybills.mvc.repository", entityManagerFactoryRef = "entityManagerFactory")
@EnableTransactionManagement
@Import({ SpringSecurityConfig.class })
public class SpringMVCConfig {
    private ResourceBundle bundle = ResourceBundle.getBundle("database");

    @Bean(name = "datasource")
    public ComboPooledDataSource dataSource() {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        try {
            dataSource.setDriverClass(bundle.getString("db_driver"));
            dataSource.setJdbcUrl(bundle.getString("db_jdbc_url"));
            dataSource.setUser(bundle.getString("db_login"));
            dataSource.setPassword(bundle.getString("db_password"));
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        return dataSource;
    }

    @Bean
    public SessionFactory sessionFactory() {
        LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource());
        builder.scanPackages("com.mybills.mvc.domain").addProperties(getHibernateProperties());
        return builder.buildSessionFactory();
    }

    @Bean
    public HibernateTransactionManager txManager() {
        return new HibernateTransactionManager(sessionFactory());
    }

    @Bean
    public InternalResourceViewResolver configureInternalResourceViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/pages/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setDatabase(Database.MYSQL);
        jpaVendorAdapter.setGenerateDdl(true);
        jpaVendorAdapter.setShowSql(true);
        jpaVendorAdapter
                .setDatabasePlatform("org.hibernate.dialect.MySQL5Dialect");
        return jpaVendorAdapter;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(this.dataSource());
        emf.setJpaVendorAdapter(this.jpaVendorAdapter());
        emf.setPackagesToScan("com.mybills.mvc");
        emf.setJpaProperties(this.getHibernateProperties());
        return emf;
    }

    @Bean
    public IBillUserService billUserService() {
        return new BillUserServiceImpl();
    }


    private Properties getHibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.format_sql", "true");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.put("hibernate.jdbc.batch_size", 10);
        properties.put("hibernate.jdbc.fetch_size", 50);
        properties.put("hibernate.max_fetch_depth", 3);
        return properties;
    }


}
