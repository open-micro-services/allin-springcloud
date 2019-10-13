package com.boonya.springboot.activiti.sbactiviti.service;

import com.boonya.springboot.activiti.sbactiviti.utils.BaseUtils;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: ActivitiService
 * @Description: TODO(功能说明)
 * @author: pengjunlin
 * @motto: 学习需要毅力，那就秀毅力
 * @date 2019/10/13 22:35
 */
@Service
@Transactional
public class ActivitiService {

    Logger logger= LoggerFactory.getLogger(ActivitiService.class);

    private final RuntimeService runtimeService;

    private final TaskService taskService;

    private final HistoryService historyService;

    private final RepositoryService repositoryService;

    @Autowired
    public ActivitiService(RuntimeService runtimeService, TaskService taskService, HistoryService historyService, RepositoryService repositoryService) {
        this.runtimeService = runtimeService;
        this.taskService = taskService;
        this.historyService = historyService;
        this.repositoryService = repositoryService;
    }

    public ProcessDefinition deployProcess(MultipartFile bpmn, String path) throws IOException {
        if (BaseUtils.isNullOrEmpty(bpmn) || BaseUtils.isNullOrEmpty(path)) {
            return null;
        }
        //上传文件到processes
        File file = new File(path + bpmn.getOriginalFilename());
        bpmn.transferTo(file);

        String resource = ResourceLoader.CLASSPATH_URL_PREFIX + bpmn.getOriginalFilename();
        Deployment deployment = repositoryService.createDeployment().addClasspathResource(resource).deploy();
        logger.info("Process [" + deployment.getName() + "] deployed successful");
        return repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).singleResult();
    }

    public ProcessInstance startProcess(String key) {
        if (BaseUtils.isNullOrEmpty(key)) {
            return null;
        }
        return runtimeService.startProcessInstanceByKey(key);
    }

    public List<TaskInfo> getTasksByAssignee(String assignee) {
        if (BaseUtils.isNullOrEmpty(assignee)) {
            return null;
        }
        List<Task> tasks = taskService.createTaskQuery().taskAssignee(assignee).list();
        if (BaseUtils.isNullOrEmpty(tasks)) {
            return null;
        }
        List<TaskInfo> infos = new ArrayList<TaskInfo>();
        for (Task task: tasks) {
            infos.add(new TaskInfo(task.getId(), task.getName()));
        }
        return infos;
    }

    public List<TaskInfo> getTasksByGroup(String group) {
        if (BaseUtils.isNullOrEmpty(group)) {
            return null;
        }
        List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup(group).list();
        if (BaseUtils.isNullOrEmpty(tasks)) {
            return null;
        }
        List<TaskInfo> infos = new ArrayList<>();
        for (Task task : tasks) {
            infos.add(new TaskInfo(task.getId(), task.getName()));
        }
        return infos;
    }

    public List<TaskInfo> getTasks(String assigneeOrGroup) {
        if (BaseUtils.isNullOrEmpty(assigneeOrGroup)) {
            return null;
        }
        List<Task> tasks = taskService.createTaskQuery().taskCandidateOrAssigned(assigneeOrGroup).list();
        if (BaseUtils.isNullOrEmpty(tasks)) {
            return null;
        }
        List<TaskInfo> infos = new ArrayList<>();
        for (Task task : tasks) {
            infos.add(new TaskInfo(task.getId(), task.getName()));
        }
        return infos;
    }

    public void completeTask(String taskId, Object item) {
        Map<String, Object> map = BaseUtils.object2ConditionMap(item);
        if (BaseUtils.isNullOrEmpty(taskId) || BaseUtils.isNullOrEmpty(map)) {
            logger.error("Params cannot be empty");
            throw new RuntimeException("Params cannot be empty");
        }
        taskService.complete(taskId, map);
    }

    public void completeTask(String taskId) {
        if (BaseUtils.isNullOrEmpty(taskId)) {
            logger.error("Param taskId cannot be empty");
            return;
        }
        taskService.complete(taskId);
    }

    public static class TaskInfo {

        private String id;
        private String name;

        public TaskInfo(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

}