package com.zynaptic;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class ZynapticApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZynapticApplication.class, args);
    }
}

@Controller
class MainController {
    @GetMapping("/")
    @ResponseBody
    public String home() {
        List<Course> courses = List.of(
                new Course("Network Fundamentals", "Learn Networking, TCP/IP, Routing & Switching."),
                new Course("Cybersecurity Essentials", "Threats, Vulnerabilities, Security Controls."),
                new Course("Advanced Firewall", "Firewall configurations and management."),
                new Course("Ethical Hacking", "Penetration testing and vulnerability assessment.")
        );

        String courseHtml = courses.stream().map(c ->
            "<div class='course-item'>" +
                "<h4>" + c.title() + "</h4>" +
                "<p>" + c.description() + "</p>" +
            "</div>"
        ).collect(Collectors.joining());

        // Inline CSS and JS for single file
        return """
<!DOCTYPE html>
<html lang='en'>
<head>
    <meta charset='UTF-8'>
    <title>Zynaptic - Network & Security Training</title>
    <style>
        body { font-family: Arial,sans-serif; margin:0; padding:0; background:#f4f4f4; color:#222; }
        header { background:#263238; color:#fff; padding:20px 0; text-align:center; }
        nav a { color:#fff; margin:0 15px; text-decoration:none; }
        .hero { background:#2196F3; color:#fff; padding:40px 20px; text-align:center; }
        section { padding:30px 20px; }
        h3 { color:#263238; }
        .course-list { display:flex; flex-wrap:wrap; gap:20px; }
        .course-item { background:#fff; box-shadow:0 2px 6px rgba(0,0,0,0.08); padding:15px; border-radius:8px; flex:1 1 220px; }
        form { max-width:400px; margin:20px auto; display:flex; flex-direction:column; }
        input, textarea { margin-bottom:10px; padding:8px; border-radius:5px; border:1px solid #bbb; }
        button { background:#263238; color:#fff; border:none; padding:10px; border-radius:5px; cursor:pointer; font-weight:bold; }
        #formMessage { color:green; margin-top:10px; }
        footer { background:#263238; color:#fff; text-align:center; padding:10px 0; position:fixed; width:100%; bottom:0; }
        @media (max-width:650px) { .course-list {flex-direction:column;} footer{position:static;} }
    </style>
</head>
<body>
    <header>
        <h1>Zynaptic</h1>
        <nav>
            <a href='#courses'>Courses</a>
            <a href='#contact'>Contact</a>
        </nav>
    </header>
    <section class='hero'>
        <h2>Upgrade Your Network & Security Skills with Zynaptic</h2>
        <p>Hands-on training for professionals and students in networking and cybersecurity.</p>
    </section>
    <section id='courses'>
        <h3>Our Courses</h3>
        <div class='course-list'>
            """ + courseHtml + """
        </div>
    </section>
    <section id='contact'>
        <h3>Contact Zynaptic</h3>
        <form id='contactForm'>
            <input type='text' name='name' placeholder='Your Name' required />
            <input type='email' name='email' placeholder='Your Email' required />
            <textarea name='message' placeholder='Your Message' required></textarea>
            <button type='submit'>Send</button>
        </form>
        <div id='formMessage'></div>
    </section>
    <footer>
        &copy; 2025 Zynaptic | All Rights Reserved
    </footer>
    <script>
        document.addEventListener("DOMContentLoaded", function() {
            var form = document.getElementById("contactForm");
            var msgDiv = document.getElementById("formMessage");
            if (form) {
                form.addEventListener("submit", function(e) {
                    e.preventDefault();
                    msgDiv.textContent = "Thank you for your message! Zynaptic will reply soon.";
                    form.reset();
                });
            }
        });
    </script>
</body>
</html>
""";
    }
}

record Course(String title, String description) {}