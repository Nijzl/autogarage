package nl.lnijzink.autogarage.service;

import nl.lnijzink.autogarage.dto.ActionDto;
import nl.lnijzink.autogarage.model.Action;
import nl.lnijzink.autogarage.reposit.ActionRepository;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class ActionServiceImpl implements ActionService{

    private final ActionRepository repos;

    public ActionServiceImpl(ActionRepository repos){this.repos = repos;}

    @Override
    public long addAction(ActionDto gdto){
        Action a = new Action(gdto.getName(), gdto.getDescription(), gdto.getPrice());
        repos.save(a);
        return a.getId();
    }

    @Override
    public ActionDto getAction(long id){
        Action b = repos.findById(id).get();
        return new ActionDto(b.getId(), b.getName(), b.getDescription(), b.getPrice());
    }

    @Override
    public List<ActionDto> getActions(){
        ArrayList<ActionDto> pList = new ArrayList<>();
        repos.findAll().forEach((p) -> pList.add(new ActionDto(p.getId(), p.getName(), p.getDescription(), p.getPrice()
        )));
        return pList;
    }


}
