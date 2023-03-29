(ns seven-guis.db)

(def default-db
  {:count 0
   :temp {:celsius nil
          :fahrenheit nil
          :error nil}
   :flight {:one-way? false
            :start-date {:val ""
                         :valid? true}
            :end-date {:val ""
                       :valid? true}
            :message ""}
   :timer {:duration 10
           :current 0
           :interval nil}})
