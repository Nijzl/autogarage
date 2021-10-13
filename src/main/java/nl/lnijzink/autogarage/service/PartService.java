package nl.lnijzink.autogarage.service;

import nl.lnijzink.autogarage.dto.PartDto;

import java.util.List;

public interface PartService {
    public long createPart(PartDto odto);
    public PartDto getPart(long id);
    public List<PartDto> getParts();
}
