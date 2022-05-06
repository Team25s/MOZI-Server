package mozi.mozispring.Service;

import mozi.mozispring.Domain.Dto.MomentRetDto;
import mozi.mozispring.Domain.Moment;
import mozi.mozispring.Domain.MomentPhoto;
import mozi.mozispring.Repository.MomentPhotoRepository;
import mozi.mozispring.Repository.MomentRepository;
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
    public MomentRetDto getMoment(Long momentId){
        Moment findMoment = momentRepository.findById(momentId).get();
        List<MomentPhoto> momentPhotoList = momentPhotoRepository.findAllByMomentId(findMoment.getId());
        MomentRetDto momentRetDto = new MomentRetDto();
        momentRetDto.setTitle(findMoment.getTitle());
        momentRetDto.setContent(findMoment.getContent());
        momentRetDto.setDate(findMoment.getDate());
        momentRetDto.setUserId(findMoment.getUserId());
        momentRetDto.setHashTag(findMoment.getHashTag());

        List<String> fileNameList = new ArrayList<>();
        for(MomentPhoto momentPhoto : momentPhotoList){
            fileNameList.add(momentPhoto.getFileName());
        }
        momentRetDto.setFileNameList(fileNameList);
        return momentRetDto;
    }
}
