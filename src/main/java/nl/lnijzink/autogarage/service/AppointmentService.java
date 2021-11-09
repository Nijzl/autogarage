package nl.lnijzink.autogarage.service;

import nl.lnijzink.autogarage.dto.AppointmentDto;
import nl.lnijzink.autogarage.dto.CarDto;

import java.util.List;

public interface AppointmentService {
    public String createAppointment(AppointmentDto appointmentDto);
    public AppointmentDto getAppointment(Integer id);
    public List<AppointmentDto> getAppointments();
}
