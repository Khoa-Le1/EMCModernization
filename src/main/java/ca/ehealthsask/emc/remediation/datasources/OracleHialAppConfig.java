package ca.ehealthsask.emc.remediation.datasources;

import lombok.Data;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import javax.validation.constraints.NotNull;

@Data
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "oracle.hialapp.datasource")
@EnableTransactionManagement
@EnableJpaRepositories(
        transactionManagerRef = "hialappTransactionManager",
        entityManagerFactoryRef = "hialappEntityManagerFactory",
        basePackages = {"ca.ehealthsask.emc.remediation.datasources.hialapp.repo"}
)
public class OracleHialAppConfig {
    @NotNull
    private String url;

    @NotNull
    private String username;

    @NotNull
    private String password;

    @Primary
    @Bean(name = "datasource")
    DataSource dataSource() {
        return DataSourceBuilder.create().build();
//        OracleDataSource dataSource = new OracleDataSource();
//        dataSource.setUser(username);
//        dataSource.setPassword(password);
//        dataSource.setURL(url);
//        dataSource.setImplicitCachingEnabled(true);
//        dataSource.setFastConnectionFailoverEnabled(true);
//        return dataSource;
    }

    @Primary
    @Bean(name = "hialappEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("datasource") DataSource dataSource
    ) {
        return builder
                .dataSource(dataSource)
                .packages("ca.ehealthsask.emc.datasources.hialapp.domain")
                .persistenceUnit("hialapp")
                .build();
    }

    @Bean(name = "hialappTransactionManager")
    public PlatformTransactionManager barTransactionManager(
            @Qualifier("hialappEntityManagerFactory") EntityManagerFactory entityManagerFactory
    ) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
