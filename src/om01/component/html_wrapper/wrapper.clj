(ns om01.component.html-wrapper.wrapper
  (:require [om.dom :as dom]
            [om01.component.html-wrapper.main :as main])
  (:use [sablono.core]))

(defn wrap-page-html [html-str]
  (dom/render-to-str (html
                      [:body
                       (main/head)
                       [:div {:id "app"}
                        [:div (main/header-html)]
                        ;; [:div.ui.main.text.container {:style {:margin-top "5em"}} html-str]
                        [:div.ui.main.text.container {:style {:margin-top "5em"}}]
                        [:script {:type "text/javascript", :src "/cljs-out/dev-main.js"}]]])))

(comment (defn wrap-page-html [html-str]
           (dom/render-to-str (html
                               [:body
                                (main/head)
                                [:div {:id "app"}
                                 [:div (main/header-html)]
                                 ;; [:div.ui.main.text.container {:style "margin-top: 5em;"} html-str]]
                                 [:div.ui.main.text.container {:style "margin-top: 5em;"}]]
                                ;; (include-js "/cljs-out/dev-main.js")
                                [:script {:type "text/javascript", :src "/cljs-out/dev-main.js"}]
                                ]))))
