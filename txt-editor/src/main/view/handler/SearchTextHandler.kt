package main.view.handler

import javafx.event.ActionEvent
import javafx.event.EventHandler
import main.view.screen.SearchScreen

class SearchTextHandler {
    class onSearchFindRequest(root: SearchScreen): EventHandler<ActionEvent> {
        override fun handle(event: ActionEvent?) {
        }
    }

    class onSearchFindNextRequest(root: SearchScreen): EventHandler<ActionEvent> {
        override fun handle(event: ActionEvent?) {
        }
    }

    class onSearchReplaceRequest(root: SearchScreen): EventHandler<ActionEvent> {
        override fun handle(event: ActionEvent?) {
        }
    }

    class onSearchReplaceAllRequest(root: SearchScreen): EventHandler<ActionEvent> {
        override fun handle(event: ActionEvent?) {
        }
    }
}