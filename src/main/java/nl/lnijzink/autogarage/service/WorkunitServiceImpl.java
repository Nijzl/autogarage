package nl.lnijzink.autogarage.service;

import nl.lnijzink.autogarage.dto.WorkunitDto;
import nl.lnijzink.autogarage.model.Workunit;
import nl.lnijzink.autogarage.reposit.WorkunitRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WorkunitServiceImpl implements WorkunitService{
    private final WorkunitRepository workunitRepository;

    public WorkunitServiceImpl(WorkunitRepository workunitRepository){this.workunitRepository = workunitRepository;}

    @Override
    public long createWorkunit(WorkunitDto dto){
        Workunit workunit = new Workunit(dto.getType(), dto.getCar(), dto.getMechanic());
        workunitRepository.save(workunit);
        return workunit.getId();
    }

    @Override
    public WorkunitDto getWorkunit(long id){
        Optional<Workunit> workunit = workunitRepository.findById(id);
        if(workunit.isPresent()) {
            return new WorkunitDto(workunit.get().getType());
        }else {
            return new WorkunitDto();
        }
    }
}
