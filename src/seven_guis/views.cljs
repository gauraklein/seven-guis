(ns seven-guis.views
  (:require
   [re-frame.core :as rf]
   [seven-guis.subs :as subs]
   [seven-guis.events :as events]))

(defn main-panel []
  (let [count @(rf/subscribe [::subs/count])
        {:keys [celsius fahrenheit error]} @(rf/subscribe [::subs/temp])]
    [:div
     [:h1 "7 GUIS"]
     [:div
     ;; TODO: Seperate these elements into their own components
      [:h3 "Counter"]
      [:div
       [:label count " "]
       [:input {:type "button" :value "Increment"
                :on-click #(rf/dispatch [::events/inc-counter])}]]]
     [:div
      [:h3 "Temperature Converter"
       [:div
        (when :error [:h3 {:style {:color "red"}} error])
        [:input {:type "text" :value celsius
                 :on-change #(rf/dispatch [::events/calc-temp :celsius (-> % .-target .-value)])}]
        " Celsius = "
        [:input {:type "text" :value fahrenheit
                 :on-change #(rf/dispatch [::events/calc-temp :fahrenheit (-> % .-target .-value)])}]
        " Fahrenheit"]]]]))
