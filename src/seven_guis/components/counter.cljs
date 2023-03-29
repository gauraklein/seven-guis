(ns seven-guis.components.counter
  (:require 
   [re-frame.core :as rf]
   [seven-guis.subs :as subs]
   [seven-guis.events :as events]))

(defn counter
  []
  (let [count @(rf/subscribe [::subs/count])]
    [:div {:class "window"}
     [:div {:class "title-bar"}
      [:h3 "Counter"]]
     [:div {:class "window-body"}
      [:label {:class "label"} count]
      [:input {:type "button"
               :class "button"
               :value "Increment"
               :on-click #(rf/dispatch [::events/inc-counter])}]]]))
