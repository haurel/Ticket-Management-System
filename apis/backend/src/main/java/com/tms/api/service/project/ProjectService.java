package com.tms.api.service.project;

import java.text.MessageFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.objects.models.api.ResponseModel;
import com.common.repository.logging.LoggingApiRepository;
import com.common.service.BaseService;
import com.common.types.api.ResponseStatusType;
import com.tms.api.objects.models.organization.OrganizationModel;
import com.tms.api.objects.models.project.ProjectModel;
import com.tms.api.objects.models.project.document.ProjectDocument;
import com.tms.api.objects.models.project.request.AddNewProjectModel;
import com.tms.api.objects.types.StatusType;
import com.tms.api.objects.viewmodels.organization.OrganizationProjectViewModel;
import com.tms.api.repository.database.organization.OrganizationRepository;
import com.tms.api.repository.database.project.ProjectRepository;

@Service
@Transactional
public class ProjectService extends BaseService {
    
    private final ProjectRepository _projectRepository;
    private final OrganizationRepository _organizationRepository;

    public ProjectService(ProjectRepository projectRepository,
        OrganizationRepository organizationRepository,
        LoggingApiRepository loggingApiRepository,
        ModelMapper modelMapper
    ) {
        super(loggingApiRepository, modelMapper);
        
        _projectRepository = projectRepository;
        _organizationRepository = organizationRepository;
    }

    public ResponseModel<OrganizationProjectViewModel> GetOrganizationProjects(String organizationId) {
        try {
            var organizationDocument = _organizationRepository.findById(organizationId).orElse(null);
            if (organizationDocument == null) {
                return GetResponse(null, "Organization not found!");
            }

            var organizationModel = new OrganizationModel(organizationDocument.id(), organizationDocument.name(), null, null);
            var projectDocuments = _projectRepository.findByOrganizationId(organizationId);
            if (projectDocuments == null || projectDocuments.isEmpty()) {
                return new ResponseModel<OrganizationProjectViewModel>(new OrganizationProjectViewModel(organizationModel, null), null, ResponseStatusType.Warning);
            }

            var projects = projectDocuments
                .stream()
                .map(p -> new ProjectModel(p.getProjectId(), p.getOrganizationId(), p.getProjectGuid(), p.getProjectGuid(), p.getUsers(), p.getStatusId(), p.getCreatedDate(), p.getModifiedDate()))
                .collect(Collectors.toList());

            return GetResponse(new OrganizationProjectViewModel(organizationModel, projects), null);
        } catch (Exception exception) {
            var map = new HashMap<String, Object>();
            map.put("Method", "GetOrganizationProjects");
            map.put("Request", MessageFormat.format("OrganizationId: {0}", organizationId));
            map.put("Exception", ExceptionUtils.getStackTrace(exception));
            LogException(exception, map);

            return GetResponse(null, exception.toString());
        }
    }

    public ResponseModel<String> AddNewProject(AddNewProjectModel request) {
        try {
            var organizationDocument = _organizationRepository.findById(request.organizationId()).orElse(null);
            if (organizationDocument == null) {
                return GetResponse(null, "Organization not found!");
            }

            var projectGuid = UUID.randomUUID().toString();
            var projectDocument = new ProjectDocument(null, request.organizationId(), projectGuid, request.projectName(), request.users(), StatusType.Active, GetDateNow(), null);

            projectDocument = _projectRepository.save(projectDocument);
            return GetResponse(projectDocument.getProjectId(), null);
        } catch (Exception exception) {
            var map = new HashMap<String, Object>();
            map.put("Method", "AddNewProject");
            map.put("Request",  request);
            map.put("Exception", ExceptionUtils.getStackTrace(exception));
            LogException(exception, map);

            return GetResponse(null, exception.toString());
        }
    }

    public ResponseModel<ProjectModel> GetProjectById(String projectId) {
        try {
            var projectDocument = _projectRepository.findById(projectId).orElse(null);
            String responseMessage = null;
            ProjectModel project = null;
            if (projectDocument == null) {
                responseMessage = "Project not found!";
            } else {
                project = new ProjectModel(projectDocument.getProjectId(), projectDocument.getOrganizationId(), projectDocument.getProjectGuid(), projectDocument.getProjectName(), projectDocument.getUsers(), projectDocument.getStatusId(), null, null);
            }

            return GetResponse(project, responseMessage);
        } catch (Exception exception) {
            var map = new HashMap<String, Object>();
            map.put("Method", "GetOrganizationProjects");
            map.put("Request", MessageFormat.format("ProjectId: {0}", projectId));
            map.put("Exception", ExceptionUtils.getStackTrace(exception));
            LogException(exception, map);

            return GetResponse(null, exception.toString());
        }
    }

    public ResponseModel<Boolean> SaveProject(ProjectModel project) {
        try {
            Date modifiedDate = null;
            if (project.projectId() != null) {
                modifiedDate = GetDateNow();
            }

            var projectDocument = new ProjectDocument(project.projectId(), project.organizationId(), project.projectGuid(), project.projectName(), project.Users(), project.statusId(), project.createdDate(), modifiedDate);
            _projectRepository.save(projectDocument);
            return GetResponse(true, null);
        } catch (Exception exception) {
            var map = new HashMap<String, Object>();
            map.put("Method", "GetOrganizationProjects");
            map.put("Request", project);
            map.put("Exception", ExceptionUtils.getStackTrace(exception));
            LogException(exception, map);

            return GetResponse(null, exception.toString());
        }
    }
}
