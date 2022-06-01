package mozi.mozispring.Moment;

import mozi.mozispring.Domain.Dto.DeleteDto;
import mozi.mozispring.Domain.Dto.MomentDto;
import mozi.mozispring.Domain.Dto.MomentRetDto;
import mozi.mozispring.Domain.Moment;
import mozi.mozispring.Domain.MomentPhoto;
import mozi.mozispring.Domain.User;
import mozi.mozispring.Firebase.FireBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MomentService {

    private MomentRepository momentRepository;
    private MomentPhotoRepository momentPhotoRepository;
    private FireBaseService fireBaseService;

    @Autowired
    public MomentService(MomentRepository momentRepository, MomentPhotoRepository momentPhotoRepository, FireBaseService fireBaseService) {
        this.momentRepository = momentRepository;
        this.momentPhotoRepository = momentPhotoRepository;
        this.fireBaseService = fireBaseService;
    }

    /**
     * 모먼트 반환
     */
    public MomentRetDto getMoment(Long momentId){ // 모먼트 id
        Moment findMoment = momentRepository.findById(momentId).get();
        List<MomentPhoto> momentPhotoList = momentPhotoRepository.findAllByMomentId(findMoment.getId());
        MomentRetDto momentRetDto = new MomentRetDto();

        momentRetDto.setTitle(findMoment.getTitle());
        momentRetDto.setContent(findMoment.getContent());
        momentRetDto.setDate(findMoment.getDate());
        momentRetDto.setUserId(findMoment.getUserId());
        momentRetDto.setHashTag(findMoment.getHashTag());

        List<String> fileURLList = new ArrayList<>();
        for(MomentPhoto momentPhoto : momentPhotoList){
            fileURLList.add(momentPhoto.getFileURL());
        }
        momentRetDto.setFileURLList(fileURLList);
        return momentRetDto;
    }

    /**
     * 특정 유저의 모든 모먼트 모두 불러오기
     */
    public List<MomentRetDto> getAllMoment(Long id) {
        List<Moment> momentList = momentRepository.findAllByUserId(id);
        List<MomentRetDto> momentRetDtoList = new ArrayList<>();
        for(Moment moment : momentList){
            momentRetDtoList.add(getMoment(moment.getId()));
        }
        return momentRetDtoList;
    }

    /**
     * 모먼트 기록하기
     */
    public MomentPhoto createMoment(MomentDto momentDto) {
        Moment moment = new Moment();
        moment.setTitle(moment.getTitle());
        moment.setContent(moment.getContent());
        moment.setDate(moment.getDate());
        moment.setUserId(moment.getUserId());
        moment.setHashTag(moment.getHashTag());
        Moment savedMoment = momentRepository.save(moment);

        MomentPhoto momentPhoto = new MomentPhoto();
        try {
            for (MultipartFile multipartFile : momentDto.getMultipartFiles()) {
                String filename = UUID.randomUUID().toString() + ".jpg";
                String mediaLink = fireBaseService.uploadFiles(multipartFile, filename);

                momentPhoto.setMomentId(savedMoment.getId());
                // *********
                momentPhoto.setFileName(filename);
                momentPhoto.setFileName(mediaLink);
                // *********
                momentPhotoRepository.save(momentPhoto);
            }
        }catch(Exception e){
            System.out.println(e.getStackTrace());
            return momentPhoto;
        }
        return momentPhoto;
    }

    /**
     * 모먼트 삭제하기
     */
    public DeleteDto deleteMoment(Optional<User> findUser, Long id) {
        Moment findMoment = momentRepository.findById(id).get();

        DeleteDto deleteDto = new DeleteDto();
        if(!(findMoment.getUserId()==findUser.get().getId())){ // 자신의 모먼트가 아닌 것을 삭제하려고 하는 경우
            deleteDto.setDeleted(false);
            deleteDto.setMessage("자신의 모먼트만 삭제할 수 있습니다. ");
            return deleteDto;
        }
        List<MomentPhoto> momentPhotoList = momentPhotoRepository.findAllByMomentId(id);
        int count = 0;
        int len = momentPhotoList.size();
        momentRepository.deleteById(findMoment.getId());
        for(MomentPhoto momentPhoto: momentPhotoList){
            try {
                fireBaseService.deleteFiles(momentPhoto.getFileName()); // 파이어베이스에서 이미지 삭제
                momentPhotoRepository.deleteById(momentPhoto.getId());  // 디비에서 모먼트 사진 정보 삭제
            }catch(Exception e){
                System.out.println(e.getMessage());
                continue;
            }
            count++;
        }
        if(count == len){
            deleteDto.setDeleted(true);
            deleteDto.setMessage("성공적으로 삭제되었습니다.");
            return deleteDto;
        }
        deleteDto.setDeleted(false);
        deleteDto.setMessage("모먼트를 삭제할 수 없습니다.");
        return deleteDto;
    }

    /**
     * 해시태그로 모먼트 검색하기
     * @param tag
     * @return
     */
    public List<MomentRetDto> getMomentByHashTag(String tag) {
        List<MomentRetDto> momentRetDtoList = new ArrayList<>();
        List<Moment> momentList;
        try {
            momentList = momentRepository.findByHashTagContaining(tag);
        }catch(Exception e){
            System.out.println(e.getStackTrace());
            return momentRetDtoList;
        }
        for(Moment moment: momentList){
            momentRetDtoList.add(getMoment(moment.getId()));
        }
        return momentRetDtoList;
    }
}
