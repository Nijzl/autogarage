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

    @Override
    public List<PartDto> getParts(){
        ArrayList<PartDto> pList = new ArrayList<>();
        partRepository.findAll().forEach((p) -> pList.add(new PartDto(p.getId(), p.getName(), p.getPrice(), p.getQuantity())));
        return pList;
    }

    @Override
    public PartDto getPart(Long id){
        Part p = partRepository.findById(id).get();
        return new PartDto(p.getId(), p.getName(), p.getPrice(), p.getQuantity());
    }

    @Override
    public long createPart(PartDto odto){
        Part o = new Part(odto.getName(), odto.getPrice(), odto.getQuantity());
        partRepository.save(o);
        return o.getId();
    }

    @Override
    public void deletePart(Long id){
        boolean exists = partRepository.existsById(id);
        if(exists){
            partRepository.deleteById(id);
        }
    }




}
