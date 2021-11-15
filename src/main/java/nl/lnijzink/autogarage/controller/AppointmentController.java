package nl.lnijzink.autogarage.controller;

import nl.lnijzink.autogarage.dto.AppointmentDto;
import nl.lnijzink.autogarage.dto.CarDto;
import nl.lnijzink.autogarage.dto.CustomerDto;
import nl.lnijzink.autogarage.model.Appointment;
import nl.lnijzink.autogarage.model.Customer;
import nl.lnijzink.autogarage.reposit.AppointmentRepository;
import nl.lnijzink.autogarage.service.AppointmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final AppointmentRepository appointmentRepository;

    protected AppointmentController(AppointmentService appointmentService, AppointmentRepository appointmentRepository){
        this.appointmentService = appointmentService;
        this.appointmentRepository = appointmentRepository;}

    @GetMapping("/")
    public String getAppointments(Model model) {
        var appointments = appointmentService.getAppointments();
        model.addAttribute("listofAppointments", appointments);
        return "AppointmentsList";
    }

    @GetMapping("/create")
    public String createAppointment(Model model){
        model.addAttribute("Appointment", new AppointmentDto());
        return "AppointmentForm";
    }

    @PostMapping("/create")
    public String createAppointment(@Valid @ModelAttribute("Appointment") AppointmentDto appointmentDto,
                    BindingResult bindingResult){
        appointmentService.createAppointment(appointmentDto);
        return "AppointmentDisplay";
    }

    @GetMapping("/update/{id}")
    public String updateAppointment(@PathVariable("id") Long id, Model model) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid appointment Id:" + id));
        model.addAttribute("appointment", appointment);
        return "AppointmentUpdate";
    }

    @PostMapping("/update/{id}")
    public String updateAppointment(@PathVariable("id") Long id, @Valid Appointment appointment,
                                 BindingResult result, Model model) {
        if (result.hasErrors()) {
            appointment.setId(id);
            return "AppointmentUpdate";
        }
        appointmentRepository.save(appointment);
        return "redirect:/appointments/";
    }

    @GetMapping("/delete/{id}")
    public String deleteAppointment(@PathVariable("id") Long id, Model model) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid appointment Id:" + id));
        appointmentRepository.delete(appointment);
        return "redirect:/appointments/";
    }


 }

