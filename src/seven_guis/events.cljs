(ns seven-guis.events
  (:require
   [re-frame.core :as rf]
   [seven-guis.db :as db]
   [day8.re-frame.tracing :refer-macros [fn-traced]]))

(rf/reg-event-db
 ::initialize-db
 (fn-traced [_ _]
            db/default-db))

;; Counter

(rf/reg-event-db
 ::inc-counter
 (fn [db _]
   (update db :count inc)))

;; Temp

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
     (-> db
         (assoc-in [:temp k] v)
         (assoc-in [:temp :error] "Error, please use a number")))))

;; Flight

(rf/reg-event-db
 ::set-flight-type
 (fn [db [_ type]]
   (assoc-in db [:flight :one-way?] (= type "one-way"))))

(defn- valid-date?
  [date-string]
  (and
   (seq date-string)
   (re-matches #"[0-9]{2}\.[0-9]{2}\.[0-9]{4}" date-string)))

(rf/reg-event-db
 ::set-flight-date
 (fn [db [_ k v]]
   (-> db
       (assoc-in [:flight k :val] v)
       (assoc-in [:flight k :valid?] (valid-date? v)))))

(defn- confirmation-message
  [{:keys [one-way? start-date end-date]}]
  (if one-way?
    (str "You have booked a one-way flight on " (:val start-date))
    (str "You have booked a return flight from " (:val start-date) " to " (:val end-date))))

(rf/reg-event-db
 ::book-flight
 (fn [db _]
   (assoc-in db [:flight :message] (confirmation-message (:flight db)))))

;; Timer

(rf/reg-event-db
 ::init-timer
 (fn [db _]
   (js/clearInterval (-> db :timer :interval))
   (-> db
       (assoc-in [:timer :current] 0)
       (assoc-in [:timer :interval] (js/setInterval #(rf/dispatch [::increment-timer]) 100)))))

(rf/reg-event-db
 ::reset-timer
 (fn [db _]
   (let [interval (-> db :timer :interval)]
     (js/clearInterval interval)
     (rf/dispatch [::init-timer]))))

(defn- inc-and-round
  "increments num by 0.1 and rounds to 1 decimal place"
  [num]
  (/ (. js/Math round (* (+ num  0.1) 10)) 10))

(rf/reg-event-db
 ::increment-timer
 (fn [db _]
   (let [current (-> db :timer :current)]
     (assoc-in db [:timer :current] (inc-and-round current)))))

(rf/reg-event-db
 ::set-duration
 (fn [db [_ v]]
   (assoc-in db [:timer :duration] v)))

;; CRUD

(rf/reg-event-db
 ::set-prefix
 (fn [db [_ v]]
   (assoc-in db [:crud :prefix] v)))

(rf/reg-event-db 
 ::set-selected
 (fn [db [_ v]]
   (let [records (-> db :crud :records)]
     (-> db
         (assoc-in [:crud :selected] v)
         (assoc-in [:crud :temp-record] (get records v))))))

(rf/reg-event-db 
 ::update-temp-record
 (fn [db [_ k v]]
   (assoc-in db [:crud :temp-record k] v)))

(rf/reg-event-db
 ::update-record
 (fn [db _]
   (let [{:keys [selected temp-record]} (:crud db)]
     (assoc-in db [:crud :records selected] temp-record))))

(rf/reg-event-db 
 ::delete-record
 (fn [db _]
 (let [selected (-> db :crud :selected)]
   (update-in db [:crud :records] dissoc selected))
   ))

(rf/reg-event-db 
 ::create-record
 (fn [db _]
   (let [temp-record (-> db :crud :temp-record)]
     (when (and (seq temp-record)
                (:name temp-record)
                (:surname temp-record)))
     (assoc-in db [:crud :records (random-uuid)] temp-record))))