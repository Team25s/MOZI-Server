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
    private ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleRepository scheduleRepository, UserRepository userRepository, SimplUserRepository simplUserRepository, ScheduleService scheduleService) {
        this.scheduleRepository = scheduleRepository;
        this.userRepository = userRepository;
        this.simplUserRepository = simplUserRepository;
        this.scheduleService = scheduleService;
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
        return scheduleService.makeSchedule(scheduleDto, findUser, simplUser); // 일정 등록
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
        return scheduleService.updateSchedule(scheduleDto, findUser); // 일정 수정
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
        return scheduleService.deleteSchedule(scheduleDelDto, findUser); // 일정 삭제
    }
}
