package org.setu.RecipeApp.console.models

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import mu.KotlinLogging
import org.setu.RecipeApp.console.helpers.exists
import org.setu.RecipeApp.console.helpers.read
import org.setu.RecipeApp.console.helpers.write

import java.util.*

private val logger = KotlinLogging.logger {}

val JSON_FILE = "recipes.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.ArrayList<RecipeAppModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class RecipeJSONStore : RecipeAppStore {

    var recipes = mutableListOf<RecipeAppModel>()

    init {
        if (exists(JSON_FILE)) {
            deserialize()
        }
    }

    //returns everything in recipe list
    override fun findAll(): MutableList<RecipeAppModel> {
        return recipes
    }

    //returns one specific item from recipe list
    override fun findOne(id: Long) : RecipeAppModel? {
        var foundRecipe: RecipeAppModel? = recipes.find { p -> p.id == id }
        return foundRecipe
    }

    //Creates new item in recipe list
    override fun create(recipe: RecipeAppModel) {
        recipe.id = generateRandomId()
        recipes.add(recipe)
        serialize()
    }

    //Attempted a delete function to clear JSON file
     fun delete() {
        recipes.clear()
         serialize()
    }

    //Updates item in recipe list
    override fun update(recipe: RecipeAppModel) {
        var foundRecipe = findOne(recipe.id!!)
        if (foundRecipe != null) {
            foundRecipe.title = recipe.title
            foundRecipe.ingredients = recipe.ingredients
            foundRecipe.description = recipe.description
        }
        serialize()
    }

    //Logs all items in recipe list
    internal fun logAll() {
        recipes.forEach { logger.info("${it}") }
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(recipes, listType)
        write(JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(JSON_FILE)
        recipes = Gson().fromJson(jsonString, listType)
    }
}
