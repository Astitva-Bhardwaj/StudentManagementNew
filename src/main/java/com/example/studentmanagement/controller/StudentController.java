package com.example.studentmanagement.controller;

import com.example.studentmanagement.model.Student;
import com.example.studentmanagement.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Operation(summary = "List all students", description = "Retrieve all students in the system")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved students")
    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public String listStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "list-students";
    }

    @Operation(summary = "Show registration form", description = "Display form for registering a new student")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved students")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/new")
    public String showRegistrationForm(Model model) {
        model.addAttribute("student", new Student());
        return "register-student";
    }

    @Operation(summary = "Register a new student", description = "Register a new student in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Student registered successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid student data")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public String registerStudent(@ModelAttribute Student student) {
        studentService.saveStudent(student);
        return "redirect:/students";
    }

    @Operation(summary = "Get student details", description = "Retrieve the details of a student by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student details retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Student not found")
    })
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{id}")
    public String showStudentDetails(@PathVariable String id, Model model) {
        model.addAttribute("student", studentService.getStudentById(id).orElse(null));
        return "student-details";
    }

    @Operation(summary = "Edit student details", description = "Display form to edit student details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Form displayed successfully"),
            @ApiResponse(responseCode = "404", description = "Student not found")
    })
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model) {
        model.addAttribute("student", studentService.getStudentById(id).orElse(null));
        return "edit-student";
    }

    @Operation(summary = "Update student details", description = "Update the details of an existing student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid student data")
    })
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/{id}")
    public String updateStudent(@PathVariable String id, @ModelAttribute Student student) {
        student.setId(id);
        studentService.saveStudent(student);
        return "redirect:/students";
    }
    @Operation(summary = "Delete a student", description = "Delete a student by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Student deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Student not found")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}/delete")
    public String deleteStudent(@PathVariable String id) {
        studentService.deleteStudent(id);
        return "redirect:/students";
    }
}
