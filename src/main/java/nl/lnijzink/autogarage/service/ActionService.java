package nl.lnijzink.autogarage.service;

import nl.lnijzink.autogarage.dto.ActionDto;

import java.util.List;

public interface ActionService {

        public Long createAction(ActionDto gdto);
        public ActionDto getAction(Long id);
        public List<ActionDto> getActions();

    }

