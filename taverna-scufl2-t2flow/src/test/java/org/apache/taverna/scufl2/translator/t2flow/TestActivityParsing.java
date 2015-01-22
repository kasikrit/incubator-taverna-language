package org.apache.taverna.scufl2.translator.t2flow;

import static org.junit.Assert.assertNotNull;

import java.net.URL;

import org.apache.taverna.scufl2.api.common.NamedSet;
import org.apache.taverna.scufl2.api.common.Scufl2Tools;
import org.apache.taverna.scufl2.api.configurations.Configuration;
import org.apache.taverna.scufl2.api.container.WorkflowBundle;
import org.apache.taverna.scufl2.translator.t2flow.T2FlowParser;
import org.junit.Test;


@SuppressWarnings("unused")
public class TestActivityParsing {

    private static final String WF_ALL_ACTIVITIES = "/defaultActivitiesTaverna2.2.t2flow";
	private static final String WF_AS = "/as.t2flow";
	private static Scufl2Tools scufl2Tools = new Scufl2Tools();

    @Test
    public void readSimpleWorkflow() throws Exception {
        URL wfResource = getClass().getResource(WF_ALL_ACTIVITIES);
        assertNotNull("Could not find workflow " + WF_ALL_ACTIVITIES,
                wfResource);
        T2FlowParser parser = new T2FlowParser();
        parser.setValidating(true);
        // parser.setStrict(true);
        WorkflowBundle wfBundle = parser
                .parseT2Flow(wfResource.openStream());
		NamedSet<Configuration> cfgs = wfBundle.getProfiles().iterator().next()
				.getConfigurations();
        // System.out.println(cfgs);

    }
    
}