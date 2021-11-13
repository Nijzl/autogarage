package nl.lnijzink.autogarage.controller;

import nl.lnijzink.autogarage.dto.AppointmentDto;
import nl.lnijzink.autogarage.dto.CustomerDto;
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

    protected AppointmentController(AppointmentService appointmentService){this.appointmentService =
            appointmentService;}

    @GetMapping("/")
    public String getAppointments(Model model) {
        var appointments = appointmentService.getAppointments();
        model.addAttribute("listofAppointments", appointments);
        return "AppointmentsList";
    }

    @GetMapping("/{id}")
    public AppointmentDto getAppointment(@PathVariable Long id){
        return appointmentService.getAppointment(id);
    }

    @GetMapping("/create")
    public String createAppointment(Model model){
        model.addAttribute("Appointment", new AppointmentDto());
        return "AppointmentForm";
    }

    @PostMapping("/create")
    public String createAppointment(@Valid @ModelAttribute("Appointment") AppointmentDto appointmentDto,
                    BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "AppointmentForm";
        }
        appointmentService.createAppointment(appointmentDto);
        return "AppointmentDisplay";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteAppointment(@PathVariable("appointmentId") Long id){
        appointmentService.deleteAppointment(id);
        return "AppointmentDeleteDisplay";
    }

 }

