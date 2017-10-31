package deploy;

import java.util.HashMap;

import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.junit.Test;

import com.sun.javafx.collections.MappingChange.Map;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

/**
 * 排他网关
 * @author Administrator
 *
 */
public class ExclusiveGateWayLine extends DeployandDefindProcess{
	
	
	//部署定义流程
	@Test
	public void deployHelloWord(){
		super.deployHelloWord("排他网关", "diagrams/ExclusiveGateWayLine.bpmn", "diagrams/ExclusiveGateWayLine.png");
	}
	
	
	//启动流程
	@Test
	public void runFlowHelloWord(){
		super.runFlowHelloWord("ExclusiveGateWayProcess");
	}
	
	/**
	 * 完成排他网关，完成推送
	 */
	@Test
	public void  completeTask(){
		String assignee ="职员";
		HashMap<String, Object> param = new HashMap<String,Object>();
		param.put("money", 700);
	
		TaskService taskService = super.processEngine.getTaskService();
		TaskQuery taskQuery = taskService.createTaskQuery();
		//设置查询的当前的assange
		taskQuery.taskAssignee(assignee);
		java.util.List<Task> list = taskQuery.list();
		if(list!=null&&list.size()>0){
			String taskID = list.get(0).getId();
			System.out.println("当前任务ID="+taskID);
			taskService.complete(taskID, param);
			System.out.println("完成任务ID="+taskID);
		}
		
		
	}
	
	
	

}
