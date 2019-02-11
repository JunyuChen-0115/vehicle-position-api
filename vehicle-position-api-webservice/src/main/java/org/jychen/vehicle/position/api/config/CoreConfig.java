package org.jychen.vehicle.position.api.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
        "org.jychen.vehicle.position.api.messaging",
        "org.jychen.vehicle.position.api.service",
        "org.jychen.vehicle.position.api.controller"})
public class CoreConfig {

}
