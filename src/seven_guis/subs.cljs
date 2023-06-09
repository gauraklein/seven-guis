(ns seven-guis.subs
  (:require
   [re-frame.core :as rf]))

(rf/reg-sub 
 ::count
 (fn [db] (:count db)))

(rf/reg-sub
 ::temp 
 (fn [db] (:temp db)))

(rf/reg-sub
 ::flight
 (fn [db] (:flight db)))

(rf/reg-sub 
 ::timer
 (fn [db] (:timer db)))

(rf/reg-sub
 ::crud 
 (fn [db] (:crud db)))