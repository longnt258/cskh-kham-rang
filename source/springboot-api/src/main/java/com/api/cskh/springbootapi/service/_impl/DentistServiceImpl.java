package com.api.cskh.springbootapi.service._impl;

import com.api.cskh.springbootapi.common.utils.DateUtil;
import com.api.cskh.springbootapi.common.utils.LogUtil;
import com.api.cskh.springbootapi.domain.Dentist;
import com.api.cskh.springbootapi.dto.DentistDTO;
import com.api.cskh.springbootapi.repository.DentistRepository;
import com.api.cskh.springbootapi.service.DentistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DentistServiceImpl implements DentistService {
    private final DentistRepository dentistRepository;

    @Override
    public List<DentistDTO> findAll() {
        LogUtil.logger.info("Find all Dentist");
        List<DentistDTO> dentistResultList = new LinkedList<>();
        List<Dentist> dentists = dentistRepository.findAll();
        dentists.sort(Comparator.comparing(Dentist::getCreatedDate));
        dentists.forEach(d -> {
            dentistResultList.add(new DentistDTO(d));
        });
        return dentistResultList;
    }

    @Override
    public DentistDTO findById(Integer dentistId) {
        LogUtil.logger.info("Find Dentist by id");
        DentistDTO dentistDTO = null;
        try {
            dentistDTO = new DentistDTO(dentistRepository.findDentistByDentistId(dentistId));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dentistDTO;
    }

    @Override
    public DentistDTO createByAdmin(DentistDTO dentistDTO) {
        LogUtil.logger.info("Create new Dentist");
        try {
            Dentist dentist = Dentist.builder()
                    .fullName(dentistDTO.getFullName())
                    .status(true)
                    .startDatetime(dentistDTO.getStartDateTime())
                    .endDatetime(dentistDTO.getEndDateTime())
                    .schedules(new ArrayList<>())
                    .build();
            return new DentistDTO(dentistRepository.save(dentist));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /* Update cũng dùng trong xoá Dentist (chỉ disable, ko xoá trong db), chỉ cần add thuộc tính status = false trong dentistDTO */
    @Override
    public DentistDTO update(Integer dentistId, DentistDTO dentistDTO) {
        LogUtil.logger.info("Update Dentist by id: " + dentistId);
        try {
            Dentist dentist = dentistRepository.findDentistByDentistId(dentistId);

            /* Kiểm tra thông tin tránh bị null */
            if(dentistDTO.getFullName() != null) {
                dentist.setFullName(dentistDTO.getFullName());
            }
            if(dentistDTO.getStartDateTime() != null) {
                dentistDTO.setStartDateTime(DateUtil.convertString2Date(dentistDTO.getStartDateTimeString()));
                dentist.setStartDatetime(dentistDTO.getStartDateTime());
            }
            if(dentistDTO.getEndDateTime() != null) {
                dentistDTO.setEndDateTime(DateUtil.convertString2Date(dentistDTO.getEndDateTimeString()));
                dentist.setEndDatetime(dentistDTO.getEndDateTime());
            }
            if(dentistDTO.getStatus() != null) { // just set True for status to switch
                dentist.setStatus(!dentist.getStatus());
            }

            return new DentistDTO(dentistRepository.save(dentist));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
