package deploy;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;

public class DeployandDefindProcess {
	protected ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
	
	
	public void deployHelloWord(String deploymentNane,String classpathResourcesBpmn,String ClasspathResourcePng) {
		
		RepositoryService repositoryService = processEngine.getRepositoryService(); // 创建仓库服务对象

		DeploymentBuilder deploymentBuilder = repositoryService.createDeployment(); // 得到发布构造器
		deploymentBuilder.name(deploymentNane);
		deploymentBuilder.addClasspathResource(classpathResourcesBpmn);
		deploymentBuilder.addClasspathResource(ClasspathResourcePng);
		Deployment deployment = deploymentBuilder.deploy();
		System.out.println(deployment.getId());
		System.out.println(deployment.getName());

	}
	
	/**
	 * 启动helloWord定义流程
	 */
	
	public  void runFlowHelloWord(String InstanceByKey){
		RuntimeService runtimeService = processEngine.getRuntimeService();
		ProcessInstance  processInstance = runtimeService.startProcessInstanceByKey(InstanceByKey);
		System.out.println(processInstance.getId());
		System.out.println(processInstance.getName());
		
		RepositoryService  repositoryService = processEngine.getRepositoryService();
		ProcessDefinition processDefinition = repositoryService.getProcessDefinition(processInstance.getDeploymentId());
		System.out.println(processDefinition.getId());
		System.out.println(processDefinition.getName());
	}
	
	
	/**
	 * 完成流程
	 */
	public  void completeTask(String taskID){
		//String taskID="20004";
		TaskService taskService = processEngine.getTaskService();
		taskService.complete(taskID);
		System.out.println("成成创建任务ID"+taskID);
	}

}
