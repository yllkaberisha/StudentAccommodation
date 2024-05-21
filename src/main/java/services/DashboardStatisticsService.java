package services;

import models.dto.UserDto;

import java.util.List;

public class DashboardStatisticsService {

    public int countMaleUsers(List<UserDto> users) {
        return (int) users.stream().filter(user -> "M".equals(user.getGender())).count();
    }

    public int countFemaleUsers(List<UserDto> users) {
        return (int) users.stream().filter(user -> "F".equals(user.getGender())).count();
    }
}
