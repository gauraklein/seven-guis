(ns seven-guis.views
  (:require
   [seven-guis.components.counter :refer [counter]]
   [seven-guis.components.temp-converter :refer [temp-converter]]))

(defn main-panel []
  [:div
   [:h1 "7 GUIS"]
   [counter]
   [temp-converter]
   ])
