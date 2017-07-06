package main.view.screen

interface IScreenBuilder {
    fun createComponents()
    fun createButtons()
    fun createMenus()
    fun createKeyStrokes()
}