package nl.lnijzink.autogarage.service;

import nl.lnijzink.autogarage.dto.AppointmentDto;
import nl.lnijzink.autogarage.dto.CarDto;

import java.util.List;

public interface AppointmentService {

    public Long createAppointment(AppointmentDto appointmentDto);
    public AppointmentDto getAppointment(Long id);
    public List<AppointmentDto> getAppointments();
    public void deleteAppointment(Long id);

}
