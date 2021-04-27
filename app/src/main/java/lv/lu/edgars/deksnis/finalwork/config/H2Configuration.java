package lv.lu.edgars.deksnis.finalwork.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;


@Configuration
@PropertySource("classpath:application.properties")
public class H2Configuration {


    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .setName("FINAL_WORK")
                .build();
    }

    @Bean
    public Properties hibernateProperties(
        @Value("${hibernate.show.sql}") Boolean showSql,
        @Value("${hibernate.hbm2ddl.auto}") String hbm2ddl,
        @Value("${hibernate.dialect}") String dialect)

    {
        Properties properties = new Properties();
        properties.put("hibernate.show.sql", showSql);
        properties.put("hibernate.hbm2ddl.auto", hbm2ddl);
        properties.put("hibernate.dialect", dialect);
        return properties;
    }
    @Bean
    public SessionFactory sessionFactory(DataSource dataSource, Properties properties)
    {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setHibernateProperties(properties);
        return sessionFactory(dataSource,properties);
    }

}
