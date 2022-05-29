package mozi.mozispring.Schedule;

import io.swagger.annotations.ApiOperation;
import mozi.mozispring.Domain.Dto.DeleteDto;
import mozi.mozispring.Domain.Dto.ScheduleDelDto;
import mozi.mozispring.Domain.Dto.ScheduleDto;
import mozi.mozispring.Domain.Schedule;
import mozi.mozispring.Domain.SimplUser;
import mozi.mozispring.Domain.User;
import mozi.mozispring.User.SimplUserRepository;
import mozi.mozispring.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ScheduleController {

    private ScheduleRepository scheduleRepository;
    private UserRepository userRepository;
    private SimplUserRepository simplUserRepository;

    @Autowired
    public ScheduleController(ScheduleRepository scheduleRepository, UserRepository userRepository, SimplUserRepository simplUserRepository) {
        this.scheduleRepository = scheduleRepository;
        this.userRepository = userRepository;
        this.simplUserRepository = simplUserRepository;
    }

    /**
     * 유저 모든 일정 불러오기
     */
    @ApiOperation(value="유저 모든 일정 불러오기", notes="유저 모든 일정 불러오기")
    @GetMapping("/schedule/{id}")
    @ResponseBody
    public List<Schedule> getScheduleController(@PathVariable("id") Long id){
        List<Schedule> schedules = scheduleRepository.findAllById(id);
        return schedules;
    }

    /**
     * 일정 등록하기
     */
    @ApiOperation(value="일정 등록하기", notes="NEED JWT IN HEADER: 일정 등록하기")
    @PostMapping("/schedule")
    @ResponseBody
    public Schedule makeScheduleController(@RequestBody ScheduleDto scheduleDto){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails)principal;
        String userEmail = ((UserDetails) principal).getUsername();
        User findUser = userRepository.findByEmail(userEmail).get();
        SimplUser simplUser = simplUserRepository.findById(findUser.getId()).get();

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
     * 일정 수정하기 
     */
    @ApiOperation(value="일정 수정하기 ", notes="NEED JWT IN HEADER: 일정 수정하기 ")
    @PutMapping("/schedule")
    @ResponseBody
    public Schedule updateScheduleController(@RequestBody ScheduleDto scheduleDto){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails)principal;
        String userEmail = ((UserDetails) principal).getUsername();
        User findUser = userRepository.findByEmail(userEmail).get();

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
     * 일정 삭제하기
     */
    @ApiOperation(value="일정 삭제하기", notes="NEED JWT IN HEADER: 일정 삭제하기")
    @DeleteMapping("/schedule")
    @ResponseBody
    public DeleteDto deleteScheduleController(@RequestBody ScheduleDelDto scheduleDelDto){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails)principal;
        String userEmail = ((UserDetails) principal).getUsername();
        User findUser = userRepository.findByEmail(userEmail).get();

        DeleteDto deleteDto = new DeleteDto();
        if(findUser.getId().equals(scheduleDelDto.getUserId())){
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
