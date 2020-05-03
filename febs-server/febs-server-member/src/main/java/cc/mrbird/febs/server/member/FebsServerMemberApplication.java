package cc.mrbird.febs.server.member;

import cc.mrbird.febs.common.security.starter.annotation.EnableFebsCloudResourceServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableAsync
@SpringBootApplication
@EnableFebsCloudResourceServer
@EnableTransactionManagement
@MapperScan("cc.mrbird.febs.server.member.mapper")
public class FebsServerMemberApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(FebsServerMemberApplication.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }
}
