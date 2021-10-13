package nl.lnijzink.autogarage.service;

import nl.lnijzink.autogarage.dto.PartDto;
import nl.lnijzink.autogarage.model.Part;
import nl.lnijzink.autogarage.reposit.PartRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PartServiceImpl implements PartService {

    private final PartRepository repos;

    public PartServiceImpl(PartRepository repos){this.repos = repos;}

    @Override
    public long createPart(PartDto odto){
        Part o = new Part(odto.getName(), odto.getPrice(), odto.getQuantity());
        repos.save(o);
        return o.getId();
    }

    @Override
    public PartDto getPart(long id){
        Part p = repos.findById(id).get();
        return new PartDto(p.getId(), p.getName(), p.getPrice(), p.getQuantity());
    }

    @Override
    public List<PartDto> getParts(){
        ArrayList<PartDto> pList = new ArrayList<>();
        repos.findAll().forEach((p) -> pList.add(new PartDto(p.getId(), p.getName(), p.getPrice(), p.getQuantity())));
        return pList;
    }
}
