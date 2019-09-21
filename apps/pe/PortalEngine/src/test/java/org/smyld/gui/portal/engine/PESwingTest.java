package org.smyld.gui.portal.engine;

import org.smyld.gui.portal.engine.sources.PortalAppXMLSetReaderPESwing;
import org.smyld.sys.SMYLDSystem;
import org.junit.Test;
import org.junit.Assert;


public class PESwingTest {


    @Test
    public void testParser()throws Exception{
        PortalAppXMLSetReaderPESwing testXML = new PortalAppXMLSetReaderPESwing("src/test/resources/Application.xml",null);
        Assert.assertEquals("Incorrect windows number",5,testXML.loadWindows().size());
        Assert.assertEquals("Incorrect  menu number",2,testXML.loadMenus().size());
    }


    //@Test
    public void testBillingTool()throws Exception{
        String argsList = "";
        switch (SMYLDSystem.getOperatingSystemGroup()){
            case Windows:
                argsList = "-var:smyld_main C:\\development\\java\\projects\\main -var:smyld_apps C:\\development\\java\\projects\\apps -var:ext_lib C:\\development\\java\\tools\\lib";
                break;
            case Linux:
                argsList = "-var:smyld_main /home/mfjamil/development/java/projects/main -var:smyld_apps /home/mfjamil/development/java/projects/apps -var:ext_lib /home/mfjamil/development/java/tools/lib";
                break;
            default:
                throw new Exception("Unsupported OS");
        }
        PortalManager manager = new PortalManager("src/test/resources/billingTool/Three60t_Billing_Interface.xml",argsList.split(" "));
    }



}
