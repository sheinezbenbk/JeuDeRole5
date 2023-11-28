package org.ldv.sprintbootaventure.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import jakarta.persistence.*




@Controller
class MainControlleur {
    @GetMapping("/")
    fun index(model:Model):String{

        return "visiteur/acceuil"
    }
    @GetMapping ("/login")
    fun login(model:Model):String{

        return "visiteur/login"
    }
    @GetMapping("/inscription")
    fun inscription(model:Model):String{

        return "visiteur/inscription"
    }


}
