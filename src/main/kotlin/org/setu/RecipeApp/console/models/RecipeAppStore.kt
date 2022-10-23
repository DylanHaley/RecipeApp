package org.setu.RecipeApp.console.models

interface RecipeAppStore {
    fun findAll(): List<RecipeAppModel>
    fun findOne(id: Long): RecipeAppModel?
    fun create(recipe: RecipeAppModel)
    fun update(recipe: RecipeAppModel)
//    fun delete(recipe: RecipeAppModel)
}
