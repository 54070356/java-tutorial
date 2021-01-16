package com.laofuzi.spring.service1.consul;

import java.util.Date;

import reactor.core.publisher.Flux; 
import reactor.core.scheduler.Schedulers;

public class FluxTest {
	public static void main(String[] args) {
		Flux<Date> m1 = Flux.just(new Date());
		Flux<Date> m2 = Flux.defer(()->Flux.just(new Date())).subscribeOn(Schedulers.boundedElastic());
		m1.subscribe(System.out::println);
		m2.subscribe(System.out::println);
        //延迟5秒钟
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		m1.subscribe(System.out::println);
		m2.subscribe(System.out::println);
		m2.take(1);
		
	}

}
