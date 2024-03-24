package com.example.prog4gradle.controller;

import com.example.prog4gradle.modele.Employee;
import com.example.prog4gradle.repository.entity.EmployeeEntity;
import com.example.prog4gradle.service.EmployeeService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

@Controller
@AllArgsConstructor
public class EmployeeController {
    private EmployeeService service ;
    @GetMapping("/add")
    public String getEmployees(Model model){
        model.addAttribute("employeeToAdd", new Employee() );
        return "form";
    };
    @PostMapping("/addEmployee")
    public String addEmployee(@RequestParam("imageFile") MultipartFile multipartFile,
                              @ModelAttribute("employeeToAdd") Employee employee) throws IOException {
        byte[] imageByte = multipartFile.getBytes();
        String image = Base64.getEncoder().encodeToString(imageByte);
        employee.setImage(image);
        service.addEmployee(employee);
        return "redirect:/add";
    }

    @GetMapping("/infoPerso/{id}")
    public String infoEmployee(@PathVariable Long id, Model model){
        EmployeeEntity employee = service.getById(id);
        model.addAttribute("employeeInfo", employee);
        return "infoEmployee";
    }

    @GetMapping("/infoPerso/{id}/pdf")
    public ResponseEntity<InputStreamResource> generatePdfForEmployee(@PathVariable(name = "id") Long id) {
        EmployeeEntity employee = service.getById(id);

        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        Context context = new Context();
        context.setVariable("employeeInfo", employee);

        String html = templateEngine.process("infoEmployee", context);

        byte[] pdfBytes = generatePdfFromHtml(html);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("inline", "employee_" + id + ".pdf");

        InputStreamResource inputStreamResource = new InputStreamResource(new ByteArrayInputStream(pdfBytes));

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(inputStreamResource);
    }

    private byte[] generatePdfFromHtml(String html) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(html);
            renderer.layout();
            renderer.createPDF(outputStream);

            outputStream.close();

            return outputStream.toByteArray();
        } catch (Exception e) {
            System.err.println("Error generating PDF: " + e.getMessage());
            return new byte[0];
        }
    }


    @GetMapping("/info")
    public String showEmployees(@RequestParam(value = "Finder", required = false)String finder,
                                @RequestParam(value = "DateFinder", required = false)String  dateFinder,
                                @RequestParam(value = "sortByName", required = false, defaultValue = "false") String sortByName,
                                @RequestParam(value = "sortDirection", required = false, defaultValue = "asc") String sortDirection,
                                Model model){
        List<Employee> employees;
        if (finder != null && !finder.isEmpty()){
            employees = service.getByFinder(finder);
        }else if(dateFinder != null && !dateFinder.isEmpty()) {
            employees = service.getByDateFinder(dateFinder);
        }else {
            employees = service.getEmployees();
        }

        if (sortByName == "true") {
            if ("asc".equals(sortDirection)) {
                employees.sort(Comparator.comparing(Employee::getFirstName));
            } else if ("desc".equals(sortDirection)) {
                employees.sort(Comparator.comparing(Employee::getFirstName).reversed());
            }
        }

        model.addAttribute("sortByName", sortByName);
        model.addAttribute("sortDirection", sortDirection);
        model.addAttribute("employeeToShow", employees);
        return "listEmployee";
    }
    @GetMapping("/export/csv")
    public void exportCSV(@RequestParam(value = "Finder", required = false)String finder,
                          @RequestParam(value = "DateFinder", required = false)String  dateFinder,
                            HttpServletResponse response)throws IOException {
        List<Employee> employees;
        if(finder != null && !finder.isEmpty()) {
            employees = service.getByFinder(finder);
        }else if(dateFinder != null && !dateFinder.isEmpty() ) {
            employees = service.getByDateFinder(dateFinder);
        }else {
            employees = service.getEmployees();
        }
        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=Employees.csv");

        CSVPrinter csvPrinter = new CSVPrinter(response.getWriter(), CSVFormat.DEFAULT.withHeader("FirstName","LastName","Post","Gender"));
        for (Employee employee : employees){
            csvPrinter.printRecord(employee.getFirstName(),employee.getLastName(),employee.getPost(),employee.getSex());
        }
        csvPrinter.close();

    }

    @GetMapping("/updatePage/{id}")
    public String getUpdatePage(@PathVariable Long id , Model model){
        model.addAttribute("employeeToUpdate", service.getById(id));
        return "update";
    };

    @PostMapping("/update/{id}")
    public String updateEmployee( @RequestParam("imageFileUpdate") MultipartFile multipartFile,
                                    @PathVariable Long id , @ModelAttribute("employeeToUpdate") Employee employee) throws IOException {
        byte[] imageByte = multipartFile.getBytes();
        String image = Base64.getEncoder().encodeToString(imageByte);
        employee.setImage(image);
        service.updateEmployee(id , employee);
        return "redirect:/add";
    }


    @GetMapping("/loginPage")
    public String showLoginPage(Model model){
        model.addAttribute("error","Invalid credentials");
        return "login";
    }

    @PostMapping("/login")
    public String login(String firstName , String password,Model model, HttpSession session){
        EmployeeEntity employee = service.getByFirstName(firstName);
        if(employee != null && password.equals(employee.getPassword())){
            session.setAttribute("employeeOK", employee );
            return "redirect:/add";
        }else {
            model.addAttribute("error", "Invalid credentials");
            return "redirect:/loginPage";
        }
    }

}
