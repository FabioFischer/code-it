package main.view.screen

abstract class AbstractScreen(val screenWidth: Int, val screenHeight: Int, val screenName: String) : javax.swing.JFrame(), IScreenBuilder {
    private val serialVersionUID = 1L

    init {
        setSize(screenWidth, screenHeight)
        this.title = screenName
        this.isVisible = true
    }

    fun initToolbarButton(listener: java.awt.event.ActionListener, button: javax.swing.JButton, toolbar: javax.swing.JToolBar, name: String?, icon: javax.swing.ImageIcon) {
        button.icon = icon
        button.text = name
        button.toolTipText = name
        button.addActionListener(listener)

        toolbar.add(button)
        toolbar.addSeparator()
    }

    fun initMenuItem(listener: java.awt.event.ActionListener, menuItem: javax.swing.JMenuItem, menu: javax.swing.JMenu, name: String?, icon: javax.swing.ImageIcon, keyShortCut: javax.swing.KeyStroke) {
        menuItem.icon = icon
        menuItem.text = name
        menuItem.toolTipText = name
        menuItem.accelerator = keyShortCut
        menuItem.addActionListener(listener)

        menu.add(menuItem)
    }
}