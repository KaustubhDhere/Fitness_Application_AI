package com.fitness.activityService.service;

import com.fitness.activityService.dto.ActivityRequest;
import com.fitness.activityService.dto.ActivityResponse;
import com.fitness.activityService.model.Activity;
import com.fitness.activityService.repository.ActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActivityService {

    private final ActivityRepository activityRepository;

    public ActivityResponse trackActivity(ActivityRequest activityRequest) {

        Activity activity= Activity.builder()
                .userId(activityRequest.getUserId())
                .type(activityRequest.getType())
                .duration(activityRequest.getDuration())
                .carloriesBurned(activityRequest.getCarloriesBurned())
                .startTime(activityRequest.getStartTime())
                .additionalMetrics(activityRequest.getAdditionalMetrics())
                .build();

        Activity savedActivity= activityRepository.save(activity);

        return mapToResponse(savedActivity);
    }

     /*
         @Return : Activity Response
     */
    private ActivityResponse mapToResponse(Activity savedActivity) {

        return   ActivityResponse.builder()
                .id(savedActivity.getId())
                .userId(savedActivity.getUserId())
                .type(savedActivity.getType())
                .duration(savedActivity.getDuration())
                .carloriesBurned(savedActivity.getCarloriesBurned())
                .startTime(savedActivity.getStartTime())
                .additionalMetrics(savedActivity.getAdditionalMetrics())
                .createdAt(savedActivity.getCreatedAt())
                .updatedAt(savedActivity.getUpdatedAt())
                .build();
    }

    public List<ActivityResponse> getUserActivity(String userId) {
        List<Activity> activityResponses = activityRepository.findByUserId(userId);
        return activityResponses.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public ActivityResponse getActivity(String activityId) {
         return activityRepository.findById(activityId)
                 .map(this::mapToResponse)
                .orElseThrow(()-> new RuntimeException("Invalid Activity ID"));
    }
}
