package com.smtpbenchmark.controllers;


import java.io.IOException;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import com.smtpbenchmark.entity.BenchmarkSession;
import com.smtpbenchmark.entity.Message;
import com.smtpbenchmark.entity.User;
import com.smtpbenchmark.services.EmailService;
import com.smtpbenchmark.services.LoginService;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;

@Controller
@SessionAttributes("messagesSent")
public class EmailController {
    private EmailService emailService;
    private LoginService loginService;

    public EmailController(EmailService emailService, LoginService loginService) {
        this.emailService = emailService;
        this.loginService = loginService;
    }



    @GetMapping("/")
    public String login(Model model) {
        
        User user = new User();
        model.addAttribute("user", user);
        return "login";
        
    }

    @PostMapping("/send")
public String sendMessage(
    Model model,
    HttpSession session,
    // Rendiamo i parametri opzionali se torniamo dai risultati tramite sessione
    @RequestParam(value = "username", required = false) String username, 
    @RequestParam(value = "password", required = false) String password
) throws MessagingException {
    
    // Se i parametri sono presenti (veniamo dal Login), usiamoli e salviamoli
    if (username != null && password != null) {
        try {
            loginService.login(username, password);
            session.setAttribute("username", username);
            session.setAttribute("password", password);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Credenziali non valide. Accesso negato");
            model.addAttribute("user", new User());
            return "login"; 
        }
    }
    
    // Controllo sicurezza: se non c'è nulla in sessione, nega l'accesso
    if (session.getAttribute("username") == null) {
        return "redirect:/";
    }

    model.addAttribute("message", new Message());
    return "send-message";
}

    @PostMapping("/benchmark-results")
    public String benchmarkResults(
        @RequestParam("object") String object, 
        @RequestParam("message") String message,
        @RequestParam("messagesNumber") Integer messagesNumber,
        @RequestParam("attachment") MultipartFile attachment,
        Model model,
        HttpSession session
        ) throws IOException{
        try{
        ArrayList<Message> messagesSent = new ArrayList<Message>();
        messagesSent = emailService.sendNMessages(messagesNumber,object, message, attachment);
        long min = messagesSent.stream()
                         .mapToLong(Message::getDurationTime)
                         .min()
                         .orElse(0L);
        long max = messagesSent.stream()
                         .mapToLong(Message::getDurationTime)
                         .max()
                         .orElse(0L);
        double avg = messagesSent.stream()
                           .mapToLong(Message::getDurationTime)
                           .average()
                           .orElse(0L);
        // Gestione Cronologia in Sessione
        ArrayList<BenchmarkSession> history = (ArrayList<BenchmarkSession>) session.getAttribute("benchmarkHistory");
        if (history == null) {
            history = new ArrayList<>();
        }
        
        BenchmarkSession summary = new BenchmarkSession(
            object,
            messagesNumber,
            messagesSent.get(0).getSize(),
            min,
            max,
            avg,
            messagesSent.get(0).getAttachment()
        );
        // Aggiungo in cima alla lista per vedere l'ultimo test per primo
        history.add(0, summary); 
        session.setAttribute("benchmarkHistory", history);
        model.addAttribute("object", messagesSent.get(0).getObject());
        model.addAttribute("messagesNumber", messagesNumber);
        model.addAttribute("length", messagesSent.get(0).getSize());
        model.addAttribute("minTime", min);
        model.addAttribute("maxTime", max);
        model.addAttribute("avgTime", avg);
        model.addAttribute("attachment", messagesSent.get(0).getAttachment());
        return "redirect:/results";
    } catch (Exception e) {
        
        model.addAttribute("errorMessage", e);
        model.addAttribute("message", new Message());
        return "send-message"; 
    }
    }

    @GetMapping("/results")
    public String showResults(HttpSession session, Model model) {
        ArrayList<BenchmarkSession> history = (ArrayList<BenchmarkSession>) session.getAttribute("benchmarkHistory");
        

        model.addAttribute("history", history);
        return "benchmark-results";
    }


    @GetMapping("/clear-benchmark")
    public String clearHistory(HttpSession session) {
        session.removeAttribute("benchmarkHistory");
        return "redirect:/results";
}

    

}
