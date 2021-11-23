package nl.lnijzink.autogarage.service;

import nl.lnijzink.autogarage.dto.AppointmentDto;
import nl.lnijzink.autogarage.model.Appointment;
import nl.lnijzink.autogarage.model.Car;
import nl.lnijzink.autogarage.reposit.AppointmentRepository;
import nl.lnijzink.autogarage.reposit.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AppointmentServiceImplTest {

    @Mock
    private AppointmentRepository mockAppointmentRepository;
    @Mock
    private CarRepository mockCarRepository;

    private AppointmentServiceImpl appointmentServiceImplUnderTest;

    @BeforeEach
    void setUp() {
        appointmentServiceImplUnderTest = new AppointmentServiceImpl(mockAppointmentRepository, mockCarRepository);
    }

    @Test
    void testGetAppointments() {
        final List<Appointment> appointments = List.of(new Appointment("date", "time", "type"));
        when(mockAppointmentRepository.findAll()).thenReturn(appointments);
        final List<AppointmentDto> result = appointmentServiceImplUnderTest.getAppointments();
    }

    @Test
    void testGetAppointments_AppointmentRepositoryReturnsNoItems() {
        when(mockAppointmentRepository.findAll()).thenReturn(Collections.emptyList());
        final List<AppointmentDto> result = appointmentServiceImplUnderTest.getAppointments();
    }

    @Test
    void testGetAppointment() {
        final Optional<Appointment> appointment = Optional.of(new Appointment("date", "time", "type"));
        when(mockAppointmentRepository.findById(0L)).thenReturn(appointment);
        final AppointmentDto result = appointmentServiceImplUnderTest.getAppointment(0L);
    }

    @Test
    void testGetAppointment_AppointmentRepositoryReturnsAbsent() {
        when(mockAppointmentRepository.findById(0L)).thenReturn(Optional.empty());
        final AppointmentDto result = appointmentServiceImplUnderTest.getAppointment(0L);
    }

    @Test
    void testCreateAppointment() {
        final AppointmentDto appointmentDto = new AppointmentDto(0L, "date", "time", "type", new Car("licencePlate", "brand", "model", 2020));
        when(mockAppointmentRepository.save(any(Appointment.class))).thenReturn(new Appointment("date", "time", "type"));
        final Long result = appointmentServiceImplUnderTest.createAppointment(appointmentDto);
        assertThat(result).isEqualTo(0L);
        verify(mockAppointmentRepository).save(any(Appointment.class));
    }

    @Test
    void testDeleteAppointment() {
        when(mockAppointmentRepository.existsById(0L)).thenReturn(false);
        appointmentServiceImplUnderTest.deleteAppointment(0L);
        verify(mockAppointmentRepository).deleteById(0L);
    }

    @Test
    void testAssignAppointmentToCar() {
        final Optional<Car> car = Optional.of(new Car("licencePlate", "brand", "model", 2020));
        when(mockCarRepository.findById("id")).thenReturn(car);
        final Optional<Appointment> appointment = Optional.of(new Appointment("date", "time", "type"));
        when(mockAppointmentRepository.findById(0L)).thenReturn(appointment);
        when(mockAppointmentRepository.save(any(Appointment.class))).thenReturn(new Appointment("date", "time", "type"));
        final ResponseEntity<Object> result = appointmentServiceImplUnderTest.assignAppointmentToCar("licencePlate", 0L);
        verify(mockAppointmentRepository).save(any(Appointment.class));
    }

    @Test
    void testAssignAppointmentToCar_CarRepositoryReturnsAbsent() {
        when(mockCarRepository.findById("id")).thenReturn(Optional.empty());
        final Optional<Appointment> appointment = Optional.of(new Appointment("date", "time", "type"));
        when(mockAppointmentRepository.findById(0L)).thenReturn(appointment);
        when(mockAppointmentRepository.save(any(Appointment.class))).thenReturn(new Appointment("date", "time", "type"));
        final ResponseEntity<Object> result = appointmentServiceImplUnderTest.assignAppointmentToCar("licencePlate", 0L);
        verify(mockAppointmentRepository).save(any(Appointment.class));
    }

    @Test
    void testAssignAppointmentToCar_AppointmentRepositoryFindByIdReturnsAbsent() {
        final Optional<Car> car = Optional.of(new Car("licencePlate", "brand", "model", 2020));
        when(mockCarRepository.findById("id")).thenReturn(car);
        when(mockAppointmentRepository.findById(0L)).thenReturn(Optional.empty());
        when(mockAppointmentRepository.save(any(Appointment.class))).thenReturn(new Appointment("date", "time", "type"));
        final ResponseEntity<Object> result = appointmentServiceImplUnderTest.assignAppointmentToCar("licencePlate", 0L);
        verify(mockAppointmentRepository).save(any(Appointment.class));
    }

}
