package cn.fengylb.mycommunity.mycommunity;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.fengylb.mycommunity.mycommunity.mapper")
public class MycommunityApplication {

    public static void main(String[] args) {
        SpringApplication.run(MycommunityApplication.class, args);
    }

}
