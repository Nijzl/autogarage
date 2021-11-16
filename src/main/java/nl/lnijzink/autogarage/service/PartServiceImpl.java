package nl.lnijzink.autogarage.service;

import nl.lnijzink.autogarage.dto.PartDto;
import nl.lnijzink.autogarage.model.Part;
import nl.lnijzink.autogarage.reposit.PartRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PartServiceImpl implements PartService {

    private final PartRepository partRepository;

    public PartServiceImpl(PartRepository partRepository){this.partRepository = partRepository;}


    // Get List of Parts
    @Override
    public List<PartDto> getParts(){
        ArrayList<PartDto> pList = new ArrayList<>();
        partRepository.findAll().forEach((p) -> pList.add(new PartDto(p.getId(), p.getName(), p.getPrice(), p.getQuantity())));
        return pList;
    }

    // Get Single Part
    @Override
    public PartDto getPart(Long id){
        Part p = partRepository.findById(id).get();
        return new PartDto(p.getId(), p.getName(), p.getPrice(), p.getQuantity());
    }

    // Create New Part
    @Override
    public Long createPart(PartDto odto){
        Part o = new Part(odto.getName(), odto.getPrice(), odto.getQuantity());
        partRepository.save(o);
        return o.getId();
    }

    // Delete Part
    @Override
    public void deletePart(Long id){
        boolean exists = partRepository.existsById(id);
        if(exists){
            partRepository.deleteById(id);
        }
    }

}
