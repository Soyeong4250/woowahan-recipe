package com.woowahan.recipe.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Slf4j
@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {

    @Bean(name = "asyncExecutor")
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        int processorCnt = Runtime.getRuntime().availableProcessors();  // 현재 시스템의 사용가능한 프로세스 개수
        log.info("processorCnt : {}", processorCnt);  // 애플리케이션 실행할 때가 아닌 필요 시에 생성
        executor.setCorePoolSize(processorCnt);  // 프로세스 개수만큼 Core 개수 설정 (현재 미리 만들어놓은 프로세스 개수)
        executor.setMaxPoolSize(processorCnt * 2);  // CPU, 메모리에 따라 달라짐 (51번부터는 Core를 새로 만들어 Queue의 맨 앞에 있던 프로세스부터 제공하는데 이는 processorCnt의 2배까지)
        executor.setQueueCapacity(50);  // CPU, 메모리에 따라 달라짐 (현재 모든 Core가 사용중이라면 50번까지 줄을 세움)
        executor.setKeepAliveSeconds(60);  // Queue의 크기 이상만큼 만들어졌던 Core들을 얼만큼 살려놓았다가 정리할 것인지
        executor.setThreadNamePrefix("AsyncExecutor");  // 이름을 주어 로깅 시 더 편리하게 사용
        executor.initialize();  // pool 초기화
        return executor;
    }
}
