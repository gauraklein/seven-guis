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
           :interval nil}
   :crud {:prefix ""
          :records {#uuid "80dec7cf-1e4a-4105-9f6e-1197ebc8b89b" {:surname "Emil" :name "Hans"}
                    #uuid "7549c193-f6c9-4c01-a229-f25bb77cfbd3" {:surname "Mustermann" :name "Max"}
                    #uuid "c502bb1d-b241-4a68-b84b-49a7868a3160" {:surname "Tisch" :name "Roman"}}
          :selected nil}})
