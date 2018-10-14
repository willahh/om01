(ns om01.route
  (:require [compojure.core :refer [GET routes]]
            [compojure.route :as route]
            [om.dom :as dom]
            [om01.api.api :as api]
            [om01.component.html-wrapper.wrapper :refer [wrap-page-html]]
            [om01.route-list :as route-list]
            [ring.middleware.defaults :refer [site-defaults wrap-defaults]])
  (:use [sablono.core]))

(def global-state (atom {}))

(defmacro defcustomroute [handler route-list & body]
  (let [route-list (for [route route-list/routes]
                     `(~'GET ~(:uri route) []
                       (-> (~(:handler route))
                           wrap-page-html)))]
    `(def ~handler
       (wrap-defaults
        (compojure.core/routes
         ~@route-list
         ~@body)
        (assoc-in site-defaults [:security :anti-forgery] false)))))

(defcustomroute app
  route-list
  api/api-route
  (route/not-found "<h1>Page not found</h1>"))
