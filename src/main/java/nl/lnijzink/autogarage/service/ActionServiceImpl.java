package nl.lnijzink.autogarage.service;

import nl.lnijzink.autogarage.dto.ActionDto;
import nl.lnijzink.autogarage.model.Action;
import nl.lnijzink.autogarage.reposit.ActionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ActionServiceImpl implements ActionService {

    private final ActionRepository actionRepository;

    public ActionServiceImpl(ActionRepository actionRepository){this.actionRepository = actionRepository;}


    // Get List of Actions
    @Override
    public List<ActionDto> getActions(){
        ArrayList<ActionDto> pList = new ArrayList<>();
        actionRepository.findAll().forEach((p) -> pList.add(new ActionDto(p.getId(), p.getName(), p.getDescription(), p.getPrice()
        )));
        return pList;
    }

    // Get Single Action
    @Override
    public ActionDto getAction(Long id){
        Action b = actionRepository.findById(id).get();
        return new ActionDto(b.getId(), b.getName(), b.getDescription(), b.getPrice());
    }

    // Create New Action
    @Override
    public Long createAction(ActionDto gdto){
        Action a = new Action(gdto.getName(), gdto.getDescription(), gdto.getPrice());
        actionRepository.save(a);
        return a.getId();
    }

    //Delete Action
    @Override
    public void deleteAction(Long id){
        boolean exists = actionRepository.existsById(id);
        if(exists){
            actionRepository.deleteById(id);
        }
    }

}
