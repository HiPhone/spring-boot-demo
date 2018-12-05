package org.hiphone.elasticsearch.config;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;

/**
 * @author HiPhone
 */
@Slf4j
@Configuration
public class EsConfig {

    @Value("${elasticsearch.ips}")
    private String hostIps;

    @Value("${elasticsearch.tcp.port}")
    private int port;

    @Value("${elasticsearch.cluster.name}")
    private String clusterName;

    @Value("${elasticsearch.pool-size}")
    private int poolSize;

    @Bean(name = "transportClient")
    public TransportClient transportClient() {
        TransportClient transportClient = null;

        //写入配置信息
        try {
            Settings esSetting = Settings.builder()
                    .put("cluster.name", clusterName)
                    .put("client.transport.sniff", true)
                    .put("thread_pool.search.size", poolSize)
                    .build();

            transportClient = new PreBuiltTransportClient(esSetting);

            String[] hostIpList = hostIps.split(",");
            for (String hostIp : hostIpList) {
                TransportAddress transportAddress = new TransportAddress(InetAddress.getByName(hostIp), port);
                transportClient.addTransportAddress(transportAddress);
            }
            log.info("Creating elasticsearch transportClient success");
        } catch (Exception e) {
            log.error("Creating elsastic search transportClient gets error!", e);
        }
        return transportClient;
    }
}
