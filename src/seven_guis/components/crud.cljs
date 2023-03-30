(ns seven-guis.components.crud
  (:require
   [re-frame.core :as rf]
   [seven-guis.subs :as subs]
   [seven-guis.events :as events]))

(defn- generate-row
  [record]
  (let [id (key record)
        {:keys [name surname]} (val record)]
    [:option {:value id
              :on-click #(rf/dispatch [::events/set-selected id])}
     (str surname ", " name)])) 

(defn- includes-prefix?
  [prefix string]
  (boolean (re-matches (re-pattern (str "(?i)" prefix ".*")) string)))

(defn- filter-by-prefix
  [records prefix]
  (filter #(includes-prefix? prefix (:surname (val %))) records))

(defn crud
  []
  (fn []
    (let [{:keys [prefix temp-record selected records]} @(rf/subscribe [::subs/crud])]
      [:div {:class "window"}
       [:div {:class "title-bar"}
        [:h3 "CRUD"]]
        [:div {:class "window-body"}
         [:div
          [:label "Filter prefix: "]
          [:input {:type "text" :value prefix :on-change #(rf/dispatch [::events/set-prefix (-> % .-target .-value)])}]]
         [:div {:style {:display "flex"}}
          (into [:select {:size 5 :style {:min-width "20rem"}}]
                (if (seq prefix)
                  (let [filtered-records (filter-by-prefix records prefix)]
                    (map #(generate-row %) filtered-records))
                  (map #(generate-row %) records)))
          [:div
           [:div
            [:label "Name: "]
            [:input {:type "text"
                     :value (:name temp-record)
                     :on-change #(rf/dispatch [::events/update-temp-record :name (-> % .-target .-value)])}]]
           [:div
            [:label "Surname: "]
            [:input {:type "text"
                     :value (:surname temp-record)
                     :on-change #(rf/dispatch [::events/update-temp-record :surname (-> % .-target .-value)])}]]]]
         [:div
          [:input {:type "button"
                   :class "button"
                   :value "Create"
                   :on-click #(rf/dispatch [::events/create-record])
                   :disabled (not (and (seq (:name temp-record))
                                       (seq (:surname temp-record))))}]
          [:input {:type "button"
                   :class "button"
                   :value "Update"
                   :on-click #(rf/dispatch [::events/update-record])
                   :disabled (nil? selected)}]
          [:input {:type "button"
                   :class "button"
                   :value "Delete"
                   :on-click #(rf/dispatch [::events/delete-record])
                   :disabled (nil? selected)}]]]])))
