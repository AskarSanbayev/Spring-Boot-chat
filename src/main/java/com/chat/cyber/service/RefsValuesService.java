package com.chat.cyber.service;

import com.chat.cyber.model.RefsValues;
import com.chat.cyber.model.enums.RefsCodeName;
import com.chat.cyber.model.enums.RefsValuesCodeName;

import java.util.List;
import java.util.Optional;

public interface RefsValuesService {
    List<RefsValues> findByRefsCodeName(String codeName);

    Optional<RefsValues> findByIdAndRefsCodeName(Long valueId, RefsCodeName refCodeName);

    Optional<RefsValues> findCodeNameAndRefsCodeName(RefsValuesCodeName codeName, RefsCodeName refCodeName);
}
