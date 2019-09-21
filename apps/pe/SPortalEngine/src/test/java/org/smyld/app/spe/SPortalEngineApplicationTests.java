package org.smyld.app.spe;

import org.smyld.app.spe.factory.SPEFactory;
import org.smyld.app.spe.service.PEManagerService;
import org.smyld.app.spe.util.PEConstants;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.lang.NonNull;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Nonnull;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SPortalEngineApplicationTests {

	@Test
	public void contextLoads() {

	}

	@Test
	public void testCommandLine(){
		String[] verArgs= {"--version"};
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		System.setOut(new PrintStream(baos));
		SPortalEngineApplication.main(verArgs);
		Assert.assertTrue("Did not handle the version argument", baos.toString().contains(PEConstants.PE_VERSION));
		String[] appArgs= {"--app=src/test/resources/settings/xml/Application.xml"};
		SPortalEngineApplication.main(appArgs);

		Assert.assertTrue("Did not recognize the app argument", baos.toString().contains(PEManagerService.LOG_APP_RECIEVE_MESSAGE));
	}

	@Test
	public void testFactory(){
		SPEFactory factory = new SPEFactory();
		Assert.assertTrue(factory.getGuiBuilders().size()>0);
		Assert.assertTrue(factory.getLayoutHandlers().size()>0);

	}


	@Test
	public void testSettingsToolkitDetection(){
		String[] appArgs= {"--app=src/test/resources/settings/xml/Application.xml"};
		SPortalEngineApplication.main(appArgs);
		System.out.println( " Created toolkit is: " + 	SPortalEngineApplication.getInstance().peManagerService.getPeGUIBuilder().getAnnotatedClass().getName());

	}


}
