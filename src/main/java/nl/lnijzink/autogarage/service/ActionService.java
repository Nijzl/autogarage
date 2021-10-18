package nl.lnijzink.autogarage.service;

import nl.lnijzink.autogarage.dto.ActionDto;

import java.util.List;

public interface ActionService {
        public long addAction(ActionDto gdto);
        public ActionDto getAction(long id);
        public List<ActionDto> getActions();
    }

