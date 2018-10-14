(ns ^:figwheel-hooks om01.core
  (:require
   [goog.dom :as gdom]
   [react :as react]
   [react-dom :as react-dom]
   [create-react-class :as create-react-class]
   [sablono.core :as sab :include-macros true]
   [om.core :as om :include-macros true]))

(println "This text is printed from src/om01/core.cljs. Go ahead and edit it and see reloading in action.")

(defn multiply [a b] (* a b))
;; this is to support om with the latest version of React
(set! (.-createClass react) create-react-class)

;; define your app data so that it doesn't get over-written on reload
(defonce app-state (atom {:text "Hello world!"
                          :rows [{:id "1" :text "Item 1"}
                                 {:id "2" :text "Item 2"}
                                 {:id "3" :text "Item 3"}]}))

(defn get-app-element []
  (gdom/getElement "app"))

(defn remove-home-item [pos]
  (swap! app-state update-in [:rows]
         (fn [v]
           (filter #(not= pos (:id %)) v))))

(defn add-home-item []
  (swap! app-state update-in [:rows]
         (fn [v]
           (conj v (let [id (rand-int 1000)]
                     {:id id
                      :text (str "Item " id)})))))

(defn home-html []
  [:div
   [:h3 "home"]
   [:ul
    (map (fn [m]
           [:li (:text m)])
         (:rows @app-state))]
   [:select {:id "home-item"}
    (map (fn [m]
           [:option (:id m)])
         (:rows @app-state))]
   [:button {:on-click #(remove-home-item (.-value (.getElementById js/document "home-item")))} "Delete home item"]
   [:button {:on-click #(add-home-item)} "Add item"]])

(defn mount [el]
  (om/root
   (fn [data owner]
     (reify om/IRender
       (render [_]
         (sab/html
          [:div
           [:h1 (:text data)]
           (home-html)]))))
   app-state
   {:target el}))

(defn mount-app-element []
(when-let [el (get-app-element)]
  (mount el)))

;; conditionally start your application based on the presence of an "app" element
;; this is particularly helpful for testing this ns without launching the app
(mount-app-element)

;; specify reload hook with ^;after-load metadata
(defn ^:after-load on-reload []
(mount-app-element)
;; optionally touch your app-state to force rerendering depending on
;; your application
;; (swap! app-state update-in [:__figwheel_counter] inc)
)
