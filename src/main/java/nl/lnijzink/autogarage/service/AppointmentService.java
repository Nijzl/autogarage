package nl.lnijzink.autogarage.service;

import nl.lnijzink.autogarage.dto.AppointmentDto;
import nl.lnijzink.autogarage.dto.CarDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AppointmentService {

    public Long createAppointment(AppointmentDto appointmentDto);
    public AppointmentDto getAppointment(Long id);
    public List<AppointmentDto> getAppointments();
    public void deleteAppointment(Long id);
    public ResponseEntity assignAppointmentToCar(String licencePlate, Long id);
}
