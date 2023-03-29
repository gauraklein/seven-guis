(ns seven-guis.components.timer
  (:require
   [re-frame.core :as rf]
   [seven-guis.subs :as subs]
   [seven-guis.events :as events]))

(defn timer
  []
  (let [_ (rf/dispatch [::events/init-timer])]
    (fn []
      (let [{:keys [duration current interval]} @(rf/subscribe [::subs/timer])]
        (when (>= current duration) (js/clearInterval interval))
        [:div {:class "window"}
         [:div {:class "title-bar"}
          [:h3 "Timer"]]
         [:div {:class "window-body"}
          [:div
           [:label "Elapsed Time: "]
           [:progress {:value current :max duration}]]
          [:p (str current "s")]
          [:div
           [:label "Duration: "]
           [:input {:type "range"
                    :class "range"
                    :value duration
                    :max 25
                    :on-change #(rf/dispatch [::events/set-duration (-> % .-target .-value)])}]]]
         [:input {:type "button"
                  :class "button"
                  :value "Reset"
                  :on-click #(rf/dispatch [::events/reset-timer])}]]))))
