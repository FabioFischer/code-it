package main.view.screen

abstract class AbstractView(val screenWidth: Int, val screenHeight: Int, val screenName: String) : javax.swing.JFrame(), IViewBuilder {

    init {
        setSize(screenWidth, screenHeight)
        setTitle(screenName)
    }

    fun initToolbarButton(listener: java.awt.event.ActionListener, button: javax.swing.JButton, toolbar: javax.swing.JToolBar, name: String?, icon: javax.swing.ImageIcon) {
        button.icon = icon
        button.toolTipText = name
        button.addActionListener(listener)

        toolbar.add(button)
        toolbar.addSeparator()
    }

    fun initMenuItem(listener: java.awt.event.ActionListener, menuItem: javax.swing.JMenuItem, menu: javax.swing.JMenu, name: String?, icon: javax.swing.ImageIcon, keyShortCut: javax.swing.KeyStroke) {
        menuItem.text = name
        menuItem.icon = icon
        menuItem.toolTipText = name
        menuItem.addActionListener(listener)
        menuItem.accelerator = keyShortCut

        menu.add(menuItem)
    }
}