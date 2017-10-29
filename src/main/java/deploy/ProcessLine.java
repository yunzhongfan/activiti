package deploy;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;


/**
 * 测试点
 * 流程变量的操作：设置流程变量、获取流程变量
 * 流程连线
 * @author Administrator
 *
 */
public class ProcessLine {
	ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
	
	@Test
	public void deployHelloWord() {
		
		RepositoryService repositoryService = processEngine.getRepositoryService(); // 创建仓库服务对象

		DeploymentBuilder deploymentBuilder = repositoryService.createDeployment(); // 得到发布构造器
		deploymentBuilder.name("流程连线");
		deploymentBuilder.addClasspathResource("diagrams/processConnectLine.bpmn");
		deploymentBuilder.addClasspathResource("diagrams/processConnectLine.png");
		Deployment deployment = deploymentBuilder.deploy();
		System.out.println(deployment.getId());
		System.out.println(deployment.getName());

	}
	
	/**
	 * 启动helloWord定义流程
	 */
	@Test
	public  void runFlowHelloWord(){
		RuntimeService runtimeService = processEngine.getRuntimeService();
		ProcessInstance  processInstance = runtimeService.startProcessInstanceByKey("processConnectLine");
		System.out.println(processInstance.getId());
		System.out.println(processInstance.getName());
		
		RepositoryService  repositoryService = processEngine.getRepositoryService();
		ProcessDefinition processDefinition = repositoryService.getProcessDefinition(processInstance.getDeploymentId());
		System.out.println(processDefinition.getId());
		System.out.println(processDefinition.getName());
	}

}
