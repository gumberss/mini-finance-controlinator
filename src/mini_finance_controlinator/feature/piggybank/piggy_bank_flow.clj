(ns mini-finance-controlinator.feature.piggybank.piggy-bank-flow
  (:require
    [mini-finance-controlinator.feature.piggybank.piggy-bank-db :as p.db])
  (:use clojure.pprint)
  )

(defn key-string->keyword [[key value]]
  {(keyword key) value})

(defn create! [data]
  (let [piggy-bank (into {} (map key-string->keyword data))]
    (if (p.db/existing? (p.db/db-datomic) piggy-bank)
      (throw (ex-info "Piggy bank already exist" {:piggy-bank piggy-bank}))
      (p.db/create! (p.db/connection) piggy-bank))))

(defn get-all
  ([]  (p.db/get-all))
  ([date]  (p.db/get-all-by-date date)))

(defn update! [data]
  (let [piggy-bank (into {} (map key-string->keyword data))]
    (if (p.db/existing? (p.db/db-datomic) piggy-bank)
      (p.db/update! piggy-bank)
      (throw (ex-info "Piggy bank not found by name" {:piggy-bank/name piggy-bank})))))


