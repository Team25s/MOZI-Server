package mozi.mozispring.Schedule;

import mozi.mozispring.Domain.Dto.DeleteDto;
import mozi.mozispring.Domain.Dto.ScheduleDelDto;
import mozi.mozispring.Domain.Dto.ScheduleDto;
import mozi.mozispring.Domain.Schedule;
import mozi.mozispring.Domain.SimplUser;
import mozi.mozispring.Domain.User;
import mozi.mozispring.User.SimplUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleService {
    private SimplUserRepository simplUserRepository;
    private ScheduleRepository scheduleRepository;

    @Autowired
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


    /**
     * 일정 수정
     */
    public Schedule updateSchedule(ScheduleDto scheduleDto, User findUser) {
        Schedule findSchedule = scheduleRepository.findByTitle(scheduleDto.getTitle()).get();
        List<SimplUser> simplUserList = findSchedule.getFriendList();
        boolean flag = false;
        for(SimplUser simplUser: simplUserList){
            if(findUser.getId() == simplUser.getId()){
                flag = true;
                break;
            }
        }
        if (flag){
            findSchedule.setTitle(scheduleDto.getTitle());
            findSchedule.setLocation(scheduleDto.getLocation());
            findSchedule.setStartDate(scheduleDto.getStartDate());
            findSchedule.setEndDate(scheduleDto.getEndDate());

            List<Long> participants = scheduleDto.getFriends();
            List<SimplUser> simplUserList2 = new ArrayList<>();
            for(Long id: participants){
                SimplUser findSimplUser = simplUserRepository.findById(id).get();
                simplUserList2.add(findSimplUser);
            }
            findSchedule.setFriendList(simplUserList2);
            Schedule schedule = scheduleRepository.save(findSchedule);
            return schedule;
        }else{
            return new Schedule();
        }
    }

    /**
     * 일정 삭제
     */
    public DeleteDto deleteSchedule(ScheduleDelDto scheduleDelDto, User findUser) {
        DeleteDto deleteDto = new DeleteDto();
        if(findUser.getId()==scheduleDelDto.getUserId()){
            scheduleRepository.deleteById(scheduleDelDto.getId());
            deleteDto.setDeleted(true);
            deleteDto.setMessage("성공적으로 삭제하였습니다.");
            return deleteDto;
        }else{
            deleteDto.setDeleted(false);
            deleteDto.setMessage("자신의 일정만 삭제할 수 있습니다.");
            return deleteDto;
        }
    }
}
