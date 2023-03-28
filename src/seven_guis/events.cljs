(ns seven-guis.events
  (:require
   [re-frame.core :as rf]
   [seven-guis.db :as db]
   [day8.re-frame.tracing :refer-macros [fn-traced]]
   ))

(rf/reg-event-db
 ::initialize-db
 (fn-traced [_ _]
   db/default-db))

(rf/reg-event-db
 ::inc-counter
   (fn [db _]
     (update db :count inc)))

;; function that takes key and value and updates map conditionally
(defn- update-temp
  [k v]
  (let [num-val (js/Number v)]
    (if (= :celsius k)
      {:error nil
       :celsius v
       :fahrenheit (int (+ 32 (* num-val 1.8)))}
      {:error nil
       :fahrenheit v
       :celsius (int (* 0.5556 (- num-val 32)))})))

(rf/reg-event-db
 ::calc-temp
 (fn [db [_ k v]]
   (if (re-matches #"\d+" v)
     (assoc db :temp (update-temp k v))
     (assoc-in db [:temp :error] "Error, please use a number"))))

