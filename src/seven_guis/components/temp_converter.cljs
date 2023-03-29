(ns seven-guis.components.temp-converter
  (:require
   [re-frame.core :as rf]
   [seven-guis.subs :as subs]
   [seven-guis.events :as events]))

;; TODO: allow the input val to be displayed when in error and set the bg color accordingly
(defn temp-converter
  []
  (let [{:keys [celsius fahrenheit error]} @(rf/subscribe [::subs/temp])]
    [:div {:class "window"}
     [:div {:class "title-bar"}
      [:h3 "Temperature Converter"]]
     [:div {:class "window-body"}
      (when :error [:p {:style {:color "red"}} error])
      [:input {:type "text"
               :value celsius
               :on-change #(rf/dispatch [::events/calc-temp :celsius (-> % .-target .-value)])}]
      " Celsius = "
      [:input {:type "text" 
               :value fahrenheit
               :on-change #(rf/dispatch [::events/calc-temp :fahrenheit (-> % .-target .-value)])}]
      " Fahrenheit"]]))
