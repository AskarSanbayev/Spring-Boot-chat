package com.chat.cyber.service.impl;

import com.chat.cyber.model.RefsValues;
import com.chat.cyber.model.enums.RefsCodeName;
import com.chat.cyber.model.enums.RefsValuesCodeName;
import com.chat.cyber.repo.RefsValuesRepo;
import com.chat.cyber.service.RefsValuesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RefsValuesServiceImpl implements RefsValuesService {

    @Autowired
    private RefsValuesRepo refsValuesRepo;

    @Override
    public List<RefsValues> findByRefsCodeName(String codeName) {
        return refsValuesRepo.findByRefsCodeName(codeName);
    }

    @Override
    public Optional<RefsValues> findByIdAndRefsCodeName(Long valueId, RefsCodeName refCodeName) {
        return Optional.empty();
    }

    @Override
    public Optional<RefsValues> findCodeNameAndRefsCodeName(RefsValuesCodeName codeName, RefsCodeName refCodeName) {
        return refsValuesRepo.findByCodeNameAndRefsCodeName(codeName.getValue(), refCodeName.getValue());
    }
}
