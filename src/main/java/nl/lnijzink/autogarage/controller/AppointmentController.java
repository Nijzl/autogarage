package nl.lnijzink.autogarage.controller;

import nl.lnijzink.autogarage.dto.AppointmentDto;
import nl.lnijzink.autogarage.dto.CarDto;
import nl.lnijzink.autogarage.dto.CustomerDto;
import nl.lnijzink.autogarage.model.Appointment;
import nl.lnijzink.autogarage.model.Customer;
import nl.lnijzink.autogarage.reposit.AppointmentRepository;
import nl.lnijzink.autogarage.reposit.CarRepository;
import nl.lnijzink.autogarage.service.AppointmentService;
import nl.lnijzink.autogarage.service.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.naming.Context;
import javax.validation.Valid;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final AppointmentRepository appointmentRepository;
    private final CarService carService;
    private final CarRepository carRepository;

    protected AppointmentController(AppointmentService appointmentService,
                                    AppointmentRepository appointmentRepository, CarService carService,
                                    CarRepository carRepository){
        this.appointmentService = appointmentService;
        this.appointmentRepository = appointmentRepository;
        this.carService = carService;
        this.carRepository = carRepository;}

    // Get List of Appointments
    @GetMapping("/")
    public String getAppointments(Model model) {
        var appointments = appointmentService.getAppointments();
        model.addAttribute("listofAppointments", appointments);
        return "AppointmentsList";
    }

    // Get Single Appointment
    @GetMapping("/view/{id}")
    public String getAppointment(@PathVariable("id") Long id, Model model) {
        AppointmentDto appointment = appointmentService.getAppointment(id);
        model.addAttribute("appointment", appointment);
        return "AppointmentGet";
    }

    // Create New Appointment
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

    // Update Appointment
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

    // Delete Appointment
    @GetMapping("/delete/{id}")
    public String deleteAppointment(@PathVariable("id") Long id, Model model) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid appointment Id:" + id));
        appointmentRepository.delete(appointment);
        return "redirect:/appointments/";
    }

    // Assign Appointment to Car
    @GetMapping("/car")
    public String assignAppointmentToCar(Model model){
        model.addAttribute("Car", new CarDto());
        model.addAttribute("Appointment", new AppointmentDto());
        model.addAttribute("listOfCars", carRepository.findAll());
        model.addAttribute("listOfAppointments", appointmentRepository.findAll());
        return "LinkCarAndAppointment";
    }

    @PostMapping("/car")
    public String assignAppointmentToCar(@RequestParam String licencePlate,
                                      @RequestParam Long id) {
        appointmentService.assignAppointmentToCar(licencePlate, id);
        return "LinkCarAndAppointmentSuccessful";
    }

 }

