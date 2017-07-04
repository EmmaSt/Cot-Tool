(ns cot-tool.core
  (:require [cljs.pprint :refer [pprint]]
            [reagent.core :as reagent]))

(def race-names
  {:human "Mensch"
   :elf   "Elf"
   :dwarf "Zwerg"})

(def attributes [:strength :constitution :dexterty])

(def attribute-names
  {:strength "St"
   :constitution "Ko"
   :dexterty "Ge"})

(defn calc-skill-points [points]
  (if (zero? points)
    0
    (let [delta (quot (+ points 2) 3)]
      (+ delta (calc-skill-points (dec points))))))

(defn used-skill-points [skill-map]
  (reduce + (map calc-skill-points (vals skill-map))))

(defonce state
  (reagent/atom
   {:race :human
    :attr (zipmap attributes (repeat 8))
    :bought-life 0}))

(defn race-select []
  [:select {:on-change (fn [event]
                         (let [race-string (.-value (.-target event))
                               race (keyword race-string)]
                           (swap! state assoc :race race)))
            :value (:race @state)}
   (for [[race race-name] race-names]
     ^{:key race}
     [:option {:value race}
      race-name])])

(defn attribute-value [att]
  [:tr
   [:td (attribute-names att)]
   [:td (get-in @state [:attr att])]
   [:td [:button {:on-click (fn []
                              (swap! state update-in [:attr att] inc) )}
         "+"]]
   [:td [:button {:on-click (fn []
                             (swap! state update-in [:attr att] dec) )}
         "-"]]])

(defn attribute-values []
  [:table
   [:tbody
    (for [att attributes]
      ^{:key att}
      [attribute-value att])]])

(defn life []
  (let [constitution (:constitution (:attr @state))
        bought-life (:bought-life @state)
        calculated-life (+ constitution bought-life)]
    [:div
     "LeP "
     calculated-life
     " (" bought-life ") "
     [:button {:on-click (fn []
                           (swap! state update :bought-life inc))}
      "+"]
     " "
     [:button {:on-click (fn []
                           (swap! state update :bought-life dec))}
            "-"]]))

(defn current-state []
  [:pre (with-out-str (pprint @state))])

(defn app []
  [:h1 "Charakter erstellen"])

(defn ^:export run []
  (when-let [dom-node (.getElementById js/document "container")]
    (reagent/render app dom-node)))
