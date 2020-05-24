package co.com.daniel.web;

import co.com.daniel.domain.Persona;
import co.com.daniel.servicio.IPersonaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
public class ControladorInicio {

    @Autowired
    private IPersonaService personaService;

    @GetMapping("/")
    public String inicio(Model model, @AuthenticationPrincipal User user){
        List<Persona> personas = personaService.listarPersonas();

        model.addAttribute("personas", personas);
        Double saldoTotal = personas.stream().map(p-> p.getSaldo()).reduce(0D, Double::sum);
        model.addAttribute("saldoTotal", saldoTotal);
        model.addAttribute("totalClientes", personas.size());
        log.info("usuario en sesion: "+user);
        log.info("ejecutando el controlador Spring MVC");
        return "index";
    }

    @GetMapping("/agregar")
    public String agregar(Persona persona){
        return "modificar";
    }

    @PostMapping("/guardar")
    public String guardar (@Valid Persona persona, Errors errores){
        if(errores.hasErrors()){
            return "modificar";
        }
        personaService.guardar(persona);
        return "redirect:/";
    }
    @GetMapping("/editar/{id}")
    public String editar (Persona persona, Model model){
        persona = personaService.encontrarPersona(persona);
        model.addAttribute("persona", persona);
        return "modificar";
    }
    @GetMapping("/eliminar")
    public String eliminar (Persona persona){
        personaService.eliminar(persona);
        return "redirect:/";
    }

}
