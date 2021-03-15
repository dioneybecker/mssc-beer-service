package com.dioneybecker.msscbeerservice.config;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;

import com.netflix.appinfo.AmazonInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.commons.util.InetUtils;
import org.springframework.cloud.netflix.eureka.EurekaInstanceConfigBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import lombok.extern.slf4j.Slf4j;


@Profile("awsdev")
@Slf4j
@EnableDiscoveryClient
@Configuration
public class ServiceDiscovery {

    @Bean
    @Profile("awsdev")
    public EurekaInstanceConfigBean eurekaInstanceConfigBean(InetUtils inetUtils) {
        EurekaInstanceConfigBean config = new EurekaInstanceConfigBean(inetUtils);
        AmazonInfo info = AmazonInfo.Builder.newBuilder().autoBuild("eureka");

        log.info("Public Hostname: " +  info.get(AmazonInfo.MetaDataKey.publicHostname));
        log.info("Public IPv4: " + info.get(AmazonInfo.MetaDataKey.publicIpv4));

        config.setHostname(info.get(AmazonInfo.MetaDataKey.publicHostname));
        config.setIpAddress(info.get(AmazonInfo.MetaDataKey.publicIpv4));
        config.setDataCenterInfo(info);
        return config;      
    }
    
}
