package nl.lnijzink.autogarage.service;

import nl.lnijzink.autogarage.dto.AppointmentDto;
import nl.lnijzink.autogarage.dto.CustomerDto;
import nl.lnijzink.autogarage.model.Appointment;
import nl.lnijzink.autogarage.reposit.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService{

    private final AppointmentRepository appointmentRepository;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository){this.appointmentRepository =
            appointmentRepository;}

    @Override
    public Long createAppointment(AppointmentDto appointmentDto) {
        Appointment appointment = new Appointment(appointmentDto.getDate());
        appointmentRepository.save(appointment);
        return appointment.getId();
    }

    @Override
    public AppointmentDto getAppointment(Long id) {
        Optional<Appointment> appointment = appointmentRepository.findById(id);
        if (appointment.isPresent()){
            return new AppointmentDto(appointment.get().getId(), appointment.get().getDate());}
        else{
            return new AppointmentDto();
        }
    }

    @Override
    public List<AppointmentDto> getAppointments() {
            ArrayList<AppointmentDto> appointmentList = new ArrayList<>();
            appointmentRepository.findAll().forEach((appointment) -> appointmentList.add(new AppointmentDto(appointment.getId(),
                    appointment.getDate()
            )));
            return appointmentList;
    }

}
