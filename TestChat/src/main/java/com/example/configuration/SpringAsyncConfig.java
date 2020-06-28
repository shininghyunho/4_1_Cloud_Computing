package com.example.configuration;

import com.example.controller.MainController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;

@Configuration
@EnableAsync
public class SpringAsyncConfig {
    //선언부
    final Logger log = LoggerFactory.getLogger(MainController.class);

    @Bean(name = "threadPoolTaskExecutor", destroyMethod = "destroy")
    public Executor threadPoolTaskExecutor() {
        log.info("thread 설정합니다.");
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        // 1) 스레드 풀을 해당 개수까지 기본적으로 생성함. 처음 요청이 들어올 때 poll size만큼 생성한다.
        taskExecutor.setCorePoolSize(3);
        // 2) 지금 당장은 Core 스레드를 모두 사용중일때, 큐에 만들어 대기시킨다.
        taskExecutor.setQueueCapacity(10);
        // 3) 대기하는 작업이 큐에 꽉 찰 경우, 풀을 해당 개수까지 더 생성한다.
        taskExecutor.setMaxPoolSize(30);
        // 스레드 이름 접미사
        taskExecutor.setThreadNamePrefix("Executor-");
        taskExecutor.initialize();
        return new HandlingExecutor(taskExecutor); // HandlingExecutor로 wrapping 합니다.
    }

    public class HandlingExecutor implements AsyncTaskExecutor {
        private AsyncTaskExecutor executor;

        public HandlingExecutor(AsyncTaskExecutor executor) {
            this.executor = executor;
        }

        @Override
        public void execute(Runnable task) {
            executor.execute(createWrappedRunnable(task));
        }

        @Override
        public void execute(Runnable task, long startTimeout) {
            executor.execute(createWrappedRunnable(task), startTimeout);
        }

        @Override
        public Future<?> submit(Runnable task) {
            return executor.submit(createWrappedRunnable(task));
        }

        @Override
        public <T> Future<T> submit(final Callable<T> task) {
            return executor.submit(createCallable(task));
        }

        private <T> Callable<T> createCallable(final Callable<T> task) {
            return new Callable<T>() {
                @Override
                public T call() throws Exception {
                    try {
                        return task.call();
                    } catch (Exception ex) {
                        handle(ex);
                        throw ex;
                    }
                }
            };
        }

        private Runnable createWrappedRunnable(final Runnable task) {
            return new Runnable() {
                @Override
                public void run() {
                    try {
                        task.run();
                    } catch (Exception ex) {
                        handle(ex);
                    }
                }
            };
        }

        private void handle(Exception ex) {
            log.info("Failed to execute task. : {}", ex.getMessage());
            log.error("Failed to execute task. ",ex);
        }

        public void destroy() {
            if(executor instanceof ThreadPoolTaskExecutor){
                ((ThreadPoolTaskExecutor) executor).shutdown();
            }
        }
    }

}