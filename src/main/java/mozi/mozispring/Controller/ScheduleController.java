//package mozi.mozispring.Controller;
//
//import mozi.mozispring.Domain.Dto.ScheduleDto;
//import mozi.mozispring.Domain.Schedule;
//import mozi.mozispring.Domain.User;
//import mozi.mozispring.Repository.ScheduleRepository;
//import mozi.mozispring.Repository.UserRepository;
//import mozi.mozispring.Util.BasicResponse;
//import mozi.mozispring.Util.CommonResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@Controller
//public class ScheduleController {
//
//    private ScheduleRepository scheduleRepository;
//    private UserRepository userRepository;
//
//    @Autowired
//    public ScheduleController(ScheduleRepository scheduleRepository, UserRepository userRepository) {
//        this.scheduleRepository = scheduleRepository;
//        this.userRepository = userRepository;
//    }
//
//    /**
//     * 유저 모든 일정
//     */
//    @GetMapping("/schedule")
//    @ResponseBody
//    public ResponseEntity<? extends BasicResponse> getScheduleController(){
//
//    }
//
//    /**
//     * 일정 등록하기
//     */
//    @PutMapping("/schedule")
//    @ResponseBody
//    public ResponseEntity<? extends BasicResponse> makeScheduleController(@RequestBody ScheduleDto scheduleDto){
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        UserDetails userDetails = (UserDetails)principal;
//        String userEmail = ((UserDetails) principal).getUsername();
//        User findUser = userRepository.findByEmail(userEmail).get();
//
//        Schedule schedule = new Schedule();
//
//        schedule.setTitle(schedule.getTitle());
//        schedule.setLocation(scheduleDto.getLocation());
//        schedule.setStartDate(scheduleDto.getStartDate());
//        schedule.setEndDate(scheduleDto.getEndDate());
//
//        List<String> participants = scheduleDto.getFriends();
//        participants.add(findUser.getName());
//        for(String s : participants){
//
//        }
//    }
//
//    /**
//     * 일정 삭제하기
//     */
//    @DeleteMapping("/schedule")
//    @ResponseBody
//    public ResponseEntity<? extends BasicResponse> deleteScheduleController(){
//
//    }
//}
