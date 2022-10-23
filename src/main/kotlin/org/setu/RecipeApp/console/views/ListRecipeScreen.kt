package org.setu.RecipeApp.console.views

import org.setu.RecipeApp.console.controllers.RecipeAppUIController
import org.setu.RecipeApp.console.models.RecipeAppModel
import tornadofx.*

class ListRecipeScreen : View("List Recipes") {

    val recipeUIController: RecipeAppUIController by inject()
    val tableContent = recipeUIController.recipes.findAll()
    val data = tableContent.observable()


    //Lists all recipes that are currently in the list
    override val root = vbox {
        setPrefSize(1200.0, 400.0)
        tableview(data) {
            readonlyColumn("ID", RecipeAppModel::id)
            readonlyColumn("TITLE", RecipeAppModel::title)
            readonlyColumn("INGREDIENTS", RecipeAppModel::ingredients)
            readonlyColumn("DESCRIPTION", RecipeAppModel::description)
        }
        button("Close") {
            useMaxWidth = true
            action {
                runAsyncWithProgress {
                    recipeUIController.closeListRecipe()
                }
            }
        }
    }

}