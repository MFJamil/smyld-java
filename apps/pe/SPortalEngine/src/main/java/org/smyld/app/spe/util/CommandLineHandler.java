package org.smyld.app.spe.util;

import org.smyld.app.spe.service.PEManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;



/**
 * Java class CommandLineHandler
 *
 *   Handling the Portal Engine command line arguments
 * @author Mohammed Jamil
 * @version 1.0, 02 Jun 2019
 */
@Component
public class CommandLineHandler {
    private static final Logger log = LoggerFactory.getLogger( CommandLineHandler.class );

    @Autowired
    private PEManagerService peManagerService;



    public void processCommandLine(ApplicationArguments args){

        if (args.getOptionNames().contains(CommandLineArgs.version.name())) {
            log.info(String.format("Portal Engine (%s)", PEConstants.PE_VERSION));
        }else if(args.getOptionNames().contains(CommandLineArgs.app.name())){
            peManagerService.processSingleApplication(args.getOptionValues(CommandLineArgs.app.name()).get(0),args);
        }else {
            for (String name : args.getOptionNames()) {
                log.info("arg-" + name + "=" + args.getOptionValues(name));
            }
        }
    }

    /**
     * Command Line Argument that lists all possible Portal Engine command line arguments
     */
    public enum CommandLineArgs{version,app,setting};

}
