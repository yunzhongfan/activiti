package deploy;

import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.junit.Test;

public class DeployFlow {

	ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
	
	
	/**
	 * helloWord ，流程定义部署
	 * description:将helloWord流程定义部署到数据库
	 */
	@Test
	public void deployHelloWord() {
		
		RepositoryService repositoryService = processEngine.getRepositoryService(); // 创建仓库服务对象

		DeploymentBuilder deploymentBuilder = repositoryService.createDeployment(); // 得到发布构造器
		deploymentBuilder.name("helloWord入门程序");
		deploymentBuilder.addClasspathResource("diagrams/HelloWorld.bpmn");
		deploymentBuilder.addClasspathResource("diagrams/HelloWorld.png");
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
		ProcessInstance  processInstance = runtimeService.startProcessInstanceByKey("companyLeaderkey");
		System.out.println(processInstance.getId());
		System.out.println(processInstance.getName());
		
		RepositoryService  repositoryService = processEngine.getRepositoryService();
		ProcessDefinition processDefinition = repositoryService.getProcessDefinition(processInstance.getDeploymentId());
		System.out.println(processDefinition.getId());
		System.out.println(processDefinition.getName());
	}
	
	
	/**
	 * 查询当前人的任务
	 */
	
	@Test
	public void findPersonTask(){
		String engine="部门经理";
		TaskService taskService= processEngine.getTaskService();
		TaskQuery  taskQuery= taskService.createTaskQuery();
		taskQuery.taskAssignee(engine);
		
		List<Task> Tasklist = taskQuery.list();
		for(Task list:Tasklist){
			System.out.println("1代办任务ID:"+list.getId()+" ");
			System.out.println("2代办任务名称:"+list.getName()+" ");
			System.out.println("3代办任务创建时间:"+list.getCreateTime()+" ");
			System.out.println("4代办任务办理人:"+list.getAssignee()+" ");
			System.out.println("5流程实例ID:"+list.getProcessInstanceId()+" ");
			System.out.println("6执行对象ID:"+list.getExecutionId()+" ");
			System.out.println("7流程定义ID:"+list.getProcessDefinitionId()+" ");
		}
	}
			/**
			 * 完成代办理任务
			 */
			@Test
		public  void completeTask(){
			String taskID="5008";
			TaskService taskService = processEngine.getTaskService();
			taskService.complete(taskID);
			System.out.println("成成创建任务ID"+taskID);
		}
			
			
			/**
			 * 查询和删除流程
			 * description:查询流程定义
			 */
}
