package mozi.mozispring.Moment;

import io.swagger.annotations.ApiOperation;
import mozi.mozispring.Domain.Dto.DeleteDto;
import mozi.mozispring.Domain.Dto.MomentDto;
import mozi.mozispring.Domain.Dto.MomentRetDto;
import mozi.mozispring.Domain.Moment;
import mozi.mozispring.Domain.MomentPhoto;
import mozi.mozispring.Domain.User;
import mozi.mozispring.User.UserRepository;
import mozi.mozispring.Firebase.FireBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
public class MomentController {
    private UserRepository userRepository;
    private MomentService momentService;

    @Autowired
    public MomentController(UserRepository userRepository, MomentService momentService) {
        this.userRepository = userRepository;
        this.momentService = momentService;
    }

    /**
     * 특정 유저의 모든 모먼트 모두 불러오기
     */
    @GetMapping("/moment-list/{id}")
    @ResponseBody
    public List<MomentRetDto> getAllMomentController(@PathVariable("id") Long id){ // PathVariable로 유저 id 전달
        return momentService.getAllMoment(id); // 특정 유저의 모든 모먼트 모두 불러오기
    }

    /**
     * 유저의 모먼트 한 건 불러오기
     */
    @ApiOperation(value="유저의 모먼트 한 건 불러오기", notes="유저의 모먼트 한 건 불러오기")
    @GetMapping("/moment/{id}")
    @ResponseBody
    public MomentRetDto getSingleMomentController(@PathVariable("id") Long momentId){ // PathVariable로 모먼트 id 전달
        return momentService.getMoment(momentId);
    }

    /**
     * 모먼트 기록하기
     */
    @ApiOperation(value="모먼트 기록하기", notes="모먼트 기록하기")
    @PostMapping("/moment")
    @ResponseBody
    public MomentPhoto createMomentController(@RequestBody MomentDto momentDto){
        return momentService.createMoment(momentDto); // 모먼트 기록하기
    }

    /**
     * 모먼트 삭제하기
     */
    @ApiOperation(value="모먼트 삭제하기", notes="NEED JWT IN HEADER: 모먼트 삭제하기")
    @DeleteMapping("/moment/{id}")
    @ResponseBody
    public DeleteDto deleteMomentController(@PathVariable("id") Long id){ // 삭제할 모먼트 id 를 파라미터로 전달
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails)principal;
        String userEmail = ((UserDetails) principal).getUsername();
        Optional<User> findUser = userRepository.findByEmail(userEmail);
        return momentService.deleteMoment(findUser, id);
    }

    /**
     * 해시태그로 모먼트 검색하기
     */
    @ApiOperation(value="해시태그로 모먼트 검색하기", notes="해시태그로 모먼트 검색하기")
    @GetMapping("/moment/{tag}")
    @ResponseBody
    public List<MomentRetDto> getMomentByHashTagController(@PathVariable("tag") String tag){
        return momentService.getMomentByHashTag(tag);
    }
}
