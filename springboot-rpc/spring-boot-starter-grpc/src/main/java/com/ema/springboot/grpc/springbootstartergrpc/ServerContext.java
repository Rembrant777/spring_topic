package com.ema.springboot.grpc.springbootstartergrpc;

import io.grpc.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
public class ServerContext {
	private Channel channel;

}