package io.github.iweidujiang.lab12.demo.health;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 自定义健康检查指示器。
 *
 * @author 苏渡苇
 */
@Component
public class LabHealthIndicator extends AbstractHealthIndicator {

    /**
     * 自定义健康检查详情。
     *
     * @param builder Health.Builder
     * @throws Exception 检查异常
     */
    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        builder.up()
                .withDetail("status", "OK")
                .withDetail("author", "苏渡苇")
                .withDetail("uptime", new Date());
    }
}
