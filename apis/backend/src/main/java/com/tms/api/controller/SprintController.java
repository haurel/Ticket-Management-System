package com.tms.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.common.objects.models.api.ResponseModel;
import com.tms.api.objects.models.sprint.SprintModel;
import com.tms.api.objects.models.sprint.request.AddSprintRequestModel;
import com.tms.api.objects.models.sprint.request.UpdateSprintRequestModel;
import com.tms.api.service.sprint.SprintService;


@RestController
@RequestMapping("sprint")
public class SprintController extends BaseController {
    
    private final SprintService _sprintService;
    public SprintController(SprintService sprintService) {
        _sprintService = sprintService;
    }

    @GetMapping("getSprints")
    public  ResponseModel<List<SprintModel>> GetSprints(@RequestParam String projectId) {
        return _sprintService.GetSprints(projectId);
    }
    
    @PostMapping("addSprint")
    public ResponseModel<String> AddSprint(@RequestBody AddSprintRequestModel request) {
        return _sprintService.AddSprint(request);
    }
    
    @PostMapping("updateSprint")
    public ResponseModel<String> UpdateSprint(@RequestBody UpdateSprintRequestModel request) {
        return _sprintService.UpdateSprint(request);
    }
    
    @GetMapping("getCurrentSprint")
    public ResponseModel<SprintModel> GetCurrentSprint(@RequestParam String projectId) {
        return _sprintService.GetCurrentSprint(projectId);
    }
    
}