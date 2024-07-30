package com.tms.api.service.sprint;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.common.objects.models.api.ResponseModel;
import com.common.repository.logging.LoggingApiRepository;
import com.tms.api.objects.models.sprint.SprintModel;
import com.tms.api.objects.models.sprint.document.SprintDocument;
import com.tms.api.objects.models.sprint.request.AddSprintRequestModel;
import com.tms.api.objects.models.sprint.request.UpdateSprintRequestModel;
import com.tms.api.objects.types.task.SprintType;
import com.tms.api.repository.database.project.ProjectRepository;
import com.tms.api.repository.database.sprint.SprintRepository;
import com.tms.api.service.BaseService;

@Service
public class SprintService extends BaseService  {

    private final SprintRepository _sprintRepository;
    private final ProjectRepository _projectRepository;

    public SprintService(LoggingApiRepository loggingApiRepository,
        ModelMapper modelMapper,
        SprintRepository sprintRepository,
        ProjectRepository projectRepository
    ) {
        super(loggingApiRepository, modelMapper);
        _sprintRepository = sprintRepository;
        _projectRepository = projectRepository;
    }

    public  ResponseModel<List<SprintModel>> GetSprints(String projectId) {
        try {
            var sprints = _sprintRepository.findAllByProjectId(projectId);
            var response = sprints
                .stream()
                .map(s -> new SprintModel(s.getSprintId(), s.getSprintName(), s.getProjectId(), s.getTasks(), s.getSprintType(), s.getStartDate(), s.getEndDate()))
                .collect(Collectors.toList());

            return GetResponse(response, null);

        } catch (Exception exception) {
            var map = new HashMap<String, Object>();
            map.put("Method", "GetSprints");
            map.put("Request", MessageFormat.format("ProjectId: {0}", projectId));
            map.put("Exception", ExceptionUtils.getStackTrace(exception));
            LogException(exception, map);

            return GetResponse(null, exception.toString());
        }
    }

    public ResponseModel<String> AddSprint(AddSprintRequestModel request) {
        try {
            var currentProject = _projectRepository.findById(request.getProjectId()).orElse(null);
            if (currentProject == null) {
                return GetResponse(null, "Project not found");
            }

            var sprintDocument = new SprintDocument(null, request.getSprintName(), request.getProjectId(), null, SprintType.Inactive, GetDateNow(), null);
            sprintDocument = _sprintRepository.save(sprintDocument);
            if (sprintDocument.getSprintId() == null) {
                return GetResponse(null, "Sprint wasn't saved!");
            }
            
            return GetResponse(sprintDocument.getSprintId(), null);
        } catch (Exception exception) {
            var map = new HashMap<String, Object>();
            map.put("Method", "AddSprint");
            map.put("Request", request);
            map.put("Exception", ExceptionUtils.getStackTrace(exception));
            LogException(exception, map);

            return GetResponse(null, exception.toString());
        }
    }

    public ResponseModel<String> UpdateSprint(UpdateSprintRequestModel request) {
        try {
            var sprintDocument = _sprintRepository.findById(request.getSprintId()).orElse(null);
            if (sprintDocument == null) {
                return GetResponse(null, "Sprint not found!");
            }

            var updateSprintDocument = sprintDocument;
            var shouldUpdateSprintDocument = false;
            if (request.getSprintType() != null
               && sprintDocument.getSprintType() != request.getSprintType()) {
                updateSprintDocument.setSprintType(request.getSprintType());
                shouldUpdateSprintDocument = true;
            }

            if (request.getEndDate() != null) {
                updateSprintDocument.setEndDate(request.getEndDate());
                shouldUpdateSprintDocument = true;
            }

            if (shouldUpdateSprintDocument) {
                _sprintRepository.save(updateSprintDocument);
                return GetResponse(updateSprintDocument.getSprintId(), "Sprint was updated");
            }

            return GetResponse(null, "Sprint was not modified");
        } catch (Exception exception) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("Method", "UpdateSprint");
            map.put("Request", request);
            map.put("Exception", ExceptionUtils.getStackTrace(exception));
            LogException(exception, map);

            return GetResponse(null, exception.toString());
        }
    }

    public ResponseModel<SprintModel> GetCurrentSprint(String projectId) {
        try {
            var sprintDocument = _sprintRepository.findByProjectIdAndSprintType(projectId, SprintType.Current);
            if (sprintDocument == null) {
                return GetResponse(null, "Current Sprint was not found!");
            }

            var response = _modelMapper.map(sprintDocument, SprintModel.class);
            return GetResponse(response, "");
        } catch (Exception exception) {
            var map = new HashMap<String, Object>();
            map.put("Method", "GetCurrentSprint");
            map.put("Request", MessageFormat.format("ProjectId: {0}", projectId));
            map.put("Exception", ExceptionUtils.getStackTrace(exception));
            LogException(exception, map);

            return GetResponse(null, exception.toString());
        }
    }
    
}
