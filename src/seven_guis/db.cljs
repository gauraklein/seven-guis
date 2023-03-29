(ns seven-guis.db)

(def default-db
  {:name "re-frame"
   :count 0
   ;; TODO: make sure empty strings work here
   :temp {:celsius nil
          :fahrenheit nil
          :error nil}
   :flight {:one-way? false
                   :start-date {:val ""
                                :valid? true}
                   :end-date {:val ""
                              :valid? true}
                   :message ""}})
