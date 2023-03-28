(ns seven-guis.db)

(def default-db
  {:name "re-frame"
   :count 0
   ;; TODO: maybe nest these keys under :temp
   :temp {:celsius nil
          :fahrenheit nil
          :error nil}})
