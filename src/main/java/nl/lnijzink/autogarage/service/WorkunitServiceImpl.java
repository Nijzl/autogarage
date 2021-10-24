package nl.lnijzink.autogarage.service;

import nl.lnijzink.autogarage.dto.WorkunitDto;
import nl.lnijzink.autogarage.model.Workunit;
import nl.lnijzink.autogarage.reposit.WorkunitRepository;

import java.util.Optional;

public class WorkunitServiceImpl implements WorkunitService{
    private final WorkunitRepository repos;

    public WorkunitServiceImpl(WorkunitRepository repos){this.repos = repos;}

    @Override
    public long createWorkunit(WorkunitDto dto){
        Workunit workunit = new Workunit(dto.getType(), dto.getCar(), dto.getMechanic());
        repos.save(workunit);
        return workunit.getId();
    }

    @Override
    public WorkunitDto getWorkunit(long id){
        Optional<Workunit> workunit = repos.findById(id);
        if(workunit.isPresent()) {
            return new WorkunitDto(workunit.get().getType());
        }else {
            return new WorkunitDto();
        }
    }
}
