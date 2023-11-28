package org.ldv.sprintbootaventure.controller.admin



import org.ldv.sprintbootaventure.model.entity.Armure
import org.ldv.sprintbootaventure.model.dao.ArmureDAO
import org.ldv.sprintbootaventure.model.dao.QualiteDAO
import org.ldv.sprintbootaventure.model.dao.TypeArmureDAO
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.mvc.support.RedirectAttributes

/**
 * Affiche les détails d'une qualité en particulier.
 *
 * @param_id L'identifiant unique de la qualité à afficher.
 * @param_model Le modèle utilisé pour transmettre les données à la vue.
 * @return Le nom de la vue à afficher.
 * @throws NoSuchElementException si la qualité avec l'ID spécifié n'est pas trouvée.
 */

@Controller
class ArmureControlleur (val armureDao: ArmureDAO, val qualiteDao: QualiteDAO, val typeArmureDao: TypeArmureDAO) {

    @GetMapping("/admin/armure")
    fun index(model : Model): String {
        // Récupère toutes les qualités depuis la base de données
        val armures = this.armureDao.findAll()

        // Ajoute la liste des qualités au modèle pour affichage dans la vue
        model.addAttribute("Armures", armures)

        // Retourne le nom de la vue à afficher
        return "admin/armure/index"
    }

    @GetMapping("/admin/armure/{id}")
    fun show(@PathVariable id: Long, model: Model): String {
        // Récupère la qualité avec l'ID spécifié depuis la base de données
        val uneArmure = this.armureDao.findById(id).orElseThrow()

        // Ajoute la qualité au modèle pour affichage dans la vue
        model.addAttribute("Armure", uneArmure)

        // Retourne le nom de la vue à afficher
        return "admin/armure/show"
    }

    /**
     * Affiche le formulaire de création d'une nouvelle qualité.
     *
     * @param model Le modèle utilisé pour transmettre les données à la vue.
     * @return Le nom de la vue à afficher (le formulaire de création).
     */
    @GetMapping("/admin/armure/create")
    fun create(model: Model): String {
        // Crée une nouvelle instance de Armure avec des valeurs par défaut
        val nouvelleArmure = Armure(null, "", "","")
        val qualites = qualiteDao.findAll()
        model.addAttribute("qualites",qualites)
        val typeArmures = typeArmureDao.findAll()
        model.addAttribute("typeArmures",typeArmures)


        // Ajoute la nouvelle qualité au modèle pour affichage dans le formulaire de création
        model.addAttribute("nouvelleArmure", nouvelleArmure)

        // Retourne le nom de la vue à afficher (le formulaire de création)
        return "admin/armure/create"
    }

    @PostMapping("/admin/armure")
    fun store(@ModelAttribute nouvelleArmure: Armure, redirectAttributes: RedirectAttributes): String {
        // Sauvegarde la nouvelle qualité dans la base de données
        val savedArmure = this.armureDao.save(nouvelleArmure)
        // Ajoute un message de succès pour être affiché à la vue suivante
        redirectAttributes.addFlashAttribute("msgSuccess", "Enregistrement de ${savedArmure.id} réussi")
        // Redirige vers la page d'administration des qualités
        return "redirect:/admin/armure"
    }

    @GetMapping("/admin/armure/{id}/edit")
    fun edit(@PathVariable id: Long, model: Model): String {
        // Récupère la qualité avec l'ID spécifié depuis la base de données
        val uneArmure = this.armureDao.findById(id).orElseThrow()

        // Ajoute la qualité au modèle pour affichage dans la vue
        model.addAttribute("Armure", uneArmure)

        val qualites = qualiteDao.findAll()
        model.addAttribute("qualites",qualites)
        val typeArmures = typeArmureDao.findAll()
        model.addAttribute("typeArmures",typeArmures)

        // Retourne le nom de la vue à afficher
        return "admin/armure/edit"
    }

    /**
     * Gère la soumission du formulaire de mise à jour de qualité.
     *
     * @param-Armure L'objet Armure mis à jour à partir des données du formulaire.
     * @param-redirectAttributes Les attributs de redirection pour transmettre des messages à la vue suivante.
     * @return La redirection vers la page d'administration des qualités après la mise à jour réussie.
     * @throws NoSuchElementException si la qualité avec l'ID spécifié n'est pas trouvée dans la base de données.
     */
    @PostMapping("/admin/armure/update")
    fun update(@ModelAttribute armure: Armure, redirectAttributes: RedirectAttributes): String {
        // Recherche de la qualité existante dans la base de données
        val armureModifier = this.armureDao.findById(armure.id ?: 0).orElseThrow()

        // Mise à jour des propriétés de la qualité avec les nouvelles valeurs du formulaire
        armureModifier.nom = armure.nom
        armureModifier.description = armure.description
        armureModifier.cheminImage = armure.cheminImage
        armureModifier.typeArmure = armure.typeArmure
        armureModifier.qualite = armure.qualite


        // Sauvegarde la qualité modifiée dans la base de données
        val savedArmure = this.armureDao.save(armureModifier)

        // Ajoute un message de succès pour être affiché à la vue suivante
        redirectAttributes.addFlashAttribute("msgSuccess", "Modification de ${savedArmure.nom} réussie")

        // Redirige vers la page d'administration des qualités
        return "redirect:/admin/armure"
    }

    /**
     * Gère la suppression d'une qualité par son identifiant.
     *
     * @param id L'identifiant de la qualité à supprimer.
     * @param redirectAttributes Les attributs de redirection pour transmettre des messages à la vue suivante.
     * @return La redirection vers la page d'administration des qualités après la suppression réussie.
     * @throws NoSuchElementException si la qualité avec l'ID spécifié n'est pas trouvée dans la base de données.
     */
    @PostMapping("/admin/armure/delete")
    fun delete(@RequestParam id: Long, redirectAttributes: RedirectAttributes): String {
        // Recherche de la qualité à supprimer dans la base de données
        val armure: Armure = this.armureDao.findById(id).orElseThrow()

        // Suppression de la qualité de la base de données
        this.armureDao.delete(armure)

        // Ajoute un message de succès pour être affiché à la vue suivante
        redirectAttributes.addFlashAttribute("msgSuccess", "Suppression de ${armure.nom} réussie")

        // Redirige vers la page d'administration des qualités
        return "redirect:/admin/armure"
    }


}