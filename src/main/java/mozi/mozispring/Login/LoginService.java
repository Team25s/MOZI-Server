package mozi.mozispring.Login;

import com.google.cloud.storage.StorageException;
import mozi.mozispring.Comment.CommentRepository;
import mozi.mozispring.Domain.Dto.*;
import mozi.mozispring.Domain.SimplUser;
import mozi.mozispring.Domain.User;
import mozi.mozispring.Favorites.FavoritesRepository;
import mozi.mozispring.Firebase.FireBaseService;
import mozi.mozispring.Friend.FriendRepository;
import mozi.mozispring.Jwt.JwtTokenProvider;
import mozi.mozispring.Moment.MomentRepository;
import mozi.mozispring.Profile.ProfileRepository;
import mozi.mozispring.Schedule.ScheduleRepository;
import mozi.mozispring.User.SimplUserRepository;
import mozi.mozispring.User.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    private PasswordEncoder passwordEncoder;
    private JwtTokenProvider jwtTokenProvider;
    private UserRepository userRepository;
    private MomentRepository momentRepository;
    private CommentRepository commentRepository;
    private ScheduleRepository scheduleRepository;
    private FireBaseService fireBaseService;
    private SimplUserRepository simplUserRepository;
    private FriendRepository friendRepository;
    private FavoritesRepository favoritesRepository;

    public LoginService(PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider, UserRepository userRepository, MomentRepository momentRepository, CommentRepository commentRepository, ScheduleRepository scheduleRepository, FireBaseService fireBaseService, SimplUserRepository simplUserRepository, FriendRepository friendRepository, FavoritesRepository favoritesRepository) {
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
        this.momentRepository = momentRepository;
        this.commentRepository = commentRepository;
        this.scheduleRepository = scheduleRepository;
        this.fireBaseService = fireBaseService;
        this.simplUserRepository = simplUserRepository;
        this.friendRepository = friendRepository;
        this.favoritesRepository = favoritesRepository;
    }

    /**
     * 회원가입
     * @param signInDto
     * @return
     */
    public JoinDto join(SignInDto signInDto) {
        System.out.println("회원가입 요청입니다.");
        Optional<User> result = userRepository.findByEmail(signInDto.getEmail());
        if (result.isPresent()) {
            JoinDto joinDto = new JoinDto();
            joinDto.setMessage("이미 존재하는 회원입니다.");
            joinDto.setUserId(-1L);
            return joinDto;
        } else {
            User user = User.builder()
                    .email(signInDto.getEmail())
                    .password(passwordEncoder.encode(signInDto.getPassword()))
                    .name(signInDto.getName())
                    .build();
            userRepository.save(user);
            SimplUser simplUser = SimplUser.builder()
                    .email(user.getEmail())
                    .name(user.getName())
                    .mbti(user.getMbti())
                    .profileFilename(null)
                    .profileFileURL(null)
                    .build();
            JoinDto joinDto = new JoinDto();
            joinDto.setUserId(simplUserRepository.save(simplUser).getId());
            joinDto.setMessage("회원가입에 성공하였습니다.");
            return joinDto;
        }
    }

    /**
     * 로그인
     * @param logInDto
     * @return
     */
    public JwtRetDto login(LogInDto logInDto) {
        System.out.println("로그인 요청입니다.");
        Optional<User> findUser = userRepository.findByEmail(logInDto.getEmail());
        if(!findUser.isPresent()){
            JwtRetDto jwtRetDto = new JwtRetDto();
            jwtRetDto.setMessage("가입되지 않은 아이디입니다.");
            jwtRetDto.setJwt(null);
            return jwtRetDto;
        }
        User member = findUser.get();
        if (!passwordEncoder.matches(logInDto.getPassword(), member.getPassword())) {
            JwtRetDto jwtRetDto = new JwtRetDto();
            jwtRetDto.setMessage("잘못된 비밀번호입니다.");
            jwtRetDto.setJwt(null);
            return jwtRetDto;
        }
        JwtRetDto jwtRetDto = new JwtRetDto();
        jwtRetDto.setMessage("유효한 jwt를 발급하였습니다.");
        jwtRetDto.setJwt(jwtTokenProvider.createToken(member.getEmail()));
        return jwtRetDto;
    }

    /**
     * 회원탈퇴
     * @param logInDto
     * @return
     */
    public DeleteDto withdraw(LogInDto logInDto) {
        System.out.println("1. 회원탈퇴 요청입니다. ");
        Optional<User> findUser = userRepository.findByEmail(logInDto.getEmail());

        DeleteDto deleteDto = new DeleteDto();
        if(!findUser.isPresent()){
            System.out.println("2. 탈퇴하려는 계정이 존재하지 않습니다.");
            deleteDto.setDeleted(false);
            deleteDto.setMessage("탈퇴하려는 계정이 존재하지 않습니다. ");
            return deleteDto;
        }

        if(passwordEncoder.matches(logInDto.getPassword(), findUser.get().getPassword())) { // 올바른 비밀번호일 경우
            if (findUser.get().getProfileFilename() != null){
                try {
                    fireBaseService.deleteFiles(findUser.get().getProfileFilename()); // 파이어베이스에서 프로필 이미지 삭제
                    System.out.println("3. 파이어베이스에서 이미지를 삭제하고 있습니다.");
                }catch(StorageException e){
                    System.out.println("4. 이미지 삭제 도중 예외가 발생했습니다. ");
                    deleteDto.setDeleted(false);
                    deleteDto.setMessage("이미지 삭제 도중 예외가 발생했습니다.");
                    return deleteDto;
                }
            }
            // ************************ 삭제 동작 ********************************
            userRepository.deleteById(findUser.get().getId());                // 나의 정보 디비에서 삭제
            simplUserRepository.deleteByEmail(findUser.get().getEmail());     // 나의 요약 정보 디비에서 삭제
            scheduleRepository.deleteAllByUserId(findUser.get().getId());     // 나와 관련된 모든 일정 삭제
            momentRepository.deleteAllByUserId(findUser.get().getId());       // 나와 관련된 모든 모먼트 삭제
            friendRepository.deleteByUserId(findUser.get().getId());          // 나의 친구 목록 삭제
            friendRepository.deleteByFriendId(findUser.get().getId());        // 친구 목록에서 나를 삭제
            favoritesRepository.deleteAllByUserId(findUser.get().getId());    // 나의 즐겨찾기 목록 삭제
            favoritesRepository.deleteAllByOpponentId(findUser.get().getId()); // 친구의 즐겨찾기 목록에서 나를 삭제
            commentRepository.deleteAllByUserId(findUser.get().getId());      // 내게 달린 모든 댓글 삭제
            // ******************************************************************

            System.out.println("5. 디비에서 회원을 삭제하였습니다.");
            deleteDto.setDeleted(true);
            deleteDto.setMessage("성공적으로 탈퇴되었습니다.");
            return deleteDto;
        }else{
            System.out.println("6. 비밀번호가 맞지 않습니다.");
            deleteDto.setDeleted(false);
            deleteDto.setMessage("비밀번호가 맞지 않습니다.");
            return deleteDto;
        }
    }

    /**
     * 탈퇴 회원 보유 콘텐츠 수량 고지 api
     * @param userEmail
     * @return
     */
    public WithdrawDto countUserContent(String userEmail) {
        Optional<User> findUser = userRepository.findByEmail(userEmail);
        Long userId = findUser.get().getId();
        return WithdrawDto.builder()
                .comment(commentRepository.countByUserId(userId))
                .moment(momentRepository.countByUserId(userId))
                .schedule(scheduleRepository.countByUserId(userId))
                .build();
    }
}
