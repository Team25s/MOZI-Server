package mozi.mozispring.Moment;

import mozi.mozispring.Domain.Dto.MomentRetDto;
import mozi.mozispring.Domain.Moment;
import mozi.mozispring.Domain.MomentPhoto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MomentService {

    private MomentRepository momentRepository;
    private MomentPhotoRepository momentPhotoRepository;

    @Autowired
    public MomentService(MomentRepository momentRepository, MomentPhotoRepository momentPhotoRepository) {
        this.momentRepository = momentRepository;
        this.momentPhotoRepository = momentPhotoRepository;
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
}
