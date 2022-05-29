package mozi.mozispring.Schedule;

import mozi.mozispring.Domain.Dto.ScheduleDto;
import mozi.mozispring.Domain.Schedule;
import mozi.mozispring.Domain.SimplUser;
import mozi.mozispring.Domain.User;
import mozi.mozispring.User.SimplUserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleService {
    private SimplUserRepository simplUserRepository;
    private ScheduleRepository scheduleRepository;


    public ScheduleService(SimplUserRepository simplUserRepository, ScheduleRepository scheduleRepository) {
        this.simplUserRepository = simplUserRepository;
        this.scheduleRepository = scheduleRepository;
    }

    /**
     *  새로운 일정 등록
     */
    public Schedule makeSchedule(ScheduleDto scheduleDto, User findUser, SimplUser simplUser) {
        Schedule schedule = new Schedule();
        schedule.setTitle(schedule.getTitle());
        schedule.setLocation(scheduleDto.getLocation());
        schedule.setStartDate(scheduleDto.getStartDate());
        schedule.setEndDate(scheduleDto.getEndDate());

        List<Long> participants = scheduleDto.getFriends();
        List<SimplUser> simplUserList = new ArrayList<>();
        for(Long id: participants){
            SimplUser findSimplUser = simplUserRepository.findById(id).get();
            simplUserList.add(findSimplUser);
        }
        simplUserList.add(simplUser);
        schedule.setFriendList(simplUserList);
        participants.add(findUser.getId());  // 본인 추가

        int memberCount = 0;
        Schedule savedSchedule = null;
        for(Long id : participants){
            schedule.setUserId(id);
            savedSchedule = scheduleRepository.save(schedule);
            memberCount += 1;
        }
        if (memberCount == participants.size()){
            return savedSchedule;
        }
        return new Schedule();
    }




    
}
