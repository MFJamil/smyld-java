package org.smyld.app.spe;

import org.smyld.app.spe.service.PEManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * Java class SPortalEngineApplication
 *
 *   Main Spring as well as the JVM application
 * @author Mohammed Jamil
 * @version 1.0, 02 Jun 2019
 */
@SpringBootApplication
public class SPortalEngineApplication implements ApplicationRunner {


	@Autowired
	public PEManagerService peManagerService;

	private static SPortalEngineApplication instance = null ;

	public SPortalEngineApplication(){
		instance = this;
	}

	public static SPortalEngineApplication getInstance(){
		if (instance == null)
			new SPortalEngineApplication();
		return instance;
	}

	public static void main(String[] args) {
		// Below code is to suspend the population of Spring banner to speed up the start up time
		SpringApplication app = new SpringApplication(SPortalEngineApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {

		peManagerService.processCommandLine(args);
	}
}
