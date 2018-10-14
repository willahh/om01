(ns om01.route-list
  (:require [om01.front.page.home :as home]
            [om01.front.page.about :as about]
            [om01.front.page.week :as week]
            [om01.front.page.actor :as actor]))

(def routes
  [{:uri "/"
    :handler home/page-html}
   {:uri "/about"
    :handler about/page-html}
   {:uri "/week"
    :handler week/page-html}
   {:uri "/actor"
    :handler actor/page-html}])