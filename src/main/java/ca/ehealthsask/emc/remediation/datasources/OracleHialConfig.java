package ca.ehealthsask.emc.remediation.datasources;

import lombok.Data;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
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
@EnableConfigurationProperties
@Configuration
@ConfigurationProperties(prefix = "oracle.hial.datasource")
@EnableTransactionManagement
@EnableJpaRepositories(
        transactionManagerRef = "hialTransactionManager",
        entityManagerFactoryRef = "hialEntityManagerFactory",
        basePackages = {"ca.ehealthsask.emc.remediation.datasources.hial.repo"}
)
public class OracleHialConfig {
    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private String url;

    @Bean(name = "datasource")
    @Primary
    DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "hialEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("datasource") DataSource dataSource
    ) {
        return builder
                .dataSource(dataSource)
                .packages("ca.ehealthsask.emc.datasources.hial.domain")
                .persistenceUnit("hialapp")
                .build();
    }

    @Bean(name = "hialTransactionManager")
    public PlatformTransactionManager barTransactionManager(
            @Qualifier("hialEntityManagerFactory") EntityManagerFactory entityManagerFactory
    ) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
