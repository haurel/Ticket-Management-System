package com.tms.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.common.objects.models.api.ResponseModel;
import com.tms.api.objects.models.project.ProjectModel;
import com.tms.api.objects.models.project.request.AddNewProjectModel;
import com.tms.api.objects.viewmodels.organization.OrganizationProjectViewModel;
import com.tms.api.service.project.ProjectService;



@RestController
@RequestMapping("project")
public class ProjectController {

    private final ProjectService _projectService;

    public ProjectController(ProjectService projectService) {
        _projectService = projectService;
    }

    @GetMapping("getOrganizationProjects")
    public ResponseModel<OrganizationProjectViewModel> GetOrganizationProjects(@RequestParam String organizationId) {
        return _projectService.GetOrganizationProjects(organizationId);
    }

    @PostMapping("addNewProject")
    public ResponseModel<String> addNewProject(@RequestBody AddNewProjectModel request) {
        return _projectService.AddNewProject(request);
    }
    
    @GetMapping("getProjectById")
    public ResponseModel<ProjectModel> GetProjectById(@RequestParam String projectId) {
        return _projectService.GetProjectById(projectId);
    }
    
    @PostMapping("saveProject")
    public ResponseModel<Boolean> SaveProject(@RequestBody ProjectModel project) {
        return _projectService.SaveProject(project);
    }
}
