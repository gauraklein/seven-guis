(ns seven-guis.components.counter
  (:require 
   [re-frame.core :as rf]
   [seven-guis.subs :as subs]
   [seven-guis.events :as events]))

(defn counter
  []
  (let [count @(rf/subscribe [::subs/count])]
    [:div
     ;; TODO: Seperate these elements into their own components
     [:h3 "Counter"]
     [:div
      [:label count " "]
      [:input {:type "button" :value "Increment"
               :on-click #(rf/dispatch [::events/inc-counter])}]]]))
