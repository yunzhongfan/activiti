package utils;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.junit.Test;

public class CreateTable {
	
	/****
	 * �������̱�
	 * */
	@Test
	public void createTable() {
		ProcessEngine processEngine = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml")
				.buildProcessEngine();
		
		System.out.println("------processEngine:" + processEngine); 
	}


}
