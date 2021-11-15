package nl.lnijzink.autogarage.service;

import nl.lnijzink.autogarage.dto.PartDto;

import java.util.List;

public interface PartService {

    public Long createPart(PartDto odto);
    public PartDto getPart(Long id);
    public List<PartDto> getParts();
    public void deletePart(Long id);

}
