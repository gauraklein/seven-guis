(ns seven-guis.components.temp-converter
  (:require
   [re-frame.core :as rf]
   [seven-guis.subs :as subs]
   [seven-guis.events :as events]))


(defn temp-converter
  []
  (let [{:keys [celsius fahrenheit error]} @(rf/subscribe [::subs/temp])]
    [:div
     [:h3 "Temperature Converter"
      [:div
       (when :error [:h3 {:style {:color "red"}} error])
       [:input {:type "text" :value celsius
                :on-change #(rf/dispatch [::events/calc-temp :celsius (-> % .-target .-value)])}]
       " Celsius = "
       [:input {:type "text" :value fahrenheit
                :on-change #(rf/dispatch [::events/calc-temp :fahrenheit (-> % .-target .-value)])}]
       " Fahrenheit"]]]))