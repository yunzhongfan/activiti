package deploy;

import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.junit.Test;


/**
 * 测试点
 * 流程变量的操作：设置流程变量、获取流程变量
 * 流程连线
 * @author Administrator
 *
 */
public class ProcessLine extends DeployandDefindProcess {
	ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
	
	@Test
	public void deployHelloWord() {
		super.deployHelloWord("流程连线", "diagrams/processConnectLine.bpmn", "diagrams/processConnectLine.png");

	}
	
	/**
	 * 启动helloWord定义流程
	 */
	@Test
	public  void runFlowHelloWord(){
		super.runFlowHelloWord("processConnectLine");
	}
	
	
	/*
	 * 流程参数设置
	 * 通过takeServer或者RunTimeServer
	 */
	@Test
	public void setProcessParam(){
		try {
			String processParam="同意";
			
			TaskService taskService = super.processEngine.getTaskService();
			TaskQuery taskQuery = taskService.createTaskQuery();
			taskQuery.taskAssignee("departmentLeader");
			List<Task> taskList = taskQuery.list();
			if((!taskList.isEmpty())&&taskList.size()>0){
				String taskId = taskList.get(0).getId();
				System.out.println(taskId);
				taskService.setVariable(taskId, "message", processParam);
				//完成该任务ID
				super.completeTask(taskId);
				//上面的这两部可以用流程连线来完成效果一样
			//	taskService.complete(taskId, (Map<String, Object>) new HashMap().put("message", processParam));;
		
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//完成任务ID
		
		
		
	
		
		
	}
	

}
