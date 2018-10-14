(ns om01.component.html-wrapper.wrapper
  (:require [om01.component.html-wrapper.main :as main]))

(defn wrap-page-html [html]
  [:div (main/header-html)
   [:div.ui.main.text.container {:style {:margin-top "5em"}}
    html]])
