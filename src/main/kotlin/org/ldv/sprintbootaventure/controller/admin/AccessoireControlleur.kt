package org.ldv.sprintbootaventure.controller.admin


import org.ldv.sprintbootaventure.model.dao.AccessoireDAO
import org.ldv.sprintbootaventure.model.entity.Accessoire
import org.ldv.sprintbootaventure.model.dao.QualiteDAO
import org.ldv.sprintbootaventure.model.dao.TypeAccessoireDAO
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
class AccessoireControlleur (val accessoireDao: AccessoireDAO, val qualiteDao: QualiteDAO, val typeaccessoireDao: TypeAccessoireDAO) {

    @GetMapping("/admin/accessoire")
    fun index(model : Model): String {
        // Récupère toutes les qualités depuis la base de données
        val accessoires = this.accessoireDao.findAll()

        // Ajoute la liste des qualités au modèle pour affichage dans la vue
        model.addAttribute("accessoires", accessoires)

        // Retourne le nom de la vue à afficher
        return "admin/accessoire/index"
    }

    @GetMapping("/admin/accessoire/{id}")
    fun show(@PathVariable id: Long, model: Model): String {
        // Récupère la qualité avec l'ID spécifié depuis la base de données
        val uneaccessoire = this.accessoireDao.findById(id).orElseThrow()

        // Ajoute la qualité au modèle pour affichage dans la vue
        model.addAttribute("accessoire", uneaccessoire)

        // Retourne le nom de la vue à afficher
        return "admin/accessoire/show"
    }

    /**
     * Affiche le formulaire de création d'une nouvelle qualité.
     *
     * @param model Le modèle utilisé pour transmettre les données à la vue.
     * @return Le nom de la vue à afficher (le formulaire de création).
     */
    @GetMapping("/admin/accessoire/create")
    fun create(model: Model): String {
        // Crée une nouvelle instance de accessoire avec des valeurs par défaut
        val nouvelleaccessoire = Accessoire(null, "", "","")
        val qualites = qualiteDao.findAll()
        model.addAttribute("qualites",qualites)
        val typeaccessoires = typeaccessoireDao.findAll()
        model.addAttribute("typeaccessoires",typeaccessoires)


        // Ajoute la nouvelle qualité au modèle pour affichage dans le formulaire de création
        model.addAttribute("nouvelleaccessoire", nouvelleaccessoire)

        // Retourne le nom de la vue à afficher (le formulaire de création)
        return "admin/accessoire/create"
    }

    @PostMapping("/admin/accessoire")
    fun store(@ModelAttribute nouvelleaccessoire: Accessoire, redirectAttributes: RedirectAttributes): String {
        // Sauvegarde la nouvelle qualité dans la base de données
        val savedaccessoire = this.accessoireDao.save(nouvelleaccessoire)
        // Ajoute un message de succès pour être affiché à la vue suivante
        redirectAttributes.addFlashAttribute("msgSuccess", "Enregistrement de ${savedaccessoire.id} réussi")
        // Redirige vers la page d'administration des qualités
        return "redirect:/admin/accessoire"
    }

    @GetMapping("/admin/accessoire/{id}/edit")
    fun edit(@PathVariable id: Long, model: Model): String {
        // Récupère la qualité avec l'ID spécifié depuis la base de données
        val uneaccessoire = this.accessoireDao.findById(id).orElseThrow()

        // Ajoute la qualité au modèle pour affichage dans la vue
        model.addAttribute("accessoire", uneaccessoire)

        val qualites = qualiteDao.findAll()
        model.addAttribute("qualites",qualites)
        val typeaccessoires = typeaccessoireDao.findAll()
        model.addAttribute("typeaccessoires",typeaccessoires)

        // Retourne le nom de la vue à afficher
        return "admin/accessoire/edit"
    }

    /**
     * Gère la soumission du formulaire de mise à jour de qualité.
     *
     * @param accessoire L'objet accessoire mis à jour à partir des données du formulaire.
     * @param redirectAttributes Les attributs de redirection pour transmettre des messages à la vue suivante.
     * @return La redirection vers la page d'administration des qualités après la mise à jour réussie.
     * @throws NoSuchElementException si la qualité avec l'ID spécifié n'est pas trouvée dans la base de données.
     */
    @PostMapping("/admin/accessoire/update")
    fun update(@ModelAttribute accessoire: Accessoire, redirectAttributes: RedirectAttributes): String {
        // Recherche de la qualité existante dans la base de données
        val accessoireModifier = this.accessoireDao.findById(accessoire.id ?: 0).orElseThrow()

        // Mise à jour des propriétés de la qualité avec les nouvelles valeurs du formulaire
        accessoireModifier.nom = accessoire.nom
        accessoireModifier.description = accessoire.description
        accessoireModifier.cheminImage = accessoire.cheminImage
        accessoireModifier.qualite = accessoire.qualite
        accessoireModifier.typeAccessoire = accessoire.typeAccessoire



        // Sauvegarde la qualité modifiée dans la base de données
        val savedaccessoire = this.accessoireDao.save(accessoireModifier)

        // Ajoute un message de succès pour être affiché à la vue suivante
        redirectAttributes.addFlashAttribute("msgSuccess", "Modification de ${savedaccessoire.nom} réussie")

        // Redirige vers la page d'administration des qualités
        return "redirect:/admin/accessoire"
    }

    /**
     * Gère la suppression d'une qualité par son identifiant.
     *
     * @param id L'identifiant de la qualité à supprimer.
     * @param redirectAttributes Les attributs de redirection pour transmettre des messages à la vue suivante.
     * @return La redirection vers la page d'administration des qualités après la suppression réussie.
     * @throws NoSuchElementException si la qualité avec l'ID spécifié n'est pas trouvée dans la base de données.
     */
    @PostMapping("/admin/accessoire/delete")
    fun delete(@RequestParam id: Long, redirectAttributes: RedirectAttributes): String {
        // Recherche de la qualité à supprimer dans la base de données
        val accessoire: Accessoire = this.accessoireDao.findById(id).orElseThrow()

        // Suppression de la qualité de la base de données
        this.accessoireDao.delete(accessoire)

        // Ajoute un message de succès pour être affiché à la vue suivante
        redirectAttributes.addFlashAttribute("msgSuccess", "Suppression de ${accessoire.nom} réussie")

        // Redirige vers la page d'administration des qualités
        return "redirect:/admin/accessoire"
    }


}