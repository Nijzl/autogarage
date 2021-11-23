package nl.lnijzink.autogarage.service;

import nl.lnijzink.autogarage.dto.AppointmentDto;
import nl.lnijzink.autogarage.model.Appointment;
import nl.lnijzink.autogarage.reposit.AppointmentRepository;
import nl.lnijzink.autogarage.reposit.CarRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final CarRepository carRepository;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, CarRepository carRepository){
        this.appointmentRepository = appointmentRepository;
        this.carRepository = carRepository;
    }


    //Get List of Appointments
    @Override
    public List<AppointmentDto> getAppointments() {
        ArrayList<AppointmentDto> appointmentList = new ArrayList<>();
        appointmentRepository.findAll().forEach((appointment) -> appointmentList.add(new AppointmentDto(appointment.getId(),
                appointment.getDate(), appointment.getTime(), appointment.getType(), appointment.getCar()
        )));
        return appointmentList;
    }

    // Get Single Appointment
    @Override
    public AppointmentDto getAppointment(Long id) {
        Optional<Appointment> appointment = appointmentRepository.findById(id);
        if (appointment.isPresent()){
            return new AppointmentDto(appointment.get().getId(), appointment.get().getDate(),
                    appointment.get().getTime(), appointment.get().getType(), appointment.get().getCar());}
        else{
            return new AppointmentDto();
        }
    }

    // Create New Appointment
    @Override
    public Long createAppointment(AppointmentDto appointmentDto) {
        Appointment appointment = new Appointment(appointmentDto.getDate(), appointmentDto.getTime(),
                appointmentDto.getType());
        appointmentRepository.save(appointment);
        return appointment.getId();
    }

    // Delete Appointment
    @Override
    public void deleteAppointment(Long id){
        boolean exists = appointmentRepository.existsById(id);
        if(exists){
            appointmentRepository.deleteById(id);
        }
    }

    // Assign Appointment to Car
    @Override
    public ResponseEntity<Object> assignAppointmentToCar(String licencePlate, Long id) {
        var optionalCar = carRepository.findById(licencePlate);
        var optionalAppointment = appointmentRepository.findById(id);

        if (optionalCar.isPresent() && optionalAppointment.isPresent()) {
            var car = optionalCar.get();
            var appointment = optionalAppointment.get();
            appointment.setCar(car);
            appointmentRepository.save(appointment);
        }
        return ResponseEntity.ok("Success");
    }

}
