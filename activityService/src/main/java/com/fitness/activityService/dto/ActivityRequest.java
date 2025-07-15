package com.fitness.activityService.dto;

import com.fitness.activityService.constant.ActivityType;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.Map;

@Data
public class ActivityRequest {
    private String userId;
    private ActivityType type;
    private Integer duration;
    private Integer carloriesBurned;
    private LocalDateTime startTime;
    private Map<String, Object> additionalMetrics;
}
