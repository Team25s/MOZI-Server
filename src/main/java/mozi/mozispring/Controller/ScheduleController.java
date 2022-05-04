package mozi.mozispring.Controller;

import mozi.mozispring.Domain.Dto.ScheduleDelDto;
import mozi.mozispring.Domain.Dto.ScheduleDto;
import mozi.mozispring.Domain.Schedule;
import mozi.mozispring.Domain.SimplUser;
import mozi.mozispring.Domain.User;
import mozi.mozispring.Repository.ScheduleRepository;
import mozi.mozispring.Repository.SimplUserRepository;
import mozi.mozispring.Repository.UserRepository;
import mozi.mozispring.Util.BasicResponse;
import mozi.mozispring.Util.CommonResponse;
import mozi.mozispring.Util.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    @GetMapping("/schedule")
    @ResponseBody
    public ResponseEntity<? extends BasicResponse> getScheduleController(Long id){
        List<Schedule> schedules = scheduleRepository.findAllById(id);
        return ResponseEntity.ok().body(new CommonResponse(schedules));
    }

    /**
     * 일정 등록하기
     */
    @PostMapping("/schedule")
    @ResponseBody
    public ResponseEntity<? extends BasicResponse> makeScheduleController(@RequestBody ScheduleDto scheduleDto){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails)principal;
        String userEmail = ((UserDetails) principal).getUsername();
        User findUser = userRepository.findByEmail(userEmail).get();

        Schedule schedule = new Schedule();
        schedule.setTitle(schedule.getTitle());
        schedule.setLocation(scheduleDto.getLocation());
        schedule.setStartDate(scheduleDto.getStartDate());
        schedule.setEndDate(scheduleDto.getEndDate());

        List<Long> participants = scheduleDto.getFriends();
        List<SimplUser> simplUserList = new ArrayList<>();
        for(Long id: participants){
            SimplUser simplUser = simplUserRepository.findById(id).get();
            simplUserList.add(simplUser);
        }
        schedule.setFriendList(simplUserList);
        participants.add(findUser.getId());  // 본인 추가

        int memberCount = 0;
        for(Long id : participants){
            schedule.setUserId(id);
            scheduleRepository.save(schedule);
            memberCount += 1;
        }
        if (memberCount == participants.size()){
            return ResponseEntity.ok().body(new CommonResponse<>("성공적으로 추가되었습니다."));
        }
        return ResponseEntity.ok().body(new ErrorResponse("일정을 추가할 수 없습니다."));
    }

    /**
     * 일정 수정하기 
     */
    @PutMapping("/schedule")
    @ResponseBody
    public ResponseEntity<? extends BasicResponse> updateScheduleController(@RequestBody ScheduleDto scheduleDto){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails)principal;
        String userEmail = ((UserDetails) principal).getUsername();
        User findUser = userRepository.findByEmail(userEmail).get();

        if()

    }


    /**
     * 일정 삭제하기
     */
    @DeleteMapping("/schedule")
    @ResponseBody
    public ResponseEntity<? extends BasicResponse> deleteScheduleController(@RequestBody ScheduleDelDto scheduleDelDto){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails)principal;
        String userEmail = ((UserDetails) principal).getUsername();
        User findUser = userRepository.findByEmail(userEmail).get();

        if(findUser.getId().equals(scheduleDelDto.getUserId())){
            scheduleRepository.deleteById(scheduleDelDto.getId());
            return ResponseEntity.ok().body(new CommonResponse<>("성공적으로 삭제하였습니다."));
        }else{
            return ResponseEntity.ok().body(new ErrorResponse("자신의 일정만 삭제할 수 있습니다."));
        }
    }
}
