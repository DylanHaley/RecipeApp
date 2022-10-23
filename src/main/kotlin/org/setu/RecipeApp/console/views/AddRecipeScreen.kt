package org.setu.RecipeApp.console.views

import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Orientation
import org.setu.RecipeApp.console.controllers.RecipeAppUIController
import tornadofx.*

 class AddRecipeScreen : tornadofx.View("Add Recipes") {
    val model = ViewModel()
    val _title = model.bind { SimpleStringProperty() }
     val _ingredients = model.bind { SimpleStringProperty() }
     val _description = model.bind { SimpleStringProperty() }
    val recipeUIController: RecipeAppUIController by inject()

     //Creates form for user to input and add a new recipe
    override val root = form {
        setPrefSize(600.0, 200.0)
        fieldset(labelPosition = Orientation.VERTICAL) {
            field("Recipe Name") {
                textfield(_title).required()
            }
            field("Ingredients") {
                textfield(_ingredients).required()
            }
            field("Description") {
                textfield(_description).required()
            }
                button("Add") {
                    enableWhen(model.valid)
                    isDefaultButton = true
                    useMaxWidth = true
                    action {
                        runAsyncWithProgress {
                            recipeUIController.addRecipe(_title.toString(), _ingredients.toString(), _description.toString())

                        }
                    }
                }
            button("Close") {
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        recipeUIController.closeAddRecipe()
                    }
                }
            }
        }
    }

    override fun onDock() {
        _title.value = ""
        _ingredients.value = ""
        _description.value = ""
        model.clearDecorators()
    }
}