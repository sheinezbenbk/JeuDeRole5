package org.ldv.sprintbootaventure.controller.admin

import org.ldv.sprintbootaventure.model.dao.TypeArmeDAO
import org.ldv.sprintbootaventure.model.entity.TypeArme
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@Controller
class TypeArmeControlleur (val typeArmeDAO: TypeArmeDAO){
    /**
     * Affiche la liste de tout les types d'Armes.
     *
     * @param model Le modèle utilisé pour transmettre les données à la vue.
     * @return Le nom de la vue à afficher.
     */
    @GetMapping("/admin/typearme")
    fun index(model: Model): String {
        // Récupère tout les types d'Armes depuis la base de données
        val typeArme= this.typeArmeDAO.findAll()

        // Ajoute la liste des types d'armes au modèle pour affichage dans la vue
        model.addAttribute("typearmes", typeArme)

        // Retourne le nom de la vue à afficher
        return "admin/typearme/index"
    }
    /**
     * Affiche les détails d'un type d'arme en particulier.
     *
     * @param id L'identifiant unique du type d'arme à afficher.
     * @param model Le modèle utilisé pour transmettre les données à la vue.
     * @return Le nom de la vue à afficher.
     * @throws NoSuchElementException si le type d'arme avec l'ID spécifié n'est pas trouvée.
     */
    @GetMapping("/admin/typearme/{id}")
    fun show(@PathVariable id: Long, model: Model): String {
        // Récupère le type d'arme avec l'ID spécifié depuis la base de données
        val typeArme = this.typeArmeDAO.findById(id).orElseThrow()

        // Ajoute la qualité au modèle pour affichage dans la vue
        model.addAttribute("letypeArme", typeArme)

        // Retourne le nom de la vue à afficher
        return "admin/typearme/show"
    }
    /**
     * Affiche le formulaire de création d'une nouvelle qualité.
     *
     * @param model Le modèle utilisé pour transmettre les données à la vue.
     * @return Le nom de la vue à afficher (le formulaire de création).
     */
    @GetMapping("/admin/typearme/create")
    fun create(model: Model): String {
        // Crée une nouvelle instance de Qualite avec des valeurs par défaut
        val nouveauTypeArme = TypeArme(null, 0, 0, 0,0,"")

        // Ajoute le nouveau type d'Arme au modèle pour affichage dans le formulaire de création
        model.addAttribute("nouveauTypeArme", nouveauTypeArme)

        // Retourne le nom de la vue à afficher (le formulaire de création)
        return "admin/typearme/create"
    }

    @PostMapping("/admin/typearme")
    fun store(@ModelAttribute nouveauTypeArme: TypeArme, redirectAttributes: RedirectAttributes): String {
        // Sauvegarde le nouveau type d'Arme dans la base de données
        val savedTypeArme = this.typeArmeDAO.save(nouveauTypeArme)
        // Ajoute un message de succès pour être affiché à la vue suivante
        redirectAttributes.addFlashAttribute("msgSuccess", "Enregistrement de ${savedTypeArme.nom} réussi")
        // Redirige vers la page d'administration des types d'Armes
        return "redirect:/admin/typearme"
    }
    /**
     * Affiche les détails d'un type d'arme en particulier pour ensuite le modifier.
     *
     * @param id L'identifiant unique du type d'arme à afficher.
     * @param model Le modèle utilisé pour transmettre les données à la vue.
     * @return Le nom de la vue à afficher.
     */
    @GetMapping("/admin/typearme/{id}/edit")
    fun edit(@PathVariable id: Long, model: Model): String {
        // Récupère le type d'Arme avec l'ID spécifié depuis la base de données
        val leTypeArme = this.typeArmeDAO.findById(id).orElseThrow()

        // Ajoute le type d'Arme au modèle pour affichage dans la vue
        model.addAttribute("typearme", leTypeArme)

        // Retourne le nom de la vue à afficher
        return "admin/typearme/edit"
    }
    /**
     * Gère la soumission du formulaire de mise à jour de typeArme.
     *
     * @param typeArme L'objet TypeArme mis à jour à partir des données du formulaire.
     * @param redirectAttributes Les attributs de redirection pour transmettre des messages à la vue suivante.
     * @return La redirection vers la page d'administration des qualités après la mise à jour réussie.
     * @throws NoSuchElementException si la qualité avec l'ID spécifié n'est pas trouvée dans la base de données.
     */
    @PostMapping("/admin/typearme/update")
    fun update(@ModelAttribute nouveauTypeArme: TypeArme, redirectAttributes: RedirectAttributes): String {
        // Recherche du type d'Arme existant dans la base de données
        val typeArmeModifier = this.typeArmeDAO.findById(nouveauTypeArme.id ?: 0).orElseThrow()

        // Mise à jour des propriétés du typeArme avec les nouvelles valeurs du formulaire
        typeArmeModifier.nom =nouveauTypeArme.nom
        typeArmeModifier.nombreDes= nouveauTypeArme.nombreDes
        typeArmeModifier.valeurDeMax = nouveauTypeArme.valeurDeMax
        typeArmeModifier.multiplicateurCritique = nouveauTypeArme.multiplicateurCritique
        typeArmeModifier.activationCritique = nouveauTypeArme.activationCritique
        // Sauvegarde le type d'Arme modifiée dans la base de données
        val savedTypeArme = this.typeArmeDAO.save(typeArmeModifier)

        // Ajoute un message de succès pour être affiché à la vue suivante
        redirectAttributes.addFlashAttribute("msgSuccess", "Modification de ${savedTypeArme.nom} réussie")

        // Redirige vers la page d'administration des qualités
        return "redirect:/admin/typearme"
    }

    /**
     * Gère la suppression d'un type d'Arme par son identifiant.
     *
     * @param id L'identifiant du typeArme à supprimer.
     * @param redirectAttributes Les attributs de redirection pour transmettre des messages à la vue suivante.
     * @return La redirection vers la page d'administration des types d'armes après la suppression réussie.
     */
    @PostMapping("/admin/typearme/delete")
    fun delete(@RequestParam id: Long, redirectAttributes: RedirectAttributes): String {
        // Recherche du type d'arme à supprimer dans la base de données
        val typeArme: TypeArme = this.typeArmeDAO.findById(id).orElseThrow()

        // Suppression du type d'arme de la base de données
        this.typeArmeDAO.delete(typeArme)

        // Ajoute un message de succès pour être affiché à la vue suivante
        redirectAttributes.addFlashAttribute("msgSuccess", "Suppression de ${typeArme.nom} réussie")

        // Redirige vers la page d'administration des qualités
        return "redirect:/admin/typearme"
    }
}
