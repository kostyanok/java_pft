package ru.stqa.ptf.soap;

import biz.futureware.mantis.rpc.soap.client.MantisConnectLocator;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import biz.futureware.mantis.rpc.soap.client.ProjectData;
import org.testng.annotations.Test;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

public class SoapTests {
    @Test
    public void testGetProjects() throws RemoteException, MalformedURLException, ServiceException {
        MantisConnectPortType mc = new MantisConnectLocator()
                .getMantisConnectPort(new URL("http://localhost:8080/mantisbt-2.17.1/api/soap/mantisconnect.php"));
        ProjectData[] projects = mc.mc_projects_get_user_accessible("administrator", "root");
        System.out.println(projects.length);
        for(ProjectData projectData: projects){
            System.out.println(projectData.getName());
        }
    }
}
