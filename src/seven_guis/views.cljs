(ns seven-guis.views
  (:require
   [seven-guis.components.counter :refer [counter]]
   [seven-guis.components.temp-converter :refer [temp-converter]]
   [seven-guis.components.flight-booker :refer [flight-booker]]
   [seven-guis.components.timer :refer [timer]]
   [seven-guis.components.crud :refer [crud]]))

(defn main-panel []
  [:div
   [:h1 "7 GUIS"]
   [counter]
   [temp-converter]
   [flight-booker]
   [timer]
   [crud]])
