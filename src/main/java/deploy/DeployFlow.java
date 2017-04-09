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
	 * helloWord �����̶��岿��
	 * description:��helloWord���̶��岿�����ݿ�
	 */
	@Test
	public void deployHelloWord() {
		
		RepositoryService repositoryService = processEngine.getRepositoryService(); // �����ֿ�������

		DeploymentBuilder deploymentBuilder = repositoryService.createDeployment(); // �õ�����������
		deploymentBuilder.name("helloWord���ų���");
		deploymentBuilder.addClasspathResource("diagrams/HelloWorld.bpmn");
		deploymentBuilder.addClasspathResource("diagrams/HelloWorld.png");
		Deployment deployment = deploymentBuilder.deploy();
		System.out.println(deployment.getId());
		System.out.println(deployment.getName());

	}
	
	
	
	/**
	 * ����helloWord��������
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
	 * ��ѯ��ǰ�˵�����
	 */
	
	@Test
	public void findPersonTask(){
		String engine="���ž���";
		TaskService taskService= processEngine.getTaskService();
		TaskQuery  taskQuery= taskService.createTaskQuery();
		taskQuery.taskAssignee(engine);
		
		List<Task> Tasklist = taskQuery.list();
		for(Task list:Tasklist){
			System.out.println("1��������ID:"+list.getId()+" ");
			System.out.println("2������������:"+list.getName()+" ");
			System.out.println("3�������񴴽�ʱ��:"+list.getCreateTime()+" ");
			System.out.println("4�������������:"+list.getAssignee()+" ");
			System.out.println("5����ʵ��ID:"+list.getProcessInstanceId()+" ");
			System.out.println("6ִ�ж���ID:"+list.getExecutionId()+" ");
			System.out.println("7���̶���ID:"+list.getProcessDefinitionId()+" ");
		}
	}
			/**
			 * ��ɴ���������
			 */
			@Test
		public  void completeTask(){
			String taskID="5008";
			TaskService taskService = processEngine.getTaskService();
			taskService.complete(taskID);
			System.out.println("�ɳɴ�������ID"+taskID);
		}
			
			
			/**
			 * ��ѯ��ɾ������
			 * description:��ѯ���̶���
			 */
}
