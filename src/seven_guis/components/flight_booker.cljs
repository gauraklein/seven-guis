(ns seven-guis.components.flight-booker
   (:require 
   [re-frame.core :as rf]
   [seven-guis.subs :as subs]
   [seven-guis.events :as events]))

(defn- chronological?
  [start-date end-date]
  (< (. js/Date parse start-date)
     (. js/Date parse end-date)))

(defn- able-to-book?
  [{:keys [one-way? start-date end-date]}]
  (or
   ;; one-way with valid start-date
   (and one-way? (:valid? start-date))
  ;; return trip with valid dates that are chronological
   (and (not one-way?)
        (:valid? start-date)
        (:valid? end-date)
        (chronological? (:val start-date) (:val end-date)))))

  (defn flight-booker
    []
    (let [{:keys [one-way? start-date end-date message] :as flight-data} @(rf/subscribe [::subs/flight-booker])]
      [:div {:style {:display "flex"
                     :flex-direction "column"
                     :max-width "300px"}}
       [:h3 "Flight Booker"]
       [:select {:on-change #(rf/dispatch [::events/set-flight-type (-> % .-target .-value)])}
        [:option {:value "return"} "return flight"]
        [:option {:value "one-way"} "one way flight"]]
       [:input {:type "text"
                :value (:val start-date)
                :style (when (not (:valid? start-date)) {:background-color "red"})
                :on-change #(rf/dispatch [::events/set-flight-date :start-date (-> % .-target .-value)])}]
       [:input {:type "text"
                :disabled one-way?
                :value (:val end-date)
                :style (when (not (:valid? end-date)) {:background-color "red"})
                :on-change #(rf/dispatch [::events/set-flight-date :end-date (-> % .-target .-value)])}]
       [:input {:type "button"
                :value "Book"
                :disabled (not (able-to-book? flight-data))
                :on-click #(rf/dispatch [::events/book-flight])}]
       (when message [:h4 message])]))