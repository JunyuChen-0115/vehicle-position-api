package org.jychen.vehicle.position.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.core.mapping.CassandraMappingContext;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@Configuration
@EnableCassandraRepositories(basePackages = "org.jychen.vehicle.position.api.repository")
public class CassandraConfig extends AbstractCassandraConfiguration {

    @Value("${spring.cassandra.keyspace}")
    private String keyspaceName;

    @Value("${spring.cassandra.contactpoints}")
    private String contactPoints;

    @Value("${spring.cassandra.port}")
    private int port;

    @Override
    protected String getKeyspaceName() {
        return keyspaceName;
    }

    @Bean
    public CassandraClusterFactoryBean cluster() {
        CassandraClusterFactoryBean cluster =
                new CassandraClusterFactoryBean();
        cluster.setContactPoints(contactPoints);
        cluster.setPort(port);
        cluster.setJmxReportingEnabled(false);
        return cluster;
    }

    @Bean
    public CassandraMappingContext cassandraMapping() {
        return new CassandraMappingContext();
    }
}
