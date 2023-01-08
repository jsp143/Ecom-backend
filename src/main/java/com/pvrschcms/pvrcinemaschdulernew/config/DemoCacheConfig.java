package com.pvrschcms.pvrcinemaschdulernew.config;

import org.springframework.context.annotation.Configuration;

import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
//import com.hazelcast.config.MaxSizeConfig;

@Configuration
public class DemoCacheConfig {

	public Config configure() {
		return new Config().setInstanceName("hazlecast-insatnce")
				.addMapConfig(new MapConfig().setName("userCache").setTimeToLiveSeconds(2000));
						//.setMaxSizeConfig(new MaxSizeConfig(200, MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE))
						//.setEvictionPolicy(EvictionPolicy.LRU).setTimeToLiveSeconds(2000));
	}
}
