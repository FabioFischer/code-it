package main.view.listener

import javafx.beans.value.ChangeListener
import javafx.beans.value.ObservableValue
import javafx.scene.control.Tab


class TabListener: ChangeListener<Tab> {
    override fun changed(observable: ObservableValue<out Tab>?, oldValue: Tab?, newValue: Tab?) {
        println("old: $oldValue |||| new: $newValue")
    }

    companion object  {
        fun listen(): TabListener = TabListener()
    }
}