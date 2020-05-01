package cc.mrbird.febs.server.member;

import cc.mrbird.febs.common.annotation.FebsCloudApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableAsync
@SpringBootApplication
@FebsCloudApplication
@EnableTransactionManagement
@EnableGlobalMethodSecurity(prePostEnabled = true)
@MapperScan("cc.mrbird.febs.server.member.mapper")
public class FebsServerMemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(FebsServerMemberApplication.class, args);
    }
}